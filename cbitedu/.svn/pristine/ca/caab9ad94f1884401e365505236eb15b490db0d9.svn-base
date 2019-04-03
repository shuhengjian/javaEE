/**   
 * 功能描述：首页首页品牌
 * @Package: com.creatorblue.cbitedu.ty.back.service 
 * @author: shj 
 * @date: 2019年2月22日 上午10:34:42 
 */
package com.creatorblue.cbitedu.ty.front.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.creatorblue.cbitedu.core.baseclass.service.BaseService;
import com.creatorblue.cbitedu.core.exception.ServiceException;
import com.creatorblue.cbitedu.ty.domain.TtyType;
import com.creatorblue.cbitedu.ty.persistence.TtyBrandMapper;
import com.creatorblue.cbitedu.ty.persistence.TtyPriceMapper;
import com.creatorblue.cbitedu.ty.persistence.TtyTypeMapper;

/**
 * @ClassName: TtyAdvertisingService.java
 */
@Service(value = "ttyTypeService")
public class TtyTypeService<T> extends BaseService<T> {
	@Autowired
	private TtyTypeMapper<T> mapper;

	public TtyTypeMapper<T> getMapper() {
		return mapper;
	}
	public TtyType selectByPrimaryKey(String typeId) throws ServiceException{
		return getMapper().selectByPrimaryKey(typeId);
	}
}
