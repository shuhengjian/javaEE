<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<%@ include file="/WEB-INF/jsp/common/title.jsp"%>

</head>
<script type="text/javascript">
//渲染样式
$.parser.parse();
$(function() {
	$("tbody tr:even").css("background-color", "#ECE9D8");
	//$("#content tr:odd").css("background-color","#D2B48C");
	//$("#content tr:even").css("background-color","#C0C0C0"); 
	
	
	$("#btn_save").click(function(){
		var bool=$("#tsysOperateForm").validationEngine('validate');
		if(bool==true)
		{
				$.ajax({
					url : '${ctx}/tsysModuleoperateController.do?method=saveModuleOperate',
					data: $("#tsysOperateForm").serialize(),
					type : 'POST',
					dataType : 'json',
					error : function() {
						alert('系统繁忙，请稍后再试');
					},
					success : function(data) {
						if (data == true) {
							jQuery.messager.alert('提示:','保存成功!','info');
						
						} else
							jQuery.messager.alert('提示:','保存失败!','warning');
					}
				});
		}else{jQuery.messager.alert('提示:','您的表单输入校验不通过，请重填!','info');  }
		
			
	});
	//返回
	$("#btn_back").click(function(){
	   $("#win").window("close");
	 });
}); 
function addRow(tar){
		$(tar).parents("tbody").append($(tar).parents("tr").clone().find("input").val("").end());
}
function removeRow(tar,operateName)
{	
	if($(tar).parents("table").find("tr").length==2 && operateName=='')
	{
		return false;
	}
	var tarTR=$(tar).parents("tr");
	var operateId=tarTR.find(":hidden[name='operateId']").val();
	if(operateId){
		$.get("${ctx}/tsysModuleoperateController.do?method=delModuleOperate&operateId="+operateId,function(r){
			if(r=='1'){
				tarTR.remove();	
			}else{
				$.messager.alert('提示:','不能删除，可能已关联角色权限','warning');
			}
		});
	}else{
		tarTR.remove();		
	}
}
</script>
<body >

	<form action="" method="post" name="tsysOperateForm" id="tsysOperateForm" >
	<input  type="hidden" name="moduleId" value="${module.moduleId }">
			<div>
				 <table style="width:90%;   table-layout:inherit; text-align:center;
    			 border:2px; border-collapse:inherit; ">
				<tbody id="content" > 
				<tr style="background-color:#BCD2EE;">
						<th><fmt:message key="tsysmodule.moduleName" /></th>
						<th><fmt:message key="tsysmoduleoperate.operateName" /></th>
						<th><fmt:message key="tsysmoduleoperate.operateCode" /></th>
						<th>操作</th>
				</tr>
			   	<c:choose>
			   		<c:when test="${empty moduleoperate}">
			   		<tr>   
						<td width="80px">${module.moduleName } <input name="operateId" value="" type="hidden"/></td>
						<td ><input name="operateName" class="text validate[required,maxSize[50]]" style="font-size: 12; border-width:1;width: 70px;" value="${mo.operateName }"></td>
						<td ><input name="operateCode" class="text validate[required,maxSize[15]]" style="font-size: 12; border-width:1;width: 70px;" value="${mo.operateCode }"></td>
						<td>
							<a href="javascript:void()"  onclick="addRow(this)" >增加</a>
							<a href="javascript:void()"  onclick="removeRow(this,'')" >立即删除</a>
						</td>
						</tr>
			   		</c:when>

					<c:otherwise>
					<c:forEach items="${moduleoperate}" var="mo" varStatus="ts" >
				    <tr>   
						<td width="80px">${mo.moduleName}
						<input name="operateId" value="${mo.operateId }" type="hidden"/>
						 </td>
						<td ><input name="operateName" class="text validate[required,maxSize[50]]"  style="font-size: 12; border-width:1;width: 70px;" value="${mo.operateName }"></td>
						<td ><input name="operateCode"  class="text validate[required,maxSize[15]]" style="font-size: 12; border-width:1;width: 70px;" value="${mo.operateCode }"></td>
						<td>
						<a href="javascript:void()"  onclick="addRow(this)" >增加</a>
							<a href="javascript:void()"  onclick="removeRow(this,'${mo.operateName }')" >立即删除</a>
						</td>
					</tr>
					</c:forEach>
					</c:otherwise>
				</c:choose>
				
				</tbody>	
			   </table>
			</div>
	</form>
		
	<div align="center" class="foot-buttons" style="margin-top:5px">
	<a id="btn_save"  class="easyui-linkbutton" iconCls="icon-save"><fmt:message key="button.save" /></a>
	<a id="btn_back"  class="easyui-linkbutton" iconCls="icon-back"><fmt:message key="button.back" /></a>
	</div>
</body>
</html>
