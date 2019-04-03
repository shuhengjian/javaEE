package com.creatorblue.cbitedu.system.service;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.creatorblue.cbitedu.core.baseclass.service.BaseService;
import com.creatorblue.cbitedu.core.exception.ServiceException;
import com.creatorblue.cbitedu.system.persistence.TsysCustomvaluesMapper;
/**
 * <p> Title: </p>
 * <p> Description:</p>
 * <p> Copyright: Copyright (c) 2014 </p>
 * <p> Company:hihsoft.co.,ltd </p>
 *
 * @author zhujw
 * @version 1.0
 */

@Service(value ="tsysCustomvaluesService")
public class TsysCustomvaluesService<T> extends BaseService<T> {
	@Autowired
	private TsysCustomvaluesMapper<T> mapper;
	//@Autowired允许业务逻辑层调用其他服务
	//private TsysUserService<TsysUser> tsysUserService;

	public TsysCustomvaluesMapper<T> getMapper() {
		return mapper;
	}
	
	public List selectPageTsysCustomvaluesByMap(Map map) throws ServiceException {
		return getMapper().selectPageTsysCustomvaluesByMap(map);

	}

	public List selectPageTsysCustomvalues() throws ServiceException {
		return getMapper().selectPageTsysCustomvalues();
	}

}
