<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>


<!-- 新闻管理 -->


<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<%@ include file="/WEB-INF/jsp/common/meta.jsp"%>
<%@ include file="/WEB-INF/jsp/common/title.jsp"%>
<style type="text/css">
	#imgShowBox{
		width:200px;
		height:200px;
		position: relative;
		top:30px;
		right:0;
	}
	#imgShowBox img{
		width:100%;
	}
</style>
<script type="text/javascript">
	function getMyDate(str) {
		    var oDate = new Date(str),
		    oYear = oDate.getFullYear(),
		    oMonth = oDate.getMonth()+1,
		    oDay = oDate.getDate(),
		    oHour = oDate.getHours(),
		    oMin = oDate.getMinutes(),
		    oSen = oDate.getSeconds(),
		    oTime = oYear +'-'+ addZero(oMonth) +'-'+ addZero(oDay) +' '+ addZero(oHour) +':'+
		addZero(oMin) +':'+addZero(oSen);
		    return oTime;
		}
	$(function() {
		
		$("#tsys_manager_list").flexigrid({
			url : "${ctx}/ttyBackAdvertisingController.do?method=query",// ajax方式对应的url地址
			type : 'post',// 数据发送方式 
			dataType : 'json',// 数据加载的类型(xml/json) 
			contentType : 'application/x-www-form-urlencoded; charset=UTF-8',
			sortname : "advertisingId",//排序
			sortorder : "asc",
			useRp : true,
			checkbox : true,// 是否要多选框
			idProperty : 'advertisingId',// 多选框绑定行的id
			singleSelect : true, //仅允许选择单行 
			rp : 10,
			showTableToggleBtn : false,
			width : 'auto',// 宽度值 
			height : $("body").height() - 120,// 插件的高度，单位为px   
			striped : true,// 是否显示斑纹效果，默认是奇偶交互的形式
			resizable : false,// 是否可伸缩   
			errormsg : '发生错误', //错误提升信息
			usepager : true, // 是否分页
			nowrap : true, //是否不换行               
			rpOptions : [ 5, 10, 15 ],
			proitemg : '正在处理数据，请稍候 ...', //正在处理的提示信息                  
			colModel : [ {
				display : '序号',
				name : 'rownum_',
				width : 30,
				sortable : true,
				align : 'center',
				process : displayID   //扩展函数
			},{
				display : '状态',//状态
				name : 'advertisingState',
				width : 200,
				sortable : true,
				align : 'center',
				process:function(value,i){
					if(value.innerText==1)value.innerHTML='<span class="state">启用</span>';
					if(value.innerText==2)value.innerHTML='<span class="state">停用</span>';
					return value;
				}
			},{
				display : '广告名称',//广告名称
				name : 'advertisingName',
				width : 200,
				sortable : true,
				align : 'left',
			
			},{
				display : '开始时间',//广告开始时间
				name : 'advertisingBeginTime',
				width : 200,
				sortable : true,
				align : 'left',
			/* 	process:function(value,i){
					var time = value.innerText;
					value.innerHTML=new Date(parseInt(time)).toLocaleString();
					return value;
				} */
			},{
				display : '结束时间',//广告结束时间
				name : 'advertisingEndTime',
				width : 200,
				sortable : true,
				align : 'left',
			/* 	process:function(value,i){
					var time = value.innerText;
					value.innerHTML=new Date(parseInt(time)).toLocaleString();
					return value;
				} */
			},{
				display : '广告图片',//广告图片
				name : 'tsysAttach.fileUrl',
				width : 150,
				sortable : true,
				align : 'center',
				process:function(value,i){
					var img =  '<img style=\"height:18px;width:40px;margin-left:-15px;position:absolute\"  onmouseover=\"show(this)\" onmouseout=\"hid(this)\" src=\"${ctx}/uploads/' + value.innerText + '\" alt=\"图片加载失败\">';
					value.innerHTML = img;
					return value;
				}
			}]
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
		$("#container").layout();

		$(window).resize(function() { // 当窗口的大小改变是触发这个resize事件
			$("#container").layout();
		});
		/*条件查询*/
		$("#btn_query").click(function() {
			$('#tsys_manager_list').flexOptions({
				params : [{"name" : "advertising_advertisingState",
							"value" : $("#advertisingState").val()
						},{"name" : "advertising_advertisingName",
							"value" : $("#advertisingName").val()
						}],
				contentType : "application/x-www-form-urlencoded; charset=UTF-8",
				newp : 1
				}).flexReload();
		});
		/*修改*/
		$("#btn_edit").click(function() {
			if ($(".trSelected").length != 1) {
				jQuery.messager.alert('提示:', '请选中一条记录!', 'info');
				return false;
			}
			var handler = function() {
				var hid = function() {
					win.window("close");
				};
				$("input[type='button']").click(hid);
			};
			var id = $(".trSelected", $("#tsys_manager_list")).attr("data-id");
			dialogFun = sy.modalDialog({
				title : '广告修改',
				width : 600,
				height : 350,//500
				resizable : true,
				url : '${ctx}/ttyBackAdvertisingController.do?method=modify&id='+id
			});
		});
		/*删除*/
		$("#btn_del").click(
			function() {
				if ($(".trSelected").length == 0) {
					jQuery.messager.alert('提示:', '请先选中一条记录!', 'info');
					return false;
				}
				
				jQuery.messager.confirm('提示:', '你确认要删除吗?', function(event) {
					if (event) {
						var advertisingIds = [];
						
						$(".trSelected", $("#tsys_manager_list")).each(
								function() {
									advertisingIds.push($(this).attr("data-id"));
								});
						$.ajax({
							url : "${ctx}/ttyBackAdvertisingController.do",
							data : {
								"method" : "del",
								"advertisingIds" : advertisingIds
							},
							traditional : true, // 加上这个属性，可以直接传数组类型的数据
							type : 'post',
							dataType : 'json',
							success : function(data) {
								if (data.flag) {
									jQuery.messager.alert('提示:', '删除成功！',
											'', function() {
												reloadlist(); // 只刷新列表就可以了
											});
								} else {
									jQuery.messager.alert('提示:', '删除失败！',
											'warning');
								}
							}
						});
					}
				});
			});
		/*启用*/
		$("#enable").click(
				function() {
					if ($(".trSelected").length == 0) {
						jQuery.messager.alert('提示:', '请先选中一条记录!', 'info');
						return false;
					}
					jQuery.messager.confirm('提示:', '你确认要启用所选中的吗?', function(event) {
						if (event) {
							var advertisingIds = [];
							$(".trSelected", $("#tsys_manager_list")).each(
									function() {
										advertisingIds.push($(this).attr("data-id"));
									});							
							$.ajax({
								url : "${ctx}/ttyBackAdvertisingController.do",
								data : {
									method : "state",
									advertisingIds : advertisingIds,
									advertisingState: "1"
								},
								traditional : true, // 加上这个属性，可以直接传数组类型的数据
								type : 'post',
								dataType : 'json',
								success : function(data) {
									if (data.flag) {
										jQuery.messager.alert('提示:', '启用成功！',
												'', function() {
													reloadlist(); // 只刷新列表就可以了
												});
									} else {
										jQuery.messager.alert('提示:', '启用失败！',
												'warning');
									}
								}
							});
						}
					});
				});
		//停用
		$("#disable").click(
				function() {
					if ($(".trSelected").length == 0) {
						jQuery.messager.alert('提示:', '请先选中一条记录!', 'info');
						return false;
					}
					jQuery.messager.confirm('提示:', '你确认要停用所选中的吗?', function(event) {
						if (event) {
							var advertisingIds = [];
							$(".trSelected", $("#tsys_manager_list")).each(
									function() {
										advertisingIds.push($(this).attr("data-id"));
									});							
							$.ajax({
								url : "${ctx}/ttyBackAdvertisingController.do",
								data : {
									method : "state",
									advertisingIds : advertisingIds,
									advertisingState: "2"
								},
								traditional : true, // 加上这个属性，可以直接传数组类型的数据
								type : 'post',
								dataType : 'json',
								success : function(data) {
									if (data.flag) {
										jQuery.messager.alert('提示:', '停用成功！',
												'', function() {
													reloadlist(); // 只刷新列表就可以了
												});
									} else {
										jQuery.messager.alert('提示:', '停用失败！',
												'warning');
									}
								}
							});
						}
					});
				});
	});
	function reloadlist(){
		$('#tsys_manager_list').flexOptions({
			contentType : "application/x-www-form-urlencoded; charset=UTF-8",
			newp : 1
		}).flexReload();
	}
	
	var dialogFun = null;
	var btn_add = function() {
		dialogFun = sy.modalDialog({
			title : '广告添加',
			width : 600,
			height : 350,//500
			resizable : true,
			url : '${ctx}/ttyBackAdvertisingController.do?method=add'
		});
	};
	//图片预览
	function show(tag){
		$(tag).css({"height":"100px","width":"auto","z-index":"1000"});
	}
	function hid(tag){
		$(tag).css({"height":"18px","width":"40px","z-index":"1"});
	}  
	// 关闭窗口
	function closeDialog() {
		dialogFun.dialog('close');
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
							<td align="left">广告名称</td>
							<td><input type="text" id="advertisingName" name="advertisingName" class="text" value="">
								</td>
							<!-- 广告状态 -->
							<td align="left">广告状态</td>
							<td>
								<cbitedu:para name="advertising_state" id="advertisingState" cssClass="select" withEmpty="true" />
							</td>
						</tr>
					</table>
				</div>
				<!-- 查询条件布局end-->
				<!-- 按钮栏布局begin -->
				<div id="toolbar_area" border="false">
					
					<a class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="btn_add()">
						<fmt:message key="button.add" /> <!-- 新增按钮 -->
					</a>
					<a class="easyui-linkbutton" iconCls="icon-edit" plain="true" id="btn_edit">
						<fmt:message key="button.edit" /> <!-- 编辑按钮 -->
					</a>
					<a class="easyui-linkbutton" iconCls="icon-remove" plain="true" id="btn_del">
						<fmt:message key="button.remove" /> <!-- 删除按钮 -->
					</a>
					<a class="easyui-linkbutton" iconCls="icon-no" plain="true" id="disable">
					<fmt:message key="button.disable" /> <!-- 停用按钮 -->
					</a>
					<a class="easyui-linkbutton" iconCls="icon-ok" plain="true" id="enable">
						<fmt:message key="button.enable" /> <!-- 启用按钮 -->
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
		
</body>
</html>