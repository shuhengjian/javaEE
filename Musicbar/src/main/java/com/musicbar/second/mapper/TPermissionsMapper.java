package com.musicbar.second.mapper;

import java.util.List;

import com.musicbar.second.domain.TPermissions;

public interface TPermissionsMapper {
	
	/**
	 * 删除角色权限
	 * @param roleId
	 * @return
	 */
    int deleteByPrimaryKey(String roleId);
    
    /**
     * 为角色分配权限
     * @param record
     * @return
     */
    int insert(List<TPermissions> list);
    /**
     * 根据角色id查看分配的权限
     * @param roleId
     * @return
     */
    List<TPermissions> selectByPrimaryKey(String roleId);

}