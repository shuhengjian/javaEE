package com.creatorblue.cbitedu.system.service;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.creatorblue.cbitedu.core.baseclass.service.BaseService;
import com.creatorblue.cbitedu.core.exception.ServiceException;
import com.creatorblue.cbitedu.system.persistence.TorgUserMapper;
/**
 * <p> Title: </p>
 * <p> Description:</p>
 * <p> Copyright: Copyright (c) 2014 </p>
 * <p> Company:hihsoft.co.,ltd </p>
 *
 * @author zhujw
 * @version 1.0
 */

@Service(value ="torgUserService")
public class TorgUserService<T> extends BaseService<T> {
	@Autowired
	private TorgUserMapper<T> mapper;
	//@Autowired允许业务逻辑层调用其他服务
	//private TsysUserService<TsysUser> tsysUserService;

	public TorgUserMapper<T> getMapper() {
		return mapper;
	}
	
	public List selectPageTorgUserByMap(Map map) throws ServiceException {
		return getMapper().selectPageTorgUserByMap(map);

	}

	public List selectPageTorgUser() throws ServiceException {
		return getMapper().selectPageTorgUser();
	}

}
