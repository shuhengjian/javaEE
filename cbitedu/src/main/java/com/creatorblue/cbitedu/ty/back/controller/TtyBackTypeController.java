package com.creatorblue.cbitedu.ty.back.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.WebUtils;

import com.creatorblue.cbitedu.core.baseclass.controller.HihBaseController;
import com.creatorblue.cbitedu.core.page.Page;
import com.creatorblue.cbitedu.core.utils.Identities;
import com.creatorblue.cbitedu.ty.back.service.TtyBackTypeService;
import com.creatorblue.cbitedu.ty.domain.TtyBrand;
import com.creatorblue.cbitedu.ty.domain.TtyType;
@Controller
@RequestMapping("/ttyBackTypeController.do")
public class TtyBackTypeController extends HihBaseController{
	@Autowired
	private TtyBackTypeService ttyBackTypeService;
	/**
	 * 访问列表， 并且初始化列表中所需的数据
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(params = "method=list")
	public ModelAndView list(HttpServletRequest request,
			HttpServletResponse response) {
		ModelAndView mv = new ModelAndView("/type/ttytypelist");
		return mv;
	}
	
	/**
	 * 访问列表， 查询数据
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(params = "method=query")
	public void query(HttpServletRequest request, HttpServletResponse response) {
		Page page = this.getPage(request);
		Map<String, Object> param = WebUtils.getParametersStartingWith(request,
				"type_");
		param.put("typeState", request.getParameter("brand_state"));
		param.put("page", page);
		List<TtyType> list = ttyBackTypeService.selectPageTtyTypeByMap(param);
		this.convertParam(list, "typeStart", "typeStart");
		this.convertParam(list, "typeState", "brand_state");
		renderJson(list, page, response);
	}
	
	/**
	 * 進入新增頁面， 并初始化新增页面所需要的数据
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(params = "method=add")
	public ModelAndView add(HttpServletRequest request,
			HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("type/ttytypeform");
		return mav;
	}
	
	/**
	 * 修改选中的数据
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(params = "method=modify")
	public ModelAndView modify(HttpServletRequest request,
			HttpServletResponse response) {
		ModelAndView mav = new ModelAndView("type/ttytypeform");
		String id = request.getParameter("id");
		TtyType ttyType = ttyBackTypeService.selectByPrimaryKey(id);
		mav.addObject("ttyType", ttyType);
		return mav;
	}
	
	/**
	 * 异步提交表单， 保存数据。
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "method=ajaxSave")
	public void ajaxSave(HttpServletRequest request,
		HttpServletResponse response) {
		TtyType ttyType = new TtyType();
		this.setValue(request, ttyType);
		boolean flag = true;
		String msg = null;
		try {
			TtyType result = ttyBackTypeService.checkTheNameWithParentId(ttyType);
			if (result != null) {
				msg = "已经存在相同名称的类型名称,请重新输入类型名称！";
				String checkResult = "{\"flag\" : " + false
						+ " , \"msg\" : \"" + msg + "\"}";
				this.renderJson(response, checkResult);
				return;
			}
			if (StringUtils.isEmpty(ttyType.getTypeId())) {
				ttyType.setTypeId(Identities.uuid());
				ttyBackTypeService.insert(ttyType);
				msg = "保存成功！";
			} else {
				ttyBackTypeService.update(ttyType);
				msg = "修改成功！";
			}

		} catch (Exception e) {
			flag = false;
			msg = StringUtils.isEmpty(ttyType.getTypeId()) ? "保存失败！" : "修改失败";
			e.printStackTrace();
		}
		String result = "{\"flag\" : " + flag + " , \"msg\" : \"" + msg + "\"}";
		this.renderJson(response, result);
	}
	/**
	 * 删除一条或多条数据
	 * -1表示异常 删除失败
	 * 1  删除成功
	 * 0 所选品牌下面包含商品不可删除
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "method=del")
	public void deleteById(HttpServletRequest request,
			HttpServletResponse response) {
		String[] typeIds = request.getParameterValues("typeIds");
		String flag = "1";
		int j = 0;
		//循环判断品牌下面是否含有商品
		for (int i = 0; i < typeIds.length; i++) {
			j = ttyBackTypeService.selectCountProduct(typeIds[i]);
			if(j > 0) {
				break;
			}
		}
		//如果有商品则跳过删除
		if(j > 0) {
			flag = "0";
		}else {
			try {
				for (int i = 0; i < typeIds.length; i++) {
					ttyBackTypeService.deleteByPrimaryKey(typeIds[i]);
				}
			} catch (Exception e) {
				e.printStackTrace();
				flag = "-1";
			}
		}
		String result = "{\"flag\" : " + flag+ "}";
		this.renderJson(response, result);
	}
	/**
	 * 启用，禁用
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "method=state")
	public void state(HttpServletRequest request,
			HttpServletResponse response) {
		TtyType ttyType = new TtyType();
		String typeState = request.getParameter("typeState");
		ttyType.setTypeState(typeState);
		String[] typeIds = request.getParameterValues("typeIds");
		boolean flag = true;
		try {
			for (int i = 0; i < typeIds.length; i++) {
				ttyType.setTypeId(typeIds[i]);
				ttyBackTypeService.updateTypeStateByPrimaryKey(ttyType);
			}
		} catch (Exception e) {
			e.printStackTrace();
			flag = false;
		}
		String result = "{\"flag\" : " +flag + "}";
		this.renderJson(response, result);
	}
}