<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<%@ include file="/WEB-INF/jsp/common/meta.jsp"%>
<%@ include file="/WEB-INF/jsp/common/title.jsp"%>
</head>
<body>
	<div class="easyui-tabs" id="infoTab">
		<div title="基本信息" style="padding: 10px" align="center">
			<table class="FormView" style="font-size: 12px;" border="0" cellspacing="1" cellpadding="0">
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
		<div title="角色信息" style="overflow: hidden; height: 412px;" id="roleInfo">
			<div id="roles_list"></div>
		</div>
		<div title="权限信息">
			<div style="overflow: auto; height: 412px;" id="powerInfo">
				<ul id="module_tree" class="ztree"></ul>
			</div>
		</div>
	</div>
<script type="text/javascript">
	var userId ='${userInfo.userId}';
	$(function() {
		showUserInfoList(userId);
		showUserPower(userId);
	});
	function showUserPower(uid){
		//$("#powerInfo").load("${ctx}/tsysUserinfoController.do?method=getPowerInfoByUserId&id="+uid);
		// 操作权限
		var zTree, treeNodes;
		var setting = {
			check : {
				enable : false,
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
			url : '${ctx}/tsysUserinfoController.do?method=getPowerInfoByUserId&id='+uid,
//			url : '${ctx}/tsysUserinfoController.do?method=getPowerInfoByUserId&id="+uid',
			dataType : 'json',
			contentType : "application/x-www-form-urlencoded; charset=utf-8",
			error : function() {
				alert('系统繁忙,请稍后再试');
			},
			success : function(data) {
				treeNodes = data;
				$(treeNodes).each(function() {
					this.chkDisabled = true;
				});
				zTree = $.fn.zTree.init($("#module_tree"), setting, treeNodes);
				zTree.expandAll(true);
			}
		});
	}
	function showUserInfoList(uid){
		$("#roles_list").flexigrid({
			url : "${ctx}/tsysUserinfoController.do?method=getRoleInfoByUserId&id="+uid,// ajax方式对应的url地址
			type : 'post',// 数据发送方式 
			dataType : 'json',// 数据加载的类型(xml/json)        
			contentType : 'application/x-www-form-urlencoded; charset=UTF-8',
			sortname : "roleId",//排序
			sortorder : "asc",
			useRp : true,
			checkbox : false,// 是否要多选框
			idProperty : 'roleId',// 多选框绑定行的id
			singleSelect : true, //仅允许选择单行 
			rp : 15,
			showTableToggleBtn : false,
			width : 'auto',// 宽度值 
			height : 'auto',// 插件的高度，单位为px   
			striped : true,// 是否显示斑纹效果，默认是奇偶交互的形式
			resizable : false,// 是否可伸缩   
			errormsg : '发生错误', //错误提升信息
			usepager : false, // 是否分页
			nowrap : true, //是否不换行               
			rpOptions : [ 10, 15, 20, 30, 40, 50 ],
			proitemg : '正在处理数据，请稍候 ...', //正在处理的提示信息    

			colModel : [ {
				display : '角色名称',
				name : 'roleName',
				width : 260,
				sortable : true,
				align : 'center'
			}, {
				display : '所属机构',
				name : 'org.orgName',
				width : 100,
				sortable : false,
				align : 'center'
			}, {
				display : '创建人',
				name : 'userinfo.userRealname',
				width : 80,
				sortable : false,
				align : 'center'
			}, {
				display : '创建时间',
				name : 'createDate',
				width : 80,
				sortable : false,
				align : 'center'
			} ]
		});
	}
</script>	
</body>
</html>