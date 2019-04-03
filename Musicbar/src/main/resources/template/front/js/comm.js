$(function() {
})
// 返回当前项目名
function getContextPath() {
	var pathName = window.document.location.pathname;
	// 获取带"/"的项目名，如：/myproj
	var projectName = pathName
			.substring(0, pathName.substr(1).indexOf('/') + 1);
	return projectName;
}
// cookie操作方法封装
var Cookie = {
	setCookie : function(key, val, time) { // 设置cookie方法 ，过期日期的时间
		var strsec = getsec(time);
		val = compileStr(val); // 加密
		var exp = new Date();// 获取当前时间
		exp.setTime(exp.getTime() + strsec * 1);
		document.cookie = key + "=" + val + ";expires=" + exp.toGMTString(); // 设置cookie
	},
	getCookie : function(key) { // 获取cookie方法
		/* 获取cookie参数 */
		var cookies = document.cookie.replace(/[ ]/g, ""); // 获取cookie，并且将获得的cookie格式化，去掉空格字符
		var arrCookie = cookies.split(";") // 将获得的cookie以"分号"为标识
		// 将cookie保存到arrCookie的数组中
		var tips; // 声明变量tips
		for (var i = 0; i < arrCookie.length; i++) { // 使用for循环查找cookie中的tips变量
			var arr = arrCookie[i].split("="); // 将单条cookie用"等号"为标识，将单条cookie保存为arr数组
			if (key == arr[0]) { // 匹配变量名称，其中arr[0]是指的cookie名称，如果该条变量为tips则执行判断语句中的赋值操作
				tips = uncompileStr(arr[1]);// 将cookie的值赋给变量tips并解密
				break; // 终止for循环遍历
			}
		}
		return tips;
	},
	deleteCookie : function(key) { // 删除cookie方法
		var date = new Date(); // 获取当前时间
		date.setTime(date.getTime() - 10000); // 将date设置为过去的时间
		document.cookie = key + "=v; expires =" + date.toGMTString(); // 设置cookie
	}
};
// 设置Cookie失效时间
function getsec(str) {
	var str1 = str.substring(0, 1) * 1;
	var str2 = str.substring(1, str.length);
	if (str2 == "s") {
		return str1 * 1000; // 秒
	} else if (str2 == "h") {
		return str1 * 60 * 60 * 1000; // 小时
	} else if (str2 == "d") { // 天
		return str1 * 24 * 60 * 60 * 1000;
	}
}
var M = {}
/* 警告弹窗 */
function warning(str) {
	M.dialog12 = jqueryAlert({
		'icon' : 'img/warning.png',
		'content' : str,
		'closeTime' : 1500
	});
	if (M.dialog12) {
		return M.dialog12.show();
	}
}
/* 成功提示 */
function goodsCartNull(str) {
	M.dialog12 = jqueryAlert({
		'content' : str,
		'closeTime' : 1000
	});
	if (M.dialog12) {
		return M.dialog12.show();
	}
}
/* 成功提示 */
function right(str) {
	M.dialog12 = jqueryAlert({
		'icon' : 'img/right.png',
		'content' : str,
		'closeTime' : 500
	});
	if (M.dialog12) {
		return M.dialog12.show();
	}
}
/* 确认提示框 */
function errorModalBox(str) {
	M.dialog12 = jqueryAlert({
		'content' : str,
		'modal' : true,
		'buttons' : {
			'确定' : function() {
				M.dialog12.close();
				_isExit=true;
			}
		}
	})
	if (M.dialog12) {
		return M.dialog12.show();
	}
}
// 对字符串进行加密
function compileStr(value) {
	var c = String.fromCharCode(value.charCodeAt(0) + value.length);
	for (var i = 1; i < value.length; i++) {
		c += String.fromCharCode(value.charCodeAt(i) + value.charCodeAt(i - 1));
	}
	return escape(c);
}
// 字符串进行解密
function uncompileStr(value) {
	value = unescape(value);
	var c = String.fromCharCode(value.charCodeAt(0) - value.length);
	for (var i = 1; i < value.length; i++) {
		c += String.fromCharCode(value.charCodeAt(i) - c.charCodeAt(i - 1));
	}
	return c;
}