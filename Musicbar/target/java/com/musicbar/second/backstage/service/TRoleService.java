package com.musicbar.second.backstage.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.druid.sql.visitor.functions.Insert;
import com.musicbar.second.domain.TMenu;
import com.musicbar.second.domain.TOperation;
import com.musicbar.second.domain.TPermissions;
import com.musicbar.second.domain.TRole;
import com.musicbar.second.domain.TUser;
import com.musicbar.second.domain.TUserRole;
import com.musicbar.second.mapper.TMenuMapper;
import com.musicbar.second.mapper.TOperationMapper;
import com.musicbar.second.mapper.TPermissionsMapper;
import com.musicbar.second.mapper.TRoleMapper;
import com.musicbar.second.mapper.TUserMapper;
import com.musicbar.second.mapper.TUserRoleMapper;

/**
 * 角色管理
 * @author Administrator
 *
 */

@Service
public class TRoleService {
	@Autowired
	private TUserMapper tUserMapper;
	@Autowired
	private TMenuMapper tMenuMapper;
	@Autowired
	private TOperationMapper tOperationMapper;
	@Autowired
	private TRoleMapper tRoleMapper;
	@Autowired
	private TUserRoleMapper tUserRoleMapper;
	@Autowired
	private TPermissionsMapper tPermissionsMapper;
	
	/**
	 * 角色新增
	 * @param record
	 * @return
	 */
	public int insert(TRole record) {
		return tRoleMapper.insert(record);
	}
	/**
	 * 角色删除
	 * @param roleId
	 * @return
	 */
	public int deleteRole(String roleId) {
		return tRoleMapper.deleteByPrimaryKey(roleId);
	}
	/**
	 * 条件查询
	 * @return
	 */
	public List<TRole> selectAllRole(TRole role){
		return tRoleMapper.selectAllRole(role);
	}
	/**
	 * 根据角色id查询角色
	 * @param roleId
	 * @return
	 */
	public TRole selectByPrimaryKey(String roleId) {
		return tRoleMapper.selectByPrimaryKey(roleId);
	}
	
	/**
	 * 查询菜单
	 * @return
	 */
	public List<TMenu> selectMenu() {
		// TODO Auto-generated method stub
		return tMenuMapper.selectAll();
	}
	/**
	 * 根据菜单id查询操作
	 * @return
	 */
	public List<TOperation> selectOperation(String menuId){
		return tOperationMapper.selectByMenuId(menuId);
		
	}
	/**
	 * 根据角色id查询权限
	 * @param roleId
	 * @return
	 */
	public List<TPermissions> selectPermissions(String roleId) {
		// TODO Auto-generated method stub
		return tPermissionsMapper.selectByPrimaryKey(roleId);
	}
	/**
	 * 新增角色权限
	 * @param record
	 * @return
	 */
	public int save(List<TPermissions> list) {
		return tPermissionsMapper.insert(list);
	}
	/**
	 * 删除角色权限
	 * @param roleId
	 * @return
	 */
	public int delete(String roleId) {
		return tPermissionsMapper.deleteByPrimaryKey(roleId);
	}
	/**
	 * 查询所有用户
	 * @return
	 */
	public List<TUser> selectUser(){
		return tUserMapper.selectAllUser();
	}
	/**
	 * 根据用户id查询用户
	 * @param userId
	 * @return
	 */
	public TUser selectUserByUserId(String userId) {
		return tUserMapper.selectByPrimaryKey(userId);
	}
	/**
	 * 根据用户id查角色
	 * @param userId
	 * @return
	 */
	public List<TUserRole> selectRoleByUser(String userId){
		return tUserRoleMapper.selectByPrimaryKey(userId);
	}
	/**
	 * 为用户分配角色
	 * @param record
	 * @return
	 */
	public int insertUserRole(TUserRole record) {
		return tUserRoleMapper.insert(record);
	}
	/**
	 * 根据用户id删除用户分配的角色
	 * @param userId
	 * @return
	 */
	public int deleteUserRole(String userId) {
		return tUserRoleMapper.deleteByPrimaryKey(userId);
	}
	/**
	 * 根据菜单id查询菜单名
	 * @param string
	 * @return
	 */
	public String selectMenuNameById(String id) {
		// TODO Auto-generated method stub
		return tMenuMapper.selectName(id);
	}
	/**
	 * 根据操作id查找操作名
	 * @param string
	 * @return
	 */
	public TOperation selectOperationById(String id) {
		// TODO Auto-generated method stub
		return tOperationMapper.selectOperationById(id);
	}
	/**
	 * 根据角色id查询用户角色
	 * @param roleId
	 * @return
	 */
	public List<TUserRole> selectByRoleId(String roleId){
		return tUserRoleMapper.selectByRoleId(roleId);
	}
	/**
	 * 根据角色名查询角色
	 * @param roleName
	 * @return
	 */
	public TRole selectRoleByName(String roleName) {
		// TODO Auto-generated method stub
		return tRoleMapper.selectRoleByName(roleName);
	}
	/**
	 * 修改角色
	 * @param tRole
	 * @return
	 */
	public int updateRole (TRole tRole) {
		return tRoleMapper.updateByPrimaryKey(tRole);
	}
	
	
	public List<TRole> selectRole(TRole role){
		return tRoleMapper.selectRole(role);
	}

}
