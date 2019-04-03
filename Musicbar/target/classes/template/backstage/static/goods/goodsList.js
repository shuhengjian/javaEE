$(function() {
	/* 查询商品列表 */
	queryAll(1, 1);
	/* 查询商品分类 */
	selectType();
	selectState();
})
//重新渲染表单
function renderForm() {
	layui.use('form', function() {
		var form = layui.form;//高版本建议把括号去掉，有的低版本，需要加()
		form.render();
	});
}

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
				let startRow = all[0].startRow;
				let	endRow = all[0].endRow;
				$("tbody").empty();
				for (let i = 0; i < list.length; i++) {
					console.log(list[i].goodsStock,
							list[i].goodsInventoryWarning)
					/* 列表追加 */
					$("tbody").append(
									"<tr data-id='1'>\
						<td>\
						<input type='checkbox' name='chk_one' id='selectall' value='' class='"
											+ list[i].goodsId
											+ "' onchange='sel(this)'/>\
						</td>\
						<td>"
											+ list[i].goodsCode
											+ "</td>\
						<td>"
											+ list[i].type.typeName
											+ "</td>\
						<td>"
											+ list[i].goodsName
											+ "</td>\
						<td>"
											+ list[i].goodsUnits
											+ "</td>\
						<td>"
											+ list[i].goodsQuantity
											+ "</td>\
						<td>"
											+ list[i].goodsStandard
											+ "</td>\
						<td>"
											+ list[i].goodsStock
											+ "</td>\
						<td>"
											+ list[i].goodsSales
											+ "</td>\
						<td>"
											+ list[i].goodsSpecial
											+ "</td>\
						<td>"
											+ list[i].goodsState
											+ "</td>\
						<td style='display:none;'><input th:value='"
											+ list[i].goodsId
											+ "'/></td>\
						<td class='td-manage' data-id=\""
											+ list[i].goodsId
											+ "\">\
							<a title='编辑' onclick=\"WeAdminEdit('修改商品','goods_open?goodsId="
											+ list[i].goodsId
											+ "', '"
											+ list[i].goodsId
											+ "', 650, 560)\" href='javascript:;'>\
								<i class='layui-icon'>&#xe642;</i>\
							</a>\
							&nbsp; &nbsp;\
							<a title=\"删除\" onclick=\"goods_dele(this)\" href='javascript:;'\>\
								<i class=\"layui-icon\">&#xe640;</i>\
							</a>\
							<span class=\"moveUpOrDown\" style=\""+(startRow==1&&i==0?'display:none':'')+"\">上移</span>" +
									" | <span class=\"moveUpOrDown\" style=\""+(endRow==count&&i==list.length-1?'display:none':'')+"\">下移</span>\
							</td>\
					</tr>")
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
					goodsId : $(tag).parents("td").data("id")
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

//全选
function selectall(tag) {
	let t = $(tag).prop("checked");
	$("input[type='checkbox']").prop("checked", t);
}
function sel(tag) {
	let t = $(tag).is(':checked');
	let value = true;
	for (let i = 0; i < $("input[name='chk_one']").length; i++) {
		if (!$("input[name='chk_one']")[i].checked) {
			value = false;
			break;
		}
	}
	$("input[name='selectall']").prop("checked", value);
	$(tag).prop("checked", t);
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