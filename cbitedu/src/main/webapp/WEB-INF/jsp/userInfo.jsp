<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<%@ include file="/WEB-INF/jsp/common/meta.jsp"%>
<%@ include file="/WEB-INF/jsp/common/title.jsp"%>
</head>
<script type="text/javascript">
$(function() {
	$("#d1").load("${ctx}/index.do?method=roles");
	$("#d2").load("${ctx}/index.do?method=power");
});
</script>
<body>
	<div class="easyui-tabs" style="height: 445px;">
		<div title="基本信息" style="padding: 10px" align="center">
			<table class="FormView" border="0" cellspacing="1" cellpadding="0"
				style="font-size: 12px; color: #000000;">
				<col class="Label" />
				<col class="Data" />
				<col class="Label" />
				<col class="Data" />
				<tr>
					<td>用户ID</td>
					<td>${userInfo.userId}</td>
					<td>登录账号</td>
					<td>${userInfo.loginName}</td>
				</tr>
				<tr>
					<td>姓名</td>
					<td>${userInfo.userRealname}</td>
					<td>性别</td>
					<td>${userInfo.sex}</td>
				</tr>
				<tr>
					<td>手机</td>
					<td>${userInfo.mobiletel}</td>
					<td>传真</td>
					<td>${userInfo.fax}</td>
				</tr>
				<tr>
					<td>电子邮箱</td>
					<td>${userInfo.email}</td>
					<td>邮编</td>
					<td>${userInfo.zip}</td>
				</tr>
			</table>
		</div>
		<div title="角色信息">
			<div style="overflow: hidden; height: 412px;" id="d1">
			</div>
		</div>
		<div title="权限信息">
			<div style="overflow: auto; height: 412px;" id="d2">
			</div>
		</div>
	</div>
</body>
</html>