package com.creatorblue.cbitedu.ty.front.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.creatorblue.cbitedu.core.baseclass.service.BaseService;
import com.creatorblue.cbitedu.core.page.Page;
import com.creatorblue.cbitedu.system.domain.TsysUserinfo;
import com.creatorblue.cbitedu.ty.domain.TsysAttach;
import com.creatorblue.cbitedu.ty.domain.TtyNews;
import com.creatorblue.cbitedu.ty.persistence.TtyNewsMapper;
@Service(value = "ttyNewsFrontService")
public class TtyNewsFrontService<T> extends BaseService<T> {
	
	@Autowired
	private TtyNewsMapper<T> mapper;
	
	public TtyNewsMapper<T> getMapper() {
		return mapper;
	}
	/**
	 * 查询所有新闻信息
	 * @return
	 */
	public List<TtyNews> selectAll(TtyNews ttyNews){
		return mapper.selectAll(ttyNews);
	}
	/**
	*查询首页活动图片
	 */
	public TsysAttach selectPkid(String value){
		return getMapper().selectPkid(value);
	}
	
	/**
	*查询首页活动内容
	 */
	public TtyNews selectActivity() {
		TtyNews news=new TtyNews();
		try {
			news=getMapper().selectActivity();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return news;
	}
	/**
	*查询首页天逸动态
	 */
	public List<TtyNews> selectCompanyDynamic(Integer value){
		return getMapper().selectCompanyDynamic(value);
	}
	
	
	/**
	 * 根据新闻id查询单条新闻
	 */
	public TtyNews selectDetailsById(String newsId) {
		return mapper.selectDetailsById(newsId);
	}
	/**
	 * 根据新闻id查询上一篇
	 * @param newsId
	 * @return
	 */
	public TtyNews selectPrev(TtyNews newsId) {
		return getMapper().selectPrev(newsId);
	}
	/**
	 * 根据新闻id查询下一篇
	 * @param newsId
	 * @return
	 */
	public TtyNews selectNext(TtyNews newsId) {
		return getMapper().selectNext(newsId);
	}
	/**
	 * 根据用户id查询发布人姓名
	 * @param newsId
	 * @return
	 */
	public TsysUserinfo selectCreateName(String newsId) {
		return getMapper().selectCreateName(newsId);
	}
	/**
	 * 根据新闻id关联pkid查询新闻图片
	 */
	public List<TsysAttach> selectNewsImg ( ) {
		return getMapper().selectNewsImg();
	}
	/**
	 * 根据新闻id关联pkid查询新闻图片
	 */
	public TsysAttach findNewsImg ( String value)  {
		return getMapper().findNewsImg(value);
	}
}
