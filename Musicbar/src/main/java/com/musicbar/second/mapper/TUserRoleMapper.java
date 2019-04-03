package com.musicbar.second.mapper;

import java.util.List;

import com.musicbar.second.domain.TUserRole;

public interface TUserRoleMapper {
	/**
	 * 根据用户id删除用户分配的角色
	 * @param userRoleId
	 * @return
	 */
    int deleteByPrimaryKey(String userId);
    /**
     * 为用户分配角色
     * @param record
     * @return
     */
    int insert(TUserRole record);
    /**
     * 根据用户id查询分配的角色
     * @param userRoleId
     * @return
     */
    List<TUserRole> selectByPrimaryKey(String userId);
    /**
     * 根据角色id查询
     * @param roleId
     * @return
     */
    List<TUserRole> selectByRoleId(String roleId);
    
}