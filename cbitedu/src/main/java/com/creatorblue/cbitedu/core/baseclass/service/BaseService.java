package com.creatorblue.cbitedu.core.baseclass.service;

import java.util.List;

import org.apache.log4j.Logger;

import com.creatorblue.cbitedu.core.baseclass.domain.BaseDomain;
import com.creatorblue.cbitedu.core.baseclass.persistence.BaseSqlMapper;
import com.creatorblue.cbitedu.core.exception.ServiceException;

/**
 * Title: 业务逻辑层基类 Description: Copyright: Copyright (c) 2014
 * Company:hihsoft.co.,ltd
 * 
 * @author hihsoft.co.,ltd
 * @version 1.0
 */
public abstract class BaseService<T> {
	protected final Logger log = Logger.getLogger(this.getClass());

	public abstract BaseSqlMapper<T> getMapper();

	/**
	 * 插入數據
	 * 
	 * @param t
	 * @throws ServiceException
	 */
	public void insert(T t) throws ServiceException {
		getMapper().insert(t);
	}

	public int insertBatch(List<T> list) {
		return getMapper().insertBatch(list);
	}

	/**
	 * 更新數據
	 * 
	 * @param t
	 * @throws ServiceException
	 */
	public void update(T t) throws ServiceException {
		getMapper().update(t);
	}

	public void updateBySelective(T t) {
		getMapper().updateBySelective(t);
	}

	/**
	 * 批量刪除
	 * 
	 * @param ids
	 * @throws ServiceException
	 */
	public void delete(String... ids) throws ServiceException {
		if (ids == null || ids.length < 1) {
			return;
		}
		for (String id : ids) {
			getMapper().delete(id);
		}
	}

	public T selectDetailById(String id) throws ServiceException {
		return getMapper().selectDetailById(id);
	}

	/**
	 * 記錄數
	 * 
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public int selectByCount(BaseDomain model) throws Exception {
		return getMapper().selectByCount(model);
	}

	/**
	 * 帶分頁條件的查詢
	 * 
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public List<T> selectByList(BaseDomain model) throws Exception {
		Integer rowCount = selectByCount(model);
		model.getPage().setTotalRows(rowCount);
		return getMapper().selectByList(model);
	}

	/**
	 * 根據查詢條件查詢
	 * 
	 * @param t
	 * @return
	 * @throws ServiceException
	 */
	public List select(T t) throws ServiceException {
		return getMapper().select(t);
	}
}
