package com.creatorblue.cbitedu.ty.persistence;

import java.util.Map;

import com.creatorblue.cbitedu.core.baseclass.persistence.BaseSqlMapper;
import com.creatorblue.cbitedu.ty.domain.TsysAttach;
import com.creatorblue.cbitedu.ty.domain.TtyBrand;

public interface TsysAttachMapper<T> extends BaseSqlMapper<T> {
	/**
	 * 根据id删除
	 * @param pkid
	 * @return
	 */
	public int deleteByPrimaryKey(String pkid);
	/**
	 * 新增
	 * @param tsysAttach
	 * @return
	 */
	public int insert(TsysAttach tsysAttach);
	/**
	 * 根据id查询图片
	 * @param id
	 * @return
	 */
	public TsysAttach selectFileUrlById(String id);
	/**
	 * 
	 * @param obj
	 * @return
	 */
	public TsysAttach selectAttachById(Map<String, Object> obj);
	
	public TsysAttach selectfindById(String obj);
	
}