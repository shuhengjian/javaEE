package com.musicbar.second.comm.shiro;

import java.util.HashMap;
import java.util.Map;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.musicbar.core.shiro.FilterChainDefinition;
import com.musicbar.second.backstage.service.TUserService;
import com.musicbar.second.comm.config.ConfigProperties;
import com.musicbar.second.domain.TUser;


@Component("myShiroRealm")
public class MyShiroRealm extends AuthorizingRealm implements FilterChainDefinition{

	@Autowired
	private TUserService userService;
	
	@Autowired
    private ConfigProperties config;
	
/*	public MyShiroRealm() {
		System.out.println("ccccccccccccccccccc");
	}*/
	//@Autowired
	//@Lazy
	//private UserDaoImpl userDao;
	//业务访问认证(判断登陆用户是否有指定业务权限)
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		//获取登录用户
		String userId= (String) principals.getPrimaryPrincipal();
		//给当前用户授权的权限（功能权限、角色）
		SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
		//。。根据用户ID查询用户的角色
		  
		//。。循环创建角色ID数组，同时填充用户角色
		simpleAuthorizationInfo.addRole("admin");
		  
		//。。根据角色查询角色的权限，循环权限取出权限标识并填充权限
		simpleAuthorizationInfo.addStringPermission("type:add");
		
		
        //User user = userDao.selectByName(name);
        //添加角色和权限
       
      
        //添加权限
        
        simpleAuthorizationInfo.addStringPermission("type:query");
        return simpleAuthorizationInfo;
	}
	//用户登陆认证(从数据库中查询用户信息填充进shiro域，登陆操作时会自动调用该方法，
	//进行账号密码比较)
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		//加这一步的目的是在Post请求的时候会先进认证，然后在到请求
	    if (token.getPrincipal() == null) {
	        return null;
	    }
        
        //获取用户信息
       //UsernamePasswordToken utoken = (UsernamePasswordToken)token;
        //获取用户名密码
      /* utoken.getUsername();
       utoken.getPassword();*/
        //自己写代码
        
        String mobile = token.getPrincipal().toString();      
        //String pwd = token.getCredentials().toString();
        String pwd = new String((char[])token.getCredentials());
	    //根据用户名和密码查询用户信息，
	   	TUser user = new TUser();
	    user.setUserMobile(mobile);
	    user.setUserPassword(pwd);
	    TUser u = userService.selectMobilePwd(user);
        if (u == null) {
            //这里返回后会报出对应异常
            return null;
        } else {
        	//这里验证authenticationToken和simpleAuthenticationInfo的信息
        	//。。用户保存到redis
        	//u.getUserId()和redis的k(键值)要一致
        	SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(
        			u.getUserId(), u.getUserPassword(), getName());
            return simpleAuthenticationInfo;      	
        }
	}
	
	public Map<String,String> getFilterChainDefinitionMap(){
		Map<String,String> map = new HashMap<String, String>();
		//登出
        /*map.put("/logout","logout");
        //处理登陆请求时不认证
        map.put("/**","anon");//请求路径 ，标识
        //对所有用户认证
        map.put("/**","authc");//验证过滤
*/		 
		return map;
	}
	
	public Map<String,String> getUrlMap(){
		Map<String,String> map = new HashMap<String, String>();
		/*map.put("loginUrl","/backstage/login.html"); //登录
		map.put("successUrl","/backstage/index.html");//首页
		map.put("unauthorizedUrl","/error");//错误页面，认证不通过跳转
*/		return map;
	}
	
}
