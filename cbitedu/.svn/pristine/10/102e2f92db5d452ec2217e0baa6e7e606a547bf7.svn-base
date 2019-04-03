<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="/WEB-INF/jsp/common/meta.jsp"%>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<%@ include file="/WEB-INF/jsp/common/title.jsp"%>

<script type="text/javascript">
var myWindow;
	$(function() {
		var g=$("#userinfo_manager_list").flexigrid({
			url : "${ctx}/tsysUserinfoController.do?method=query&orgId=${orgId}",// ajax方式对应的url地址
			type : 'post',// 数据发送方式 
			dataType : 'json',// 数据加载的类型(xml/json)        
			contentType : 'application/x-www-form-urlencoded; charset=UTF-8',
			params : [ {
				"name" : "login_name",
				"value" : $("#login_name").val()
			}, {
				"name" : "user_realname",
				"value" : $("#user_realname").val()
			},{
				"name" : "mobiletel",
				"value" : $("#mobiletel").val()
			}, {
				"name" : "sex",
				"value" : $("#sex").val()
			}, {
				"name" : "state",
				"value" : $("#state").val()
			}, {
				"name" : "queryType",
				"value" : $("#queryType").val()
			}  ],
			sortname : "user_id",//排序
			sortorder : "asc",
			useRp : true,
			checkbox : true,// 是否要多选框
			//rowId : 'user_id',// 多选框绑定行的id
			idProperty : 'userId',// 多选框绑定行的id
			singleSelect : false, //仅允许选择单行 
			rp : 15,
			showTableToggleBtn : false,
			width : 'auto',// 宽度值 
			height : $("body").height() - 180,// 插件的高度，单位为px   
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
				process : displayID,   //扩展函数
				align : 'center'
			}, {
				display : '登录名称',
				name : 'loginName',
				width : 200,
				sortable : true,
				process : displayName,   //扩展函数
				align : 'center'
			}, {
				display : '用户实名',
				name : 'userRealname',
				width : 160,
				sortable : true,
				align : 'center'
			}, {
				display : '手机号',
				name : 'mobiletel',
				width : 160,
				sortable : true,
				align : 'center'
			},{
				display : '性别',
				name : 'sex',
				width : 100,
				sortable : true,
				align : 'center'
			},{
				display : '状态',
				name : 'state',
				width : 80,
				sortable : true,
				align : 'center'
			}]
		});
		
		function displayID(value,i){
			//返回当前页码
			var p = $("div.pGroup>.pcontrol>input").val();
			//返回数据行数组
			var tbl = $("#userinfo_manager_list>tbody>tr");
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
		/*姓名显示超链接*/
		function displayName(value,i){
			value.innerHTML = '<a onclick=showUserInfo("'+i+'") href="javascript:void(0)">'+value.innerHTML+'</a>';
			return value;
		}
		
		$("#btn_add").click(
				function() {
					myWindow = parent.parent.sy.modalDialog({
						height:240,
						width:680,
						title : "用户基本信息",
						url: '${ctx}/tsysUserinfoController.do?method=add&orgId='+'${orgId}',
						//data:{org_id:'${orgId}'},		
						onLoad:function(){
							var execWin=$(myWindow).find("iframe").get(0).contentWindow;//子页面的window对象
							var execBody=execWin.document.body;//子页面的body DOM对象
							$(execBody).find("#btn_back").click(function(){
								execWin.back_click(myWindow);
							});
							$(execBody).find("#btn_save").click(function(){
								execWin.save_click(myWindow,query);
							});
						}
					});
				}); 
		//修改
		$("#btn_edit").click(function() {
	        var lengthFlag = $(".trSelected").length;
			if(lengthFlag == 0 ){
				jQuery.messager.alert('提示:','请先选中一条记录!','info');
				return;
			}else if(lengthFlag > 1){
				jQuery.messager.alert('提示：','每次修改请选择一条记录！','info');
				return ;
			}		
			var id = $(".trSelected",$("#userinfo_manager_list")).attr("data-id");
			myWindow = parent.parent.sy.modalDialog({
				height:240,
				title : "用户修改",
				url: '${ctx}/tsysUserinfoController.do?method=modify&id='+id,
				onLoad:function(){
					var execWin=$(myWindow).find("iframe").get(0).contentWindow;//子页面的window对象
					var execBody=execWin.document.body;//子页面的body DOM对象
					$(execBody).find("#btn_back").click(function(){
						execWin.back_click(myWindow);
					});
					$(execBody).find("#btn_save").click(function(){
						execWin.save_click(myWindow,query);
					});
				}
			});
		});
		
		//用户角色分配
		$("#btn_assign").click(function() {
					myWindow = parent.parent.sy.modalDialog({
						title : "角色分配",
						url: '${ctx}/tsysUserinfoController.do?method=setRole',
						onLoad:function(){
							var execWin=$(myWindow).find("iframe").get(0).contentWindow;//子页面的window对象
							var execBody=execWin.document.body;//子页面的body DOM对象
							$(execBody).find("#btn_back").click(function(){
								execWin.back_click(myWindow);
							});
							$(execBody).find("#btn_save").click(function(){
								execWin.save_click(myWindow,query);
							});
						}
					});
					
		});
		
		//用户排序
		
			$("#btn_sort").click(function() {
				  if('${orgId}'==null || '${orgId}'=='' || '${orgId}'==0 )
					 {
					 jQuery.messager.alert('提示:','请先选择一条机构!','info');
					 return;
					 }
				        myWindow = parent.sy.modalDialog({
						title : "用户排序",
						close : false,
						url: '${ctx}/tsysUserinfoController.do?method=tomove&treeid='+'${orgId}',
						onLoad:function(){
							var execWin=$(myWindow).find("iframe").get(0).contentWindow;//子页面的window对象
							var execBody=execWin.document.body;//子页面的body DOM对象
							$(execBody).find("#btn_save").click(function(){
								execWin.save_sort(reloadlist);
								
							});
							
						}
				       });
			       
		});
		
		
		$("#container").layout();
		$(window).resize(function() {
			$("#container").layout();
		});
	});
	
	//查询
	function query() {
		$('#userinfo_manager_list').flexOptions({
			params : [ {
				"name" : "login_name",
				"value" : $("#login_name").val()
			}, {
				"name" : "user_realname",
				"value" : $("#user_realname").val()
			},{
				"name" : "mobiletel",
				"value" : $("#mobiletel").val()
			}, {
				"name" : "sex",
				"value" : $("#sex").val()
			}, {
				"name" : "state",
				"value" : $("#state").val()
			}, {
				"name" : "queryType",
				"value" : $("#queryType").val()
			}   ],
			contentType: "application/x-www-form-urlencoded; charset=UTF-8",
			newp:1
		}).flexReload();
	}
	
	function reloadlist(){
		query();		
	}
	/*
	* 查询表单重置
	*/
	function queryReset(){
		$('#login_name').val('');
		$('#user_realname').val('');
		$('#mobiletel').val('');
		//$('#sex option:first').attr('selected','selected');
		//$('#state option:first').attr('selected','selected');
		//$('#queryType option:first').attr('selected','selected');//这样写 火狐下不生效
		$('#sex').val("");
		$('#state').val("");
		$('#queryType').val("0");
		query();
	}
	/* 停用 改变用户状态为停用 */
	function disableUser(){
		var lengthFlag = $(".trSelected").length;
		if(lengthFlag == 0 ){
			jQuery.messager.alert('提示:','请先选中一条记录!','info');
			return;
		}else if(lengthFlag > 1){
			jQuery.messager.alert('提示：','一次请选择一条记录停用！','info');
			return ;
		}
		var id = $(".trSelected",$("#userinfo_manager_list")).attr("data-id");
		$.ajax({
			url: "${ctx}/tsysUserinfoController.do?method=changeUserState",
			type: "POST",
			data: {id:id,type:0},
			dataType: 'json',
			error: function(){
				alert("发生错误!");
			},
			success: function(data){
				if(data.flag){
					jQuery.messager.alert('提示:', data.msg, 'info', function(){
						reloadlist();
					});
					$("#win").window("close");
				}else{
					jQuery.messager.alert('提示:', data.msg,'info');
				}
			}
		});   
	}
	/* 启用 改变用户状态为启用*/
	function enableUser(){
		var lengthFlag = $(".trSelected").length;
		if(lengthFlag == 0 ){
			jQuery.messager.alert('提示:','请先选中一条记录!','info');
			return;
		}else if(lengthFlag > 1){
			jQuery.messager.alert('提示：','一次只能选择一条记录！','info');
			return ;
		}
		var id = $(".trSelected",$("#userinfo_manager_list")).attr("data-id");
		$.ajax({
			url: "${ctx}/tsysUserinfoController.do?method=changeUserState",
			type: "POST",
			data: {id:id,type:1},
			dataType: 'json',
			error: function(){
				alert("发生错误!");
			},
			success: function(data){
				if(data.flag){
					jQuery.messager.alert('提示:', data.msg, 'info', function(){
						reloadlist();
					});
					$("#win").window("close");
				}else{
					jQuery.messager.alert('提示:', data.msg,'info');
				}
			}
		});   
	}
	
	function resetPassword(){
		var lengthFlag = $(".trSelected").length;
		if(lengthFlag == 0 ){
			jQuery.messager.alert('提示:','请先选中一条记录!','info');
			return;
		}else if(lengthFlag > 1){
			jQuery.messager.alert('提示：','一次只能选择一条记录！','info');
			return ;
		}
		var id = $(".trSelected",$("#userinfo_manager_list")).attr("data-id");
		$.ajax({
			url: "${ctx}/tsysUserinfoController.do?method=resetPassword",
			type: "POST",
			data: {id:id},
			dataType: 'json',
			error: function(){
				alert("发生错误!");
			},
			success: function(data){
				if(data.flag){
					jQuery.messager.alert('提示:', data.msg, 'info', function(){
						reloadlist();
					});
				}else{
					jQuery.messager.alert('提示:', data.msg,'info');
				}
			}
		});   
	}
	
	function showUserInfo(id){
		var dialog = parent.sy.modalDialog({
				title : '用户信息',
				url : "${ctx}/tsysUserinfoController.do?method=showUserInfo&id="+id
		}); 
	}
	
	function delRolePrivilege() {
				if ($(".trSelected").length == 0) {
					jQuery.messager.alert('提示:', '请先选中一条记录!', 'info');
					return false;
				}
				
				jQuery.messager.confirm('提示:', '你确认要回收用户已经分配的所有角色吗?', function(event) {
					if (event) {
						var userIds = [];
						$(".trSelected", $("#userinfo_manager_list")).each(
								function() {
									userIds.push($(this).attr("data-id"));
								});
						
						$.ajax({
							url : "${ctx}/tsysUserinfoController.do",
							data : {
								method : "delRolePrivilege",
								userIds : userIds
							},
							traditional : true, // 加上这个属性，可以直接传数组类型的数据
							type : 'post',
							dataType : 'json',
							success : function(data) {
								if (data.flag) {
									jQuery.messager.alert('提示:', '回收权限成功！',
											'', function() {
												reloadlist(); // 只刷新列表就可以了
											});
								} else {
									jQuery.messager.alert('提示:', '回收权限失败！',
											'warning');
								}
							}
						});
					}
				});
			}
	
	//导出
	function exportExcel(){
		var cols = $('#userinfo_manager_list').getCheckCol();
		var column = new Array();
		$(cols).each(function(){
			column.push(this.name);
		});
		//alert(arr);
		var url = "<%=request.getContextPath()%>/tsysUserinfoController.do?method=export&column="+column;
	    $('#userinfo_form').attr("action",url).submit();
	}	
	
</script>
</head>
<body>

	<div style="position: relative;" id="container" fit="true" class="easyui-layout">		
		<div region="north" border="false">
			<div id="query_area">
				<!-- 按钮栏布局end -->
				<!-- 查询条件区域begin-->
				<form id="userinfo_form"  action="" method="post">
				<input type="hidden" name="orgId" value="${orgId}">
				<table class="FormView" border="0" cellspacing="1" cellpadding="0">
					<colgroup>
					<col class="Label" />
					<col class="Data" />
					<col class="Label" />
					<col class="Data" />
					</colgroup>
					<tr>
						<td align="left">
							<fmt:message key="userinfo.loginName" />
						</td>
						<td>
							<input type="text" name="login_name" id="login_name" class="text" value="">
						</td>
						<td align="left">
							<fmt:message key="userinfo.userRealname" />
						</td>
						<td>
							<input type="text" name="user_realname" id="user_realname" class="text" value="">
						</td>
					</tr>
					
					<tr>
						<td align="left">
							<fmt:message key="userinfo.mobiletel" />
						</td>
						<td>
							<input type="text" name="mobiletel" id="mobiletel" class="text" value="">
						</td>
						<td align="left">
							<fmt:message key="userinfo.sex" />
						</td>
						<td>
							<select id="sex" name="sex" class="select">
								<option value="">全部</option>
								<option value="男">男</option>
								<option value="女">女</option>
						    </select>
						</td>
					</tr>
					
					<tr>
					   	<td align="left">
							<fmt:message key="userinfo.state" />
						</td>
						<td>
						<select id="state" name="state" class="select">
							<option value="">全部</option>
							<option value="1">启用</option>
							<option value="0">停用</option>
							<option value="2">调岗</option>
						</select>
						</td>
					    <td align="left">
					      <fmt:message key="query.type" />
						</td>
						<td>
						<select id="queryType" name="queryType" class="select">
							<option value="0">不递归机构查询</option>
							<option value="1">递归机构查询</option>
						</select>
						</td>
					</tr>
				</table>
				</form>
			</div>
			<!-- 查询条件布局end-->
			
			<!-- 按钮栏布局begin -->
			<div id="toolbar_area" border="false">
				<cbitedu:auth operate_code="QUERY" module_code="T_SYS_USER" id="btn_query"
					icon="icon-query" onclick="query()" cssClass="easyui-linkbutton"
					value="button.query" type="link" plain="true"/>
				<cbitedu:auth operate_code="RELOAD" module_code="T_SYS_USER" 
					icon="icon-add" onclick="queryReset()" cssClass="easyui-linkbutton"
					value="button.reset" type="link" plain="true"/>
				<cbitedu:auth operate_code="ADD" module_code="T_SYS_USER" id="btn_add"
					icon="icon-query" onclick="query()" cssClass="easyui-linkbutton"
					value="button.add" type="link" plain="true"/>
				<cbitedu:auth operate_code="EDIT" module_code="T_SYS_USER" id="btn_edit"
					icon="icon-edit" onclick="query()" cssClass="easyui-linkbutton"
					value="button.edit" type="link" plain="true"/>
				<cbitedu:auth operate_code="STOP" module_code="T_SYS_USER"
					icon="icon-no" onclick="disableUser()" cssClass="easyui-linkbutton"
					value="button.disable" type="link" plain="true"/>
				<cbitedu:auth operate_code="ENABLE" module_code="T_SYS_USER" 
					icon="icon-ok" onclick="enableUser()" cssClass="easyui-linkbutton"
					value="button.enable" type="link" plain="true"/>
				<cbitedu:auth operate_code="RESET" module_code="T_SYS_USER" 
					icon="icon-reset" onclick="resetPassword()" cssClass="easyui-linkbutton"
					value="button.resetpassword" type="link" plain="true"/>
				<cbitedu:auth operate_code="AUTH_ROLE" module_code="T_SYS_USER" id="btn_assign"
					icon="icon-user"  cssClass="easyui-linkbutton"
					value="button.assign.role" type="link" plain="true"/>
				<cbitedu:auth operate_code="CANCEL" module_code="T_SYS_USER" 
					icon="icon-reset" onclick="delRolePrivilege()" cssClass="easyui-linkbutton"
					value="button.role.reset" type="link" plain="true"/>
				<cbitedu:auth operate_code="SORT" module_code="T_SYS_USER" id="btn_sort"
					icon="icon-user"  cssClass="easyui-linkbutton"
					value="button.move" type="link" plain="true"/>
				<cbitedu:auth operate_code="EXPORT" module_code="T_SYS_USER" id="btn_export"
					icon="icon-excel"  cssClass="easyui-linkbutton"
					value="button.excel" type="link" plain="true" onclick="exportExcel()"/>
			</div>
		</div>
		
		<!-- 列表布局begin -->
		<div id="grid-body" class="grid-body" region="center">
			<div id="userinfo_manager_list"></div>
		</div>
	</div>
	
	<!-- 表单窗口设计：新增、修改、查看明细 -->
	<div id="win" closed="true" style="width: 700px; height: 250px;">
		<div id="msg"></div>
	</div>

</body>
</html>