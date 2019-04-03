<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<%@ include file="/WEB-INF/jsp/common/meta.jsp"%>
<%@ include file="/WEB-INF/jsp/common/title.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" type="text/css" href="${ctx}/ui_widget/ztree/css/demo.css"> 
<script type="text/javascript" src="${ctx}/js/jquery.form.js"></script>

</head>
<body>

<div  id="container" fit="true"
			class="easyui-layout easyui-panel">
<script type="text/javascript">
	var setting = {
			view : {
				dblClickExpand : true
			},
			data : {
				simpleData : {
					enable : true,
					idKey : "id",
					pIdKey : "pid",
					rootPId : 0
				}
			},
			callback : {
				onClick : onClick
			}
		};
	/*加载岗位树*/
	
	var zNodes = $.parseJSON('${zNodes}');
	$(function(){
		$.parser.parse();
		$.validationEngine.defaults.promptPosition="bottomLeft";
		$("#formID").validationEngine({
			showOnMouseOver : true
		});
		$.fn.zTree.init($("#treeDemo"), setting, zNodes);
		var treeObj = $.fn.zTree.getZTreeObj("treeDemo");
		treeObj.expandAll(true);
	});

	function save_click(myWindow,query){
		var bool = $("#formID").validationEngine('validate');
		if(!bool){
			return ;
		}
		
		$("#formID").ajaxSubmit(function(data){  // 异步提交表单数据
			if(data.success){
				parent.jQuery.messager.alert('提示:', data.msg, 'info',function(){
					query();
				});
				myWindow.dialog("close");
			}else{
				parent.jQuery.messager.alert('提示:', data.msg,'info');
			}
		});
	}
	
	function back_click(myWindow){
		myWindow.dialog("close");
	}

	function onClick(e, treeId, treeNode) {
		$("#parentPostid").val(treeNode.id);
		$("#userpost").val(treeNode.name);
		$("#menuContent").fadeOut("fast");
	}
		
	function showMenu() {
		var cityObj = $("#userpost");
		var cityOffset = $("#userpost").offset();
		$("#menuContent").css({
			left : cityOffset.left + "px",
			top : cityOffset.top + cityObj.outerHeight() + "px"
		}).slideDown("fast");
		$("body").bind("mousedown", onBodyDown);
	}
	
	function hideMenu() {
		$("#menuContent").fadeOut("fast");
		$("body").unbind("mousedown", onBodyDown);
	}
	
	function onBodyDown(event) {
		if (!(event.target.id == "menuBtn" || event.target.id == "menuContent" || $(
				event.target).parents("#menuContent").length > 0)) {
			hideMenu();
		}
	}
</script>
<!-- 表单布局设计begin -->
<cbitedu:form bean="userinfo" scope="request">
<form action="${ctx}/tsysUserinfoController.do?method=saveUser1" method="post" name="userinfoForm" id="formID">
	<input type="hidden" name="userId" />
	<input type="hidden" name="orgId" id="orgId" <c:choose>
					             <c:when test="${empty orgId}">
					                 value="${userinfo.orgId}"
					             </c:when>
					             <c:otherwise>
					             	value="${orgId}"
					             </c:otherwise>
                     </c:choose>/>
	<div>
		<table class="FormView" border="0" cellspacing="1" cellpadding="0">
			<col class="Label" />
			<col class="Data" />
			<col class="Label" />
			<col class="Data" />
			<tr>
				<td>
					<fmt:message key="userinfo.loginName" /><!-- 登录账号 -->
				</td>
				<td>
					<input type="text" id="loginName" name="loginName" class="text validate[required]" />
					<font color="red">*</font>
				</td>
	            <td>
					<fmt:message key="userinfo.userRealname" /><!-- 用户实名 -->
				</td>
				<td>
					<input type="text" id="userRealname" name="userRealname"  class="text validate[required]" />
					<font color="red">*</font>
				</td>
			</tr>
			<tr>
				<td>
					<fmt:message key="userinfo.password" /><!-- 用户密码 -->
				</td>
				<td>
					<input type="password" id="password" name="password" class="text validate[required]" value="123456"/>
					<font color="red">*默认123456</font>
				</td>
	            <td>
					<fmt:message key="userinfo.userpost" /><!-- 所属岗位 -->
				</td>
				<td> 
					<input type="hidden" id="parentPostid" name="postId"  />
					<input type="text" id="userpost" name="postName" readonly="readonly" class="text" />&nbsp;
					<a id="menuBtn" href="javascript:void(0)" onclick="showMenu(); return false;">选择</a>
				</td>
			</tr>
			<tr>
				<td>
					<fmt:message key="userinfo.sex" /><!-- 用户性别 -->
				</td>
				<td>
					<select id="sex" name="sex" class="select">						
						<option value="男">男</option>
						<option value="女">女</option>
						<option value="">未知</option>
					</select>
				</td>
				<td>
					<fmt:message key="userinfo.mobiletel" /><!-- 手机 -->
				</td>
				<td>
					<input type="text" id="mobiletel" name="mobiletel" class="text validate[maxSize[11]]" />
				</td>
			</tr>
			<tr>
				<td>
					<fmt:message key="userinfo.fax" /><!-- 传真-->
				</td>
				<td>
					<input type="text" id="fax" name="fax" class="text" />
				</td>
				<td>
					<fmt:message key="userinfo.email" /><!-- 电子邮箱 -->
				</td>
				<td>
					<input type="text" id="email" name="email" class="text validate[email]" />
				</td>
			</tr>			
			<tr>
				<td>
					<fmt:message key="userinfo.zip" /><!-- 邮编 -->
				</td>
				<td>
					<input type="text" id="zip" name="zip" class="text" />
				</td>
				<td>
					<fmt:message key="userinfo.user_type" /><!-- 用户类型-->
				</td>
				<td>
				<cbitedu:para name="userType" cssClass="select" type="select"></cbitedu:para>
				</td>


			</tr>			
		</table>
	</div>
</form>
<!-- 表单布局设计end -->
<!-- 表单内的按钮设计begin -->
<div align="center" class="foot-buttons" style="margin-top: 5px">
	<a id="btn_save"  class="easyui-linkbutton" iconCls="icon-save"><fmt:message key="button.save" /></a>
	<a id="btn_back"  class="easyui-linkbutton" iconCls="icon-back"><fmt:message key="button.back" /></a>
</div>
</cbitedu:form>
	<div id="menuContent" class="menuContent" style="display: none; position: absolute;">
		<ul id="treeDemo" class="ztree" style="margin-top: 0; height : 120px; width: 190px; " ></ul>
	</div>
</div>	
</body>
</html>
