package com.creatorblue.cbitedu.system.service;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.creatorblue.cbitedu.core.baseclass.service.BaseService;
import com.creatorblue.cbitedu.core.exception.ServiceException;
import com.creatorblue.cbitedu.system.domain.TuserPost;
import com.creatorblue.cbitedu.system.persistence.TuserPostMapper;
/**
 * <p> Title: </p>
 * <p> Description:</p>
 * <p> Copyright: Copyright (c) 2014 </p>
 * <p> Company:hihsoft.co.,ltd </p>
 *
 * @author zhujw
 * @version 1.0
 */

@Service(value ="tuserPostService")
public class TuserPostService<T> extends BaseService<T> {
	@Autowired
	private TuserPostMapper<T> mapper;
	//@Autowired允许业务逻辑层调用其他服务
	//private TsysUserService<TsysUser> tsysUserService;

	public TuserPostMapper<T> getMapper() {
		return mapper;
	}
	
	public List selectPageTuserPostByMap(Map map) throws ServiceException {
		return getMapper().selectPageTuserPostByMap(map);

	}

	public List selectPageTuserPost() throws ServiceException {
		return getMapper().selectPageTuserPost();
	}
	
	public List<TuserPost> selectDetailByUserId(TuserPost userPost) throws ServiceException {
		return getMapper().selectDetailByUserId(userPost);
	}
	
	public void updateUserPost(TuserPost userPost) throws ServiceException {
		getMapper().updateUserPost(userPost);
	}

}
