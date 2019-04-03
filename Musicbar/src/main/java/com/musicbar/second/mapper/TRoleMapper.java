package com.musicbar.second.mapper;

import java.util.List;

import com.musicbar.second.domain.TRole;
/**
 * 角色管理
 * @author Administrator
 *
 */
public interface TRoleMapper {
	
	/**
	 * 条件查询
	 * @return
	 */
	List<TRole> selectAllRole(TRole role);
	
	/**
	 * 删除角色
	 * @param roleId
	 * @return
	 */
    int deleteByPrimaryKey(String roleId);
    /**
     * 新增角色
     * @param record
     * @return
     */
    int insert(TRole record);
    /**
     * 根据id查询角色
     * @param roleId
     * @return
     */
    TRole selectByPrimaryKey(String roleId);
    /**
     * 修改角色
     * @param record
     * @return
     */
    int updateByPrimaryKey(TRole record);
    /**
     * 根据角色名查询角色
     * @param roleName
     * @return
     */
	TRole selectRoleByName(String roleName);

	
	List<TRole> selectRole(TRole role);

	
}