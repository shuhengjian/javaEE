<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<ul id="module_tree" class="ztree"></ul>
<script type="text/javascript">
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
		url : '${ctx}/index.do?method=powerData',
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
</script>

