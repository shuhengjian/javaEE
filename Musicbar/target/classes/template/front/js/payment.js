let tx = getContextPath();// 拿到当前项目名 /musicbar
var _isExit; // 全局
$(function() {
	_isExit = true;
	$(".returnBtn").click(function() {
		history.back(-1);
	})
	let goods = Cookie.getCookie("goods");
	if (goods == null || goods == '[]') {
		goodsCartNull("您还未添加商品 !");
	}
	$(".goPay").on("click", function() {
		var is;
		if (_isExit) {
			is = goPay(goods);
			_isExit=false;
		}
		if (is == -1) {
			return;
		}
		if (is == 0) {
			goodsCartNull("您还未添加商品 !");
			_isExit = true;
			return;
		}
	});
	$.ajax({
		url : tx + '/front/goods_car',
		data : {
			"goods" : goods
		},
		type : 'post',
		dataType : 'json',
		async : false,
		success : function(goodsCart) {
			$(".totalPay span").html(goodsCart.sum);
			$('.paymentList').empty();// 刪除box裡面的所有元素
			let cartNum = goodsCart.goodsList.length;
			$(".goPay span").text("(" + cartNum + ")");
			goodsCart.goodsList.forEach(function(obj) {
				divF(obj);
			})
		}
	})
	goods = JSON.parse(goods);
	$(".input-number-decrement").click(function() { // +
		inputNumber(goods, this, -1);
	});
	$(".input-number-increment").click(function() { // -
		inputNumber(goods, this, 1);
	});
	//
	$(".input-number").focus(function() {// 输入框获得焦点
		let value = $(this).val();
		$(this).blur(function() {// 输入框失去焦点
			let _value = $.trim($(this).val());
			if (_value > 200 || _value < 1 || _value == '') { // 数字非法
				$(this).val(value);
			} else {
				let gid = $(this).parents(".selcetNo").data("gid");
				checkGoods(gid, goods, parseInt(_value));
				$(this).val(_value);
				let pro = $(".productLine");
				var sumPrice = 0.0;
				for (var i = 0; i < pro.length; i++) {
					let price = $(pro[i]).find(".productPrice span").text();
					let number = $(pro[i]).find(".input-number").val();
					sumPrice += parseFloat(price * number);
				}
				$(".totalPay span").html(sumPrice.toFixed(1));
			}
		})
	})
	$(".delProduct").click(function() {
		let productLine = $(this).parents(".productLine");
		let gId = $(productLine).find(".selcetNo").data("gid");
		M.dialog3 = jqueryAlert({
			'title' : '删除商品',
			'content' : '确定要删除该商品吗？',
			'modal' : true,
			'buttons' : {
				'取消' : function() {
					M.dialog3.close();
				},
				'确定' : function() {
					let isb = delectGoods(gId, goods);
					if (isb) {
						M.dialog3.close();
						$(productLine).remove();
						right("删除成功!");
						setTimeout("window.location.reload()", 300);// 延迟刷新

					}
				}

			}
		})
		if (M.dialog3) {
			return M.dialog3.show();
		}

	})

})

function goPay(goods) {
	let is = 0;
	if (!_isExit) {
		return;
	} 
	console.log(goods+"cc")
	if (goods != null && goods.length > 0 && goods.indexOf('[]') == -1) {
		$.ajax({
			url : tx + '/front/goConfirmOrder',
			data : {
				"goods" : JSON.stringify(goods)
			},
			type : 'post',
			dataType : 'json',
			async : false,
			beforeSend : function() {
				$("#loadingwrapper").css("display", "inline-block")
			},
			success : function(goodsCart) {
				if (goodsCart.code == 0) {
					$("#loadingwrapper").css("display", "none")
					errorModalBox(goodsCart.msg);
					_isExit = false;
					is = -1;
				} else {
					location.href = tx+"/front/ordersQuery";
					is = 1;
					setTimeout(function() {
						location.href = tx+"/front/ordersQuery";
					}, 500)

				}

			}
		})
	}
	return is;
}

function divF(obj, i) {//
	let goodsUnits = obj.goodsInfo.goodsUnits;
		goodsUnits =goodsUnits.substring(goodsUnits.indexOf("/")+1);
	let productLine = '<ul class="productLine">\
        <li class="productName">'
			+ obj.goodsInfo.goodsName
			+ '</li>\
        <li class="productImg"><img class="img-responsive" src="'
			+ tx
			+ '/file/'
			+ obj.goodsInfo.attch.fileUel
			+ '"></li>\
        <li class="productInfo">\
            <div class="infoRow">\
                <a class="delProduct pull-right" href="javascript:void(0)"><span class="glyphicon glyphicon-trash"></span></a>\
            </div>\
            <div class="infoRow">\
                <div class="productCapacity">'
			+ obj.goodsInfo.goodsQuantity
			+ ''
			+ obj.goodsInfo.goodsUnits
			+ '</div>\
                <div class="productPrice">¥&nbsp;<span>'
			+ obj.goodsInfo.goodsPrice
			+ '</span>/'
			+ obj.goodsInfo.goodsStandard
			+ '</div> \
            </div>\
            <div class="infoRow">\
                <div class="productUnit">单位：'
			+ goodsUnits
			+ '</div>\
                <div class="selcetNo" data-gid="'
			+ obj.goodsInfo.goodsId
			+ '">\
                <span class="input-number-decrement">–</span><input class="input-number" maxlength="3" type="number" value="'
			+ obj.num
			+ '" min="0" max="10"><span class="input-number-increment">+</span></div>\
            </div>\
        </li>\
        <li class="driver"></li>\
    </ul>';
	$('.paymentList').append(productLine);
}
// 修改商品数量
function inputNumber(goods, e, num) {
	let gid = $(e).parents(".selcetNo").data("gid");
	let number = $(e).parents(".selcetNo").find(".input-number");// 数量对象
	let price = parseFloat($(e).parents(".productInfo").find(
			".productPrice span").text());// 单价
	let sum = parseFloat($(".totalPay span").text());// 总计
	let value = parseInt($(number).val()); // 数量
	let _num = value + num;
	if (_num > 0 && _num < 200) {
		if (num > 0) {
			sum += price;
		} else {
			sum -= price;
		}
		$(number).val(_num);
		checkGoods(gid, goods, _num)
		$(".totalPay span").text(sum.toFixed(1));// 保留一位小数
	}
}
// 修改数量保存至cookie
function checkGoods(gid, goods, num) {
	for ( var i in goods) { // 循环jsoN数组
		if (goods[i].goodsId == gid) {
			goods[i].num = num; // 修改数量
			break;
		}
	}
	Cookie.setCookie("goods", JSON.stringify(goods), "1h");
}
// 删除某一个商品
function delectGoods(gid, goods) {
	let isb = false;
	for ( var i in goods) { // 循环jsoN数组
		if (goods[i].goodsId == gid) {
			goods.splice(i, 1);
			isb = true;
			break;
		}
	}
	Cookie.setCookie("goods", JSON.stringify(goods), "1h");
	return isb;
}