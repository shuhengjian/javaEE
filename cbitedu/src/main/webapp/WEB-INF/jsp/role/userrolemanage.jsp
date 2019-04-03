<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="/WEB-INF/jsp/common/meta.jsp"%>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<%@ include file="/WEB-INF/jsp/common/title.jsp"%>
<script type="text/javascript" src="${ctx}/js/jquery.form.js"></script>
<link rel="stylesheet" type="text/css" href="${ctx}/ui_widget/ztree/css/demo.css">
<script type="text/javascript">
	var zTree,zNodes;
	$(function() {
		var pStr=$("#cForm").serializeArray();
		$("#userinfo_manager_list").flexigrid({
			url : "${ctx}/tsysUserinfoController.do?method=query",// ajax方式对应的url地址
			type : 'post',// 数据发送方式 
			dataType : 'json',// 数据加载的类型(xml/json)        
			contentType : 'application/x-www-form-urlencoded; charset=UTF-8',
			params : pStr,
			sortname : "user_id",//排序
			sortorder : "asc",
			useRp : true,
			checkbox : true,// 是否要多选框
			idProperty : 'userId',// 多选框绑定行的id
			singleSelect : false, //仅允许选择单行 
			rp : 15,
			showTableToggleBtn : false,
			width : 'auto',// 宽度值 
			height : 'auto',// 插件的高度，单位为px   
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
				width : 220,
				sortable : true,
				align : 'center'
			}, {
				display : '用户实名',
				name : 'userRealname',
				width : 200,
				sortable : true,
				align : 'center'
			}, {
				display : '手机号',
				name : 'mobiletel',
				width : 220,
				sortable : true,
				align : 'center'
			},{
				display : '性别',
				name : 'sex',
				width : 250,
				sortable : true,
				align : 'center'
			}],
			onChecked:true,
			checkCommd:function(obj){
				var chk=obj;
				var id=$(obj).parent().parent().attr("data-id");
				if(chk.checked){
					$("#saveForm").append($("<input type='hidden' name='userId' value='"+id+"'/>"));
				}else{
					$(":hidden[name='userId'][value='"+id+"']").remove();
				}
			},
			onSuccess:function(){
				//默认选中效果
				$("#userinfo_manager_list tr[data-id]").each(function(){//alert(":hidden[name='userId'][value='"+this.value+"']");
					var id=$(this).attr("data-id");
					if($(":hidden[name='userId'][value='"+id+"']").length>0){
						$(this).find(":checkbox").attr("checked","checked");
					}
				});
			}
		});
		var win = $("#win").window({
			title : "用户基本信息",
			modal : true
		});
		var handler = function() {
			var hid = function() {
				win.window("close");
			};
			$("input[type='button']").click(hid);
		};
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
		
		$("#container").layout();
		$(window).resize(function() {
			$("#container").layout();
		});
		//获取数据 机构树
		$.ajax({
        	type:'post',
        	url:'${ctx}/tsysOrgController.do?method=queryOrgList',
        	dataType:'json',
        	contentType: "application/x-www-form-urlencoded;charset=utf-8",
        	success: function(data){
        		var setting = {
       				check: {
       					enable: true,
       					chkStyle: "radio",
       					radioType: "all"
       				},
       				view: {
       					dblClickExpand: false
       				},
       				data: {
       					simpleData: {
       						enable: true,
       						idKey: "id",   
							pIdKey: "pid",   
							rootPId: 0 
       					}
       				},
       				callback: {
       					onClick: onClick,
       					onCheck: onCheck
       				}
       			};
        		zNodes = data;
				ztree=$.fn.zTree.init($("#treeDemo"), setting, zNodes);
				ztree.expandAll(true);
			}
		});
		
	});
	
	//查询
	function query() {
		var pStr=$("#cForm").serializeArray();
		$('#userinfo_manager_list').flexOptions({
			params : pStr,
			contentType: "application/x-www-form-urlencoded; charset=UTF-8",
			newp:1
		}).flexReload();
	}
	//返回
	function back_click(win) {
		win.dialog("close");
	}
	//保存
	function save_click(win,query){
		$("#saveForm").ajaxSubmit(function(data){  // 异步提交表单数据
			if(data.success){
				jQuery.messager.alert('提示:', data.msg, 'info',function(){
					query();
					back_click(win);
				});
				
			}else{
				jQuery.messager.alert('提示:', data.msg,'info');
			}
		});
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
		$('#sex').val("");
		$('#state').val("");
		$('#queryType').val("0");
		query();
	}

	function onClick(e, treeId, treeNode) {
		var zTree = $.fn.zTree.getZTreeObj("treeDemo");
		zTree.checkNode(treeNode, !treeNode.checked, null, true);
		return false;
	}

	function onCheck(e, treeId, treeNode) {
		var zTree = $.fn.zTree.getZTreeObj("treeDemo"),
		nodes = zTree.getCheckedNodes(true),
		v = "";
		vName = "";
		for (var i=0, l=nodes.length; i<l; i++) {
			v += nodes[i].id + ",";
			vName += nodes[i].name + ",";
		}
		if (v.length > 0 ) v = v.substring(0, v.length-1);
		if (vName.length > 0 ) vName = vName.substring(0, vName.length-1);
		$("#orgId").attr("value", v);
		$("#orgName").attr("value",vName);
	}
	function showMenu() {
		var cityObj = $("#orgName");
		var cityOffset = $("#orgName").offset();
		$("#orgContent").css({left:cityOffset.left + "px", top:cityOffset.top + cityObj.outerHeight() + "px"}).slideDown("fast");
		$("body").bind("mousedown", onBodyDown);
	}
	
	function hideMenu() {
		$("#orgContent").fadeOut("fast");
		$("body").unbind("mousedown", onBodyDown);
	}
	
	function onBodyDown(event) {
		if (!(event.target.id == "menuBtn" || event.target.id == "orgName" || event.target.id == "orgContent" || $(event.target).parents("#orgContent").length>0)) {
			hideMenu();
		}
	}
</script>
</head>
<body>
	<div style="position: relative;" id="container" fit="true" class="easyui-layout">
		<div region="north" border="false">
			<div id="query_area">
				<!-- 按钮栏布局end -->
				<!-- 查询条件区域begin-->
				<form id="cForm">
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
					<tr>
					   	<td align="left">
							<fmt:message key="org.orgName" />
						</td>
						<td colspan="3">
							<input type="hidden" name="orgId" id="orgId"/>
							<input id="orgName" name="orgName" type="text" readonly value="" class="text" onclick="showMenu();" />
							&nbsp;<a id="menuBtn" href="#" onclick="showMenu(); ">选择</a>
						</td>
					</tr>
				</table>
				</form>
			</div>
			<!-- 查询条件布局end-->
			
			<!-- 按钮栏布局begin -->
			<div id="toolbar_area" border="false">
				<a class="easyui-linkbutton" iconCls="icon-query" plain="true" onclick="query()"><fmt:message key="button.query" /></a>
				<a class="easyui-linkbutton" iconCls="icon-reload" plain="true" onclick="queryReset()"><fmt:message key="button.reset" /></a>
				<a class="easyui-linkbutton" iconCls="icon-edit" plain="true" id="btn_save"><fmt:message key="button.save" /></a>
			</div>
		</div>
		
		<!-- 列表布局begin -->
		<div id="grid-body" class="grid-body" region="center">
			<div id="userinfo_manager_list"></div>
		</div>
	</div>
	
	<!-- 表单窗口设计：新增、修改、查看明细 -->
	<div id="win" closed="true" style="width: 700px; height: 300px;">
		<div id="msg"></div>
	</div>
	
	<!-- 机构树选择层 -->
	<div id="orgContent" class="menuContent" style="display:none; position: absolute;">
		<ul id="treeDemo" class="ztree" style="margin-top:0; width:180px; height: 300px;"></ul>
	</div>
	<form action="${ctx }/tsysRoleController.do?method=saveUserRole" id="saveForm">
		<c:forEach items="${urList}" var="v">
			<input type="hidden" name="userId" value="${v.userId }"/>
		</c:forEach>
		<input type="hidden" name="roleId" value="${roleId }"/>
	</form>
</body>
</html>