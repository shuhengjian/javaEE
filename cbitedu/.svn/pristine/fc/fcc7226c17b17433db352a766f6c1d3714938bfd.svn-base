package com.creatorblue.cbitedu.core.interceptor;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.creatorblue.cbitedu.core.constants.Constant;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


/**
 * Title: 会话过期，重新登录
 * Description:公共属性 Copyright: Copyright (c) 2014
 * Company:hihsoft.co.,ltd
 * @author hihsoft.co.,ltd
 * @version 1.0
 */
public class SessionInterceptor extends HandlerInterceptorAdapter {
    static Log log = LogFactory.getLog(SessionInterceptor.class);

    public boolean preHandle(HttpServletRequest request,
        HttpServletResponse response, Object handler) throws Exception {
        // 设置页面不缓存
        response.setHeader("Cache-Control", "no-cache");
        response.setHeader("Cache-Control", "no-store");
        response.setHeader("Pragma", "no-cache");
        response.setDateHeader("Expires", 0);
        HttpSession session=request.getSession();
        // 后台session控制
        String[] noFilters = new String[] {
                "/dispatch.html", "/index.do?method=toLogin",
                "/index.do?method=randomCode", "/index.do?method=login",
                "/index.do?method=lockLogin","/frontJumpController","/frontRuleController"
                ,"/ttyNewsFrontController","/ttyNewsFrontController","/ttyProductController"//数组内的路径在不登录时不拦截
            };
        String uri = request.getRequestURI() +
            (StringUtils.isEmpty(request.getQueryString()) ? ""
                                                           : ("?" +
            request.getQueryString()));
        boolean beFilter = true;

        for (String s : noFilters) {
            if (uri.indexOf(s) != -1) {
                beFilter = false;
                break;
            }
        }
        if (beFilter) {
            Object obj = request.getSession().getAttribute(Constant.USER_INFO);

            if (null == obj) {
                String loginurl = "http://" + request.getServerName() + ":" +
                    request.getServerPort() + request.getContextPath();

                // 未登录
                response.setContentType("application/json;charset=UTF-8");
                PrintWriter out = response.getWriter();
                StringBuilder builder = new StringBuilder();
                response.setCharacterEncoding("UTF-8");
                response.setContentType("text/html;charset=UTF-8");
                builder.append("<script type=\"text/javascript\" charset=\"UTF-8\">");
                builder.append("alert(\"页面过期,请重新登录\");");
                builder.append("window.top.location.href='dispatch.html'");
                builder.append("</script>");
                out.print(builder.toString());
                out.close();

                return false;
            }
        }

        return super.preHandle(request, response, handler);
    }
}
