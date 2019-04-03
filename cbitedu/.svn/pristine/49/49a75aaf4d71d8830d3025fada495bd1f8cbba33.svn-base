<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<%@ include file="/WEB-INF/jsp/common/meta.jsp"%>
<%@ include file="/WEB-INF/jsp/common/title.jsp"%>
<script type="text/javascript">
	var win;
	var query = function() {
		var pStr = $("#cForm").serializeArray();
		$('#tsys_manager_list').flexOptions({
			params : pStr
		}).flexReload();
	}
	$(function() {
		var pStr = $("#cForm").serializeArray();
		$("#tsys_manager_list")
				.flexigrid(
						{
							url : "${ctx}/tsysRoleController.do?method=query",// ajax方式对应的url地址
							type : 'post',// 数据发送方式 
							dataType : 'json',// 数据加载的类型(xml/json)        
							contentType : 'application/x-www-form-urlencoded; charset=UTF-8',
							params : pStr,
							sortname : "role_name",//排序
							sortorder : "asc",
							useRp : true,
							radio : true,// 是否要多选框
							idProperty : 'roleId',// 多选框绑定行的id
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
							preProcess : function(d) {
								if (!d.rows)
									return d;
								$.each(d.rows, function(i, o) {
									o.roleName = "<a href=\"javascript:view('"
											+ o.roleId + "')\">" + o.roleName
											+ "</a>";
								});
								return d;
							},
							colModel : [ {
								display : '序号',
								name : 'rownum_',
								width : 50,
								sortable : true,
								align : 'center',
								process : displayID
							//扩展函数
							}, {
								display : '角色名称',
								name : 'roleName',
								width : 300,
								sortable : true,
								align : 'center'
							}, 
							{
								display : '角色类型',
								name : 'roleType',
								width : 80,
								sortable : true,
								align : 'center'
							}, 
							{
								display : '所属机构',
								name : 'org.orgName',
								width : 250,
								sortable : false,
								align : 'center'
							}, {
								display : '创建人',
								name : 'userinfo.userRealname',
								width : 150,
								sortable : false,
								align : 'center'
							}, {
								display : '创建时间',
								name : 'createDate',
								width : 150,
								sortable : true,
								align : 'center'
							} ]
						});

		$("#btn_add").click(function() {
			win = parent.sy.modalDialog({
				title : "角色基本信息",
				url : '${ctx}/tsysRoleController.do?method=add',
				height : 500,
				width : 800,
				onLoad : function() {
					var execWin = $(win).find("iframe").get(0).contentWindow;//子页面的window对象
					var execBody = execWin.document.body;//子页面的body DOM对象
					$(execBody).find("#btn_back").click(function() {
						execWin.back_click(win);
					});
					$(execBody).find("#btn_save").click(function() {
						execWin.save_click(win, query);
					});
				}
			});
		});
		$("#btn_edit").click(function() {
			var id = $(".trSelected", $("#tsys_manager_list")).attr("data-id");
			if (!id) {
				$.messager.alert("操作提示", "请选择一条记录", "info");
				return;
			}
			var flag = false;
			$(".trSelected td:nth-child(4) div", $("#tsys_manager_list")).each(function(){
				if($(this).text()=='流程角色'){
					jQuery.messager.alert('提示:', '角色类型为流程角色的记录不允许删除操作!', 'info');
					flag = true;
				}
			});
			var flsag;
			if(flag){
				flsag="xx";
			}
			win = parent.sy.modalDialog({
				title : "角色基本信息",
				height : 500,
				width : 800,
				url : "${ctx}/tsysRoleController.do?method=modify&id="+ id+"&flsag="+flsag,
				onLoad : function() {
					var execWin = $(win).find("iframe").get(0).contentWindow;//子页面的window对象
					var execBody = execWin.document.body;//子页面的body DOM对象
					$(execBody).find("#btn_back").click(function() {
						execWin.back_click(win);
					});
					$(execBody).find("#btn_save").click(function() {
						execWin.save_click(win, query);
					});
				}
			});
		});
		$("#btn_del").click(function() {
			var id = $(".trSelected", $("#tsys_manager_list")).attr("data-id");
		if (!id) {
				$.messager.alert("操作提示", "请选择一条记录", "info");
				return;
			}
		var flag = true;
		$(".trSelected td:nth-child(4) div", $("#tsys_manager_list")).each(function(){
			if($(this).text()=='流程角色'){
				jQuery.messager.alert('提示:', '角色类型为流程角色的记录不允许删除操作!', 'info');
				flag = false;
			}
		});
		if(!flag) return;
			$.messager.confirm('提示:','你确认要删除吗?',function(event){
				if(event){
					$.get("${ctx}/tsysRoleController.do?method=del", {
						id : id
					}, function(data) {
						if (data == "0") {
							$.messager.alert("操作提示", "该角色已被用户使用，不能删除", "info");
						} else {
							query();
						}
					});
				}
			});
		});
		$("#btn_setUser")
				.click(
						function() {
							var id = $(".trSelected", $("#tsys_manager_list"))
									.attr("data-id");
							if (!id) {
								$.messager.alert("操作提示", "请选择一条记录", "info");
								return;
							}
							win = parent.sy
									.modalDialog({
										title : "角色基本信息",
										height : 650,
										width : 800,
										url : "${ctx}/tsysRoleController.do?method=userRole&roleId="
												+ id,
										onLoad : function() {
											var execWin = $(win).find("iframe")
													.get(0).contentWindow;//子页面的window对象
											var execBody = execWin.document.body;//子页面的body DOM对象
											$(execBody)	.find("#btn_back").click(
											function() {
												execWin.back_click(win);
											});
											$(execBody)
													.find("#btn_save")
													.click(
															function() {
																execWin
																		.save_click(
																				win,
																				query);
															});
										}
									});
						});
		$("#container").layout();
		$(window).resize(function() {
			$("#container").layout();
		});
	});
	function displayID(value, i) {
		//返回当前页码
		var p = $("div.pGroup>.pcontrol>input").val();
		//返回数据行数组
		var tbl = $("#tsys_manager_list>tbody>tr");
		//返回当前行
		var $tr = $("#row" + i);
		//返回当前序号起始值,其中*前面为当前页码,后面为每页显示条数
		var startNum = (p - 1) * parseInt($("select[name='rp']").val());
		//index+startNum,序号值
		var numID = (tbl.index($tr) + 1) + startNum;
		//为对象赋值
		value.innerHTML = numID;

		return value;
	}

	function view(id) {
		win = parent.sy.modalDialog({
			title : "角色基本信息",
			height : 700,
			width : 800,
			url : "${ctx}/tsysRoleController.do?method=view&id=" + id,
			onLoad : function() {
				var execWin = $(win).find("iframe").get(0).contentWindow;//子页面的window对象
				var execBody = execWin.document.body;//子页面的body DOM对象
				$(execBody).find("#btn_back").click(function() {
					execWin.back_click(win);
				});
			}
		});
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
				<form action="" id="cForm">
					<table class="FormView" border="0" cellspacing="1" cellpadding="0">
						<col class="Label" />
						<col class="Data" />
						<col class="Label" />
						<col class="Data" />
						<tr>
							<td align="left"><fmt:message key="tsysrole.roleName" /></td>
							<td><input type="text" name="roleName" class="text" value=""></td>
						</tr>
					</table>
				</form>
			</div>
			<!-- 查询条件布局end-->
			<!-- 按钮栏布局begin -->
			<div id="toolbar_area" border="false">
				<cbitedu:auth operate_code="QUERY" module_code="T_SYS_ROLE" id="btn_query"
					icon="icon-query" onclick="query()" cssClass="easyui-linkbutton"
					value="button.query" type="link" plain="true"/>
				<cbitedu:auth operate_code="ADD" module_code="T_SYS_ROLE" type="link"
					cssClass="easyui-linkbutton"  id="btn_add" value="button.add" icon="icon-add" plain="true"/>
				<cbitedu:auth operate_code="EDIT" module_code="T_SYS_ROLE"
					cssClass="easyui-linkbutton" value="button.edit" id="btn_edit" icon="icon-edit" type="link" plain="true"/>
				<cbitedu:auth operate_code="DELETE" module_code="T_SYS_ROLE" id="btn_del"
					cssClass="easyui-linkbutton" value="button.remove" icon="icon-remove" type="link" plain="true"/>
				<cbitedu:auth operate_code="ROLE_USER" type="link"  plain="true" cssClass="easyui-linkbutton" module_code="T_SYS_ROLE" id="btn_setUser" value="button.assign.user" icon="icon-edit"/>
			</div>
		</div>
		<!-- 列表布局begin -->
		<div id="grid-body" class="grid-body" region="center">
			<div id="tsys_manager_list"></div>
		</div>

	</div>
</body>
</html>