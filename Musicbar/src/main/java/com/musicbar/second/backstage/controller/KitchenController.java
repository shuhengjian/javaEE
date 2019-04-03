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
import com.musicbar.second.backstage.service.KitchenService;
import com.musicbar.second.comm.config.ConfigProperties;
import com.musicbar.second.comm.param.ParamUtil;
import com.musicbar.second.domain.TOrders;
import com.musicbar.second.domain.TOrdersInfo;
import com.musicbar.second.domain.TParameter;
import com.musicbar.second.domain.TRole;

import net.sf.json.JSONArray;

@Controller
@RequestMapping("/backstage")
public class KitchenController {
	@Autowired
	private KitchenService kitService;
	@Autowired
	private ParamUtil paramUtil;
	@Autowired
	private ConfigProperties configProperties;
	@Autowired
	private RedisUtils redisUtils;//缓存

	@RequestMapping("/queryKitchen")
	@LoggerAnnotation(begin="进入厨房查询商品列表页面",end="厨房查询商品列表页面结束")
	public ModelAndView queryKitchen() {
		ModelAndView mv = new ModelAndView("backstage/pages/kitchen/list.html");
		List<TOrdersInfo> list = kitService.selectCitchen("0");
		List<TOrdersInfo> list1 = kitService.selectCitchen("1");
		mv.addObject("list",list);
		mv.addObject("list1",list1);
		return mv;
	}

	@RequestMapping("/update")
	@LoggerAnnotation(begin="进入厨房查询商品列表页面",end="厨房查询商品列表页面结束")
	public ModelAndView updateKitchen(String kitState,String ordersInfoId,String ordersId) {
		ModelAndView mv = new ModelAndView("backstage/pages/kitchen/list.html");
		TOrdersInfo to = new TOrdersInfo();
		to.setKitState(kitState);
		to.setOrdersInfoId(ordersInfoId);
		kitService.updateKitchen(to);
		//查询订单详细表内 订单里面所有需要烹饪的商品
		List<TOrdersInfo> orlist = kitService.selectDetailByorderId(ordersId);
		int j=0;
		for(int i=0;i<orlist.size();i++) {
			TOrdersInfo toinfo = orlist.get(i);
			if(toinfo.getKitState().equals("2")) {
				j++;
			}
		}
		if(j==orlist.size()) {
			TOrders tOrders = new TOrders();
			tOrders.setOrdersState("1");
			tOrders.setOrdersId(ordersId);
			kitService.updateState(tOrders);
		}
		/*int pageSize = configProperties.getPageSize();//每页显示数量
		int pageNum = 1;//当前页
		PageHelper.startPage(pageNum,pageSize);*/
		List<TOrdersInfo> list = kitService.selectCitchen("0");
		List<TOrdersInfo> list1 = kitService.selectCitchen("1");
		/*PageInfo<TOrdersInfo> page = new PageInfo<>(list);*/
		mv.addObject("list", list);
		mv.addObject("list1",list1);
		return mv;
	}
	
	/*@RequestMapping("/kitchen_query")
	@ResponseBody
	@LoggerAnnotation(begin="进入厨房查询商品列表页面",end="厨房查询商品列表页面结束")
	public String kitchenPage(String ordersInfoId,String kitState,Integer pageNum) {
		
		TOrdersInfo to = new TOrdersInfo();
		to.setOrdersInfoId(ordersInfoId);
		to.setKitState(kitState);
		kitService.updateKitchen(to);
		//分页
		int pageSize = configProperties.getPageSize();//每页显示数量
		PageHelper.startPage(pageNum,pageSize);
		List<TOrdersInfo> list = kitService.selectCitchen("0");;//条件查询
		PageInfo<TOrdersInfo> page = new PageInfo<>(list);
		return JSONArray.fromObject(page).toString();
	}*/
	
	/*@RequestMapping("/updateKitchen")
	@ResponseBody
	@LoggerAnnotation(begin="进入厨房查询商品列表页面",end="厨房查询商品列表页面结束")
	public ModelAndView updateKitchen(String ordersInfoId,String kitState) {
		ModelAndView mv = new ModelAndView("backstage/pages/kitchen/list.html");
		TOrdersInfo to = new TOrdersInfo();
		to.setKitState(kitState);
		to.setOrdersInfoId(ordersInfoId);
		kitService.updateKitchen(to);
		List<TOrdersInfo> list = kitService.selectCitchen("0");
		List<TOrdersInfo> list1 = kitService.selectCitchen("1");
		mv.addObject("list",list);
		mv.addObject("list1",list1);
		return mv;
	}*/
	
	/*@RequestMapping("/update")
	@ResponseBody
	@LoggerAnnotation(begin="进入厨房查询商品列表页面",end="厨房查询商品列表页面结束")
	public String update(String ordersId,String orderDeal,Integer pageNum) {
		TOrders to = new TOrders();
		to.setOrdersDeal(orderDeal);
		to.setOrdersId(ordersId);
		kitService.updateKitchen(to);
		List<TParameter> paralist = paramUtil.getListByKey("role_state");//缓存中取状态值
		//分页
		int pageSize = configProperties.getPageSize();//每页显示数量
		PageHelper.startPage(pageNum,pageSize);
		List<TOrdersInfo> list = kitService.selectCitchen("0");;//条件查询
		PageInfo<TOrdersInfo> page = new PageInfo<>(list);
		return JSONArray.fromObject(page).toString();
	}*/
	
	/*@RequestMapping("/order_update")
	@ResponseBody
	@LoggerAnnotation(begin="进入厨房查询商品列表页面",end="厨房查询商品列表页面结束")
	public String orderUpdate(String orderState,String ordersId) {
		TOrders to = new TOrders();
		to.setOrdersState(orderState);
		to.setOrdersId(ordersId);
		kitService.updateState(to);
		String msg = "确定烹饪完成，可以出锅了吗？";
		JSONObject json = new JSONObject();
		json.put("msg", msg);
		return json.toString();
	}*/
}
