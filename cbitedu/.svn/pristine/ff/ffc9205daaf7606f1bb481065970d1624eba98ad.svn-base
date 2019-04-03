<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<div id="roles_list"></div>
<script type="text/javascript">
	$("#roles_list").flexigrid({
		url : "${ctx}/index.do?method=getRoles",// ajax方式对应的url地址
		type : 'post',// 数据发送方式 
		dataType : 'json',// 数据加载的类型(xml/json)        
		contentType : 'application/x-www-form-urlencoded; charset=UTF-8',
		sortname : "postId",//排序
		sortorder : "asc",
		useRp : true,
		checkbox : false,// 是否要多选框
		idProperty : 'postId',// 多选框绑定行的id
		singleSelect : true, //仅允许选择单行 
		rp : 15,
		showTableToggleBtn : false,
		width : 'auto',// 宽度值 
		height : 'auto',// 插件的高度，单位为px   
		striped : true,// 是否显示斑纹效果，默认是奇偶交互的形式
		resizable : false,// 是否可伸缩   
		errormsg : '发生错误', //错误提升信息
		usepager : false, // 是否分页
		nowrap : true, //是否不换行               
		rpOptions : [ 10, 15, 20, 30, 40, 50 ],
		proitemg : '正在处理数据，请稍候 ...', //正在处理的提示信息    
		colModel : [ {
			display : '角色名称',
			name : 'roleName',
			width : 160,
			sortable : true,
			align : 'center'
		},{
			display : '所属机构',
			name : 'org.orgName',
			width : 140,
			sortable : true,
			align : 'center'
		}, {
			display : '创建人',
			name : 'userinfo.userRealname',
			width : 130,
			sortable : true,
			align : 'center'
		}, {
			display : '创建时间',
			name : 'createDate',
			width : 120,
			sortable : true,
			align : 'center'
		} ]
	});
</script>