package com.musicbar.second.mapper;

import java.util.List;
import java.util.Map;

import com.musicbar.second.domain.TParameter;
import com.musicbar.second.domain.TUser;

import io.lettuce.core.dynamic.annotation.Param;

public interface TUserMapper {
    int deleteByPrimaryKey(String userId);

    int insert(TUser user);

    int insertSelective(TUser record);

    TUser selectByPrimaryKey(String userId);

    int updateByPrimaryKeySelective(TUser record);

    int updateByPrimaryKey(TUser user);

    /**
     * 条件查询所有用户
     * @param user 
     * @return
     */
    List<TUser> selectAll(TUser user);
	
    /**
     * 查询用户状态
     * @return
     */
	//List<TUser> selectState();
	
	 /**
     * 查询所有用户
     * @return
     */
	List<TUser> selectAllUser();

	TUser selectMobilePwd(TUser user);

	TUser selectMobile(String userMobile);

	int deleteAll(List<String> userId);
	
	int selectUserMobileAndIdcard(TUser user);

	int changeState(Map<String, Object> map);
	
	List<TParameter> selectState();
	
	/**
	 * 根据手机号 密码查询
	 * @param user
	 * @return
	 */
	TUser selectByNamePwd(TUser user);//查询name pwd
}