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
		//对输入框的限制只能输人数字，最大长度 为7，最大单位为千万，例如1234.56通过12345.6不通过
		var reg = /(^[1-9]([0-9]+)?(\.[0-9]{1,2})?$)|(^(0){1}$)|(^[0-9]\.[0-9]([0-9])?$)/;
		if($("#priceMax").val().replace(/\s+/g,"") == "" && $("#priceMin").val().replace(/\s+/g,"") == ""){
			$.messager.alert('提示：','价格区间最大值和价格区间最小值最少填一项！','info');
			return;
		}else{
			var min;
			var max;
			if($.trim($("#priceMin").val())!='' && $.trim($("#priceMin").val())!=null){
				 min =parseFloat ($("#priceMin").val());
				 var a = $("#priceMin").val().indexOf(".");
				 if(a < 0){
					 var b = $("#priceMin").val() + "."
					 var c = b.indexOf(".");
					 if(c > 4){
						 $.messager.alert('提示：','最小值最大单位为千万','info');
				            return;
					 }
				 }
				 if(a > 4){
					 $.messager.alert('提示：','最小值最大单位为千万','info');
			            return;
				 }
				 if (!reg.test($("#priceMin").val())) {
			        	$.messager.alert('提示：','只能输入数字','info');
			            return;
			     }
			}
			if($.trim($("#priceMax").val())!='' && $.trim($("#priceMax").val())!=null){
				max =parseFloat ($("#priceMax").val());
				var a = $("#priceMax").val().indexOf(".");
				if(a < 0){
					 var b = $("#priceMax").val() + "."
					 var c = b.indexOf(".");
					 if(c > 4){
						 $.messager.alert('提示：','最大值最大单位为千万','info');
				            return;
					 }
				 }
				if(a > 4){
					$.messager.alert('提示：','最大值最大单位为千万','info');
			            return;
				}
				if (!reg.test($("#priceMax").val())) {
		        	$.messager.alert('提示：','只能输入数字','info');
		             return;
		        }
			}
			if(min >= max){
				$.messager.alert('提示：','价格区间最大值必须大于价格区间最小值！','info');
				return;
		}
	}
			
		$("#ttyPriceForm").validationEngine({ promptPosition:"bottomRight"});
		var bool = $("#ttyPriceForm").validationEngine('validate');
		console.log($("#ttyPriceForm").serialize())
		if (bool == true) {
			$.ajax({
				url : "${ctx}/ttyBackPriceController.do?method=ajaxSave",
				type : "POST",
				data :  $("#ttyPriceForm").serialize(),
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
	$(function(){
		let fileUrl = $("#ttyPriceForm").data("img");
		if(fileUrl != null && fileUrl != '' && fileUrl != undefined){
			$("img").attr("src","/framework/price/"+ fileUrl);
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
	<cbitedu:form bean="ttyPrice" scope="request">
		<form action="${ctx}/ttyPriceController.do?method=ajaxSave" method="post" name="ttyPriceForm" id="ttyPriceForm">
			<input type="hidden" name="priceId" />
			<div class="easyui-layout easyui-panel">
				<table class="FormView" border="0" cellspacing="1" cellpadding="0">
					<col class="Label" />
					<col class="Data" />
					<tr>
						<td><fmt:message key="price.priceMin" /></td>
						<td>
							<input type="text" id="priceMin" name="priceMin" maxlength="7" class="text validate[maxSize[7]]" />
							<font color="red"></font>
						</td>
					</tr>
					<tr>
						<td><fmt:message key="price.priceMax" /></td>
						<td>
							<input type="text" id="priceMax" name="priceMax" maxlength="7" class="text validate[maxSize[7]]" />
							<font color="red"></font>
						</td>
						<tr>
						<td><fmt:message key="price.priceState" /></td>
						<td>
							<cbitedu:para style="width:159px" name="brand_state" id="priceState" cssClass="select" />
							<font color="red">*</font>
						</td>
					</tr>
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