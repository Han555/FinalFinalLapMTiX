<%-- 
    Document   : viewAllProperty
    Created on : Sep 20, 2016, 4:16:08 PM
    Author     : Student-ID
--%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<jsp:include page="header.jsp" />
<!-- Main Content -->
<div class="side-body">


    <div class="container"> 
        <div class="page-title">
            <span class="title">Display All Properties</span>
        </div>

        <div class="row">
            <div class="col-sm-6 col-md-4">
                <div class="thumbnail">
                    <img src="img/property/concert_main.jpg" alt="concert_main" style="width:300 px;height:300px">
                    <div class="caption">
                        <h3 style="text-align: center">Merlion Concert Hall</h3>
                        <p style="text-align: justify">The Concert Hall is Merlion's crown jewel, which can seat <span style="font-weight:bold;">2,000</span> spectators. It is one of only five such halls in the world with similar state-of-the-art features, which include reverberation chambers and an acoustic canopy that adapts the hall for different musical performances, producing optimum sound at every concert. </p>
                        <p><c:url var="linkHref" value="/BackController?action=concertHallLayout" /><a href="${linkHref}" class="btn btn-primary" role="button">View Seating Plan</a> </p>
                    </div>
                </div>
            </div>
            <div class="col-sm-6 col-md-4">
                <div class="thumbnail">
                    <img src="img/property/theater_main.jpg" alt="theater_main" style="width:300 px;height:300px">
                    <div class="caption">
                        <h3 style="text-align: center">Merlion Star Theater</h3>
                        <p style="text-align: justify">The Merlion Star Theater is fitted with high-end audio, video and production lighting systems for an exceptional audio-visual experience with a capacity of <span style="font-weight:bold;">2,000</span> spectators. It heightens your enjoyment of a wide range of amplified music and speech events, as well as large-scale musical theater and <span style="font-weight:bold;">dance performances</span>.</p>
                        <p><c:url var="linkHref" value="/BackController?action=theaterLayout" /><a href="${linkHref}" class="btn btn-primary" role="button">View Seating Plan</a> </p>
                    </div>
                </div>
            </div>

            <div class="col-sm-6 col-md-4">
                <div class="thumbnail">
                    <img src="img/property/outdoor_main.jpg" alt="outdoor_main" style="width:300 px;height:300px">
                    <div class="caption">
                        <h3>Merlion Outdoor Stadium</h3>
                        <p>Merlion Outdoor Stadium is blabla</p>
                        <p><a href="img/property/outdoor_main.jpg" class="btn btn-primary" role="button">Button</a> <a href="#" class="btn btn-default" role="button">Button</a></p>
                    </div>
                </div>
            </div>
        </div>
    </div> 

    <jsp:include page="footer.jsp" />
