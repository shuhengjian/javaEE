var tx;
var _isExit; // 全局
$(function() {
	_isExit = true;
	let finished = true; // 全局变量限制上拉加载时多次请求
	tx = getContextPath();// 项目名 /musicbar
	let typeIds = $(".job_sub .car_item");// 左侧类型列表
	// 点击事件
	let goods = Cookie.getCookie("goods");
	if (goods != null) {
		goods = JSON.parse(goods); // 转成json对象
		loadingGoodsNum(goods, typeIds);
	}
	$(".returnBtn").click(function() {
		location.href = 'index.html';
	})
	// 上拉加载更多
	var startX, startY, moveEndX, moveEndY, Y;
	$(".l_right").on('touchstart', function(e) {
		e.preventDefault();
		startX = e.originalEvent.changedTouches[0].pageX, // 起始横坐标
		startY = e.originalEvent.changedTouches[0].pageY
	});
	$(".l_right")
			.on(
					"touchmove",
					function(e) {
						if (!finished) {
							return;
						}
						let scrollTop = $(".l_right").scrollTop()
								|| document.body.scrollTop
								|| window.pageYOffset;
						let clientHeight = $(".l_right")[0].clientHeight;
						let scrollHeight = $(".l_right")[0].scrollHeight;
						e.preventDefault();
						moveEndX = e.originalEvent.changedTouches[0].pageX;
						moveEndY = e.originalEvent.changedTouches[0].pageY;
						Y = moveEndY - startY;
						if (Y < 0 && (scrollTop + clientHeight >= scrollHeight) && $(".productLine").length>2) {
							finished = false;
							// 向上拉
							let pageNum = $(".fr_ite_beer").data("pagenum");
							let pages = $(".fr_ite_beer").data("pages");
							if (pageNum >= pages) {
								finished = false;
								$(".fr_ite_beer").append(
										'<div class="update">没有更多了...</div>');
								return;
							}
							console.log(11)
							$(".fr_ite_beer")
									.append('<div class="update">正在加载<img src="img/looding1.gif"></div>');
							setTimeout(function() {
								pageGoods(tx);
								finished = true;
							}, 1500);
						}
						/*setTimeout(function() {
							finished = true;
						}, 1500);*/

					})
	$(".buyBtn").on('touchstart', function(event) {
		buyBtn(event, this);
	});
})
function buyBtn(event, _this) {
	let tId = $(_this).parents(".productInfo").data("tid"); // 该商品的类型id
	let gId = $(_this).parents(".productInfo").data("gid");// 该商品的商品Id
	let typeIds = $(".job_sub .car_item");// 左侧类型列表
	let e = event;
	let goods = Cookie.getCookie("goods");
	if (goods != null) {
		let data = "goodsId=" + gId + "&goods=" + goods;
		$.ajax({
			url : tx + '/front/addGoodsCart',
			data : {
				"goodsId" : gId,
				"goods" : goods
			},
			type : 'post',
			dataType : 'json',
			async : false,
			success : function(res) {
				if (res.code == 0) {
					errorModalBox(res.msg);
					_isExit = false;
				} else {
					_isExit = true;
				}
			}
		})
	}
	if (!_isExit) {
		return;
	}
	for (var i = 0; i < typeIds.length; i++) {
		let _typeId = $(typeIds[i]).data("tid");
		if (_typeId == tId) {
			let num = 1;
			let isb = setGoodsCookie(e, gId, tId, num, typeIds);// 将商品保存在cookie中
			if (isb) {
				flayCar(e);
			}
		}
	}

}
function pageGoods(tx) {
	let pageNum = $(".fr_ite_beer").data("pagenum");
	let pages = $(".fr_ite_beer").data("pages");
	if (pageNum < pages) {
		pageNum++;
		let tid = $(".productInfo").data("tid");
		let data = "type.typeId=" + tid + "&pageIndex=" + pageNum;
		$.post(tx + "/front/page_goods?" + data, function(res) {
			res = JSON.parse(res); // 转成json对象
			$(".fr_ite_beer").data("pagenum", res.pageNum);
			let l = $(".productLine").length;
			let size = res.total
			res = res.list;
			for ( var i in res) {
				if (l < size) {
					// finished = true;
					addGoods(tx, res, i);

				}
			}
			$('.update').css("display", "none");
		});
	}
}
function addGoods(tx, res, i) {
	let goodsUl = '<ul class="productLine">\
	<li class="productName">'
			+ res[i].goodsName
			+ '</li>\
	<li class="productImg"><img class="img-responsive"\
		src="'
			+ tx
			+ '/file/'
			+ res[i].attch.fileUel
			+ '"></li>\
	<li class="productInfo" data-tid="'
			+ res[i].type.typeId
			+ '"\
		data-gid="'
			+ res[i].goodsId
			+ '">\
		<div class="infoRow">\
			<div class="productCapacity">'
			+ res[i].goodsQuantity
			+ '/'
			+ res[i].goodsUnits
			+ '</div>\
			<div class="productPrice">¥&nbsp;'
			+ res[i].goodsPrice
			+ '/'
			+ res[i].goodsStandard
			+ '</div>\
		</div>\
		<div class="infoRow">\
			<div class="productFabulous">\
				<span class="glyphicon glyphicon-heart-empty"></span>'
			+ res[i].goodsSales
			+ '\
			</div>\
			<div class="buyBtn">\
				<span class="glyphicon glyphicon-plus"></span>\
			</div>\
		</div>\
	</li>\
	<li class="driver"></li>\
</ul>';
	$(".fr_ite_beer").append(goodsUl);
	$(".buyBtn").on('touchstart', function(event) {
		buyBtn(event, this);
	});
}
// 加入购物车动画
function flayCar(event) {
	var offset = $('.glyphicon-shopping-cart').offset();
	var thisItem = $(this);
	var flyer = $('<span class="goods_car_num" style="display: block;">1</span>')
	var scrollTop = $(document).scrollTop();
	flyer.fly({
		start : {
			left : event.originalEvent.changedTouches[0].pageX, // 起始横坐标
			top : event.originalEvent.changedTouches[0].pageY
		// 起始纵坐标
		},
		end : {
			left : offset.left + 3, // 结束
			top : offset.top + 3,
			width : 0,
			height : 0
		},
	});
}

// 设置商品到Cookie
function setGoodsCookie(e, gid, tid, num, typeIds) {
	let goods = Cookie.getCookie("goods");
	let isGoods = false;
	if (goods != null) {
		goods = JSON.parse(goods); // 转成json对象
		let sum = 0;
		for ( var i in goods) { // 循环jsoN数组
			if (goods[i].goodsId == gid) {
				flayCar(e);
				goods[i].num++; // 添加数量
				isGoods = true; // 数组中已存在该goodsId商品
				break;
			}
		}
		if (!isGoods) { // 不存在该goodsId的商品添加
			isGoods = true;
			let _goods = {
				goodsId : gid,
				typeId : tid,
				num : num
			}
			goods[goods.length] = _goods;
		}
	} else { // 第一次点击添加
		isGoods = true;
		goods = [ {
			goodsId : gid,
			typeId : tid,
			num : num
		} ];
	}
	if (typeIds != null) {
		loadingGoodsNum(goods, typeIds);
	}
	Cookie.setCookie("goods", JSON.stringify(goods), "1h");
	return isGoods;
}
// 对商品进行分组 设置分类下的商品数量
function loadingGoodsNum(goods, typeIds) {
	let data = [];
	// 对goods数组按类型Id分组保存在data数组
	for (var i = 0; i < goods.length; i++) {
		if (!data[goods[i].typeId]) {
			var arr = [];
			arr.push(goods[i]);
			data[goods[i].typeId] = arr;
		} else {
			data[goods[i].typeId].push(goods[i]);
		}
	}
	for (var i = 0; i < typeIds.length; i++) {
		let _typeId = $(typeIds[i]).data("tid");
		if (data[_typeId] != undefined) {
			let num = 0;
			data[_typeId].forEach(function(obj) {
				num += obj.num;
			});
			// 对应类型下的商品数量
			if (num > 0) {
				$(typeIds[i]).find(".goods_car_num").css("display", "block")
				$(typeIds[i]).find(".goods_car_num").text(num);
			} else {
				$(typeIds[i]).find(".goods_car_num").css("display", "none")
			}
		}
	}
	let sum = 0;
	goods.forEach(function(obj) {
		sum += obj.num;
	});
	// 设置购物车中商品的数量
	if (sum > 0) {
		$(".cb_goods_car").css("display", "inline-block")
		$(".cb_goods_car").text(sum);
	} else {
		$(".cb_goods_car").css("display", "none")
	}

}
