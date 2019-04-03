<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<%@ include file="/WEB-INF/jsp/common/meta.jsp"%>
<%@ include file="/WEB-INF/jsp/common/title.jsp"%>
</head>
<script type="text/javascript">
	var nodeId;
	var dict_name;
	$(function() {
		load_tree();
		var win = $("#win").window({ // 通过javascript创建窗口
			title : "数据字典",
			modal : true,
			closed : true,
			width : 310,
			height : 280
		});
		var handler = function() {
			$("input[type='button']").click(function(){
				win.window("close");
			});
		};

		//新增
		$("#btn_add").click(
			function() {
				win.window('open');
				$("#msg").load("${ctx}/tsysDictController.do?method=add", { "tree_id" : "0" }, handler);
			});

		$("#btn_edit").click(function() { // 修改
			if(typeof(nodeId) == 'undefined'){
				jQuery.messager.alert('提示:', '请选择需要修改的节点！', 'info');
				return;
			}else{
				win.window('open');
				$("#msg").load("${ctx}/tsysDictController.do?method=modify", { "id" : nodeId }, handler);
			}
		});

		$("#btn_del").click(function() { // 删除
			if(typeof(nodeId) == 'undefined'){
				jQuery.messager.alert('提示:', '请选择需要删除的节点！', 'info');
				return;
			}else{
				jQuery.messager.confirm('提示:','你确认要删除吗?',function(event){
					if(event){
						$.ajax({
							url: "${ctx}/tsysDictController.do?method=del",
							data:{ dictIds : nodeId},
							type: 'post',
							dataType: 'json',					
							success:function(data){
								if(data.flag){
									jQuery.messager.alert('提示:','删除成功！', '', function(){
										reloadlist(); // 只刷新列表就可以了
									});
								}else{
									jQuery.messager.alert('提示:','删除失败！', 'warning');
								}
							}
						});
					}
				});
			}
		});
		$("#btn_query").click(function() { // 查询
			dict_name = $("#dictName").val();
			load_tree();
		});

	});
	function load_tree() {
		var setting = {
			check : {
				enable : false,
				chkboxType : {"Y" : "", "N" : ""}
			},
			edit : {
				enable : true,
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
				onClick : treeClick
			}
		};

		//获取数据
		$.ajax({
			type : 'post',
			url : '${ctx}/tsysDictController.do?method=initTree',
			data : { 'dict_dictName':dict_name, 'dict_parentType' : '0'},
			dataType : 'json',
			contentType : "application/x-www-form-urlencoded; charset=utf-8",
			error : function() {
				jQuery.messager.alert('提示:', '系统繁忙,请稍后再试!', 'warning');
			},
			success : function(data) {
				treeNodes = data;
				$.fn.zTree.init($("#dictionary_tree"), setting, treeNodes);
				var zTree = $.fn.zTree.getZTreeObj("dictionary_tree");
				zTree.expandAll(true); // 展开所有节点
			}
		});
	}

	function treeClick(event, treeId, treeNode) {
		nodeId = treeNode.id;
		//1列表 2树复选 3树单选
		var url = "";
		if (treeNode.type == 1) {
			url = "${ctx}/tsysDictController.do?method=dictlist&tree_id="+treeNode.id+"&type="+treeNode.type;
		} else {
			url = "${ctx}/tsysDictController.do?method=dicttree&tree_id="+treeNode.id+"&type="+treeNode.type;
		}
		$("#centerIframe").attr("src", url); 
	}

	function reloadlist() {
		window.location.reload();
	}
</script>
<body class="easyui-layout">
	<div data-options="region:'west',split:true,title:' '" style="width:300px;">
		<div id="query_area">
			<!-- 按钮栏布局end -->
			<!-- 查询条件区域begin-->
			<table class="FormView" border="0" cellspacing="1" cellpadding="0">
				<col class="Label" />
				<col class="Data" />
				<tr>
					<td align="left"><!-- 字典名称 -->
						<fmt:message key="tsysdict.dictName" />
					</td>
					<td>
						<input type="text" name="dictName" id="dictName" class="text">
					</td>
				</tr>
			</table>
		</div>
		<div id="toolbar_area" border="false">
			<a class="easyui-linkbutton" iconCls="icon-add" plain="true" id="btn_add">
				<fmt:message key="button.add" />  <!-- 新增按钮 -->
			</a>
			<a class="easyui-linkbutton" iconCls="icon-edit" plain="true" id="btn_edit">
				<fmt:message key="button.edit" /> <!-- 编辑按钮 -->
			</a>
			<a class="easyui-linkbutton" iconCls="icon-remove" plain="true" id="btn_del">
				<fmt:message key="button.remove" /><!-- 删除按钮 -->
			</a>
			<a class="easyui-linkbutton" iconCls="icon-query" plain="true" id="btn_query">
				<fmt:message key="button.query" /> <!-- 查询按钮 -->
			</a>
		</div>
		<div class="zTreeDemoBackground left">
			<ul id="dictionary_tree" class="ztree"></ul>
		</div>
	</div>
	<div data-options="region:'center',title:' '">
		<iframe id="centerIframe" name="centerIframe" src="" style="width: 100%;height: 100%" frameBorder="no" scrolling="yes"></iframe>
	</div>
	<div id="win">
		<div id="msg"></div>
	</div>
</body>
</html>
