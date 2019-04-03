package com.creatorblue.cbitedu.system.controller;

import java.util.ArrayList;
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

import com.creatorblue.cbitedu.core.baseclass.controller.HihBaseController;
import com.creatorblue.cbitedu.core.exception.ControllerException;
import com.creatorblue.cbitedu.core.page.Page;
import com.creatorblue.cbitedu.core.utils.Identities;
import com.creatorblue.cbitedu.system.domain.TsysSerialno;
import com.creatorblue.cbitedu.system.service.TsysSerialnoService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
/**
 * 各种编码生成
 * @author zhujw
 *
 */
@Controller
@RequestMapping("/tsysSerialnoController.do")
public class TsysSerialnoController  extends HihBaseController {
	@Autowired
	private TsysSerialnoService<TsysSerialno> tsysSerialnoService;
	/*@Autowired
	private SerialNumber serialNumber;*/
	/**
	 * 进入页面
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(params = "method=list")
	public ModelAndView list(HttpServletRequest request,
			HttpServletResponse response) throws ControllerException {
		/*try {
			System.out.println(SerialNo.getSerialNo("oraIdRule"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		ModelAndView mv = new ModelAndView("/serialno/tsysserialnolist");
		return mv;
	}
	/**
	 * 访问列表
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(params = "method=query")
	public void query(HttpServletRequest request, HttpServletResponse response)
			throws ControllerException {
		Map<String, Object> result = new HashMap<String, Object>();	
		result.put("serial_name", request.getParameter("serial_name"));
		result.put("secound_name", request.getParameter("secound_name"));
		Page page =  this.getPage(request);		
		result.put("page", page);
		List<TsysSerialno> list = tsysSerialnoService.selectPageTsysSerialnoList(result);
		this.convertParam(list, "create_type","create_type");
		renderJson(list, page, response);
	}
	/**
	 * 访问新增页面
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(params = "method=add")
	public ModelAndView add(HttpServletRequest request, HttpServletResponse response)
			throws ControllerException {
		TsysSerialno tsysSerialno = new TsysSerialno();
		tsysSerialno.setFormular_regx("{YYYY}{MM}{DD}-{NO}");
		tsysSerialno.setStep("1");
		tsysSerialno.setInit_value("1");
		tsysSerialno.setSerial_length("5");
		ModelAndView mv = new ModelAndView("/serialno/tsysserialnoform");
		mv.addObject("tsysSerialno",tsysSerialno);
		return mv;
	}
	/**
	 * 访问编辑页面
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(params = "method=modify")
	public ModelAndView modify(HttpServletRequest request, HttpServletResponse response)
			throws ControllerException {
		String serialno_id=request.getParameter("serialno_id");
		Map<String,String> map=new HashMap<String,String>();
		map.put("serialno_id", serialno_id);		
		TsysSerialno tsysSerialno = new TsysSerialno();
		tsysSerialno=tsysSerialnoService.selectTsysSerialno(map);
		
		ModelAndView mv = new ModelAndView("/serialno/tsysserialnoform");
		mv.addObject("tsysSerialno",tsysSerialno);
		return mv;
	}
	
	@RequestMapping(params = "method=ajaxSave")
	public void saveTsysSerialno(HttpServletRequest request,
			HttpServletResponse response) {
		Map<String, Object> result = new HashMap<String, Object>();
		String t=request.getParameter("serial_length");
		System.out.println(t);
		
		try {
			TsysSerialno tsysSerialno = new TsysSerialno();
			this.setValue(request, tsysSerialno);
			if (StringUtils.isEmpty(tsysSerialno.getSerialno_id())) {
				tsysSerialno.setSerialno_id(Identities.uuid());	
				tsysSerialnoService.insert(tsysSerialno);				
			} else {
				//判断规则是否改变
				if(!tsysSerialno.getFormular_regx().equals(tsysSerialno.getFormular_regx_old()))
					tsysSerialno.setCurrent_value("");//当规则改变时则重置初始值
				tsysSerialnoService.update(tsysSerialno);
			}
			result.put("msg", "保存成功！");
			result.put("success", true);
		} catch (Exception e) {
			e.printStackTrace();
			result.put("msg", "保存失败，重新输入再试一次！");
			result.put("success", false);
		}
		
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			renderJson(response, objectMapper.writeValueAsString(result));
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@RequestMapping(params = "method=del")
	public void delTsysSerialno(HttpServletRequest request,
			HttpServletResponse response) {
		String serialno_id=request.getParameter("serialno_id");	
		String[] serialno=serialno_id.split(",");
		List<String> serialnoList = new ArrayList<String>();
		for(int i=0;i<serialno.length;i++){
			serialnoList.add(serialno[i]);
		}
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			tsysSerialnoService.deleteBatch(serialnoList);
			result.put("msg", "删除成功！");
			result.put("success", true);
		} catch (Exception e) {
			e.printStackTrace();
			result.put("msg", "删除失败！");
			result.put("success", false);
		}

		ObjectMapper objectMapper = new ObjectMapper();
		try {
			renderJson(response, objectMapper.writeValueAsString(result));
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
