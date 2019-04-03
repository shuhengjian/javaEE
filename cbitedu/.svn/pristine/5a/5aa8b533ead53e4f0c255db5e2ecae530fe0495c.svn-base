<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<%@ include file="/WEB-INF/jsp/common/title.jsp"%>
<script type="text/javascript" src="${ctx}/js/jquery.form.js"></script>
<script type="text/javascript">
	$(function(){
		$( "[title]" ).tooltip({			
		});
		$.parser.parse();		
	});
	
	function save_click() {
		var bool = $("#tsysserialnoForm").validationEngine('validate');
		if (bool == true) {
			//判断是否符合规则定义
			if(!validate())
				return;
			$.ajax({
				url : "${ctx}/tsysSerialnoController.do?method=ajaxSave",
				type : "POST",
				data : $("#tsysserialnoForm").serialize(),
				dataType : 'json',
				error : function() {
					alert("发生错误!");
				},
				success : function(data) {
					if (data.success) {
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
	function previesreg_click(){
		//判断是否符合规则定义
		if(!validate())
			return;
		var formular_regx=$('#formular_regx').val();
		var create_type=$('#create_type').val();
		var serial_length=$('#serial_length').val();
		var serial_length=$('#serial_length').val();
		var step=$('#step').val();
		var init_value=$('#init_value').val();
		var regx_data="规则："+formular_regx+"，生成流水号为：</br>";
		var date = new Date();
		var year=date.getFullYear();    //获取完整的年份(4位,1970-????)
		var month=(date.getMonth()+1)>9?(date.getMonth()+1).toString():"0"+(date.getMonth()+1).toString();       //获取当前月份(0-11,0代表1月)
		var day=date.getDate()>9?date.getDate():"0"+date.getDate();        //获取当前日(1-31)
		
		//设置记数
		var no=0;
		//alert(serial_length);
		//alert(create_type);
		for(var i=0;i<10;i++){			
			var formular_regx_temp=formular_regx;			
			formular_regx_temp=formular_regx_temp.replace("{YYYY}",year);
			formular_regx_temp=formular_regx_temp.replace("{MM}",month);
			formular_regx_temp=formular_regx_temp.replace("{DD}",day);
			if(no==0)
				no=init_value;
			else
				no= (parseInt(no)+ parseInt(step)).toString();
			if(create_type!='5')
				formular_regx_temp=formular_regx_temp.replace("{NO}",addZero(no,serial_length));
			else
				formular_regx_temp=formular_regx_temp.replace("{NO}",no);;
			regx_data+="&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+formular_regx_temp+"</br>";
		}
		//alert(regx_data);	
		jQuery.messager.alert('预览规则', regx_data);
	}
	//自动补"0"
	function addZero(str,length){  		  
	      return new Array(length - str.length+1).join("0") + str;                  
	} 
	//判断是否符合规则定义
	function validate(){
		var formular_regx=$('#formular_regx').val();
		var create_type =$('#create_type').val();
		if(formular_regx.indexOf("{NO}")>-1){
			if(create_type==2){
				if(formular_regx.indexOf("{YYYY}")>-1&&formular_regx.indexOf("{MM}")>-1&&formular_regx.indexOf("{DD}")>-1){
					return true;
				}else{
					jQuery.messager.alert('提示', "规则定义必需包含年月日，如：{YYYY}{MM}{DD}");
					return false;
				}
			}else{
				if(create_type==3){
					if(formular_regx.indexOf("{YYYY}")>-1&&formular_regx.indexOf("{MM}")>-1){
						return true;
					}else{
						jQuery.messager.alert('提示', "规则定义必需包含年月，如：{YYYY}{MM}");
						return false;
					}
				}else{
					if(create_type==4){
						if(formular_regx.indexOf("{YYYY}")>-1){
							return true;
						}else{
							jQuery.messager.alert('提示', "规则定义必需包含年，如：{YYYY}");
							return false;
						}
					}else{
						if(create_type==5){
							if(formular_regx=='{NO}'){
								return true;
							}else{
								jQuery.messager.alert('提示', "规则定义必需为{NO}");
								$('#formular_regx').val('{NO}');
								return false;
							}
						}else{
							return true;
						}
						
					}
				}
			}
		}else{
			jQuery.messager.alert('提示', "规则定义必需包含序号，如：{NO}");
			return false;
		}		
		
	}
	function changeFormular(){
		$('formular_regx').val('{NO}');
	}
</script>
<cbitedu:form bean="tsysSerialno" scope="request">

<form action="${ctx}/tsysSerialnoController.do?method=ajaxSave" method="post" name="tsysserialnoForm" id="tsysserialnoForm">
	
	<input type="hidden" name="serialno_id" id="serialno_id"/><!-- 序号 -->
	<input type="hidden" name="formular_regx_old" id="formular_regx_old"/><!-- 规则 -->
	<input type="hidden" name="current_value" id="current_value"/><!-- 当前值 -->
	<div>
		<table class="FormView" border="0" cellspacing="1" cellpadding="0">
			<col class="Label" style="width: 60px;" />
			<col class="Data"/>
			<tr>
				<td width="30%">
					<fmt:message key="tsysserialno.serial_name" /><!-- 规则名称 -->
				</td>
				<td>
					<input type="text" id="serial_name" name="serial_name" class="text validate[required]" />
					<font color="red">*</font>
				</td>
			</tr>
			<tr>
				<td>
					<fmt:message key="tsysserialno.secound_name" /><!-- 规则别名-->
				</td>
				<td>
					<input type="text" id="secound_name" name="secound_name" class="text validate[required]" />
					<font color="red">*</font>
				</td>
			</tr>
			<tr>
				<td>
					<fmt:message key="tsysserialno.formular_regx" /> <!-- 规则 -->
				</td>
				<td>
					<input type="text" name="formular_regx" id="formular_regx" class="text validate[required]"/>
					
					<font color="red">*</font>
					<img title="格式形如：{YYYY}{MM}{DD}-{NO}==>20140922-00001" src="${ctx}/ui_widget/easyui/themes/icons/help.png" style="vertical-align:middle;"/>
				</td>
			</tr>
			<tr>
				<td>
					<fmt:message key="tsysserialno.create_type" /><!-- 生成方式 -->
				</td>
				<td>
					<cbitedu:para name="create_type" id="create_type" cssClass="select" defValue="${tsysserialno.create_type}"></cbitedu:para>
					<font color="red">*</font>
					<img title="流水号生成规则：<br/>&nbsp;&nbsp1、递增 <br/>&nbsp;&nbsp2、每日生成(每日从初始值记数,递增) <br/>&nbsp;&nbsp3、每月生成(每月从初始值记数,递增) <br/>&nbsp;&nbsp4、每年生成(每年从初始值记数,递增)<br/>&nbsp;&nbsp5、序列递增(按序列从初始值记数,递增)" src="${ctx}/ui_widget/easyui/themes/icons/help.png" style="vertical-align:middle;"/>
				</td>
			</tr>
			<tr>
				<td>
					<fmt:message key="tsysserialno.serial_length" /><!-- 流水号长度 -->
				</td>
				<td>
					<input type="text"  id="serial_length" name="serial_length"  class="text validate[required]"/>
					<font color="red">*</font>
					<img title="这个长度表示当前流水号的长度数，只包括流水号部分{NO}。</br>比如：长度为5,当前流水号为5，则在流水号前补4个0，表示为00005。" src="${ctx}/ui_widget/easyui/themes/icons/help.png" style="vertical-align:middle;"/>
				
				</td>
			</tr>
			<tr>
				<td>
					<fmt:message key="tsysserialno.step" /><!-- 步长 -->
				</td>
				<td>
					<input type="text"  id="step" name="step"  class="text validate[required]"/>
					<font color="red">*</font>
					<img title="流水号每次递增的数字" src="${ctx}/ui_widget/easyui/themes/icons/help.png" style="vertical-align:middle;"/>
				</td>
			</tr>
			<tr>
				<td>
					<fmt:message key="tsysserialno.init_value" /><!-- 初始值 -->
				</td>
				<td>
					<input type="text"  id="init_value" name="init_value"  class="text validate[required]"/>
					<font color="red">*</font>
				</td>
			</tr>		
			<tr>
				<td>
					<fmt:message key="tsysserialno.tab_name" /><!-- 表名 -->
				</td>
				<td>
					<input type="text"  id="tab_name" name="tab_name"  class="text validate[required]"/>
					<font color="red">*</font>
				</td>
			</tr>
			<tr>
				<td>
					<fmt:message key="tsysserialno.remark" /><!-- 备注 -->
				</td>
				<td>
					<input type="text"  id="remark" name="remark"  class="text"/>
				</td>
			</tr>
			
		</table>
	</div>
</form>
<!-- 表单布局设计end -->
<!-- 表单内的按钮设计begin -->
<div align="center" class="foot-buttons" style="margin-top: 5px">
	<a id="btn_save" onclick="save_click();" class="easyui-linkbutton" iconCls="icon-save"><fmt:message key="button.save" /></a>
	<a id="btn_save" onclick="previesreg_click();" class="easyui-linkbutton" iconCls="icon-large-shapes"><fmt:message key="button.previesreg" /></a>
	<a id="btn_save" onclick="back_click();" class="easyui-linkbutton" iconCls="icon-back"><fmt:message key="button.back" /></a>
</div>
</cbitedu:form>