<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<%@ include file="/WEB-INF/jsp/common/meta.jsp"%>
<%@ include file="/WEB-INF/jsp/common/title.jsp"%>
</head>
<script type="text/javascript">

	var tree_id;  // 定义个tree_id用来保存，选中节点的id
	var setting = {
		view : {
			selectedMulti : false //是否允许同时选中多个节点
		},
		async : {
			enable : true,
			url : "${ctx}/tsysDictController.do?method=asyncDictTreeData&type=${tree_id}",
			autoParam : [ "id" ] //自动提交的父节点的属性
		},
		callback : {
			onAsyncError : onAsyncError, 
			beforeExpand : beforeExpand, 
			onClick : callback
		}
	};
	//用于捕获父节点展开之前的事件回调函数
	function beforeExpand(treeId, treeNode) {

		if (!treeNode.isParent) {
			jQuery.messager.alert('提示:', '请选择父节点', '');
			return false;
		} else {
			return true;
		}
	}
	// 用于捕获异步加载出现异常错误的事件
	function onAsyncError(event, treeId, treeNode, XMLHttpRequest, textStatus,
			errorThrown) {
		jQuery.messager.alert('提示:', '异步获取数据出现异常', 'error');
	}
	//选中节点的点击事件。
	function callback(event, treeId, treeNode) {
		tree_id = treeNode.id;
	}

	$(document).ready(function() {
		$.fn.zTree.init($("#dictionary_tree"), setting);

		var win = $("#win").window({ // 通过javascript创建窗口
			title : "数据字典",
			modal : true,
			closed : true,
			width : 300,
			height : 330
		});

		var handler = function() { // 回调函数
			$("input[type='button']").click(function() {
				win.window("close");
			});
		};

		// 新增的方法
		$("#btn_add").click(function() {
			var zTree = $.fn.zTree.getZTreeObj("dictionary_tree");
			var nodes = zTree.getNodes();
			if (typeof (nodes[0]) != 'undefined') {
				if (typeof (tree_id) == 'undefined') {
					win.window('open');
					$("#msg").load("${ctx}/tsysDictController.do?method=addtree",
						{
							"tree_id" : "${tree_id}",
							"type" : '2'
						}, handler);
				} else {
					win.window('open');
					$("#msg").load("${ctx}/tsysDictController.do?method=addtree",
						{
							"tree_id" : tree_id,
							"type" : '2'
						}, handler);
				}
			} else {
				win.window('open');
				$("#msg").load("${ctx}/tsysDictController.do?method=addtree",
					{
						"tree_id" : "${tree_id}",
						"type" : '2'
					}, handler);
			}
		});

		// 修改节点的方法
		$("#btn_edit").click(function() {
			if (typeof (tree_id) == 'undefined') {
				jQuery.messager.alert('提示:', '请选择需要修改的节点！', 'info');
				return;
			} else {
				win.window('open');
				$("#msg").load("${ctx}/tsysDictController.do?method=modifytree",
					{
						"id" : tree_id
					}, handler);
			}
		});

		// 删除节点方法
		$("#btn_del").click(function() {
			if (typeof (tree_id) == 'undefined') {
				jQuery.messager.alert('提示:', '请选择需要删除的节点！', 'info');
				return;
			} else {
				jQuery.messager.confirm('提示:','你确认要删除吗?',function(event) {
					if (event) {
						$.ajax({url : "${ctx}/tsysDictController.do?method=hasChildren",
								data : {
									dict_parentType : tree_id
								},
								type : 'post',
								async : false,
								dataType : 'json',
								success : function(data) {
									if (data.flag) {
										$.ajax({url : "${ctx}/tsysDictController.do?method=del",
												data : {
												 	dictIds : tree_id
												},
												type : 'post',
												dataType : 'json',
												success : function(data) {
													if (data.flag) {
														jQuery.messager.alert('提示:', '删除成功！', '', function() {
															reloadlist(); // 只刷新列表就可以了
															});
													} else {
														jQuery.messager.alert('提示:', '删除失败！', 'warning');
													}
												}
										});
									} else {
										jQuery.messager.alert('提示:', '该节点存在子节点，禁止删除！', 'warning');
									}
								}
						});
					}
				});
			}
		});
	});

	// 刷新列表 
	function reloadlist() {
		window.location.reload();
	}
</script>
<body class="easyui-layout">
	<div data-options="region:'west'">
		<div id="toolbar_area" border="false">
			<a class="easyui-linkbutton" iconCls="icon-add" plain="true" id="btn_add">
				<fmt:message key="button.add" /> <!-- 新增按钮 -->
			</a>
			<a class="easyui-linkbutton" iconCls="icon-edit" plain="true" id="btn_edit">
				<fmt:message key="button.edit" /> <!-- 编辑按钮 -->
			</a>
			<a class="easyui-linkbutton" iconCls="icon-remove" plain="true" id="btn_del">
				<fmt:message key="button.remove" /> <!-- 删除按钮 -->
			</a>
		</div>
		<div class="zTreeDemoBackground left">
			<ul id="dictionary_tree" class="ztree"></ul>
		</div>
	</div>
	<div id="win">
		<div id="msg"></div>
	</div>
</body>
</html>
