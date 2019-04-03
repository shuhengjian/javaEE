package com.creatorblue.cbitedu.system.service;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.creatorblue.cbitedu.core.baseclass.service.BaseService;
import com.creatorblue.cbitedu.core.exception.ServiceException;
import com.creatorblue.cbitedu.system.persistence.TsysAppMapper;
/**
 * <p> Title: </p>
 * <p> Description:</p>
 * <p> Copyright: Copyright (c) 2014 </p>
 * <p> Company:hihsoft.co.,ltd </p>
 *
 * @author zhujw
 * @version 1.0
 */

@Service(value ="tsysAppService")
public class TsysAppService<T> extends BaseService<T> {
	@Autowired
	private TsysAppMapper<T> mapper;
	//@Autowired允许业务逻辑层调用其他服务
	//private TsysUserService<TsysUser> tsysUserService;

	public TsysAppMapper<T> getMapper() {
		return mapper;
	}
	
	public List selectPageTsysAppByMap(Map map) throws ServiceException {
		return getMapper().selectPageTsysAppByMap(map);

	}

	public List selectPageTsysApp() throws ServiceException {
		return getMapper().selectPageTsysApp();
	}
	
	public boolean checkModuleOfSysApp(String id){
		boolean flag = false;
		Integer count = mapper.checkModuleOfSysApp(id);
		if(count!=null&&count>0){
			flag = true;
			return flag;
		}
		return flag;
	}
	
}
