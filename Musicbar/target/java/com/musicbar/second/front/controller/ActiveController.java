package com.musicbar.second.front.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.musicbar.core.annotation.LoggerAnnotation;
import com.musicbar.second.backstage.service.TActivityInfoService;
import com.musicbar.second.backstage.service.TAttachService;
import com.musicbar.second.comm.config.ConfigProperties;
import com.musicbar.second.domain.TActivityInfo;

@Controller
@RequestMapping("/front")
public class ActiveController {
	@Autowired
	private  TActivityInfoService tActivityInfoService;
	
	@Autowired
	private TAttachService attachService; // 图片
	
	@Autowired
	private ConfigProperties configProperties;
	
	/**
	 * 进入活动页面
	 * @return
	 */
	@RequestMapping("/goActive")
	@LoggerAnnotation(begin = "进入活动中。。", end = "成功进入活动。。")
	public ModelAndView goActive() {
		ModelAndView mv = new ModelAndView("front/activity");
		return mv;
	}
	/**
	 * 进入活动详情页面
	 * @return
	 */
	@RequestMapping("/goActiveInfo")
	@LoggerAnnotation(begin = "进入活动中。。", end = "成功进入活动。。")
	public ModelAndView goActiveInfo(String activId) {
		ModelAndView mv = new ModelAndView("front/activityRead");
		TActivityInfo activ = tActivityInfoService.selectActiveById(activId);
		mv.addObject("activ",activ);
		return mv;
	}
	/**
	 * 查询活动列表
	 * @return
	 */
	@RequestMapping("/active_frontQueryAll")
	@LoggerAnnotation(begin="进入查询活动列表方法",end="查询活动列表方法结束") 
	@ResponseBody
	public String queryAll( TActivityInfo active,int pageNum) {
		int pageSize = configProperties.getPageSize();
		PageHelper.startPage(pageNum,pageSize);
		List<TActivityInfo> list = tActivityInfoService.selectAll(active);
		PageInfo<TActivityInfo> page = new PageInfo<>(list);
		return net.sf.json.JSONArray.fromObject(page).toString();
	}
}
