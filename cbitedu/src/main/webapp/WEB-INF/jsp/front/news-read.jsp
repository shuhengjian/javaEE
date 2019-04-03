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
                      <li><a href="${ctx }/ttyNewsFrontController/news.html">新闻中心</a></li>
                      <li class="active">企业动态</li>
                    </ol>
                </div>
            </div>
        </div>
    </div>
    
    <div id="cont">        
        <div class="container">
            <div class="row">
                <div class="col-sm-3 cb-img-adapt">
                    <!-- <img src="images/news/new-2.jpg"> -->
                   		 <img src="${ctx }/uploads/${newsImg.fileUrl}">
                </div>
                <div class="col-sm-9 cb-img-adapt">
                    <ul class="cb-newsread-box">
                        <li class="cb-title">${news.newsTitle }</li>
                        <li class="cb-editTime">
                            <font><b>发布者</b>：${newsName.loginName }</font><font><b>发布时间</b>：<fmt:formatDate value="${news.createTime }" pattern="yyyy年MM月dd日" /></font></li>
                        <li class="cb-content">
                            <p>${news.newsContent }<a href="#">http://www.shilongpack.com/product.asp</a></p>
                        </li>
                        <li class="cb-context">
                          <c:if test="${ newsPrev !=null}"><b >上一篇：</b> <a href="${ctx }/ttyNewsFrontController/queryDetails/newsId/${newsPrev.newsId }/news.html">${newsPrev.newsTitle }</a><br></c:if> 
                          <c:if test="${ newsNext !=null}"> <b>下一篇：</b> <a href="${ctx }/ttyNewsFrontController/queryDetails/newsId/${newsNext.newsId }/news.html">${newsNext.newsTitle }</a></c:if>
                        </li>                    
                    </ul>
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