<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<%@ include file="/WEB-INF/jsp/common/title.jsp"%>
<script type="text/javascript" src="${ctx}/js/jquery.form.js"></script>
<script type="text/javascript">
	$(function() {
		$.parser.parse();
		/* var option = "${option}";
		  if(option == "update"){
			$("#displaySort").attr("disabled", "false");
		} */
	});
	function save_click() {
		var bool = $("#tsysDictForm").validationEngine('validate');
		if (bool == true) {
			$.ajax({
				url : "${ctx}/tsysDictController.do?method=ajaxSave",
				type : "POST",
				data : $("#tsysDictForm").serialize(),
				dataType : 'json',
				error : function() {
					alert("发生错误!");
				},
				success : function(data) {
					if (data.flag) {
						jQuery.messager.alert('提示:', data.msg, 'info',
								function() {
									reloadlist();
								});
						$("#win").window("close");
					} else {
						jQuery.messager.alert('提示:', data.msg, 'info');
					}
				}
			});
		} else {
			jQuery.messager.alert('提示:', '您的表单输入校验不通过，请重填!', 'info');
		}
	}

	function back_click() {
		$("#win").window("close");
	}
</script>
<!-- 表单布局设计begin -->
<cbitedu:form bean="tsysDict" scope="request">
	<!-- 更新时，对属性进行赋值，标签的作用是：添加value属性，并未这个属性赋值  -->
	<form action="${ctx}/tsysDictController.do?method=ajaxSave"
		method="post" name="tsysDictForm" id="tsysDictForm">
		<input type="hidden" id="dictId" name="dictId" />
		<input type="hidden" id="parentType" name="parentType" />
		
		<!-- 取得父类的id -->
		<div>
			<table class="FormView" border="0" cellspacing="1" cellpadding="0">
				<col class="Label" />
				<col class="Data" />
				<tr>
					<td><fmt:message key="tsysdict.dictType" />
						<!-- 字典分类 --></td>
					<td><input type="text" id="dictType" name="dictType"
						class="text validate[required,maxSize[10]]" /> <font color="red">*</font>
					</td>
				</tr>
				<tr>
					<td><fmt:message key="tsysdict.dictName" />
						<!-- 字典名称 --></td>
					<td><input type="text" id="dictName" name="dictName"
						class="text validate[required,maxSize[30]]" /> <font color="red">*</font>
					</td>
				</tr>
				<tr>
					<td><fmt:message key="tsysdict.displaySort" />
						<!-- 字典展现分类 --></td>
					<td><cbitedu:para id="displaySort" name="dictDisplaySort"
							cssClass="select" withEmpty="fasle" /> <font color="red">*</font>
					</td>
				</tr>
		<tr>
					<td><fmt:message key="tsysdict.multiType" />
						<!-- 单选或多选 --></td>
					<td><cbitedu:para id="multiType" name="dictMultiType"
							cssClass="select" withEmpty="true" /></td>
				</tr>
						<tr>
			<td>默认值 <!-- 字典描述 --></td>
					<td><select id="isdefault" name="isdefault" class="select">
							<option value="1">是</option>
							<option value="0">否</option>
					</select></td>
				</tr>
				<tr>
					<td><fmt:message key="tsysdict.remark" />
						<!-- 字典描述 --></td>
					<td><input type="text" id="remark" name="remark"
						class="text validate[maxSize[10]]" /></td>
				</tr>

			</table>
		</div>
	</form>
	<!-- 表单布局设计end -->
	<!-- 表单内的按钮设计begin -->
	<div align="center" class="foot-buttons" style="margin-top: 5px">
		<a id="btn_save" onclick="save_click();" class="easyui-linkbutton"
			iconCls="icon-save"><fmt:message key="button.save" /></a> <a
			id="btn_save" onclick="back_click();" class="easyui-linkbutton"
			iconCls="icon-back"><fmt:message key="button.back" /></a>
	</div>
</cbitedu:form>

