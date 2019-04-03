<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<%@ include file="/WEB-INF/jsp/common/meta.jsp"%>
<%@ include file="/WEB-INF/jsp/common/title.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<title>完整demo</title>
    <meta http-equiv="Content-Type" content="text/html;charset=utf-8"/>
    <script type="text/javascript" charset="utf-8" src='${ctx}/ui_widget/ueditor/ueditor.config.js'></script>
    <script type="text/javascript" charset="utf-8" src='${ctx}/ui_widget/ueditor/ueditor.all.min.js'> </script>
    <!--建议手动加在语言，避免在ie下有时因为加载语言失败导致编辑器加载失败-->
    <!--这里加载的语言文件会覆盖你在配置项目里添加的语言类型，比如你在配置项目里配置的是英文，这里加载的中文，那最后就是中文-->
    <script type="text/javascript" charset="utf-8" src='${ctx}/ui_widget/ueditor/lang/zh-cn/zh-cn.js'></script>

    <style type="text/css">
        .clear {
            clear: both;
        }
    </style>
 <script>
 	var ue = UE.getEditor('editor');
 </script>
</head>
<body>
    <script id="editor" type="text/plain" style="width:1024px;height:100px;"></script></br>
    <a>保存</a>
</body>
	<div></div>
</html>