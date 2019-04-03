/**   
 * 功能描述：
 * @Package: com.musicbar.core.utils 
 * @author: shj 
 * @date: 2019年3月18日 下午7:58:31 
 */
package com.musicbar.core.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpRequest;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @ClassName: CookieUtil.java
 * @Description: 该类的功能描述
 * @version: v1.0.0
 * @author: shj
 * @date: 2019年3月18日 下午7:58:31
 */
public class CookieUtil {
	public static final String BACK_CART = "backCart";

	/**
	 * 根据名字获取cookie
	 * 
	 * @param request
	 * @param name
	 *            cookie名字
	 * @return
	 */
	public Cookie getCookieByName(HttpServletRequest request, String name) {
		Map<String, Cookie> cookieMap = ReadCookieMap(request);
		if (cookieMap.containsKey(name)) {
			Cookie cookie = (Cookie) cookieMap.get(name);
			cookie.setValue(getCookieDecoder(cookie.getValue()));
			return cookie;
		} else {
			return null;
		}
	}

	/**
	 * 将cookie封装到Map里面
	 * 
	 * @param request
	 * @return
	 */
	private Map<String, Cookie> ReadCookieMap(HttpServletRequest request) {
		Map<String, Cookie> cookieMap = new HashMap<String, Cookie>();
		Cookie[] cookies = request.getCookies();
		if (null != cookies) {
			for (Cookie cookie : cookies) {
				cookieMap.put(cookie.getName(), cookie);
			}
		}
		return cookieMap;
	}

	/**
	 * 添加cookie
	 * 
	 * @param response
	 * @param name
	 * @param value
	 */
	public void addCookie(HttpServletRequest req, HttpServletResponse response, String name, String value) {
		Cookie cookie = getCookieByName(req, name);
		if (cookie == null) {
			cookie = new Cookie(name.trim(), setCookieEncoder(value.trim()));
			cookie.setMaxAge(30 * 60);// 设置为30min
		} else {
			cookie.setMaxAge(30 * 60);// 设置为30min
			cookie.setValue(setCookieEncoder(value.trim()));
		}
		cookie.setPath("/");
		response.addCookie(cookie);
	}

	// 对cookie解碼
	public String getCookieDecoder(String cookieName) {
		String cookie = null;
		try {
			cookie = URLDecoder.decode(cookieName, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return cookie;
	}

	// 对cookie編碼
	public String setCookieEncoder(String value) {
		String cookie = null;
		try {
			cookie = URLEncoder.encode(value, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return cookie;
	}

}
