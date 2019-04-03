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
			url : "${ctx}/ttyBackProductController.do?method=query",// ajax方式对应的url地址
			type : 'post',// 数据发送方式 
			dataType : 'json',// 数据加载的类型(xml/json)        
			contentType : 'application/x-www-form-urlencoded; charset=UTF-8',
			sortname : "productId",//排序
			sortorder : "asc",
			useRp : true,
			checkbox : true,
			//radio : true,// 是否要多选框
			idProperty : 'productId',// 多选框绑定行的id
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
				name : 'id',
				width : 50,
				sortable : true,
				align : 'center',
				process : displayID   //displayID是扩展函数
			},{
				display : '产品名称',
				name : 'productName',
				width : 180,
				sortable : true,
				align : 'center',
			}, {
				display : '品牌名称',
				name : 'ttyBrand.brandName',
				width : 100,
				sortable : true,
				align : 'center',
			}, {
				display : '产品类型',
				name : 'ttyType.typeName',
				width : 80,
				sortable : true,
				align : 'center',
			}, {
				display : '商家',
				name : 'tsysOrg.orgName',
				width : 200,
				sortable : true,
				align : 'center',
			}, {
				display : '市场价格(万)',
				name : 'productMarketPrice',
				width : 70,
				sortable : true,
				align : 'center',
			}, {
				display : '一口价(万)',
				name : 'productFlatlyPrice',
				width : 60,
				sortable : true,
				align : 'center',
			}, {
				display : '产品介绍',
				name : 'productRemark',
				width : 280,
				sortable : true,
				align : 'center',
			}, {
				display : '产品颜色',
				name : 'productColor',
				width : 80,
				sortable : true,
				align : 'center',
			}, {
				display : '产品状态',
				name : 'productState',
				width : 80,
				sortable : true,
				align : 'center',
				process:function(value,i){
					if(value.innerText == 0)value.innerHTML='<span class="state">已下架</span>';
					if(value.innerText == 1)value.innerHTML='<span class="state">已上架</span>';
					return value;
				}
				}]
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

		$("#container").layout();

		$(window).resize(function() { // 当窗口的大小改变是触发这个resize事件
			$("#container").layout();
		});

	$("#btn_query").click(function() {
			$('#tsys_manager_list').flexOptions({
				params : [{"name" : "product_productName",
							"value" : $("#productName").val()
						},{"name" : "brandName",
							"value" : $("#brandName").val()
						},{"name" : "typeName",
							"value" : $("#typeName").val()
						},{"name" : "typeStart",
							"value" : $("#typeStart").val()
						},{"name" : "priceId",
							"value" : $("#priceId").val()
						},{"name" : "priceMin",
							"value" : $("#priceMin").val()
						},{"name" : "priceMax",
							"value" : $("#priceMax").val()
						},{"name" : "orgName",
							"value" : $("#orgName").val()
						},{"name" : "product_productState",
							"value" : $("#productState").val()
						}],
				contentType : "application/x-www-form-urlencoded; charset=UTF-8",
				newp : 1
				}).flexReload();
		});

	$("#btn_edit").click(function() {
			if ($(".trSelected").length == 0) {
				jQuery.messager.alert('提示:', '请先选中一条记录!', 'info');
				return false;
			}
			else if ($(".trSelected").length != 1) {
				jQuery.messager.alert('提示:', '只能选中一条记录!', 'info');
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
				title : '产品基本信息',
				width : 800,
				height : 450,
				resizable : true,
				url : '${ctx}/ttyBackProductController.do?method=modify&id='+id
			});
		});

		$("#btn_del").click(
				function() {
					if ($(".trSelected").length == 0) {
						jQuery.messager.alert('提示:', '请先选中一条记录!', 'info');
						return false;
					}
					jQuery.messager.confirm('提示:', '你确认要删除吗?', function(event) {
						if (event) {
							var productIds = [];
							$(".trSelected", $("#tsys_manager_list")).each(
									function() {
										productIds.push($(this).attr("data-id"));
									});							
							$.ajax({
								url : "${ctx}/ttyBackProductController.do",
								data : {
									method : "del",
									productIds : productIds
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
		//重置
		$("#btn_reset").click(
			function(){
				$('#productName').val('');
				$('#brandName').val("");
				$('#typeStart').val("");
				$('#typeName').val("");
				$('#priceId').val("");
				$('#priceMin').val("");
				$('#priceMax').val("");
				$('#orgName').val("");
				$('#productState').val("");
				query();
			});
		//产品上架
		$("#btn_putaway").click(
			function(){
				var lengthFlag = $(".trSelected").length;
				if(lengthFlag == 0 ){
					jQuery.messager.alert('提示:','请先选中一条记录!','info');
					return false;
				}
				jQuery.messager.confirm('提示:', '你确认要上架所选中的产品吗?', function(event) {
					if (event) {
						var productIds = [];
						$(".trSelected", $("#tsys_manager_list")).each(
								function() {
									productIds.push($(this).attr("data-id"));
								});							
						$.ajax({
							url : "${ctx}/ttyBackProductController.do",
							data : {
								method : "state",
								productIds : productIds,
								productState: "1"
							},
							traditional : true, // 加上这个属性，可以直接传数组类型的数据
							type : 'post',
							dataType : 'json',
							success : function(data) {
								if (data.flag) {
									jQuery.messager.alert('提示:', '上架成功！',
											'', function() {
												reloadlist(); // 只刷新列表就可以了
											});
								} else {
									jQuery.messager.alert('提示:', '上架失败！',
											'warning');
								}
							}
						});
					}
				});
			});
		//产品下架
		$("#btn_unshelve").click(
			function(){
				var lengthFlag = $(".trSelected").length;
				if(lengthFlag == 0 ){
					jQuery.messager.alert('提示:','请先选中一条记录!','info');
					return false;
				}
				jQuery.messager.confirm('提示:', '你确认要下架所选中的产品吗?', function(event) {
					if (event) {
						var productIds = [];
						$(".trSelected", $("#tsys_manager_list")).each(
								function() {
									productIds.push($(this).attr("data-id"));
								});							
						$.ajax({
							url : "${ctx}/ttyBackProductController.do",
							data : {
								method : "state",
								productIds : productIds,
								productState: "0"
							},
							traditional : true, // 加上这个属性，可以直接传数组类型的数据
							type : 'post',
							dataType : 'json',
							success : function(data) {
								if (data.flag) {
									jQuery.messager.alert('提示:', '下架成功！',
											'', function() {
												reloadlist(); // 只刷新列表就可以了
											});
								} else {
									jQuery.messager.alert('提示:', '下架失败！',
											'warning');
								}
							}
						});
					}
				});
			});
		/*预览*/
		$("#btn_preview").click(function() {
			if ($(".trSelected").length == 0) {
				jQuery.messager.alert('提示:', '请先选中一条记录!', 'info');
				return false;
			}
			else if ($(".trSelected").length != 1) {
				jQuery.messager.alert('提示:', '只能选中一条记录!', 'info');
				return false;
			}
			var id = $(".trSelected", $("#tsys_manager_list")).attr("data-id");
			window.open("${ctx }/ttyProductController.do?method=queryDetails&productId="+id);
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
			title : '产品基本信息',
			width : 810,
			height : 500,
			resizable : true,
			url : '${ctx}/ttyBackProductController.do?method=add'
		});
	};
	// 关闭窗口
	function closeDialog() {
		dialogFun.dialog('close');
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
						<!-- 产品名称 -->
						<td align="left">
							<fmt:message key="product.productName" />
						</td>
						<td>
							<input type="text" id="productName" name="product_productName" class="text" value="" placeholder="输入一个产品名称" style="width: 206px;height:auto">
						</td>
						<!-- 品牌名称 -->
						<td align="left">
							<fmt:message key="product.ttyBrand.brandName" />
						</td>
						<td>
							<!-- <input type="text" id="brandName" name="brandName" class="text" value="" placeholder="输入一个品牌名称"> -->
							<select name="brandName" id="brandName" style="width: 210px;height:auto">
								<option value="">请选择</option>
		                      	<c:forEach items="${ttyBrand }" var="b">
		                   			<option value="${b.brandName}">${b.brandName }</option>
		                   		</c:forEach>
		                    </select>
						</td>
					</tr>
					<tr>
						<!-- 产品父类型 -->
						<%-- <td align="left">
							<fmt:message key="product.ttyType.typeStart" />
						</td>
						<td>
							<!-- <input type="text" id="typeName" name="typeName" class="text" value="" placeholder="输入一个产品父类型"> -->
							<select name="typeStart" id="typeStart" style="width: 210px;height:auto">
								<option value="">请选择</option>
			                    <option value="1">平行进口</option>
			                    <option value="2">中规车</option>
		                    </select>
						</td> --%>
						<!-- 产品类型 -->
						<td align="left">
							<fmt:message key="product.ttyType.typeName" />
						</td>
						<td>
							<!-- <input type="text" id="typeName" name="typeName" class="text" value="" placeholder="输入一个产品类型"> -->
							<select name="typeName" id="typeName" style="width: 210px;height:auto">
							<option value="">请选择</option>
								<optgroup label="平行进口">
			                      	<c:forEach items="${ttyType }" var="t">
				                      	<c:if test="${t.typeStart == 1 }">
				                   			<option value="${t.typeName}">${t.typeName }</option>
				                   		</c:if>
			                   		</c:forEach>
			                   	</optgroup>
			                   	<optgroup label="中规车">
			                      	<c:forEach items="${ttyType }" var="t">
				                      	<c:if test="${t.typeStart == 2 }">
				                   			<option value="${t.typeName}">${t.typeName }</option>
				                   		</c:if>
			                   		</c:forEach>
			                   	</optgroup>
		                    </select>
						</td>
						<!-- 商家 -->
						<td align="left">
							<fmt:message key="product.tsysOrg.orgName" />
						</td>
						<td>
							<select name="orgName" id="orgName" style="width: 210px;height:auto">
								<option value="">请选择</option>
		                      	<c:forEach items="${tsysOrg }" var="o">
		                   			<option value="${o.orgName}">${o.orgName }</option>
		                   		</c:forEach>
		                    </select>
						</td>
					</tr>
					<tr>
						<!-- 最低价 -->
						<td align="left">
							<fmt:message key="product.ttyPrice.priceMin" />
						</td>
						<td>
							<input type="number" id="priceMin" name="priceMin" class="text" value="" placeholder="输入一口价最低价" style="width: 206px;height:auto">
						</td>
						<!-- 最高价 -->
						<td align="left">
							<fmt:message key="product.ttyPrice.priceMax" />
						</td>
						<td>
							<input type="number" id="priceMax" name="priceMax" class="text" value="" placeholder="输入一口价最高价" style="width: 206px;height:auto">
						</td>
					</tr>
					<tr>				
						<!-- 产品状态 -->
						<td align="left">
							<fmt:message key="product.productState" />
						</td>
						<td>
							<cbitedu:para name="product_state" id="productState" cssClass="select" withEmpty="true" style="width: 210px;height:auto"/>
						</td>
						<td></td>
						<td></td>
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
				<a class="easyui-linkbutton" iconCls="icon-no" plain="true" id="btn_unshelve">
					<fmt:message key="button.unshelve" /> <!-- 下架按钮 -->
				</a>
				<a class="easyui-linkbutton" iconCls="icon-ok" plain="true" id="btn_putaway">
					<fmt:message key="button.putaway" /> <!-- 上架按钮 -->
				</a>
				<a class="easyui-linkbutton" iconCls="icon-search" plain="true" id="btn_preview">
					<fmt:message key="button.preview" /> <!-- 预览按钮 -->
				</a>
				<a class="easyui-linkbutton" iconCls="icon-reset" plain="true" id="btn_reset">
					<fmt:message key="button.reset" /> <!-- 重置按钮 -->
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
