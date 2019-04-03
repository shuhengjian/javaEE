package com.creatorblue.cbitedu.system.service;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.creatorblue.cbitedu.core.baseclass.service.BaseService;
import com.creatorblue.cbitedu.core.exception.ServiceException;
import com.creatorblue.cbitedu.system.persistence.TsysUserprivilegeMapper;
/**
 * <p> Title: </p>
 * <p> Description:</p>
 * <p> Copyright: Copyright (c) 2014 </p>
 * <p> Company:hihsoft.co.,ltd </p>
 *
 * @author zhujw
 * @version 1.0
 */

@Service(value ="tsysUserprivilegeService")
public class TsysUserprivilegeService<T> extends BaseService<T> {
	@Autowired
	private TsysUserprivilegeMapper<T> mapper;
	//@Autowired允许业务逻辑层调用其他服务
	//private TsysUserService<TsysUser> tsysUserService;

	public TsysUserprivilegeMapper<T> getMapper() {
		return mapper;
	}
	
	public List selectPageTsysUserprivilegeByMap(Map map) throws ServiceException {
		return getMapper().selectPageTsysUserprivilegeByMap(map);

	}

	public List selectPageTsysUserprivilege() throws ServiceException {
		return getMapper().selectPageTsysUserprivilege();
	}

}
