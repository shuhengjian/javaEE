package com.creatorblue.cbitedu.system.service;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.creatorblue.cbitedu.core.baseclass.service.BaseService;
import com.creatorblue.cbitedu.core.exception.ServiceException;
import com.creatorblue.cbitedu.core.utils.Identities;
import com.creatorblue.cbitedu.system.domain.TuserRole;
import com.creatorblue.cbitedu.system.persistence.TuserRoleMapper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * Title:
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Copyright: Copyright (c) 2014
 * </p>
 * <p>
 * Company:hihsoft.co.,ltd
 * </p>
 * 
 * @author zhujw
 * @version 1.0
 */
@Service(value = "tuserRoleService")
public class TuserRoleService extends BaseService<TuserRole> {
	@Autowired
	private TuserRoleMapper<TuserRole> mapper;

	public TuserRoleMapper<TuserRole> getMapper() {
		return mapper;
	}

	public List selectPageTuserRoleByMap(Map map) throws ServiceException {
		return getMapper().selectPageTuserRoleByMap(map);
	}

	public List selectPageTuserRole(TuserRole userRole) throws ServiceException {
		return getMapper().selectPageTuserRole(userRole);
	}

	public void delTuserRoleByUserId(String userId) {
		Map map = new HashMap();
		map.put("userId", userId);
		getMapper().deleteByMap(map);
	}

	public void insertBatch(List<TuserRole> list, String roleId) {
		Map map = new HashMap();
		map.put("roleId", roleId);
		getMapper().deleteByMap(map);

		for (TuserRole tuserRole : list) {
			insert(tuserRole);
		}
	}

	@Transactional(value = "transactionManager", rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
	public void insertUsers(String roleIDs, String userIDs) {
		if (!StringUtils.isEmpty(roleIDs)) {
			String[] roles = roleIDs.split(",");
			String[] users = userIDs.split(",");
			for (String userId : users) {
				List<TuserRole> urList = new ArrayList<TuserRole>();
				for (String roleId : roles) {
					TuserRole userRole= new TuserRole();
					userRole.setUserRoleid(Identities.uuid());
					userRole.setRoleId(roleId);
					userRole.setUserId(userId);
					urList.add(userRole);
				}
				Map map = new HashMap();
				map.put("userId", userId);
				getMapper().deleteByMap(map);
				for (TuserRole tuserRole : urList) {
					insert(tuserRole);
				}
			}
		}
	}
}
