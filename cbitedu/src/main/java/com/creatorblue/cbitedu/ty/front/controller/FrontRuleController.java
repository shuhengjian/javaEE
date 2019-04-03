package com.creatorblue.cbitedu.ty.front.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.formula.functions.T;
import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.creatorblue.cbitedu.core.baseclass.controller.HihBaseController;
import com.creatorblue.cbitedu.core.exception.ControllerException;
import com.creatorblue.cbitedu.core.page.Page;
import com.creatorblue.cbitedu.ty.domain.TsysAttach;
import com.creatorblue.cbitedu.ty.domain.TtyBrand;
import com.creatorblue.cbitedu.ty.domain.TtyPrice;
import com.creatorblue.cbitedu.ty.domain.TtyProduct;
import com.creatorblue.cbitedu.ty.domain.TtyType;
import com.creatorblue.cbitedu.ty.front.service.TtyRuleTypeService;
import com.fasterxml.jackson.core.JsonProcessingException;

@Controller
@RequestMapping("/frontRuleController.do")
public class FrontRuleController extends HihBaseController  {
	
	@Autowired
	private TtyRuleTypeService ttyRuleTypeService;

	/**
	 * 访问中规车
	 * @return
	 */
	@RequestMapping(params = "method=rule")
	public ModelAndView rule(TtyProduct pro, HttpServletRequest req) {
		ModelAndView mv=new ModelAndView("front/rule-car");
		List<TsysAttach> logo = ttyRuleTypeService.selectLogo();
		List<TsysAttach> price = ttyRuleTypeService.selectPrice();
		List<TsysAttach> type = ttyRuleTypeService.selectType();
		String logoId = req.getParameter("pkid");
		String priceId = req.getParameter("priceId");
		String typeId = req.getParameter("typeId");
		if(logoId != null) {
			TtyBrand brand = new TtyBrand();
			brand.setBrandId(logoId);
			pro.setTtyBrand(brand);
		}
		if(priceId != null) {
			TtyPrice pri = new TtyPrice();
			pri.setPriceId(priceId);
			pro.setTtyPrice(pri);
		}
		if(typeId != null) {
			TtyType ty = new TtyType();
			ty.setTypeId(typeId);
			pro.setTtyType(ty);
		}
		Page page = (Page) this.getPage(req);
		if (req.getParameter("currentPage") != null) {
			page.setCurrentPage(Integer.parseInt(req.getParameter("currentPage")));
		}
		page.setPagination(true);
		pro.setPage(page);
		//String logoId = req.getParameter("brandId");
		List<TsysAttach> list = ttyRuleTypeService.selectAll(pro);
		mv.addObject("page",page);
		mv.addObject("logo",logo);
		mv.addObject("price",price);
		mv.addObject("type",type);
		mv.addObject("list", list);
		return mv;
		
	}
		
	
	@RequestMapping(params = "method=queryDetails")
	public ModelAndView selectDetails(String ProductId) {
		ModelAndView mv = new ModelAndView("/front/car-read");
		TtyProduct pro = new TtyProduct();
		pro = ttyRuleTypeService.selectDetails(ProductId);
		List<TsysAttach> list = ttyRuleTypeService.selectPicture(ProductId);
		mv.addObject("pro",pro);
		mv.addObject("list",list);
		return mv;
		
	}
	
	/**
	 * 查询全部品牌
	 */
	/*@RequestMapping(params = "method=querylogo")
	@ResponseBody
	public String queryLogo(HttpServletResponse response) 
				throws ControllerException, JsonProcessingException{
		List<TsysAttach> list = ttyRuleTypeService.selectLogo();
		JSONArray json = new JSONArray(list);
		return json.toString();
	}*/
	
	/**
	 * 查询价格区间
	 * @param response
	 * @return
	 * @throws ControllerException
	 * @throws JsonProcessingException
	 */
	/*@RequestMapping(params = "method=queryPirce")
	@ResponseBody
	public String queryPirce(HttpServletResponse response) 
				throws ControllerException, JsonProcessingException{
		List<TsysAttach> list = ttyRuleTypeService.selectPrice(); 
		JSONArray json = new JSONArray(list);
		return json.toString();
	}*/
	
	/**
	 * 查询全部分类
	 * @param response
	 * @return
	 * @throws ControllerException
	 * @throws JsonProcessingException
	 */
	/*@RequestMapping(params = "method=queryType")
	@ResponseBody
	public String queryType(HttpServletResponse response) 
				throws ControllerException, JsonProcessingException{
		List<TsysAttach> list = ttyRuleTypeService.selectType();
		JSONArray json = new JSONArray(list);
		return json.toString();
	}*/
	
	/**
	 * 查询全部产品
	 * @param response
	 * @return
	 * @throws ControllerException
	 * @throws JsonProcessingException
	 */
	/*@RequestMapping(params = "method=queryAll")
	@ResponseBody
	public ModelAndView queryAll(TtyProduct pro) 
				throws ControllerException, JsonProcessingException{
		List<TsysAttach> list = ttyRuleTypeService.selectAll(pro);
		ModelAndView mv = new ModelAndView();
		mv.addObject("list",list);
		return mv;
	}*/
	
	/**
	 * 
	 * @param ProId
	 * @return
	 */
	
	
}

