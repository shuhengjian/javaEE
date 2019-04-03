<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<%@ include file="/WEB-INF/jsp/common/front_hread.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    <img class="img-responsive" src="images/car/listing-page-header-img.jpg">

    <div class="cb-modular">
        <!--[if !IE]>分类_Begin<![endif]-->
        <div class="container-fluid" style="margin:25px 0;">
            <div class="row">
                <div class="col-sm-12">
                    <!--[if !IE]>品牌_Begin<![endif]-->
                    <ul class="nav nav-pills cb-nav-brand">
                        <li class="active logo">
                            <a href="${ctx }/frontRuleController.do?method=rule" title="全部品牌">
                                <img class="img-responsive" src="images/sign/sign-all.jpg">
                                <div class="cb-brand-name">全部品牌</div>
                            </a>
                        </li>
                        <c:forEach items="${logo }" var="logo">
                         <li>
                            <a href="${ctx }/frontRuleController.do?method=rule&pkid=${logo.pkid}" title="${logo.fileName }">
                                <img class="img-responsive" src="${ctx }/uploads/${logo.fileUrl }">
                                <div class="cb-brand-name">${logo.fileName }</div>
                            </a>
                        </li>
                        </c:forEach>
                    </ul>
                    <!--[if !IE]>价格_Begin<![endif]-->
                    <ul class="nav nav-pills cb-nav-brand">
                        <li class="active price"><a href="${ctx}/frontRuleController.do?method=rule">全部价格</a></li>
                        <c:forEach items="${price}" var = "price">
                        <c:if test="${price.priceMax == null }">
                         	<li class=""><a href="${ctx }/frontRuleController.do?method=rule&ttyPrice.priceId=${price.priceId}" ><fmt:formatNumber value="${price.priceMin }" type="number"/>W以上</a></li>
                        </c:if>
                        <c:if test="${price.priceMax != null }">
                        <li  class="" ><a href="${ctx }/frontRuleController.do?method=rule&priceId=${price.priceId}"><fmt:formatNumber value="${price.priceMin }" type="number"/>~<fmt:formatNumber value="${price.priceMax }" type="number"/>W</a></li>
                     	</c:if>
                     </c:forEach>
                    </ul>
                    <!--[if !IE]>类型_Begin<![endif]-->
                    <ul class="nav nav-pills cb-nav-brand">
                        <li class="active type"><a href="${ctx }/frontRuleController.do?method=rule">全部类型</a></li>
                        <c:forEach items="${type }" var="type">
                        	<li><a href="${ctx }/frontRuleController.do?method=rule&typeId=${type.typeId}">${type.typeName }</a></li>
                        </c:forEach>
                    </ul>
                </div>
            </div>
        </div>
        
        <!--[if !IE]>列表_Begin<![endif]-->
        <div class="container-fluid">
            <div class="row all">
            <c:forEach items="${list }" var="list">
              <div class="col-md-3 col-sm-4">
                    <div class="cb-car-box">
                        <div class="cb-car-name cb-texthidden">${list.productName}</div>
                        <div class="cb-img">
                            <a href="${ctx }/ttyProductController.do?method=queryDetails&productId=${list.productId}">
                            <img class="img-responsive" src="${ctx }/uploads/${list.productCoverPicture}"></a>
                            <a href="${ctx }/ttyProductController.do?method=queryDetails&productId=${list.productId}" class="cb-btn">立即查看</a>
                        </div>
                        <div class="cb-car-price">￥:<font class="cb-special-price">${list.productFlatlyPrice}W</font>起<br class="visible-md-block visible-sm-block"><font class="cb-proposal-price">原价￥:${list.productMarketPrice}W</font></div>
                        <div class="cb-car-info">${list.productRemark }</div>
                    </div>
                </div>
                </c:forEach>
            </div>
            
            <!--[if !IE]>分页_End<![endif]-->
            <div class="row">
                <div class="col-sm-12"> 
                    <ul class="pagination cb-pagination">
                      <li><a href="${ctx }/frontRuleController.do?method=rule&currentPage=${page.currentPage==1?page.currentPage:page.currentPage-1}">&laquo;</a></li>
                     	<c:if test="${page.pageStartRow > 1 }">
                      <li><a href="#">...</a></li>
                      </c:if>
                      <c:forEach var="i" begin="1" end="${page.totalPages }">
                      <li class="${i == page.currentPage?'active' : '' }"><a href="${ctx }/frontRuleController.do?method=rule&currentPage=${i}">${i}</a></li>
                      </c:forEach>
                      <c:if test="${page.pageEndRow < page.totalPages}">
                      <li><a href="#">...</a></li>
                      </c:if>
                     <!--  <li><a href="#">4</a></li>
                      <li><a href="#">5</a></li> -->
                      <li><a href="${ctx }/frontRuleController.do?method=rule&currentPage=${page.currentPage==page.totalPages?page.totalPages:page.currentPage+1}">&raquo;</a></li>
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