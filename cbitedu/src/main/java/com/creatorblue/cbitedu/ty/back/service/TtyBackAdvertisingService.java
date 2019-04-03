package com.creatorblue.cbitedu.ty.back.service;

import java.util.List;
import java.util.Map;

import org.apache.poi.ss.formula.functions.T;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.creatorblue.cbitedu.core.baseclass.service.BaseService;
import com.creatorblue.cbitedu.core.exception.ServiceException;
import com.creatorblue.cbitedu.ty.domain.TtyAdvertising;
import com.creatorblue.cbitedu.ty.domain.TtyBrand;
import com.creatorblue.cbitedu.ty.persistence.TsysAttachMapper;
import com.creatorblue.cbitedu.ty.persistence.TtyAdvertisingMapper;
@Service(value="ttyBackAdvertisingService")
public class TtyBackAdvertisingService extends BaseService<T> {
	@Autowired
	private TtyAdvertisingMapper<T> mapper;
	@Autowired 
	private TsysAttachMapper<T> tsysAttachMapper;
	public TtyAdvertisingMapper<T> getMapper() {
		return mapper;
	}
	public TsysAttachMapper<T> getTsysAttachMapper() {
		return tsysAttachMapper;
	}
	/**
	 * 查询所有，分页
	 * @param map
	 * @return
	 * @throws ServiceException
	 */
	public List<TtyAdvertising> selectPageTtyAdvertisingByMap(Map<String, Object> map) throws ServiceException{
		return getMapper().selectPageTtyAdvertisingByMap(map);
	}
	/**
	 * 通过id查询轮播
	 * @param brandId
	 * @return
	 * @throws ServiceException
	 */
	public TtyAdvertising selectByPrimaryKey(String advertisingId) throws ServiceException{
		return getMapper().selectByPrimaryKey(advertisingId);
	}
	/**
	 * 通过类查询是否存在
	 * @param ttyBrand
	 * @return
	 * @throws ServiceException
	 */
	public TtyAdvertising checkTheNameWithParentId(TtyAdvertising ttyAdvertising) throws ServiceException{
		return getMapper().checkTheNameWithParentId(ttyAdvertising);
	}
	/**
	 * 新增
	 * @param record
	 * @return
	 * @throws ServiceException
	 */
	public int insert(TtyAdvertising ttyAdvertising) throws ServiceException{
		getTsysAttachMapper().insert(ttyAdvertising.getTsysAttach());
		return getMapper().insert(ttyAdvertising);
	}
	/**
	 * 修改
	 * @param record
	 * @return
	 * @throws ServiceException
	 */
	public int updateByPrimaryKey(TtyAdvertising ttyAdvertising) throws ServiceException{
		return getMapper().updateByPrimaryKey(ttyAdvertising);
	}
	/**
	 * 删除
	 * @param brandId
	 * @return
	 * @throws ServiceException
	 */
	public int deleteByPrimaryKey(String advertisingId) throws ServiceException{
		getTsysAttachMapper().deleteByPrimaryKey(advertisingId);
		return getMapper().deleteByPrimaryKey(advertisingId);
	}
	/**
	 * 更新状态
	 * @param record
	 * @return
	 * @throws ServiceException
	 */
	public int updateBrandStateByPrimaryKey(TtyAdvertising record) throws ServiceException{
		return getMapper().updateBrandStateByPrimaryKey(record);
	}
}
