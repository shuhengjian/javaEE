package com.musicbar.second.front.controller;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.ReactiveListCommands.LSetCommand;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.musicbar.core.annotation.LoggerAnnotation;
import com.musicbar.core.base.Result;
import com.musicbar.core.utils.DateUtils;
import com.musicbar.core.utils.StringUtil;
import com.musicbar.second.backstage.service.TAttachService;
import com.musicbar.second.backstage.service.TTypeService;
import com.musicbar.second.domain.GoodsCart;
import com.musicbar.second.domain.GoodsCartItem;
import com.musicbar.second.domain.TGoodsInfo;
import com.musicbar.second.domain.TOrders;
import com.musicbar.second.domain.TOrdersInfo;
import com.musicbar.second.backstage.service.TGoodsInfoService;
import com.musicbar.second.backstage.service.TOrderMealServices;
import com.musicbar.second.backstage.service.TOrdersService;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Controller
@RequestMapping("/front")
public class TOrderController {

	@Autowired
	private TOrdersService tOrdersService;
	@Autowired
	private TOrderMealServices tOrderMealServices;
	@Autowired
	private TTypeService tTypeService; // 类型
	@Autowired
	private TGoodsInfoService tGoodsInfoService; // 商品
	@Autowired
	private TAttachService attachService; // 图片

	@Autowired
	private TOrderMealServices OrderMeal;

	/**
	 * 根据订单号查询订单信息
	 * 
	 * @return
	 */
	@RequestMapping("/frontOrdersQuery")
	@LoggerAnnotation(begin = "进入查询订单方法", end = "查询商品订单结束")
	@ResponseBody
	public ModelAndView queryCode(TOrders tOrders) {
		ModelAndView mv = new ModelAndView("front/order");
		List<TOrders> list = tOrdersService.selectOrdCode(tOrders);
		mv.addObject("list", list);
		return mv;
	}

	/**
	 * 查询订单商品
	 * 
	 * @param req
	 * @return
	 */
	@RequestMapping("/goConfirmOrder")
	@LoggerAnnotation(begin = "正在查询订单结算的商品。。", end = "查询完毕。。")
	@ResponseBody
	public String goGoodsCart(HttpServletRequest req, RedirectAttributes attrs) {
		String _goods = (String) req.getParameter("goods");
		String rs = OrderMeal.goodsICartAccount(_goods);
		return rs;
	}
	
	/**
	 * 进入订单结算
	 * @return
	 */
	@RequestMapping("/ordersQuery")
	@LoggerAnnotation(begin = "进入订单结算方法", end = "订单结算结束")
	@ResponseBody
	public ModelAndView con() {
		ModelAndView mv = new ModelAndView("front/confirmOrder");
		return mv;
	}

	/**
	 * 查询订单商品
	 * 
	 * @param req
	 * @return
	 */
	@RequestMapping("/confirmOrder")
	@LoggerAnnotation(begin = "正在查询订单结算的商品。。", end = "查询完毕。。")
	@ResponseBody
	public String selectGoodsCar(HttpServletRequest req) {
		String goods = (String) req.getParameter("goods");
		String goodsCart = OrderMeal.frontOrderMeal(goods);
		return goodsCart;
	}

	/**
	 * 新增订单
	 * 
	 * @param req
	 * @return
	 */
	@RequestMapping("/confirmOrderAdd")
	@LoggerAnnotation(begin = "新增订单。。", end = "新增完毕。。")
	@ResponseBody
	public String confirmOrderAdd(HttpServletRequest req,HttpSession session) {
		String goods = (String) req.getParameter("goods");
		String rs = tOrdersService.confirmOrderAdd(req,goods);
		return rs;
	}

	/**
	 * 支付页面
	 * 
	 * @param mv
	 * @param session
	 * @return
	 */
	@RequestMapping("/conFirm")
	@LoggerAnnotation(begin = "进入支付方法", end = "支付方法结束")
	public ModelAndView conFirmOrder(ModelAndView mv, HttpSession session, String id) {
		String mobile = (String) session.getAttribute("mobile");
		if (mobile != null && id == null) {
			TOrders tOrders = tOrdersService.selectseMobileSe(mobile);
			mv.addObject("tOrders", tOrders);
		}
		if (id != null) {
			TOrders tOrders = tOrdersService.selectByPrimaryKey(id);
			mv.addObject("tOrders", tOrders);
			session.setAttribute("id", id);
		}
		mv.setViewName("front/confirm");
		return mv;
	}

	/**
	 * 订单支付
	 * 
	 * @return
	 * @throws IOException
	 * @throws IllegalStateException
	 */
	@RequestMapping("/OrdersSave")
	@ResponseBody
	@LoggerAnnotation(begin="进入订单支付方法",end="订单支付方法结束")
	public String update(HttpServletRequest req,HttpSession session){
		String conUpdate = tOrdersService.update(req, session);
		return conUpdate;
	}

	/**
	 * 交易情况
	 * 
	 * @param mv
	 * @param session
	 * @return
	 */
	@RequestMapping("/payS")
	@LoggerAnnotation(begin = "进入交易情况方法", end = "交易情况方法结束")
	public ModelAndView payS(ModelAndView mv, HttpSession session) {
		String mobile = (String) session.getAttribute("mobile");
		String id = (String) session.getAttribute("id");
		TOrders tOrders = null;
		if (mobile != null && id == null) {
			tOrders = tOrdersService.selectParameter(mobile);
		}
		if (id != null) {
			tOrders = tOrdersService.selectParameterId(id);
		}
		mv.addObject("tOrders", tOrders);
		mv.setViewName("front/paySucess");
		return mv;
	}
	
	/**
	 * 我的订单页面查询
	 * @param session
	 * @param req
	 * @return
	 */
	@RequestMapping("/TOrFrontqueryAll")
	@LoggerAnnotation(begin = "进入查询订单方法", end = "查询订单方法结束")
	public ModelAndView queryAll(HttpSession session,HttpServletRequest req,String code,String act) {
		ModelAndView mv	=new ModelAndView("front/order");
		String mobile = (String) session.getAttribute("mobile");
		String active = null;
		active = req.getParameter("active");
		if(act != null) {
			active = act;
		}
		if(active == null && code == null) {
			active = "0";
		}
		if(active == null && code != null) {
			active = (String) session.getAttribute("active");
		}
		session.setAttribute("active", active);
		if(mobile != null && !mobile.isEmpty()) {
			TOrders tOrders = new TOrders();
			tOrders.setOrdersCode(code);
			tOrders.setOrdersMobile(mobile);
			tOrders.setOrdersState(active);
			List<TOrders> list = tOrdersService.selectOrdInfo(tOrders);
			mv.addObject("list", list);
		}
		mv.addObject("active", active);
		return mv;
	} 

	/**
	 * 订单完成
	 * 
	 * @return
	 * @throws IOException
	 * @throws IllegalStateException
	 */
	@RequestMapping("/accomplish")
	@ResponseBody
	@LoggerAnnotation(begin = "进入订单完成方法", end = "订单完成方法结束")
	public ModelAndView accomplish(String id) {
		ModelAndView mv = new ModelAndView("redirect:TOrFrontqueryAll");
		String active = "1";
		TOrders tOrders = tOrdersService.selectByPrimaryKey(id);
		tOrders.setOrdersState("2");
		tOrdersService.updateCode(tOrders);
		mv.addObject("active", active);
		return mv;
	}
}
