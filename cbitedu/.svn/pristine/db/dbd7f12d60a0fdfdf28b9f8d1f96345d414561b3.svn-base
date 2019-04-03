package com.creatorblue.cbitedu.system.controller;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.creatorblue.cbitedu.core.baseclass.controller.HihBaseController;
import com.creatorblue.cbitedu.core.exception.ControllerException;
import com.creatorblue.cbitedu.core.utils.Identities;
import com.creatorblue.cbitedu.system.domain.TsysApp;
import com.creatorblue.cbitedu.system.domain.TsysModule;
import com.creatorblue.cbitedu.system.service.TsysModuleService;
import com.creatorblue.cbitedu.system.service.TsysModuleoperateService;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * <p> Title: </p>
 * <p> Description:</p>
 * <p> Copyright: Copyright (c) 2014 </p>
 * <p> Company:hihsoft.co.,ltd </p>
 *
 * @author zhujw
 * @version 1.0
 */

@Controller
@RequestMapping("/tsysModuleController.do")
public class TsysModuleController extends HihBaseController {
	@Autowired
	private TsysModuleService tsysModuleService;
	@Autowired
	private TsysModuleoperateService tsysModuleoperateService;
	
	/**
	* 访问列表
	* @param request
	* @param response
	* @return
	* @throws Exception
	*/
	@RequestMapping(params="method=list")
	public ModelAndView list(HttpServletRequest request,
			HttpServletResponse response) throws ControllerException {
		ModelAndView mv = new ModelAndView("/module/tsysmodulelist");
		//设置默认节点
		TsysModule tm=tsysModuleService.setdefaultNote();
		if(tm.getModuleId()!=null && !tm.getModuleId().equals(""))
			mv.addObject("tsysModule",preview(tm));
		return mv;
	}
	
	
	private TsysModule preview(TsysModule tsysModule)
	{
		//根据模块主菜单获取应用系统数据
		TsysApp tsysApp=tsysModuleService.getApp(tsysModule);
		tsysModule.setAppId(tsysApp.getAppId());
		tsysModule.setAppName(tsysApp.getAppName());
			
		//根据parentID获取数据上级菜单模块
		if(!tsysModule.getParentModuleid().equals("0"))
		{
			TsysModule tm=tsysModuleService.getModule(tsysModule.getParentModuleid());
			tsysModule.setParentModuleid(tm.getModuleId());
			tsysModule.setParentModuleName(tm.getModuleName());
		}
		else
		{
			tsysModule.setParentModuleid("0");
			tsysModule.setParentModuleName(tsysApp.getAppName());
		}
		return tsysModule;
	}
	/**
	 * 获取菜单树形列表
	 * @param request
	 * @param response
	 * @return
	 * @throws ControllerException
	 * @throws IOException 
	 */
	@RequestMapping(params="method=moduleTree")
	public  void moduleTree(HttpServletRequest request,
			HttpServletResponse response) throws ControllerException, IOException {
		  List list=tsysModuleService.queryModuleTree(null);
			String result="";
	        ObjectMapper objectMapper=new ObjectMapper();
	        try {
	        	result= objectMapper.writeValueAsString(list);
	        } catch (Exception e) {
	             e.printStackTrace();
	        } 
	  writeWeb(response,result);
	}
	/**
	* 進入新增頁面
	* @param request
	* @param response
	* @return
	* @throws Exception
	*/
	@RequestMapping(params="method=add")
	public ModelAndView add(HttpServletRequest request,
			HttpServletResponse response) throws ControllerException {
		ModelAndView mv = new ModelAndView("/module/tsysmoduleform");
		String id=request.getParameter("id");			//当前节点ID
		String ismenu=request.getParameter("ismenu");	//是否为多应用系统主菜单
		
		TsysModule tsysModule=new TsysModule();
		if(ismenu!=null && ismenu.equals("0"))    
		{
			tsysModule.setAppId(id);
			tsysModule.setParentModuleid("0");
		}else
			 tsysModule=(TsysModule)tsysModuleService.selectDetailById(id);
		TsysApp tsysApp=tsysModuleService.getApp(tsysModule);//获取多应用系统信息
		
		//设置菜单模块的值
		TsysModule tm=new TsysModule();
		tm.setAppName(tsysApp.getAppName());
		tm.setAppId(tsysApp.getAppId());
		if(ismenu!=null && ismenu.equals("0")){
			tm.setParentModuleName(tsysApp.getAppName());
			tm.setParentModuleid("0");	
		}else{
			tm.setParentModuleid(tsysModule.getModuleId());
			tm.setParentModuleName(tsysModule.getModuleName());	
		}
		mv.addObject("tsysModule", tm);
		mv.addObject("ismenu", ismenu); 
		
		return mv;
	}
	/**
	 * 修改菜单模块
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(params="method=modify")
	public ModelAndView modify(HttpServletRequest request,
			HttpServletResponse response) throws ControllerException {
		ModelAndView mv = new ModelAndView("/module/tsysmoduleform");
		String id=request.getParameter("id");
		String ismenu=request.getParameter("ismenu");
		if(ismenu==null || !ismenu.equals("0"))
		{
			//获取模块主菜单
			TsysModule tsysModule=(TsysModule)tsysModuleService.selectDetailById(id);
			mv.addObject("tsysModule",preview(tsysModule));
			mv.addObject("ismenu", ismenu); 
			
		}
		return mv;
	}
	/**
	* 查看菜单模块记录详细
	* @param request
	* @param response
	* @return
	 * @throws IOException 
	* @throws Exception
	*/
	@RequestMapping(params="method=viewModule")
	public @ResponseBody void viewModule(HttpServletRequest request,
			HttpServletResponse response) throws ControllerException, IOException {
		String id=request.getParameter("id");
		String ismenu=request.getParameter("ismenu");
		if(ismenu==null || !ismenu.equals("0")){
			//获取模块主菜单
			TsysModule tsysModule=(TsysModule)tsysModuleService.selectDetailById(id);
			 ObjectMapper objectMapper=new ObjectMapper();
			 String result="";
		        try {
		        	result= objectMapper.writeValueAsString(preview(tsysModule));
		        } catch (Exception e) {
		             e.printStackTrace();
		        } 
			    writeWeb(response,result);
			}
	}
	
	/**
	* 保存菜单模块
	* @param request
	* @param response
	* @return
	 * @throws IOException 
	* @throws Exception
	*/
	@RequestMapping(params="method=saveModule")
	protected  @ResponseBody void saveModule(HttpServletRequest request,
			HttpServletResponse response) throws ControllerException, IOException {
		boolean flag=false;
		TsysModule tsysModule=new TsysModule();
		try{
		    this.setValue(request,tsysModule);
		    if(tsysModule.getModuleId()==null || ("").equals(tsysModule.getModuleId()))
		    {
		    	String ismenu=request.getParameter("ismenu");
				if(ismenu==null || !ismenu.equals("0"))
				{
					tsysModule.setAppId("");
					tsysModule.setAppName("");
				}
					
		    	tsysModule.setModuleId(Identities.uuid());
		        tsysModuleService.insert(tsysModule);
		    }
		    else
		    {
		    	if(!tsysModule.getParentModuleid().equals("0")){
			    	if(tsysModule.getParentModuleid().equals(tsysModule.getModuleId()))
			    		tsysModule.setParentModuleid("0");
			    	else
			    	{
			    		tsysModule.setAppId("");
						tsysModule.setAppName("");
			    	}
		    	}
		    	tsysModuleService.update(tsysModule);
		    }
		    flag=true;
		}catch(Exception e){
			flag=false;
			e.printStackTrace();
		}
		writeWeb(response,String.valueOf(flag));
	}
	
	/**
	 * 删除模块
	 * @param request
	 * @param response
	 * @throws ControllerException
	 */
	  @RequestMapping(params = "method=deleteModule")
	  public void deleteModule(HttpServletRequest request, HttpServletResponse response)
	        throws ControllerException {
	        String id = request.getParameter("id");
	        Map map=new HashMap();
	        map.put("parentModuleid", id);
	        List list=tsysModuleService.getModuleBycondition(map);
	        if (list.size()>0) {
	        	renderText(response, "0");
			}
	        else
	        {
				List operateList=tsysModuleoperateService.queryModuleOperate(id);
				if(operateList.size()>0)
					tsysModuleoperateService.deleteByModuleId(id);
				tsysModuleService.delete(id);
			     renderText(response, "1");
			}
	    }
	/**
	 * 验证菜单模块编码是否存在
	 * @param moduleCode
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping(params="method=isHasModuleCode")
	public @ResponseBody void isHasModuleCode(
			@RequestParam String moduleId,
			@RequestParam String moduleCode,HttpServletRequest request,HttpServletResponse response) throws IOException
	{
		boolean flag=true;
		try 
		{
			TsysModule tm=tsysModuleService.getModuleByCode(moduleCode);
			if(tm.getModuleId()!=null && tm.getModuleId().equals(moduleId))
			{
				flag=true;
			}else
				flag=false;
		} 
		catch (Exception e)
		{
			flag=true;
			e.printStackTrace();
		}
		writeWeb(response,String.valueOf(flag));
	}
	
	

}

