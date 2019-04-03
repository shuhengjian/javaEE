<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<%@ include file="/WEB-INF/jsp/common/front_hread.jsp"%>
<script type="text/javascript">
    window.onload = autologin;
	window.onresize = autologin;
  
    /*登录界面自动宽高*/
function autologin(){
	var browserH = document.documentElement.clientHeight;
	
	var hostbodyH = document.getElementById("aboutus");
	hostbodyH.style.minHeight = browserH - 100 + "px";	
}
</script>
    <div id="aboutus">
        <div class="cb-modular11">
            <div class="cb-about-name">湖南天逸凯腾汽车贸易有限公司</div>
            <div id="c-about" class="carousel slide" data-ride="carousel">
                <!-- 轮播（Carousel）指标 -->
                <div class="carousel-indicators">
                    <div data-target="#c-about" data-slide-to="0" class="active">企业简介</div> 
                    <div data-target="#c-about" data-slide-to="1">服务宗旨</div>
                    <div data-target="#c-about" data-slide-to="2">核心价值观</div> 
                    <div data-target="#c-about" data-slide-to="3">天逸愿景</div>
                </div>
                
                <!-- 轮播（Carousel）项目 -->
                <div class="carousel-inner">
                    <div class="item active">
                        <div class="c-infoBox">
                            <div class="c-abUs">
                                <p><font class="c-cpIdea">企业简介 / Introduce</font></p>
                                <p>公司成立于2016年，在公司领导下的团队团结一致，形成了强大的凝聚力和战斗力。</p>
    
                                <p>公司坚持"以科学管理创效益，以优质服务求发展"的宗旨，实行低成本、低利润、高服务的营销策略来争取用户，占领市场，针对市场找差距，找对策，对用户实施"三满意"政策，即"质量满意、服务满意、价格满意"，以客户需求为导向，制定适应市场变化的营销方针满足不同客户群体的需求，积极挖掘潜在用户。</p>
    
                                <p>公司秉承"以人为本，诚信经营"的企业理念，始终把"客户满意度"作为工作的核心，以诚信、合法、公正、平等互利的原则进行经营，注重公司的整体利益和长远发展，以最快捷、最专业的服务来满足客户全方位的需求，打造广大顾客心目中最认可的汽车销售公司。</p>
    
                                <p>湖南天逸凯腾汽车贸易有限公司，本着"信守承诺，感动客户，结果第一，永不言败"的企业精神，再跨发展宏图，努力把公司建成专业化、集团化。</p>
                            </div>
                        </div>
                    </div>
                    <div class="item">
                        <div class="c-infoBox">
                            <div class="c-abUs">
                                <p><font class="c-cpTeam">服务宗旨 / Purpose</font></p>
                                <p>我们不生产车，我们只是世界名车的搬运工</p>
                                <p>以诚为本、服务至上，秉承形象与品牌</p>
                            </div>
                        </div>
                    </div>
                    <div class="item">
                        <div class="c-infoBox">
                            <div class="c-abUs">
                                <p><font class="c-cpTeam">核心价值观 / Idea</font></p>
                                <p>精准-标准化的流程为客户提供最满意的服务</p>
                                <p>诚信-正直诚信的经营我们的品牌资产</p>
                                <p>团队-把供应商看作我们精英团队的一份子</p>
                                <p>创新-专注于客户和公司有影响力的创新</p>
                            </div>
                        </div>
                    </div>
                    <div class="item">
                        <div class="c-infoBox">
                            <div class="c-abUs">
                                <p><font class="c-cpTeam">天逸愿景 / Vision</font></p>
                                <p>致力于成为中国汽车销售服务连锁行业的领跑者</p>
                            </div>
                        </div>
                    </div>
                </div>
                <!-- 轮播（Carousel）导航 -->
            </div>
        </div>
    </div>
    
    <!-- 在线客服begin -->
	<%@ include file="/WEB-INF/jsp/common/zxkf.jsp"%>
	<!-- 在线客服end -->
    
   <%@ include file="/WEB-INF/jsp/common/front_bottom.jsp"%>
</body>