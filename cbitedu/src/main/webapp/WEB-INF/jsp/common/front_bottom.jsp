<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>

<!--[if !IE]>页脚_Begin<![endif]-->
<div class="footer">
	<div class="cb-footer">
		<div class="container-fluid">
			<div class="row">
				<!--[if !IE]>页脚导航_Begin<![endif]-->
				<div class="col-sm-5 col-xs-12">
					<ul class="cb-footer-menu">
						<li><b>栏目导航</b></li>
						<div class="cb-driver"></div>
						<li><a href="${ctx}/index.html">首页</a></li>
						<div class="cb-driver"></div>
						<li><a href="${ctx }/ttyProductController/list/1/parallelCar.html">平行进口</a></li>
						<div class="cb-driver"></div>
						<li><a href="${ctx }/ttyProductController/list/2/parallelCar.html">中规车</a></li>
						<div class="cb-driver"></div>
						<li><a href="${ctx }/ttyNewsFrontController/news.html">新闻中心</a></li>
						<div class="cb-driver"></div>
						<li><a href="${ctx }/frontJumpController/after_service.html">售后服务</a></li>
						<div class="cb-driver"></div>
						<li><a href="${ctx }/frontJumpController/about_us.html">关于我们</a></li>
						<div class="cb-driver"></div>
						<li><a href="${ctx }/frontJumpController/people_recruit.html">诚聘英才</a></li>
						<div class="cb-driver"></div>
						<li><a href="${ctx }/frontJumpController/contact_us.html">联系我们</a></li>
						
					</ul>

					<ul class="cb-footer-menu">
						<li><b>车主服务</b></li>
						<div class="cb-driver"></div>
						<li><a href="#">伴侣贴心服务</a></li>
						<div class="cb-driver"></div>
						<li><a href="#">汽车三包政策</a></li>
						<div class="cb-driver"></div>
						<li><a href="http://xiangxi.monfr.com/">车架号查询</a></li>
					</ul>

					<div class="cb-copyright">Copyright © 2016~2017
						湖南天逸凯腾汽车贸易有限公司</div>
					<div class="cb-copyright">
						湘ICP备17000093号-1 <img src="${ctx }/images/pic.gif">
					</div>
					<div class="cb-copyright">技术支持：湖南创蓝信息科技有限公司</div>
				</div>
				<!--[if !IE]>页脚logo_Begin<![endif]-->
				<div class="col-sm-2 col-xs-6">
					<div class="cb-footer-img">
						<img class="img-responsive" src="${ctx }/images/logo-footer.png">
					</div>
				</div>
				<!--[if !IE]>页脚-微信_Begin<![endif]-->
				<div class="col-sm-2 col-xs-6">
					<div class="cb-footer-img">
						<img class="img-responsive" src="${ctx }/images/cb-weChart.png">
					</div>
				</div>
				<!--[if !IE]>页脚-联系_Begin<![endif]-->
				<div class="col-sm-3 col-xs-12">
					<div class="cb-content">
						<span class="glyphicon glyphicon-phone-alt"></span><b>电话：</b> 0731
						- 82181033<br> <span class="glyphicon glyphicon-envelope"></span><b>邮箱：</b>
						tygjmc@163.com<br> <span
							class="glyphicon glyphicon-map-marker"></span><b>地址：</b>
						湖南省长沙市开福区三一大道370号<br> <span
							class="glyphicon glyphicon-circle-arrow-right"></span><b>入口：</b>
						<a href="${ctx }/dispatch.html">后台管理</a>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<!-- 回到顶部 -->
<div style="width:48px;height:48px;position: fixed;bottom:10px;right:0px;border: 1px solid grey;" title="回到顶部">
	<a href="#">
	<img style="width:100%;height:48px" src="${ctx }/images/置顶.png">
	</a>
</div>
<script type="text/javascript">
	$(function() {
		$('.cb-car-child img').error(function() { //加载图片 出现404状态时触发
			$(this).attr("src", "${ctx}/images/car/trending-car-img-3.jpg"); //将加载不到的图片 的src属性 修改成默认 图片 ，注意：默认图片必须保证存在，否则 会一直 调用 此函数
		});
		$('.cb-recommend-box img').error(function() { //加载图片 出现404状态时触发
			$(this).attr("src", "${ctx}/images/car/trending-car-img-4.jpg"); //将加载不到的图片 的src属性 修改成默认 图片 ，注意：默认图片必须保证存在，否则 会一直 调用 此函数
		});
		$('.cb-hot-img img').error(function() { //加载图片 出现404状态时触发
			$(this).attr("src", "${ctx}/images/news/focusNews.jpg"); //将加载不到的图片 的src属性 修改成默认 图片 ，注意：默认图片必须保证存在，否则 会一直 调用 此函数
		});
		$('.cb-type-box img').error(function() { //加载图片 出现404状态时触发
			$(this).attr("src", "${ctx}/images/sign/sign-all.jpg"); //将加载不到的图片 的src属性 修改成默认 图片 ，注意：默认图片必须保证存在，否则 会一直 调用 此函数
		});
		$('.cb-news-box img').error(function() { //加载图片 出现404状态时触发
			$(this).attr("src", "${ctx}/images/news/new-1.jpg"); //将加载不到的图片 的src属性 修改成默认 图片 ，注意：默认图片必须保证存在，否则 会一直 调用 此函数
		});
		$('.cb-car-box .cb-img img').error(function() { //加载图片 出现404状态时触发
			$(this).attr("src", "${ctx}/images/car/trending-car-img-8.jpg"); //将加载不到的图片 的src属性 修改成默认 图片 ，注意：默认图片必须保证存在，否则 会一直 调用 此函数
		});

	/* 	let cbAdvertis = $(".cb_advertis");
		let size = cbAdvertis.length;
		var arr = new Array("banner-image-1.jpg", "banner-image-2.jpg",
				"banner-image-3.jpg");
		console.log(size);
		for (var i = 0; i < 3 - size; i++) {
			let div = '<div class="cb_advertis item">\
				<a href="#"><img alt="Responsive image"\
					src="${ctx }/images/carousel/'
					+ arr[i] + '" style="width: 100%"></a>\
			</div>';
			$(".cb_carousel").append(div);
		}
		if (size == 0) {
			$(".cb_advertis:first").addClass("active");
		}
		let errorSize=0;
		  $('.cb_advertis img').error(function () {   //加载图片 出现404状态时触发
			 	 errorSize++;
		  console.log(errorSize);
		 		 console.log(errorSize)
		  		for (var i = 0; i < errorSize; i++) {
		  			 $(this).attr("src", "${ctx }/images/carousel/"+arr[3-i]);
				}
			 
		});  */
		
	})
</script>