package com.creatorblue.cbitedu.system.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.ehcache.CacheManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.creatorblue.cbitedu.core.baseclass.service.BaseService;
import com.creatorblue.cbitedu.core.constants.Constant.DATA_SCOPE;
import com.creatorblue.cbitedu.core.exception.ServiceException;
import com.creatorblue.cbitedu.core.interceptor.AConfig;
import com.creatorblue.cbitedu.core.utils.DateUtils;
import com.creatorblue.cbitedu.core.utils.Identities;
import com.creatorblue.cbitedu.core.utils.SpringContextHolder;
import com.creatorblue.cbitedu.system.domain.TsysDataprivilege;
import com.creatorblue.cbitedu.system.domain.TsysRole;
import com.creatorblue.cbitedu.system.domain.TsysRoleprivilege;
import com.creatorblue.cbitedu.system.domain.TsysUserinfo;
import com.creatorblue.cbitedu.system.persistence.TsysDataprivilegeMapper;
import com.creatorblue.cbitedu.system.persistence.TsysRoleMapper;
import com.creatorblue.cbitedu.system.persistence.TsysRoleprivilegeMapper;

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
@Service(value = "tsysRoleService")
public class TsysRoleService extends BaseService<TsysRole> {
	@Autowired
	private TsysRoleMapper<TsysRole> mapper;
	@Autowired
	private TsysRoleprivilegeMapper<TsysRoleprivilege> roleprivilegeMapper;
	@Autowired
	private TsysDataprivilegeMapper dataprivilegeMapper;

	public TsysRoleMapper<TsysRole> getMapper() {
		return mapper;
	}

	/**
	 * 通过用户查询用户已分配的角色信息
	 * 
	 * @param userinfo
	 * @return
	 * @throws ServiceException
	 */
	public List selectTsysRoleByUser(TsysUserinfo userinfo)
			throws ServiceException {
		Map map = new HashMap();
		map.put("userId", userinfo.getUserId());

		return getMapper().selectPageTsysRoleByMap(map);
	}

	public List selectPageTsysRoleByMap(Map map) throws ServiceException {
		return getMapper().selectPageTsysRoleByMap(map);
	}

	public List selectRoleInfoByMap(Map map) throws ServiceException {
		return getMapper().selectRoleInfoByMap(map);
	}

	public List selectPageTsysRole(TsysRole sysRole) throws ServiceException {
		AConfig aConfig = new AConfig("r.CREATOR", "ORG_ID");
		aConfig.setDataScope(DATA_SCOPE.SELF);
		sysRole.addAConfigs(aConfig);

		return getMapper().selectPageTsysRole(sysRole);
	}

	/**
	 * 角色下分配菜单及菜单按钮权限及数据权限
	 * 
	 * @param tsysRole
	 * @param module_ids
	 * @param org_ids
	 * @param operate_ids
	 */
	public void insertOrUpdateRole(TsysRole tsysRole, String[] module_ids,
			String[] org_ids, String[] operate_ids) {
		if ((tsysRole.getRoleId() == null) || ("").equals(tsysRole.getRoleId())) { // 新增
			tsysRole.setRoleId(Identities.uuid());
			tsysRole.setCreateDate(DateUtils.getNowDateTime());
			insert(tsysRole);
		} else {
			TsysRoleprivilege roleprivilege = new TsysRoleprivilege();
			roleprivilege.setRoleId(tsysRole.getRoleId());
			update(tsysRole);

			roleprivilegeMapper.deleteSelective(roleprivilege);

			TsysDataprivilege dataprivilege = new TsysDataprivilege();
			dataprivilege.setRoleId(tsysRole.getRoleId());
			dataprivilegeMapper.deleteSelective(dataprivilege);
		}

		if ((module_ids != null) && (module_ids.length > 0)) {
			for (String mid : module_ids) { // 保存菜单权限

				TsysRoleprivilege roleprivilege = new TsysRoleprivilege();
				roleprivilege.setModuleId(mid);
				roleprivilege.setPrivilegeId(Identities.uuid());
				roleprivilege.setRoleId(tsysRole.getRoleId());
				roleprivilegeMapper.insert(roleprivilege);
			}
		}

		if ((operate_ids != null) && (operate_ids.length > 0)) {
			for (String oid : operate_ids) { // 保存按钮权限

				TsysRoleprivilege roleprivilege = new TsysRoleprivilege();
				roleprivilege.setOperateId(oid);
				roleprivilege.setPrivilegeId(Identities.uuid());
				roleprivilege.setRoleId(tsysRole.getRoleId());
				roleprivilegeMapper.insert(roleprivilege);
			}
		}

		if ((org_ids != null) && (org_ids.length > 0)) {
			for (String oid : org_ids) { // 保存数据权限

				TsysDataprivilege dataprivilege = new TsysDataprivilege();
				dataprivilege.setOrgId(oid);
				dataprivilege.setRoleId(tsysRole.getRoleId());
				dataprivilege.setRoleOrgid(Identities.uuid());
				dataprivilege.setUserId(null);
				dataprivilegeMapper.insert(dataprivilege);
			}
		}

		CacheManager cacheManager = SpringContextHolder
				.getBean(CacheManager.class);
		cacheManager.clearAll();
	}
}
