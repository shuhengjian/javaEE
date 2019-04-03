package com.creatorblue.cbitedu.ty.back.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.creatorblue.cbitedu.core.baseclass.service.BaseService;
import com.creatorblue.cbitedu.core.exception.ServiceException;
import com.creatorblue.cbitedu.ty.domain.TsysAttach;
import com.creatorblue.cbitedu.ty.domain.TtyProduct;
import com.creatorblue.cbitedu.ty.persistence.TtyProductMapper;

/**
 * 产品管理
 * @author MECHREV
 *
 * @param <T>
 */
@Service(value="ttyBackProductService")
public class TtyBackProductService<T> extends BaseService<T>  {
	
	@Autowired
	private TtyProductMapper<T> mapper;

	@Override
	public TtyProductMapper<T> getMapper() {
		// TODO Auto-generated method stub
		return mapper;
	}
	
	/**
	 * 多图上传
	 * @param map
	 * @return
	 */
	public int insertDetailPicture(Map<String,String> map) {
		return getMapper().insertDetailPicture(map);
	}
	public int updateDetailPicture(Map<String,String> map) {
		return getMapper().updateDetailPicture(map);
	}
	public int deletDetailPicture(Map<String, Object> map) {
		return getMapper().deletDetailPicture(map);
	}
	
	
	/**
	 * 查询
	 * @param map
	 * @return
	 * @throws ServiceException
	 */
	public List<TtyProduct> selectPageTtyProductByMap(Map<String, Object> map) throws ServiceException {
		return getMapper().selectPageTtyProductByMap(map);
	}
	
	public TtyProduct selectByPrimaryKey(String productId) throws ServiceException {
		return getMapper().selectByPrimaryKey(productId);
	}
	
	/**
	 * 新增
	 * @param record
	 * @return
	 * @throws ServiceException
	 */
	public int insert(TtyProduct record) throws ServiceException {
		return getMapper().insert(record);
	}
	
	/**
	 * 修改
	 * @param record
	 * @return
	 * @throws ServiceException
	 */
	public int updateByPrimaryKeySelective(TtyProduct record) throws ServiceException {
		return getMapper().updateByPrimaryKeySelective(record);
	}
	
	/**
	 * 删除
	 * @param productId
	 * @return
	 */
	public int deleteByPrimaryKey(String productId) throws ServiceException {
		return getMapper().deleteByPrimaryKey(productId);
	}
	

	public TtyProduct checkTheNameWithParentId(TtyProduct ttyProduct) throws ServiceException {
		return getMapper().checkTheNameWithParentId(ttyProduct);
	}

	/**
	 * 修改产品状态
	 * @param productId
	 * @param type
	 * @throws ServiceException
	 */
	public int updateProductStateByPrimaryKey(TtyProduct record) throws ServiceException {
		return getMapper().updateProductStateByPrimaryKey(record);
	}
}
