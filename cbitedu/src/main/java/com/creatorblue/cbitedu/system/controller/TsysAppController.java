package com.creatorblue.cbitedu.system.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.creatorblue.cbitedu.core.baseclass.controller.HihBaseController;
import com.creatorblue.cbitedu.core.exception.ControllerException;
import com.creatorblue.cbitedu.core.page.Page;
import com.creatorblue.cbitedu.core.utils.Identities;
import com.creatorblue.cbitedu.system.domain.TsysApp;
import com.creatorblue.cbitedu.system.service.TsysAppService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * <p>
 * Title:
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Copyright: Copyright (c) 2014
 * </p>
 * <p>
 * Company:hihsoft.co.,ltd
 * </p>
 * 
 * @author zhujw
 * @version 1.0
 */
@Controller
@RequestMapping("/tsysAppController.do")
public class TsysAppController extends HihBaseController {
	@Autowired
	private TsysAppService tsysAppService;

	/**
	 * 子系統列表
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(params = "method=list")
	public ModelAndView list(HttpServletRequest request,
			HttpServletResponse response) throws ControllerException {
		ModelAndView mv = new ModelAndView("/app/tsysapplist");
		return mv;
	}

	/**
	 * 查詢
	 * 
	 * @param request
	 * @param response
	 * @throws ControllerException
	 */

	@RequestMapping(params = "method=query")
	public void query(HttpServletRequest request, HttpServletResponse response)
			throws ControllerException {
		Page page = this.getPage(request);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("appName", request.getParameter("appName"));
		map.put("appShortname", request.getParameter("appShortname"));
		map.put("page", page);

		List list = tsysAppService.selectPageTsysAppByMap(map);
		renderJson(list, page, response);
	}

	/**
	 * 進入新增頁面
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(params = "method=add")
	public ModelAndView add(HttpServletRequest request,
			HttpServletResponse response) throws ControllerException {
		return new ModelAndView("/app/tsysappform");
	}

	/**
	 * 修改
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(params = "method=modify")
	public ModelAndView modify(HttpServletRequest request,
			HttpServletResponse response) throws ControllerException {
		ModelAndView mv = new ModelAndView("/app/tsysappform");
		String id = request.getParameter("id");
		TsysApp tsysApp = (TsysApp) tsysAppService.selectDetailById(id);
		mv.addObject("tsysApp", tsysApp);

		return mv;
	}

	/**
	 * 查看记录详细
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(params = "method=view")
	public ModelAndView view(HttpServletRequest request,
			HttpServletResponse response) throws ControllerException {
		String id = request.getParameter("id");
		TsysApp tsysApp = (TsysApp) tsysAppService.selectDetailById(id);
		this.setValue(request, tsysApp);

		return new ModelAndView("/app/tsysappform");
	}

	/**
	 * 保存
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(params = "method=save")
	public ModelAndView save(HttpServletRequest request,
			HttpServletResponse response) throws ControllerException {
		TsysApp tsysApp = new TsysApp();

		try {
			this.setValue(request, tsysApp);

			if ((tsysApp.getAppId() == null) || ("").equals(tsysApp.getAppId())) { // 新增
				tsysApp.setAppId(Identities.uuid());
				tsysAppService.insert(tsysApp);
			} else {
				tsysAppService.update(tsysApp);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return new ModelAndView(new RedirectView(request.getContextPath()
				+ "/tsysAppController.do?method=list"));
	}

	/**
	 * 保存
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(params = "method=saveSysApp")
	public void saveSysApp(HttpServletRequest request,
			HttpServletResponse response) throws ControllerException {
		TsysApp tsysApp = new TsysApp();
		Map<String, Object> map = new HashMap<String, Object>();

		try {
			this.setValue(request, tsysApp);

			if ((tsysApp.getAppId() == null) || ("").equals(tsysApp.getAppId())) { // 新增
				tsysApp.setAppId(Identities.uuid());
				tsysAppService.insert(tsysApp);
				map.put("msg", "保存成功！");
			} else {
				tsysAppService.update(tsysApp);
				map.put("msg", "修改成功！");
			}

			map.put("flag", "true");
		} catch (Exception e) {
			e.printStackTrace();
			map.put("msg", StringUtils.isEmpty(tsysApp.getAppId()) ? "保存失败！"
					: "修改失败");
			map.put("flag", "false");
		}

		ObjectMapper objectMapper = new ObjectMapper();

		try {
			this.renderJson(response, objectMapper.writeValueAsString(map));
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 删除
	 * 
	 * @param request
	 * @param response
	 * @param mav
	 * @throws ControllerException
	 */
	@RequestMapping(params = "method=deleteApp")
	public void delete(HttpServletRequest request,
			HttpServletResponse response, ModelAndView mav)
			throws ControllerException {
		Map<String, Object> map = new HashMap<String, Object>();
		String id = request.getParameter("id");

		try {
			if (tsysAppService.checkModuleOfSysApp(id)) {
				map.put("msg", "应用下存在子模块,不允许删除!");
				map.put("flag", "false");
			} else {
				tsysAppService.delete(id);
				map.put("flag", "true");
			}
		} catch (Exception e) {
			e.printStackTrace();
			map.put("flag", "false");
			map.put("msg", "删除失败!");
		}

		ObjectMapper objectMapper = new ObjectMapper();

		try {
			this.renderJson(response, objectMapper.writeValueAsString(map));
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
