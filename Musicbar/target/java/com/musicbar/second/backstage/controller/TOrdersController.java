package com.musicbar.second.backstage.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.musicbar.core.annotation.LoggerAnnotation;
import com.musicbar.second.backstage.service.TOrdersService;
import com.musicbar.second.comm.param.ParamTransformation;
import com.musicbar.second.domain.TOrders;

import net.sf.json.JSONArray;

/**
 * 订单管理层
 */
@Controller
@RequestMapping("/backstage")
public class TOrdersController {
	@Autowired
	private TOrdersService tOrdersServie;
	
	@Autowired
	private ParamTransformation paramTransformation;
	/**
	 * 进入订单信息列表
	 * @return
	 */
	@RequestMapping("/orders_querylist")
	@LoggerAnnotation(begin="进入查询订单方法",end="查询商品订单结束")
	public ModelAndView list() {
		ModelAndView mv = new ModelAndView("backstage/pages/order/list");
		return mv;
	}
	/**
	 * 根据订单号查询订单信息
	 * @return
	 */
	@RequestMapping("/orders_query")
	@LoggerAnnotation(begin="进入查询订单方法",end="查询商品订单结束")
	@ResponseBody
	public String query(TOrders tOrders,int pageNum) {
		if(tOrders.getOrdersWay() !=null) {
			if(tOrders.getOrdersWay().equals("手机下单")) {
				tOrders.setOrdersWay("1");
			}
			if(tOrders.getOrdersWay().equals("前台下单")) {
				tOrders.setOrdersWay("2");
			}
		}
		if(tOrders.getOrdersState() != null) {
			if(tOrders.getOrdersState().equals("待支付")) {
				tOrders.setOrdersState("0");
			}
			if(tOrders.getOrdersState().equals("待收货")) {
				tOrders.setOrdersState("1");
			}
			if(tOrders.getOrdersState().equals("完成")) {
				tOrders.setOrdersState("2");
			}
		} 
		if(tOrders.getPaymentMode() !=null) {
			if(tOrders.getPaymentMode().equals("微信")) {
				tOrders.setPaymentMode("0");
			}
			if(tOrders.getPaymentMode().equals("支付宝")) {
				tOrders.setPaymentMode("1");
			}
			if(tOrders.getPaymentMode().equals("现金支付")) {
				tOrders.setPaymentMode("2");
			}
		}
		int pageSize= 2;
		PageHelper.startPage(pageNum, pageSize);
		List<TOrders> list = tOrdersServie.selectAll(tOrders);
		paramTransformation.convertParam(list, "ordersState", "orders_state");
		paramTransformation.convertParam(list, "paymentMode", "payment_mode");
		PageInfo<TOrders> page = new PageInfo<>(list);
		return JSONArray.fromObject(page).toString();
	}
	/**
	 * 查询订单详细信息
	 * @param tOrders
	 * @return
	 */
	@RequestMapping("/orders_queryorder")
	@LoggerAnnotation(begin="进入查询订单方法",end="查询商品订单结束")
	@ResponseBody
	public ModelAndView queryorder(String ordersId) {
		ModelAndView mv = new ModelAndView("backstage/pages/order/detail");
		List<TOrders> list = tOrdersServie.selectorder(ordersId);
		mv.addObject("list",list);
		return mv;
		
	}
}
