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
	<!-- ueditor begin-->
	<script type="text/javascript" charset="utf-8" src="${ctx}/ui_widget/ueditor/news.js"></script>
    <script type="text/javascript" charset="utf-8" src="${ctx}/ui_widget/ueditor/ueditor.all.min.js"> </script>
    <!--建议手动加在语言，避免在ie下有时因为加载语言失败导致编辑器加载失败-->
    <!--这里加载的语言文件会覆盖你在配置项目里添加的语言类型，比如你在配置项目里配置的是英文，这里加载的中文，那最后就是中文-->
    <script type="text/javascript" charset="utf-8" src="${ctx}/ui_widget/ueditor/lang/zh-cn/zh-cn.js"></script>

    <style type="text/css">
        .clear {
            clear: both;
        }
        /*图片预览*/
        #img_preview{
        	height:100px;
        	width:300px;
        }
        /* 图片上传样式 */
        #imgLoadBox{
        	width:100px;
        	height:100px;
        	margin: 10px;
        	border: 3px dashed #D8D8D8;
        	position:relative
        }
        #imgLoadBox input{
        	width:100px;
        	height:100px;
        	opacity: 0;
        	position:absolute;
        	left:0;
        	top:0;
        	z-index: 10000;
        }
        #imgLoadBox #box1{
        	width:10px;
        	height:60px;
        	background:#D8D8D8;
        	position:absolute;
        	left: 45px;
        	top: 20px;
        	z-index:1;
        }
        #imgLoadBox #box2{
        	width:60px;
        	height:10px;
        	background:#D8D8D8;
        	position:absolute;
        	left: 20px;
        	top:45px;
        	z-index:1;
        }
        /* 图片预览样式 */
        #imgshow{
        	margin-top: 13px;
        }
        #imgshow #imgremove{
        	width:15px;
        	height:15px;
        	border-radius: 50%;
        	border:1px solid white;
        	color:white;
        	background-color:#D8D8D8;
        	text-align: center;
        	position: absolute;
        	top:-7px;
        	right:-7px;
        	cursor: pointer;
        }
    </style>
    <!-- ueditor end-->
<script type="text/javascript">
	/* 保存 */
	function save_click() { 
		//验证名称
		var titleval = $("#advertisingName").val();//广告名
		titleval = titleval.replace(/\s*/g,"");
		
		var beginval =$("input[name='beginTime']").val();//开始时间
		var endval = $("input[name='endTime']").val();//结束时间
	
		let formData = new FormData($("#ttyAdvertisingForm")[0]);
		formData.append("advertisingBeginTime",beginval);
		formData.append("advertisingEndTime",endval);
		if (titleval.length<4) {//广告名称验证
			$("#advertisingName").focus();
			jQuery.messager.alert('提示:', '广告名称长度少于4，请重填!', 'info');
			return false;
		}
		if (beginval=="") {//广告开始时间验证
			$("#advertisingBeginTime").focus();
			jQuery.messager.alert('提示:', '请选择广告开始时间!', 'info');
			return false;
		}
		if(endval=="") {//广告结束时间验证
			$("#advertisingEndTime").focus();
			jQuery.messager.alert('提示:', '请选择广告结束时间!', 'info');
			return false;
		}
		if(beginval>=endval){//时间验证
			jQuery.messager.alert('提示:', '结束时间早于开始时间', 'info');
			return false;
		}
		//文件上传验证
		if($("#newsId").val()==""||$("#imgshow").html()==""){
			if($("input[type='file']").val()==""){
				$("input[type='file']").focus();
				jQuery.messager.alert('提示:', '请选择你要上传的图片', 'info');
				return false;
			}
		}
		
		//防止多次提交使按钮失效
		$("#btn_save").attr("disabled", true); 
		//ajax上传
		$.ajax({
			url : "${ctx}/ttyBackAdvertisingController.do?method=ajaxSave",
			type : "POST",
			data : formData,
			processData:false,
          	contentType: false,  
			dataType : 'json',
			error : function() {
				jQuery.messager.alert('提示:','发生错误!', '');
			},
			success : function(data) {
				//使按钮重新有效
				$("#btn_save").attr("disabled", false); 
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
		
	}
	
	function reloadList(){
		parent.reloadlist();
	}
	function back_click() {
		parent.closeDialog();
	}
	
	/*图片*/
	$(function(){//修改时图片预览ttyAdvertising
		let fileUrl = $("#ttyAdvertisingForm").data("img");
		if(fileUrl != null && fileUrl != '' && fileUrl != undefined){
			
			$("#imgshow").html('<img id=\"newsimg\" style=\"height:100px;\" src=\"${ctx}/uploads/'+fileUrl+'\" alt=\"图片加载失败\" /><div id=\"imgremove\">x</div>');
			/*图片宽度限制*/
            var imgwidth = $("#newsimg").css("width");
            imgwidth = parseInt(imgwidth.replace("px",""));
            if(imgwidth>400){
            	$("#imgshow").children("img").css("width","400px");
            }
			if($("#imgshow").html()!=""){
            	$("#imgLoadBox").css("display","none");
            }
        }
		//重新选择
		 $("#imgremove").click(function(){
 			$("#imgshow").html("");
 			$("#imgLoadBox").css("display","");
 			$("input[type='file']").val("");
 		})
		$("input[type='file']").change(function (){
			//添加时图片预览
			let imgSrc;
			let filePath = this.files[0];
			let str = filePath.name;//获取图片名字
			let size = filePath.size;//获取图片大小
			let strfix = str.substring(str.lastIndexOf("."));//截取图片后缀
			let suffix = ".jpg .jpeg .gif .png .bmp";
			//图片格式验证
			if(suffix.indexOf(strfix)<0){
				$("input[type='file']").val("");
				jQuery.messager.alert('提示:', '您的图片上传格式不正确，请重选!', 'info');
				/* alert('您的图片上传格式不正确，请重选!'); */
				return false;
			}
			//图片大小验证
			if(size>1024*1024){
				$("input[type='file']").val("");
				jQuery.messager.alert('提示:', '您的图片超过1MB，请重选!', 'info'); 
				/* alert('您的图片超过1MB，请重选!'); */
				return false;
			}
			let reader = new FileReader();
			reader.readAsDataURL(filePath);
	        reader.onload = function() {
                imgSrc = this.result;
                $("#imgshow").html('<img style=\"height:100px;\" alt="图片加载失败" src=\"'+imgSrc+'\"/><div id=\"imgremove\">x</div>');
                /*图片宽度限制*/
                var imgwidth = $("#imgshow").children("img").css("width");
                imgwidth = parseInt(imgwidth.replace("px",""));
                if(imgwidth>400){
                	$("#imgshow").children("img").css("width","400px");
                }
                if($("#imgshow").html()!=""){
                	$("#imgLoadBox").css("display","none");
                }
                //重新选择
                $("#imgremove").click(function(){
        			$("#imgshow").html("");
        			$("#imgLoadBox").css("display","");
        			$("input[type='file']").val("");
        		})
	        };
		})
	})
	$(function(){
		//验证名字
		$("#advertisingName").focus(function(){
			$("#titleinfo").html("*必填,标题长度4-20");
			$("#titleinfo").attr("color","red");
			lose();
			function lose(){
				$("#advertisingName").blur(function(){
					var nameval = $("#advertisingName").val();
					nameval = nameval.replace(/\s*/g,"");
					if(nameval.length>=4){
						$("#titleinfo").html("格式符合");
						$("#titleinfo").attr("color","green");
					}else{
						$("#titleinfo").html("*长度不得少于4个字符");
						$("#titleinfo").attr("color","red");
					}
				})
			}
		})
		//验证
	})
</script>
</head>
<body style="overflow-y:scroll;overflow-x: scroll">
	<!-- 表单布局设计begin -->
	<cbitedu:form bean="ttyAdvertising" scope="request">
		<form data-img="${ttyAdvertising.tsysAttach.fileUrl }"
		action="${ctx}/ttyBackAdvertisingController.do?method=ajaxSave" 
		method="post" name="ttyAdvertisingForm" id="ttyAdvertisingForm" 
		enctype="multipart/form-data">
			<input type="hidden" name="advertisingId" id="advertisingId"/>
			<div class="easyui-layout easyui-panel">
				<table class="FormView" border="0" cellspacing="1" cellpadding="0">
					<col class="Label" />
					<col class="Data" />
					<tr >
						<!-- 广告名-->
						<td >广告名称</td>
						<td style="display:flex;">
							<input type="text" id="advertisingName" name="advertisingName" placeholder="必填,长度4~20" maxlength="20" />
							<font color="red" id="titleinfo"></font>
							
						</td>
					</tr>
					
					<tr>
						<!-- 广告状态 -->
						<td>广告状态</td>
						<td>
							<cbitedu:para name="advertising_state" id="advertisingState" cssClass="select" />
						</td>
					</tr>
					<tr>
						<!-- 开始时间 -->
						<td>开始时间</td>
						<td>
							<input style="cursor:hand"  type="text" id="advertisingBeginTime" name="advertisingBeginTime" editable="fasle" class="easyui-datetimebox"/  >
						</td>
					</tr>
					<tr>
						<!-- 结束时间 -->
						<td>结束时间</td>
						<td>
							<input style="cursor:hand"  type="text" id="advertisingEndTime" name="advertisingEndTime" editable="fasle" class="easyui-datetimebox"/  >
						</td>
					</tr>
					<tr>
						<!-- 广告图片 -->
						<td style="vertical-align:top">广告图片</td>
						<td style="display:flex;">
							<div id="imgshow" style="display: flex;position: relative;">
							</div>
							<div id="imgLoadBox">
								<input type="file" id="tsysAttach.fileUrl" name="file"/>
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
			<button id="btn_save" onclick="save_click();" class="easyui-linkbutton" iconCls="icon-save">
				<fmt:message key="button.save" />
			</button>
			<a id="btn_save" onclick="back_click();" class="easyui-linkbutton" iconCls="icon-back">
				<fmt:message key="button.back" />
			</a>
		</div>
	</cbitedu:form>
	
</body>
</html>