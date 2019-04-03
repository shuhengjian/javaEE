package com.creatorblue.cbitedu.ty.front.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.annotations.Param;
import org.json.JSONArray;
import org.json.JSONObject;
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
import com.creatorblue.cbitedu.ty.front.service.TtyPriceService;
import com.creatorblue.cbitedu.ty.front.service.TtyProductService;
import com.fasterxml.jackson.core.JsonProcessingException;
/**
 * 产品列表展示
 * @author laj
 *
 */
@Controller
@RequestMapping("/ttyProductController.do")
public class TtyProductController extends HihBaseController{
	@Autowired
	private TtyProductService ttyProductService;
	
	@Autowired
	private TtyPriceService ttyPriceService; // 价格区间
	
	/**
	 * 访问平行进口列表
	 * @param request
	 * @param response
	 * @return
	 * @throws ControllerException
	 */
	@RequestMapping(params = "method=list")
	public ModelAndView list(TtyProduct pro,HttpServletRequest req,HttpSession session,@Param("code")String code){
		TtyProduct t1 = (TtyProduct)session.getAttribute("pro");
		TtyType typ = new TtyType();
		TtyProduct pd = null;
		typ.setTypeStart(String.valueOf(code));
		if(t1 != null && t1.getTtyType().getTypeId() != null) {
			typ.setTypeId(t1.getTtyType().getTypeId());
		}
		if(pro.getTtyType() !=null && pro.getTtyType().getTypeId() !=null) {
			typ.setTypeId(pro.getTtyType().getTypeId());
		}
		ModelAndView mv = new ModelAndView("/front/parallel-car");
		if(session.getAttribute("pro") == null) {
			
			session.setAttribute("pro", pro);
		}
		pd = (TtyProduct) session.getAttribute("pro");
		pro.setTtyType(typ);
		if(pd != null) {
			if(pro != null && pro.getTtyBrand() != null) {
				pd.setTtyBrand(pro.getTtyBrand());
			}
			if(pro!=null && pro.getTtyPrice()!=null) {
				TtyPrice t=ttyPriceService.selectByPrimaryKey(pro.getTtyPrice().getPriceId());
				pd.setTtyPrice(t);
			}
			if(pro!=null && pro.getTtyType()!=null) {
				pd.setTtyType(pro.getTtyType());
			}
			session.setAttribute("pro",pd);
		}
		List<TsysAttach> logo = ttyProductService.selectLogo();
		List<TsysAttach> price = ttyProductService.selectPrice();
		List<TsysAttach> type = ttyProductService.selectType(code);
		Page page = (Page) this.getPage(req);
		page.setPagination(true);
		if (req.getParameter("currentPage") != null) {
			page.setCurrentPage(Integer.parseInt(req.getParameter("currentPage")));
		}
		pd.setPage(page);
		List<TtyProduct> list = ttyProductService.selectAll(pd);
		mv.addObject("page", page);
		mv.addObject("logo", logo);
		mv.addObject("price", price);
		mv.addObject("type", type);
		mv.addObject("list", list);
		mv.addObject("code", code);
		return mv;
	}
	

	
	@RequestMapping(params = "method=queryDetails")
	public ModelAndView selectDetails(String productId,HttpServletRequest request, HttpServletResponse response,@Param("code")String code) {
		ModelAndView mv = new ModelAndView("/front/car-read");
		TtyProduct product = new TtyProduct();
		product = ttyProductService.selectDetails(productId);
		List<TsysAttach> list = ttyProductService.selectPicture(productId);
		mv.addObject("product", product);
		mv.addObject("list", list);
		queryNewsProduct(request, response, mv);
		queryLikeProduct(request, response, mv);
		mv.addObject("code", code);
		return mv;
	}
	
	/**
	 * 查询首页最新产品
	 * 
	 */
	@RequestMapping(params = "method=newsProduct")
	public void queryNewsProduct(HttpServletRequest request, HttpServletResponse response, ModelAndView mv) {
		List<TtyProduct> list = ttyProductService.selectNew();
		mv.addObject("newProductList", list);
	}

	/**
	 * 查询首页热销产品
	 * 
	 */
	@RequestMapping(params = "method=likeProduct")
	public void queryLikeProduct(HttpServletRequest request, HttpServletResponse response, ModelAndView mv) {
		List<TtyProduct> list = ttyProductService.selectLike();
		mv.addObject("likeProductList", list);
	}
}
