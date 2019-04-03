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

		$("#tsys_manager_list").flexigrid({
			url : "${ctx}/tsysParameterController.do?method=query",// ajax方式对应的url地址
			type : 'post',// 数据发送方式 
			dataType : 'json',// 数据加载的类型(xml/json)        
			contentType : 'application/x-www-form-urlencoded; charset=UTF-8',
			sortname : "paraid",//排序
			sortorder : "asc",
			useRp : true,
			radio : true,// 是否要多选框
			idProperty : 'paraid',// 多选框绑定行的id
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
			preProcess : function(data) {
				$.each(data.rows, function() {
					this.isdefault = this.isdefault == 1 ? '是' : '否';
				});
				return data;
			},

			colModel : [ {
				display : '序号',
				name : 'rownum_',
				width : 100,
				sortable : true,
				align : 'center',
				process : displayID
			//扩展函数
			}, {
				display : '参数名称',
				name : 'paraname',
				width : 100,
				sortable : true,
				align : 'center'
			}, {
				display : '参数编码',
				name : 'parano',
				width : 100,
				sortable : true,
				align : 'center'
			}, /* {
				display : '等级',
				name : 'paraClass',
				width : 100,
				sortable : true,
				align : 'center'
			}, */ {
				display : '字典值',
				name : 'paraKey',
				width : 150,
				sortable : true,
				align : 'center'
			}, {
				display : '字典类型',
				name : 'paraType',
				width : 100,
				sortable : true,
				align : 'center'
			}, {
				display : '排序号',
				name : 'paraorder',
				width : 100,
				sortable : true,
				align : 'center'
			}, {
				display : '显示方式',
				name : 'displaysort',
				width : 100,
				sortable : true,
				align : 'center'
			}, {
				display : '是否默认',
				name : 'isdefault',
				width : 100,
				sortable : true,
				align : 'center'
			} ]
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
		$("#container").layout(); // 自动调整窗口的大小

		$(window).resize(function() { // 当窗口的大小改变是触发这个resize事件
			$("#container").layout();// 自动调整窗口的大小
		});

		var win = $("#win").window({
			title : "参数基本信息",
			closed : true,
			// left : 400, // 设置窗口离左边的距离
			top : 100,  // 设置窗口离上面的距离
			width : 310,
			heigth : 400,
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
							"${ctx}/tsysParameterController.do?method=add", "",
							handler); // handler 是作为一个回调函数来处理的
				});

		$("#btn_query").click(function() {
				$('#tsys_manager_list').flexOptions({
					params : [{
								"name" : "para_paraname",
							    "value" : $("#paraname").val()
							 }, {
								"name" : "para_paraKey",
								"value" : $("#paraKey").val()
							}],
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
			var id = $(".trSelected", $("#tsys_manager_list")).attr("data-id");
			$("#win").window('open');
			$("#msg").load("${ctx}/tsysParameterController.do?method=modify", {
				id : id
			}, handler);
		});

		$("#btn_del").click(function() {
			if ($(".trSelected").length == 0) {
				jQuery.messager.alert('提示:', '请先选中一条记录!', 'info');
				return false;
			}
			jQuery.messager.confirm('提示:', '你确认要删除吗?',function(event) {
				if (event) {
					var paraIds = [];
					$(".trSelected", $("#tsys_manager_list")).each(function() {
					paraIds.push($(this).attr("data-id"));
					});
					$.ajax({
						url : "${ctx}/tsysParameterController.do?method=del",
						data : {paraIds : paraIds},
						traditional : true, // 加上这个属性，可以直接传数组类型的数据
						type : 'post',
						dataType : 'json',
						success : function(data) {
							if (data.flag) {
								jQuery.messager.alert('提示:', '删除成功！', '', function() {
									reloadlist(); // 只刷新列表就可以了
								});
							} else {
								jQuery.messager.alert('提示:', '删除失败！','warning');
							}
						}
					});
				}
			});
		});
	});
	function reloadlist() {
		$('#tsys_manager_list').flexOptions({
			contentType : "application/x-www-form-urlencoded; charset=UTF-8",
			newp : 1
		}).flexReload();
	}
</script>
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
						<td align="left"><fmt:message key="tsysparameter.paraname" />
							<!-- 参数名称 --></td>
						<td><input type="text" name="para_paraname" id="paraname" class="text">
						</td>
						<td align="left"><fmt:message key="tsysparameter.paraKey" />
							<!--字典值 --></td>
						<td><input type="text" name="para_paraKey" id="paraKey" class="text"/></td>
					</tr>
				</table>
			</div>
			<!-- 查询条件布局end-->
			<!-- 按钮栏布局begin -->
			<div id="toolbar_area" border="false">
				<a class="easyui-linkbutton" iconCls="icon-add" plain="true" id="btn_add">
					<fmt:message key="button.add" /> <!-- 新增按钮 -->
				</a>
				<a class="easyui-linkbutton" iconCls="icon-edit" plain="true" id="btn_edit">
					<fmt:message key="button.edit" /> <!-- 编辑按钮 -->
				</a>
				<a class="easyui-linkbutton" iconCls="icon-remove" plain="true" id="btn_del">
					<fmt:message key="button.remove" /> <!-- 删除按钮 -->
				</a>
				<a class="easyui-linkbutton" iconCls="icon-query" plain="true" id="btn_query">
					<fmt:message key="button.query" /> <!-- 查询按钮 -->
				</a>
			</div>
		</div>
		<!-- 列表布局begin -->
		<div id="grid-body" class="grid-body" region="center">
			<div id="tsys_manager_list"></div>
		</div>
	</div>
	<!-- 表单窗口设计：新增、修改、查看明细 -->
	<div id="win">
		<div id="msg"></div>
	</div>
</body>
</html>
