/**   
 * 功能描述：
 * @Package: com.musicbar.second.front.controller 
 * @author: shj 
 * @date: 2019年3月11日 上午11:03:59 
 */
package com.musicbar.second.front.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.musicbar.core.annotation.LoggerAnnotation;
import com.musicbar.second.backstage.service.TAttachService;

import com.musicbar.second.backstage.service.TTypeService;
import com.musicbar.second.comm.config.ConfigProperties;
import com.musicbar.second.domain.GoodsCart;
import com.musicbar.second.domain.GoodsCartItem;
import com.musicbar.second.backstage.service.TGoodsInfoService;
import com.musicbar.second.backstage.service.TOrderMealServices;
import com.musicbar.second.domain.TAttach;
import com.musicbar.second.domain.TGoodsInfo;
import com.musicbar.second.domain.TType;
import com.musicbar.second.backstage.service.TGoodsInfoService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * @ClassName: TTyepController.java
 * @Description: 前端显示商品&&点单
 * @version: v1.0.0
 * @author: shj
 * @date: 2019年3月11日 上午11:03:59
 */
@Controller
@RequestMapping("/front")
public class OrderMealController {
	@Autowired
	private TTypeService tTypeService; // 类型
	@Autowired
	private TGoodsInfoService tGoodsInfoService; // 商品
	@Autowired
	private TAttachService attachService; // 图片
	@Autowired
	private TOrderMealServices OrderMeal;
	@Autowired
	private ConfigProperties configProperties;
	
	@RequestMapping("/goGoodsCart")
	@LoggerAnnotation(begin = "进入购物车。。", end = "成功进入购物车。。")
	public String goGoodsCart() {
		return "front/payment";

	}

	/**
	 * 进入点单页面
	 */
	@RequestMapping("/order_meal")
	@LoggerAnnotation(begin = "进入点单", end = "点单结束")
	public ModelAndView selectTypeAndGoods(TGoodsInfo goods, Integer pageIndex, String typeId) {
		ModelAndView view = new ModelAndView("front/productList");
		if (typeId != null) {
			TType type = new TType();
			type.setTypeId(typeId);
			goods.setType(type);
		}
		// 查询所有类型
		List<TType> typeList = tTypeService.selectAll();
		if (typeList.get(0) != null) {// 第一个类型所对应的商品
			if (goods != null && goods.getType() != null || goods.getGoodsSpecial() != null) {
				selectGoods(view, goods, pageIndex);
			} else {
				goods.setType(typeList.get(0));
				selectGoods(view, goods, pageIndex);
			}
		}
		TType type = new TType();
		if (goods.getType() != null) {
			type = tTypeService.selectByPrimaryKey(goods.getType().getTypeId());
			TAttach attchType = attachService.selectByPkID(type.getTypeId());
			view.addObject("type", type);
			view.addObject("attchType", attchType);
		}
		if (goods.getGoodsSpecial() != null) {
			type.setTypeName("特价推荐");
			view.addObject("type", type);
			view.addObject("goodsSpecial", goods.getGoodsSpecial());
		}
		view.addObject("typeList", typeList);
		return view;
	}

	/**
	 * 根据类型筛选商品
	 */

	public void selectGoods(ModelAndView view, TGoodsInfo goods, Integer pageIndex) {
		if (pageIndex == null) {
			pageIndex = 1;
		}
		PageHelper.startPage(pageIndex, configProperties.getPageSize());
		List<TGoodsInfo> goodsList = tGoodsInfoService.fronteSlectAll(goods);
		PageInfo<TGoodsInfo> pageInfo = new PageInfo<TGoodsInfo>(goodsList);
		view.addObject("pageInfo", pageInfo);
		// view.addObject("goodsList", goodsList);

	}

	/**
	 * 根据类型筛选商品ajax
	 */
	@RequestMapping("/page_goods")
	@LoggerAnnotation(begin = "正在查询商品。。", end = "查询完毕。。")
	@ResponseBody
	public String selectGoods(TGoodsInfo goods, Integer pageIndex) {
		if (pageIndex == null) {
			pageIndex = 1;
		}
		PageHelper.startPage(pageIndex, 3);
		List<TGoodsInfo> goodsList = tGoodsInfoService.fronteSlectAll(goods);
		PageInfo<TGoodsInfo> pageInfo = new PageInfo<TGoodsInfo>(goodsList);
		return new JSONObject().fromObject(pageInfo).toString();
		// view.addObject("goodsList", goodsList);
	}
	/**
	 * 查询订单商品
	 * 
	 * @param req
	 * @return
	 */
	@RequestMapping("/addGoodsCart")
	@LoggerAnnotation(begin = "正在查询订单结算的商品。。", end = "查询完毕。。")
	@ResponseBody
	public String goGoodsCart( String goodsId,String goods) {
		String rs=OrderMeal.goodsInventory(goodsId,goods);
		return rs;
	}
	/**
	 * 查询购物车的商品
	 */
	@RequestMapping("/goods_car")
	@LoggerAnnotation(begin = "正在查询购物车的商品。。", end = "查询完毕。。")
	@ResponseBody
	public String selectGoodsCar(HttpServletRequest req) {
		String goods = (String) req.getParameter("goods");
		System.out.print(goods);
		String goodsCart = OrderMeal.frontOrderMeal(goods);
		return goodsCart;
	}

}
