let tx = getContextPath();// 拿到当前项目名 /musicbar\
var _isExit;
$(function() {
	_isExit = true;
	$(".returnBtn").click(function() {
		history.back(-1);
	})
	let goods = Cookie.getCookie("goods");
	$.ajax({
		url : tx + '/front/confirmOrder',
		data : {
			"goods" : goods
		},
		type : 'post',
		dataType : 'json',
		async : false,
		success : function(goodsCart) {
			$(".infoCol span").html(goodsCart.sum);// 总金额
			$(".totalPay span").html(goodsCart.sum);// 总金额
			$(".infoCol .sp").html(goodsCart.num);// 总数量
			$(".infoCol .spty").html(goodsCart.typeNum);// 总类
			$('.orderList').empty();// 刪除box裡面的所有元素
			goodsCart.goodsList.forEach(function(obj) {
				divF(obj);
			})
		}
	})

})

function divF(obj, i) {
	let productLine = '<ul class="productLine">\
        <li class="productName" name="goodsName">'
			+ obj.goodsInfo.goodsName
			+ '</li>\
        <li class="productImg"><img class="img-responsive" src="'
			+ tx
			+ '/file/'
			+ obj.goodsInfo.attch.fileUel
			+ '"></li>\
        <li class="productInfo">\
            <div class="infoRow">\
                <div class="productCapacity">'
			+ obj.goodsInfo.goodsQuantity
			+ ''
			+ obj.goodsInfo.goodsUnits
			+ '</div>\
                <div class="productPrice">¥&nbsp;<span>'
			+ obj.goodsInfo.goodsPrice
			+ '<span>/'
			+ obj.goodsInfo.goodsStandard
			+ '</div> \
            </div>\
            <div class="infoRow">\
                <div class="productUnit">单位：'
			+ obj.goodsInfo.goodsStandard
			+ '</div>\
			<div class="selcetNo">数量：'
			+ obj.num
			+ '</div>\
			</div>\
        </li>\
        <li class="driver"></li>\
    </ul>';
	$('.orderList').append(productLine);
}

function save_click() {
	if (!_isExit) {
		return;
	}
	_isExit=false;
	let mobile = $("#mobile").val().trim();
	let reg = /^[1]\d{10}$/;
	let tableNum = $("#tableNum").val().trim();
	let reg1 = /^\d+$|^\d+[.]?\d+$/;
	
	if (mobile.length == '') {
		errorModalBox('手机号不能为空');
		return false;
	} else if (!reg.test(mobile)) {
		errorModalBox("手机号必须是以1开头的11位数字");
		return false;
	}
	if (tableNum.length == '') {
		errorModalBox('台号不能为空');
		return false;
	} else if (!reg1.test(tableNum)) {
		errorModalBox("台号必须是数字");
		return false;
	} else if (tableNum < 0 || tableNum > 200) {
		errorModalBox("台号必须在1-200之间");
		return false;
	}
	let goods = Cookie.getCookie("goods");
	$.ajax({
		url : tx + '/front/confirmOrderAdd',
		data : {
			"goods" : goods,
			"mobile" : mobile,
			"tableNum" : tableNum,
			"dersWay" : 1
		},
		type : 'post',
		dataType : 'json',
		async : false,
		beforeSend : function() {
			$("#loadingwrapper").css("display", "inline-block")
		},
		success : function(rs) {
			if(rs.code == 0){
				$("#loadingwrapper").css("display", "none")
				errorModalBox(rs.msg);
			}else{
				setTimeout(function() {
					location.href = "/musicbar/front/conFirm";
				},1000)
			}
		}

	})
}