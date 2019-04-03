package com.creatorblue.cbitedu.ty.back.service;

import java.util.List;
import java.util.Map;

import org.apache.poi.ss.formula.functions.T;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.creatorblue.cbitedu.core.baseclass.persistence.BaseSqlMapper;
import com.creatorblue.cbitedu.core.baseclass.service.BaseService;
import com.creatorblue.cbitedu.core.exception.ServiceException;
import com.creatorblue.cbitedu.ty.domain.TsysAttach;
import com.creatorblue.cbitedu.ty.domain.TtyBrand;
import com.creatorblue.cbitedu.ty.domain.TtyPrice;
import com.creatorblue.cbitedu.ty.domain.TtyType;
import com.creatorblue.cbitedu.ty.persistence.TsysAttachMapper;
import com.creatorblue.cbitedu.ty.persistence.TtyTypeMapper;
@Service(value="ttyBackTypeService")
public class TtyBackTypeService<T>  extends BaseService<T> {
	@Autowired
	private TtyTypeMapper mapper;
	@Autowired 
	private TsysAttachMapper<T> tsysAttachMapper;
	public TtyTypeMapper getMapper() {
		return mapper;
	}
	public TsysAttachMapper<T> getTsysAttachMapper() {
		return tsysAttachMapper;
	}
	public List<TtyType> selectPageTtyTypeByMap(Map<String, Object> map) throws ServiceException{
		return getMapper().selectPageTtyTypeByMap(map);
	}
	public TtyType checkTheNameWithParentId(TtyType ttyType) throws ServiceException{
		return getMapper().checkTheNameWithParentId(ttyType);
	}
	public int insert(TtyType record) throws ServiceException{
		return getMapper().insert(record);
	}
	public int updateByPrimaryKey(TtyType record) throws ServiceException{
		return getMapper().updateByPrimaryKey(record);
	}
	public int deleteByPrimaryKey(String typeId) throws ServiceException{
		getTsysAttachMapper().deleteByPrimaryKey(typeId);
		return getMapper().deleteByPrimaryKey(typeId);
	}
	public TtyType selectByPrimaryKey(String typeId) throws ServiceException{
		return getMapper().selectByPrimaryKey(typeId);
	}
	public int updateTypeStateByPrimaryKey(TtyType record) throws ServiceException{
		return getMapper().updateTypeStateByPrimaryKey(record);
	}
	public List<TtyType> selectType(Map<String, Object> map) throws ServiceException {
		return getMapper().selectType(map);
	}
	public int selectCountProduct(String typeId) throws ServiceException{
		 return getMapper().selectCountProduct(typeId);
	 }
}
