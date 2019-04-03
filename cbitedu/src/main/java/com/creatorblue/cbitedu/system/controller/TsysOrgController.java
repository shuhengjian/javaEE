package com.creatorblue.cbitedu.system.controller;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.creatorblue.cbitedu.core.baseclass.controller.HihBaseController;
import com.creatorblue.cbitedu.core.exception.ControllerException;
import com.creatorblue.cbitedu.core.page.Page;
import com.creatorblue.cbitedu.system.domain.TsysOrg;
import com.creatorblue.cbitedu.system.service.TsysDataprivilegeService;
import com.creatorblue.cbitedu.system.service.TsysOrgService;
import com.creatorblue.cbitedu.system.service.TsysUserinfoService;
import com.creatorblue.cbitedu.system.util.SerialNo;
import com.fasterxml.jackson.core.JsonProcessingException;
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
@RequestMapping("/tsysOrgController.do")
public class TsysOrgController extends HihBaseController {
	@Autowired
	private TsysOrgService tsysOrgService;
	@Autowired
	private TsysUserinfoService tsysUserinfoService;
	@Autowired
	private TsysDataprivilegeService tysDataprivilegeService;
	
	
	/**
	* 访问列表
	* @param request
	* @param response
	* @return
	* @throws Exception
	*/
	@RequestMapping(params="method=orglist")
	public ModelAndView list(HttpServletRequest request,
			HttpServletResponse response) throws ControllerException {
		Page page = this.getPage(request);
		List list = tsysOrgService.selectPageTsysOrg();
		ModelAndView mv = new ModelAndView("/org/tsysorglist");
		mv.addObject("list", list);
		mv.addObject("page", page);
	    return mv;
	}
	/**
	* 进入页面
	* @param request
	* @param response
	* @return
	* @throws Exception
	*/
	@RequestMapping(params="method=list")
	public ModelAndView orglist(HttpServletRequest request,
			HttpServletResponse response) throws ControllerException {
		ModelAndView mv = new ModelAndView("/org/orginfoindex");
	    return mv;
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
		String org_id=request.getParameter("org_id");
		return new ModelAndView("/org/orginfoform").addObject("org_id",org_id).addObject("opmethod","saveOrg");
	}
	/**
	 * 修改
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(params="method=tomodify")
	public ModelAndView tomodify(HttpServletRequest request,
			HttpServletResponse response) throws ControllerException {
		ModelAndView mv = new ModelAndView("/org/orginfoform");
		String id=request.getParameter("id");
		String op=request.getParameter("op");
		if(!"saveOrg".equals(op))
		{	
		TsysOrg tsysOrg=(TsysOrg)tsysOrgService.selectDetailById(id);
	    mv.addObject("tsysOrg", tsysOrg);
		}
		else
		{
			mv.addObject("id",id);
		}
	    mv.addObject("operator",op);
		return mv;
	}
	
	/**
	 * 移动
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(params="method=tomove")
	public ModelAndView tomove(HttpServletRequest request,
			HttpServletResponse response) throws ControllerException {
		ModelAndView mv = new ModelAndView("/org/orgmove");
		return mv;
	}
	/**
	* 查看记录详细
	* @param request
	* @param response
	* @return
	* @throws Exception
	*/
	@RequestMapping(params="method=view")
	public ModelAndView view(HttpServletRequest request,
			HttpServletResponse response) throws ControllerException {
		String id=request.getParameter("id");
		TsysOrg tsysOrg=(TsysOrg)tsysOrgService.selectDetailById(id);
	    this.setValue(request,tsysOrg);
		return new ModelAndView("/org/tsysorgform");
	}
	
	/**
	* 保存
	* @param request
	* @param response
	* @return
	 * @throws JsonProcessingException 
	* @throws Exception
	*/
	@RequestMapping(params="method=saveOrg")
	public void saveOrg(HttpServletRequest request,
			HttpServletResponse response) throws JsonProcessingException{
		Map<String,Object> result = new HashMap<String,Object>();
		Map<String,Object> para = new HashMap<String,Object>();
		TsysOrg tsysOrg=new TsysOrg();
		TsysOrg tsysObject= new TsysOrg();
		try
		{
	    this.setValue(request,tsysOrg);
	    para.put("type", 3);
	    para.put("orgName", tsysOrg.getOrgName());
	    para.put("parentId", tsysOrg.getParentId());
	    tsysObject=tsysOrgService.selectOrgIdByOrgObject(para);
	    if(tsysObject!=null)
	    {
	    	result.put("msg", "已存在相同的机构名称，重新输入再试一次！");
			result.put("success", false);
	    }
	    else
	    {
	    	para = new HashMap<String,Object>();
	    	para.put("type", 4);
	 	    para.put("orgCode", tsysOrg.getOrgCode());
	 	    tsysObject=tsysOrgService.selectOrgIdByOrgObject(para);
	        if(tsysObject!=null)
	 	    {
	 	    	result.put("msg", "已存在相同的机构编码，重新输入再试一次！");
	 			result.put("success", false);
	 	    }
	    	 else
	    	 {
	    //tsysOrg.setOrgId(Identities.uuid());
	    Calendar c = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat(
				"yyyy-MM-dd HH:mm:ss");
		String createdata=sdf.format(c.getTime());
		tsysOrg.setCreateDate(createdata);
		int tsysSeq=tsysOrgService.selectOrgCount();
		//获取序列
		//Long tsysSeq=Long.parseLong(tsysOrgService.selectSysSequInfo());
		//获取机构表最大MAXID,由于考虑到跨数据库问题SQL中不直接用select max方法
		/*tsysObject =(TsysOrg)tsysOrgService.selectSysSeqMaxId().get(0);
		Long maxId =Long.parseLong(tsysObject.getOrgnumber());
		//Long maxId=()tsysOrgService.selectSysSeqMaxId().get(0);
		if(tsysSeq==null)
		{
			//获取机构表中的机构数量
			maxId=maxId+1;
			tsysOrg.setOrgnumber(maxId.toString());
		}
		else
		{
			if(tsysSeq<=maxId)
			{
				maxId=maxId+1;
			}
			else
			{
				maxId=tsysSeq;
			}
			tsysOrg.setOrgnumber(maxId.toString());
		}*/
		//tsysOrg.setOrgnumber(tsysSeq.toString());
		if(tsysSeq==0)
		{
			tsysOrg.setParentId("-1");
			tsysOrg.setAncestry(".1.-1.");
		}
		else
		{
			//获取祖谱
			String ancesty=tsysOrgService.selectSysOrgAncesty(tsysOrg.getParentId());
			if("".equals(ancesty) || ancesty==null)
			{
				tsysOrg.setAncestry("."+tsysSeq+"."+"-1"+".");
			}
			else
			{
				tsysOrg.setAncestry("."+(tsysSeq+1)+ancesty);
			}
		}
		tsysOrg.setOrgId(SerialNo.getSerialNo("oraIdRule"));
		tsysOrg.setOrgSn(new Long(tsysSeq).longValue());
		//查询机构数量
		//tsysOrg.setOrgSn(Long.parseLong(tsysOrgService.selectOrgCount().toString()));
		
		tsysOrgService.insert(tsysOrg,String.valueOf(tsysSeq+1));
		
		result.put("msg", "添加成功！");
		result.put("success", true);
	    }
	    }
		}catch(Exception e)
		{
			e.printStackTrace();
			result.put("msg", "添加失败，重新输入再试一次！");
			result.put("success", false);
		}
		ObjectMapper objectMapper=new ObjectMapper();
		String jsonresult = objectMapper.writeValueAsString(result);
		renderJson(response, jsonresult.toString());
	   
	}
	/**
	* 修改
	* @param request
	* @param response
	* @return
	* @throws Exception
	*/
	@RequestMapping(params="method=modify")
	public void modify(HttpServletRequest request,
			HttpServletResponse response){
		Map<String,Object> result = new HashMap<String,Object>();
		TsysOrg tsysOrg=new TsysOrg();
		try
		{
	    this.setValue(request,tsysOrg);
		tsysOrgService.update(tsysOrg);
		result.put("msg", "修改成功！");
		result.put("success", true);
		}catch(Exception e)
		{
			e.printStackTrace();
			result.put("msg", "修改失败，重新输入再试一次！");
			result.put("success", false);
		}
		ObjectMapper objectMapper=new ObjectMapper();
		String jsonresult="";
		try {
			jsonresult = objectMapper.writeValueAsString(result);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		renderJson(response, jsonresult);
	}
	
	/**
	* 排序
	* @param request
	* @param response
	* @return
	* @throws Exception
	*/
	@RequestMapping(params="method=move")
	public void move(HttpServletRequest request,
			HttpServletResponse response){
		Map<String,Object> result = new HashMap<String,Object>();
		TsysOrg tsysOrg=new TsysOrg();
		try
		{
		String orginfo_str=request.getParameter("orglist");
		String [] orgObject=orginfo_str.split(",");
		String [] orginfo;
		for (int i=0;i<orgObject.length;i++)
		{
			    orginfo=orgObject[i].split("@");
			    tsysOrg.setOrgId(orginfo[0]);
				tsysOrg.setParentId(orginfo[1]);
				tsysOrg.setOrgSn(Long.parseLong(orginfo[2]));
				tsysOrg.setAncestry(orginfo[3]);
				tsysOrgService.updateBySort(tsysOrg);
		}
		result.put("msg", "排序成功！");
		result.put("success", true);
		}catch(Exception e)
		{
			e.printStackTrace();
			result.put("msg", "排序失败，重新再试一次！");
			result.put("success", false);
		}
		ObjectMapper objectMapper=new ObjectMapper();
		String jsonresult="";
		try {
			jsonresult = objectMapper.writeValueAsString(result);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		renderJson(response, jsonresult);
	}
	/**
	* 获取根结点详细信息
	* @param request
	* @param response
	* @return
	* @throws Exception
	*/
	@RequestMapping(params="method=rootinfo")
	public void rootinfo(HttpServletRequest request,
			HttpServletResponse response){
		Map<String,Object> result = new HashMap<String,Object>();
		Map<String,Object> para = new HashMap<String,Object>();
		TsysOrg tsysOrg=new TsysOrg();
		
		para.put("orgId", "-1");
		//type=2 代表取字段parent_id=orgId值
		para.put("type", 2);
		try
		{
			tsysOrg=tsysOrgService.selectOrgIdByOrgObject(para);
			if(tsysOrg.getOrgId()==null || "".equals(tsysOrg.getOrgId()) )
			{
				result.put("treeid", "0");
			}
			else
			{
				result.put("treeid", tsysOrg.getOrgId());
			}
		}catch(Exception e)
		{
			result.put("treeid", "0");
		}
		ObjectMapper objectMapper=new ObjectMapper();
		String jsonresult="";
		try {
			jsonresult = objectMapper.writeValueAsString(result);
		} catch (JsonProcessingException e) {
			
			e.printStackTrace();
		}
		renderJson(response, jsonresult);
	}
	/**
	* 删除
	* @param request
	* @param response
	* @return
	* @throws Exception
	*/
	@RequestMapping(params="method=del")
	public void del(HttpServletRequest request,
			HttpServletResponse response){
		Map<String,Object> result = new HashMap<String,Object>();
		Map<String,Object> para = new HashMap<String,Object>();
		TsysOrg tsysOrg=new TsysOrg();
		String id = request.getParameter("id");
		para.put("orgId", id);
		//type=1 代表取字段orgnumber=orgId值
		para.put("type", "1");
		try
		{
	    
		String childCount=tsysOrgService.selectChildCount(id);
		if(!"0".equals(childCount))
		{
			result.put("msg", "该机构下面有子机构,不允许删除！");
			result.put("success", 1);
			
		}
		else
		{
			//String orgId=tsysOrgService.selectOrgIdByNumber(para);
			tsysOrg=tsysOrgService.selectOrgIdByOrgObject(para);
			//调用 查询用户跟角色的方法
		    if("2".equals(tsysOrg.getState()))
		    {
				Integer count = tsysUserinfoService.queryUserCountByOrgId(tsysOrg.getOrgId());
				if(count>0)
				{
					result.put("msg", "该机构下面有用户,不允许删除！");
					result.put("success", 2);
				}
				else
				{
			    
					count=tysDataprivilegeService.selectDataprivilegeNum(tsysOrg);
					if(count>0)
					{
						result.put("msg", "有角色拥有此机构,不允许删除！");
						result.put("success", 3);
					}
					else
					{
						tsysOrgService.updateStatus(id);
						result.put("msg", "删除成功！");
						result.put("success", 0);
						
					}
				}
		    }
		    else
		    {
		    	tsysOrgService.updateStatus(id);
		    	result.put("msg", "删除成功！");
				result.put("success", 0);
		    }
			//调用删除方法
			//tsysOrgService.delete(id);
			//更新排序号
			//tsysOrgService.updateSortId(Integer.parseInt(tsysOrg.getOrgSn().toString()));
			//成功删除序列号-1
			//tsysOrgService.updateSeqByDel();
		
			
		}
		
		}catch(Exception e)
		{
			e.printStackTrace();
			result.put("msg", "删除失败，请再试一次！");
			result.put("success", 3);
		}
		ObjectMapper objectMapper=new ObjectMapper();
		String jsonresult="";
		try {
			jsonresult = objectMapper.writeValueAsString(result);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		renderJson(response, jsonresult);
	}
	/**
	* 查询所有机构
	* @param request
	* @param response
	* @return
	* @throws Exception
	*/
	@RequestMapping(params="method=queryOrgList")
	public void queryOrgList(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
        ObjectMapper objectMapper=new ObjectMapper();
        TsysOrg tsysOrg=new TsysOrg();
        tsysOrg.setState("1");
		this.writeWeb(response,objectMapper.writeValueAsString(tsysOrgService.selectSysOrgInfo(tsysOrg)).toString());
	}
	/**
	* 查询所有下级机构页面
	* @param request
	* @param response
	* @return
	* @throws Exception
	*/
	@RequestMapping(params="method=child_list")
	public ModelAndView userlist(HttpServletRequest request,
			HttpServletResponse response) throws ControllerException {
		String org_id=request.getParameter("org_id");
		ModelAndView mv = new ModelAndView("/org/orginfomanage");
		mv.addObject(org_id);
	    return mv;
	}
	
	/**
	* 进入到机构首页面
	* @param request
	* @param response
	* @return
	* @throws Exception
	*/
	@RequestMapping(params="method=to_orgIndex")
	public ModelAndView to_orgIndex(HttpServletRequest request,
			HttpServletResponse response) throws ControllerException {
		
		ModelAndView mv = new ModelAndView("/org/orginfoindex");
		
	    return mv;
	}
	
	/**
	* 下级机构列表
	* @param request
	* @param response
	* @return
	* @throws Exception
	*/
	@RequestMapping(params="method=query")
	public void query(HttpServletRequest request,
			HttpServletResponse response) throws ControllerException {
		Map<String,Object> result = new HashMap<String,Object>();
		result.put("orgCode", request.getParameter("orgCode"));
		result.put("orgName", request.getParameter("orgName"));
		result.put("orgType", request.getParameter("orgType"));
		result.put("state", request.getParameter("state"));
		result.put("org_id", request.getParameter("org_id"));
		Page page = this.getPage(request);
		result.put("page", page);
		List list = tsysOrgService.selectPageTsysOrgByMap(result);
	    renderJson(list,page,response);
		
	}
	
	
}

