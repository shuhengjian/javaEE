var tx;
var isExit;
$(function() {
	isExit = true;
	tx = getContextPath();// 拿到当前项目名 /musicbar
	Cookie.deleteCookie("goods");
	$(".returnBtn").click(function() {
		if (!isExit) {
			return;
		}
		M.dialog3 = jqueryAlert({
			'title' : '支付',
			'content' : '是否继续支付？',
			'modal' : true,
			'buttons' : {
				'否' : function() {
					location.href = tx + "/front/TOrFrontqueryAll";
				},
				'是' : function() {
					isExit = true;
					M.dialog3.close();
				}

			}
		})
		if (M.dialog3) {
			isExit = false;
			return M.dialog3.show();
		}
	})
})

function orders_click() {
	let radio = $('input:radio[name="optionsRadios"]:checked').val();
	$.ajax({
		url : tx + '/front/OrdersSave',
		data : {
			"radio" : radio,
		},
		type : 'post',
		dataType : 'json',
		async : false,
		beforeSend : function() {
			$("#loadingwrapper").css("display", "inline-block")
		},
		success : function(ok) {
			setTimeout(function() {
				location.href = "/musicbar/front/payS";
			}, 1500)

		}
	})
}
