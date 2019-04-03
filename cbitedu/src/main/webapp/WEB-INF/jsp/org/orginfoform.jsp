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
	var op = '${operator}';
	$(function() {
		
		$.parser.parse();
		init_operator();
		//$('#remark').autosize();
		$.validationEngine.defaults.promptPosition = "centerRight";
		$("#formID").validationEngine({
			showOnMouseOver : true
		});

	});
	function init_operator() {
		if (op == "select") {
			$("#btndiv").hide();
			$("#orgCode").attr("readonly", "readonly");
			$("#orgName").attr("readonly", "readonly");
			$("#state").attr("disabled", "disabled");
		} else if (op == "saveOrg") {

			$("#state").attr("disabled", "disabled");

		} else if (op == "modify") {
			if ('${tsysOrg.state}' == 1) {
				$("#state").attr("disabled", "disabled");
			}
		}

	}
	function save_click() {
		var bool = $("#formID").validationEngine('validate');
		if (bool == true) {
			$("#formID").ajaxSubmit(function(data) { // 异步提交表单数据
				if (data.success) {
					jQuery.messager.alert('提示:', data.msg, 'info', function() {
						$("#btndiv").hide();
						window.parent.reloadlist();
					});

				} else {
					jQuery.messager.alert('提示:', data.msg, 'info');
				}
			});
		} else {
			jQuery.messager.alert('提示:', '您的表单输入校验不通过，请重填!', 'info');
		}
	}
</script>
</head>
<body>
 
	<cbitedu:form bean="orginfo" scope="request">
		<div style="position: relative;" id="container" fit="true"
			class="easyui-layout">
			<div region="center" border="false" style="position: relative;"
				fit="true">
				<form action="${ctx}/tsysOrgController.do?method=${operator}"
					method="post" name="orginfoForm" id="formID">
					<input type="hidden" name=parentId id="parentId" value="${id}" />
					<input type="hidden" name=orgId id="orgId" value="${tsysOrg.orgId}" />
					<input type="hidden" name=state id="state" value="1" />
					<table class="FormView" border="0" cellspacing="1" cellpadding="0">
						<col class="Label" />
						<col class="Data" />
						<col class="Label" />
						<col class="Data" />
						<tr>
							<td><fmt:message key="org.orgCode" />
								<!-- 机构编号 --></td>
							<td><input type="text" id="orgCode" name="orgCode"
								class="text validate[required]" value="${tsysOrg.orgCode}" maxlength="20" /> <font
								color="red">*</font></td>
							<td><fmt:message key="org.orgName" />
								<!-- 机构名称 --></td>
							<td><input type="text" id="orgName" name="orgName"
								class="text validate[required]" value="${tsysOrg.orgName}" maxlength="20" /> <font
								color="red">*</font></td>
						</tr>
						<!--  
						<tr>
							<td><fmt:message key="org.orgSn" /></td>
				            <td><input type="text" id="orgSn" name="orgSn" class="text" value="${tsysOrg.orgSn}"/></td>
								
						</tr>
						-->
						<tr>
							<td><fmt:message key="org.email" />
								<!-- 电子邮箱 --></td>
							<td><input type="text" id="email" name="email" class="text"
								value="${tsysOrg.email}" /></td>
							<td><fmt:message key="org.leader" />
								<!-- 机构负责人--></td>
							<td><input type="text" id="leader" name="leader"
								class="text" value="${tsysOrg.leader}" maxlength="12" /></td>
						</tr>
						<tr>
							<td><fmt:message key="org.phone" />
								<!-- 办公电话 --></td>
							<td><input type="text" id="phone" name="phone" class="text"
								value="${tsysOrg.phone}" maxlength="12" /></td>
							<td><fmt:message key="org.zip" />
								<!-- 邮政编码 --></td>
							<td><input type="text" id="zip" name="zip" class="text"
								value="${tsysOrg.zip}" /></td>
						</tr>
						<tr>
							<td><fmt:message key="org.fax" />
								<!-- 传真号码--></td>
							<td><input type="text" id="fax" name="fax" class="text"
								value="${tsysOrg.fax}" maxlength="12" /></td>
							<td><fmt:message key="org.orgType" />
								<!-- 机构类型 --></td>
							<td><cbitedu:para id="orgType" name="orgType" cssClass="select"
									defValue="${tsysOrg.orgType}" /></td>

						</tr>
						<tr>
							<td><fmt:message key="org.mobile" />
								<!-- 负责人手机号码 --></td>
							<td><input type="text" id="mobile" name="mobile"
								class="text validate[maxSize[11]]" value="${tsysOrg.mobile}" maxlength="11" /></td>
							<td><fmt:message key="org.addr" />
								<!-- 通讯地址  --></td>
							<td><input type="text" id="addr" name="addr" class="text"
								value="${tsysOrg.addr}" /></td>
						</tr>
						<tr>
							<td><fmt:message key="org.state" /></td>
							<td><cbitedu:para id="state" name="state" cssClass="select"
									defValue="${tsysOrg.state}" /></td>
							<td><fmt:message key="org.remark" />
								<!-- 单位简介  --></td>
							<td><textarea id="remark" name="remark" class="textarea">${tsysOrg.remark}</textarea>
							</td>
						</tr>
					</table>
					<div id="btndiv" align="center" class="foot-buttons"
						style="margin-top: 5px">
						<a id="btn_save" onclick="save_click();" class="easyui-linkbutton"
							iconCls="icon-save"><fmt:message key="button.save" /></a>
						<!-- 
			<a id="btn_save" onclick="back_click();" class="easyui-linkbutton" iconCls="icon-back"><fmt:message key="button.back" /></a>
			 -->
					</div>
				</form>
			</div>
		</div>
	</cbitedu:form>
</body>
</html>