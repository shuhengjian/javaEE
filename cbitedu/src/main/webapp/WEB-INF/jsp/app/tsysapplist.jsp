<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<%@ include file="/WEB-INF/jsp/common/meta.jsp"%>
<%@ include file="/WEB-INF/jsp/common/title.jsp"%>
<script type="text/javascript">
	$(function() {
		$("#tsys_manager_list").flexigrid({
			url : "<%=request.getContextPath()%>/tsysAppController.do?method=query",// ajax方式对应的url地址
			type : 'post',// 数据发送方式 
			dataType : 'json',// 数据加载的类型(xml/json) 
			contentType : 'application/x-www-form-urlencoded; charset=UTF-8',
			params : [{
				"name" : "appShortname",
				"value" : $("#appShortnameId").val()
			}, {
				"name" : "appName",
				"value" : $("#appNameId").val()
			} ],
			sortname : "appId",//排序
			sortorder : "asc",
			useRp : true,
			radio : true,// 是否要多选框
			idProperty : 'appId',// 多选框绑定行的id
			singleSelect : true, //仅允许选择单行 
			rp : 15,
			showTableToggleBtn : false,
			width : 'auto',// 宽度值 
			height : $("body").height() - 120,// 插件的高度，单位为px   
			striped : true,// 是否显示斑纹效果，默认是奇偶交互的形式
			resizable : false,// 是否可伸缩   
			errormsg : '发生错误', //错误提升信息
			usepager : true, // 是否分页
			nowrap : true, //是否不换行               
			rpOptions : [ 10, 15, 20, 30, 40, 50 ],
			proitemg : '正在处理数据，请稍候 ...', //正在处理的提示信息                  
			colModel : [ {
				display : '序号',
				name : 'rownum_',
				width : 30,
				sortable : true,
				align : 'center',
				process : displayID   //扩展函数
			},
			{
				display : '<fmt:message key="tsysapp.appName" />',
				name : 'appName',
				width : 220,
				sortable : true,
				align : 'center'
			}, {
				display : '<fmt:message key="tsysapp.appIcon" />',
				name : 'appIcon',
				width : 200,
				sortable : true,
				align : 'center'
			}, {
				display : '<fmt:message key="tsysapp.appPath" />',
				name : 'appPath',
				width : 250,
				sortable : true,
				align : 'center'
			}, {
				display : '<fmt:message key="tsysapp.appShortname" />',
				name : 'appShortname',
				width : 200,
				sortable : true,
				align : 'center'
			} ]
		});
		

		function displayID(value,i){
			//返回当前页码
			var p = $("div.pGroup>.pcontrol>input").val();
			//返回数据行数组
			var tbl = $("#tsys_manager_list>tbody>tr");
			//返回当前行
			var $tr = $("#row"+i);
			//返回当前序号起始值,其中*前面为当前页码,后面为每页显示条数
			var startNum = (p-1)* parseInt($("select[name='rp']").val());
			//index+startNum,序号值
			var numID = (tbl.index($tr)+1) + startNum;
			//为对象赋值
			value.innerHTML = numID;

			return value;
		}
		var win = $("#win").window({
			title : "子系统基本信息",
			modal : true
		});
		var handler = function() {
			var hid = function() {
				win.window("close");
			};
			$("input[type='button']").click(hid);
		};
		$("#btn_add").click(
				function() {
					win.window('open');
					$("#msg").load("${ctx}/tsysAppController.do?method=add",
							"", handler);
				});
		$("#container").layout();
		$(window).resize(function() {
			$("#container").layout();
		});
		
	});
	//查询
	function queryUser() {
		$('#tsys_manager_list').flexOptions({
			params : [ {
				"name" : "appName",
				"value" : $("#appNameId").val()
			}, {
				"name" : "appShortname",
				"value" : $("#appShortnameId").val()
			}],
			contentType: "application/x-www-form-urlencoded; charset=UTF-8",
			newp:1
		}).flexReload();
	}
	
	//修改
	function editUser(){
		if($(".trSelected").length == 0 ){
			jQuery.messager.alert('提示:','请先选中一条记录!','info');
			return false;
		}
		var handler = function() {
			var hid = function() {
				win.window("close");
			};
			$("input[type='button']").click(hid);
		};
		//两种获取ID的方法：根据不同的属性获取ID
//		var id = $(".trSelected",$("#tsys_manager_list")).attr("id").replace("row","");
		var id = $(".trSelected", $("#tsys_manager_list")).attr("data-id");
		$("#win").window('open');
		$("#msg").load("${ctx}/tsysAppController.do?method=modify",
				{id:id}, handler);
	}
	//删除
	function destroyUser(){
		if($(".trSelected").length == 0 ){
			jQuery.messager.alert('提示:','请先选中一条记录!','info');
			return false;
		}
		if($(".trSelected").length >1 ){
			jQuery.messager.alert('提示:','删除时只能选择一条!','info');
			return false;
		}
		jQuery.messager.confirm('提示:','你确认要删除吗?',function(event){
			if(event){
				var appId = $(".trSelected",$("#tsys_manager_list")).attr("id").replace("row","");
				$.ajax({
					url: "${ctx}/tsysAppController.do?method=deleteApp",
					data:{id:appId},
					type: 'post',
					dataType: 'json',					
					success:function(data){
						if(data.flag == 'true'){
							jQuery.messager.alert('提示:','删除成功！', '', function(){
								reloadlist(); // 只刷新列表就可以了
							});
						}else{
							jQuery.messager.alert('提示:',data.msg, 'warning');
						}
					}
				});
			}
			}); 
	}
	function reloadlist(){
		$('#tsys_manager_list').flexOptions({
			contentType: "application/x-www-form-urlencoded; charset=UTF-8",
			newp:1
		}).flexReload();	
	}		
</script>
</head>
<body>
		<div style="position: relative;" id="container" fit="true"
			class="easyui-layout">
			<div region="north" border="false">
				<div id="query_area">
					<!-- 按钮栏布局end -->
					<!-- 查询条件区域begin-->
					<table class="FormView" border="0" cellspacing="1" cellpadding="0">
						<col class="Label" />
						<col class="Data" />
						<col class="Label" />
						<col class="Data" />
						<tr>
							<td align="left"><fmt:message key="tsysapp.appName" /></td>
							<td><input type="text" id="appNameId" name="srh_appName" class="text"
								value=""></td>
							<td align="left"><fmt:message key="tsysapp.appShortname" /></td>
							<td><input type="text" id="appShortnameId" name="srh_appShortname" class="text"
								value=""></td>
						</tr>
					</table>
				</div>
				<!-- 查询条件布局end-->
				<!-- 按钮栏布局begin -->
				<div id="toolbar_area" border="false">
					<a class="easyui-linkbutton" iconCls="icon-add" plain="true"
						id="btn_add"><fmt:message key="button.add" /></a> <a
						class="easyui-linkbutton" iconCls="icon-edit" plain="true"
						onclick="editUser()"><fmt:message key="button.edit" /></a> <a
						class="easyui-linkbutton" iconCls="icon-remove" plain="true"
						onclick="destroyUser()"><fmt:message key="button.remove" /></a> <a
						class="easyui-linkbutton" iconCls="icon-query" plain="true"
						onclick="queryUser()"><fmt:message key="button.query" /></a>
				</div>
			</div>
			<!-- 列表布局begin -->
			<div id="grid-body" class="grid-body" region="center">
				<div id="tsys_manager_list"></div>
			</div>

		</div>
		<!-- 表单窗口设计：新增、修改、查看明细 -->
		<div id="win" closed="true" style="width: 500px; height: 350px;">
			<div id="msg"></div>
		</div>
</body>
</html>