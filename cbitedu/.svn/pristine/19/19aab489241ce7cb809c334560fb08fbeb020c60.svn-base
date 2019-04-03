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
		$("#ttyTypeForm").validationEngine({ promptPosition:"bottomRight"});
		var bool = $("#ttyTypeForm").validationEngine('validate');
		let formData = new FormData($("#ttyTypeForm")[0]);
		if (bool == true) {
			$.ajax({
				url : "${ctx}/ttyBackTypeController.do?method=ajaxSave",
				type : "POST",
				data :  formData,
				//data : $("#ttytypeForm").serialize(),
				dataType : 'json',
/* 				async: false,  
	          	cache: false,*/
	          	processData:false,
	          	contentType: false,  
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
	$(function(){
		let fileUrl = $("#ttyTypeForm").data("img");
		if(fileUrl != null && fileUrl != '' && fileUrl != undefined){
			$("img").attr("src","/framework/type/"+ fileUrl);
		}
		$("input[type='file']").change(function (){
			let imgSrc;
			let filePath = this.files[0];
			let reader = new FileReader();
			reader.readAsDataURL(filePath);
	        reader.onload = function() {
                imgSrc = this.result;
                $("img").attr("src", imgSrc);
            };
		})
	})
</script>
</head>
<body>
	<!-- 表单布局设计begin -->
	<cbitedu:form bean="ttyType" scope="request">
		<form action="${ctx}/ttyBackTypeController.do?method=ajaxSave" method="post" name="ttyTypeForm" id="ttyTypeForm" >
			<input type="hidden" name="typeId" />
			<div class="easyui-layout easyui-panel">
				<table class="FormView" border="0" cellspacing="1" cellpadding="0">
					<col class="Label" />
					<col class="Data" />
					<tr>
						<td><fmt:message key="type.typeName" /></td>
						<td>
							<input type="text" id="typeName" name="typeName" class="text validate[required,maxSize[10]]" />
							<font color="red">*</font>
						</td>
					</tr>
					<tr>
						<td><fmt:message key="type.typeStart" /></td>
						<td>
							<cbitedu:para style="width:159px" name="typeStart" id="typeStart" cssClass="select" />
							<font color="red">*</font>
						</td>
					</tr>
					<tr>
						<td><fmt:message key="type.typeState" /></td>
						<td>
							<cbitedu:para style="width:159px" name="brand_state" id="typeState" cssClass="select" />
							<font color="red">*</font>
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