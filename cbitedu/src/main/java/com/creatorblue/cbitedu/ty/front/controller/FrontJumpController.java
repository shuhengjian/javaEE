/**   
 * 功能描述：
 * @Package: com.creatorblue.cbitedu.ty.front.controller 
 * @author: shj 
 * @date: 2019年2月21日 上午11:39:10 
 */
package com.creatorblue.cbitedu.ty.front.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.creatorblue.cbitedu.core.baseclass.controller.HihBaseController;
import com.creatorblue.cbitedu.ty.domain.TsysAttach;
import com.creatorblue.cbitedu.ty.domain.TtyAdvertising;
import com.creatorblue.cbitedu.ty.domain.TtyBrand;
import com.creatorblue.cbitedu.ty.domain.TtyNews;
import com.creatorblue.cbitedu.ty.domain.TtyPrice;
import com.creatorblue.cbitedu.ty.domain.TtyProduct;
import com.creatorblue.cbitedu.ty.domain.TtyType;
import com.creatorblue.cbitedu.ty.front.service.TtyAdvertisingService;
import com.creatorblue.cbitedu.ty.front.service.TtyBrandService;
import com.creatorblue.cbitedu.ty.front.service.TtyNewsFrontService;
import com.creatorblue.cbitedu.ty.front.service.TtyPriceService;
import com.creatorblue.cbitedu.ty.front.service.TtyProductService;
import com.creatorblue.cbitedu.ty.front.service.TtyTypeService;

/**
 * @Description: 首页查询
 */
@Controller
@RequestMapping("/frontJumpController.do")
public class FrontJumpController extends HihBaseController {
	@Autowired
	private TtyAdvertisingService ttyAdvertisingService; // 广告
	@Autowired
	private TtyBrandService ttyBrandService; // 品牌
	@Autowired
	private TtyPriceService ttyPriceService; // 价格区间
	@Autowired
	private TtyTypeService ttyTypeService; // 类型
	@Autowired
	private TtyProductService ttyProductService; // 产品 汽车
	@Autowired
	private TtyNewsFrontService ttyNewsFrontService; // 新闻

	@RequestMapping(params = "method=index")
	public ModelAndView index(HttpServletRequest req, HttpServletResponse rsp, TtyProduct product,
			HttpSession session) {
		if(session.getAttribute("product")==null) {
			session.setAttribute("product",product);
		}
		ModelAndView view = new ModelAndView("front/index");
		TtyProduct	pd=(TtyProduct)session.getAttribute("product");
		if(pd!=null) {
			if(product!=null && product.getTtyBrand()!=null) {
				if (product.getTtyBrand().getBrandId().equals("index")) {
					product.setTtyBrand(null);
				}
				pd.setTtyBrand(product.getTtyBrand());
			}
			if(product!=null && product.getTtyPrice()!=null) {
				if (product.getTtyPrice().getPriceId().equals("index")) {
					product.setTtyPrice(null);
				}else if(product.getTtyPrice()!=null){
					TtyPrice t=ttyPriceService.selectByPrimaryKey(product.getTtyPrice().getPriceId());
					product.setTtyPrice(t);
				}
				pd.setTtyPrice(product.getTtyPrice());
			}
		
		
			if(product!=null && product.getTtyType()!=null) {
				if (product.getTtyType().getTypeId().equals("index")) {
					product.setTtyType(null);
				}
				pd.setTtyType(product.getTtyType());
			}
			session.setAttribute("product",pd);
		}
		queryAdvertising(req, rsp, view);
		queryBrand(req, rsp, view);
		queryPrice(req, rsp, view);
		queryType(req, rsp, view);
		List<TtyProduct> list = ttyProductService.select(pd);
		view.addObject("productList", list);
		queryNewsProduct(req, rsp, view);
		queryLikeProduct(req, rsp, view);
		queryTyDynamic(req, rsp, view);
		return view;
	}

	/**
	 * 查询首页广告
	 * 
	 */
	@RequestMapping(params = "method=advertising_query")
	public void queryAdvertising(HttpServletRequest request, HttpServletResponse response, ModelAndView view) {
		List<TsysAttach> list = ttyAdvertisingService.select(null);
		Map<String, Object> map=new HashMap<String, Object>();
		if(list.size()<3 && list.size()>0) {
			String id=list.get(0).getPkid();
			map.put("id",id);
			Integer num =0;
			if(list.size()==2) {
				num=1;
			}
			else if(list.size()==1) {
				num=2;
			}
			map.put("num",num);
		}else {
			map.put("id","");
			map.put("num",3);
		}
		List<TsysAttach> _list= ttyAdvertisingService.selectDeficiency(map);
		for (TsysAttach tsysAttach : _list) {
			if (list.size()<3) {
				list.add(tsysAttach);
			}
		}
		view.addObject("AdvertisingList", list);
		if(list.size()>0) {
			view.addObject("AdvertisingSize", list.size());
		}
	}

	/**
	 * 查询首页品牌
	 * 
	 */
	@RequestMapping(params = "method=brand_query")
	public void queryBrand(HttpServletRequest request, HttpServletResponse response, ModelAndView view) {
		List<TtyBrand> list = ttyBrandService.select(null);
		view.addObject("BrandList", list);
	}

	/**
	 * 查询首页价格
	 * 
	 */
	@RequestMapping(params = "method=price_query")
	public void queryPrice(HttpServletRequest request, HttpServletResponse response, ModelAndView view) {
		List<TtyPrice> list = ttyPriceService.select(null);
		view.addObject("priceList", list);
		
	}

	/**
	 * 查询首页类型
	 * 
	 */
	@RequestMapping(params = "method=type_query")
	public void queryType(HttpServletRequest request, HttpServletResponse response, ModelAndView view) {
		List<TtyType> list = ttyTypeService.select(null);
		view.addObject("typeList", list);
	}

	/**
	 * 查询首页最新产品
	 * 
	 */
	@RequestMapping(params = "method=news_product_query")
	public void queryNewsProduct(HttpServletRequest request, HttpServletResponse response, ModelAndView view) {
		List<TtyProduct> list = ttyProductService.selectNewProduct();
		view.addObject("newProductList", list);
	}

	/**
	 * 查询首页热销产品
	 * 
	 */
	@RequestMapping(params = "method=like_product_query")
	public void queryLikeProduct(HttpServletRequest request, HttpServletResponse response, ModelAndView view) {
		List<TtyProduct> list = ttyProductService.selectLikeProduct();
		view.addObject("likeProductList", list);
	}

	/**
	 * 查询首页天逸动态
	 * 
	 */
	@RequestMapping(params = "method=ty_dynamic_query")
	public void queryTyDynamic(HttpServletRequest request, HttpServletResponse response, ModelAndView view) {
		TtyNews news = ttyNewsFrontService.selectActivity(); // 促销内容
		view.addObject("news", news);
		if(news!=null && news.getNewsId()!=null) {
			TsysAttach attach = ttyNewsFrontService.selectPkid(news.getNewsId());// 天逸促销活动图片
			view.addObject("attach", attach);
		}
		List<TtyNews> newsLists = ttyNewsFrontService.selectCompanyDynamic(1);// 天逸动态
		view.addObject("newsLists", newsLists);
		List<TtyNews> issueLists = ttyNewsFrontService.selectCompanyDynamic(4);// 天逸动态常见问题
		view.addObject("issueLists", issueLists);
	}
	

	/**
	 * 进入售后服务
	 * 
	 */
	@RequestMapping(params = "method=after_service")
	public String afterService() {
		return "front/after-service";
	}
	
	/**
	 * 进入关于我们
	 * 
	 */
	@RequestMapping(params = "method=about_us")
	public String aboutUs() {
		return "front/about-us";
	}
	/**
	 * 进入诚聘英才
	 * 
	 */
	@RequestMapping(params = "method=people_recruit")
	public String peopleRecruit() {
		return "front/people_recruit";
	}
	/**
	 * 进入诚聘英才
	 * 
	 */
	@RequestMapping(params = "method=contact_us")
	public String contactUs() {
		return "front/contact-us";
	}
}
