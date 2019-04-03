package com.musicbar.second.backstage.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.apache.shiro.session.SessionException;
import org.apache.shiro.subject.Subject;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.musicbar.core.annotation.LoggerAnnotation;
import com.musicbar.core.redis.RedisUtils;
import com.musicbar.core.uploads.FileUpload;
import com.musicbar.core.utils.DateUtils;
import com.musicbar.second.backstage.service.TAttachService;
import com.musicbar.second.backstage.service.TRoleService;
import com.musicbar.second.backstage.service.TUserService;
import com.musicbar.second.comm.base.Constants;
import com.musicbar.second.comm.config.ConfigProperties;
import com.musicbar.second.comm.param.ParamTransformation;
import com.musicbar.second.comm.param.ParamUtil;
import com.musicbar.second.domain.TAttach;
import com.musicbar.second.domain.TParameter;
import com.musicbar.second.domain.TRole;
import com.musicbar.second.domain.TUser;
import net.sf.json.JSONArray;
/**
 * 用户管理
 * @author MECHREV
 *
 */
@Component
@Controller
@RequestMapping("/backstage")
public class TUserController {
	@Autowired
	private TUserService tUserService;
	@Autowired
	private ParamTransformation paramTransformation;
	
	@Autowired
	private ParamUtil paramUtil;
	@Autowired
	private ConfigProperties configProperties;
	
	@Autowired
	private TAttachService tAttachService;
	
	@Autowired
    private RedisUtils redis;
	
	/**
	 *  查询用户列表
	 * @return
	 */
	@RequestMapping("/user_list")
	@LoggerAnnotation(begin="进入用户列表方法开始",end="进入用户列表方法结束")
	public ModelAndView list() {
		ModelAndView mv = new ModelAndView("backstage/pages/user/list");
		return mv;
	}
	/**
	 * 条件查询用户列表
	 * @return
	 */
	@RequestMapping("/user_query")
	@LoggerAnnotation(begin="查询用户方法开始",end="查询用户方法结束")
	@ResponseBody
	public String query(TUser user, int pageNum) {
		int pageSize = configProperties.getPageSize();
		PageHelper.startPage(pageNum,pageSize);
		List<TUser> list = tUserService.selectAll(user);
		PageInfo<TUser> page = new PageInfo<>(list);
		return JSONArray.fromObject(page).toString();
	}
	
	/**
	 * 查询用户状态
	 * @return
	 */
	@RequestMapping("user_state")
	@ResponseBody
	public String selectState() {
		List<TParameter> tParameterMapper = tUserService.selectState();
		return JSONArray.fromObject(tParameterMapper).toString(); 
	}
	/**
	 * 用户编辑页面
	 * @return
	 */
	@RequestMapping("/user_edit")
	@LoggerAnnotation(begin="编辑用户方法开始",end="编辑用户方法结束")
	public ModelAndView edit(String userId) {
		ModelAndView mv = new ModelAndView("backstage/pages/user/edit");
		if(userId != null && !userId.isEmpty()) {
			TUser user = tUserService.selectByPrimaryKey(userId);
			mv.addObject("user",user);
		}
		//查询用户状态
		List<TParameter> TParameter = paramUtil.getListByKey("user_state");
		mv.addObject("TParameter", TParameter);
		return mv;
	}
	
	/**
	 * 添加or修改用户
	 * @param request
	 * @param user
	 * @return
	 */
	@RequestMapping("/user_save") 
	@ResponseBody
	@LoggerAnnotation(begin="添加or修改用户方法",end="添加or修改用户方法结束")
	public String save(TUser user,HttpServletRequest request,@RequestParam("txFile") MultipartFile txFile,
			@RequestParam("sfzZMFile") MultipartFile sfzZMFile,@RequestParam("sfzFMFile") MultipartFile sfzFMFile,
			@RequestParam("jkzZMFile") MultipartFile jkzZMFile,@RequestParam("jkzFMFile") MultipartFile jkzFMFile){
		 //创建文件上传
		JSONObject json = new JSONObject();
		List<TAttach> list = new ArrayList<>();
		String userId = user.getUserId();
		int success = 0;
		try {
			 if(txFile!=null && txFile.getSize()>0) {
				 TAttach tAttach = new TAttach();
				 tAttach.setFileRemark("头像");
				 Boolean is = uploadImg(txFile, tAttach);
				 list.add(tAttach);
			 }
			 if(sfzZMFile!=null && sfzZMFile.getSize()>0) {
				 TAttach tAttach = new TAttach();
				 tAttach.setFileRemark("身份证正面");
				 Boolean is = uploadImg(sfzZMFile, tAttach);
				 list.add(tAttach);
			 }
			 if(sfzFMFile!=null && sfzFMFile.getSize()>0) {
				 TAttach tAttach = new TAttach();
				 tAttach.setFileRemark("身份证反面");
				 Boolean is = uploadImg(sfzFMFile, tAttach);
				 list.add(tAttach);
			 }
			 if(jkzZMFile!=null && jkzZMFile.getSize()>0) {
				 TAttach tAttach = new TAttach();
				 tAttach.setFileRemark("健康证正面");
				 Boolean is = uploadImg(jkzZMFile, tAttach);
				 list.add(tAttach);
			 }
			 if(jkzFMFile!=null && jkzFMFile.getSize()>0) {
				 TAttach tAttach = new TAttach();
				 tAttach.setFileRemark("健康证反面");
				 Boolean is = uploadImg(jkzFMFile, tAttach);
				 list.add(tAttach);
			 }
			 user.setAttach(list);
			 success = tUserService.saveOrUpdate(user);
		}catch (Exception e) {
			e.printStackTrace();
		}
		if(success == -1) {
			json.put("msg", "手机号已存在");
			json.put("flag", false);
		}else if(success == -2) {
			json.put("msg", "身份证已存在 ");
			json.put("flag", false);
		}else if(success == 0) {
			json.put("msg",userId.equals("") ? "新增失败" : "修改失败");
			json.put("flag", false);
		}else if(success > 0) {
			json.put("msg",userId.equals("") ? "新增成功" : "修改成功");
			json.put("flag", true);
		}
		return json.toString();
	}	
	
	/**
	 * 图片上传
	 */
	public Boolean uploadImg(MultipartFile file,TAttach tAttach) {
		JSONObject json = new JSONObject();
		List<TAttach> list = new ArrayList<>();
		if(file!=null && file.getSize()>0){
			FileUpload fileUpload = new FileUpload();
        	fileUpload.setSaveFilePath(configProperties.getPath());//根路径
			fileUpload.setPath(configProperties.getUserPath());//上传文件的相对路径
			fileUpload.setAllowFiles(".jpg .jpeg .gif .png .bmp");//文件上传类型
			//获取当前登录用户
			
			//获取当前时间
			String time = DateUtils.fotmatDateTOyMdHms(new Date());
			
			//执行文件上传出参
			fileUpload.fileUpload(file, fileUpload);
			//文件上传失败直接提交
			if(!fileUpload.isIs() && fileUpload.getState() != "") {
				return false;
			}
			//资源文件赋值
			if(fileUpload.isIs()) {
				tAttach.setFileName(fileUpload.getFileName());
				tAttach.setFileSize(String.valueOf(fileUpload.getSize()));
				tAttach.setFileSuffix(fileUpload.getSuffix());
				tAttach.setFileUel(fileUpload.getPath()+fileUpload.getFileName());
			}
		 }
		return true;
	}
	
	/**
	 * 删除
	 * @param userId
	 * @return
	 */
	@RequestMapping("/user_delete")
	@ResponseBody
	public String delete(String userId) {
		JSONObject json = new JSONObject();
		int count = tUserService.deleteByPrimaryKey(userId);
		int num = tAttachService.deleteById(userId);
		if(count > 0 && num > 0) {
			json.put("msg", "删除成功");
		}else {
			json.put("msg", "删除失败");
		}
		return json.toString();
	}
	
	/**
	 * 批量删除
	 * @param userIds
	 * @return
	 */
	@RequestMapping("/user_deleteAll")
	@ResponseBody
	public String deleteAll(String userIds) {
		JSONObject json = new JSONObject();
		int count = 0,num = 0;
		if(!userIds.equals("")) {
			String[] split = userIds.split(",");
			List<String> list = new ArrayList<String>();
			for(String string : split) {
				list.add(string);
			}
			count = tUserService.deleteAll(list);
			num = tAttachService.deleteAll(list);
		}else {
			count = -1;
			num = -1;
		}
		if(count > 0 ) {
			json.put("msg", "删除成功");
		}else if(count == -1) {
			json.put("msg", "请勾选要删除的记录");
		}else {
			json.put("msg", "删除失败");
		}
		return json.toString();
	}
	
	/**
	 * 在职，离职
	 * @param user
	 * @return
	 */
	@RequestMapping("/user_changeState")
	@ResponseBody
	public String changeState(String[] userIds, String userState,HttpServletRequest req) {
		userIds = req.getParameterValues("userIds[]");
		JSONObject json = new JSONObject();
		Map<String, Object> map = new HashMap<>();
		map.put("map", userIds);
		map.put("userState", userState);
		int count = tUserService.changeState(map);
		if(count > 0) {
			json.put("msg", userState.equals("1")  ? "在职成功" : "离职成功");
		}else {
			json.put("msg",userState.equals("1") ? "在职失败"  : "离职失败");
		}
		return json.toString();
		
	}
	/*@RequestMapping("/user_changeState")
	@ResponseBody
	public String changeState(TUser user) {
		JSONObject json = new JSONObject();
		int count = tUserService.changeState(user);
		if(count == 1) {
			json.put("code", 200);
			json.put("msg", "在职操作成功");
		}else {
			json.put("code", 200);
			json.put("msg", "离职操作成功");
		}
		return json.toString();
	}*/

	
	
	/**
	 * 进入首页
	 * @param session
	 * @return
	 */
	/*@RequestMapping("/index")
    @LoggerAnnotation(begin = "进入后台首页开始", end = "进入后台首页结束")
    public ModelAndView index(HttpSession session){
        ModelAndView mv = new ModelAndView("backstage/index");        
        Subject sub = SecurityUtils.getSubject();
        String userId = sub.getPrincipal().toString();
        TUser user = tUserService.selectByPrimaryKey(userId);
        user.getUserName();
        mv.addObject(Constants.USER, user);
        return mv;
    }*/
	
	/**
	 * 后台登录
	 * @param user
	 * @param session
	 * @return
	 */
	/*@RequestMapping("/login")
	@ResponseBody
	public String login(TUser user, HttpSession session) {
        Map<String, Object> map = new HashMap<String, Object>();
        //添加用户信息
        Subject sub = SecurityUtils.getSubject();
        UsernamePasswordToken upt = new UsernamePasswordToken(
                user.getUserMobile(),new Sha256Hash(user.getUserPassword()
                        ,Constants.YD).toHex());
        try {
        	sub.login(upt);
        }catch (Exception e) {
            map.put(Constants.MSG, "用户名或密码错误！");
        }
        //将数据从缓存中取出
        String userId = sub.getPrincipal().toString();
        int rs = redis.getCacheObject("r", int.class);
        rs = 0;
        redis.setCacheObject(userId, rs);
        return new JSONObject(map).toString();
    }*/
	
	/**
	 * 登录判断
	 * @param attrs
	 * @return
	 */
	/*@RequestMapping("/loginJudge")
    public String loginJudge(RedirectAttributes attrs) {
		// 获取当前用户
        Subject sub = SecurityUtils.getSubject();
        String userId = sub.getPrincipal().toString();
        TUser user = tUserService.selectByPrimaryKey(userId);
        if (user.getUserState() == "0") {
            attrs.addFlashAttribute(Constants.MSG, "此用户已离职，不能登录!");
            return "redirect:login.html";
        }
        String userIdcard = user.getUserIdcard();
        String pwd = userIdcard.substring(12, 18);
        pwd = new Sha256Hash(pwd.toString(),Constants.YD).toHex();
        String userPassword = user.getUserPassword();
        if((pwd).equals(userPassword)) {
            attrs.addFlashAttribute(Constants.MSG, "密码为初始密码，请修改密码!");
            return "redirect:pwd_edit";
        }
        return "redirect:index";
    }*/
	
	/**
	 * 退出登录
	 * @param request
	 * @param response
	 * @return
	 */
	/*@RequestMapping("/exit")
    public String userExti(ServletRequest request, ServletResponse response) {
        Subject sub = SecurityUtils.getSubject();
        String userId = sub.getPrincipal().toString();
        TUser user = tUserService.selectByPrimaryKey(userId);
        if (user != null) {
             session.removeAttribute(Constants.USER); 
            // 在这里执行退出系统前需要清空的数据
            Subject subject = getSubject(request, response);
 
            String redirectUrl = getRedirectUrl(request, response, subject);
            try {
                subject.logout();
            } catch (SessionException sie) {
            	sie.printStackTrace();
            }
        }
        return "redirect:/backstage/login.html";
    }
	
	private String getRedirectUrl(ServletRequest request, ServletResponse response, Subject subject) {
		// TODO Auto-generated method stub
		return null;
	}
	private Subject getSubject(ServletRequest request, ServletResponse response) {
		// TODO Auto-generated method stub
		return null;
	}
	*/
	/**
	 * 进入后台用户修改密码页面
     * @param useId
     * @return
     */  
    /*@RequestMapping("/pwd_edit")
    @LoggerAnnotation(begin = "进入修改密码页面方法开始", end = "进入修改密码页面方法结束")
    @ResponseBody
    public ModelAndView updatePwd(String userPwd, String userOldPwd,
    		HttpSession session, RedirectAttributes attrs) {
        ModelAndView mv = new ModelAndView("backstage/pwd_edit");
        Subject sub = SecurityUtils.getSubject();
        String userId = sub.getPrincipal().toString();
        TUser user = tUserService.selectByPrimaryKey(userId);
        mv.addObject(Constants.USER, user);
        return mv;
    }*/
	
	/**
	 * 修改密码
	 * @param userPwd
	 * @param userOldPwd
	 * @param session
	 * @param attrs
	 * @return
	 */
   /* @RequestMapping("/updatePwd")
    @LoggerAnnotation(begin = "修改用户密码方法开始", end = "修改用户密码方法结束")
    public String updateUserPwd(String userPwd, String userOldPwd,
    		HttpSession session, RedirectAttributes attrs) {      
        //用户信息
        Subject sub = SecurityUtils.getSubject();
        String userId = sub.getPrincipal().toString();
        TUser user = tUserService.selectByPrimaryKey(userId);
        String userPassword = user.getUserPassword();
        userOldPwd =  new Sha256Hash(userOldPwd.toString(),Constants.YD).toHex();
        if (!(userPassword).equals(userOldPwd)) {
            attrs.addFlashAttribute(Constants.MSG, "原密码错误!！");
            return "redirect:pwd_edit";
        }
        userPwd = new Sha256Hash(userPwd.toString(),Constants.YD).toHex();
        System.out.println(userOldPwd);
        user.setUserPassword(userPwd);
        int count = tUserService.saveOrUpdate(user, userPassword);
        if (count != 1) {
            attrs.addFlashAttribute(Constants.MSG, "用户密码修改失败!");
            return "redirect:pwd_edit";
        } 
            attrs.addFlashAttribute(Constants.MSG, "用户密码修改成功!");
            session.removeAttribute(Constants.USER); // 删除登录用户
            return "backstage/login";
    }*/
}
