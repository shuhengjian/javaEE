package com.musicbar.second.backstage.controller;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.musicbar.core.annotation.LoggerAnnotation;
import com.musicbar.core.base.Result;
import com.musicbar.core.utils.CookieUtil;
import com.musicbar.second.backstage.service.TGoodsInfoService;
import com.musicbar.second.backstage.service.TOrderMealServices;
import com.musicbar.second.backstage.service.TOrdersService;
import com.musicbar.second.backstage.service.TTypeService;
import com.musicbar.second.domain.GoodsCart;
import com.musicbar.second.domain.TGoodsInfo;
import com.musicbar.second.domain.TOrders;
import com.musicbar.second.domain.TType;

/**
 * 后台点单收银
 * 
 * @author laj
 *
 */
@Controller
@RequestMapping("/backstage")
public class TCashierController {
	@Autowired
	private TTypeService tTypeService;// 商品分类

	@Autowired
	private TGoodsInfoService tGoodsInfoService;// 商品信息

	@Autowired
	private TOrderMealServices tOrderMealServices;// 点单
	
	@Autowired
	private TOrdersService tOrdersService;//结算

	/**
	 * 进入点单页面
	 * 
	 * @return
	 */
	@RequestMapping("/cashier_list")
	public ModelAndView list(TGoodsInfo goods) {
		ModelAndView mv = new ModelAndView("backstage/pages/cashier/index");
		// 查询所有类型
		List<TType> typeList = tTypeService.selectAll();
		// 查询商品信息列表
		List<TGoodsInfo> good = tGoodsInfoService.selectAll(goods);
		mv.addObject("typeList", typeList);
		mv.addObject("good", good);
		return mv;
	}

	/**
	 * 查询购物车
	 * 
	 * @return
	 */
	@RequestMapping("/select_Car")
	@ResponseBody
	public String selectCar(HttpServletRequest req, HttpServletResponse rep) {
		CookieUtil cu = new CookieUtil(); // cook工具类
		Cookie cookie = cu.getCookieByName(req, cu.BACK_CART);
		String goodsCart = null;
		if (cookie != null) {
			goodsCart = tOrderMealServices.frontOrderMeal(cu.getCookieDecoder(cookie.getValue()));
		}
		return goodsCart;
	}

	/**
	 * 添加购物车
	 * 
	 * @return
	 */
	@RequestMapping("/add_Car")
	@ResponseBody
	public String addCar(HttpServletRequest req, String goodsId, HttpServletResponse rep) {
		String goodsCart = tOrderMealServices.backstageOrderMeal(req, goodsId, rep);
		return goodsCart;
	}

	/**
	 * 修改购物车的数量
	 * 
	 * @param req
	 * @param goodsId
	 * @param num
	 * @return
	 */
	@RequestMapping("/update_car")
	@ResponseBody
	public String updateCar(HttpServletRequest req, HttpServletResponse rsp, String goodsId, String num,Boolean isAlter) {
		String cont = tOrderMealServices.backstageModified(req, rsp, goodsId, num,isAlter);
		System.out.println(cont);
		return cont;
	}

	/**
	 * 删除购物车的商品
	 * 
	 * @param req
	 * @param goodsId
	 * @param num
	 * @return
	 */
	@RequestMapping("/delete_car")
	@ResponseBody
	public String deleteCar(HttpServletRequest req, HttpServletResponse rsp, String goodsId) {
		String cont = tOrderMealServices.backstageDeleteModified(req, rsp, goodsId);
		System.out.println(cont);
		return cont;
	}
	/**
	 * 进入支付页面
	 * @return
	 */
	@RequestMapping("/result_list")
	public ModelAndView queryresult(String orderID) {
		ModelAndView mv = new ModelAndView("backstage/pages/cashier/result");
		TOrders tOrders = tOrdersService.selectByPrimaryKey(orderID);
		mv.addObject("tOrders",tOrders);
		return mv;
	}
	/**
	 * 结算
	 */
	@RequestMapping("/result_settlement")
	@ResponseBody
	public String settlement(HttpServletRequest req) {
		String goods = req.getParameter("goods");
		CookieUtil coolie = new CookieUtil();
		Cookie cookie =  coolie.getCookieByName(req, coolie.BACK_CART);
		if(cookie !=null) {
			goods = cookie.getValue();
		}
		String settlement = tOrdersService.confirmOrderAdd(req,goods);
		return settlement;
	}
	/**
	 * 支付
	 * @param orderID
	 * @param radio
	 * @return
	 */
	@RequestMapping("/back_ordersave")
	@ResponseBody
	@LoggerAnnotation(begin="进入后台订单支付方法",end="订单后台支付方法结束")
	public String save(String ordersId,String radio) {
		TOrders tOrders = tOrdersService.selectByPrimaryKey(ordersId);
		Result rs=Result.error();
		tOrders.setPaymentTime(new Date());
		tOrders.setOrdersState("1");
		tOrders.setPaymentMode(radio);
		int count = tOrdersService.updateCode(tOrders);
		JSONObject json=new JSONObject();
		if(count >0) {
			rs.setCode(1);
			rs.setMsg("下单成功");
		}else {
			rs.setMsg("下单失败");
		}
		rs.setData(json);
		return rs.toString();
	}
	/**
	 * 计算找零
	 * @param valu
	 * @param total
	 * @return
	 */
	@RequestMapping("/resu_count")
	@ResponseBody
	public String count(float valu,float total) {
		JSONObject json=new JSONObject();
		 BigDecimal b1 = new BigDecimal(Float.toString(valu));
		 BigDecimal b2 = new BigDecimal(Float.toString(total));
		 float change = b1.subtract(b2).floatValue(); 
		json.put("change", change);
		return json.toString();
	}
}
