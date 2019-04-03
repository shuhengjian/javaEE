<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<%@ include file="/WEB-INF/jsp/common/front_hread.jsp"%>
    
    <!--[if !IE]>面包屑导航_Begin<![endif]-->
    <div style="background-color:#efefef">
        <div class="container">    
            <div class="row">
                <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
                    <ol class="breadcrumb cb-breadcrumb">
                      <li><a href="${ctx}/index.html">首页</a></li>
                      <li><a href="${ctx }/ttyProductController/list/${code==1?1:2 }/parallelCar.html">${code ==1?'平行进口':'中规车' }</a></li>
                      <li class="active">${product.productName }</li>
                    </ol>
                </div>
            </div>
        </div>
    </div>
    
    <div id="cont"> 
        <div class="container">
            <div class="row">
                <!--[if !IE]>车辆图片_Begin<![endif]-->
                <div class="col-sm-7">
                    <div id="carCarousel" class="carousel slide">
                        <!-- 轮播（Carousel）项目 -->
                        <div class="carousel-inner">
                        <c:forEach items="${list }" var="list" varStatus="li">
                            <div class="item ${li.first?'active':'' }">
                                <img src="${ctx }/uploads/${list.fileUrl }" alt="First slide">
                            </div>
                        </c:forEach>
                            <!-- <div class="item">
                                <img src="images/car/discovery/d-2.jpg" alt="Second slide">
                            </div>
                            <div class="item">
                                <img src="images/car/discovery/d-3.jpg" alt="Third slide">
                            </div>
                            <div class="item">
                                <img src="images/car/discovery/d-4.jpg" alt="Third slide">
                            </div> -->
                        </div>
                        
                        <!-- 轮播（Carousel）指标 -->
                        <ol class="carousel-indicators">
                        <c:forEach items="${list }" var="list" varStatus="li">
                            <li data-target="#carCarousel" data-slide-to="${li.index }" 
                                class="${li.first?'active':'' }">
                                <img src="${ctx }/uploads/${list.fileUrl }">
                            </li>
                        </c:forEach>
                            <!-- <li data-target="#carCarousel" data-slide-to="1">
                                <img src="images/car/discovery/d-2.jpg">
                            </li>
                            <li data-target="#carCarousel" data-slide-to="2">
                                <img src="images/car/discovery/d-3.jpg">
                            </li>
                            <li data-target="#carCarousel" data-slide-to="3">
                                <img src="images/car/discovery/d-4.jpg">
                            </li> -->
                        </ol>
                    </div> 
                </div>
                <!--[if !IE]>车辆简介_Begin<![endif]-->
                <div class="col-sm-5">
                    <ul class="cb-car-introduce">
                        <li class="cb-car-name">${product.productName }</li>
                        <li class="cb-car-price">
                            一口价￥:<font class="cb-special-price">${product.productFlatlyPrice }</font>W<font class="cb-proposal-price"><i>市场价￥:${product.productMarketPrice }W</i></font> 
                        </li>
                        <li class="cb-car-other"><b>规格</b>：<i>${product.ttyType.typeName }</i></li>
                        <li class="cb-car-other"><b>颜色</b>：<i>${product.productColor }</i></li>
                        <li class="cb-car-other"><b>保障</b>：<i>全国联保</i></li>
                        <li class="cb-car-other"><b>商家</b>：<i>${product.tsysOrg.orgName }</i></li>
                        <li class="cb-car-other"><b>联系电话</b>：<i>${product.tsysOrg.phone }</i></li>
                        <li class="cb-car-other"><b>发布时间</b>：<i><fmt:formatDate value="${product.productPubTime }" pattern="yyyy年MM月dd日" /></i></li>
                        <li class="cb-car-other hidden"><a class="cb-btn" href="#">我要购车</a></li>
                    </ul>
                </div>
                <div class="col-sm-12"><div class="cb-car-driver"></div></div>
            </div>
        
            <div class="row">
                <!--[if !IE]>购车事项_Begin<![endif]-->
                <div class="col-md-9 cb-img-adapt">
                    <div class="cb-recom hidden"><font><span class="glyphicon glyphicon-cog"></span>车型配置</font></div>
                    <table class="table table-bordered table-responsive hidden">
                        <thead>
                            <tr>
                                <th colspan="2">2017款路虎发现神行HSE版（指导价46.8万）</th>
                            </tr>
                        </thead>
                        
                        <tbody>
                            <tr>
                              <td><b>长*宽*高（mm）</b></td>
                              <td>4599*1894*1724</td>
                            </tr>
                            <tr>
                              <td><b>变速箱</b></td>
                              <td>9速手自一体</td>
                            </tr>
                            <tr>
                              <td><b>发动机</b></td>
                              <td>2.0T 241马力  L4</td>
                            </tr>
                            <tr>
                              <td><b>整车质量（kg）</b></td>
                              <td>1995</td>
                            </tr>
                            <tr>
                              <td><b>工信部综合油耗（L/100km）</b></td>
                              <td>8.5</td>
                            </tr>
                            <tr>
                              <td><b>油箱容积(L)</b></td>
                              <td>70</td>
                            </tr>
                            <tr>
                              <td><b>最大马力（Ps）</b></td>
                              <td>241</td>
                            </tr>
                            <tr>
                              <td><b>最大扭矩（N.m）</b></td>
                              <td>340</td>
                            </tr>
                            <tr>
                              <td><b>燃油标号</b></td>
                              <td>95号汽油</td>
                            </tr>
                            <tr>
                              <td><b>环保标准</b></td>
                              <td>欧五</td>
                            </tr>
                            <tr>
                              <td><b>驱动模式</b></td>
                              <td>前置四驱</td>
                            </tr>
                            <tr>
                              <td><b>车辆结构</b></td>
                              <td>承载式</td>
                            </tr>
                            <tr>
                              <td><b>级别</b></td>
                              <td>中型SUV</td>
                            </tr>
                            <tr>
                              <td><b>重点配置</b></td>
                              <td>前后雷达、胎压监测、ESP、定速巡航、全景天窗、倒车影像、R19轮、无钥匙进入/启动、真皮座椅、记忆套件、七座、前座椅加热、GPS导航、氙灯、自动头灯、后视镜防炫目、感应雨刮、花粉过滤、脚感电尾</td>
                            </tr>
                        </tbody>
                    </table>
                    
                    <div>${product.productDetails }</div>
                
                    <div class="cb-recom"><font><span class="glyphicon glyphicon-check"></span>购车流程</font></div>
                    <ul class="cb-buy-flow">
                        <li><span>1</span>选定您中意的车型，点击"我要购车"</li>
                        <li><span>2</span>天逸购车顾问与您确认，具体购车事宜</li>
                        <li><span>3</span>确认车辆信息及提车方式，"自提"或"物流发车"，物流发车需自付物流费用</li>
                        <li><span>4</span>预约提车的时间及地点</li>
                        <li><span>5</span>4s店或门店提车，结清尾款</li>
                    </ul>
                    
                    <div class="cb-recom"><font><span class="glyphicon glyphicon-exclamation-sign"></span>注意事项</font></div>
                    <ul class="cb-buy-attention">
                        <li>1、<b>牌照办理：</b>门店有偿代办或自行办理。<br>（于户口所在地缴纳车辆购置税后再到对应所在地车管所上牌即可。）</li>
                        <li>2、<b>售后服务：</b>所购车辆是全国联保的，售后、保养、维修等服务均可到当地4S店。</li>
                        <li>3、<b>平行进口：</b>在本店购买质保险，享受全国含4s店三年六万公里（先到为准）整车质保。</li>
                    </ul>
                    
                    <div class="cb-recom"><font><span class="glyphicon glyphicon-heart"></span>温馨提示</font></div>
                    <ul class="cb-buy-attention">
                        <li>1、本店所有车型全国销售（区域限制另行标注），请放心选购。</li>
                        <li>2、本店所有车型支持车源地自提或者门店自提（其中车源地到门店物流费用自理）。</li>
                        <li>3、所有车型支持全款购车或分期按揭，具体需求可门店或来电咨询。</li>
                    </ul>                
                </div>
                
                <div class="col-md-3">
                    <div class="cb-recom"><font>| 看过的人还看</font></div>
                    <div class="row">
                    	<c:forEach items="${newProductList }" var="list">
                        <div class="col-md-12 col-sm-4">
                            <div class="cb-car-box">
                              <div class="cb-car-name cb-texthidden">${list.productName }</div>
                              <div class="cb-img">
                                  <a href="${ctx }/ttyProductController/queryDetails/productId/${list.productId}.html"><img class="img-responsive" src="${ctx }/uploads/${list.productCoverPicture }"></a>
                                  <a href="${ctx }/ttyProductController/queryDetails/productId/${list.productId}.html" class="cb-btn">立即查看</a>
                              </div>
                              <div class="cb-car-price">￥:<font class="cb-special-price">${list.productFlatlyPrice }W</font>起<br class="visible-sm-block"><font class="cb-proposal-price">原价￥:${list.productMarketPrice }W</font></div>
                              <div class="cb-car-info">${list.productRemark }</div>
                            </div>
                        </div>
                        </c:forEach>
                        <!-- <div class="col-md-12 col-sm-4">
                            <div class="cb-car-box">
                                <div class="cb-car-name cb-texthidden">2017款路虎发现神行2.0THSE</div>
                                <div class="cb-img">
                                    <a href="car-read.html"><img class="img-responsive" src="images/car/trending-car-img-4.jpg"></a>
                                    <a href="car-read.html" class="cb-btn">立即查看</a>
                                </div>
                                <div class="cb-car-price">￥:<font class="cb-special-price">36.3W</font>起<br class="visible-sm-block"><font class="cb-proposal-price">原价￥:46.8W</font></div>
                                <div class="cb-car-info">七座 19轮 脚感电尾门 无钥匙进入 倒车影像 氙灯 自动头灯</div>
                            </div>
                        </div>
                        
                        <div class="col-md-12 col-sm-4">
                            <div class="cb-car-box">
                                <div class="cb-car-name cb-texthidden">17款中东版奔驰GLE400</div>
                                <div class="cb-img">
                                    <a href="car-read.html"><img class="img-responsive" src="images/car/trending-car-img-5.jpg"></a>
                                    <a href="car-read.html" class="cb-btn">立即查看</a>
                                </div>
                                <div class="cb-car-price">￥:<font class="cb-special-price">79.8W</font>起<br class="visible-sm-block"><font class="cb-proposal-price">原价￥:93.8W</font></div>
                                <div class="cb-car-info">电动折叠后视镜、胎压监测、ESP、自动泊车、全景天窗、360环影、真皮座椅</div>
                            </div>
                        </div> -->
                    </div>
                    
                    <div class="cb-recom"><font>| 热门推荐</font></div>
                    <div class="row">
                        <div class="col-md-12 col-sm-4">
                        <c:forEach items="${likeProductList }" var="t">
                            <div class="cb-car-box">
                                <div class="cb-car-name cb-texthidden">${t.productName }</div>
                                <div class="cb-img">
                                    <a href="${ctx }/ttyProductController/queryDetails/productId/${t.productId}.html"><img class="img-responsive" src="${ctx }/uploads/${t.productCoverPicture }"></a>
                                    <a href="${ctx }/ttyProductController/queryDetails/productId/${t.productId}.html" class="cb-btn">立即查看</a>
                                </div>
                                <div class="cb-car-price">￥:<font class="cb-special-price">${t.productFlatlyPrice }W</font>起<br class="visible-sm-block"><font class="cb-proposal-price">原价￥:${t.productMarketPrice }W</font></div>
                                <div class="cb-car-info">${t.productRemark }</div>
                                
                            </div>
                        </c:forEach>
                        </div>
                        
                       <!--  <div class="col-md-12 col-sm-4">
                            <div class="cb-car-box">
                                <div class="cb-car-name cb-texthidden">2016款Levante3.0T</div>
                                <div class="cb-img">
                                    <a href="car-read.html"><img class="img-responsive" src="images/car/trending-car-img-8.jpg"></a>
                                    <a href="car-read.html" class="cb-btn">立即查看</a>
                                </div>
                                <div class="cb-car-price">￥:<font class="cb-special-price">97.3W</font>起<br class="visible-sm-block"><font class="cb-proposal-price">原价￥:103.8W</font></div>
                                <div class="cb-car-info">前后雷达、胎压监测、ESP、定速巡航、全景天窗、倒车影像、R19轮</div>
                            </div>
                        </div>
                        
                        <div class="col-md-12 col-sm-4">
                            <div class="cb-car-box">
                                <div class="cb-car-name cb-texthidden">17款中东版丰田陆巡4000</div>
                                <div class="cb-img">
                                    <a href="car-read.html"><img class="img-responsive" src="images/car/trending-car-img-9.jpg"></a>
                                    <a href="car-read.html" class="cb-btn">立即查看</a>
                                </div>
                                <div class="cb-car-price">￥:<font class="cb-special-price">59.8W</font>起<br class="visible-sm-block"><font class="cb-proposal-price">原价￥86W</font></div>
                                <div class="cb-car-info">车身稳定控制系统、胎压监测、R17铁轮、冰箱、无钥匙进入/启动、织物座椅、电动座椅、中差速锁</div>
                            </div>
                        </div> -->
                    </div>
                </div>
            </div>
        </div>
    </div>
    
    <!-- 在线客服begin -->
	<%@ include file="/WEB-INF/jsp/common/zxkf.jsp"%>
	<!-- 在线客服end -->
    
    <!--[if !IE]>页脚_Begin<![endif]-->
	<%@ include file="/WEB-INF/jsp/common/front_bottom.jsp"%>
</body>