package com.musicbar.second.backstage.controller;

import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.musicbar.core.utils.CookieUtil;
import com.musicbar.second.backstage.service.TGoodsInfoService;
import com.musicbar.second.backstage.service.TOrderMealServices;
import com.musicbar.second.backstage.service.TOrdersService;
import com.musicbar.second.backstage.service.TTypeService;
import com.musicbar.second.domain.GoodsCart;
import com.musicbar.second.domain.TGoodsInfo;
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
	 * 进入结算页面
	 * @return
	 */
	@RequestMapping("/result_list")
	public ModelAndView queryresult(String orderID) {
		ModelAndView mv = new ModelAndView("backstage/pages/cashier/result");
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
}
