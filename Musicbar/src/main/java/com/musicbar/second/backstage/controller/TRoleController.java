package com.musicbar.second.backstage.controller;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;


import javax.servlet.http.HttpSession;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.musicbar.core.annotation.LoggerAnnotation;
import com.musicbar.core.redis.RedisUtils;
import com.musicbar.core.utils.DateUtils;
import com.musicbar.core.utils.StringUtil;
import com.musicbar.second.backstage.service.TRoleService;
import com.musicbar.second.comm.config.ConfigProperties;
import com.musicbar.second.comm.param.ParamUtil;
import com.musicbar.second.domain.TMenu;
import com.musicbar.second.domain.TOperation;
import com.musicbar.second.domain.TParameter;
import com.musicbar.second.domain.TPermissions;
import com.musicbar.second.domain.TRole;
import com.musicbar.second.domain.TUser;
import com.musicbar.second.domain.TUserRole;

import net.sf.json.JSONArray;

/**
 * 角色管理
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/backstage")
public class TRoleController {
	@Autowired
	private TRoleService tRoleService;
	@Autowired
	private ConfigProperties configProperties;
	@Autowired
	private RedisUtils redisUtils;//缓存
	@Autowired
	private ParamUtil paramUtil;
	/**
	 * 访问角色管理
	 * @return
	 */
	@RequestMapping("/role_querylist")
	@LoggerAnnotation(begin="查询角色列表方法开始",end="查询角色列表方法结束")
	public ModelAndView queryRoleAll() {
		ModelAndView mv = new ModelAndView("backstage/pages/role/list");
		int pageSize = configProperties.getPageSize();//每页显示数量
		int pageNum = 1;//当前页
		PageHelper.startPage(pageNum,pageSize);
		List<TRole> list = tRoleService.selectAllRole(null);
		PageInfo<TRole> page = new PageInfo<>(list);
		List<TParameter> paralist = paramUtil.getListByKey("role_state");//缓存中取状态值
		mv.addObject("paralist",paralist);
		mv.addObject("page", page);
		return mv;
	}
	/**
	 * 根据角色id查询角色分配的用户
	 * @param roleId
	 * @return
	 */
	@RequestMapping("/role_queryuserlist")
	@ResponseBody
	@LoggerAnnotation(begin="查询角色方法开始",end="查询角色列表方法结束")
	public String queryUser(String roleId) {
		JSONObject json = new JSONObject();
		List<TUserRole> list = tRoleService.selectByRoleId(roleId);
		json.put("list", list);
		return json.toString();
	}
	/**
	 * 条件查询
	 * @return
	 */
	@RequestMapping("/role_query")
	@ResponseBody
	@LoggerAnnotation(begin="条件查询角色方法开始",end="条件查询角色方法结束")
	public String queryRole(String roleName,String roleState, Integer pageNum) {
		TRole role = new  TRole();
		role.setRoleName(roleName);
		role.setRoleState(roleState);
		List<TParameter> paralist = paramUtil.getListByKey("role_state");//缓存中取状态值
		//分页
		int pageSize = configProperties.getPageSize();//每页显示数量
		PageHelper.startPage(pageNum,pageSize);
		List<TRole> list = tRoleService.selectAllRole(role);//条件查询
		PageInfo<TRole> page = new PageInfo<>(list);
		return JSONArray.fromObject(page).toString();
	}
	/**
	 * 角色编辑
	 * @return
	 */
	@RequestMapping("/role_edit")
	@LoggerAnnotation(begin="角色编辑方法开始",end="角色编辑方法结束")
	public ModelAndView editRole(String roleId) {

		ModelAndView mv = new ModelAndView("backstage/pages/role/edit");
		//判断新增还是修改
		if(roleId!=null||roleId!="") {
			List<TPermissions> list = tRoleService.selectPermissions(roleId);
			String msg = ""; 
			TRole role = tRoleService.selectByPrimaryKey(roleId);
			//判断需要修改的角色是否有用户使用
			if(tRoleService.selectByRoleId(roleId).size()!=0) {
				msg = "该角色有用户使用,是否继续修改?";
			}
			mv.addObject("msg",msg);
			mv.addObject("list",list); 
			mv.addObject("role",role);
		}
		//查询出菜单
		List<TMenu> menulist = tRoleService.selectMenu();
		//查询角色状态
		List<TParameter> paralist = paramUtil.getListByKey("role_state");//缓存中取状态值
		mv.addObject("paralist",paralist);
		mv.addObject("menulist", menulist);
		return mv;
	}
	/**
	 * 查询菜单的操作
	 * @param menuId
	 * @return
	 */
	@RequestMapping("/query_operation")
	@ResponseBody
	@LoggerAnnotation(begin="查询菜单的操作方法开始",end="查询菜单的操作方法结束")
	public String QueryOperation(String menuId) {
		JSONObject json = new JSONObject();
		//根据菜单id查询出操作名
		List<TOperation> operation = tRoleService.selectOperation(menuId);
		json.put("operation", operation);
		return json.toString();
	}
	/**
	 * 保存
	 * @param roleId
	 * @param roleName
	 * @param menuIds
	 * @param operationIds
	 * @param session
	 * @return
	 */
	@RequestMapping("/role_save")
	@ResponseBody
	@LoggerAnnotation(begin="保存角色方法开始",end="保存角色方法结束")
	public String save(String roleId,String roleName,String roleState,String[] menuIds,String[] operationIds,HttpSession session) {
		JSONObject json = new JSONObject();
		String msg = "";
		boolean flag = true;
		
		TRole tRole = new TRole();
		try {
			/*新增*/
			if(roleId ==null || roleId==""|| roleId.isEmpty()) {
				//判断角色名是否存在
				TRole role = tRoleService.selectRoleByName(roleName);
				if(role !=null) {
					msg = "角色已存在";
					flag = false;
					json.put("flag",flag);
					json.put("msg",msg);
					return json.toString();
				}
				
				tRole.setRoleId(StringUtil.getUUIDValue());//角色id
				tRole.setRoleName(roleName);//角色名
				tRole.setRoleState(roleState);//角色状态
				tRole.setCreateTime(DateUtils.fotmatDateTOyMdHms(new Date()));//角色创建时间
				tRole.setCreateUser("超级管理员");//角色创建人
				//tRole.setCreateUserId(session.getAttribute(userId));//角色创建人
			/*修改*/
			}else {
				//先删除角色权限
				tRoleService.delete(roleId);//删除角色权限
				tRole.setRoleId(roleId);//角色id
				tRole.setRoleName(roleName);//角色名
				tRole.setRoleState(roleState);//角色状态
				tRole.setUpdateUser("超级管理员");//修改人
				//tRole.setUpdateUserId(session.getAttribute(userId));//修改人
				tRole.setCreateTime(DateUtils.fotmatDateTOyMdHms(new Date()));//修改时间
				//判断角色名是否存在
				TRole role = tRoleService.selectRoleByName(roleName);
				if(!role.getRoleName().equals(roleName) && role !=null) {
					msg = "角色已存在";
					flag = false;
					json.put("flag",flag);
					json.put("msg",msg);
					return json.toString();
				}
			}
			/*添加角色的权限*/
			List<TPermissions> list = new ArrayList();
			if(menuIds!=null) {
				for(int i=0;i<menuIds.length;i++) {
					TPermissions tPermissions = new TPermissions();
					tPermissions.setPermissionsId(StringUtil.getUUIDValue());//权限id
					if(roleId.equals("")||roleId==null) {
						tPermissions.setRoleId(tRole.getRoleId());
					}else {
						tPermissions.setRoleId(roleId);
					}
					tPermissions.setRoleName(roleName);//角色名
					tPermissions.setMenuId(menuIds[i]);//菜单id
					String menuName = tRoleService.selectMenuNameById(menuIds[i]);//根据菜单id查菜单名
					tPermissions.setMenuName(menuName);//菜单名
					list.add(tPermissions);
				}
			}

			if(operationIds!=null) {
				for(int j=0;j<operationIds.length;j++) {
					TPermissions tPermissions = new TPermissions();
					tPermissions.setPermissionsId(StringUtil.getUUIDValue());//权限id
					if(roleId.equals("")||roleId ==null) {
						tPermissions.setRoleId(tRole.getRoleId());
					}else {
						tPermissions.setRoleId(roleId);
					}
					tPermissions.setRoleName(roleName);//角色名
					tPermissions.setOperationId(operationIds[j]);//操作id
					TOperation operation = tRoleService.selectOperationById(operationIds[j]);//根据操作id查操作名
					tPermissions.setOperationName(operation.getOperationName());//操作名
					tPermissions.setMenuId(operation.getMenuId());//菜单id
					tPermissions.setMenuName(operation.getMenuName());//菜单名
					list.add(tPermissions);
				}
			}
			if(list.size()>0) {
				tRoleService.save(list);
			}
			if(roleId ==null || roleId==""|| roleId.isEmpty()) {
				msg = "新增成功";
				tRoleService.insert(tRole);//角色新增
			}else {
				//修改角色
				msg = "修改成功";
				tRoleService.updateRole(tRole);//角色修改
			}
		} catch (Exception e) {
			// TODO: handle exception
			if(roleId ==null || roleId==""|| roleId.isEmpty()) {
				msg = "新增失败";
			}else {
				msg = "修改失败";
			}
			flag=false;
		}
		json.put("flag",flag);
		json.put("msg",msg);
		return json.toString();
	}
	@RequestMapping("/role_delete")
	@ResponseBody
	@LoggerAnnotation(begin="删除角色方法开始",end="删除角色方法结束")
	public String delete(String roleId) {
		JSONObject json = new JSONObject();
		String msg = "删除成功";
		try {
			//判断角色是否有用户使用
			if(tRoleService.selectByRoleId(roleId).size()!=0) {
				msg = "角色有用户使用";
				json.put("msg",msg);
				return json.toString();
			}
			//删除角色权限
			tRoleService.delete(roleId);
			//删除角色
			tRoleService.deleteRole(roleId);
			
		} catch (Exception e) {
			// TODO: handle exception
			msg = "删除失败";
		}
		json.put("msg",msg);
		return json.toString();
	}
	
	/**
	 * 修改角色状态
	 * @param roleState
	 * @return
	 */
	@RequestMapping("/role_state")
	@ResponseBody
	@LoggerAnnotation(begin="修改角色状态方法开始",end="修改角色状态方法结束")
	public String roleState(String roleState,String roleId) {
		JSONObject json = new JSONObject();
		String msg = "";
		try {
			//查询角色是否有用户使用
			if(tRoleService.selectByRoleId(roleId).size()!=0) {
				msg = "角色有用户使用";
				json.put("msg",msg);
				return json.toString();
			}
			TRole tRole = new TRole();
			tRole.setRoleId(roleId);
			tRole.setRoleState(roleState);
			tRole.setUpdateUser("超级管理员");//修改人
			//tRole.setUpdateUserId(session.getAttribute(userId));//修改人
			tRole.setUpdateTime(DateUtils.fotmatDateTOyMdHms(new Date()));//修改时间
			tRoleService.updateRole(tRole);//角色修改
			//启用
			if(roleState.equals("1")) {
				msg="启用成功";
			}else {
			//停用	
				msg="停用成功";
			}
		}catch (Exception e) {
			//启用
			if(roleState=="1") {
				msg="启用失败";
			}else {
			//停用	
				msg="停用失败";
			}
		}
		json.put("msg",msg);
		return json.toString();
	}
	
	/**
	 * 进入角色分配页面
	 * @return
	 */
	@RequestMapping("/role_allotList")
	@ResponseBody
	@LoggerAnnotation(begin="用户角色分配方法开始",end="用户角色分配方法结束")
	public ModelAndView allotList() {
		ModelAndView mv = new ModelAndView("backstage/pages/role/parcel");
		//查询所有用户
		List<TUser> userlist = tRoleService.selectUser();
		//查询所有角色
		List<TRole> rolelist = tRoleService.selectAllRole(null);
		mv.addObject("userlist", userlist);
		mv.addObject("rolelist", rolelist);
		return mv;
	}
	/**
	 * 预览用户分配的角色
	 * @param userId
	 * @return
	 */
	@RequestMapping("/role_allot")
	@ResponseBody
	@LoggerAnnotation(begin="查看用户角色分配方法开始",end="查看用户角色分配方法结束")
	public String allotRole(String userId) {
		JSONObject json = new JSONObject();
		//查询用户分配的角色
		List<TUserRole> userRolelist = tRoleService.selectRoleByUser(userId);
		
		json.put("userRolelist", userRolelist);
		
		return json.toString();
	}
	
	/**
	 * 保存为用户分配的角色
	 * @param userId
	 * @return
	 */
	@RequestMapping("/role_allotSave")
	@ResponseBody
	@LoggerAnnotation(begin="保存用户角色分配方法开始",end="保存用户角色分配方法结束")
	public String allotSave(String userId,String[] roleIds) {
		JSONObject json = new JSONObject();
		boolean flag = true;
		String msg = "";
		List<TUserRole> userRolelist = tRoleService.selectRoleByUser(userId);
		//删除用户所分配的角色
		tRoleService.deleteUserRole(userId);
		//为用户分配角色
		try {
			for(int i=0;i<roleIds.length;i++) {
				
				TUserRole tUserRole =  new TUserRole();
				
				tUserRole.setRoleId(roleIds[i]);//角色id
				TRole role =  tRoleService.selectByPrimaryKey(roleIds[i]);//根据角色id查询角色
				tUserRole.setRoleName(role.getRoleName());//角色名
				
				tUserRole.setUserId(userId);//用户id
				TUser user = tRoleService.selectUserByUserId(userId);//根据用户id查询用户
				tUserRole.setUserName(user.getUserName());//用户名
				
				tUserRole.setUserRoleId(StringUtil.getUUIDValue());//id
				tRoleService.insertUserRole(tUserRole);//为用户分配角色
			}
		} catch (Exception e) {
			// TODO: handle exception
			flag = false;
			msg = "保存失败";
		}
		json.put("flag",flag);
		json.put("msg",msg);
		return json.toString();
	}

}
