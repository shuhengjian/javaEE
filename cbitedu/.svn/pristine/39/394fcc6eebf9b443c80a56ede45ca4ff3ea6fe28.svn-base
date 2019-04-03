package com.creatorblue.cbitedu.ty.back.service;

import java.util.List;
import java.util.Map;

import org.apache.poi.ss.formula.functions.T;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.creatorblue.cbitedu.core.baseclass.service.BaseService;
import com.creatorblue.cbitedu.core.exception.ServiceException;
import com.creatorblue.cbitedu.ty.domain.TtyBrand;
import com.creatorblue.cbitedu.ty.persistence.TsysAttachMapper;
import com.creatorblue.cbitedu.ty.persistence.TtyBrandMapper;
@Service(value="ttyBackBrandService")
public class TtyBackBrandService  extends BaseService<T> {
	@Autowired
	private TtyBrandMapper mapper;
	@Autowired 
	private TsysAttachMapper<T> tsysAttachMapper;
	
	public TtyBrandMapper getMapper() {
		return mapper;
	}
	public TsysAttachMapper<T> getTsysAttachMapper() {
		return tsysAttachMapper;
	}
	public List<TtyBrand> selectPageTtyBrandByMap(Map<String, Object> map) throws ServiceException{
		return getMapper().selectPageTtyBrandByMap(map);
	}
	public TtyBrand checkTheNameWithParentId(TtyBrand ttyBrand) throws ServiceException{
		return getMapper().checkTheNameWithParentId(ttyBrand);
	}
	public int insert(TtyBrand record) throws ServiceException{
		getTsysAttachMapper().insert(record.getTsysAttach());
		return getMapper().insert(record);
	}
	public int updateByPrimaryKey(TtyBrand record) throws ServiceException{
		return getMapper().updateByPrimaryKey(record);
	}
	public int deleteByPrimaryKey(String brandId) throws ServiceException{
		getTsysAttachMapper().deleteByPrimaryKey(brandId);
		return getMapper().deleteByPrimaryKey(brandId);
	}
	public TtyBrand selectByPrimaryKey(String brandId) throws ServiceException{
		return getMapper().selectByPrimaryKey(brandId);
	}
	public int updateBrandStateByPrimaryKey(TtyBrand record) throws ServiceException{
		return getMapper().updateBrandStateByPrimaryKey(record);
	}
	 public int selectCountProduct(String brandId) throws ServiceException{
		 return getMapper().selectCountProduct(brandId);
	 }
	 
	 
	 
	 
	 
	 
	 public List<TtyBrand> selectBrand(Map<String, Object> map) throws ServiceException {
			return getMapper().selectBrand(map);
		}
}
