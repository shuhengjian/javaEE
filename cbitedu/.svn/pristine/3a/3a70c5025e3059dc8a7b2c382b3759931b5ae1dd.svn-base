<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<%@ include file="/WEB-INF/jsp/common/title.jsp"%>
<script type="text/javascript">
$(function(){
	//验证模块编码是否存在
	var chkModuleCode=true;
	//渲染样式
	$.parser.parse();
	
   $("#btn_save").click(function()
   {
		var bool=$("#tsysModuleForm").validationEngine('validate');
		   if(bool==true && chkModuleCode==true)
		   {
			   $.ajax({
					url: "${ctx}/tsysModuleController.do?method=saveModule&",
					type: "POST",
					data: $("#tsysModuleForm").serialize(),
					dataType: 'json',
					error: function(){
						alert("发生错误!");
					},
					success: function(data){
						if(data == true) 
						{
							jQuery.messager.alert('提示:','保存成功！', 'info', function(){
								 tsysModuleForm.action="${ctx}/tsysModuleController.do?method=list";
								 tsysModuleForm.submit();
							});
							   
						}
						else if(data == false){
							 jQuery.messager.alert('提示:','保存失败!','warning'); 
						}
						
					}
				});   
		
		   }else{jQuery.messager.alert('提示:','您的表单输入校验不通过，请重填!','info');  }
   });
   $("input[name='moduleCode']").blur(function(){
	   var moduleId='${tsysModule.moduleId}';
		var str = $(this).val();
		str = $.trim(str); 
		if(str!=""){
		 $.ajax({
				url: "${ctx}/tsysModuleController.do?method=isHasModuleCode&moduleId="+moduleId+"&moduleCode="+str,
				type: 'post',
				dataType: 'json',
				error: function(){
					alert("发生错误!");
				},
				success: function(data){
					$('#errorCodeName').css('color','red');
					$('#errorCodeName').css('font-size','12px');
					if(data == false) {
							$('#errorCodeName').html("该模块编码已使用！");
							chkModuleCode=false;
						}
					else{
						$('#errorCodeName').html("该模块编码有效！");
						chkModuleCode=true;
					}
					
				}
			}); }else { $('#errorCodeName').html(""); chkModuleCode=false;}
				
		});
	
	//返回
	$("#btn_back").click(function(){
	   $("#win").window("close");
	 });
  });
</script>

<cbitedu:form bean="tsysModule" scope="request">
	<form action="" method="post" name="tsysModuleForm" id="tsysModuleForm" >
		<input type="hidden" name="ismenu" value="${ismenu }">
		<input type="hidden" name="appId" value="${tsysModule.appId }">
		<input type="hidden" name="parentModuleid" value="${tsysModule.parentModuleid }">
		<input type="hidden" name="moduleId" value="${tsysModule.moduleId}">
			<div>
				<table class="FormView" border="0" cellspacing="1" cellpadding="0">
					<col class="Label" />
					<col class="Data" />
				
					<tr>
						<td><fmt:message key="tsysmodule.appId" /></td>
						<td><input type="text" id="appName" name="appName" value="${tsysModule.appName}" readonly="readonly" class="text" />
						</td>
					</tr>
					<tr>
						<td><fmt:message key="tsysmodule.moduleName" /></td>
						<td>
						<input type="text" id="moduleName" name="moduleName" value="${tsysModule.moduleName }"  class="text validate[required,maxSize[25]]" />
						<font color="red">*</font>
						</td>
					</tr>
					<tr>
						<td><fmt:message key="tsysmodule.linkAddr" /></td>
						<td><input type="text" id="linkAddr" name="linkAddr" value="${tsysModule.linkAddr }" 
							class="text validate[required,maxSize[150]]" />
						</td>
					</tr>
					<tr>
						<td><fmt:message key="tsysmodule.moduleClass" /></td>
						<td><input type="text" id="moduleClass" name="moduleClass" value="${tsysModule.moduleClass }"  class="validate[required,maxSize[100]] text" /></td>
					</tr>
					<tr>
						<td><fmt:message key="tsysmodule.icon" /></td>
						<td><input type="text" id="icon"  name="icon" value="${tsysModule.icon }"  class="text validate[required,maxSize[50]]"/></td>
					</tr>
					<tr>
						<td><fmt:message key="tsysmodule.moduleCode" /></td>
						<td><input type="text" id="moduleCode"  name="moduleCode" value="${tsysModule.moduleCode }"  class="text validate[required,maxSize[32]]"/>
						<font color="red">*</font><span  id="errorCodeName">&nbsp;&nbsp; </span></td>
						
					</tr>
					<tr>
						<td><fmt:message key="tsysmodule.parentModuleid" /></td>
						<td><input type="text" id="parentModuleName"  name="parentModuleName" value="${tsysModule.parentModuleName }" class="text"  readonly="readonly" /></td>
					</tr>
					<tr>
						<td><fmt:message key="tsysmodule.sortNum" /></td>
						<td><input type="text" id="sortNum"  name="sortNum" value="${tsysModule.sortNum }"  class="text" class="text validate[required,maxSize[2]]"/></td>
					</tr>
					<tr>
						<td><fmt:message key="tsysmodule.remark" /></td>
						<td><input type="text" id="remark"  name="remark" value="${tsysModule.remark }" class="text validate[required,maxSize[100]]"/></td>
					</tr>
					<tr>
						<td><fmt:message key="tsysmodule.display" /></td>
						<td>
							<select id="display" name="display" class="select" >
								<option value="0">是</option>
								<option value="1">否</option>
							</select>
						</td>
					</tr>
					</table>
			</div>
		</form>
	<div align="center" class="foot-buttons" style="margin-top:5px">
	<a id="btn_save"  class="easyui-linkbutton" iconCls="icon-save"><fmt:message key="button.save" /></a>
	<a id="btn_back"  class="easyui-linkbutton" iconCls="icon-back"><fmt:message key="button.back" /></a>
	</div>
</cbitedu:form>