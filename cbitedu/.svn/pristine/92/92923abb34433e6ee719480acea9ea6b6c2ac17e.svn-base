package com.creatorblue.cbitedu.system.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.creatorblue.cbitedu.core.baseclass.controller.HihBaseController;
import com.creatorblue.cbitedu.core.constants.Constant;
import com.creatorblue.cbitedu.core.exception.ControllerException;
import com.creatorblue.cbitedu.core.page.Page;
import com.creatorblue.cbitedu.core.utils.DateUtils;
import com.creatorblue.cbitedu.core.utils.Identities;
import com.creatorblue.cbitedu.core.utils.ReflectUtil;
import com.creatorblue.cbitedu.system.domain.TsysDataprivilege;
import com.creatorblue.cbitedu.system.domain.TsysModuleoperate;
import com.creatorblue.cbitedu.system.domain.TsysOrg;
import com.creatorblue.cbitedu.system.domain.TsysRole;
import com.creatorblue.cbitedu.system.domain.TsysRoleprivilege;
import com.creatorblue.cbitedu.system.domain.TsysUserinfo;
import com.creatorblue.cbitedu.system.domain.TuserRole;
import com.creatorblue.cbitedu.system.domain.ZtreeInfo;
import com.creatorblue.cbitedu.system.service.TsysDataprivilegeService;
import com.creatorblue.cbitedu.system.service.TsysModuleService;
import com.creatorblue.cbitedu.system.service.TsysModuleoperateService;
import com.creatorblue.cbitedu.system.service.TsysOrgService;
import com.creatorblue.cbitedu.system.service.TsysRoleService;
import com.creatorblue.cbitedu.system.service.TsysRoleprivilegeService;
import com.creatorblue.cbitedu.system.service.TsysUserinfoService;
import com.creatorblue.cbitedu.system.service.TuserRoleService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @ClassName: TsysRoleController
 * @Description: 角色管理
 * @author tbw
 * @date 2014-4-18 下午4:30:13
 */
@Controller
@RequestMapping("/tsysRoleController.do")
public class TsysRoleController extends HihBaseController {
	@Autowired
	private TsysRoleService tsysRoleService;
	@Autowired
	private TsysModuleService tsysModuleService;
	@Autowired
	private TsysRoleprivilegeService tsysRoleprivilegeService;
	@Autowired
	private TuserRoleService tuserRoleService;
	@Autowired
	private TsysUserinfoService tsysUserinfoService;
	@Autowired
	private TsysDataprivilegeService tsysDataprivilegeService;
	@Autowired
	private TsysOrgService tsysOrgService;
	@Autowired
	private TsysModuleoperateService tsysModuleoperateService;

	/**
	 * 访问列表
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(params = "method=list")
	public ModelAndView list(HttpServletRequest request,
			HttpServletResponse response) throws ControllerException {
		ModelAndView mv = new ModelAndView("/role/tsysrolelist");

		return mv;
	}

	@RequestMapping(params = "method=query")
	public void query(HttpServletRequest request, HttpServletResponse response)
			throws ControllerException, JsonProcessingException {
		Page page = (Page) this.getPage(request);
		page.setPagination(true);
		TsysRole sysRole = new TsysRole();
		String sortname = ReflectUtil.propertyToField(request
				.getParameter("sortname"));
		String sortorder = ReflectUtil.propertyToField(request
				.getParameter("sortorder"));
		this.setValue(request, sysRole);
		sysRole.setPage(page);
		sysRole.setSortname(sortname);
		sysRole.setSortorder(sortorder);
		List list = tsysRoleService.selectPageTsysRole(sysRole);
		this.convertParam(list, "roleType", "roleType");
		renderJson(list, page, response);
	}

	/**
	 * 進入新增頁面
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(params = "method=add")
	public ModelAndView add(HttpServletRequest request,
			HttpServletResponse response) throws ControllerException {
		ModelAndView mav = new ModelAndView("/role/tsysroleform");
		mav.addObject("DATA_SCOPE", Constant.DATA_SCOPE.values());

		TsysRole tsysRole = new TsysRole();
		tsysRole.setRoleType("2");
		mav.addObject("tsysRole", tsysRole);

		return mav;
	}

	/**
	 * 修改
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(params = "method=modify")
	public ModelAndView modify(HttpServletRequest request,
			HttpServletResponse response) throws ControllerException {
		ModelAndView mv = new ModelAndView("/role/tsysroleform");
		String id = request.getParameter("id");
		TsysRole tsysRole = (TsysRole) tsysRoleService.selectDetailById(id);
		mv.addObject("tsysRole", tsysRole);
		mv.addObject("flag","modify");
		mv.addObject("DATA_SCOPE", Constant.DATA_SCOPE.values());

		return mv;
	}

	/**
	 * 查看记录详细
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(params = "method=view")
	public ModelAndView view(HttpServletRequest request,
			HttpServletResponse response) throws ControllerException {
		ModelAndView mav = modify(request, response);
		TsysRole tsysRole = (TsysRole) mav.getModel().get("tsysRole");
		this.convertParam(tsysRole, "roleType", "roleType");
		this.convertParam(tsysRole, "dataScope@dataScopeName", "DATA_SCOPE");
		mav.setViewName("/role/tsysroleview");

		return mav;
	}

	/**
	 * 保存
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(params = "method=saveRole")
	public void saveRole(TsysRole tsysRole, HttpServletRequest request,
			HttpServletResponse response) throws ControllerException,
			JsonProcessingException {
		String[] moudle_ids = request.getParameterValues("module_id");
		String[] operate_ids = request.getParameterValues("operate_id");
		String[] org_ids = request.getParameterValues("org_id");

		if (StringUtils.isEmpty(tsysRole.getRoleId())) {
			TsysUserinfo userinfo = (TsysUserinfo) getSession(request,
					Constant.USER_INFO);
			tsysRole.setCreator(userinfo.getUserId());
			tsysRole.setCreateDate(DateUtils.getNowDateTime());

			TsysOrg org = ((List<TsysOrg>) getSession(request,
					Constant.USER_DEFAULT_ORG)).get(0);
			tsysRole.setOrgId(org.getOrgId());
		}

		tsysRoleService.insertOrUpdateRole(tsysRole, moudle_ids, org_ids,
				operate_ids);

		Map<String, Object> result = new HashMap<String, Object>(2);
		result.put("msg", "添加成功！");
		result.put("success", true);

		ObjectMapper objectMapper = new ObjectMapper();
		String jsonresult = objectMapper.writeValueAsString(result);
		renderJson(response, jsonresult.toString());
	}

	@RequestMapping(params = "method=del")
	public void del(HttpServletRequest request, HttpServletResponse response)
			throws ControllerException {
		String id = request.getParameter("id");
		TuserRole userRole = new TuserRole();
		userRole.setRoleId(id);

		List list = tuserRoleService.selectPageTuserRole(userRole);

		if (list.size() > 0) {
			renderText(response, "0");
		} else {
			tsysRoleService.delete(id);
			renderText(response, "1");
		}
	}

	/**
	 * 获取菜单树形列表
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws ControllerException
	 * @throws IOException
	 */
	@RequestMapping(params = "method=moduleTree")
	public void moduleTree(HttpServletRequest request,
			HttpServletResponse response) throws ControllerException,
			IOException {
		// 菜单信息
		TsysUserinfo userinfo = (TsysUserinfo) getSession(request,
				Constant.USER_INFO);
		String roleId = request.getParameter("roleId");
		List<Map> list = tsysModuleService.queryModuleTree(userinfo); // 登录用户有权限的菜单
		List<TsysModuleoperate> opList = tsysModuleoperateService
				.selectPageTsysModuleoperate(userinfo); // 登录用户有权限的菜单操作

		List<TsysRoleprivilege> rpList = Collections.emptyList();

		if (!StringUtils.isEmpty(roleId)) {
			TsysRoleprivilege roleprivilege = new TsysRoleprivilege();
			roleprivilege.setRoleId(roleId);
			rpList = (List<TsysRoleprivilege>) tsysRoleprivilegeService
					.selectPageTsysRoleprivilege(roleprivilege); // 角色已勾选的菜单和操作
		}

		List<Map> mouduleOplist = tsysModuleoperateService.genModuleOpTree(
				list, opList, rpList);// 生成菜单树

		ObjectMapper objectMapper = new ObjectMapper();
		String jsonresult = objectMapper.writeValueAsString(mouduleOplist);
		writeWeb(response, jsonresult);
	}

	/**
	 * 机构树
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(params = "method=orgList")
	public void queryOrgList(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		TsysDataprivilege dataprivilege = new TsysDataprivilege();
		String roleId = request.getParameter("roleId");
		List<TsysDataprivilege> dpList = Collections.emptyList();

		if (!StringUtils.isEmpty(roleId)) {
			dataprivilege.setRoleId(roleId);
			dpList = (List<TsysDataprivilege>) tsysDataprivilegeService
					.selectPageTsysDataprivilege(dataprivilege);
		}

		TsysOrg tsysOrg = new TsysOrg();
		tsysOrg.setState("0");

		List<ZtreeInfo> list = (List<ZtreeInfo>) tsysOrgService
				.selectSysOrgInfo(tsysOrg);

		for (ZtreeInfo zInfo : list) {
			final String orgId = zInfo.getId();

			for (TsysDataprivilege dp : dpList) {
				if (orgId.equals(dp.getOrgId())) {
					zInfo.setChecked("true");
				}
			}
		}

		ObjectMapper objectMapper = new ObjectMapper();
		this.writeWeb(response, objectMapper.writeValueAsString(list));
	}

	/**
	 * 设置角色用户
	 * 
	 * @param roleId
	 * @param request
	 * @param response
	 * @return
	 * @throws ControllerException
	 */
	@RequestMapping(params = "method=userRole")
	public ModelAndView userRole(String roleId, HttpServletRequest request,
			HttpServletResponse response) throws ControllerException {
		ModelAndView mav = new ModelAndView("/role/userrolemanage");
		TuserRole userRole = new TuserRole();
		userRole.setRoleId(roleId);

		List<TuserRole> urList = tuserRoleService.selectPageTuserRole(userRole);
		mav.addObject("urList", urList);
		mav.addAllObjects(copyMap(request));

		return mav;
	}

	/**
	 * 设置角色的用户
	 * 
	 * @param request
	 * @param response
	 * @throws ControllerException
	 *             ,JsonProcessingException
	 */
	@RequestMapping(params = "method=saveUserRole")
	public void saveUserRole(String roleId, String[] userId,
			HttpServletRequest request, HttpServletResponse response)
			throws ControllerException, JsonProcessingException {
		List<TuserRole> urList = new ArrayList<TuserRole>();

		if (userId != null) {
			for (String uid : userId) {
				TuserRole userRole = new TuserRole();
				userRole.setRoleId(roleId);
				userRole.setUserId(uid);
				userRole.setUserRoleid(Identities.uuid());
				urList.add(userRole);
			}
		}

		tuserRoleService.insertBatch(urList, roleId);

		Map<String, Object> result = new HashMap<String, Object>(2);
		result.put("msg", "添加成功！");
		result.put("success", true);

		ObjectMapper objectMapper = new ObjectMapper();
		String jsonresult = objectMapper.writeValueAsString(result);
		renderJson(response, jsonresult.toString());
	}
}
