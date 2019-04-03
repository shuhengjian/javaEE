<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="/WEB-INF/jsp/common/meta.jsp"%>
<%@ include file="/WEB-INF/jsp/common/title.jsp"%>
<style>
.menuPic{vertical-align:middle;}
.menuDiv{display:inline;}
.menuDivRight{float:right;margin-right:20px}
</style>
<script type="text/javascript" src='${ctx}/js/index.js'>
</script>
<script type="text/javascript" charset="utf-8">
	var _menus = {};
	var menuData=$.parseJSON('${moduleNo}');
	// 调用javascript中的convertData()方法，这个方法使用了递归算法，迭代出一棵菜单树。
	_menus.menus = convertData(menuData, {
		menuid : "0"
	});
	$(function() {
		// 修改密码
		$('#passwordDialog').show().dialog({
			modal : true,
			closable : true,
			iconCls : 'icon-edit',
			resizable : true,
			buttons : [ {
				text : '修改',
				handler : function() {
					if ($('#passwordDialog form').form('validate')) {
						$.post('${ctx}/index.do?method=modifyPwd', {
							'data_pwd' : $('#pwd').val()
						}, function(data) {
							if (data.result) {
								$.messager.alert('提示', '密码修改成功！', 'info');
								$('#passwordDialog').dialog('close');
							}
						}, 'json');
					}
				}
			} ],
			onOpen : function() { //打开窗口以前触发
				$('#passwordDialog form :input').val('');
			}
		}).dialog('close');

		// 锁定用户用到的登录框
		$('#loginDialog').show().dialog({
			modal : true,
			closable : false,
			iconCls : 'icon-lock',
			buttons : [ {
				id : 'loginBtn',
				text : '登录',
				handler : function() {
					loginFun();
				}
			} ],
			onOpen : function() {
				$('#loginDialog form :input[name="password"]').val('');
				$('form :input').keyup(function(event) {
					if (event.keyCode == 13) {
						loginFun();
					}
				});
			}
		}).dialog('close');
		
		loadListMenu();
	});

	// 锁定用户方法
	var lockWindowFun = function() {
		$.post('${ctx}/index.do?method=lockWindow', function(data) {
			if (data.result) {
				$('#loginDialog').dialog('open');
				$('#loginName').val(data.loginName);
			}
		}, 'json');
	};

	// 登录方法
	var loginFun = function() {
		if ($('#loginDialog form').form('validate')) {
			$('#loginBtn').linkbutton('disable');
			$.post('${ctx}/index.do?method=lockLogin', $('#loginDialog form')
					.serialize(), function(data) {
				if (data.result == 0) {
					$('#loginDialog').dialog('close');
					window.location.href = "${ctx}/index.do?method=toIndex";
				} else {
					$.messager.alert('提示', data.msg, 'error', function() {
						$('#loginDialog form :input:eq(1)').focus();
					});
				}
				$('#loginBtn').linkbutton('enable');
			}, 'json');
		}
	};

	// 退出登录
	var logoutFun = function() {
		window.location.href = "${ctx}/index.do?method=logout";
	};

	// 显示我的信息
	var showMyInfoFun = function() {
		var dialog = parent.sy.modalDialog({
			title : '我的信息',
			url : '${ctx}/index.do?method=showMyInfo'
		});
	};
	
	
</script>
<body class="easyui-layout">
	<!-- 头部标题 -->
	<noscript>
		<div
			style="position: absolute; z-index: 100000; height: 2046px; top: 0px; left: 0px; width: 100%; background: white; text-align: center;">
			<img src="images/noscript.gif" alt='抱歉，请开启脚本支持！' />
		</div>
	</noscript>
	<div region="north" class="loginovertop" split="false" border="false"
		style="overflow: hidden;  height:65px;
        background:url(${ctx}/images/home_topbg.jpg) repeat-x;margin:0 auto; padding:0;font-family: Verdana, 微软雅黑,黑体">
		<span
			style="float: left; width: 734px; height: 68px; background: #D2E0F2"><img
			src="${ctx}/images/cp-logo.png" /></span>

		<div id="sessionInfoDiv"
			style="position: absolute; right: 10px; top: 5px;">
			<span style="float: right; margin-top: 6px; margin-right: 4px"><font
				color="#000000"
				style="font-family: '微软雅黑', Courier New; font-size: 13px;">
					欢迎:&nbsp;${sessionScope.defaultOrg[0].orgName}----${sessionScope.userinfo.userRealname} </font></span>
		</div>

		<div style="position: absolute; right: 0px; bottom: 0px;">
			<a href="javascript:void(0);" class="easyui-menubutton"
				data-options="menu:'#layout_north_pfMenu',iconCls:'icon-themes'">更换皮肤</a>
			<a href="javascript:void(0);" class="easyui-menubutton"
				data-options="menu:'#layout_north_kzmbMenu',iconCls:'icon-panel'">控制面板</a>
			<a href="javascript:void(0);" class="easyui-menubutton"
				data-options="menu:'#layout_north_zxMenu',iconCls:'icon-quit'">注销</a>
		</div>
		<div id="layout_north_pfMenu" style="width: 120px; display: none;">
			<div onclick="sy.changeTheme('default');" title="default">default</div>
			<div onclick="sy.changeTheme('metro');" title="metro">metro</div>
			<div onclick="sy.changeTheme('bootstrap');" title="bootstrap">bootstrap</div>
		</div>
		<div id="layout_north_kzmbMenu" style="width: 100px; display: none;">
			<div data-options="iconCls:'icon-edit'"
				onclick="javascript:$('#passwordDialog').dialog('open');">修改密码</div>
			<div class="menu-sep"></div>
			<div data-options="iconCls:'icon-user'" onclick="showMyInfoFun();">我的信息</div>
		</div>
		<div id="layout_north_zxMenu" style="width: 100px; display: none;"
			class="tool-btn">
			<div data-options="iconCls:'icon-lock'" onclick="lockWindowFun();">锁定窗口</div>
			<div class="menu-sep"></div>
			<div data-options="iconCls:'icon-save'" onclick="logoutFun();">退出系统</div>
		</div>

	</div>

	<!-- 左侧导航 -->
	<div data-options="region:'west',split:true, fit:false" title="<div class='menuDiv'>导航菜单</div> <div class='menuDiv menuDivRight'><img onclick='changeMenuStyle(this)' class='menuPic' src='${ctx }/ui_widget/easyui/themes/icons/menustyletree.gif'/>
	<img class='menuPic' onclick='changeMenuStyle(this)' src='${ctx }/ui_widget/easyui/themes/icons/menustylelist.gif'/></div>"
		style="width: 180px">
		<div id="nav"></div>
		<ul id="menuTree" class="ztree"></ul>
	</div>

	<!-- 页脚信息 -->
	<div data-options="region:'south',border:false"
		style="height: 20px; background: #F3F3F3; padding: 2px; vertical-align: middle;">
		<span id="sysVersion">系统版本：V1.0</span>
	</div>

	<!-- 内容tabs -->
	<div id="center" data-options="region:'center'">
		<div id="tabs" class="easyui-tabs"
			data-options="fit:true,border:false">
			<div title="首页" style="padding: 5px; display: block;"></div>
		</div>
	</div>

	<!-- 用于弹出框 -->
	<div id="parent_win"></div>

	<!-- 解锁登录框 -->
	<div id="loginDialog" title="解锁登录" style="display: none;">
		<form method="post" class="form">
			<table class="table">
				<tr>
					<th width="50">登录名</th>
					<td><input id="loginName" name="loginName" type="text"
						class="easyui-validatebox" data-options="required:true" /></td>
				</tr>
				<tr>
					<th>密码</th>
					<td><input name="password" type="password"
						class="easyui-validatebox" data-options="required:true" /></td>
				</tr>
			</table>
		</form>
	</div>

	<!-- 修改密码框 -->
	<div id="passwordDialog" title="修改密码" style="display: none;">
		<form method="post" class="form">
			<table class="table">
				<tr>
					<th>新密码</th>
					<td><input id="pwd" name="data.pwd" type="password"
						class="easyui-validatebox" data-options="required:true" /></td>
				</tr>
				<tr>
					<th>重复密码</th>
					<td><input type="password" class="easyui-validatebox"
						data-options="required:true,validType:'eqPwd[\'#pwd\']'" /></td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>