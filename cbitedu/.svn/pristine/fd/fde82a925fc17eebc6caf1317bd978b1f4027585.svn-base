package com.creatorblue.cbitedu.system.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.creatorblue.cbitedu.core.baseclass.service.BaseService;
import com.creatorblue.cbitedu.core.constants.Constant;
import com.creatorblue.cbitedu.core.exception.ServiceException;
import com.creatorblue.cbitedu.core.interceptor.AConfig;
import com.creatorblue.cbitedu.core.utils.Eryptogram;
import com.creatorblue.cbitedu.core.utils.Identities;
import com.creatorblue.cbitedu.system.domain.TsysPostTreeinfo;
import com.creatorblue.cbitedu.system.domain.TsysUserinfo;
import com.creatorblue.cbitedu.system.domain.TuserPost;
import com.creatorblue.cbitedu.system.persistence.TsysUserinfoMapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

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
@Service(value = "tsysUserinfoService")
public class TsysUserinfoService<T> extends BaseService<T> {
	@Autowired
	private TsysUserinfoMapper<T> mapper;

	// @Autowired允许业务逻辑层调用其他服务
	// private TsysUserService<TsysUser> tsysUserService;
	public TsysUserinfoMapper<T> getMapper() {
		return mapper;
	}

	public List selectPageTsysUserinfoByMap(Map map) throws ServiceException {
		AConfig aConfig = new AConfig("CREATOR", "ORG_ID");
		map.put("aConfig", aConfig);
		return getMapper().selectPageTsysUserinfoByMap(map);
	}

	public List<String> selectChildByOrgId(Map<String, Object> map)
			throws ServiceException {
		/*
		 * AConfig aConfig = new AConfig("CREATOR", "ORG_ID");
		 * map.put("aConfig",aConfig);
		 */
		return getMapper().selectChildByOrgId(map);
	}

	public void saveUserPost(TuserPost tuserPost) throws ServiceException {
		getMapper().insertUserPost(tuserPost);
	}

	public void insertUserPost(TsysUserinfo tsysUserinfo)
			throws ServiceException {
		String userPost = tsysUserinfo.getUserpost();

		if ((userPost != null) && !"".equals(userPost)) {
			String[] userPosts = userPost.split(",");

			for (int i = 0; i < userPosts.length; i++) {
				TuserPost tuserPost = new TuserPost();
				tuserPost.setUserJobid(Identities.uuid());
				tuserPost.setUserId(tsysUserinfo.getUserId());
				tuserPost.setPostId(userPosts[i]);
				getMapper().insertUserPost(tuserPost);
			}
		}
	}

	public List selectPageTsysUserinfo(TsysUserinfo userinfo)
			throws ServiceException {
		return getMapper().selectPageTsysUserinfo(userinfo);
	}

	/**
	 * 查询机构数
	 * 
	 * @return
	 * @throws ServiceException
	 */
	public List selectSysOrgInfo() throws ServiceException {
		return getMapper().selectSysOrgInfo();
	}

	/**
	 * 查询角色树
	 * 
	 * @return
	 * @throws ServiceException
	 */
	public List selectRoleInfo() throws ServiceException {
		return getMapper().selectRoleInfo();
	}

	/**
	 * 查询岗位数
	 * 
	 * @return
	 * @throws ServiceException
	 */
	public String selectSysPostInfo(Map<String, String> map)
			throws ServiceException {
		List<TsysPostTreeinfo> list = (ArrayList<TsysPostTreeinfo>) getMapper()
				.selectSysPostInfo(map);

		for (int i = 0; i < list.size(); i++) {
			TsysPostTreeinfo bean = list.get(i);

			if (bean.getCount() == 0) {
				list.get(i).setState("open");
			}
		}

		String jsonStr = "";
		try {
			jsonStr = new ObjectMapper().writeValueAsString(list);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return jsonStr;
	}

	/**
	 * 查询当前用户是否存在
	 * 
	 * @return
	 * @throws ServiceException
	 */
	public TsysUserinfo selectExitsUserByUsername(String loginName)
			throws ServiceException {
		Map map = new HashMap();
		map.put("loginName", loginName);
		// map.put("state", 1);
		List<TsysUserinfo> list = getMapper().selectExitsUserByUsername(map);

		if ((list != null) && (list.size() > 0)) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * 重置用户密码
	 * 
	 * @param userId
	 */
	public void resetPasswordByuserId(String userId) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userId", userId);
		map.put("password", Eryptogram.getUserPasswd(Constant.DEFAULT_PASSWORD));
		System.out.println(Constant.DEFAULT_PASSWORD);
		System.out.println(Eryptogram.getUserPasswd(Constant.DEFAULT_PASSWORD));
		getMapper().resetPassword(map);
	}

	/**
	 * 修改用户状态
	 * 
	 * @param userId
	 * @param type
	 */
	public void changeUserState(String userId, String type) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("state", type);
		map.put("userId", userId);
		getMapper().changeUserState(map);
	}

	public Integer queryUserCountByOrgId(String orgId) {
		return getMapper().queryUserCountByOrgId(orgId);
	}

	public List selectUserinfoByOrgId(String orgId) {
		return getMapper().selectUserinfoByOrgId(orgId);
	}

	/**
	 * 根据机构ID获取用户SortNum值
	 * 
	 * @param orgId
	 * @return
	 */
	public long getSortNumByOrgId(String orgId) {
		long result = 1;
		Map<String, Object> map = getMapper().getSortNumByOrgId(orgId);
		if (map == null) {
			return result;
		} else {
			BigDecimal sort = (BigDecimal) map.get("SORTNUM");
			if (sort != null) {
				Long sort1 = sort.longValue();
				final AtomicLong long1 = new AtomicLong(sort1);
				result = long1.incrementAndGet();
			} else {
				BigDecimal uCount1 = (BigDecimal) map.get("USERCOUNT");
				if (uCount1 != null) {
					Long count = uCount1.longValue();
					final AtomicLong long1 = new AtomicLong(count);
					result = long1.incrementAndGet();
				} else {
					return result;
				}

			}
		}
		return result;
	}

	public void updateBySort(TsysUserinfo tsysuser) {
		getMapper().updateBySort(tsysuser);
	}
}
