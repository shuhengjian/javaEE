package com.musicbar.second.backstage.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.musicbar.core.annotation.LoggerAnnotation;
import com.musicbar.core.base.Result;
import com.musicbar.core.utils.StringUtil;
import com.musicbar.second.domain.TGoodsInfo;
import com.musicbar.second.domain.TOrders;
import com.musicbar.second.domain.TOrdersInfo;
import com.musicbar.second.mapper.TOrdersMapper;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
@Service
public class TOrdersService {
	@Autowired
	private TOrdersMapper tOrdersmapper;
	@Autowired
	private TGoodsInfoService tGoodsInfoService; 
	@Autowired
	private TOrderMealServices OrderMeal;
	
	/**
	 *  新增订单
	 * @param ordersId
	 * @return
	 */
	public String confirmOrderAdd(HttpServletRequest req,String goods) {
		JSONObject jb=	new JSONObject();
		HttpSession session = req.getSession();
		JSONArray goodsArry = JSONArray.fromObject(goods);
		String [] goodsIdArry=new String[goodsArry.size()];
		String mobile = req.getParameter("mobile");//手机号
		String tableNum = req.getParameter("tableNum");//台号
		String orderID = StringUtil.getUUIDValue();//订单id
		String code =null; //StringUtil.getSerialNumber(null);//订单号
		String dersWay = req.getParameter("dersWay");//下单类型
		
		synchronized (this) {
			//订单号累加
			TOrders payCode = tOrdersmapper.selectPayCode();
			if(payCode == null) {
				code = StringUtil.getSerialNumber(null);//订单号
			}else {
				String str = payCode.getOrdersCode();
				Long pCo = Long.parseLong(str);
				pCo = pCo+1;
				code = String.valueOf(pCo);
			}
			
		}
		int tbNum = 0;
		if(tableNum != null) {
			tbNum =Integer.valueOf(tableNum);
		}
		
		tbNum = Integer.valueOf(tableNum);
		BigDecimal sum =BigDecimal.ZERO;//总金额
		Integer numSum=0;//总数量
		List<TOrdersInfo > tgi =new ArrayList<>();
		TOrdersInfo ordersInfo=null;
		if(goodsArry.size() > 0) {
			for (int i = 0; i < goodsArry.size(); i++) {
				JSONObject job = goodsArry.getJSONObject(i); // 遍历 jsonarray 数组，把每一个对象转成 json 对象
				String goodsId = job.getString("goodsId"); // 每个商品的Id
				Integer num = job.getInt("num"); // 该商品的数量
				numSum += num;
				goodsIdArry[i]=goodsId;
				TGoodsInfo gList = tGoodsInfoService.selectGoodsCar(goodsId); // 根据ID查询商品
				BigDecimal price = gList.getGoodsPrice().multiply(BigDecimal.valueOf(num));// 小计=单价*数量
				ordersInfo =new TOrdersInfo();
				ordersInfo.setGoodsId(goodsId);
				ordersInfo.setGoodsName(gList.getGoodsName());
				ordersInfo.setGoodsNum(num);
				ordersInfo.setGoodsPrice(gList.getGoodsPrice());
				ordersInfo.setGoodsQuantity(gList.getGoodsQuantity());
				ordersInfo.setGoodsStandard(gList.getGoodsStandard());
				ordersInfo.setGoodsUnits(gList.getGoodsUnits());
				ordersInfo.setOrdersId(orderID);
				ordersInfo.setOrdersInfoId(StringUtil.getUUIDValue());
				sum = sum.add(price); // 总金额累加
				tgi.add(ordersInfo);
			}
			TOrders tOrders = new TOrders();
			tOrders.setOrdersId(orderID);
			tOrders.setOrdersCode(code);
			tOrders.setOrdersMobile(mobile);
			if(tbNum != 0) {
				tOrders.setTableNum(tbNum);
			}
			tOrders.setOrdersMoney(sum);
			tOrders.setOrdersNum(numSum);
			tOrders.setOrdersWay(dersWay);
			tOrders.setOrdersState("0");
			tOrders.setCreateTime(new Date());
			session.setAttribute("mobile", mobile);
			
			tOrdersmapper.insert(tOrders); 
			
			OrderMeal.modifyHostId(tgi);
		}
		jb.put("orderID",orderID);
		return jb.toString();
	}
	
	/**
	 * 订单支付
	 * @param req
	 * @param session
	 * @return
	 */
	public String update(HttpServletRequest req,HttpSession session){
		String mobile = (String) session.getAttribute("mobile");
		String id = (String) session.getAttribute("id");
		String radio = req.getParameter("radio");//支付方式
		TOrders tOrders = null;
		if(mobile != null && id == null) {
			tOrders = selectseMobileSe(mobile);
		}
		if(id != null) {
			tOrders = selectByPrimaryKey(id);
		}
		tOrders.setPaymentTime(new Date());
		tOrders.setOrdersState("1");
		tOrders.setPaymentMode(radio);
		tOrdersmapper.updateCode(tOrders);
		
		Result ok = Result.OK();
		//ok.addData("", );
		return ok.toString();
	}
	
	
	/**
	 * 根据id查询
	 * @param ordersId
	 * @return
	 */
	public TOrders selectByPrimaryKey(String ordersId) {
		return tOrdersmapper.selectByPrimaryKey(ordersId);
	};
	
	
	/**
	 * 根据条件查询所有订单基本信息
	 * @return
	 */
	public List<TOrders> selectAll(TOrders tOrders) {
		return tOrdersmapper.selectAll(tOrders);

	}  
   /**
	 * 查询订单详细信息
	 * @param tOrders
	 * @return
	 */
	public List<TOrders> selectorder(String ordersId){
		return tOrdersmapper.selectorder(ordersId);
	}
   
   public List<TOrders> selectOrdCode(TOrders tOrders){
	   return tOrdersmapper.selectOrdCode(tOrders);
   };
	/**
     * 根据订单号查询
    * @param ordersId
    * @return
    */
   public List<TOrders> selectCode(String ordersId) {
	   return tOrdersmapper.selectCode(ordersId);
   };
   
   /**
    * 根据状态号查询
   * @param ordersId
   * @return
   */
   public int selectState(String ordersId) {
	   return selectState(ordersId);
   };
   
   /**
    * 根据id查询商品信息
    * @return
    */
   public List<TOrders> selectOrdInfo(TOrders tOrders) {
	   return tOrdersmapper.selectOrdInfo(tOrders);
   }; 
   
   /**
    * 前端订单更新状态
    * @param tOrders
    * @return
    */
   public int updateCode(TOrders tOrders){
	   return tOrdersmapper.updateCode(tOrders);
   };
   
   
   /**
    * 根据session手机号查询
   * @param ordersId
   * @return
   */
  public TOrders selectseMobileSe(String ordersId){
	  return tOrdersmapper.selectseMobileSe(ordersId);
  };
  
  /**

   * 关联参数表根据手机号查询
   * @param ordersId
   * @return
   */
   public TOrders selectParameter(String tOrders) {
	  return tOrdersmapper.selectParameter(tOrders);
  };
  /**
   * 关联参数表根据id查询
   * @param ordersId
   * @return
   */
   public TOrders selectParameterId(String tOrders) {
	  return tOrdersmapper.selectParameterId(tOrders);
  };


}
   

