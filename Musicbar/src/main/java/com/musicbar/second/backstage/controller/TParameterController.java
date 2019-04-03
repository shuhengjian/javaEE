package com.musicbar.second.backstage.controller;

import java.util.ArrayList;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.musicbar.core.annotation.LoggerAnnotation;
import com.musicbar.core.redis.RedisUtils;
import com.musicbar.second.backstage.service.TParameterService;
import com.musicbar.second.comm.config.ConfigProperties;
import com.musicbar.second.comm.param.ParamUtil;
import com.musicbar.second.domain.TParameter;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
/**
 * 参数管理
 * @author Shinelon
 *
 */
@Controller
@RequestMapping("/backstage")
public class TParameterController {
	@Autowired
	private TParameterService tParameterService;
	@Autowired
	private RedisUtils redisUtils;
	@Autowired
	private ConfigProperties configProperties;
	@Autowired
	private ParamUtil paramutil;
	/**
	 * 查询所有参数
	 * @return
	 */
	@RequestMapping("/param_querylist")
	@LoggerAnnotation(begin="查询参数方法开始",end="查询参数方法结束")
	public ModelAndView list() {
		ModelAndView mv = new ModelAndView("backstage/pages/parameter/list");
		int pageSize = configProperties.getPageSize();//每页显示数量
		int pageNum = 1;//当前页
		PageHelper.startPage(pageNum,pageSize);
		List<TParameter> list = tParameterService.selectAll();	
		PageInfo<TParameter> page = new PageInfo<>(list);
		//查询参数状态
		List<TParameter> paralist = paramutil.getListByKey("para_state");//缓存中取状态值
		List<TParameter> paralist1 = paramutil.getListByKey("ISDEFAULT");//缓存中取状态值
		mv.addObject("paralist",paralist);
		mv.addObject("paralist1",paralist1);
		mv.addObject("page",page);
		return mv;
	}
	/**
	 * 根据条件查询
	 * @return
	 */
	@RequestMapping("/param_query")
	@ResponseBody
	@LoggerAnnotation(begin="查询方法开始",end="查询方法结束")
	public String query(String paraName,String paraType,String paraState,Integer pageNum) {
		TParameter tParameter = new TParameter();
		tParameter.setParaName(paraName);
		tParameter.setParaType(paraType);
		tParameter.setParaState(paraState);
		List<TParameter> paralist = paramutil.getListByKey("para_state");
		int pageSize = configProperties.getPageSize();//每页显示数量
		PageHelper.startPage(pageNum,pageSize);
		List<TParameter> list = tParameterService.selectByParam(tParameter);
		PageInfo<TParameter> page = new PageInfo<>(list);
		return JSONArray.fromObject(page).toString();
		
	}
	/**
	 * 进入编辑页面
	 * @param paraId
	 * @return
	 */
	@RequestMapping("/param_edit")
	@LoggerAnnotation(begin="进入参数编辑页面方法",end="查询参数编辑页面方法结束")
	public ModelAndView edit(String paraId) {
		ModelAndView mv = new ModelAndView("backstage/pages/parameter/edit");
		//判断新增还是修改
		if(paraId != null && !paraId.isEmpty()) {
			TParameter parameter = tParameterService.selectById(paraId);
			 	 mv.addObject("parameter",parameter);
		}
		//查询参数状态
		List<TParameter> paralist = paramutil.getListByKey("para_state");//缓存中取状态值
		List<TParameter> paralist1 = paramutil.getListByKey("ISDEFAULT");//缓存中取状态值
		mv.addObject("paralist",paralist);
		mv.addObject("paralist1",paralist1);
		return mv;
	}
	/**
	 * 保存
	 * @return
	 */
	@RequestMapping("/param_save")
	@LoggerAnnotation(begin="新增参数方法开始",end="新增参数方法结束")
	@ResponseBody
	public String save(TParameter param) {
		JSONObject json = new JSONObject();
		String msg = param.getParaId() != null && !param.getParaId().isEmpty()? "修改成功":"添加成功";
		TParameter tparam = new TParameter();
		tparam.setParaName(param.getParaName());
		tparam.setParaVal(param.getParaVal());
		List<TParameter> plist = tParameterService.selectByParam(tparam);
		boolean flag = true;
		
		if(plist.size() != 0) {
			TParameter pa = plist.get(0);
			if(param.getParaId()!=null &&!pa.getParaId().equals(param.getParaId()) ||param.getParaId()==null) {
				msg = "字典名称已存在";
				flag = false;
				json.put("flag", flag);
				json.put("msg", msg);
				return json.toString();
			}
			
		}
		TParameter tparamter = new TParameter();
		tparamter.setParaName(param.getParaName());
		tparamter.setParaNo(param.getParaNo());
		List<TParameter> paralist = tParameterService.selectByParam(tparamter);
		if(paralist.size() != 0) {
			TParameter pa = paralist.get(0);
			if(param.getParaId()!=null &&!pa.getParaId().equals(param.getParaId()) ||param.getParaId()==null) {
				msg = "字典值已存在";
				flag = false;
				json.put("flag", flag);
				json.put("msg", msg);
				return json.toString();
			}
		}
		int success = tParameterService.insertparameter(param);
		json.put("msg", msg);
		json.put("flag", flag);
		List<TParameter> list = tParameterService.slectAllTParameter();
		
		List<String> list1 = new ArrayList<>();
		for(int i=0;i<list.size();i++) {
			TParameter tParameter =  list.get(i);
			JSONObject json1 = JSONObject.fromObject(tParameter);
			list1.add(json1.toString());	
		}
		redisUtils.deleteCache("parameter");
		redisUtils.setCacheList("parameter", list1);
		
		return json.toString();
	}
	/**
	 * 删除
	 * @return
	 */
	@RequestMapping("/param_delete")
	@ResponseBody
	@LoggerAnnotation(begin="删除角色方法开始",end="删除角色方法结束")
	public String delete(String paraId) {
		JSONObject json = new JSONObject();
		String msg = "删除成功";
		TParameter tp = tParameterService.selectById(paraId);
		if(tp.getParaState().equals("1")) {
			msg = "启用状态不能删除，请先禁用！";
		}else {
			tParameterService.deleteByPrimaryKey(paraId);
		}
		
		json.put("msg",msg);
		
		List<TParameter> list = tParameterService.slectAllTParameter();
		
		List<String> list1 = new ArrayList<>();
		for(int i=0;i<list.size();i++) {
			TParameter tParameter =  list.get(i);
			JSONObject json1 = JSONObject.fromObject(tParameter);
			list1.add(json1.toString());	
		}
		redisUtils.deleteCache("parameter");
		redisUtils.setCacheList("parameter", list1);
		
		return json.toString();
	}
	/**
	 * 修改参数状态
	 * @param paraState
	 * @param paraId
	 * @return
	 */
	@RequestMapping("/param_state")
	@ResponseBody
	@LoggerAnnotation(begin="修改参数状态方法开始",end="修改参数状态方法结束")
	public String paramState(String paraState,String paraId) {
		JSONObject json = new JSONObject();
		String msg = "";
		try {
			TParameter param = new TParameter();
			param.setParaId(paraId);
			param.setParaState(paraState);
			tParameterService.updateparameter(param);
			if(paraState.equals("1")) {
				msg = "启用成功";
			}else {
				msg = "停用成功";
			}
			
		}catch (Exception e) {
			// TODO: handle exception
			if(paraState == "1") {
				msg = "启用失败";
			}else {
				msg = "停用失败";
			}
		}
		json.put("msg", msg);
		return json.toString();
	}
}
