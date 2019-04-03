$(function(){
	let present=window.location.pathname;
	//let _present=present.substring(present.lastIndexOf("/"));
	$(".cb_nav li").bind("click",function(){
		console.log(this)
		$(this).addClass("active");
		$(this).siblings().removeClass('active');
	})
	console.log($(".cb_nav_ul li:eq(6)"))
	if(present.indexOf("index")>0&&present.indexOf("frontJumpController")>0){
		$(".cb_nav_ul li:eq(0)").addClass("active");
	}
	if(present.indexOf("list")>0&&present.indexOf("ttyProductController")>0&&present.indexOf("/1/")>0){
		$(".cb_nav_ul li:eq(1)").addClass("active");
		$(".cb_nav_ul li:eq(1)").siblings().removeClass("active");
	}
	if(present.indexOf("list")>0&&present.indexOf("ttyProductController")>0&&present.indexOf("/2/")>0){
		$(".cb_nav_ul li:eq(2)").addClass("active");
		$(".cb_nav_ul li:eq(2)").siblings().removeClass("active");
	}
	if(present.indexOf("news")>0&&present.indexOf("ttyNewsFrontController")>0){
		$(".cb_nav_ul li:eq(3)").addClass("active");
		$(".cb_nav_ul li:eq(3)").siblings().removeClass("active");
	}
	if(present.indexOf("after_service")>0&&present.indexOf("frontJumpController")>0){
		$(".cb_nav_ul li:eq(4)").addClass("active");
		$(".cb_nav_ul li:eq(4)").siblings().removeClass("active");
	}
	if(present.indexOf("about_us")>0&&present.indexOf("frontJumpController")>0){
		$(".cb_nav_ul li:eq(5)").addClass("active");
		$(".cb_nav_ul li:eq(5)").siblings().removeClass("active");
	}
	if(present.indexOf("people_recruit")>0&&present.indexOf("frontJumpController")>0){
		$(".cb_nav_ul li:eq(6)").addClass("active");
		$(".cb_nav_ul li:eq(6)").siblings().removeClass("active");
	}
	if(present.indexOf("contact_us")>0&&present.indexOf("frontJumpController")>0){
		$(".cb_nav_ul li:eq(7)").addClass("active");
		$(".cb_nav_ul li:eq(7)").siblings().removeClass("active");
	}
})