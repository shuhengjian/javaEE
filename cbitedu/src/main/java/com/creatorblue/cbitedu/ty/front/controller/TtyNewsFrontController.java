package com.creatorblue.cbitedu.ty.front.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.annotations.Param;
import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.creatorblue.cbitedu.core.baseclass.controller.HihBaseController;
import com.creatorblue.cbitedu.core.exception.ControllerException;
import com.creatorblue.cbitedu.core.page.Page;
import com.creatorblue.cbitedu.system.domain.TsysUserinfo;
import com.creatorblue.cbitedu.ty.domain.TsysAttach;
import com.creatorblue.cbitedu.ty.domain.TtyNews;
import com.creatorblue.cbitedu.ty.front.service.TtyNewsFrontService;
import com.fasterxml.jackson.core.JsonProcessingException;

@Controller
@RequestMapping("/ttyNewsFrontController.do")
public class TtyNewsFrontController extends HihBaseController {
	@Autowired
	private TtyNewsFrontService ttyNewsFrontService;
	
	/**
	 * 新闻中心动态
	 * @param ttyNews
	 * @return
	 */
	@RequestMapping(params = "method=news") 	
	public ModelAndView queryIndustry(HttpServletRequest req,@Param("newsId")String newsId) {
		ModelAndView mv = new ModelAndView("front/new-center");
		//创建page对象
		Page page = (Page) this.getPage(req);
		//
		if (req.getParameter("currentPage") != null) {
			page.setCurrentPage(Integer.parseInt(req.getParameter("currentPage")));
		}
		//每页显示数
		page.setPageSize(8);
		//创建新闻对象
		TtyNews ttyNews1=new TtyNews();
		//是否开启分页，ture为开启，false关闭
		page.setPagination(true);
		//获取页面的新闻动态的状态，newsTypeCode=1时，企业动态，2为行业动态
		String newsTypeCode = req.getParameter("newsTypeCode");
		if(newsTypeCode == null) {
			newsTypeCode = "1";
		}
		//转型
		int newsTypeCode1 = Integer.parseInt(newsTypeCode);
		ttyNews1.setNewsTypeCode(newsTypeCode1);
		ttyNews1.setPage(page);
		List<TsysAttach> list1 = ttyNewsFrontService.selectAll(ttyNews1);
		List<TsysAttach> newsImg = ttyNewsFrontService.selectNewsImg();//新闻详情图片
		mv.addObject("page", page);
		mv.addObject("list1",list1);
		mv.addObject("newsImg", newsImg);
		return mv;
	}
	
	
	/**
	 * 新闻详情页视图
	 * @param newsId
	 * @return
	 */
	@RequestMapping(params="method=queryDetails")
	public ModelAndView queryDetails(@Param("newsId")String newsId) {
		ModelAndView mv = new ModelAndView("front/news-read");
		TtyNews news = ttyNewsFrontService.selectDetailsById(newsId);//当前新闻
		TtyNews newsPrev = ttyNewsFrontService.selectPrev(news);//上一条
		TtyNews newsNext = ttyNewsFrontService.selectNext(news);//下一条
		TsysUserinfo newsName = ttyNewsFrontService.selectCreateName(newsId);//新闻发布人
		TsysAttach newsImg = ttyNewsFrontService.findNewsImg(newsId);//新闻详情图片
		if (newsImg!=null) {
			mv.addObject("newsImg", newsImg);
		}
		mv.addObject("news", news);
		mv.addObject("newsPrev", newsPrev);
		mv.addObject("newsNext", newsNext);
		mv.addObject("newsName", newsName);
		return mv;
	}
}
