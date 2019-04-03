package com.creatorblue.cbitedu.ty.back.service;

import java.util.List;
import java.util.Map;

import org.apache.poi.ss.formula.functions.T;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.creatorblue.cbitedu.core.baseclass.service.BaseService;
import com.creatorblue.cbitedu.core.exception.ServiceException;
import com.creatorblue.cbitedu.ty.domain.TtyPrice;
import com.creatorblue.cbitedu.ty.persistence.TsysAttachMapper;
import com.creatorblue.cbitedu.ty.persistence.TtyPriceMapper;

@Service(value="TtyBackPriceService")
public class TtyBackPriceService<T>  extends BaseService<T> {

	@Autowired
	private TtyPriceMapper mapper;
	@Autowired 
	private TsysAttachMapper<T> tsysAttachMapper;
	public TtyPriceMapper getMapper() {
		return mapper;
	}
	public TsysAttachMapper<T> getTsysAttachMapper() {
		return tsysAttachMapper;
	}
	public List<TtyPrice> selectPageTtyPriceByMap(Map<String, Object> map) throws ServiceException{
		return getMapper().selectPageTtyPriceByMap(map);
	}
	public TtyPrice checkTheMaxWithParentId(TtyPrice ttyPrice) throws ServiceException{
		return getMapper().checkTheMaxWithParentId(ttyPrice);
	}
	public TtyPrice checkTheMinWithParentId(TtyPrice ttyPrice) throws ServiceException{
		return getMapper().checkTheMinWithParentId(ttyPrice);
	}
	public int insert(TtyPrice record) throws ServiceException{
		return getMapper().insert(record);
	}
	public int updateByPrimaryKey(TtyPrice record) throws ServiceException{
		return getMapper().updateByPrimaryKey(record);
	}
	public int deleteByPrimaryKey(String priceId) throws ServiceException{
		getTsysAttachMapper().deleteByPrimaryKey(priceId);
		return getMapper().deleteByPrimaryKey(priceId);
	}
	public int updatePriceStateByPrimaryKey(TtyPrice record) throws ServiceException{
		return getMapper().updatePriceStateByPrimaryKey(record);
	}
	public TtyPrice selectByPrimaryKey(String priceId) throws ServiceException{
		return getMapper().selectByPrimaryKey(priceId);
	}
	 public List selectAllProductFlatlyPrice() throws ServiceException{
		 return getMapper().selectAllProductFlatlyPrice();
	 }
	 public List<TtyPrice> selectPrice(Map<String, Object> map) throws ServiceException {
		return getMapper().selectPrice(map);
	}
	public List selectAllMin()throws ServiceException {
			return getMapper().selectAllMin();
	}
	public List selectAllMax()throws ServiceException {
		return getMapper().selectAllMax();
	}
	public TtyPrice selectMaxAndMin(String priceId)throws ServiceException {
		return getMapper().selectMaxAndMin(priceId);
	}
}
