<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<%@ include file="/WEB-INF/jsp/common/title.jsp"%>
<script type="text/javascript" src="${ctx}/js/jquery.form.js"></script>
<script type="text/javascript">
	$(function(){
		$.parser.parse();
		/* $("#formID").validationEngine({
			showOnMouseOver : true
		}); */
	});
/* 	function save_click(){
		$("#formID").ajaxSubmit(function(data){  // 异步提交表单数据
			if(data.flag){
				jQuery.messager.alert('提示:', data.msg, 'info', function(){
					reloadlist();
				});
				$("#win").window("close");
			}else{
				jQuery.messager.alert('提示:', data.msg,'info');
			}
		});
	} */
	
	function save_click() {
		var bool = $("#tsysParameterForm").validationEngine('validate');
		if (bool == true) {
			$.ajax({
				url : "${ctx}/tsysParameterController.do?method=ajaxSave",
				type : "POST",
				data : $("#tsysParameterForm").serialize(),
				dataType : 'json',
				error : function() {
					jQuery.messager.alert('提示:', '发生错误!', 'error');
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
	}
</script>
<!-- 表单布局设计begin -->
<cbitedu:form bean="tsysParameter" scope="request">
<form action="${ctx}/tsysParameterController.do?method=ajaxSave" method="post" name="tsysParameterForm" id="tsysParameterForm">
	<input type="hidden" name="paraid" />
	<div>
		<table class="FormView" border="0" cellspacing="1" cellpadding="0">
			<col class="Label" />
			<col class="Data"/>
			<tr>
				<td>
					<fmt:message key="tsysparameter.paraname" /><!-- 参数名称 -->
				</td>
				<td>
					<input type="text" id="paraname" name="paraname" class="text validate[required,maxSize[10]]" />
					<font color="red">*</font>
				</td>
			</tr>
			<tr>
				<td>
					<fmt:message key="tsysparameter.parano" /><!-- 参数编码 -->
				</td>
				<td>
					<input type="text" id="parano" name="parano" class="text validate[required,maxSize[15]]" />
					<font color="red">*</font>
				</td>
			</tr>
			<%-- <tr>
				<td>
					<fmt:message key="tsysparameter.paraClass" /> <!-- 参数等级 -->
				</td>
				<td>
					<input type="text" name="paraClass" id="paraClass" class="text validate[required,maxSize[2]]"/>
					<font color="red">*</font>
				</td>
			</tr> --%>
			<tr>
				<td>
					<fmt:message key="tsysparameter.paraKey" /><!-- 字典值 -->
				</td>
				<td>
					<input type="text"  id="paraKey" name="paraKey" class="text validate[required,maxSize[30]]"/>
					<font color="red">*</font>
				</td>
			</tr>
			<tr>
				<td>
					<fmt:message key="tsysparameter.paraType" /><!-- 字典类型 -->
				</td>
				<td>
					<input type="text"  id="paraType" name="paraType" class="text validate[required,maxSize[20]]"/>
					<font color="red">*</font>
				</td>
			</tr>
			<tr>
				<td>
					<fmt:message key="tsysparameter.isdefault" /><!-- 是否默认 -->
				</td>
				<td>
					<input type="text"  id="isdefault" name="isdefault" class="text validate[maxSize[1]]"/>
				</td>
			</tr>
			<tr>
				<td>
					<fmt:message key="tsysparameter.paraorder" /><!-- 排序号 -->
				</td>
				<td>
					<input type="text"  id="paraorder" name="paraorder" class="text validate[maxSize[5]]"/>
				</td>
			</tr>
			<tr>
				<td>
					<fmt:message key="tsysparameter.parentparaid" /><!-- 父类型 -->
				</td>
				<td>
					<input type="text"  id="parentparaid" name="parentparaid" class="text validate[maxSize[10]]"/>
				</td>
			</tr>
			<tr>
				<td>
					<fmt:message key="tsysparameter.displaysort" /><!-- 显示方式 -->
				</td>
				<td>
					<input type="text"  id="displaysort" name="displaysort" class="text validate[maxSize[2]]"/>
				</td>
			</tr>
			<tr>
				<td>
					<fmt:message key="tsysparameter.remark" /><!-- 备注 -->
				</td>
				<td>
					<input type="text"  id="remark" name="remark" class="text validate[maxSize[50]]"/>
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

