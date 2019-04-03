<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<%@ include file="/WEB-INF/jsp/common/title.jsp"%>
<script type="text/javascript" src="${ctx}/js/jquery.form.js"></script>
<script type="text/javascript">
	$(function(){
		$.parser.parse();
		$("#dictType").attr("readonly", "readonly");
		$("#dictName").attr("readonly", "readonly");
	});

	function save_click() {
		var bool = $("#tsysDictForm").validationEngine('validate');
		if (bool == true) {
			$.ajax({
				url : "${ctx}/tsysDictController.do?method=ajaxSaveData",
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
	
	function back_click(){
		$("#win").window("close");
		reloadlist();
	}
</script>
<!-- 表单布局设计begin -->
<cbitedu:form bean="tsysDict" scope="request"> <!-- 更新时，对属性进行赋值，标签的作用是：添加value属性，并未这个属性赋值  -->
<form action="${ctx}/tsysDictController.do?method=ajaxSave" method="post" name="tsysDictForm" id="tsysDictForm">
	<input type="hidden" name= "dictId" id="dictId" value="${tsysDict.dictId }"/>
	<input type="hidden" name= "parentType" id="parentType" value="${tsysDict.parentType }"/>
	<input type="hidden" name= "displaySort" id="displaySort" value="${tsysDict.displaySort }" />
	<div>
		<table class="FormView" border="0" cellspacing="1" cellpadding="0">
			<col class="Label" />
			<col class="Data" />
			<tr>
				<td>
					<fmt:message key="tsysdict.dictType" /><!-- 字典分类 -->
				</td>
				<td>
					<input type="text" id="dictType" name="dictType" class="text validate[required,maxSize[10]]" />
					<font color="red">*</font>
				</td>
			</tr>
			<tr>
				<td>
					<fmt:message key="tsysdict.dictName" /><!-- 字典名称 -->
				</td>
				<td>
					<input type="text" id="dictName" name="dictName" class="text validate[required,maxSize[10]]" />
					<font color="red">*</font>
				</td>
			</tr>
			<tr>
				<td>
					<fmt:message key="tsysdict.dictValue" /><!-- 字典值 -->
				</td>
				<td>
					<input type="text" id="dictValue" name="dictValue" class="text validate[required,maxSize[10]]" />
					<font color="red">*</font>
				</td>
			</tr>
			<tr>
				<td>
					<fmt:message key="tsysdict.dictCode" /><!-- 字典编码 -->
				</td>
				<td>
					<input type="text" id="dictCode" name="dictCode" class="text validate[required,maxSize[10]]" />
					<font color="red">*</font>
				</td>
			</tr>
			<tr>
				<td>
					<fmt:message key="tsysdict.remark" /><!-- 字典描述 -->
				</td>
				<td>
					<input type="text" id="remark" name="remark" class="text validate[maxSize[10]]" />
				</td>
			</tr>
			<tr>
				<td>
					<fmt:message key="tsysdict.sortNum" /><!-- 排序号 -->
				</td>
				<td>
					<input type="text" id="sortNum" name="sortNum" class="text validate[maxSize[10]]" />
				</td>
			</tr>
			<tr>
				<td>
					<fmt:message key="tsysdict.dictClass" /><!-- 级数 -->
				</td>
				<td>
					<input type="text" id="dictClass" name="dictClass" class="text validate[maxSize[10]]" />
				</td>
			</tr>
		<tr>
			<td>默认值 <!-- 字典描述 --></td>
					<td><select id="isdefault" name="isdefault" class="select">
							<option value="1">是</option>
							<option value="0">否</option>
					</select></td>
				</tr>
			<tr>
				<td>
					<fmt:message key="tsysdict.orgId" /><!-- 所属机构 -->
				</td>
				<td>
					<input type="text" id="orgId" name="orgId" class="text validate[maxSize[10]]" />
				</td>
			</tr>
		</table>
	</div>
</form>
<!-- 表单布局设计end -->
<!-- 表单内的按钮设计begin -->
<div align="center" class="foot-buttons" style="margin-top: 5px">
	<a id="btn_save" onclick="save_click();" class="easyui-linkbutton" iconCls="icon-save"><fmt:message key="button.save" /></a>
	<a id="btn_save" onclick="back_click();" class="easyui-linkbutton" iconCls="icon-back"><fmt:message key="button.back" /></a>
</div>
</cbitedu:form>
