<%-- 
    Document   : reservationSearchResult
    Created on : Sep 19, 2016, 5:34:05 PM
    Author     : catherinexiong
--%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<jsp:useBean id="pList" class="java.util.List<entity.PropertyEntity>" scope="request"/>
<jsp:useBean id="pRList" class="java.util.List<entity.PropertyEntity>" scope="request"/>
<jsp:include page="header.jsp" />


<style type="text/css">
    .product-box {

        display:inline-block;
        position:relative;
    }
    .urun-text {
        position: absolute;
        background-color: rgba(15, 15, 15, 0.93);
        color: #fff;
        bottom: 0px;
        text-align: left;

        padding: 20px 15px 20px 15px;
        width:100%;
    }
    .urun-title {
        position: absolute;
        bottom: 250px;
        text-align: left;
        z-index: 15;
        padding-top: 5px;
        padding-bottom: 5px;
        color: #fff;
        padding-left: 20px;
        text-shadow: 0 1px 2px rgba(0, 0, 0, .6);
        background-color: rgba(194, 0, 0, 0.93);
        width: 100%;
    }
    .click-arrow-right {
        position: absolute;
        z-index: 16;
        color: #fff;
        font-size: 20px;
        bottom: 10px;
        right: 33px;
    }

    .urun-title {
        width:100%;

    }

</style>


<div class="container-fluid">
    <div class="side-body">
        <div class="page-title">
            <span class="title">Avaliable Venues with Recommendation</span>

        </div>

        <div class="row">
            <c:forEach items="${pList}" var="pList">
                <c:choose>
                    <c:when test="${pList.propertyName.equals('Merlion Concert Hall')}">
                        <div class="col-md-4">
                            <div class="product-box">
                                <img src="img/property/concert_main.jpg" alt="concert" class="align-center img-responsive" style="width:300 px;height:300px">
                                <c:forEach items="${pRList}" var="pRList">
                                    <c:choose>
                                        <c:when test="${pRList.propertyName.equals('Merlion Concert Hall')}">
                                            <div class="urun-title"><span class="glyphicon glyphicon-star"></span> Recommended</div>
                                        </c:when>
                                    </c:choose>
                                </c:forEach>
                                <div class="urun-text">Merlion Concert Hall<br>With reverberation chambers and an acoustic canopy<br>More cater for musical performances </div>
                                <div class="click-arrow-right"><c:url var="linkHref" value="/BackController?action=subConcertHallSelected" /><a href="${linkHref}"><span class="glyphicon glyphicon-arrow-right"></span></a>
                                </div>
                            </div>
                        </div>

                    </c:when>    
                    <c:when test="${pList.propertyName.equals('Merlion Star Theater')}">
                        <div class="col-md-4">
                            <div class="product-box">
                                <img src="img/property/theater_main.jpg" alt="theater" class="align-center img-responsive" style="width:300 px;height:300px" >
                                <c:forEach items="${pRList}" var="pRList">
                                    <c:choose>
                                        <c:when test="${pRList.propertyName.equals('Merlion Star Theater')}">
                                            <div class="urun-title"><span class="glyphicon glyphicon-star"></span> Recommended</div>
                                        </c:when>
                                    </c:choose>
                                </c:forEach>

                                <div class="urun-text">Merlion Star Theater <br>With high-end audio, video and production lighting systems for an exceptional audio-visual experience</div>
                                <div class="click-arrow-right"><c:url var="linkHref" value="/BackController?action=subTheaterSelected" /><a href="${linkHref}"><span class="glyphicon glyphicon-arrow-right"></span></a>
                                </div>
                            </div>
                        </div>
                    </c:when>
                </c:choose>
            </c:forEach>



        </div>
        <% session.setAttribute("daterange", request.getAttribute("daterange"));
        session.setAttribute("eventid", request.getAttribute("eventid"));
        session.setAttribute("type", request.getAttribute("type"));%>
    </div>
</div>

<jsp:include page="footer.jsp" />
