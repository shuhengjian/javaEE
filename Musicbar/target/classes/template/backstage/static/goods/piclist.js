$(function() {
	selectType();
	selectState();
	queryAll(1, 10);

	/*批量删除*/
	$(".cb-btn-remove").click(function() {
		var checked = $("input[name='chk_one']:checked");
		if (checked.length == 0) {
			layer.open({
				title : '提示',
				content : '请先选中一条记录!'
			});
			return false;
		}
		var param = "";
		checked.each(function() {
			param += $(this).attr("class") + ",";
		});
		layui.use('layer', function() {
			layer.confirm('确认要删除选择记录？', {
				btn : [ '确定', '取消' ],
				time : 200000, //20s后自动关闭
			}, function(index) {
				$.ajax({
					url : "goods_deleteAll",
					type : "post",
					data : {
						id : param
					},
					dataType : "json",
					success : function(res) {
						layer.alert(res.msg, {
							title : "提示",
							btn : [ '确定' ]
						}, function(index, item) {
							location.reload();
						});
					}
				})
				layer.close(index);
			});
		})
	})

	/*上架*/
	$(".enable").click(function() {
		layui.use('layer', function() {
			var checked = $("input[name='chk_one']:checked");
			if (checked.length == 0) {
				layer.open({
					title : '提示',
					content : '请先选中一条记录!'
				});
				return false;
			}
			var goodsId = checked.attr("class");
			layer.confirm('确认要上架该商品？', {
				btn : [ '确定', '取消' ],
				time : 200000, //20s后自动关闭
			}, function(index) {
				$.ajax({
					url : "goods_updateState",
					type : "post",
					data : {
						goodsId : goodsId,
						goodsState : "1"
					},
					dataType : "json",
					success : function(res) {
						layer.alert(res.msg, {
							title : "提示",
							btn : [ '确定' ]
						}, function(index, item) {
							location.reload();
						});
					}
				})
				layer.close(index);
			});
		})
	})

	/*下架*/
	$(".binable").click(function() {
		layui.use('layer', function() {
			var checked = $("input[name='chk_one']:checked");
			if (checked.length == 0) {
				layer.open({
					title : '提示',
					content : '请先选中一条记录!'
				});
				return false;
			}
			var goodsId = checked.attr("class");
			layer.confirm('确认要下架该商品？', {
				btn : [ '确定', '取消' ],
				time : 200000, //20s后自动关闭
			}, function(index) {
				$.ajax({
					url : "goods_updateState",
					type : "post",
					data : {
						goodsId : goodsId,
						goodsState : "0"
					},
					dataType : "json",
					success : function(res) {
						layer.alert(res.msg, {
							title : "提示",
							btn : [ '确定' ]
						}, function(index, item) {
							location.reload();
						});
					}
				})
				layer.close(index);
			});
		})
	})
})

/* 查询商品分类 */
function selectType() {
	$.ajax({
		url : "goods_type",
		type : "post",
		dataType : "json",
		success : function(res) {
			$("[name=type]").nextAll().remove();
			$.each(res, function(i, o) {
				$("[name=type]").after(
						"<option value='" + o.typeId + "'>" + o.typeName
								+ "</option>");
				renderForm();
			})
		}
	})
}

/*查询商品状态*/
function selectState() {
	$.ajax({
		url : "goods_state",
		type : "post",
		dataType : "json",
		success : function(res) {
			$("[name=state]").nextAll().remove();
			$.each(res, function(i, o) {
				$("[name=state]").after(
						"<option value='" + o.paraNo + "'>" + o.paraVal
								+ "</option>");
				renderForm();
			})
		}
	})
}

/* 查询商品列表 */
function queryAll(pageNum, pageSize) {
	$.ajax({
		url : "goods_queryAll",
		type : "post",
		data : $("form").serialize() + "&pageNum=" + pageNum || 1
				+ "&pageSize=" + pageSize || 1,
		success : function(res) {
			let all = JSON.parse(res);
			let list = all[0].list;
			//总页数
			let pages = all[0].pages;
			console.log(all)
			//当前页码
			let pageNum = all[0].pageNum;
			//每页显示的数
			let pageSize = all[0].pageSize;
			//总记录数
			let count = all[0].total;
			$(".fr_ite").empty();
			for (let i = 0; i < list.length; i++) {
				/* 列表追加 */
				$(".fr_ite")
						.append(
								"<ul class='productLine "
										+ (list[i].goodsStock < list[i].goodsInventoryWarning ? 'back'
												: '')
										+ "'>\
						<li class='productInfo'>\
	                	<div class='productName'>"
										+ list[i].goodsName
										+ "</div>\
	                	<div style='display:none;'><input th:value='"
										+ list[i].goodsId
										+ "'/></div>\
	                	<div class='icon'>\
	                	<input type='checkbox' name='chk_one' id='selectall' value='' class='"
										+ list[i].goodsId
										+ "' onchange='sel(this)'/>\
	                	<a title=\"删除\" onclick=\"goods_dele(this)\" href='javascript:;'\>\
	                	<i class='layui-icon' style='color: #FF0000; font-size: 20px;'>&#xe640;</i>\
						</a>\
	                    	<a title='编辑' onclick=\"WeAdminEdit('修改商品','goods_open?goodsId="
										+ list[i].goodsId
										+ "', '"
										+ list[i].goodsId
										+ "', 600, 400)\" href='javascript:;' style='#FF0000'>\
	                    	<i class='layui-icon' style='color: #FF0000; font-size: 20px;'>&#xe642;</i>\
							</a>\
	                	</div>\
	                	<div class='productImg'><img class='img-responsive' src='/musicbar/file/"
										+ list[i].attch.fileUel
										+ "'></div>\
	                    <div class='infoRow'>\
	                        <div class='productCapacity'>"
										+ list[i].goodsQuantity
										+ ""
										+ list[i].goodsStandard
										+ "</div>\
	                        <div class='productPrice'>¥&nbsp;"
										+ list[i].goodsPrice
										+ "/"
										+ list[i].goodsUnits
										+ "</div>\
	                         <div class='productFabulous'>"
										+ list[i].goodsStock
										+ "</div>\
	                    </div>\
	                </li>\
	                </ul>");
			}
			//调用分页方法
			getPageList(count, pageNum, pageSize);
		}
	})
}

/*删除*/
function goods_dele(tag) {
	layui.use('layer', function() {
		layer.confirm('确认要删除该记录？', {
			btn : [ '确定', '取消' ],
			time : 200000, //20s后自动关闭
		}, function(index) {
			$.ajax({
				url : "goods_delete",
				data : {
					goodsId : $(tag).prev().attr("class")
				},
				type : "post",
				dataType : "json",
				success : function(res) {
					layer.alert(res.msg, {
						title : "提示",
						btn : [ '确定' ]
					}, function(index, item) {
						location.reload();
					});
				}
			})
			layer.close(index);
		});
	})
}

function renderForm() {
	layui.use('form', function() {
		var form = layui.form;//高版本建议把括号去掉，有的低版本，需要加()
		form.render();
	});
}
//自己封装分页方法
function getPageList(count, curr, limit) {
	//分页方法
	layui.use([ 'laypage', 'layer' ], function() {
		var laypage = layui.laypage, layer = layui.layer;
		//完整功能
		laypage.render({
			elem : 'pagefenye',
			count : count || 0,
			theme : '#009587',
			limit : limit || 3,
			limits : [ 5, 10, 20, 30, 40 ],
			curr : curr || 1,
			layout : [ 'count', 'prev', 'page', 'next', 'refresh', 'skip' ],
			jump : function(obj, first) {
				//debugger;
				if (!first) {
					//window.location.href = "?curr="+obj.curr+"&pageSize="+obj.limit+"&enterId="+'${enterId}';
					queryAll(obj.curr, obj.limit);
				}
			}
		});
	});
}