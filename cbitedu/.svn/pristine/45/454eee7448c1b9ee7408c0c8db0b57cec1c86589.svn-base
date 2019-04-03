package com.creatorblue.cbitedu.ty.back.service;

import java.util.List;
import java.util.Map;

import org.apache.poi.ss.formula.functions.T;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.creatorblue.cbitedu.core.baseclass.persistence.BaseSqlMapper;
import com.creatorblue.cbitedu.core.baseclass.service.BaseService;
import com.creatorblue.cbitedu.core.exception.ServiceException;
import com.creatorblue.cbitedu.system.persistence.TsysAppMapper;
import com.creatorblue.cbitedu.ty.domain.TsysAttach;
import com.creatorblue.cbitedu.ty.domain.TtyBrand;
import com.creatorblue.cbitedu.ty.domain.TtyNews;
import com.creatorblue.cbitedu.ty.persistence.TsysAttachMapper;
import com.creatorblue.cbitedu.ty.persistence.TtyNewsMapper;
/**
 *新闻后台
 * @author Administrator
 *
 * @param <T>
 */
@Service(value="ttyBackNewsService")
public class TtyBackNewsService<T> extends BaseService<T>{
	@Autowired
	private TtyNewsMapper<T> ttyNewsMapper;
	@Autowired 
	private TsysAttachMapper<T> tsysAttachMapper;
	public TtyNewsMapper<T> getMapper() {
		// TODO Auto-generated method stub
		return ttyNewsMapper;
	}
	public TsysAttachMapper<T> getTsysAttachMapper() {
		return tsysAttachMapper;
	}
	/**
	 * 条件查询
	 * @param map
	 * @return
	 * @throws ServiceException
	 */
	public List<TtyNews> selectPageTtyNewsByMap(Map<String, Object> map) throws ServiceException {
		return getMapper().selectPageTtyNewsByMap(map);

	}
	/**
	 * 根据新闻标题查询
	 * @param newsTitle
	 * @return
	 */
	public List selectDetailByTitle(String newsTitle) {
		return getMapper().selectDetailByTitle(newsTitle);
	}
	/**
	 * 根据id查询
	 * @param newsId
	 * @return
	 */
	public TtyNews selectByPrimaryKey(String newsId) {
		return getMapper().selectByPrimaryKey(newsId);
	}
	/**
	 * 查询新闻内容
	 * @param newsId
	 * @return
	 */
	public TtyNews selectNewsContentById(String newsId) {
		return getMapper().selectNewsContentById(newsId);
	}
	/**
	 * 根据id查询图片
	 * @return
	 */
	public TsysAttach selectAttachById(String newsId) {
		return getTsysAttachMapper().selectFileUrlById(newsId);
	}
	/**
	 * 修改
	 * @param newsId
	 * @return
	 */
	public int updateByNews(TtyNews record) throws ServiceException{
		//先删除
		getTsysAttachMapper().deleteByPrimaryKey(record.getNewsId());
		//再添加
		getTsysAttachMapper().insert(record.getTsysAttach());
		return getMapper().updateByNews(record);
	}
	/**
	 * 新增
	 * @param record
	 * @return
	 * @throws ServiceException
	 */
	public int insert(TtyNews record) throws ServiceException{
		getTsysAttachMapper().insert(record.getTsysAttach());
		return getMapper().insert(record);
	}
	/**
	 * 删除
	 * @param newsId
	 * @return
	 * @throws ServiceException
	 */
	public int del(String newsId)throws ServiceException{
		getTsysAttachMapper().deleteByPrimaryKey(newsId);
		return getMapper().del(newsId);
	}
	/**
	 * 更改新闻状态
	 * @param ttyNews
	 * @return
	 */
	public int updateState (TtyNews ttyNews) {
		return getMapper().updateState(ttyNews);
	}
}