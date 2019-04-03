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
<cbitedu:form bean="tsysRole" scope="request">
<div class="easyui-panel" title="角色基本信息" data-options="closable:false,collapsible:true" style="position: relative;width:770px">
<form action="${ctx}/tsysRoleController.do?method=saveRole" method="post"
		name="tsysRoleForm" id="tsysRoleForm">
		<input type="hidden" id="roleId" name="roleId" />
		<input type="hidden" id="orgId" name="orgId" />
		<input type="hidden" id="creator" name="creator" />
		<input type="hidden" id="createDate" name="createDate" />
			<table class="FormView" border="0" cellspacing="1" cellpadding="0">
				<col class="Label" />
				<col class="Data" />
				<col class="Label" />
				<col class="Data" />
				<tr>
					<td><fmt:message key="tsysrole.roleName" /></td>
					<td><input type="text" id="roleName"
						name="roleName" class="text validate[required,maxSize[30]]" style="width:200px;"/><font
						color="red">**</font></td>
				<td><fmt:message key="tsysrole.remark" /></td>
					<td><input id="remark" name="remark" class="text" style="width:270px;"/></td>
				</tr>
				<tr>
				<td><fmt:message key="tsysrole.roleType" /></td>
					<td>
							<cbitedu:para id="roleType" name="roleType" type="select" style="width:200px;"/>
							<font color="red">**</font>
							</td>
					<td><fmt:message key="tsysrole.dataScope" /></td>
								<td><select id="dataScope" name="dataScope" style="width:270px">
										<c:forEach items="${DATA_SCOPE }" var="v">
											<option value="${v.value }">${v.name }</option>
										</c:forEach>
					</select></td>
										
				</tr>
			</table>
	</form>
	</div>
	<!-- 表单布局设计end -->
	<div id="dpanel" class="easyui-panel" data-options="closable:false,collapsible:true" title="数据权限" style="position: relative;;width:770px">
			<ul id="orginfo_tree" class="ztree"></ul>
	</div>
	<div align="center" class="easyui-panel" title="操作权限" data-options="closable:false,collapsible:true" title="操作权限" style="position: relative;;width:770px">
		<ul id="module_tree" class="ztree"></ul>
		<div align="center" class="foot-buttons" >
			<a id="btn_save" class="easyui-linkbutton"
				iconCls="icon-save"><fmt:message key="button.save" /></a> 
			<a id="btn_back" class="easyui-linkbutton"
				iconCls="icon-back"><fmt:message key="button.back" /></a>
		</div>
	</div>
</cbitedu:form>
<script type="text/javascript">
	$(function() {
		//$(":text").eq(0).focus();
		$.validationEngine.defaults.promptPosition="centerRight";
		$("#tsysRoleForm").validationEngine({
			showOnMouseOver : true
		});

		load_tree();
		load_org_tree();
		
		changeDataScope();
		
		$("#dataScope").change(changeDataScope);
		var flag = "${flag}";
     	if("modify" == flag) {
			$("select[name='roleType']").attr("disabled","disabled");
			$("#roleName").attr("disabled","disabled");
		}
		
	});
	function changeDataScope(){
		var dval=$("#dataScope").val();
		if(dval=="<%=Constant.DATA_SCOPE.CUSTOM.getValue()%>"){
			//$("#orginfo_tree").show();
			//$("#dpanel").panel('expand');
			$("#dpanel").panel('open');
		}else{
			//$("#orginfo_tree").hide();
			//$("#dpanel").panel('collapse');
			$("#dpanel").panel('close');
		}
	}
	var zTree, treeNodes, org_zTree, org_treeNodes;
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
			}
		};

		//获取数据 机构树
		$.ajax({
			type : 'post',
			url : '${ctx}/tsysRoleController.do?method=orgList&roleId='
				+ $("#roleId").val(),
			dataType : 'json',
			contentType : "application/x-www-form-urlencoded;charset=utf-8",
			timeout : 7000,
			error : function() {
				alert('系统繁忙,请稍后再试');
			},
			success : function(data) {
				org_treeNodes=data;
				$.fn.zTree.init($("#orginfo_tree"), setting, org_treeNodes);
				org_zTree = $.fn.zTree.getZTreeObj("orginfo_tree");
				org_zTree.expandAll(true);
				$(data).each(function(){
					if(this.checked){
						var treeNode=org_zTree.getNodeByParam("id",this.id);
						org_zTree.checkNode(treeNode, true, true);//执行checkNode方法确保父节点能被选中
					}
				});
			}
		});
	}

	function back_click(win) {
		win.dialog("close");
	}
	function save_click(win,query) {
		$("select[name='roleType']").removeAttr("disabled");
		$("#roleName").removeAttr("disabled");
		var chkNodes = zTree.getCheckedNodes();
		chkNodes = $.grep(chkNodes, function(o, i) {
// 			if(o.ismenu=="1"){//如果是菜单节点，并且是最后一级的菜单
// 				var menuChild=zTree.getNodesByParam("ismenu","1",o);
// 				return menuChild.length==0;
// 			}else if(o.isop=="1"){//如果是操作节点
// 				return true;
// 			}
			return true;
		});
		$(chkNodes).each(function() {
			if(this.ismenu=="1"){
				$("#tsysRoleForm").append("<input type='hidden' name='module_id' value='"+this.id+"'/>");
			}else if(this.isop=="1"){
				$("#tsysRoleForm").append("<input type='hidden' name='operate_id' value='"+this.id+"'/>");
			}
		});

		var org_chkNodes = org_zTree.getCheckedNodes();
		org_chkNodes=$.grep(org_chkNodes,function(o,i){//排除半选状态的部门
			return !o.getCheckStatus().half;
		});
		$(org_chkNodes).each(function() {
			$("#tsysRoleForm").append("<input type='hidden' name='org_id' value='"+this.id+"'/>");
		});

		var bool = $("#tsysRoleForm").validationEngine('validate');
		if(bool){
			$("#tsysRoleForm").ajaxSubmit(function(data){
				if(data.success){
					jQuery.messager.alert('提示:', data.msg, 'info',function(){
						query();
						back_click(win);
					});
					
				}else{
					jQuery.messager.alert('提示:', data.msg,'info');
				}
			});
		}else{
			jQuery.messager.alert('提示:', '您的表单输入校验不通过，请重填!', 'info');
		}
		
		return false;
	}
</script>
</body>
</html>