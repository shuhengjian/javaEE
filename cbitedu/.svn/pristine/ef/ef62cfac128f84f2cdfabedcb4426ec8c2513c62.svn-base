package com.creatorblue.cbitedu.system.service;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.creatorblue.cbitedu.core.baseclass.service.BaseService;
import com.creatorblue.cbitedu.core.exception.ServiceException;
import com.creatorblue.cbitedu.system.persistence.TrolePostMapper;
/**
 * <p> Title: </p>
 * <p> Description:</p>
 * <p> Copyright: Copyright (c) 2014 </p>
 * <p> Company:hihsoft.co.,ltd </p>
 *
 * @author zhujw
 * @version 1.0
 */

@Service(value ="trolePostService")
public class TrolePostService<T> extends BaseService<T> {
	@Autowired
	private TrolePostMapper<T> mapper;
	//@Autowired允许业务逻辑层调用其他服务
	//private TsysUserService<TsysUser> tsysUserService;

	public TrolePostMapper<T> getMapper() {
		return mapper;
	}
	
	public List selectPageTrolePostByMap(Map map) throws ServiceException {
		return getMapper().selectPageTrolePostByMap(map);

	}

	public List selectPageTrolePost() throws ServiceException {
		return getMapper().selectPageTrolePost();
	}

}
