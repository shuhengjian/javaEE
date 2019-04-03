package com.creatorblue.cbitedu.ty.persistence;

import java.util.List;
import java.util.Map;

import com.creatorblue.cbitedu.core.baseclass.persistence.BaseSqlMapper;
import com.creatorblue.cbitedu.system.domain.TsysUserinfo;
import com.creatorblue.cbitedu.ty.domain.TsysAttach;
import com.creatorblue.cbitedu.ty.domain.TtyBrand;
import com.creatorblue.cbitedu.ty.domain.TtyNews;
/**
 * 新闻
 * @author Administrator
 *
 * @param <T>
 */
public interface TtyNewsMapper<T> extends BaseSqlMapper<T>  {
	/**
	 * 查询新闻动态
	 * @param ttyNews
	 * @return
	 */
	public List<TtyNews> selectAll(TtyNews ttyNews);
	/**
	 *条件查询
	 * @param map
	 * @return
	 */
	public List<TtyNews> selectPageTtyNewsByMap(Map<String, Object> map);
	/**
	 * 根据新闻标题查询
	 * @param newsTitle
	 * @return
	 */
	public List selectDetailByTitle(String newsTitle);
	/**
	 * 根据id查询新闻内容
	 * @param newsId
	 * @return
	 */
	public TtyNews selectNewsContentById(String newsId);
	/**
	 * 查询
	 */
	public TtyNews selectByPrimaryKey(String newsId);
	/**
	 * 修改
	 * @param record
	 * @return
	 */
	public int updateByNews(TtyNews record);
	/**
	*查询首页活动图片
	 */
	public TsysAttach selectPkid(String value);
	
	/**
	*查询首页活动内容
	 */
	TtyNews selectActivity( );
	/**
	*查询首页天逸动态
	 */
	List<TtyNews> selectCompanyDynamic(Integer value);
	
	/**
	 * 新增
	 * @param record
	 * @return
	 */
	 public int insert(TtyNews record);
	 /**
	  * 删除
	  * @param newsId
	  * @return
	  */
	 public int del(String newsId);
	 
	 /**
		 * 根据新闻id查询单条新闻
		*/
		public TtyNews selectDetailsById(String newsId);
		
		/**
		 * 根据新闻id查询上一篇
		 * @param newsId
		 * @return
		 */
		public TtyNews selectPrev(TtyNews newsId);
		/**
		 * 根据新闻id查询下一篇
		 * @param newsId
		 * @return
		 */
		public TtyNews selectNext(TtyNews newsId);
		/**
		 * 根据用户id查询发布人姓名
		 * @param newsId
		 * @return
		 */
		public TsysUserinfo selectCreateName(String newsId);
		/**
		 * 根据新闻id关联pkid查询新闻图片(动态)
		 */
		public List<TsysAttach> selectNewsImg ();
		/**
		 * 更改新闻状态
		 * @param ttyNews
		 * @return
		 */
		public int updateState(TtyNews ttyNews);
		/**
		 * 根据新闻id关联pkid查询新闻图片(详情)
		 */
		public  TsysAttach  findNewsImg(String value);
}
