<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<%@ include file="/WEB-INF/jsp/common/meta.jsp"%>
<%@ include file="/WEB-INF/jsp/common/title.jsp"%>

<script type="text/javascript">
	var tree_id = "";
	var ismenu = ""; //是否为主菜单（0：多应用系统主菜单，  null:模块菜单）
	var currentNodeId = ""; //当前节点ID
	function treeClick(e, treeId, treeNode) {
		tree_id = treeNode.id;
		ismenu = treeNode.ismenu;
		//var url = "${ctx}/tsysModuleController.do?method=viewModule&id="+treeNode.id;
		$.ajax({
			type : "post",
			dataType : "text",
			data : {
				"ismenu" : ismenu
			},
			url : '${ctx}/tsysModuleController.do?method=viewModule&id='
					+ treeNode.id,
			cache : false,
			async : false,
			error : function() {
				alert("请求失败!");
			},
			success : function(result) {
				var module = eval('(' + result + ')');
				modlueViewShow(module);
			}
		});
	}


	$(function() {
		var win = $("#win").window({
			title : "菜单模块信息",
			modal : true
		});
		var handler = function() {
			var hid = function() {
				win.window("close");
			};
			$("input[type='button']").click(hid);
		};
		
		
		//添加模块
		$("#btn_add").click(
				function() {
					var node = moduleTree.getSelectedNodes()[0];
					if (node != null) {
						ismenu = node.ismenu;
						currentNodeId = node.id; //当前节点ID
						win.window('open');
						$("#msg").load(
								"${ctx}/tsysModuleController.do?method=add&ismenu="
										+ ismenu + "&id=" + currentNodeId
										+ "", "", handler);

					} else
						jQuery.messager.alert('提示:','请选择主菜单!','info');

		});
		//修改模块
		$("#btn_edit").click(function(){
			var node = moduleTree.getSelectedNodes()[0];
			if (node != null && node.ismenu!=0) {
				currentNodeId = node.id; 		//当前节点ID
				win.window('open');
				$("#msg").load(
						"${ctx}/tsysModuleController.do?method=modify&id=" + currentNodeId
								+ "", "", handler);
				
			} else
				jQuery.messager.alert('提示:','请选择需修改菜单模块!','info');

		});
		
		//删除模块
		$("#btn_delete").click(function(){
			var node = moduleTree.getSelectedNodes()[0];
			if (node != null && node.ismenu!=0) {
				jQuery.messager.confirm('提示:','你确认要删除吗?',function(event){
					if(event){
							currentNodeId = node.id; 		//当前节点ID
							$.get("${ctx}/tsysModuleController.do?method=deleteModule",{id:currentNodeId},function(data){
								if(data=="0"){
									$.messager.alert("操作提示", "该菜单模块拥有子模块，不能删除","warning");
								}else{
									jQuery.messager.alert('提示:','删除成功！', 'info', function(){
										window.location.href="tsysModuleController.do?method=list";
									});
								}
							});
							
					}
				});
			} else
				jQuery.messager.alert('提示:','请选择需删除得菜单模块!','info');
				
				
			 
			

		});
		//设置模块操作
		$("#btn_add_operate").click(function(){
			var node = moduleTree.getSelectedNodes()[0];
			if (node != null && node.ismenu=="1") {
			
				currentNodeId = node.id; //当前节点ID
				
				win.window('open');
				$("#msg").load(
						"${ctx}/tsysModuleoperateController.do?method=list&id=" + currentNodeId
								+ "", "", handler);

			} else
				jQuery.messager.alert('提示:','请选择一个菜单模块设置操作!','info');
		});
	});
	
	function modlueViewShow(tsysmodule) {
	//	$("#moduleViewDiv").show();
		$("#appId").val(tsysmodule.appName);
		$("#moduleName").val(tsysmodule.moduleName);
		$("#linkAddr").val(tsysmodule.linkAddr);
		$("#moduleClass").val(tsysmodule.moduleClass);
		$("#icon").val(tsysmodule.icon);
		$("#moduleClass").val(tsysmodule.moduleClass);
		$("#moduleCode").val(tsysmodule.moduleCode);
		$("#parentModuleid").val(tsysmodule.parentModuleid);
		$("#parentModuleName").val(tsysmodule.parentModuleName);
		$("#sortNum").val(tsysmodule.sortNum);
		$("#remark").val(tsysmodule.remark);
		$("#display").val(tsysmodule.display);
	}
</script>
<body class="easyui-layout">
	<div data-options="region:'west',split:true,title:'菜单树'"
		style="width: 280px;">
			<div id="toolbar_area" border="false">
				<a class="easyui-linkbutton" iconCls="icon-add" plain="true"
					id="btn_add"><fmt:message key="button.add" /></a> 
				<a class="easyui-linkbutton" iconCls="icon-edit" plain="true"
					id="btn_edit"><fmt:message key="button.edit" /></a> 
				<a class="easyui-linkbutton" iconCls="icon-remove" plain="true"
					id="btn_delete"><fmt:message key="button.remove" /></a>
				<a class="easyui-linkbutton" iconCls="icon-add" plain="true"
					id="btn_add_operate">设置操作</a>
			</div>

			<div>
				<jsp:include page="tsysmoduletree.jsp"></jsp:include>
			</div>
	</div>
	
		
		<div data-options="region:'center',title:'菜单模块详细信息'">
				<div class="right_list"
							style="width: 100%; height: 100%; "
							id="moduleViewDiv">
							<form action="/tsysAppController" method="post"
								name="tsysAppForm" id="formID">
								<input type="hidden" name="parentModuleid" id="parentModuleid" >
								<div>
									<table class="FormView" border="0" cellspacing="1"cellpadding="0">
										<col class="Label" />
										<col class="Data" />
										<tr>
											<td><fmt:message key="tsysmodule.appId" /></td>
											<td><input type="text" id="appId" name="appId" value="${tsysModule.appName }"
												class="text validate[required,maxSize[25]]" /></td>
										</tr>
										<tr>
											<td><fmt:message key="tsysmodule.moduleName" /></td>
											<td><input type="text" id="moduleName" name="moduleName" value="${tsysModule.moduleName }"
												class="text validate[required,maxSize[25]]" /></td>
										</tr>
										<tr>
											<td><fmt:message key="tsysmodule.linkAddr" /></td>
											<td><input type="text" id="linkAddr" name="linkAddr" value="${tsysModule.linkAddr }"
												class="text validate[required,maxSize[15]]" /></td>
										</tr>
										<tr>
											<td><fmt:message key="tsysmodule.moduleClass" /></td>
											<td><input type="text" id="moduleClass"
												name="moduleClass" value="${tsysModule.moduleClass }"
												class="validate[required,maxSize[100]] text" /></td>
										</tr>
										<tr>
											<td><fmt:message key="tsysmodule.icon" /></td>
											<td><input type="text" id="icon" name="icon" value="${tsysModule.icon }"
												class="text validate[required,maxSize[2]]" /></td>
										</tr>
										<tr>
											<td><fmt:message key="tsysmodule.moduleCode" /></td>
											<td><input type="text" id="moduleCode" name="moduleCode" value="${tsysModule.moduleCode }"
												class="text validate[required,maxSize[2]]" /></td>
										</tr>
										<tr>
											<td><fmt:message key="tsysmodule.parentModuleid" /></td>
											<td><input type="text" id="parentModuleName"
												name="parentModuleName" value="${tsysModule.parentModuleName }"
												class="text validate[required,maxSize[2]]" /></td>
										</tr>
										<tr>
											<td><fmt:message key="tsysmodule.sortNum" /></td>
											<td><input type="text" id="sortNum" name="sortNum" value="${tsysModule.sortNum }"
												class="text validate[required,maxSize[2]]" /></td>
										</tr>
										<tr>
											<td><fmt:message key="tsysmodule.remark" /></td>
											<td><input type="text" id="remark" name="remark" value="${tsysModule.remark }"
												class="text validate[required,maxSize[2]]" /></td>
										</tr>
										<tr>
											<td><fmt:message key="tsysmodule.display" /></td>
											<td><input type="text" id="display" name="display" value="${tsysModule.display }"
												class="text validate[required,maxSize[2]]" /></td>
										</tr>
									</table>
								</div>
							</form>
						</div>
		</div>

	<!-- 表单窗口设计：新增、修改、查看明细 -->
	<div id="win" closed="true" style="width: 480px; height: 350px;">
		<div id="msg"></div>
	</div>
</body>
</html>
