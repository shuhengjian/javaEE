<%@page import="com.creatorblue.cbitedu.core.constants.Constant"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<%@ include file="/WEB-INF/jsp/common/meta.jsp"%>
<script type="text/javascript" src="${ctx}/js/jquery.form.js"></script>
</head>
<body style="overflow:auto;background-color: white">
<!-- 表单布局设计begin -->
<div class="easyui-panel" title="角色基本信息" data-options="closable:false,collapsible:true" style="position: relative;width:770px">
		<input type="hidden" id="roleId" name="roleId" value="${tsysRole.roleId }"/>
			<table class="FormView" border="0" cellspacing="1" cellpadding="0">
				<col class="Label" />
				<col class="Data" />
				<col class="Label" />
				<col class="Data" />
				<tr>
					<td><fmt:message key="tsysrole.roleName" /></td>
					<td>${tsysRole.roleName }</td>
					<td><fmt:message key="tsysrole.remark" /></td>
					<td>${tsysRole.remark }</td>
				</tr>
				<tr>
					
					<td><fmt:message key="tsysrole.dataScope" /></td>
					<td>
						${tsysRole.dataScopeName }
					</td>
					<td></td>
					<td></td>
				</tr>
			</table>
	</div>
	<c:set var="dc"><%=Constant.DATA_SCOPE.CUSTOM.getValue()%></c:set>
	<c:if test="${tsysRole.dataScope==dc}">
	<!-- 表单布局设计end -->
	<div id="dpanel" class="easyui-panel" data-options="closable:false,collapsible:true" title="数据权限" style="position: relative;;width:770px">
			<ul id="orginfo_tree" class="ztree"></ul>
	</div>
	</c:if>
	<div align="center" class="easyui-panel" title="操作权限" data-options="closable:false,collapsible:true" title="操作权限" style="position: relative;;width:770px">
		<ul id="module_tree" class="ztree"></ul>
		<div align="center" class="foot-buttons" >
			<a id="btn_back" class="easyui-linkbutton"
				iconCls="icon-back"><fmt:message key="button.back" /></a>
		</div>
	</div>
<script type="text/javascript">
	$(function() {
		load_tree();
		load_org_tree();
	});
	var zTree, treeNodes, org_zTree, org_treeNode;
	//加载菜单菜单树
	function load_tree() {
		var setting = {
			check : {
				enable : true,
				chkStyle : "checkbox"
			},
			view : {
				dblClickExpand : false
			},
			data : {
				simpleData : {
					enable : true,
					idKey : "id",
					pIdKey : "pId",
					rootPId : 0
				}
			}
		};

		//获取数据
		$.ajax({
			type : 'post',
			url : '${ctx}/tsysRoleController.do?method=moduleTree&roleId='
					+ $("#roleId").val(),
			dataType : 'json',
			contentType : "application/x-www-form-urlencoded; charset=utf-8",
			error : function() {
				alert('系统繁忙,请稍后再试');
			},
			success : function(data) {
				treeNodes = data;
				$(treeNodes).each(function(){
					this.chkDisabled = true;
				});
				zTree = $.fn.zTree.init($("#module_tree"), setting, treeNodes);
				zTree.expandAll(true);
			}
		});
	}
	//加载部门树
	function load_org_tree() {
		var setting = {
			check : {
				enable : true,
				chkStyle : "checkbox"
			},
			view : {
				dblClickExpand : false
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
				onClick : function() {
				}
			}
		};

		//获取数据 机构树
		$.ajax({
			type : 'post',
			url : '${ctx}/tsysRoleController.do?method=orgList',
			dataType : 'json',
			contentType : "application/x-www-form-urlencoded;charset=utf-8",
			timeout : 7000,
			error : function() {
				alert('系统繁忙,请稍后再试');
			},
			success : function(data) {
				org_treeNodes = data;
				$(org_treeNodes).each(function(){
					this.chkDisabled = true;
				});
				$.fn.zTree.init($("#orginfo_tree"), setting, org_treeNodes);
				org_zTree = $.fn.zTree.getZTreeObj("orginfo_tree");
				org_zTree.expandAll(true);
			}
		});
	}

	function back_click(win) {
		win.dialog("close");
	}
</script>
</body>
</html>