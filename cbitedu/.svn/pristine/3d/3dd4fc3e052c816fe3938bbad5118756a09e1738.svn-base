package com.creatorblue.cbitedu.system.controller;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.creatorblue.cbitedu.core.baseclass.controller.HihBaseController;
import com.creatorblue.cbitedu.core.constants.Constant;
import com.creatorblue.cbitedu.core.exception.ControllerException;
import com.creatorblue.cbitedu.core.page.Page;
import com.creatorblue.cbitedu.core.plugins.poi.excel.ExcelExportUtil;
import com.creatorblue.cbitedu.core.plugins.poi.excel.entity.ExportParams;
import com.creatorblue.cbitedu.core.utils.Eryptogram;
import com.creatorblue.cbitedu.core.utils.Identities;
import com.creatorblue.cbitedu.system.domain.TsysModuleoperate;
import com.creatorblue.cbitedu.system.domain.TsysOrg;
import com.creatorblue.cbitedu.system.domain.TsysPost;
import com.creatorblue.cbitedu.system.domain.TsysUserinfo;
import com.creatorblue.cbitedu.system.domain.TuserPost;
import com.creatorblue.cbitedu.system.domain.TuserRole;
import com.creatorblue.cbitedu.system.domain.ZtreeInfo;
import com.creatorblue.cbitedu.system.service.TsysModuleService;
import com.creatorblue.cbitedu.system.service.TsysModuleoperateService;
import com.creatorblue.cbitedu.system.service.TsysOrgService;
import com.creatorblue.cbitedu.system.service.TsysPostService;
import com.creatorblue.cbitedu.system.service.TsysRoleService;
import com.creatorblue.cbitedu.system.service.TsysUserinfoService;
import com.creatorblue.cbitedu.system.service.TuserPostService;
import com.creatorblue.cbitedu.system.service.TuserRoleService;
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
@Controller
@RequestMapping("/tsysUserinfoController.do")
public class TsysUserinfoController extends HihBaseController {
	@Autowired
	private TsysUserinfoService tsysUserinfoService;
	@Autowired
	private TuserRoleService tuserRoleService;
	@Autowired
	private TsysOrgService tsysOrgService;
	@Autowired
	private TsysPostService tsysPostService;
	@Autowired
	private TuserPostService tuserPostService;
	@Autowired
    private TsysRoleService tsysRoleService;

	@Autowired
	TsysModuleoperateService moduleoperateService;
	@Autowired
	private TsysModuleService tsysModuleService;
	@Autowired
	private TsysModuleoperateService tsysModuleoperateService;
	
	/**
	 * 进入页面
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(params = "method=list")
	public ModelAndView list(HttpServletRequest request,
			HttpServletResponse response) throws ControllerException {
		ModelAndView mv = new ModelAndView("/user/userinfoindex");

		return mv;
	}

	/**
	 * 访问列表
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(params = "method=query")
	public void query(HttpServletRequest request, HttpServletResponse response)
			throws ControllerException {
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("loginName", request.getParameter("login_name"));
		result.put("user_realname", request.getParameter("user_realname"));
		result.put("mobiletel", request.getParameter("mobiletel"));
		result.put("sex", request.getParameter("sex"));
		result.put("state", request.getParameter("state"));
		String queryType = request.getParameter("queryType");
		String orgId = request.getParameter("orgId");
		Page page =  this.getPage(request);
		if("1".equals(queryType)){
			List<String> argList = new ArrayList<String>();
			argList.add(orgId);
			List<String> orgList = getChildIdsByParent(argList);
/*			List<TsysOrg> orgList = tsysOrgService.selectChildById(orgId);
			List<String> argList = new ArrayList<String>();
			for(TsysOrg org:orgList){
				argList.add(org.getOrgId());
			}*/
			result.put("orgList",orgList);
		}else{
			result.put("org_id",  request.getParameter("orgId"));
		}
		result.put("page", page);
		List list = tsysUserinfoService.selectPageTsysUserinfoByMap(result);
		this.convertParam(list, "state", "state");
		renderJson(list, page, response);
	}
	
	
	/**
	 * 递归获取子ID
	 * @param orgId
	 * @return
	 */
	private List<String> getChildIdsByParent(List<String> orgIds){
		List<String> result = new ArrayList<String>();
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("pids", orgIds);
		result = tsysUserinfoService.selectChildByOrgId(map);
		if(result.size() == 0){
			result.addAll(orgIds);
		}else{
			result.addAll(orgIds);
			map.put("pids", result);
			result.addAll(tsysUserinfoService.selectChildByOrgId(map));
		}
		return result;
	}
	
	
	
	@RequestMapping(params = "method=queryUserCount")
	public void queryUserCountByOrgId(HttpServletRequest request, HttpServletResponse response)
			throws ControllerException {
		String orgId = request.getParameter("org_id");
		Integer count = tsysUserinfoService.queryUserCountByOrgId(orgId);
		renderText(response,Integer.toString(count));
	}

	@RequestMapping(params = "method=queryOrgList")
	public void queryOrgList(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ObjectMapper objectMapper = new ObjectMapper();
		this.writeWeb(
				response,
				objectMapper.writeValueAsString(
						tsysUserinfoService.selectSysOrgInfo()).toString());
	}

	@RequestMapping(params = "method=queryPostList")
	public void queryPostList(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String post_id = request.getParameter("post_id");
		Map<String, String> map = new HashMap<String, String>();
		map.put("post_id", post_id);

		String postStr = tsysUserinfoService.selectSysPostInfo(map);
		this.writeWeb(response, postStr);
	}

	@RequestMapping(params = "method=getData")
	@ResponseBody
	public Map<String, Object> getData(HttpServletRequest request,
			HttpServletResponse response) throws ControllerException {
		Map<String, Object> result = new HashMap<String, Object>();
		String pagec = request.getParameter("page"); // 当前页数
		String pageSize = request.getParameter("rows"); 
		Page page =  this.getPage(request);
		TsysUserinfo userinfo=new TsysUserinfo();
		userinfo.setPage(page);
		List list = tsysUserinfoService.selectPageTsysUserinfo(userinfo);
		result.put("total", page.getTotalRows());
		result.put("rows", list);

		return result;
	}

	@RequestMapping(params = "method=delData")
	@ResponseBody
	public Map<String, Object> delDatagetData(HttpServletRequest request,
			HttpServletResponse response) throws ControllerException {
		Map<String, Object> result = new HashMap<String, Object>();

		try {
			String id = request.getParameter("id"); // 当前页数
			tsysUserinfoService.delete(id);
			result.put("msg", this.CREATED_SUCCESS);
			result.put("success", true);
		} catch (Exception e) {
			e.printStackTrace();
			result.put("msg", "删除失败");
			result.put("success", false);
		}

		return result;
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
		ModelAndView mav = new ModelAndView("/user/userinfoform");
		String org_id = request.getParameter("orgId");
		// 开通时间为默认当前时间
		Calendar c = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String dredge_time = sdf.format(c.getTime());
		String treeInfo = this.getPostTree(request, response);
		mav.addObject("zNodes", treeInfo);
		mav.addObject("orgId", org_id);
		mav.addObject("dredge_time", dredge_time);
		return mav;
	}

	/**
	 * 获取岗位树
	 * @param request
	 * @param response
	 * @return
	 */
	private String getPostTree(HttpServletRequest request,
			HttpServletResponse response){
		String result = null;
		List<TsysPost> list = tsysPostService.selectPageTsysPostByMap(null);
		List<ZtreeInfo> treeList = new ArrayList<ZtreeInfo>();
		for (Iterator<TsysPost> i = list.iterator(); i.hasNext();) {
			TsysPost post = i.next();
			ZtreeInfo tree = new ZtreeInfo();
			tree.setId(post.getPostId());
			tree.setName(post.getPostName());
			tree.setPid(post.getParentPostid());
			treeList.add(tree);
		}
		try {
			result = new ObjectMapper().writeValueAsString(treeList);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return result;
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
		ModelAndView mv = new ModelAndView("/user/userinfoform");
		String id = request.getParameter("id");
		TsysUserinfo tsysUserinfo = (TsysUserinfo) tsysUserinfoService
				.selectDetailById(id);
		String treeInfo = this.getPostTree(request, response);
		mv.addObject("zNodes", treeInfo);
		mv.addObject("userinfo", tsysUserinfo);
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
		String id = request.getParameter("id");
		TsysUserinfo tsysUserinfo = (TsysUserinfo) tsysUserinfoService
				.selectDetailById(id);
		this.setValue(request, tsysUserinfo);

		return new ModelAndView("/userinfo/tsysuserinfoform");
	}
	
	/**
	 * 设置用户角色 
	 * @param request
	 * @param response
	 * @return
	 * @throws ControllerException
	 */
	@RequestMapping(params = "method=setRole")
	public ModelAndView setRole(HttpServletRequest request,
			HttpServletResponse response) throws ControllerException {
		String id = request.getParameter("id");
		TsysUserinfo tsysUserinfo = (TsysUserinfo) tsysUserinfoService
				.selectDetailById(id);
		ModelAndView mv = new ModelAndView("/user/userroleform");
		mv.addObject("tsysUserinfo", tsysUserinfo);
		return mv;
	}

	/**
	 * 用户角色授权
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "method=saveUserRole")
	public void saveUserRole(HttpServletRequest request,
			HttpServletResponse response) {
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			String userId = request.getParameter("userId");
			String roleIDs = request.getParameter("roldIDs");
			if(!StringUtils.isEmpty(userId)){
				tuserRoleService.delTuserRoleByUserId(userId);
			}
			if(!StringUtils.isEmpty(roleIDs)){
				String[] roles = roleIDs.split(",");
				TuserRole userRole = null;
				for(String role:roles){
					userRole = new TuserRole();
					userRole.setUserRoleid(Identities.uuid());
					userRole.setRoleId(role);
					userRole.setUserId(userId);
					tuserRoleService.insert(userRole);
				}
			}
			result.put("msg", "用户角色授权成功！！");
			result.put("success", true);
		} catch (Exception e) {
			e.printStackTrace();
			result.put("msg", "保存失败，重新输入再试一次！");
			result.put("success", false);
		}
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			renderJson(response, objectMapper.writeValueAsString(result));
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 用户角色授权
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "method=saveUserRoles")
	public void saveUserRoles(HttpServletRequest request,
			HttpServletResponse response) {
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			String userIDs = request.getParameter("userIDs");
			String roleIDs = request.getParameter("roldIDs");
				tuserRoleService.insertUsers(roleIDs,userIDs);
				result.put("msg", "用户角色授权成功！");
				result.put("success", true);
			}
		 catch (Exception e) {
			e.printStackTrace();
			result.put("msg", "保存失败，重新输入再试一次！");
			result.put("success", false);
		}
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			renderJson(response, objectMapper.writeValueAsString(result));
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 获取角色树
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(params = "method=queryRoleTree")
	public void queryRoleTree(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ObjectMapper objectMapper = new ObjectMapper();
		String userId = request.getParameter("userId");
		TuserRole args = new TuserRole();
		args.setUserId(userId);
		List<ZtreeInfo> roleTree = (List<ZtreeInfo>)tsysUserinfoService.selectRoleInfo();
		String result = objectMapper.writeValueAsString(roleTree);
		this.writeWeb(response,result);
	}
	
	/**
	 * 获取角色树
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(params = "method=queryUserTree")
	public void queryUserTreeByOrgId(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ObjectMapper objectMapper = new ObjectMapper();
		HashMap<String,Object> args = new HashMap<String,Object>();
		String orgId = request.getParameter("orgId");
		String[] orgIDs = orgId.split(",");
		List<String> list = Arrays.asList(orgIDs);
		args.put("orgList",list);
		List<TsysUserinfo> userlist = tsysUserinfoService.selectPageTsysUserinfoByMap(args);
		List<ZtreeInfo> userTree = new ArrayList<ZtreeInfo>();
		for(TsysUserinfo user:userlist){
			ZtreeInfo node = new ZtreeInfo();
			node.setId(user.getUserId());
			node.setName(user.getUserRealname());
			userTree.add(node);
		}
		String result = objectMapper.writeValueAsString(userTree);
		this.writeWeb(response,result);
	}
	
	/**
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(params = "method=saveUser1")
	public void saveUser(HttpServletRequest request,
			HttpServletResponse response) {
		Map<String, Object> result = new HashMap<String, Object>();

		try {
			TsysUserinfo tsysUserinfo = new TsysUserinfo();
			TuserPost userPost = new TuserPost();
			String postId = request.getParameter("postId");
			userPost.setPostId(postId);
			this.setValue(request, tsysUserinfo);

			if (StringUtils.isEmpty(tsysUserinfo.getUserId())) {
				tsysUserinfo.setUserId(Identities.uuid());
				tsysUserinfo.setCreateMan(tsysUserinfo.getUserId());
				tsysUserinfo.setPassword(Eryptogram.getUserPasswd(Constant.DEFAULT_PASSWORD));
         		Calendar c = Calendar.getInstance();
				SimpleDateFormat sdf = new SimpleDateFormat(
						"yyyy-MM-dd HH:mm:ss");
				String createdata = sdf.format(c.getTime());
				tsysUserinfo.setCreateDate(createdata);

				TsysUserinfo user = tsysUserinfoService
						.selectExitsUserByUsername(tsysUserinfo.getLoginName());
				boolean flag = user == null;

				if (flag) {
					long sortNum = tsysUserinfoService.getSortNumByOrgId(tsysUserinfo.getOrgId());
					userPost.setUserId(tsysUserinfo.getUserId());
					userPost.setUserJobid(Identities.uuid());
					tsysUserinfo.setSortNum(sortNum);
					tsysUserinfo.setState("1");
					tsysUserinfo.setPassword(Eryptogram.getUserPasswd(Constant.DEFAULT_PASSWORD));
					tsysUserinfoService.insert(tsysUserinfo);
					tuserPostService.insert(userPost);//保存用户岗位
					result.put("msg", this.CREATED_SUCCESS);
					result.put("success", true);
				} else {
//					tsysUserinfoService.insert(tsysUserinfo);
					result.put("msg", "登录名称已存在，重新输入！");
					result.put("success", false);
				}
			} else {
				tsysUserinfo.setPassword(Eryptogram.getUserPasswd(Constant.DEFAULT_PASSWORD));
				tsysUserinfoService.update(tsysUserinfo);
				userPost.setUserId(tsysUserinfo.getUserId());
				List<TuserPost> userPosts = tuserPostService.selectDetailByUserId(userPost);
				if(userPosts==null||userPosts.size()==0){
					userPost.setUserJobid(Identities.uuid());
					tuserPostService.insert(userPost);//保存用户岗位
				}else{
					tuserPostService.updateUserPost(userPost);//更新用户岗位
				}
				result.put("msg", "修改成功！");
				result.put("success", true);
			}
		} catch (Exception e) {
			e.printStackTrace();
			result.put("msg", "保存失败，重新输入再试一次！");
			result.put("success", false);
		}

		ObjectMapper objectMapper = new ObjectMapper();
		try {
			renderJson(response, objectMapper.writeValueAsString(result));
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@RequestMapping(params = "method=showUserInfo")
	public ModelAndView showUserInfo(HttpServletRequest request,
			HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		String id = request.getParameter("id");
		TsysUserinfo tsysUserinfo = (TsysUserinfo) tsysUserinfoService
				.selectDetailById(id);
		mav.addObject("userInfo", tsysUserinfo);
		mav.setViewName("/user/userInfo");
		return mav;
	}
	
	/**
	 * 根据用户ID查找对应的角色信息
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "method=getRoleInfoByUserId")
	public void getRoleInfoByUserId(HttpServletRequest request,
			HttpServletResponse response) {
		String userId = request.getParameter("id");
		Map<String,Object> para = new HashMap<String,Object>();
		para.put("userId", userId);
		List list = tsysRoleService.selectRoleInfoByMap(para);
		this.convertParam(list, "roleType", "roleType");
		Page page=this.getPage(request);
	    page.setTotalRows(list.size()); // 获取数据的总条数
	    page.setPagination(false);
        renderJson(list, page, response);
	}
	
	/**
	 * 根据用户ID查找对应的角色信息
	 * @param request
	 * @param response
	 * @throws IOException 
	 */
	@RequestMapping(params = "method=getPowerInfoByUserId")
	public void getPowerInfoByUserId(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		String userId = request.getParameter("id") ;
		userId = userId== null ? "": userId;
		TsysUserinfo userinfo = (TsysUserinfo) tsysUserinfoService
				.selectDetailById(userId);
		
		List<Map> list = tsysModuleService.queryModuleTree(userinfo); // 登录用户有权限的菜单
		List<TsysModuleoperate> opList = tsysModuleoperateService
				.selectPageTsysModuleoperate(userinfo); // 登录用户有权限的菜单操作
		List<Map> mouduleOplist = tsysModuleoperateService.genModuleOpTree(
				list, opList, null);// 生成菜单树
		ObjectMapper objectMapper = new ObjectMapper();
		String jsonresult = objectMapper.writeValueAsString(mouduleOplist);
		writeWeb(response, jsonresult);
	}
	/**
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "method=changeUserState")
	public void changeUserState(HttpServletRequest request,
			HttpServletResponse response) {
		Map<String, Object> result = new HashMap<String, Object>();
		String type = request.getParameter("type");
		try {
			String userId = request.getParameter("id");
			tsysUserinfoService.changeUserState(userId,type);
			result.put("flag", true);
			if("1".equals(type)){
				result.put("msg", "用户已经成功启用!");
			}else{
				result.put("msg", "用户已经成功停用!");
			}	
		} catch (Exception e) {
			e.printStackTrace();
			
			if("1".equals(type)){
				result.put("msg", "启用失败,请稍后尝试！");
			}else{
				result.put("msg", "停用失败,请稍后尝试！");
			}	
			result.put("flag", false);
		}
		
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			renderJson(response, objectMapper.writeValueAsString(result));
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "method=resetPassword")
	public void resetPassword(HttpServletRequest request,
			HttpServletResponse response) {
		Map<String, Object> result = new HashMap<String, Object>();
		
		try {
			String userId = request.getParameter("id");
			tsysUserinfoService.resetPasswordByuserId(userId);
			result.put("flag", true);
			result.put("msg", "用户密码重置成功！");
		} catch (Exception e) {
			e.printStackTrace();
			result.put("msg", "重置密码失败,请稍后尝试！");
			result.put("flag", false);
		}
		
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			renderJson(response, objectMapper.writeValueAsString(result));
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
	}

	@RequestMapping(params = "method=saveUser")
	@ResponseBody
	public Map<String, Object> saveTUser(HttpServletRequest request,
			HttpServletResponse response) {
		Map<String, Object> result = new HashMap<String, Object>();

		try {
			TsysUserinfo tsysUserinfo = new TsysUserinfo();
			this.setValue(request, tsysUserinfo);

			if (StringUtils.isEmpty(tsysUserinfo.getUserId())) {
				tsysUserinfo.setUserId(Identities.uuid());
				result.put("msg", this.CREATED_SUCCESS);
			} else {
				tsysUserinfoService.update(tsysUserinfo);
				result.put("msg", "修改成功！");
			}

			result.put("success", true);
		} catch (Exception e) {
			e.printStackTrace();
			result.put("msg", "保存失败，重新输入再试一次！");
			result.put("success", false);
		}

		return result;
	}

	public  void save(HttpServletRequest request,
			HttpServletResponse response, ModelAndView mav, boolean isNew)
			throws ControllerException {
		Map<String, Object> result = new HashMap<String, Object>();
		mav.setView(new RedirectView(request.getContextPath()
				+ "/tsysUserinfoController.do?method=list"));

		try {
			TsysUserinfo tsysUserinfo = new TsysUserinfo();
			this.setValue(request, tsysUserinfo);

			if (StringUtils.isEmpty(tsysUserinfo.getUserId())) {
				tsysUserinfo.setUserId(Identities.uuid());

				tsysUserinfoService.insert(tsysUserinfo);
			} else {
				tsysUserinfoService.update(tsysUserinfo);
			}

			result.put("msg", this.CREATED_SUCCESS);
			result.put("success", true);
			ObjectMapper objectMapper = new ObjectMapper();
			mav.addObject("result", objectMapper.writeValueAsString(result));
			mav.setViewName("jsonView");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 向页面输出一个字节流
	 * 
	 * @param response
	 *            HttpServletResponse
	 * @param cntStr
	 *            要向页面输出的字符串类型的信息
	 **/
	protected void writeWeb(HttpServletResponse response, String cntStr)
			throws IOException {
		response.reset(); // reset
		response.setContentType("text/html; charset=GBK"); // 设置该信息的类别,该类别可以是js、html、文本等信息
		/* 创建输出流 */

		ServletOutputStream servletOS = null;
		InputStream inputStream = null;

		try {
			servletOS = response.getOutputStream();
			inputStream = new ByteArrayInputStream(cntStr.getBytes("GBK"));

			byte[] buf = new byte[1024]; // 缓存大小,也可以看成一次读取信息的大小,
			int readLength;

			while (((readLength = inputStream.read(buf)) != -1)) {
				servletOS.write(buf, 0, readLength); // 将读取的信息写入页面
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (inputStream != null) {
				inputStream.close();
			}

			if (servletOS != null) {
				servletOS.flush();
				servletOS.close();
			}
		}
	}

	/**
	 * 进入用戶页面
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(params = "method=user_list")
	public ModelAndView userlist(HttpServletRequest request,
			HttpServletResponse response) throws ControllerException {
		String org_id = request.getParameter("org_id");
		ModelAndView mv = new ModelAndView("/user/userinfomanage");
		mv.addObject("orgId", org_id);
		return mv;
	}
	
	/**
	 * 移动
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(params="method=tomove")
	public ModelAndView tomove(HttpServletRequest request,
			HttpServletResponse response) throws ControllerException {
		ModelAndView mv = new ModelAndView("/user/usersort");
		String orgId=request.getParameter("treeid");
		mv.addObject("orgId", orgId);
		return mv;
	}
	
	/**
	* 查询机构下的用户树
	* @param request
	* @param response
	* @return
	* @throws Exception
	*/
	@RequestMapping(params="method=userListByOrg")
	public void userListByOrg(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
        ObjectMapper objectMapper=new ObjectMapper();
        Map<String,Object> para = new HashMap<String,Object>();
        List<ZtreeInfo> userList = new ArrayList<ZtreeInfo>();
        ZtreeInfo treeObject  = new ZtreeInfo();
        String orgId=request.getParameter("orgId");
        TsysUserinfo userinfo ;
        TsysOrg tsysOrg=new TsysOrg();
    	para.put("orgId", orgId);
    	para.put("type", 1);
    	tsysOrg=tsysOrgService.selectOrgIdByOrgObject(para);
    	treeObject.setId(tsysOrg.getOrgId());
    	treeObject.setPid("-1");
    	treeObject.setName(tsysOrg.getOrgName());
    	userList.add(treeObject);
    	List userOrgList = tsysUserinfoService.selectUserinfoByOrgId(tsysOrg.getOrgId());
    	for(int i=0;i<userOrgList.size();i++)
    	{
    		treeObject = new ZtreeInfo();
    		userinfo=(TsysUserinfo)userOrgList.get(i);
    		treeObject.setId(userinfo.getUserId());
        	treeObject.setPid(tsysOrg.getOrgId());
        	treeObject.setName(userinfo.getUserRealname());
        	userList.add(treeObject);	
    	}
		this.writeWeb(response,objectMapper.writeValueAsString(userList).toString());
	}
	/**
	 * 删除用户所有权限
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(params="method=delRolePrivilege")
	public void delRolePrivilege(HttpServletRequest request,
			HttpServletResponse response) throws Exception{
		
		String[] userIds = request.getParameterValues("userIds");
		boolean flag = true;
		try {
			for (int i = 0; i < userIds.length; i++) {
				tuserRoleService.delTuserRoleByUserId(userIds[i]);
			}
		} catch (Exception e) {
			e.printStackTrace();
			flag = false;
		}
		String result = "{\"flag\" : " + flag + "}";
		this.renderJson(response, result);
	}
	
	/**
	 * 
	* 排序
	* @param request
	* @param response
	* @return
	* @throws Exception
	*/
	@RequestMapping(params="method=move")
	public void move(HttpServletRequest request,
			HttpServletResponse response){
		Map<String,Object> result = new HashMap<String,Object>();
		TsysUserinfo tsysUser=new TsysUserinfo();
		try
		{
		String userinfo_str=request.getParameter("userlist");
		String [] userObject=userinfo_str.split(",");
		String [] userinfo;
		for (int i=0;i<userObject.length;i++)
		{
			    userinfo=userObject[i].split("@");
			    tsysUser.setUserId(userinfo[0]);
			    tsysUser.setSortNum(Long.parseLong(userinfo[1]));
			    tsysUserinfoService.updateBySort(tsysUser);
		}
		result.put("msg", "排序成功！");
		result.put("success", true);
		}catch(Exception e)
		{
			e.printStackTrace();
			result.put("msg", "排序失败，重新再试一次！");
			result.put("success", false);
		}
		ObjectMapper objectMapper=new ObjectMapper();
		String jsonresult="";
		try {
			jsonresult = objectMapper.writeValueAsString(result);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		renderJson(response, jsonresult);
	}
	
	/**
	 * 导出
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(params = "method=export")
	public void toexport(HttpServletRequest request,
			HttpServletResponse response) throws ControllerException {
		/*
		 * 查询数据
		 */
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("loginName", request.getParameter("login_name"));
		result.put("user_realname", request.getParameter("user_realname"));
		result.put("mobiletel", request.getParameter("mobiletel"));
		result.put("sex", request.getParameter("sex"));
		result.put("state", request.getParameter("state"));
		String queryType = request.getParameter("queryType");
		String orgId = request.getParameter("orgId");
		Page page =  this.getPage(request);
		page.setPageSize(Integer.MAX_VALUE);
		//page.setPageSize(40);
		if("1".equals(queryType)){
			List<String> argList = new ArrayList<String>();
			argList.add(orgId);
			List<String> orgList = getChildIdsByParent(argList);
			result.put("orgList",orgList);
		}else{
			result.put("org_id",  request.getParameter("orgId"));
		}
		result.put("page", page);
		List<TsysUserinfo> list = tsysUserinfoService.selectPageTsysUserinfoByMap(result);	
		
		/*
		 * Excel导出
		 */
		
		//导出excel名称、标题名称、工作薄名称
		ExportParams entity=new ExportParams("用户查询信息","用户基本信息","用户");
		/*
		 * 变量说明：
		 	 * HttpServletRequest
		 	 * HttpServletResponse
			 * ExportParams 表格标题属性 
			 * Excel对象Class
			 * Excel对象数据List
		 * exportExcel(HttpServletRequest request,HttpServletResponse response,ExportParams entity,Class<?> pojoClass, Collection<?> dataSet)
		 */		
		ExcelExportUtil.exportExcel(request,response,entity,TsysUserinfo.class, list);
	}
}
