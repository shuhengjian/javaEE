/**   
 * 功能描述：首页广告图
 * @Package: com.creatorblue.cbitedu.ty.back.service 
 * @author: shj 
 * @date: 2019年2月22日 上午10:34:42 
 */
package com.creatorblue.cbitedu.ty.front.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.creatorblue.cbitedu.core.baseclass.persistence.BaseSqlMapper;
import com.creatorblue.cbitedu.core.baseclass.service.BaseService;
import com.creatorblue.cbitedu.core.exception.ServiceException;
import com.creatorblue.cbitedu.system.persistence.TsysPostMapper;
import com.creatorblue.cbitedu.ty.domain.TsysAttach;
import com.creatorblue.cbitedu.ty.persistence.TtyAdvertisingMapper;

/**
 * @ClassName: TtyAdvertisingService.java
 * @Description: 该类的功能描述
 * @version: v1.0.0
 * @author: shj
 * @date: 2019年2月22日 上午10:34:42
 */
@Service(value = "ttyAdvertisingService")
public class TtyAdvertisingService<T> extends BaseService<T> {
	@Autowired
	private TtyAdvertisingMapper<T> mapper;

	public TtyAdvertisingMapper<T> getMapper() {
		return mapper;
	}
	public List<TsysAttach> selectDeficiency(Map<String, Object> map) {
	return getMapper().selectDeficiency(map);
	}	
}
