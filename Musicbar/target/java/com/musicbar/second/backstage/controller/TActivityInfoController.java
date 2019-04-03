package com.musicbar.second.backstage.controller;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.musicbar.core.annotation.LoggerAnnotation;
import com.musicbar.core.uploads.FileUpload;
import com.musicbar.core.uploads.ImgCompress;
import com.musicbar.core.utils.StringUtil;
import com.musicbar.second.backstage.service.TActivityInfoService;
import com.musicbar.second.backstage.service.TAttachService;
import com.musicbar.second.comm.config.ConfigProperties;
import com.musicbar.second.comm.param.ParamTransformation;
import com.musicbar.second.domain.TActivityInfo;
import com.musicbar.second.domain.TAttach;
import com.musicbar.second.domain.TGoodsInfo;

/** 
 * 活动管理实现类
 * @author xzt
 *
 */
@Controller
@RequestMapping("/backstage")
public class TActivityInfoController { 
	@Autowired
	private  TActivityInfoService tActivityInfoService;
	
	@Autowired
	private ParamTransformation paramTransformation;
	
	@Autowired
	private ConfigProperties configProperties;
	
	@Autowired
	private TAttachService tAttachService;
	
	/**
	 * 进入活动列表页面
	 * @return
	 */
	@RequestMapping("/active_querylist")
	@LoggerAnnotation(begin="进入查询活动列表页面",end="查询活动列表页面结束")
	public ModelAndView list() {
		ModelAndView mv = new ModelAndView("backstage/pages/active/list");
		return mv;
	}
	/**
	 * 查询活动列表
	 * @return
	 */
	@RequestMapping("/active_queryAll")
	@LoggerAnnotation(begin="进入查询活动列表方法",end="查询活动列表方法结束") 
	@ResponseBody
	public String queryAll( TActivityInfo active,int pageNum) {
		int pageSize = configProperties.getPageSize();
		PageHelper.startPage(pageNum,pageSize);
		List<TActivityInfo> list = tActivityInfoService.selectAll(active);
		PageInfo<TActivityInfo> page = new PageInfo<>(list);
		return net.sf.json.JSONArray.fromObject(page).toString();
	}
	
	/**
	 * 查询活动状态
	 * @return
	 */
	@RequestMapping("/active_state")
	@ResponseBody
	public String selectState() { 
		List<TActivityInfo> list = tActivityInfoService.selectStates();
		return net.sf.json.JSONArray.fromObject(list).toString(); 
	}
	
	/**
	 * 进入编辑页面
	 * @return
	 */
	@RequestMapping("/active_open")
	@LoggerAnnotation(begin="进入活动添加页面方法",end="查询活动添加页面方法结束")
	public ModelAndView open(String activId) {
		ModelAndView mv = new ModelAndView("backstage/pages/active/edit");
		if(activId != null && !activId.isEmpty()) {
			TActivityInfo activ = tActivityInfoService.selectActiveById(activId);
			mv.addObject("activ",activ);
		}
		//查询活动状态
		List<TActivityInfo> list = tActivityInfoService.selectStates();
		mv.addObject("list", list);
		return mv; 
	}
    /**
	 * 添加or修改
	 * @return
	 * @throws IOException 
	 * @throws IllegalStateException 
	 */
	@RequestMapping("/active_save") 
	@ResponseBody
	@LoggerAnnotation(begin="进入活动添加or修改方法",end="查询活动添加or修改方法结束")
	public String save(HttpServletRequest request,TActivityInfo active,@RequestParam("file") MultipartFile file){
		TAttach attach = new TAttach();//创建资源类 
		//创建文件上传
		FileUpload fileUpload = new FileUpload();
		JSONObject json=new JSONObject();
		String fileUrl = request.getParameter("img");
		
		if(file !=null && file.getSize() > 0) {
			fileUpload.setSaveFilePath(configProperties.getPath());//根路径
			fileUpload.setPath(configProperties.getActivePath());//上传文件的相对路径
			fileUpload.setAllowFiles(".jpg .jpeg .gif .png .bmp");//文件上传类型
			fileUpload.fileUpload(file, fileUpload);
		}
		attach.setFileName(active.getActivTheme());
		if(fileUpload.isIs()) {
			attach.setFileSuffix(fileUpload.getSuffix());
			attach.setFileUel(fileUpload.getPath()+fileUpload.getFileName());
		}
		String actTheme = request.getParameter("activTheme");
		String actId = request.getParameter("activId");
		String msg = active.getActivId() !=null && ! active.getActivId().isEmpty()? "修改":"添加";
		int success = tActivityInfoService.saveOrUpdate(active,fileUrl);
		try {
			//压缩图片
			String url = fileUpload.getSaveFilePath()+fileUpload.getPath()+fileUpload.getFileName();
			ImgCompress imgCom = new ImgCompress(url);
			imgCom.resize(600,300,url);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(success > 0) {
			json.put("code", 200);
	       	json.put("msg", msg+"成功");
	       	json.put("flag", true);
		}else {
			if(success == 0) {
			    json.put("code", 500);
			    json.put("msg", msg+"失败");
			    json.put("flag", false); 
	    	}else if(success == -1 && active.getActivId() == "") {
	    		json.put("code", 500);
	    		json.put("msg", "活动主题已存在");
	    		json.put("flag", false);
	    	}else if(actTheme.equals(active.getActivTheme()) && !actId.equals(active.getActivId())){
	    		json.put("code", 500);
	    		json.put("msg", "活动主题已存在");
	    		json.put("flag", false);
	    	}else {
	    		json.put("code", 200);
	    		json.put("msg", msg+"成功");
	    		json.put("flag", true);
	    	}
		}
		return json.toString();

	}
	/**
	 * 删除
	 * @param goodsId
	 * @return
	 */
	@RequestMapping("/active_delete")
	@ResponseBody
	public String delete(String activId) {
		JSONObject json=new JSONObject();
		int count = tActivityInfoService.deleteById(activId);
		int num = tAttachService.deleteById(activId);
		if(count > 0 && num > 0) {
			json.put("code", 200);
			json.put("msg", "删除成功");
		}else {
			json.put("code", 500);
			json.put("msg", "删除失败");
		}
		return json.toString();
	}
	/**
	 * 批量删除
	 */
	@RequestMapping("/active_deleteAll")
	@ResponseBody
	public String deleteAll(String id) {
		JSONObject json=new JSONObject();
		int count = 0,num = 0;
		if(!id.equals("")) {
			String[] split = id.split(",");
			List<String> list = new ArrayList<String>();
			for(String string : split) {
				list.add(string);
			}
			count = tActivityInfoService.deleteAll(list);
			num = tAttachService.deleteAll(list);
		}else {
			count = -1;
			num = -1;
		}
		if(count > 0 ) {
			json.put("code", 200);
			json.put("msg", "操作成功");
		}else if(count == -1) {
			json.put("code", 500);
			json.put("msg", "请勾选要删除的记录");
		}else {
			json.put("code", 500);
			json.put("msg", "操作失败");
		}
		return json.toString();
	}
	/**
	 * 启用，禁用
	 * @param goods
	 * @return
	 */
	@RequestMapping("/active_updateState")
	@ResponseBody
	public String updateState(TActivityInfo active) {
		JSONObject json=new JSONObject();
		int count = tActivityInfoService.updateState(active);
		if(count == 1) {
			json.put("code", 200);
			json.put("msg", "操作成功");
		}else {
			json.put("code", 500);
			json.put("msg", "操作失败");
		}
		return json.toString();
	}
	/**
	 * 预览
	 * @return
	 */
	@RequestMapping("/goActiveInfo")
	@LoggerAnnotation(begin = "进入活动中。。", end = "成功进入活动。。")
	public ModelAndView goActiveInfo(String activId) {
		ModelAndView mv = new ModelAndView("backstage/pages/active/preview");
		TActivityInfo activ = tActivityInfoService.selectActiveById(activId);
		mv.addObject("activ",activ);
		return mv;
	}
}
