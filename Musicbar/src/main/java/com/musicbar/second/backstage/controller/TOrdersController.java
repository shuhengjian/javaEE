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
import com.musicbar.core.redis.RedisUtils;
import com.musicbar.second.backstage.service.TOrdersService;
import com.musicbar.second.comm.config.ConfigProperties;
import com.musicbar.second.comm.param.ParamTransformation;
import com.musicbar.second.comm.param.ParamUtil;
import com.musicbar.second.domain.TOrders;
import com.musicbar.second.domain.TParameter;

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
	private RedisUtils redisUtils;
	@Autowired
	private ConfigProperties configProperties;
	@Autowired
	private ParamUtil paramutil;
	/**
	 * 进入订单信息列表
	 * @return
	 */
	@RequestMapping("/orders_querylist")
	@LoggerAnnotation(begin="进入查询订单方法",end="查询商品订单结束")
	public ModelAndView list() {
		ModelAndView mv = new ModelAndView("backstage/pages/order/list");
		int pageSize = configProperties.getPageSize();//每页显示数量
		int pageNum = 1;//当前页
		PageHelper.startPage(pageNum,pageSize);
		List<TOrders> list = tOrdersServie.selectOrderAll();	
		PageInfo<TOrders> page = new PageInfo<>(list);
		//查询订单状态
		List<TParameter> statelist = paramutil.getListByKey("orders_state");//缓存中取状态值
		List<TParameter> waylist = paramutil.getListByKey("orders_way");//缓存中取状态值
		List<TParameter> modelist = paramutil.getListByKey("payment_mode");//缓存中取状态值
		mv.addObject("statelist",statelist);
		mv.addObject("waylist",waylist);
		mv.addObject("modelist",modelist);
		mv.addObject("page",page);
		return mv;
	}
	/**
	 * 条件查询
	 * @return
	 */
	@RequestMapping("/orders_query")
	@LoggerAnnotation(begin="进入查询订单方法",end="查询商品订单结束")
	@ResponseBody
	public String query(String ordersState,String ordersWay,String paymentMode,String ordersCode,Integer pageNum) {
		TOrders tOrders = new TOrders();
		tOrders.setOrdersState(ordersState);
		tOrders.setOrdersWay(ordersWay);
		tOrders.setPaymentMode(paymentMode);
		tOrders.setOrdersCode(ordersCode);
		List<TParameter> statelist = paramutil.getListByKey("orders_state");//缓存中取状态值
		List<TParameter> waylist = paramutil.getListByKey("orders_way");//缓存中取状态值
		List<TParameter> modelist = paramutil.getListByKey("payment_mode");//缓存中取状态值
		int pageSize = configProperties.getPageSize();//每页显示数量
		PageHelper.startPage(pageNum,pageSize);
		List<TOrders> list = tOrdersServie.selectAll(tOrders);
		PageInfo<TOrders> page = new PageInfo<>(list);
		return JSONArray.fromObject(page).toString();
	}
	/**
	 * 查询订单详细信息
	 * @param tOrders
	 * @return
	 */
	@RequestMapping("/orders_detail")
	@LoggerAnnotation(begin="进入查询订单方法",end="查询商品订单结束")
	@ResponseBody
	public ModelAndView queryorder(String ordersId) {
		ModelAndView mv = new ModelAndView("backstage/pages/order/detail");
		List<TOrders> list = tOrdersServie.selectorder(ordersId);
		mv.addObject("list",list);
		return mv;
		
	}
}
