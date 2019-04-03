<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<%@ include file="/WEB-INF/jsp/common/title.jsp"%>
<script type="text/javascript" src="${ctx}/js/jquery.form.js"></script>
<script type="text/javascript">

$(function(){
	$.parser.parse();
/* 	$("#tsysAppForm").validationEngine({
		showOnMouseOver : true
	}); */
});
/* function save_click(){
	$("#tsysAppForm").ajaxSubmit(function(data){  // 异步提交表单数据
		alert(123);
		if(data.flag){
			jQuery.messager.alert('提示:', data.msg, 'info', function(){
				reloadlist();
			});
			$("#win").window("close");
		}else{
			jQuery.messager.alert('提示:', data.msg,'info');
		}
	});
} */
function save_click(){
	var bool=$("#tsysAppForm").validationEngine('validate');
	   if(bool==true)
	   {
		   $.ajax({
				url: "${ctx}/tsysAppController.do?method=saveSysApp",
				type: "POST",
				data: $("#tsysAppForm").serialize(),
				dataType: 'json',
				error: function(){
					alert("发生错误!");
				},
				success: function(data){
					if(data.flag){
						jQuery.messager.alert('提示:', data.msg, 'info', function(){
							reloadlist();
						});
						$("#win").window("close");
					}else{
						jQuery.messager.alert('提示:', data.msg,'info');
					}
				}
			});   
	   }else{
		   jQuery.messager.alert('提示:','您的表单输入校验不通过，请重填!','info');  
	}
}
function back_click(){
	$("#win").window("close");
}
</script>
</head>
<body>
<!-- 表单布局设计begin -->
<cbitedu:form bean="tsysApp" scope="request">
		<form action="<%=request.getContextPath()%>/tsysAppController.do?method=saveSysApp" method="post" name="tsysAppForm"
			id="tsysAppForm">
			<div>
			<input type="hidden" name="appId" />
				<table class="FormView" border="0" cellspacing="1" cellpadding="0">
					<col class="Label" />
					<col class="Data" />
					<tr width="100%">
						<td><fmt:message key="tsysapp.appName" /></td>
						<td><input type="text" id="appName" name="appName"
							class="text validate[required,maxSize[60]]" /><font color="red">**</font>
						</td>
					</tr>
					<tr>
						<td><fmt:message key="tsysapp.appShortname" /></td>
						<td><input type="text" id="appShortname" name="appShortname"
							class="text validate[required,maxSize[15]]" /><font color="red">**</font>
						</td>
					</tr>
					<tr>
						<td><fmt:message key="tsysapp.appPath" /></td>
						<td><input type="text" id="appPath" name="appPath"
							class="validate[required,maxSize[100]] text" /><font
							color="red">**</font></td>
					</tr>
					<tr>
						<td><fmt:message key="tsysapp.appIcon" /></td>
						<td><input type="text" id="appIcon" name="appIcon"
							class="text validate[required,maxSize[10]]"/><font color="red">**</font>
						</td>
					</tr>
					</table>
			</div>
		</form>
	<!-- 表单布局设计end -->	
	<!-- 表单内的按钮设计begin -->
	<div align="center" class="foot-buttons" style="margin-top:5px">
	<a id="btn_save" onclick="save_click();" class="easyui-linkbutton" iconCls="icon-save"><fmt:message key="button.save" /></a>
	<a id="btn_save" onclick="back_click();" class="easyui-linkbutton" iconCls="icon-back"><fmt:message key="button.back" /></a>
		</div>
	</cbitedu:form>
</body>
</html>