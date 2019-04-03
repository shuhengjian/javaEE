/*var contextPath = "<%=request.getContextPath()%>";*/
var grid;
var win;
var form;
 $(function(){
	grid = $("#userTable").datagrid({
		fit: true,//自动大小  
		idField:'userId',
        singleSelect:true,//单行选取
        url:contextPath+"/tsysUserinfoController.do?method=getData",
		loadMsg:'数据装载中......',  
        rownumbers:true,//行号 
		pagination:true,//显示分页
		pageSize:10,
		pageList: [10,15,30,50,100],//可以设置每页记录条数的列表 
		toolbar:[{
			text:'新增',
			iconCls:'icon-add',
			handler:newData
		},'-',{
			text:'修改',
			iconCls:'icon-edit',
			handler:editData
		},'-',{
			text:'删除',
			iconCls:'icon-remove',
			handler:delData
		},'-',{
			text:'查询',
			iconCls:'icon-search'
		}],
		 columns:[[
			{field:'userId',title:'用户编号',width:130},
			{field:'userRealname',title:'用户姓名',width:100},
			{field:'loginName',title:'登录名称',width:80,align:'right'},
			{field:'sex',title:'性别',width:80,align:'right'},
			{field:'mobiletel',title:'手机',width:150},
			{field:'fax',title:'传真',width:60,align:'center'},
			{field:'zip',title:'邮编',width:60,align:'center'},
			{field:'deptId',title:'部门',width:60,align:'center'},
			{field:'state',title:'用户状态',width:60,align:'center'}
		]],
	});
	//设置分页控件 
    var p = grid.datagrid('getPager'); 
    $(p).pagination({ 
        pageSize: 10,//每页显示的记录条数，默认为10 
        pageList: [10,15,30,50,100],//可以设置每页记录条数的列表 
        //beforePageText: '第',//页数文本框前显示的汉字 
        //afterPageText: '页    共 {pages} 页', 
        //displayMsg: '当前显示 {from} - {to} 条记录   共 {total} 条记录', 
    }); 
	$('#btn-save,#btn-cancel').linkbutton();
	win = $('#user-window').window({
		closed:true
	});
	form = win.find('form');
});

/**
 * 新打开用户窗口
 */
function newData(){
	win.window('open');
	form.form('clear');
	form.url = contextPath+'/tsysUserinfoController.do?method=saveUser';
}
/**
 * 编辑用户信息
 */
function editData(){
	var row = grid.datagrid('getSelected');
	if (row){
		win.window('open');
		form.form('load',row);
//		form.url = '/etmvcwithspring/contact/update/'+row.userId;
		form.url =  contextPath+'/tsysUserinfoController.do?method=saveUser';
	} else {
		$.messager.show({
			title:'警告', 
			msg:'请先选择数据！！'
		});
	}
}
/**
 * 保存数据
 */
function saveData(){
	form.form('submit', {
		url:form.url,
		success:function(data){
			eval('data='+data);
			if (data.success){
				$.messager.alert('成功',data.msg,'info');
				grid.datagrid('reload');
				win.window('close');
			} else {
				$.messager.alert('错误',data.msg,'error');
			}
		}
	});
}
/**
 * 删除用户
 */
function delData(){
	var row = grid.datagrid('getSelected');
	if (row){
		$.messager.confirm('Confirm', '您确定要删除该数据吗?', function(r) {
            if (r){
             $.post(contextPath+'/tsysUserinfoController.do?method=delData', { id: row.userId }, 
                    function(result) {
                      if (result.success) {
                    	  grid.datagrid('reload');
                        } else {
                            $.message.show({
                                title: 'Error',
                                msg: result.msg
                            });
                        }
                    },
                  'json');
            }
        });
	} else {
		$.messager.show({
			title:'警告', 
			msg:'请先选择通讯记录。'
		});
	}
}
/**
 * 关闭窗口
 */
function closeWindow(){
	win.window('close');
}

