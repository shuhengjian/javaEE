<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>
	<!DOCTYPE head PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<head>
<title>天逸国际</title>
<!-- Meta -->
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="IE=9" />
<!-- 360浏览器默认采用高速模式渲染页面 -->
<meta name="renderer" content="webkit">

<meta name="keywords" content="Demo">
<meta name="description" content="Demo">
<meta name="author" content="">

<!-- Favicon -->
<link rel="shortcut icon" href="${ctx }/img/favicon.ico">
<!-- Global CSS  -->
<link rel="stylesheet" href="${ctx }/css/bootstrap.min.css">
<!--Custom Css-->
<link rel="stylesheet" href="${ctx }/css/ty.css">
<link rel="stylesheet" href="${ctx }/css/ty-mobil.css">
<!-- CSS Implementing Plugins -->
<link rel="stylesheet" href="${ctx }/css/line-icons.css">
<link rel="stylesheet" href="${ctx }/css/font-awesome.min.css">
<link rel="stylesheet" href="${ctx }/normalize.css">

<link rel="stylesheet" href="${ctx }/css/zxkf.css">

<script type="text/javascript" src="${ctx }/js/jquery.min.js"></script>
<script type="text/javascript" src="${ctx }/js/bootstrap.min.js"></script>
<script type="text/javascript" src="${ctx }/js/front_index.js"></script>
<script type="text/javascript" src="${ctx }/js/zxkf.js"></script>

<script type="text/javascript">
    window.onload = autologin;
	window.onresize = autologin;
  
    /*登录界面自动宽高*/
function autologin(){
	var browserH = document.documentElement.clientHeight;
	
	var hostbodyH = document.getElementById("contactus");
	hostbodyH.style.minHeight = browserH - 100 + "px";	
}
</script>
<!--[if lt IE 9]>
<script src="js/html5shiv.js"></script>
<script src="js/respond.min.js"></script>
<![endif]-->
</head>
<!--[if !IE]>注释规范_Begin<![endif]-->
<!--[if !IE]>注释规范_End<![endif]-->
<body>
    <div class="header">
        <!--[if !IE]>header-bar_Begin<![endif]-->
        <div class="cb-header-tool">
            <div class="container-fluid">
                <div class="col-md-3 col-sm-3 cb-cp-logo hidden-xs">
                    <img class="img-responsive" src="${ctx }/images/cp-logo.png">
                </div>
                
                <div class="col-md-3 col-md-offset-1 col-sm-4">
                    <div class="input-group hidden">
                        <input type="text" class="form-control">
                        <span class="input-group-btn"><button class="btn btn-default" type="button">搜索</button></span>
                    </div>
                </div>
                
                <div class="col-md-5 col-sm-5 hidden-xs">
                    <div class="cb-cp-tel"><i>服务热线：0731 - 82181033</i></div>
                </div>
            </div>
        </div>
        
        <!--[if !IE]>网站导航_Begin<![endif]-->
        <nav class="navbar navbar-default cb-navbar" role="navigation">
            <div class="container-fluid">
                <div class="navbar-header">
                    <button type="button" class="navbar-toggle cb-navbar-toggle" data-toggle="collapse"
                            data-target="#example-navbar-collapse">
                        <span class="sr-only">切换导航</span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                    </button>
                    <a href="#" title="#"><img class="img-responsive visible-xs-block" src="${ctx }/images/cp-logo.png"></a>
                </div>
                <div class="collapse navbar-collapse cb-navbar-nav" id="example-navbar-collapse">
                    <ul class="nav navbar-nav cb-navbar-nav pull-right cb_nav_ul">
                        <li class="active"><a href="${ctx}/frontJumpController/index.html"><span class="glyphicon glyphicon-play"></span>天逸名车</a></li>
                        <li><a href="${ctx }/ttyProductController/list/1/parallelCar.html"><span class="glyphicon glyphicon-play"></span>平行进口</a></li>
                        <li><a href="${ctx }/ttyProductController/list/2/parallelCar.html"><span class="glyphicon glyphicon-play"></span>中规车</a></li>
                        <li><a href="${ctx }/ttyNewsFrontController/news.html"><span class="glyphicon glyphicon-play"></span>新闻中心</a></li>
                        <li><a href="${ctx }/frontJumpController/after_service.html"><span class="glyphicon glyphicon-play"></span>售后服务</a></li>
                        <li><a href="${ctx }/frontJumpController/about_us.html"><span class="glyphicon glyphicon-play"></span>关于我们</a></li>
                        <li><a href="${ctx }/frontJumpController/people_recruit.html"><span class="glyphicon glyphicon-play"></span>诚聘英才</a></li>
                        <li><a href="${ctx }/frontJumpController/contact_us.html"><span class="glyphicon glyphicon-play"></span>联系我们</a></li>
                    </ul>
                </div>
            </div>
        </nav>
    </div>
    
    <div id="contactus">
        <div class="cb-contact-box">
            <div class="cb-contact-word">
                <ul class="cb-ul-info">
                    <li class="cb-cp-name">湖南天逸凯腾汽车贸易有限公司<li>
                    <li class="cb-driver"></li>
                    <li class="cb-cp-info"><span class="glyphicon glyphicon-earphone p-marginR10"></span>电话：0731-82181033</li>
                    <li class="cb-cp-info"><span class="glyphicon glyphicon-envelope p-marginR10"></span>邮箱：postmaster@creatorblue.com</li>
                    <li class="cb-cp-info"><span class="glyphicon glyphicon-map-marker p-marginR10"></span>地址：湖南省长沙市开福区三一大道370号</li>
                </ul>
            </div>
            <div class="cb-contact-wechart">
                <div class="cb-wechart-box">
                    <img class="img-responsive" src="${ctx }/images/cb-weChart.png">
                    <div class="cb-wechart-word"><font style="color:#fd0000;">扫扫二维码</font><br>关注天逸</div>
                </div>
            </div>
        </div>
    </div>
  	<!-- 在线客服begin -->
	<div class="qq">
		<div id="rightArrow">
			<a href="javascript:;" title="在线客户"></a>
		</div>
		<div id="floatDivBoxs">
			<div class="floatDtt">在线客服</div>
			<div class="floatShadow">
				<ul class="floatDqq">
					<li style="padding-left:0px;">
						<a target="_blank" href="tencent://message/?uin=4612117&Site=sc.chinaz.com&Menu=yes"><img src="${ctx }/images/qq.png" align="absmiddle">&nbsp;&nbsp;在线客服1号</a>
					</li>
					<li style="padding-left:0px;">
						<a target="_blank" href="tencent://message/?uin=28449472&Site=sc.chinaz.com&Menu=yes"><img src="${ctx }/images/qq.png" align="absmiddle">&nbsp;&nbsp;在线客服2号</a>
					</li>
					<li style="padding-left:0px;">
						<a target="_blank" href="tencent://message/?uin=1457465352&Site=sc.chinaz.com&Menu=yes"><img src="${ctx }/images/qq.png" align="absmiddle">&nbsp;&nbsp;在线客服3号</a>
					</li>
				</ul>
			</div>
		</div>
	</div>	
	<!-- 在线客服end -->  
     <!--[if !IE]>页脚_Begin<![endif]-->
   <%@ include file="/WEB-INF/jsp/common/front_bottom.jsp"%>
</body>