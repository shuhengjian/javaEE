package com.musicbar.second.comm.xss;
/*package com.creatorblue.bootdemo.comm.xss;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
//@WebFilter(initParams={@WebInitParam(name="excludes",value="/favicon.ico,/img/*,/js/*,/css/*")
//	,@WebInitParam(name="isIncludeRichText",value="true")},urlPatterns="/*")
public class XssFilter implements Filter {
	private static final Logger logger = LogManager.getLogger(XssFilter.class);
	 

	private static boolean IS_INCLUDE_RICH_TEXT = false;//是否过滤富文本内容
	
	public List<String> excludes = new ArrayList<>();//需要过滤的请求路径正则列表
  
    @Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException,ServletException {
    	if(logger.isDebugEnabled()){
  			logger.debug("xss filter is open");
  		}
  		
  		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
  		if(handleExcludeURL(req, resp)){
  			filterChain.doFilter(request, response);
			return;
		}
  		
  		XssHttpServletRequestWrapper xssRequest = new XssHttpServletRequestWrapper((HttpServletRequest) request,IS_INCLUDE_RICH_TEXT);
  		filterChain.doFilter(xssRequest, response);
    }
    
    private boolean handleExcludeURL(HttpServletRequest request, HttpServletResponse response) {
 
		if (excludes == null || excludes.isEmpty()) {
			return false;
		}
 
		String url = request.getRequestURI();
		for (String pattern : excludes) {
			Pattern p = Pattern.compile("^" + pattern);
			Matcher m = p.matcher(url);
			if (m.find()) {
				return true;
			}
		}
 
		return false;
	}
 
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		if(logger.isDebugEnabled()){
			logger.debug("xss filter init ====================");
		}
		String isIncludeRichText = filterConfig.getInitParameter("isIncludeRichText");
		if(isIncludeRichText!= null && !isIncludeRichText.isEmpty()){
			IS_INCLUDE_RICH_TEXT = Boolean.getBoolean(isIncludeRichText);
		}
		
		String temp = filterConfig.getInitParameter("excludes");
		if (temp != null) {
			String[] url = temp.split(",");
			for (int i = 0; url != null && i < url.length; i++) {
				excludes.add(url[i]);
			}
		}
	}
 
	@Override
	public void destroy() {} 

}
*/