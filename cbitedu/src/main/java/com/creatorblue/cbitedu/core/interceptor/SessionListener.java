package com.creatorblue.cbitedu.core.interceptor;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * Title: 会话过期，重新登录 Description:公共属性 Copyright: Copyright (c) 2014
 * Company:hihsoft.co.,ltd
 * 
 * @author hihsoft.co.,ltd
 * @version 1.0
 */
public class SessionListener implements HttpSessionListener {

	/**
	 * 用户和Session绑定关系
	 */
	public static final Map<String, HttpSession> USER_SESSION=new HashMap<String, HttpSession>();
	
	/**
	 * seeionId和用户的绑定关系
	 */
	public static final Map<String, String> SESSIONID_USER=new HashMap<String, String>();
	
	@Override
	public void sessionCreated(HttpSessionEvent arg0) {
		// TODO Auto-generated method stub
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent se) {
		String sessionId=se.getSession().getId();
		//当前session销毁时删除当前session绑定的用户信息
		//同时删除当前session绑定用户的HttpSession
		USER_SESSION.remove(SESSIONID_USER.remove(sessionId));
	}
	
}
