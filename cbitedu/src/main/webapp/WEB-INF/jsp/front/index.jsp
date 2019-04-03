<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/front_hread.jsp"%>

<!--[if !IE]>轮播广告_Begin<![endif]-->
<div id="imgCarousel" class="carousel slide" data-ride="carousel">
	<!-- Indicators -->
	<ol class="carousel-indicators">
		<!-- <li data-target="#imgCarousel" data-slide-to="0" class="active"><div></div></li>
		<li data-target="#imgCarousel" data-slide-to="1"><div></div></li>
		<li data-target="#imgCarousel" data-slide-to="2"><div></div></li>  -->
		  <c:forEach  var="i"  varStatus="c" begin="0" end="${AdvertisingSize>0?AdvertisingSize-1:0 }">
		 	<li data-target="#imgCarousel" data-slide-to="${i }" <c:if test="${c.first }">class="active"</c:if> ><div></div></li>
		 </c:forEach> 
	</ol>

	<!-- Wrapper for slides-->
	<div class="carousel-inner cb_carousel" role="listbox" >
	 <c:forEach items="${AdvertisingList }" var="alist"  varStatus="c" begin="0">
		<div class="cb_advertis ${c.first?'item active': 'item'}" >
			<a href="#"><img alt="Responsive image" 
				src="${ctx }/uploads/${alist.fileUrl }" style="width: 100%;height: 100%"></a>
		</div>
		</c:forEach>
		<!-- <div class="item">
			<a href="#"><img alt="Responsive image"
				src="images/carousel/banner-image-2.jpg"></a>
		</div>
		<div class="item">
			<a href="#"><img alt="Responsive image"
				src="images/carousel/banner-image-3.jpg"></a>
		</div>  -->
	</div>

	<!-- Controls -->
	<a class="left carousel-control" href="#imgCarousel" data-slide="prev">
		<div class="p-blockLeft">
			<span class="glyphicon glyphicon-menu-left" aria-hidden="true"></span>
		</div>
	</a> <a class="right carousel-control" href="#imgCarousel"
		data-slide="next">
		<div class="p-blockRight">
			<span class="glyphicon glyphicon-menu-right" aria-hidden="true"></span>
		</div>
	</a>
</div>

<!--[if !IE]>平行进口车_Begin<![endif]-->
<div class="cb-import-index">
	<!--[if !IE]>全部分类_Begin<![endif]-->
	<div class="cb-left-area">
		<div class="cb-recom">
			<font>| 全部分类</font>
		</div>
		<!--[if !IE]>品牌_Begin<![endif]-->
		<div class="cb-type-area">
			<div class="cb-type-header">
				<div class="cb-type-title">品牌</div>
				<a class="pull-right" href="${ctx }/ttyProductController/list/1/parallelCar.html">更多品牌</a>
			</div>

			<div class="cb-type-body">
				<div class="cb-type-box ${product.ttyBrand.brandId==null?'ty_active':''}">
					<a href="${ctx }/frontJumpController/index/ttyBrand.brandId/index.html"  onclick="selectProduct(this)" data-bid=0 style="text-align: center;"> <img class="img-responsive"
						src="${ctx }/images/sign/sign-all.jpg" />
						<div class="cb-brand-name cb-texthidden">全部</div>
					</a>
				</div>
				<c:forEach items="${BrandList }" var="blist">
				 <div class="cb-type-box ${blist.pkid==product.ttyBrand.brandId?'ty_active':''}" style="text-align: center;">
					<a href="${ctx }/frontJumpController/index/ttyBrand.brandId/${blist.pkid}.html"> <img class="img-responsive"
						src="${ctx }/uploads/${blist.fileUrl }">
						<div class="cb-brand-name cb-texthidden">${blist.fileName}</div>
					</a>
				</div>
				</c:forEach>
				<!--
				<div class="cb-type-box">
					<a href="#"> <img class="img-responsive"
						src="images/sign/sign2.jpg">
						<div class="cb-brand-name cb-texthidden">大众</div>
					</a>
				</div>
				<div class="cb-type-box">
					<a href="#"> <img class="img-responsive"
						src="images/sign/sign3.jpg">
						<div class="cb-brand-name cb-texthidden">mini</div>
					</a>
				</div>
				<div class="cb-type-box">
					<a href="#"> <img class="img-responsive"
						src="images/sign/sign4.jpg">
						<div class="cb-brand-name cb-texthidden">丰田</div>
					</a>
				</div>
				<div class="cb-type-box">
					<a href="#"> <img class="img-responsive"
						src="images/sign/sign5.jpg">
						<div class="cb-brand-name cb-texthidden">奥迪</div>
					</a>
				</div>
				<div class="cb-type-box">
					<a href="#"> <img class="img-responsive"
						src="images/sign/sign15.jpg">
						<div class="cb-brand-name cb-texthidden">雪佛兰</div>
					</a>
				</div>
				<div class="cb-type-box">
					<a href="#"> <img class="img-responsive"
						src="images/sign/sign7.jpg">
						<div class="cb-brand-name cb-texthidden">英菲尼迪</div>
					</a>
				</div>
				<div class="cb-type-box">
					<a href="#"> <img class="img-responsive"
						src="images/sign/sign8.jpg">
						<div class="cb-brand-name cb-texthidden">宝马</div>
					</a>
				</div>
				<div class="cb-type-box">
					<a href="#"> <img class="img-responsive"
						src="images/sign/sign9.jpg">
						<div class="cb-brand-name cb-texthidden">凯迪拉克</div>
					</a>
				</div>
				<div class="cb-type-box">
					<a href="#"> <img class="img-responsive"
						src="images/sign/sign10.jpg">
						<div class="cb-brand-name cb-texthidden">玛莎拉蒂</div>
					</a>
				</div>
				<div class="cb-type-box">
					<a href="#"> <img class="img-responsive"
						src="images/sign/sign11.jpg">
						<div class="cb-brand-name cb-texthidden">奔驰</div>
					</a>
				</div>
				<div class="cb-type-box">
					<a href="#"> <img class="img-responsive"
						src="images/sign/sign14.jpg">
						<div class="cb-brand-name cb-texthidden">林肯</div>
					</a>
				</div> -->
			</div>
		</div>
		<!--[if !IE]>价格_Begin<![endif]-->
		<div class="cb-type-area">
			<div class="cb-type-header">
				<div class="cb-type-title">价格区间</div>
			</div>

			<div class="cb-type-body">
				<div class="cb-type-pricerange ${product.ttyPrice.priceId==null?'ty_active':''}">
					<a href="${ctx }/frontJumpController/index/ttyPrice.priceId/index.html"  onclick="selectProduct(this)" data-pid=0>全部</a>
				</div>
				<c:forEach items="${priceList }" var="plist">
				 <div class="cb-type-pricerange ${product.ttyPrice.priceId==plist.priceId?'ty_active':''}">
					<a href="${ctx }/frontJumpController/index/ttyPrice.priceId/${plist.priceId}.html">
					<c:if test="${ plist.priceMax !=null && plist.priceMin !=null}">
					<fmt:formatNumber value="${plist.priceMin  }" type="number"/>~<fmt:formatNumber value="${plist.priceMax  }" type="number"/>W
					</c:if>
					<c:if test="${ plist.priceMax ==null}">
						<fmt:formatNumber value="${plist.priceMin  }" type="number"/>W以上
					</c:if>
					<c:if test="${ plist.priceMin ==null}">
						<fmt:formatNumber value="${plist.priceMax }" type="number"/>W以下
					</c:if>
					</a>
				</div>
				</c:forEach>
				<!--
				<div class="cb-type-pricerange">
					<a href="#">30~50W</a>
				</div>
				<div class="cb-type-pricerange">
					<a href="#">50~100W</a>
				</div>
				<div class="cb-type-pricerange">
					<a href="#">100W以上</a>
				</div> -->
			</div>
		</div>
		<!--[if !IE]>车型_Begin<![endif]-->
		<div class="cb-type-area">
			<div class="cb-type-header ">
				<div class="cb-type-title">类型</div>
			</div>

			<div class="cb-type-body">
				<div class="cb-type-source ${product.ttyType.typeId==null?'ty_active':''}">
					<a href="${ctx }/frontJumpController/index/ttyType.typeId/index.html" onclick="selectProduct(this)" data-tid=0>全部</a>
				</div>
				 <c:forEach items="${typeList }" var="tlist">
			<div class="cb-type-source ${product.ttyType.typeId==tlist.typeId?'ty_active':''}">
					<a href="${ctx }/frontJumpController/index/ttyType.typeId/${tlist.typeId}.html">${tlist.typeName }</a>
				</div>
				</c:forEach>
					<!-- 
				<div class="cb-type-source">
					<a href="#">中东</a>
				</div>
				<div class="cb-type-source">
					<a href="#">中规</a>
				</div>
				<div class="cb-type-source">
					<a href="#">欧版</a>
				</div>
				<div class="cb-type-source">
					<a href="#">墨版</a>
				</div>
				<div class="cb-type-source">
					<a href="#">加版</a>
				</div> -->
			</div>
		</div>
	</div>

	<!--[if !IE]>平行进口车_Begin<![endif]-->
	<div class="cb-right-area">
		<div class="cb-recom">
			<font>| 平行进口车</font>原装体验
		</div>
		<div class="cb-car-body">
		 <c:forEach items="${productList }" var="prolist">
			<div class="cb-car-child">
				<div class="cb-car-box">
					<div class="cb-car-name cb-texthidden">${prolist.productName }</div>
					<div class="cb-img">
						<a href="${ctx }/ttyProductController/queryDetails/productId/${prolist.productId}.html"><img class="img-responsive"
							src="${ctx }/uploads/${prolist.productCoverPicture }"></a> <a
							href="${ctx }/ttyProductController/queryDetails/productId/${prolist.productId}.html" class="cb-btn">立即查看</a>
					</div>
					<div class="cb-car-price">
						￥:<font class="cb-special-price">${prolist.productFlatlyPrice }W</font>起<br
							class="visible-sm-block">
						<font class="cb-proposal-price">原价￥:${prolist.productMarketPrice }W</font>
					</div>
					<div class="cb-car-info">${prolist.productRemark }</div>
				</div>
			</div>
</c:forEach>
<!-- 			<div class="cb-car-child">
				<div class="cb-car-box">
					<div class="cb-car-name cb-texthidden">2017款路虎发现神行2.0THSE</div>
					<div class="cb-img">
						<a href="car-read.do"><img class="img-responsive"
							src="images/car/trending-car-img-4.jpg"></a> <a
							href="car-read.do" class="cb-btn">立即查看</a>
					</div>
					<div class="cb-car-price">
						￥:<font class="cb-special-price">36.3W</font>起<br
							class="visible-sm-block">
						<font class="cb-proposal-price">原价￥:46.8W</font>
					</div>
					<div class="cb-car-info">七座 19轮 脚感电尾门 无钥匙进入 倒车影像 氙灯 自动头灯</div>
				</div>
			</div>

			<div class="cb-car-child">
				<div class="cb-car-box">
					<div class="cb-car-name cb-texthidden">17款中东版奔驰GLE400</div>
					<div class="cb-img">
						<a href="car-read.do"><img class="img-responsive"
							src="images/car/trending-car-img-5.jpg"></a> <a
							href="car-read.do" class="cb-btn">立即查看</a>
					</div>
					<div class="cb-car-price">
						￥:<font class="cb-special-price">79.8W</font>起<br
							class="visible-sm-block">
						<font class="cb-proposal-price">原价￥:93.8W</font>
					</div>
					<div class="cb-car-info">电动折叠后视镜、胎压监测、ESP、自动泊车、全景天窗、360环影、真皮座椅</div>
				</div>
			</div>


			<div class="cb-car-child">
				<div class="cb-car-box">
					<div class="cb-car-name cb-texthidden">17款美规福特野马2.3T</div>
					<div class="cb-img">
						<a href="car-read.do"><img class="img-responsive"
							src="images/car/trending-car-img-7.jpg"></a> <a
							href="car-read.do" class="cb-btn">立即查看</a>
					</div>
					<div class="cb-car-price">
						￥:<font class="cb-special-price">32.98W</font>起<br
							class="visible-sm-block">
						<font class="cb-proposal-price">原价￥:39.8W</font>
					</div>
					<div class="cb-car-info">自动防炫目后视镜、胎压监测、R18轮、LED尾灯、后座椅折叠、无钥匙进入/启动</div>

				</div>
			</div>

			<div class="cb-car-child">
				<div class="cb-car-box">
					<div class="cb-car-name cb-texthidden">2016款Levante3.0T</div>
					<div class="cb-img">
						<a href="car-read.do"><img class="img-responsive"
							src="images/car/trending-car-img-8.jpg"></a> <a
							href="car-read.do" class="cb-btn">立即查看</a>
					</div>
					<div class="cb-car-price">
						￥:<font class="cb-special-price">97.3W</font>起<br
							class="visible-sm-block">
						<font class="cb-proposal-price">原价￥:103.8W</font>
					</div>
					<div class="cb-car-info">前后雷达、胎压监测、ESP、定速巡航、全景天窗、倒车影像、R19轮</div>
				</div>
			</div>

			<div class="cb-car-child">
				<div class="cb-car-box">
					<div class="cb-car-name cb-texthidden">17款中东版丰田陆巡4000</div>
					<div class="cb-img">
						<a href="car-read.do"><img class="img-responsive"
							src="images/car/trending-car-img-9.jpg"></a> <a
							href="car-read.do" class="cb-btn">立即查看</a>
					</div>
					<div class="cb-car-price">
						￥:<font class="cb-special-price">59.8W</font>起<br
							class="visible-sm-block">
						<font class="cb-proposal-price">原价￥86W</font>
					</div>
					<div class="cb-car-info">车身稳定控制系统、胎压监测、R17铁轮、冰箱、无钥匙进入/启动、织物座椅、电动座椅、中差速锁</div>
				</div>
			</div> -->
		</div>
	</div>
</div>

<!--[if !IE]>新推荐_Begin<![endif]-->
<div class="cb-recom-index">
	<div class="cb-recom">
		<font>| 新推荐</font>为您在第一时间带来最新体验
	</div>
	<div class="cb-recom-area">
	 <c:forEach items="${newProductList }" var="newprolist">
		<div class="cb-recommend-box">
			<div class="cb-car-img">
				<a href="${ctx }/ttyProductController/queryDetails/productId/${newprolist.productId}.html"><img class="img-responsive"
					src="${ctx }/uploads/${newprolist.productCoverPicture }"></a>
			</div>
			<div class="cb-car-info">
				<div class="cb-car-name cb-texthidden">${newprolist.productName }</div>
				<div class="cb-car-otherinfo">${newprolist.productRemark }</div>
				<div class="cb-car-price">
					￥:<font>${newprolist.productFlatlyPrice }W</font>起
				</div>
				<div class="cb-btn-line">
					<a class="cb-btn" href="${ctx }/ttyProductController/queryDetails/productId/${newprolist.productId}.html">立即查看</a>
				</div>
			</div>
		</div>
		</c:forEach>
<!-- 		<div class="cb-recommend-box">
			<div class="cb-car-img">
				<a href="car-read.do"><img class="img-responsive"
					src="images/car/trending-car-img-6.jpg"></a>
			</div>
			<div class="cb-car-info">
				<div class="cb-car-name cb-texthidden">玛莎拉蒂
					新款Quattroporte总裁轿车系列</div>
				<div class="cb-car-otherinfo">7速手自一体、279马力V6
					4.0L、全时四驱、最大扭矩397N.m、油箱140L</div>
				<div class="cb-car-price">
					￥:<font>120W起</font>
				</div>
				<div class="cb-btn-line">
					<a class="cb-btn" href="car-read.do">立即查看</a>
				</div>
			</div>
		</div>
		<div class="cb-recommend-box">
			<div class="cb-car-img">
				<a href="car-read.do"><img class="img-responsive"
					src="images/car/trending-car-img-3.jpg"></a>
			</div>
			<div class="cb-car-info">
				<div class="cb-car-name cb-texthidden">宝马 全新528Li</div>
				<div class="cb-car-otherinfo">7速手自一体、279马力V6
					2.5L、全时四驱、最大扭矩397N.m、油箱140L</div>
				<div class="cb-car-price">
					￥:<font>76.3W起</font>
				</div>
				<div class="cb-btn-line">
					<a class="cb-btn" href="car-read.do">立即查看</a>
				</div>
			</div>
		</div>
		<div class="cb-recommend-box">
			<div class="cb-car-img">
				<a href="car-read.do"><img class="img-responsive"
					src="images/car/trending-car-img-4.jpg"></a>
			</div>
			<div class="cb-car-info">
				<div class="cb-car-name cb-texthidden">路虎 新款揽胜运动版</div>
				<div class="cb-car-otherinfo">5速手自一体、360马力V8
					3.0L、全时四驱、最大扭矩397N.m、油箱180L</div>
				<div class="cb-car-price">
					￥:<font>200W起</font>
				</div>
				<div class="cb-btn-line">
					<a class="cb-btn" href="car-read.do">立即查看</a>
				</div>
			</div>
		</div> -->
	</div>
</div>

<!--[if !IE]>热销_Begin<![endif]-->
<div class="cb-recom-index">
	<div class="cb-recom">
		<font>| 热销</font>大数据告诉您市场人气车型，款款火爆
	</div>
	<div class="cb-recom-area">
	 <c:forEach items="${likeProductList }" var="likeprolist">
		<div class="cb-recommend-box">
			<div class="cb-car-img">
				<a href="${ctx }/ttyProductController/queryDetails/productId/${likeprolist.productId}.html"><img class="img-responsive"
					src="${ctx }/uploads/${likeprolist.productCoverPicture }"></a>
			</div>
			<div class="cb-car-info">
				<div class="cb-car-name cb-texthidden">${likeprolist.productName }</div>
				<div class="cb-car-otherinfo">${likeprolist.productRemark }</div>
				<div class="cb-car-price">
					￥:<font>${likeprolist.productFlatlyPrice }W</font>起
				</div>
				<div class="cb-btn-line">
					<a class="cb-btn" href="${ctx }/ttyProductController/queryDetails/productId/${likeprolist.productId}.html">立即查看</a>
				</div>
			</div>
		</div>
		</c:forEach>
<!-- 		<div class="cb-recommend-box">
			<div class="cb-car-img">
				<a href="car-read.do"><img class="img-responsive"
					src="images/car/trending-car-img-5.jpg"></a>
			</div>
			<div class="cb-car-info">
				<div class="cb-car-name cb-texthidden">玛莎拉蒂
					新款Quattroporte总裁轿车系列</div>
				<div class="cb-car-otherinfo">7速手自一体、279马力V6
					4.0L、全时四驱、最大扭矩397N.m、油箱140L</div>
				<div class="cb-car-price">
					￥:<font>120W起</font>
				</div>
				<div class="cb-btn-line">
					<a class="cb-btn" href="car-read.do">立即查看</a>
				</div>
			</div>
		</div>
		<div class="cb-recommend-box">
			<div class="cb-car-img">
				<a href="car-read.do"><img class="img-responsive"
					src="images/car/trending-car-img-7.jpg"></a>
			</div>
			<div class="cb-car-info">
				<div class="cb-car-name cb-texthidden">宝马 全新528Li</div>
				<div class="cb-car-otherinfo">7速手自一体、279马力V6
					2.5L、全时四驱、最大扭矩397N.m、油箱140L</div>
				<div class="cb-car-price">
					￥:<font>76.3W起</font>
				</div>
				<div class="cb-btn-line">
					<a class="cb-btn" href="car-read.do">立即查看</a>
				</div>
			</div>
		</div>
		<div class="cb-recommend-box">
			<div class="cb-car-img">
				<a href="car-read.do"><img class="img-responsive"
					src="images/car/trending-car-img-9.jpg"></a>
			</div>
			<div class="cb-car-info">
				<div class="cb-car-name cb-texthidden">路虎 新款揽胜运动版</div>
				<div class="cb-car-otherinfo">5速手自一体、360马力V8
					3.0L、全时四驱、最大扭矩397N.m、油箱180L</div>
				<div class="cb-car-price">
					￥:<font>200W起</font>
				</div>
				<div class="cb-btn-line">
					<a class="cb-btn" href="car-read.do">立即查看</a>
				</div>
			</div>
		</div> -->
	</div>
</div>

<!--[if !IE]>新闻_Begin<![endif]-->
<div class="cb-news-index">
	<div class="cb-recom">
		<font>| 天逸动态</font> 凭借众多资深行业精英人才和经验丰富的专业队伍，秉承“诚信为本，顾客至上”的经营理念
	</div>
	<div class="container-fulid">
		<div class="row">
			<div class="col-md-6 cb_news_like">
				<div class="cb-hot-img">
					<img class="img-responsive" src="${ctx }/uploads/${attach.fileUrl }">
				</div>

				<div class="cb-hot-news">
					<div class="cb-news-line">
						<div class="cb-news-title cb-texthidden">
							<span class="glyphicon glyphicon-menu-right"></span><a
								href="${ctx }/ttyNewsFrontController/queryDetails/newsId/${news.newsId }/news.html" class="ty_activity">${news.newsTitle }</a>
						</div>
						<div class="cb-news-date"><fmt:formatDate value="${news.createTime }" pattern="yyyy年MM月dd日" /></div>
					</div>
					<div class="cb-news-info">
						${news.newsIntroduce }
						</div>
				</div>
			</div>

			<div class="col-md-6 cb_news_dynamic">
			<c:forEach items="${newsLists }" var="newslist" varStatus="status">
				<div class="cb-newsline-box">
					<div class="cb-news-sort">0${status.index+1}</div>
					<div class="cb-news-line">
						<div class="cb-news-title cb-texthidden">
							<span class="glyphicon glyphicon-menu-right"></span>
							<a href="${ctx }/ttyNewsFrontController/queryDetails/newsId/${newslist.newsId }/news.html">${newslist.newsTitle }</a>
						</div>
						<div class="cb-news-date"><fmt:formatDate value="${newslist.createTime }" pattern="yyyy年MM月dd日" /></div>
					</div>
					<div class="cb-news-info">
						${newslist.newsIntroduce}11</div>
				</div>
				</c:forEach>
<!-- 
				<div class="cb-newsline-box">
					<div class="cb-news-sort">02</div>
					<div class="cb-news-line">
						<div class="cb-news-title cb-texthidden">
							<span class="glyphicon glyphicon-menu-right"></span><a
								href="news-read.do">天逸国际名车九月酬宾汇</a>
						</div>
						<div class="cb-news-date">2017-04-30</div>
					</div>
					<div class="cb-news-info">
						天逸国际名车九月酬宾汇火热引爆，订车即享超值礼，订购指定车型豪赠2万消费礼，爱车装饰，轮毂升级，超低折扣，即刻尊享！</div>
				</div>

				<div class="cb-newsline-box">
					<div class="cb-news-sort">03</div>
					<div class="cb-news-line">
						<div class="cb-news-title cb-texthidden">
							<span class="glyphicon glyphicon-menu-right"></span><a
								href="news-read.do">天逸自驾俱乐部青藏自驾游火热报名</a>
						</div>
						<div class="cb-news-date">2017-04-30</div>
					</div>
					<div class="cb-news-info">
						目前本店17款奔驰GLS450现车充足，颜色可选，17款奔驰GLS450不仅越野性能傲视群雄，公路行驶能力仍然是翘楚。</div>
				</div> -->
			</div>
		</div>
	</div>
</div>

<!--[if !IE]>综合_Begin<![endif]-->
<div class="cb-comprehensive-index">
	<!--[if !IE]>关于我们_Begin<![endif]-->
	<div class="cb-comprehensive-us">
		<div class="cb-recom">
			<font>| 关于我们</font>
		</div>
		<div class="cb-us-img">
			<img class="img-responsive" src="${ctx }/images/ty.jpg">
		</div>
		<div class="cb-us-index">
			天逸凯腾汽车贸易有限公司成立于2016年，坚持"以科学管理创效益，以优质服务求发展"的宗旨，实行低成本、低利润、高服务的营销策略来争取用户，占领市场，针对市场找差距，找对策，对用户实施"三满意"政策，即"质量满意、服务满意、价格满意"，以客户需求为导向，制定适应市场变化的营销方针满足不同客户群体的需求。
		</div>
	</div>
	<!--[if !IE]>平行进口车常见问题_Begin<![endif]-->
	<div class="cb-comprehensive-knowledge">
		<div class="cb-recom">
			<font>| 平行进口车常见问题</font>
		</div>
		<ul class="cb-knowledge-list">
		 <c:forEach items="${issueLists }" var="isslist">
			<li class="cb-knowledge-question"><span
				class="glyphicon glyphicon-question-sign"></span><a href="${ctx }/ttyNewsFrontController/queryDetails/newsId/${isslist.newsId }/news.html">${isslist.newsTitle }</a></li>
			<li class="cb-knowledge-answer">
				${isslist.newsIntroduce }
			</li>
			</c:forEach>
			<!-- <li class="cb-knowledge-question"><span
				class="glyphicon glyphicon-question-sign"></span><a href="#">平行进口汽车与中规车的区别</a></li>
			<li class="cb-knowledge-answer">
				各国按汽车安全法规，大致可分两大阵营：欧规阵营、北美阵营。欧洲、俄、中、日、韩，都属于欧规阵营。美、加、墨，都属于北美阵营。欧规阵营的车，基本都符合中国法规，通常汉化一下就能上牌。
			</li>
			<li class="cb-knowledge-question"><span
				class="glyphicon glyphicon-question-sign"></span><a href="#">平行进口车如何上牌照</a></li>
			<li class="cb-knowledge-answer">
				我国平行进口汽车超过七成来自天津，借助自贸试验区与平行进口车双重政策的叠加，天津自贸试验区内的平行进口汽车经销商积极打造从海外采购、口岸物流、到销售及售后服务全产业链服务平台。
			</li> -->
		</ul>
	</div>
</div>

<!-- 在线客服begin -->
<%@ include file="/WEB-INF/jsp/common/zxkf.jsp"%>
<!-- 在线客服end -->

<%@ include file="/WEB-INF/jsp/common/front_bottom.jsp"%>
</body>