package com.creatorblue.cbitedu.system.controller;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.creatorblue.cbitedu.core.baseclass.controller.HihBaseController;
import com.creatorblue.cbitedu.core.constants.Constant;
import com.creatorblue.cbitedu.core.exception.ControllerException;
import com.creatorblue.cbitedu.core.page.Page;
import com.creatorblue.cbitedu.core.utils.DateUtils;
import com.creatorblue.cbitedu.core.utils.Eryptogram;
import com.creatorblue.cbitedu.system.domain.TsysModule;
import com.creatorblue.cbitedu.system.domain.TsysModuleoperate;
import com.creatorblue.cbitedu.system.domain.TsysOrg;
import com.creatorblue.cbitedu.system.domain.TsysRole;
import com.creatorblue.cbitedu.system.domain.TsysUserinfo;
import com.creatorblue.cbitedu.system.service.TsysModuleService;
import com.creatorblue.cbitedu.system.service.TsysModuleoperateService;
import com.creatorblue.cbitedu.system.service.TsysOrgService;
import com.creatorblue.cbitedu.system.service.TsysRoleService;
import com.creatorblue.cbitedu.system.service.TsysUserinfoService;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
@RequestMapping("/index.do")
public class IndexController extends HihBaseController {
	@Autowired
	TsysUserinfoService userinfoService;
	@Autowired
	TsysModuleService moduleService;
	@Autowired
	TsysOrgService orgService;
	@Autowired
	TsysRoleService roleService;
	@Autowired
	TsysModuleoperateService moduleoperateService;
	@Autowired
	private TsysModuleService tsysModuleService;
	@Autowired
	private TsysModuleoperateService tsysModuleoperateService;

	/**
	 * 系统登录
	 * 
	 * @param userinfo
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(params = "method=login")
	public void login(TsysUserinfo userinfo, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String loginName = userinfo.getLoginName();
		String password = userinfo.getPassword();
		String front_pwd = Eryptogram.getUserPasswd(password); // 加密后的密码与数据库存储的密码进行比较
		String msg = "";
		int result = 0;

		String yzm = request.getParameter("yzm");
		String yyzm = (String) request.getSession().getAttribute("yzm");

		if (!yzm.equals(yyzm)) {
			msg = "验证码错误";
			result = 1;
		} else {
			TsysUserinfo tSysUserinfo = userinfoService
					.selectExitsUserByUsername(loginName);

			if (tSysUserinfo == null) {
				msg = "账号不存在，请与系统管理员联系";
				result = 1;
			} else {
				String dbpwd = tSysUserinfo.getPassword();
				boolean passwordEq = dbpwd.equals(front_pwd);

				if (!passwordEq) {
					msg = "密码不正确";
					result = 1;
				}

				if ("0".equals(tSysUserinfo.getState())) {
					msg = "用户已停用,请与系统管理员联系!";
					result = 1;
				} else {
					putSession(request, Constant.USER_INFO, tSysUserinfo);

					Long userLogincount = tSysUserinfo.getUserLogincount();
					userLogincount = (userLogincount == null) ? 0L
							: userLogincount;
					tSysUserinfo.setUserLogincount(++userLogincount);
					tSysUserinfo.setLoginDate(DateUtils.getNowDateTime());
					tSysUserinfo.setLoginIp(request.getRemoteAddr());
					userinfoService.update(tSysUserinfo);
				}
			}
		}

		Map map = new HashMap();
		map.put("result", result);
		map.put("msg", msg);

		ObjectMapper mapper = new ObjectMapper(); // jackson框架，作用是把Object转换成json
		renderJson(response, mapper.writeValueAsString(map));
	}

	/**
	 * 跳转到系统主页界面
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(params = "method=toIndex")
	public ModelAndView toIndex(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		TsysUserinfo userinfo = (TsysUserinfo) getSession(request,
				Constant.USER_INFO);

		// 用户及机构信息 根据用户的id来获取用户的机构信息
		String orgId = userinfo.getOrgId();

		// List<TsysOrg> orgList = orgService.selectTsysOrgByUser(userinfo);
		List orgList = new ArrayList(1);
		orgList.add(orgService.selectDetailById(orgId));
		putSession(request, Constant.USER_DEFAULT_ORG, orgList);

		// 获得用户角色 根据用户的id来获取用户的机构信息
		List<TsysRole> roleList = roleService.selectTsysRoleByUser(userinfo);
		putSession(request, Constant.USER_DEFAULT_ROLE, roleList);

		// 用户权限信息 根据用户的id来获取用户的“按钮权限”
		List<Map> operateList = moduleoperateService
				.selectModuleoperateByUser(userinfo);
		Map<String, Map<String, String>> privilegesMap = new HashMap<String, Map<String, String>>(
				64);

		for (Map<String, String> map : operateList) { // 转换成Map结构

			String opcode = map.get("operate_code");
			String mcode = map.get("module_code");
			String pKey = opcode + mcode;
			Map<String, String> pValue = new HashMap<String, String>(2);
			pValue.put(opcode, mcode);
			privilegesMap.put(pKey, pValue);
		}

		putSession(request, Constant.USER_PRIVILEGES_DATA, privilegesMap);

		// 菜单信息
		Map paramMap = new HashMap();
		paramMap.put("display", "0");

		List<TsysModule> moduleList = moduleService.selectPageTsysModuleByMap(
				paramMap, userinfo);
		ObjectMapper mapper = new ObjectMapper();
		putSession(request, Constant.MODULE_NO,
				mapper.writeValueAsString(moduleList));

		return new ModelAndView("/index");
	}

	@RequestMapping(params = "method=toLogin")
	public ModelAndView toLogin(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		remove(request, Constant.USER_INFO);

		return new ModelAndView("/login");
	}

	/**
	 * 系统退出
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(params = "method=logout")
	public RedirectView logout(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// 退出登录,/会话失效，跳到登录页面
		request.getSession().invalidate();

		return new RedirectView("/index.do?method=toLogin", true);
	}

	private Color getRandColor(int fc, int bc) { // 给定范围获得随机颜色

		Random random = new Random();

		if (fc > 255) {
			fc = 255;
		}

		if (bc > 255) {
			bc = 255;
		}

		int r = fc + random.nextInt(bc - fc);
		int g = fc + random.nextInt(bc - fc);
		int b = fc + random.nextInt(bc - fc);

		return new Color(r, g, b);
	}

	/**
	 * 生成验证码
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(params = "method=randomCode")
	public void randomCode(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// 验证码图片的宽度。
		int width = 73;

		// 验证码图片的高度。
		int height = 30;
		BufferedImage buffImg = new BufferedImage(width, height,
				BufferedImage.TYPE_INT_RGB);
		Graphics2D g = buffImg.createGraphics();

		// 创建一个随机数生成器类。
		Random random = new Random();

		// 设定图像背景色(因为是做背景，所以偏淡)
		g.setColor(getRandColor(200, 250));
		g.fillRect(0, 0, width, height);

		// 创建字体，字体的大小应该根据图片的高度来定。
		Font font = new Font("Times New Roman", Font.HANGING_BASELINE, 28);
		// 设置字体。
		g.setFont(font);
		// 画边框。
		g.setColor(Color.BLACK);
		g.drawRect(0, 0, width - 1, height - 1);
		// 随机产生155条干扰线，使图象中的认证码不易被其它程序探测到。
		// g.setColor(Color.GRAY);
		g.setColor(getRandColor(160, 200));

		/*
		 * for (int i = 0; i < 155; i++) { int x = random.nextInt(width); int y
		 * = random.nextInt(height); int xl = random.nextInt(12); int yl =
		 * random.nextInt(12); g.drawLine(x, y, x + xl, y + yl); }
		 */

		// randomCode用于保存随机产生的验证码，以便用户登录后进行验证。
		StringBuffer randomCode = new StringBuffer();

		// 设置默认生成4个验证码
		int length = 4;

		// 设置备选验证码:包括"a-z"和数字"0-9"
		// abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ
		String base = "0123456789";
		int size = base.length();

		// 随机产生4位数字的验证码。
		for (int i = 0; i < length; i++) {
			// 得到随机产生的验证码数字。
			int start = random.nextInt(size);
			String strRand = base.substring(start, start + 1);

			// 用随机产生的颜色将验证码绘制到图像中。
			// 生成随机颜色(因为是做前景，所以偏深)
			// g.setColor(getRandColor(1, 100));

			// 调用函数出来的颜色相同，可能是因为种子太接近，所以只能直接生成
			g.setColor(new Color(20 + random.nextInt(110), 20 + random
					.nextInt(110), 20 + random.nextInt(110)));

			g.drawString(strRand, (15 * i) + 6, 24);

			// 将产生的四个随机数组合在一起。
			randomCode.append(strRand);
		}

		// 将四位数字的验证码保存到Session中。
		HttpSession session = request.getSession();
		session.setAttribute("yzm", randomCode.toString());

		// 图象生效
		g.dispose();
		// 禁止图像缓存。
		response.setHeader("Pragma", "no-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
		response.setContentType("image/jpeg");

		// 将图像输出到Servlet输出流中。
		ServletOutputStream sos = response.getOutputStream();
		ImageIO.write(buffImg, "jpeg", sos);
		sos.flush();
		sos.close();
	}

	/**
	 * 修改用户密码
	 * 
	 * @param request
	 * @param response
	 * @throws IOException
	 * @throws ControllerException
	 */
	@RequestMapping(params = "method=modifyPwd")
	public void modifyPwd(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		String data_pwd = request.getParameter("data_pwd");
		TsysUserinfo tsysUserinfo = (TsysUserinfo) this.getSession(request,
				Constant.USER_INFO);
		boolean flag = true;

		if (tsysUserinfo != null) {
			tsysUserinfo.setPassword(Eryptogram.getUserPasswd(data_pwd));

			try {
				userinfoService.updateBySelective(tsysUserinfo);
			} catch (Exception e) {
				flag = false;
				e.printStackTrace();
			}
		}

		this.writeWeb(response, "{\"result\" : " + flag + "}");
	}

	/**
	 * 锁定用户
	 * 
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping(params = "method=lockWindow")
	public void lockWindow(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		TsysUserinfo tsysUserInfo = (TsysUserinfo) this.getSession(request,
				Constant.USER_INFO); // 获取登录用户
		String loginName = tsysUserInfo.getLoginName();
		request.getSession().invalidate();
		this.writeWeb(response, "{\"result\" : true, \"loginName\" : \""
				+ loginName + "\"}");
	}

	/**
	 * 锁定用户登录
	 * 
	 * @param userinfo
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(params = "method=lockLogin")
	public void lockLogin(TsysUserinfo userinfo, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String loginName = userinfo.getLoginName();
		String password = userinfo.getPassword();
		String front_pwd = Eryptogram.getUserPasswd(password); // 加密后的密码与数据库存储的密码进行比较
		String msg = "";
		int result = 0;
		TsysUserinfo tSysUserinfo = userinfoService
				.selectExitsUserByUsername(loginName);

		String dbpwd = tSysUserinfo.getPassword();
		boolean passwordEq = dbpwd.equals(front_pwd);

		if (!passwordEq) {
			msg = "密码不正确";
			result = 1;
		} else if ("0".equals(tSysUserinfo.getState())) {
			msg = "用户已停用,请与系统管理员联系!";
			result = 1;
		} else {
			// 用户及机构信息
			putSession(request, Constant.USER_INFO, tSysUserinfo);

			final String userId = tSysUserinfo.getUserId();
			List<TsysOrg> orgList = orgService
					.selectTsysOrgByUser(tSysUserinfo);
			putSession(request, Constant.USER_DEFAULT_ORG, orgList);
		}

		Map map = new HashMap();
		map.put("result", result);
		map.put("msg", msg);

		ObjectMapper mapper = new ObjectMapper();
		renderJson(response, mapper.writeValueAsString(map));
	}

	/**
	 * 显示我的信息
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(params = "method=showMyInfo")
	public ModelAndView showMyInfo(HttpServletRequest request,
			HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();

		// 用户基本信息
		TsysUserinfo tsysUserinfo = (TsysUserinfo) this.getSession(request,
				Constant.USER_INFO);
		mav.addObject("userInfo", tsysUserinfo);

		List<TsysRole> roleList = (List<TsysRole>) this.getSession(request,
				Constant.USER_DEFAULT_ROLE);

		mav.setViewName("userInfo");

		return mav;
	}

	/**
	 * @Description:不做任何处理跳转到roles.jsp页面
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(params = "method=roles")
	public ModelAndView redirect(HttpServletRequest request,
			HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();

		mav.setViewName("/roles");
		return mav;
	}

	/**
	 * @Description:跳转到权限信息
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(params = "method=power")
	public ModelAndView redirectPower(HttpServletRequest request,
			HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/power");
		return mav;
	}

	@RequestMapping(params = "method=powerData")
	public void power(HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		TsysUserinfo userinfo = (TsysUserinfo) getSession(request,
				Constant.USER_INFO);
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
	 * 展示我的信息中获取用户角色
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "method=getRoles")
	public void showMyInfo_getRoles(HttpServletRequest request,
			HttpServletResponse response) {
		List<TsysRole> roleList = (List<TsysRole>) this.getSession(request,
				Constant.USER_DEFAULT_ROLE);
		Page page = this.getPage(request);
		page.setTotalRows(roleList.size()); // 获取数据的总条数
		page.setPagination(false);
		this.convertParam(roleList, "roleType", "rolesort");
		renderJson(roleList, page, response);
	}
}
