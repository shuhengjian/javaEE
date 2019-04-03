<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<%@ include file="/WEB-INF/jsp/common/meta.jsp"%>
<%@ include file="/WEB-INF/jsp/common/title.jsp"%>
</head>
<script type="text/javascript">
	$(function() {

		$("#tsys_serialno_list").flexigrid({
			url : "${ctx}/tsysSerialnoController.do?method=query",// ajax方式对应的url地址
			type : 'post',// 数据发送方式 
			dataType : 'json',// 数据加载的类型(xml/json)        
			contentType : 'application/x-www-form-urlencoded; charset=UTF-8',
			sortname : "serialno_id",//排序
			sortorder : "asc",
			useRp : true,
			checkbox : true,// 是否要多选框
			idProperty : 'serialno_id',// 多选框绑定行的id
			singleSelect : false, //仅允许选择单行 
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
				width : 100,
				sortable : true,
				align : 'center',
				process : displayID
			//扩展函数
			}, {
				display : '规则名称',
				name : 'serial_name',
				width : 100,
				sortable : true,
				align : 'center'
			}, {
				display : '规则别名',
				name : 'secound_name',
				width : 100,
				sortable : true,
				align : 'center'
			}, {
				display : '规则',
				name : 'formular_regx',
				width : 100,
				sortable : true,
				align : 'center'
			}, {
				display : '生成方式',
				name : 'create_type',
				width : 100,
				sortable : true,
				align : 'center'
			}, {
				display : '流水号长度',
				name : 'serial_length',
				width : 100,
				sortable : true,
				align : 'center'
			}, {
				display : '步长',
				name : 'step',
				width : 100,
				sortable : true,
				align : 'center'
			}, {
				display : '初始值',
				name : 'init_value',
				width : 100,
				sortable : true,
				align : 'center'
			}, {
				display : '当前值',
				name : 'current_value',
				width : 100,
				sortable : true,
				align : 'center'
			} ]
		});

		function displayID(value, i) {
			//返回当前页码
			var p = $("div.pGroup>.pcontrol>input").val();
			//返回数据行数组
			var tbl = $("#tsys_serialno_list>tbody>tr");
			
			var $tr = $("#row" + i);
			//返回当前序号起始值,其中*前面为当前页码,后面为每页显示条数
			var startNum = (p - 1) * parseInt($("select[name='rp']").val());
			//index+startNum,序号值
			var numID = (tbl.index($tr) + 1) + startNum;
			//为对象赋值
			value.innerHTML = numID;

			return value;
		}
		$("#container").layout();

		$(window).resize(function() { // 当窗口的大小改变是触发这个resize事件
			$("#container").layout();
		});

		var win = $("#win").window({
			title : "流水号详细信息",
			closed : true,
			modal : true
		});
		var handler = function() { // 作为回调函数
			$("input[type='button']").click(function() {
				win.window("close");
			});
		};

		$("#btn_add").click(
				function() {
					win.window('open');
					$("#msg").load(
							"${ctx}/tsysSerialnoController.do?method=add", "",
							handler); // handler 是作为一个回调函数来处理的
		});

		$("#btn_query")
				.click(
						function() {
							$('#tsys_serialno_list')
									.flexOptions(
											{
												params : [
														{
															"name" : "serial_name",
															"value" : $(
																	"#serial_name")
																	.val()
														},
														{
															"name" : "secound_name",
															"value" : $(
																	"#secound_name")
																	.val()
														} ],
												contentType : "application/x-www-form-urlencoded; charset=UTF-8",
												newp : 1
											}).flexReload();
						});

		$("#btn_edit").click(function() {
			if ($(".trSelected").length != 1) {
				jQuery.messager.alert('提示:', '请先选中一条记录!', 'info');
				return false;
			}
			var handler = function() {
				$("input[type='button']").click(function() {
					win.window("close");
				});
			};
			var serialno_id = $(".trSelected", $("#tsys_serialno_list")).attr("data-id");
			$("#win").window('open');
			$("#msg").load("${ctx}/tsysSerialnoController.do?method=modify", {
				serialno_id : serialno_id
			}, handler);
		});

		$("#btn_del")
				.click(
						function() {
							if ($(".trSelected").length == 0) {
								jQuery.messager.alert('提示:', '请先选中一条记录!',
										'info');
								return false;
							}
							jQuery.messager
									.confirm(
											'提示:',
											'你确认要删除吗?',
											function(event) {
												if (event) {
													var serialno_id = new Array();
													$(".trSelected",$("#tsys_serialno_list")).each(
														function() {
															serialno_id.push($(this).attr("data-id"));
													});
													$.ajax({
																url : "${ctx}/tsysSerialnoController.do?method=del&serialno_id="+serialno_id,
																traditional : true, // 加上这个属性，可以直接传数组类型的数据
																type : 'post',
																dataType : 'json',
																success : function(
																		data) {
																	if (data.success) {
																		jQuery.messager
																				.alert(
																						'提示:',
																						'删除成功！',
																						'',
																						function() {
																							reloadlist(); // 只刷新列表就可以了
																						});
																	} else {
																		jQuery.messager
																				.alert(
																						'提示:',
																						'删除失败！',
																						'warning');
																	}
																}
															});
												}
											});
						});
	});
	function reloadlist() {
		$('#tsys_serialno_list').flexOptions({
			contentType : "application/x-www-form-urlencoded; charset=UTF-8",
			newp : 1
		}).flexReload();
	}
</script>
<body>
	<div style="position: relative;" id="container" fit="true"
		class="easyui-layout">
		<div region="north" border="false">
			<!-- 查询条件区域begin-->
			<div id="query_area">
				<table class="FormView" border="0" cellspacing="1" cellpadding="0">
					<col class="Label" />
					<col class="Data" />
					<col class="Label" />
					<col class="Data" />
					<tr>
						<td align="left"><fmt:message key="tsysserialno.serial_name" />
							<!-- 参数名称 -->
						</td>
						<td>
							<input type="text" name="serial_name" id="serial_name" class="text">
						</td>
						<td align="left"><fmt:message key="tsysserialno.secound_name" />
							<!-- 参数名称 -->
						</td>
						<td>
							<input type="text" name="secound_name" id="secound_name" class="text">
						</td>
					</tr>
				</table>
			</div>
			<!-- 查询条件布局end-->
			<!-- 按钮栏布局begin -->
			<div id="toolbar_area" border="false">
				<a class="easyui-linkbutton" iconCls="icon-add" plain="true"
					id="btn_add"> <fmt:message key="button.add" /> <!-- 新增按钮 -->
				</a> <a class="easyui-linkbutton" iconCls="icon-edit" plain="true"
					id="btn_edit"> <fmt:message key="button.edit" /> <!-- 编辑按钮 -->
				</a> <a class="easyui-linkbutton" iconCls="icon-remove" plain="true"
					id="btn_del"> <fmt:message key="button.remove" /> <!-- 删除按钮 -->
				</a> <a class="easyui-linkbutton" iconCls="icon-query" plain="true"
					id="btn_query"> <fmt:message key="button.query" /> <!-- 查询按钮 -->
				</a>
			</div>
			<!-- 按钮栏布局end -->
		</div>
		<!-- 列表布局begin -->
		<div id="grid-body" class="grid-body" region="center">
			<div id="tsys_serialno_list"></div>
		</div>
	</div>
	<!-- 表单窗口设计：新增、修改、查看明细 -->
	<div id="win" closed="true" style="width: 500px; height: 350px;">
			<div id="msg"></div>
	</div>
</body>
</html>
