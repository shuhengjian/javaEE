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
<style type="text/css">
	/*覆盖validation的消息提示样式 */
	.formErrorContent{line-height:15px !important;}
	 #imgLoadBox{
        	width:100px;
        	height:100px;
        	margin: 10px;
        	border: 3px dashed #D8D8D8;
        	position:relative;
        }
        #imgLoadBox input{
        	width:100px;
        	height:100px;
        	opacity: 0;
        }
        #imgLoadBox #box1{
        	width:10px;
        	height:60px;
        	background:#D8D8D8;
        	position:absolute;
        	left: 45px;
        	top: 20px;
        }
        #imgLoadBox #box2{
        	width:60px;
        	height:10px;
        	background:#D8D8D8;
        	position:absolute;
        	left: 20px;
        	top:45px;
        }
        #imgshow{
        	margin-top: 13px;
        }
       #imgremove{
        	width:20px;
        	height:20px;
        	border-radius: 50%;
        	border:1px solid white;
        	color:white;
        	background-color:#D8D8D8;
        	text-align: center;
        	position: absolute;
        	top:-7px;
        	right:-7px;
        	cursor: pointer;
        	z-index:100000;
        }
</style>
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
		$("#ttybrandForm").validationEngine({ promptPosition:"bottomRight"});
		var bool = $("#ttybrandForm").validationEngine('validate');
		let formData = new FormData($("#ttybrandForm")[0]);
		if (bool == true) {
			$.ajax({
				url : "${ctx}/ttyBrandController.do?method=ajaxSave",
				type : "POST",
				data :  formData,
				dataType : 'json',
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
					} else if(!data.flag && data.flag != null && !data.is) {
						jQuery.messager.alert('提示:', data.msg, 'info');
					}else if(!data.is){
						jQuery.messager.alert('提示:', data.state, 'info');
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
	//图片上传样式
	$(function(){
		$("input[type='file']").nextAll("img").css({"display":"inline-block","width":"auto","max-width":"350px","max-height":"100px","position":"absolute"});
		$("input[type='file']").css({"z-index":"10000","display":"inline-block","opacity":"0","width":"390px","position":"absolute"}).attr("title","点击选择图片"); 
	})
	//加载图片
	$(function(){
		var div = $("<div id=\"imgremove\" onclick=\"xxx(this)\">x</div>");
		//判断加载图片
		let fileUrl = $("#ttybrandForm").data("img");
		if(fileUrl != null && fileUrl != '' && fileUrl != undefined){
			$("img").attr("src","${ctx}/uploads/"+ fileUrl).attr("alt","图片加载失败");
			$("#box1").css({"opacity":"0"});
            $("#box2").css({"opacity":"0"});
            $("#imgLoadBox").css("border","0");
            $("img").load(function(){
            	let top = $("img").css("top");
    			let left = $("img").css("width");
                $("img").after(div);
                $(div).css("top",top);
                $(div).css("left",left);
            });
		}
		$("input[type='file']").change(function (){
			let imgSrc;
			let filePath = this.files[0];
			let reader = new FileReader();
			reader.readAsDataURL(filePath);
	        reader.onload = function() {
                imgSrc = this.result;
                $("img").attr("src", imgSrc).attr("alt","图片加载失败").attr("title","点击选择图片").css("width","auto");
                $("#box1").css({"opacity":"0"});
                $("#box2").css({"opacity":"0"});
                $("#imgLoadBox").css("border","0");
                $("img").load(function(){
                	let top = $("img").css("top");
        			let left = $("img").css("width");
                    $("img").after(div);
                    $(div).css("top",top);
                    $(div).css("left",left);
                });
            };
		})
		
	})
	//图片×号
	function xxx(tag){
		 $("#box1").css({"opacity":"1"});
         $("#box2").css({"opacity":"1"});
         $("#imgLoadBox").css("border","3px dashed #D8D8D8");
         $("img").removeAttr("src").removeAttr("alt").css("width","0");
         $("input[type='file']").val("");
         $(tag).remove();
	}
	//图片上传验证
	function fun(field, rules, i, options) {
		let fileUrl = $("#ttybrandForm").data("img");
		let suffix = ".jpg .jpeg .gif .png .bmp";
		let img =  $("img").attr("src");
		options.validationEventTrigger= false;
		options.autoPositionUpdate = true;
		options.promptPosition = "centerRight:-80,10";
		options.addPromptClass = "formError-small";
		if(field[0].files.length > 0){
			let fileSuffix = field[0].files[0].name.substring(field[0].files[0].name.indexOf("."));
			if(suffix.indexOf(fileSuffix) < 0){
				return "文件格式不正确！";
			}
		}
		if((fileUrl == '' | fileUrl.length == 0) && (field[0].files[0] == null | img == undefined)){
			return "未选择品牌图标！";
		}
		if(field[0].files[0] != null && field[0].files[0].size >= 1024000){
			return "文件大小限制1M！";
		}
	}
</script>
</head>
<body style="overflow-y:scroll;overflow-x: scroll">
	<!-- 表单布局设计begin -->
	<cbitedu:form bean="ttyBrand" scope="request">
		<form data-img="${ttyBrand.tsysAttach.fileUrl }" action="${ctx}/ttyBrandController.do?method=ajaxSave" method="post" name="ttybrandForm" id="ttybrandForm" enctype="multipart/form-data">
			<input type="hidden" name="brandId" />
			<div class="easyui-layout easyui-panel">
				<table class="FormView" border="0" cellspacing="1" cellpadding="0">
					<col class="Label" />
					<col class="Data" />
					<tr>
						<td><fmt:message key="brand.brandName" /></td>
						<td >
							<input style="width: 300px;" type="text" id="brandName" name="brandName" class="text validate[required,maxSize[15]]" maxlength="15" placeholder="最多15个文字！"/>
							<font color="red">*</font>
						</td>
					</tr>
					<tr>
						<td><fmt:message key="brand.brandState" /></td>
						<td>
							<cbitedu:para style="width:300px" name="brand_state" id="brandState" cssClass="select" />
							<font color="red">*</font>
						</td>
					</tr>
					<tr>
						<td style="vertical-align:middle;width:150px" ><fmt:message  key="brand.tsysAttach.fileUrl" /></td>
						<td style="display:flex">
							<div id="imgshow"></div>
							<div id="imgLoadBox">
								<input type="file" id="tsysAttach.fileUrl" name="file" class="${ttyBrand.brandId == null ? 'text validate[required,funcCall[fun]]' : 'text validate[funcCall[fun]]'}  " >
								<img/>
								<div id="box1"></div>
								<div id="box2"></div>
							</div>
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