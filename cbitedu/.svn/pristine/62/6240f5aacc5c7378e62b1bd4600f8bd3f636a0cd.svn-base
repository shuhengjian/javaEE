<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!-- HTTP 1.1 -->
<meta http-equiv="Cache-Control" content="no-store" />
<%@ page import="java.util.Map"%>
<%@ page import="java.util.HashMap"%>
<!-- HTTP 1.0 -->
<meta http-equiv="Pragma" content="no-cache" />
<!-- Prevents caching at the Proxy Server -->
<META HTTP-EQUIV="Expires" CONTENT="Mon, 04 Dec 1999 21:29:02 GMT">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<%
Map<String, Cookie> cookieMap = new HashMap<String, Cookie>();
Cookie[] cookies = request.getCookies();
if (null != cookies) {
	for (Cookie cookie : cookies) {
		cookieMap.put(cookie.getName(), cookie);
	}
}
String easyuiTheme = "bootstrap";//默认的easyui风格
if (cookieMap.containsKey("easyuiTheme")) {
	Cookie cookie = (Cookie) cookieMap.get("easyuiTheme");
	easyuiTheme = cookie.getValue();
}
%>
<link rel="shortcut icon" href="favicon.ico" type="image/x-icon" />
<!-- easyui -->
<link rel="stylesheet" type="text/css" href="${ctx}/css/default.css" title="bootstrap"/>
<link id="easyuiTheme" rel="stylesheet" href="${ctx}/ui_widget/easyui/themes/<%=easyuiTheme%>/easyui.css" type="text/css">
<link rel="stylesheet" type="text/css" href="${ctx}/ui_widget/easyui/themes/default/easyui.css" title="blue"/>
<link rel="stylesheet" type="text/css" href="${ctx}/ui_widget/easyui/themes/icon.css" />
<link rel="stylesheet" type="text/css" href="${ctx}/ui_widget/easyui/themes/normalize.css" />
<script type="text/javascript" src="${ctx}/ui_widget/easyui/jquery.min.js"></script>
<script type="text/javascript" src="${ctx}/ui_widget/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${ctx}/ui_widget/easyui/locale/easyui-lang-zh_CN.js" charset="utf-8"></script>
<!-- flexigrid -->
<link rel="stylesheet" type="text/css" href="${ctx}/ui_widget/flexigrid/themes/gray/flexigrid.css"/>
<script type="text/javascript" src="${ctx}/ui_widget/flexigrid/js/flexigrid.js"></script>
<!-- ztree -->
<link rel="stylesheet" type="text/css" href="${ctx}/ui_widget/ztree/css/zTreeStyle/zTreeStyle.css">
<script type="text/javascript" src="${ctx}/ui_widget/ztree/js/jquery.ztree.all-3.5.min.js"></script>
<!-- jquery.validationEngine -->
<link rel="stylesheet" type="text/css" href="${ctx}/ui_widget/formvalidator/css/validationEngine.jquery.css">
<link rel="stylesheet" href="${ctx}/ui_widget/formvalidator/css/template.css" type="text/css"/>
<script type="text/javascript" src="${ctx}/ui_widget/formvalidator/js/jquery.validationEngine-zh_CN.js"></script>
<script type="text/javascript" src="${ctx}/ui_widget/formvalidator/js/jquery.validationEngine.js"></script>
<script type="text/javascript" src="${ctx}/js/exteasyui.js"  charset="utf-8"></script>
<script type="text/javascript" src="${ctx}/js/extjquery.js"></script>
<script type="text/javascript" src="${ctx}/js/extends.js"></script>
<script type="text/javascript" src="${ctx}/js/common.js"></script>