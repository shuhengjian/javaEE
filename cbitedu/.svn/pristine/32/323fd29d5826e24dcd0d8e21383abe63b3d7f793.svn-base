<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<%@ include file="/WEB-INF/jsp/common/front_hread.jsp"%>

<img class="img-responsive" src="${ctx }/images/news/aboutus-page-header-img.jpg">
	
    <div class="cb-modular">
        <div class="container-fluid">
            <!--[if !IE]>tab_Begin<![endif]-->
            <div class="row">
                <div class="col-md-12">
                    <ul id="typeTab" class="nav nav-tabs cb-tabs">
                        <li class="${param.newsTypeCode == null || param.newsTypeCode == 1 ? 'active':''}"><a href="#typeTab1" data-toggle="tab">
                          企业动态</a></li>
                        <li class="${param.newsTypeCode == 2 ? 'active':''}"><a href="#typeTab1" data-toggle="tab">
                          行业动态</a></li>
                    </ul>
                </div>
            </div>
            <div class="tab-content">
                <!--[if !IE]>企业新闻列表_Begin<![endif]-->
                <div class="tab-pane active" id="typeTab1">
                    <div class="row news">
	                    <c:forEach items="${list1 }" var="news">
	                        <c:forEach items="${newsImg }" var="newsImg">
			                    <c:if test="${news.newsId == newsImg.pkid}">
			                        <div class="col-md-3 col-sm-4">
					                    <a href="${ctx }/ttyNewsFrontController/queryDetails/newsId/${news.newsId}/news.html">    
					                        <div class="cb-news-box">
					                            <div class="cb-img-box"><img class="img-responsive" src="${ctx }/uploads/${newsImg.fileUrl}"></div>
					                            <div class="cb-news-title cb-texthidden">${news.newsTitle }</div>
					                            <div class="cb-news-date cb-texthidden"><span class="glyphicon glyphicon-time"></span><fmt:formatDate value="${news.createTime }" pattern="yyyy年MM月dd日" /></div>
					                        </div>
					                    </a> 
			                		</div>
								</c:if> 	
	                		</c:forEach> 
	                	</c:forEach> 
                    </div>
                </div>
                
                <!--[if !IE]>分页_End<![endif]-->
                <div class="row">
	                <div class="col-sm-12"> 
	                    <ul class="pagination cb-pagination">
	                      <li><a href="${ctx }/ttyNewsFrontController/news/${page.currentPage==1?page.currentPage:page.currentPage-1}/${param.newsTypeCode == null? 1: param.newsTypeCode }/news.html">&laquo;</a></li>
	                    <c:if test="${page.beginPage  > 1 }">
		               	<li><a>...</a></li>
		               	</c:if>
	                    <c:forEach var="i" begin="${page.beginPage }" end="${page.endPage }">
	                      <li class="${i==page.currentPage?'active':''}"><a href="${ctx }/ttyNewsFrontController/news/${i}/${param.newsTypeCode == null? 1: param.newsTypeCode }/news.html">${i }</a></li>
	                   </c:forEach>
	                   <c:if test="${page.endPage < page.totalPages }">
	                 	<li><a>...</a></li>
	                   </c:if>   
	                      <li><a href="${ctx }/ttyNewsFrontController/news/${page.currentPage==page.totalPages?page.totalPages:page.currentPage+1}/${param.newsTypeCode == null? 1: param.newsTypeCode }/news.html">&raquo;</a></li>
	                    </ul>
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
    <script type="text/javascript">
    $(function () { 
    	 $('#typeTab').on('show.bs.tab', function (e) {
    		var active = $(e.target).parent().index();//获取当前active的在li中的位置，得到的是当前被选中的状态
    		//把newsTypeCode的状态放到事件中传过去，状态
    		var newsTypeCode = active + 1;
    		//然后
       	 	location = "${ctx }/ttyNewsFrontController/news/newsTypeCode/" + newsTypeCode +"/news.html";//地址对象
       	})
    });
   
	</script>
</body>