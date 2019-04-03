<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">   
<title>文件上传组件</title>
<!-- Load Queue widget CSS and jQuery -->
<style type="text/css">@import url(${ctx}/ui_widget/plupload/js/jquery.plupload.queue/css/jquery.plupload.queue.css);</style>
<script type="text/javascript" src="${ctx}/ui_widget/easyui/jquery-1.8.0.min.js"></script>
<!-- Load plupload and all it's runtimes and finally the jQuery queue widget -->
<script type="text/javascript" src="${ctx}/ui_widget/plupload/js/plupload.full.js"></script>
<script type="text/javascript" src="${ctx}/ui_widget/plupload/js/i18n/zh_CN.js"></script>
<script type="text/javascript" src="${ctx}/ui_widget/plupload/js/jquery.plupload.queue/jquery.plupload.queue.js"></script>

<script type="text/javascript">
// Convert divs to queue widgets when the DOM is ready
$(function() {
	//$("#uploader").pluploadQueue({
var uploader = $("#uploader").pluploadQueue({
		// General settings
		runtimes : "html5",
		url : "plupload",
		max_file_size : "1000mb",
		chunk_size : "10mb",
		unique_names : true,
		rename : true,
		// Specify what files to browse for		
		filters : [
			{title : "Image files", extensions : "jpg,gif,png"},
			{title : "Vedio files", extensions : "mp4,avi,rmv,rmvb"},			
			{title : "Zip files", extensions : "zip,rar"}
		],
		// PreInit events, bound before any internal events
		preinit : {
			UploadFile: function(up, file) {
				up.settings.multipart_params = {name: file.name};
			}
		}
		
	});


});
</script>
			
</head>
 
<body>
	<div id="uploader">
		<p>You browser doesn't have Flash, Silverlight, Gears, BrowserPlus or HTML5 support.</p>
	</div>
</body>
</html>
