<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<%@ include file="/WEB-INF/jsp/common/meta.jsp"%>
<%@ include file="/WEB-INF/jsp/common/title.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="${ctx}/ui_widget/ztree/css/demo.css">
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
	zNodes = ${zNodes};
	$(function() {
		$.fn.zTree.init($("#treeDemo"), setting, zNodes);
		var treeObj = $.fn.zTree.getZTreeObj("treeDemo");
		treeObj.expandAll(true);
		$.parser.parse();
	});

	function onClick(e, treeId, treeNode) {
		$("#parentPostid").val(treeNode.id);
		$("#parentPostName").val(treeNode.name);
		$("#menuContent").fadeOut("fast");
	}

	function showMenu() {
		var cityObj = $("#parentPostName");
		var cityOffset = $("#parentPostName").offset();
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
	function save_click() {
		$("#tsysPostForm").validationEngine({ promptPosition:"bottomRight"});
		var bool = $("#tsysPostForm").validationEngine('validate');
		if (bool == true) {
			$.ajax({
				url : "${ctx}/tsysPostController.do?method=ajaxSave",
				type : "POST",
				data : $("#tsysPostForm").serialize(),
				dataType : 'json',
				error : function() {
					jQuery.messager.alert('提示:','发生错误!', '');
				},
				success : function(data) {
					if (data.flag) {
						jQuery.messager.alert('提示:', data.msg, 'info', function(){
							reloadList();
							back_click();
						});
					} else {
						jQuery.messager.alert('提示:', data.msg, 'info');
					}
				}
			});
		} else {
			jQuery.messager.alert('提示:', '您的表单输入校验不通过，请重填!', 'info');
		}
	}
	
	function reloadList(){
		parent.reloadlist();
	}
	function back_click() {
		parent.closeDialog();
	}
	
	function clear(){
/* 		$("#postName").val("");
		$("#remark").val("");
		$("#postType").val("4"); // 把岗位类型重置成下来菜单的第一个
 */		$("#parentPostName").val("");
		$("#parentPostid").val("");
	}
	
</script>
</head>
<body>
	<!-- 表单布局设计begin -->
	<cbitedu:form bean="tsysPost" scope="request">
		<form action="${ctx}/tsysPostController.do?method=ajaxSave" method="post" name="tsysPostForm" id="tsysPostForm">
			<input type="hidden" name="postId" />
			<div class="easyui-layout easyui-panel">
				<table class="FormView" border="0" cellspacing="1" cellpadding="0">
					<col class="Label" />
					<col class="Data" />
					<tr>
						<td><fmt:message key="tsyspost.postName" /></td>
						<td>
							<input type="text" id="postName" name="postName" class="text validate[required,maxSize[10]]" />
							<font color="red">*</font>
						</td>
					</tr>
					<tr>
						<td><fmt:message key="tsyspost.remark" /></td>
						<td>
							<input type="text" id="remark" name="remark" class="text validate[required,maxSize[15]]" />
							<font color="red">*</font>
						</td>
					</tr>
					<tr>
						<td><fmt:message key="tsyspost.postType" /></td>
						<td>
							<cbitedu:para name="postType" id="postType" cssClass="select" />
							<font color="red">*</font>
						</td>
					</tr>
					<tr>
						<td><fmt:message key="tsyspost.parentPostid" /></td>
						<td>
							<input type="hidden" id="parentPostid" name="parentPostid"  />
							<input type="text" id="parentPostName" name="parentPostName" readonly="readonly" class="text" />&nbsp;
							<a id="menuBtn" href="javascript:void(0)" onclick="showMenu(); return false;">选择</a>
							<a id="menuBtn" href="javascript:void(0)" onclick="clear()">清空</a>
							<!-- <font color="red">*</font> -->
						</td>
					</tr>
				</table>
			</div>
		</form>
		<!-- 表单布局设计end -->
		<!-- 表单内的按钮设计begin -->
		<div align="center" class="foot-buttons" style="margin-top: 5px">
			<a id="btn_save" onclick="save_click();" class="easyui-linkbutton" iconCls="icon-save">
				<fmt:message key="button.save" />
			</a>
			<a id="btn_save" onclick="back_click();" class="easyui-linkbutton" iconCls="icon-back">
				<fmt:message key="button.back" />
			</a>
		</div>
	</cbitedu:form>
	<div id="menuContent" class="menuContent" style="display: none; position: absolute;">
		<ul id="treeDemo" class="ztree" style="margin-top: 0; height : 140px; width: 160px; " ></ul>
	</div>
</body>
</html>