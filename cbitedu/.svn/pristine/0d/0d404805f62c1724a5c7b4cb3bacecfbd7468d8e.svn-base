package com.creatorblue.cbitedu.ty.back.controller;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.WebUtils;

import com.creatorblue.cbitedu.core.baseclass.controller.HihBaseController;
import com.creatorblue.cbitedu.core.constants.Constant;
import com.creatorblue.cbitedu.core.exception.ControllerException;
import com.creatorblue.cbitedu.core.page.Page;
import com.creatorblue.cbitedu.core.utils.DateUtils;
import com.creatorblue.cbitedu.core.utils.Identities;
import com.creatorblue.cbitedu.core.utils.PropertiesUtil;
import com.creatorblue.cbitedu.system.domain.TsysUserinfo;

import com.creatorblue.cbitedu.ty.back.service.TtyBackNewsService;
import com.creatorblue.cbitedu.ty.domain.TsysAttach;

import com.creatorblue.cbitedu.ty.domain.TtyNews;

import comm.FileUpload;

@Controller
@RequestMapping("ttyBackNewsController.do")
public class TtyBackNewsController extends HihBaseController{
	@Autowired
	private TtyBackNewsService  ttyNewsService;
	/**
	 * 	访问列表， 并且初始化列表中所需的数据
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(params = "method=list")
	public ModelAndView list(HttpServletRequest request,
			HttpServletResponse response) throws ControllerException {
		ModelAndView mv = new ModelAndView("/news/ttynewslist");
		return mv;
	}

	/**
	 * 访问列表， 查询数据
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(params = "method=query")
	public void query(HttpServletRequest request, HttpServletResponse response) {
		Page page = this.getPage(request);
		Map<String, Object> param = WebUtils.getParametersStartingWith(request,
				"news_");
		param.put("page", page);
		List<TtyNews> list = ttyNewsService.selectPageTtyNewsByMap(param);
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
			HttpServletResponse response) throws ControllerException {
		return new ModelAndView("/news/ttynewsform");
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
		ModelAndView mav = new ModelAndView("/news/ttynewsform");

		String id = request.getParameter("id");
		TtyNews ttyNews = (TtyNews) ttyNewsService.selectByPrimaryKey(id);
		mav.addObject("ttyNews", ttyNews);
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
			HttpServletResponse response,
			@RequestParam("file") MultipartFile file) {
		//获得上传的内容
		TtyNews ttyNews = new TtyNews();
		this.setValue(request, ttyNews);
		//创建资源类
		TsysAttach tsysAttach = new TsysAttach();
		//文件上传
		FileUpload fileUpload = new FileUpload();
		boolean flag = true;
		String msg = null;
		//根据标题查询新闻
		List list = ttyNewsService.selectDetailByTitle(ttyNews.getNewsTitle());
		try {
			//路径
			fileUpload.setSaveFilePath(PropertiesUtil.getValue( "saveFilePath"));
			fileUpload.setPath(PropertiesUtil.getValue( "newsPath"));
			fileUpload.setAllowFiles(".jpg .jpeg .gif .png .bmp");
			//获取当前登录用户
			TsysUserinfo userinfo = (TsysUserinfo) getSession(request,Constant.USER_INFO);//获取当前登录用户
			String uId = userinfo.getUserId();
			//获取当前时间转换成字符串
			String time = DateUtils.fotmatDateTOyMdHms(new Date());
			//执行文件上传
			fileUpload.fileUpload(file, fileUpload);
			//文件上传失败直接提交
			if(!fileUpload.isIs() && fileUpload.getState() != "") {
				flag = false;
				msg = "文件上传失败!";
				String result = "{\"flag\" : " + flag + " , \"msg\" : \"" + msg + "\"}";
				this.renderJson(response, result);
				return;
			}
			//赋值
			tsysAttach.setFileName(ttyNews.getNewsTitle());
			//新增
			if (StringUtils.isEmpty(ttyNews.getNewsId())) {
				//判断标题是否存在
				if(list.size()!=0) {
					msg = "标题已存在！";
					String checkResult = "{\"flag\" : " + false+ " , \"msg\" : \"" + msg + "\"}";
					this.renderJson(response, checkResult);
					return;
				}
				ttyNews.setNewsId(Identities.uuid());
				//资源
				tsysAttach.setFileUrl(fileUpload.getPath()+fileUpload.getFileName());
				tsysAttach.setAttachId(Identities.uuid());
				tsysAttach.setPkid(ttyNews.getNewsId());
				tsysAttach.setCreateUserId(uId);
				tsysAttach.setCreateTime(time);
				tsysAttach.setSecondName(fileUpload.getOriginalName());
				tsysAttach.setFileSuffix(fileUpload.getSuffix());
				tsysAttach.setFileSize(String.valueOf(fileUpload.getSize()));
				//新闻
				ttyNews.setTsysAttach(tsysAttach);
				ttyNews.setCreateTime(new Date());
				ttyNews.setCreateUserId(uId);
				ttyNewsService.insert(ttyNews);
				msg = "保存成功！";
			//修改
			} else {
				//判断标题是否存在
				TtyNews news = (TtyNews) list.get(0);
				if(!news.getNewsTitle().equals(ttyNews.getNewsTitle())) {
					msg = "标题已存在！";
					String checkResult = "{\"flag\" : " + false
							+ " , \"msg\" : \"" + msg + "\"}";
					this.renderJson(response, checkResult);
					return;
				}
				//根据id查询资源表内的属性
				TsysAttach attach = ttyNewsService.selectAttachById(ttyNews.getNewsId());
				if(file==null||file.getSize()<=0) {
					//不修改图片
					tsysAttach.setFileUrl(attach.getFileUrl());
					tsysAttach.setSecondName(attach.getSecondName());
					tsysAttach.setFileSuffix(attach.getFileSuffix());
					tsysAttach.setFileSize(attach.getFileSize());
				}else {
					//修改图片
					tsysAttach.setFileUrl(fileUpload.getPath()+fileUpload.getFileName());
					tsysAttach.setSecondName(fileUpload.getOriginalName());
					tsysAttach.setFileSuffix(fileUpload.getSuffix());
					tsysAttach.setFileSize(String.valueOf(fileUpload.getSize()));
				}
				//资源
				tsysAttach.setPkid(ttyNews.getNewsId());
				tsysAttach.setAttachId(Identities.uuid());
				tsysAttach.setCreateUserId(attach.getCreateUserId());
				tsysAttach.setCreateTime(attach.getCreateTime());
				tsysAttach.setUpdateUserId(uId);
				tsysAttach.setUpdateTime(time);
				//新闻
				ttyNews.setTsysAttach(tsysAttach);
				ttyNews.setUpdateUserId(uId);
				ttyNews.setUpdateTime(new Date());
				ttyNewsService.updateByNews(ttyNews);
				msg = "修改成功！";
			}
		} catch (Exception e) {
			flag = false;
			msg = "保存失败";
			e.printStackTrace();
		}
		String result = "{\"flag\" : " + flag + " , \"msg\" : \"" + msg + "\"}";
		this.renderJson(response, result);
	}
	/**
	 * 删除一条或多条数据
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "method=del")
	public void deleteById(HttpServletRequest request,
			HttpServletResponse response) {
		String[] newsId = request.getParameterValues("newsId");
		boolean flag = true;
		try {
			//循环删除
			for (int i = 0; i < newsId.length; i++) {
				ttyNewsService.del(newsId[i]);
			}
		} catch (Exception e) {
			e.printStackTrace();
			flag = false;
		}
		String result = "{\"flag\" : " + flag + "}";
		this.renderJson(response, result);
	}
	/**
	 * 修改新闻状态
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "method=updateState")
	public void updateState(HttpServletRequest request,
			HttpServletResponse response) {
		//获取新闻状态 新闻id
		Integer newsState = Integer.valueOf(request.getParameter("state"));
		String newsId =request.getParameter("id");
		TtyNews ttyNews =  new TtyNews();
		ttyNews.setNewsState(newsState);
		ttyNews.setNewsId(newsId);
		//更改状态
		int i= ttyNewsService.updateState(ttyNews);
		boolean flag = false;
		if(i==1) {
			flag = true ;
		}
		String result = "{\"flag\" : " + flag + "}";
		this.renderJson(response, result);
	}
	/**
	 * 查询新闻内容
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(params = "method=queryNewsContent")
	@ResponseBody
	public String queryNewsContent(HttpServletRequest request,
			HttpServletResponse response) {
		//获得新闻id
		String newsId =request.getParameter("id");
		TtyNews ttyNews = ttyNewsService.selectNewsContentById(newsId);
		JSONObject json = new JSONObject(ttyNews);
		return json.toString();
	}

}
