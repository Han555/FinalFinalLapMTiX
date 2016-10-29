<%-- 
    Document   : propertyMain
    Created on : Sep 20, 2016, 3:33:20 PM
    Author     : Student-ID
--%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<jsp:include page="header.jsp" />
<!-- Main Content -->
<div class="side-body">


    <div class="container"> 
        <div class="page-title">
            <span class="title">Property Management System</span>
        </div>

        <div class="row">
            <div class="col-sm-6 col-md-4">
                <div class="thumbnail">
                    <img src="img/seatallocation.jpg" alt="concert_main" style="width:300 px;height:300px">
                    <div class="caption">
                        <h3 style="text-align: center">Seating Plan Management</h3>
                        <p><c:url var="linkHref" value="/BackController?action=viewAllProperty" /><a href="${linkHref}" class="btn btn-primary" role="button">Enter</a> </p>
                    </div>
                </div>
            </div>
            <div class="col-sm-6 col-md-4">
                <div class="thumbnail">
                    <img src="img/resservationProperty.jpg" alt="theater_main" style="width:300 px;height:300px">
                    <div class="caption">
                        <h3 style="text-align: center">Reservation of properties</h3>
                        <p><c:url var="linkHref" value="/BackController?action=reservationMain" /><a href="${linkHref}" class="btn btn-primary" role="button">Enter</a> </p>
                    </div>
                </div>
            </div>

            <div class="col-sm-6 col-md-4">
                <div class="thumbnail">
                    <img src="img/schedule.jpg" alt="outdoor_main" style="width:300 px;height:300px">
                    <div class="caption">
                        <h3>Maintenance Schedule</h3>
                        <p><c:url var="linkHref" value="/BackController?action=maintenance" /><a href="${linkHref}" class="btn btn-primary" role="button">Enter</a> </p>
                    </div>
                </div>
            </div>
        </div>

        <div class="row">
            <div class="col-sm-6 col-md-4">
                <div class="thumbnail">
                    <img src="img/mansupply.jpg" alt="concert_main" style="width:300 px;height:300px">
                    <div class="caption">
                        <h3 style="text-align: center">Manpower</h3>
                        <p><c:url var="linkHref" value="/BackController?action=manpowerMain" /><a href="${linkHref}" class="btn btn-primary" role="button">Enter</a> </p>
                    </div>
                </div>
            </div>
            <div class="col-sm-6 col-md-4">
                <div class="thumbnail">
                    <img src="img/equipment.jpg" alt="theater_main" style="width:300 px;height:300px">
                    <div class="caption">
                        <h3 style="text-align: center">Equipment & Fixture</h3>
                        <p><c:url var="linkHref" value="/BackController?action=equipmentMain" /><a href="${linkHref}" class="btn btn-primary" role="button">Enter</a> </p>
                    </div>
                </div>
            </div>

            <div class="col-sm-6 col-md-4">
                <div class="thumbnail">
                    <img src="img/foodoutlet.jpg" alt="outdoor_main" style="width:300 px;height:300px">
                    <div class="caption">
                        <h3>Food Outlets</h3>
                        <p><c:url var="linkHref" value="/BackController?action=outletMain" /><a href="${linkHref}" class="btn btn-primary" role="button">Enter</a> </p>
                    </div>
                </div>
            </div>
        </div>
    </div> 



    <jsp:include page="footer.jsp" />
