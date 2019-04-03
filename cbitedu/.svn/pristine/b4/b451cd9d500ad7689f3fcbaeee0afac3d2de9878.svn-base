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
	/* 新闻保存 */
	function save_click() { 
		//验证标题
		var titleval = $("#newsTitle").val();//标题
		titleval = titleval.replace(/\s*/g,"");
		var introval = $("#newsIntroduce").val();//新闻介绍
		introval = introval.replace(/\s*/g,"");
		/* $("#ttyNewsForm").validationEngine({ promptPosition:"bottomRight"});
		var bool = $("#ttyNewsForm").validationEngine('validate'); */
		
		if (titleval.length<4) {//新闻标题验证
			$("#newsTitle").focus();
			jQuery.messager.alert('提示:', '标题长度少于4，请重填!', 'info');
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
		//新闻介绍验证
		if(introval.length==0){
			$("#newsIntroduce").focus();
			jQuery.messager.alert('提示:','新闻介绍不能为空', 'info');
			return false;
		}
		//新闻内容验证
		if(UE.getEditor('editor').getContentTxt().replace(/\s+/g,"").length == 0){
			jQuery.messager.alert('提示:','新闻内容不能为空并且不能只有图片!', '');
			return;
		}
		//防止多次提交使按钮失效
		$("#btn_save").attr("disabled", true); 
		let formData = new FormData($("#ttyNewsForm")[0]);
		formData.append("newsContent", UE.getEditor('editor').getContent());
		//ajax上传
		$.ajax({
			url : "${ctx}/ttyBackNewsController.do?method=ajaxSave",
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
	$(function(){//修改时图片预览
		let fileUrl = $("#ttyNewsForm").data("img");
		if(fileUrl != null && fileUrl != '' && fileUrl != undefined){
			
			$("#imgshow").html('<img id=\"newsimg\" style=\"height:100px;\" src=\"${ctx}/uploads/'+fileUrl+'\" alt=\"图片加载失败\" /><div id=\"imgremove\">x</div>');
            $("#imgshow").addClass("img_preview");
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
 		});
 		//新闻内容
 		
		
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
                //图片宽度太大判断
                
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
		//实例化编辑器
		var ue = UE.getEditor('editor');
		var id = $("[name='newsId']").val();
		if(id!=""){
			$.ajax({
				type:"POST",
				url:"${ctx}/ttyBackNewsController.do?method=queryNewsContent",
				data: {id:id},
				async:true,
				dataType:"json",
				success:function(res) {
					//将内容注入文本编辑器
					UE.getEditor("editor").addListener("ready", function () {
						UE.getEditor('editor').setContent(res.newsContent);
					});
				}
			});
		}
		

		//验证标题
		$("#newsTitle").focus(function(){
			$("#titleinfo").html("*必填,标题长度4-20");
			$("#titleinfo").attr("color","red");
			lose();
			function lose(){
				$("#newsTitle").blur(function(){
					var titleval = $("#newsTitle").val();
					titleval = titleval.replace(/\s*/g,"");
					if(titleval.length>=4){
						$("#titleinfo").html("标题格式符合");
						$("#titleinfo").attr("color","green");
					}else{
						$("#titleinfo").html("*标题长度不得少于4个字符");
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
	<cbitedu:form bean="ttyNews" scope="request">
		<form data-img="${ttyNews.tsysAttach.fileUrl }"
		action="${ctx}/ttyNewsBackController.do?method=ajaxSave" 
		method="post" name="ttyNewsForm" id="ttyNewsForm" 
		enctype="multipart/form-data">
			<input type="hidden" name="newsId" id="newsId"/>
			<div class="easyui-layout easyui-panel">
				<table class="FormView" border="0" cellspacing="1" cellpadding="0">
					<col class="Label" />
					<col class="Data" />
					<tr >
						<!-- 新闻标题 -->
						<td ><fmt:message key="ttynews.newsTitle" /></td>
						<td style="display:flex;">
							<input type="text" id="newsTitle" name="newsTitle" placeholder="必填,长度4~20" maxlength="20" />
							<font color="red" id="titleinfo"></font>
							
						</td>
					</tr>
					<tr>
						<!-- 新闻类型 -->
						<td><fmt:message key="ttynews.newsTypeCode" /></td>
						<td>
							<cbitedu:para name="newsTypeCode" id="newsTypeCode" cssClass="select" />
						</td>
					</tr>
					<tr>
						<!-- 新闻状态 -->
						<td><fmt:message key="ttynews.newsState" /></td>
						<td>
							<cbitedu:para name="newsState" id="newsState" cssClass="select" />
						</td>
					</tr>
					
					<tr>
						<!-- 新闻图片 -->
						<td style="vertical-align:top"><fmt:message key="ttynews.newsImg" /></td>
						<td style="display:flex;">
							<div id="imgshow" style="display:flex;position:relative;">
							</div>
							<div id="imgLoadBox">
								<input type="file" id="tsysAttach.fileUrl" name="file"/>
								<div id="box1"></div>
								<div id="box2"></div>
							</div>
						</td>
					</tr>
					<tr>
						<!-- 新闻介绍 -->
						<td style="vertical-align:top"><fmt:message key="ttynews.newsIntroduce" /></td>
						<td>
							<textarea style="width:500px;height:50px;resize: none;" placeholder="必填" maxlength="100" type="text" id="newsIntroduce" name="newsIntroduce" ></textarea>
						</td>
					</tr>
					
					<tr>
						<!-- 新闻内容 -->
						<td><fmt:message key="ttynews.newsContent" /></td>
						<td style="vertical-align:top;" id="imgSet">
							<script id="editor" type="text/plain" style="width:500px;height:200px;"></script>
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