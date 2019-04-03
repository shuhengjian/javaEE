<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用户数据列表</title>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<%@ include file="/WEB-INF/jsp/common/meta.jsp"%>

<script type="text/javascript">
$(function() { 

	$("#page_list").flexigrid({
        url : '${ctx}/tsysRoleController.do?method=listpage',// ajax方式对应的url地址
        params:[{
			"name" : "pageSize",
			"value" : 15
		}],
        type: 'post',// 数据发送方式 
        dataType : 'json',// 数据加载的类型(xml/json)        
        contentType: 'application/x-www-form-urlencoded; charset=UTF-8',
        sortname : "ROLE_ID",//排序
        sortorder:"desc",               
        usepager : false,
        useRp : true,
        checkbox : false,// 是否要多选框
        radio : true,
        rowId : 'ROLE_ID',// 多选框绑定行的id
        singleSelect: true, //仅允许选择单行 
        rp : 15, rpOptions : [15, 20, 30, 40,50,200],
        showTableToggleBtn : false,
        width : 'auto',// 宽度值 
        height :130,
        striped: false,
        resizable: true,  
        errormsg: '发生错误', 
        usepager: true, 
        nowrap: true,  
        rp : 15, rpOptions : [15, 20, 30, 40,50,200],      
        proitemg: '正在处理数据，请稍候 ...', //正在处理的提示信息                  
        colModel : [{
                        display : '序号',
                        name : 'rnum',
                        width : 50,
                        sortable : true,
                        align : 'center'
                    }]
       });   
   
});
</script>
</head>
<body>

<div id="page_list"></div>
</body>
</html>