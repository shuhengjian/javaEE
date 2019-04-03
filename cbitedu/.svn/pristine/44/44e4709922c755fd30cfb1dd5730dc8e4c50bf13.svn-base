<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="/WEB-INF/jsp/common/meta.jsp"%>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<%@ include file="/WEB-INF/jsp/common/title.jsp"%>
<script type="text/javascript" src="${ctx}/js/jquery.form.js"></script>
<script type="text/javascript">
	var zTree, treeNodes;
	var orgzTree, orgTreeNodes;
	var userzTree, userTreeNodes;
	$(function() {
		$.parser.parse();//不加此方法 图标不会显示
		load_orgTree();
		load_tree();
		$("#checkAllTrue").bind("click", {type:"checkAllTrue"}, checkNode);
		$("#checkAllFalse").bind("click", {type:"checkAllFalse"}, checkNode);
	});

	//加载树
	function load_orgTree() {
		var setting = {
			check : {
				enable : true,
				chkboxType : {
					"Y" : "",
					"N" : ""
				}
			},
			edit : {
				enable : false,
				showRemoveBtn : false,
				showRenameBtn : false
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
				//onClick: treeClick,
				onCheck : onCheck
			}
		};

		//获取数据 机构树
		$.ajax({
			type : 'post',
			url : '${ctx}/tsysOrgController.do?method=queryOrgList',
			dataType : 'json',
			contentType : "application/x-www-form-urlencoded;charset=utf-8",
			timeout : 7000,
			error : function() {
				alert('系统繁忙,请稍后再试');
			},
			success : function(data) {
				orgTreeNodes = data;
				$.fn.zTree.init($("#orginfo_tree"), setting, orgTreeNodes);
				orgzTree = $.fn.zTree.getZTreeObj("orginfo_tree");
				myExpandNode(orgzTree, true);
				orgzTree.checkAllNodes(false);//取消树节点全部选中
			}
		});
	}
	function onCheck(e, treeId, treeNode) {
		var checkedNodes = orgzTree.getCheckedNodes(true);
		if (checkedNodes.length == 0) {
			userzTree.destroy();
			return;
		}
		var orgIDs = "";
		for (var i = 0; i < checkedNodes.length; i++) {
			if (i == (checkedNodes.length - 1)) {
				orgIDs += checkedNodes[i].id;
			} else {
				orgIDs += checkedNodes[i].id + ",";
			}
			;
		}
		load_userinfo_tree(orgIDs);
	}
	//加载角色树
	function load_tree() {
		var setting = {
			check : {
				enable : true,
				chkStyle : "checkbox"
			},
			edit : {
				enable : false,
				showRemoveBtn : false,
				showRenameBtn : false
			},
			view : {
				dblClickExpand : false
			},
			data : {
				simpleData : {
					enable : true,
					idKey : "id"
				}
			},
			callback : {
				onClick : function() {
				}
			}
		};
		var uid = $('#userId').val();
		//获取角色树
		$.ajax({
			type : 'post',
			url : '${ctx}/tsysUserinfoController.do?method=queryRoleTree',
			data : {
				userId : uid
			},
			dataType : 'json',
			contentType : "application/x-www-form-urlencoded;charset=utf-8",
			timeout : 7000,
			error : function() {
				alert('系统繁忙,请稍后再试');
			},
			success : function(data) {
				treeNodes = data;
				$.fn.zTree.init($("#roleinfo_tree"), setting, treeNodes);
				zTree = $.fn.zTree.getZTreeObj("roleinfo_tree");
				myExpandNode(zTree, true);
			}
		});
	}

	/*
	 * 展开根节点
	 */
	function myExpandNode(tree, flag) {
		var nodes = tree.getNodes();
		if(nodes.length == 1){
			tree.expandNode(nodes[0], true,false ,true,false);
		}
	}

	//点击树 加载对应节点下的用户列表
	var tree_id = "";
	function treeClick(e, treeId, treeNode) {
		tree_id = treeNode.id;
		load_userinfo_tree(tree_id);
	}

	/*保存操作*/
	function save_click(myWindow, query) {
		var treeObj = $.fn.zTree.getZTreeObj("roleinfo_tree");
		var selectNodes = treeObj.getCheckedNodes(true);
		if (selectNodes.length == 0) {
			parent.jQuery.messager.alert('提示:', '至少选择一种角色!', 'info');
			return;
		}
		var userObj = $.fn.zTree.getZTreeObj("userinfo_tree");
		if (!userObj || typeof (userObj) == "undefined") {
			parent.jQuery.messager.alert('提示:', '点击机构树加载用户列表,若机构下无用户,请先添加用户！',
					'info');
			return;
		}
		var selectUserNodes = userObj.getCheckedNodes(true);
		if (selectUserNodes.length == 0) {
			parent.jQuery.messager.alert('提示:', '至少选择一名用户!', 'info');
			return;
		}
		var roleIDs = "";
		for (var i = 0; i < selectNodes.length; i++) {
			if (i == (selectNodes.length - 1)) {
				roleIDs += selectNodes[i].id;
			} else {
				roleIDs += selectNodes[i].id + ",";
			}
		}
		var userIDs = "";
		for (var i = 0; i < selectUserNodes.length; i++) {
			if (i == (selectUserNodes.length - 1)) {
				userIDs += selectUserNodes[i].id;
			} else {
				userIDs += selectUserNodes[i].id + ",";
			}
		}
		$.ajax({
			url : '${ctx}/tsysUserinfoController.do?method=saveUserRoles',
			data : {
				userIDs : userIDs,
				roldIDs : roleIDs
			},
			type : "POST",
			dataType : 'json',
			error : function() {
				alert("发生错误!");
			},
			success : function(data) {
				if (data.success) {
					parent.jQuery.messager.alert('提示:', data.msg, 'info',
							function() {
								query();
							});
					myWindow.dialog("close");
				} else {
					parent.jQuery.messager.alert('提示:', data.msg, 'info');
				}
			}
		});
	}

	/*返回列表*/
	function back_click(myWindow) {
		myWindow.dialog("close");
	}

	//加载角色树
	function load_userinfo_tree(userOrgId) {
		var setting = {
			check : {
				enable : true,
				chkStyle : "checkbox"
			},
			edit : {
				enable : false,
				showRemoveBtn : false,
				showRenameBtn : false
			},
			view : {
				dblClickExpand : false
			},
			data : {
				simpleData : {
					enable : true,
					idKey : "id"
				}
			},
			callback : {
				onClick : function() {
				}
			}
		};
		//获取用户角色树
		$.ajax({
			type : 'post',
			url : '${ctx}/tsysUserinfoController.do?method=queryUserTree',
			data : {
				orgId : userOrgId
			},
			dataType : 'json',
			contentType : "application/x-www-form-urlencoded;charset=utf-8",
			timeout : 7000,
			error : function() {
				alert('系统繁忙,请稍后再试');
			},
			success : function(data) {
				userTreeNodes = data;
				$.fn.zTree.init($("#userinfo_tree"), setting, userTreeNodes);
				userzTree = $.fn.zTree.getZTreeObj("userinfo_tree");
				myExpandNode(userzTree, true);
				var nodes = userzTree.getNodes();
				showCheckAllBtn(nodes.length);
			}
		});
	}
	/*显示用户树下的全选 取消 按钮*/
	function showCheckAllBtn(l){
		if(l==0){
			$("#checkAllTrue").hide();
			$("#checkAllFalse").hide();
		}else{
			$("#checkAllTrue").show();
			$("#checkAllFalse").show();
		}
	}
	/*全选 取消全选*/
	function checkNode(e) {
		var zTree = $.fn.zTree.getZTreeObj("userinfo_tree"),
		type = e.data.type,
		nodes = zTree.getSelectedNodes();
		if (type.indexOf("All")<0 && nodes.length == 0) {
			alert("请先选择一个节点");
		}
		if (type == "checkAllTrue") {
			zTree.checkAllNodes(true);
		} else if (type == "checkAllFalse") {
			zTree.checkAllNodes(false);
		}
	}

</script>
</head>
<body>
	<div style="position: relative;" id="container" fit="true"
		class="easyui-layout">
		<div region="center" border="false">
			<div id="query_area">
				<!-- 表单布局设计begin -->
				<cbitedu:form bean="tsysUserinfo" scope="request">
					<input type="hidden" name="userId" id="userId" />
					<table class="FormView" border="0" cellspacing="1" cellpadding="0">
						<col class="Data" />
						<col class="Data" />
						<col class="Data" />
						<tr>
							<td width="45%">用户机构</td>
							<td width="25%">待选人员</td>
							<td width="30%">角色选择</td>
						</tr>
				<tr>
							<td valign="top">
					<div id="div2" style="overflow-y:scroll">
						<ul id="orginfo_tree" class="ztree" style="border: 1px;width:120px;"></ul>
					</div>
			    </td>	
				<td valign="top">
					<a id="checkAllTrue" href="#" class="easyui-linkbutton"  onclick="return false;" style="display:none;">全选</a>&nbsp;&nbsp;&nbsp;&nbsp; 
					<a id="checkAllFalse" href="#" class="easyui-linkbutton" onclick="return false;" style="display:none;">取消</a>
					<ul id="userinfo_tree" class="ztree"
									style="border: 0px;width:150px;"></ul>
				</td>
				<td valign="top">				
					<ul id="roleinfo_tree" class="ztree"
									style="border: 0px;width:150px;"></ul>
				</td>
			</tr>	
		</table>
<!-- 表单布局设计end -->
<!-- 表单内的按钮设计begin -->
	<div align="center" class="foot-buttons" style="margin-bottom: 20px">
        <a id="btn_save" class="easyui-linkbutton" iconCls="icon-save"><fmt:message
								key="button.save" /></a>
        <a id="btn_back" class="easyui-linkbutton" iconCls="icon-back"><fmt:message
								key="button.back" /></a>
    </div>
</cbitedu:form>
</div>
</div>
</div>
</body>
</html>