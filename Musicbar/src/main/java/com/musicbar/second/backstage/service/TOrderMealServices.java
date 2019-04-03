/**   
 * 功能描述：
 * @Package: com.musicbar.second.backstage.service 
 * @author: shj 
 * @date: 2019年3月18日 下午4:02:57 
 */
package com.musicbar.second.backstage.service;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.musicbar.core.base.Result;
import com.musicbar.core.utils.CookieUtil;
import com.musicbar.second.domain.GoodsCart;
import com.musicbar.second.domain.GoodsCartItem;
import com.musicbar.second.domain.TGoodsInfo;
import com.musicbar.second.domain.TOrdersInfo;
import com.musicbar.second.mapper.TOrdersInfoMapper;
import com.musicbar.second.mapper.TOrdersMapper;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * @ClassName: TOrderMeal.java
 * @Description:商品点单 && 购物车
 * @version: v1.0.0
 * @author: shj
 * @date: 2019年3月18日 下午4:02:57
 */
@Service
public class TOrderMealServices {
	@Autowired
	private TGoodsInfoService tGoodsInfoService; // 商品
	@Autowired
	private TOrdersInfoMapper mapper;

	// 前台下单
	public String frontOrderMeal(String goods) {
		GoodsCart goodsCar = null; // 购物车
		try {
			JSONArray goodsArry = JSONArray.fromObject(goods);
			if (goodsArry.size() > 0) {
				goodsCar = new GoodsCart(); // 购物车
				List<GoodsCartItem> cartItems = new ArrayList<>(); // 购物车中的商品集合
				BigDecimal sum = BigDecimal.ZERO;
				Integer numSum = 0; // 总数量
				String[] types = new String[goodsArry.size()];
				GoodsCartItem cartItem = null;// 每一项商品
				for (int i = 0; i < goodsArry.size(); i++) {
					cartItem = new GoodsCartItem();
					JSONObject job = goodsArry.getJSONObject(i); // 遍历 jsonarray 数组，把每一个对象转成 json 对象
					String goodsId = job.get("goodsId") + ""; // 每个商品的Id
					Integer num = (Integer) job.get("num"); // 该商品的数量
					numSum += num;
					types[i] = job.get("typeId") + ""; // 类型
					TGoodsInfo gList = tGoodsInfoService.selectGoodsCar(goodsId); // 根据ID查询商品
					BigDecimal price = gList.getGoodsPrice().multiply(BigDecimal.valueOf(num));// 小计=单价*数量
					sum = sum.add(price); // 总金额累加
					cartItem.setGoodsInfo(gList);
					cartItem.setNum(num);
					cartItem.setSriceSubtotal(price);
					cartItems.add(cartItem);
				}
				List<String> list = new ArrayList<String>();
				for (int i = 0; i < types.length; i++) {
					if (!list.contains(types[i])) {
						list.add(types[i]);
					}
				}
				goodsCar.setTypeNum(list.size());
				goodsCar.setSum(sum);
				goodsCar.setNum(numSum);
				goodsCar.setGoodsList(cartItems);

			}
		} catch (NullPointerException e) {
			e.printStackTrace();
		}
		return JSONObject.fromObject(goodsCar).toString();

	}

	// 后代点单
	public String backstageOrderMeal(HttpServletRequest req, String goodsId, HttpServletResponse rep) {
		CookieUtil cu = new CookieUtil(); // cook工具类
		GoodsCart cart = new GoodsCart();
		String goodsCart = null;
		Cookie cookie = null;
		int num = 1;
		Result rs = Result.OK();
		boolean isGoods = false; // 标识，判断cookie是否已存在点击的商品
		if (goodsId != null && !goodsId.isEmpty()) { // 将商品添加进购物车
			TGoodsInfo goodsInfo = tGoodsInfoService.selectGoodsById(goodsId);
			cookie = cu.getCookieByName(req, cu.BACK_CART);
			JSONArray goodsArry = new JSONArray();
			JSONObject jsObj = null;
			GoodsCartItem cartItem = new GoodsCartItem();// 每一项商品;
			if (cookie != null) {
				BigDecimal sriceSubtotal = BigDecimal.ZERO;
				goodsArry = JSONArray.fromObject(cookie.getValue());
				for (int i = 0; i < goodsArry.size(); i++) {
					JSONObject job = goodsArry.getJSONObject(i); // 遍历 jsonarray 数组，转成 json 对象
					if (goodsInfo.getGoodsId().equals(job.get("goodsId"))) { // cookie已存在点添加的商品
						isGoods = true;
						num += job.getInt("num");// 数量
						if (num > goodsInfo.getGoodsStock()) {
							rs.setCode(0);
							rs.setMsg(goodsInfo.getGoodsName() + "库存不足!库存数:" + goodsInfo.getGoodsStock());
							break;
						}
						sriceSubtotal = sriceSubtotal.multiply(BigDecimal.valueOf(num));// 商品小计
						cartItem.setGoodsInfo(goodsInfo); // 商品信息
						cartItem.setNum(num); // num
						cartItem.setSriceSubtotal(sriceSubtotal); // 小计
						jsObj = new JSONObject();
						jsObj.put("goodsId", goodsId); // 商品ID
						jsObj.put("num", num); // 商品数量
						goodsArry.set(i, jsObj); //
						break;
					}
				}
			} else {
				jsObj = new JSONObject();
				if (num > goodsInfo.getGoodsStock()) {
					rs.setCode(0);
					rs.setMsg(goodsInfo.getGoodsName() + "库存不足!库存数:" + goodsInfo.getGoodsStock());
				} else {
					isGoods = true;
					cartItem.setGoodsInfo(goodsInfo);
					cartItem.setNum(num); // 数量
					cartItem.setSriceSubtotal(goodsInfo.getGoodsPrice());// 小计

					jsObj.put("num", num);
					jsObj.put("goodsId", goodsId);
					goodsArry.add(jsObj); //
				}
			}
			if (!isGoods) { //
				jsObj = new JSONObject();
				if (num > goodsInfo.getGoodsStock()) {
					rs.setCode(0);
					rs.setMsg(goodsInfo.getGoodsName() + "库存不足!库存数:" + goodsInfo.getGoodsStock());
				} else {
					cartItem.setGoodsInfo(goodsInfo);
					cartItem.setNum(num); // 数量
					cartItem.setSriceSubtotal(goodsInfo.getGoodsPrice());// 小计

					jsObj.put("num", num);
					jsObj.put("goodsId", goodsId);
					goodsArry.add(jsObj); //
				}

			}
			if (rs.getCode() == 1) {
				cu.addCookie(req, rep, cu.BACK_CART, goodsArry.toString());// 发送到服务端
				BigDecimal sumPrice = BigDecimal.ZERO;
				for (int i = 0; i < goodsArry.size(); i++) {
					JSONObject job = goodsArry.getJSONObject(i); // 遍历 jsonarray 数组，转成 json 对象
					String gId = job.getString("goodsId");
					int gNum = job.getInt("num");
					TGoodsInfo _goodsInfo = tGoodsInfoService.selectGoodsById(gId);
					BigDecimal _sriceSubtotal = _goodsInfo.getGoodsPrice().multiply(BigDecimal.valueOf(gNum));// 商品小计
					sumPrice = sumPrice.add(_sriceSubtotal);
				}
				JSONObject jb = JSONObject.fromObject(goodsInfo);
				jb.put("num", num);
				jb.put("number", goodsArry.size());
				jb.put("sumPrice", sumPrice);
				rs.setData(jb);
			}
		}

		return rs.toString();

	}

	/**
	 * 
	 * @Function: TOrderMealServices.java
	 * @param:购物车商品数量修改 -1
	 *                      or 1 or 大于1
	 * @return：修改后的值
	 */
	public String backstageModified(HttpServletRequest req, HttpServletResponse rep, String goodsId, String num,
			boolean isAlter) {
		CookieUtil cu = new CookieUtil(); // cook工具类
		JSONObject jsonObj = new JSONObject();
		BigDecimal sriceSubtotal = BigDecimal.ZERO;
		if (goodsId != null && !goodsId.isEmpty()) {
			Cookie cookie = cu.getCookieByName(req, cu.BACK_CART);
			if (cookie != null) {
				JSONArray goodsArry = JSONArray.fromObject(cookie.getValue());
				for (int i = 0; i < goodsArry.size(); i++) {
					JSONObject job = goodsArry.getJSONObject(i); // 遍历 jsonarray 数组，转成 json 对象
					if (goodsId.equals(job.get("goodsId"))) { // 找到点击的商品
						Pattern pattern = Pattern.compile("[-1-9]*");
						Matcher isNum = pattern.matcher(num);
						TGoodsInfo goodsInfo = tGoodsInfoService.selectGoodsById(goodsId);
						if (isNum.matches()) {
							int _num = job.getInt("num");
							if ((Integer.parseInt(num) == 1 || Integer.parseInt(num) == -1) && isAlter) {
								_num += Integer.parseInt(num);// 数量、
								job.put("num", _num);
							}
							if (Integer.parseInt(num) >= 1 && !isAlter) {
								job.put("num", num);
							}
							sriceSubtotal = goodsInfo.getGoodsPrice().multiply(BigDecimal.valueOf(_num));// 商品小计
							job.put("goodsId", goodsId);
							jsonObj.put("sriceSubtotal", sriceSubtotal);
							jsonObj.put("num", job.getInt("num"));
							goodsArry.set(i, job);
							break;
						} else {
							return null;
						}
					}
				}
				BigDecimal sumPrice = BigDecimal.ZERO;
				for (int i = 0; i < goodsArry.size(); i++) {
					JSONObject job = goodsArry.getJSONObject(i); // 遍历 jsonarray 数组，转成 json 对象
					String gId = job.getString("goodsId");
					int gNum = job.getInt("num");
					TGoodsInfo goodsInfo = tGoodsInfoService.selectGoodsById(gId);
					BigDecimal _sriceSubtotal = goodsInfo.getGoodsPrice().multiply(BigDecimal.valueOf(gNum));// 商品小计
					sumPrice = sumPrice.add(_sriceSubtotal);
				}
				jsonObj.put("sumPrice", sumPrice);
				cu.addCookie(req, rep, cu.BACK_CART, goodsArry.toString());// 发送到服务端
			}
		}
		return jsonObj.toString(); // 返回修改改后的数量
	}

	public int insert(List<TOrdersInfo> ordersInfo) {
		return mapper.insert(ordersInfo);
	};

	public int modifyHostId(List<TOrdersInfo> ordersInfo) {
		return mapper.modifyHostId(ordersInfo);
	};

	// 后台点单功能删除购物车中的商品
	public String backstageDeleteModified(HttpServletRequest req, HttpServletResponse rep, String goodsId) {
		CookieUtil cu = new CookieUtil(); // cook工具类
		Result rs = Result.error();
		if (goodsId != null && !goodsId.isEmpty()) {
			Cookie cookie = cu.getCookieByName(req, cu.BACK_CART);
			if (cookie != null) {
				JSONArray goodsArry = JSONArray.fromObject(cookie.getValue());
				for (int i = 0; i < goodsArry.size(); i++) {
					JSONObject job = goodsArry.getJSONObject(i); // 遍历 jsonarray 数组，转成 json 对象
					if (goodsId.equals(job.get("goodsId"))) { // 找到点击的商品
						goodsArry.remove(i);
						rs.setCode(1);
						rs.setMsg("删除成功!");
						cookie.setValue(goodsArry.toString());
						break;
					}
				}
				BigDecimal sumPrice = BigDecimal.ZERO;
				for (int i = 0; i < goodsArry.size(); i++) {
					JSONObject job = goodsArry.getJSONObject(i); // 遍历 jsonarray 数组，转成 json 对象
					String gId = job.getString("goodsId");
					int gNum = job.getInt("num");
					TGoodsInfo goodsInfo = tGoodsInfoService.selectGoodsById(gId);
					BigDecimal _sriceSubtotal = goodsInfo.getGoodsPrice().multiply(BigDecimal.valueOf(gNum));// 商品小计
					sumPrice = sumPrice.add(_sriceSubtotal);
				}
				JSONObject jsobj = new JSONObject();
				jsobj.put("sumPrice", sumPrice);
				rs.setData(jsobj);
				cu.addCookie(req, rep, cu.BACK_CART, goodsArry.toString());// 发送到服务端
			} else {
				rs.setMsg("删除失败");
			}
		} else {
			rs.setMsg("删除失败");
		}
		return rs.toString();
	}

	/**
	 * @Function: TOrderMealServices.java
	 * @param:商品id 以及商品
	 *                 加入购物车
	 */
	public String goodsInventory(String goodsId, String goods) {
		Result rs = Result.OK();
		boolean isNum = true;
		JSONArray goodsArry = JSONArray.fromObject(goods);
		if (goodsArry != null && goodsArry.size() > 0) {
			for (int i = 0; i < goodsArry.size(); i++) {
				JSONObject job = goodsArry.getJSONObject(i);
				if (goodsId.equals(job.getString("goodsId"))) {
					// 遍历 jsonarray 数组，转成 json 对象
					TGoodsInfo goodsInfo = tGoodsInfoService.selectGoodsById(job.getString("goodsId"));
					Integer num = job.getInt("num");
					if (goodsInfo.getGoodsStock() <= num) {
						rs.setCode(0);
						rs.setMsg(goodsInfo.getGoodsName() + "库存不足!库存数：" + goodsInfo.getGoodsStock());
						isNum = false;
						break;
					}
				}
			}
			rs.setData(goodsArry);
		}
		return rs.toString();
	}

	/**
	 * @Function: TOrderMealServices.java
	 * @param:商品 arry
	 *               购物车结算
	 */
	public String goodsICartAccount(String goods) {
		Result rs = Result.error();
		boolean isNum = true;
		JSONArray goodsArry = JSONArray.fromObject(goods);
		if (goodsArry != null && goodsArry.size() > 0) {
			for (int i = 0; i < goodsArry.size(); i++) {
				JSONObject job = goodsArry.getJSONObject(i); // 遍历 jsonarray 数组，转成 json 对象
				TGoodsInfo goodsInfo = tGoodsInfoService.selectGoodsById(job.getString("goodsId"));
				Integer num = job.getInt("num");
				if (goodsInfo.getGoodsStock() < num) {
					rs.setMsg(goodsInfo.getGoodsName() + "库存不足!库存数：" + goodsInfo.getGoodsStock());
					isNum = false;
					break;
				}
			}
			if (isNum) {
				rs.setCode(1);
				rs.setData(goodsArry);
			}
		}
		return rs.toString();
	}

}
