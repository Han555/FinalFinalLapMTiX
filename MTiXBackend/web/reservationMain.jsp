<%-- 
    Document   : ReservationMain
    Created on : Sep 22, 2016, 10:55:09 AM
    Author     : catherinexiong
--%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<!doctype html>
<jsp:include page="header.jsp" />
<!-- Main Content -->
<!-- Main Content -->
<div class="container-fluid">
    <div class="side-body padding-top">
        <div class="row" >
            <div class="col-sm-6">
                <!--    <div class="col-lg-3 col-md-6 col-sm-6 col-xs-12"> -->
                <c:url var="linkHref" value="/BackController?action=reservationSearch" />
                <a href="${linkHref}">
                    <div class="card green summary-inline">
                        <div class="card-body">
                            <i class="icon fa fa-share-alt fa-4x"></i>
                            <div class="content">
                                <div class="title" style="font-size:25px;">Create Event <span style="color:#000000"><u>without</u> </span>Sub Events</div>
                                <div class ="description" style="color:#000000;">Click here if the event doesn't have any sub events</div>
                            </div>
                            <div class="clear-both"></div>
                        </div>
                    </div>
                </a>
                <!--    </div> -->
            </div>
            <div class="col-sm-6">
                <!--   <div class="col-lg-3 col-md-6 col-sm-6 col-xs-12"> -->
                <c:url var="linkHref" value="/BackController?action=createEventWithSub" />
                <a href="${linkHref}">
                    <div class="card green summary-inline">
                        <div class="card-body">
                            <i class="icon fa fa-share-alt fa-4x"></i>
                            <div class="content">
                                <div class="title" style="font-size:25px;">Create Event<span style="color:#000000"> <u>with</u></span> Sub Events</div>
                                <div class ="description" style="color:#000000;">Click here if the event has any sub events</div>
                            </div>
                            <div class="clear-both"></div>
                        </div>
                    </div>
                </a>
                <!--    </div> -->
            </div>
        </div>
<!--        <div class="row">
            <div class="col-sm-6">
                   <div class="col-lg-3 col-md-6 col-sm-6 col-xs-12"> 
                <c:url var="linkHref" value="/BackController?action=addExtraEquipment" />
                <a href="${linkHref}">
                    <div class="card blue summary-inline">
                        <div class="card-body">
                            <i class="icon fa fa-share-alt fa-4x"></i>
                            <div class="content">
                                <div class="title" style="font-size:25px;">Add <span style="color:#000000"> <u>Extra </u></span>Equipments to Events</div>
                                <div class ="description" style="color:#000000;">Click here if the event needs to add extra equipments</div>
                            </div>
                            <div class="clear-both"></div>
                        </div>
                    </div>
                </a>
                    </div> 
            </div>

<
            <div class="col-sm-6">
                   <div class="col-lg-3 col-md-6 col-sm-6 col-xs-12"> 
                <c:url var="linkHref" value="/BackController?action=editReservation" />
                <a href="${linkHref}">
                    <div class="card blue summary-inline">
                        <div class="card-body">
                            <i class="icon fa fa-share-alt fa-4x"></i>
                            <div class="content">
                                <div class="title" style="font-size:25px;">Add <span style="color:#000000"> <u>Extra </u></span>Manpower to Events</div>
                                <div class ="description" style="color:#000000;">Click here if the event needs to add extra Manpower</div>
                            </div>
                            <div class="clear-both"></div>
                        </div>
                    </div>
                </a>
                    </div> 
            </div>
        </div>  -->
<!--        <div class="row">
            <div class="col-sm-6">
                   <div class="col-lg-3 col-md-6 col-sm-6 col-xs-12"> 
                <c:url var="linkHref" value="/BackController?action=cancelReservation" />
                <a href="${linkHref}">
                    <div class="card yellow summary-inline">
                        <div class="card-body">
                            <i class="icon fa fa-share-alt fa-4x"></i>
                            <div class="content">
                                <div class="title" style="font-size:25px;">Update an <span style="color:#000000"> <u>Event</u></span> Reservation</div>
                                <div class ="description" style="color:#000000;">Click here if an event reservation needs to be edited</div>
                            </div>
                            <div class="clear-both"></div>
                        </div>
                    </div>
                </a>
                    </div> 
            </div>
            <div class="col-sm-6">
                   <div class="col-lg-3 col-md-6 col-sm-6 col-xs-12"> 
                <c:url var="linkHref" value="/BackController?action=addExtraManpower" />
                <a href="${linkHref}">
                    <div class="card yellow summary-inline">
                        <div class="card-body">
                            <i class="icon fa fa-share-alt fa-4x"></i>
                            <div class="content">
                                <div class="title" style="font-size:25px;">Cancel an <span style="color:#000000"> <u>Event</u></span> Reservation</div>
                                <div class ="description" style="color:#000000;">Click here if an event reservation needs to be canceled</div>
                            </div>
                            <div class="clear-both"></div>
                        </div>
                    </div>
                </a>
                    </div> 
            </div>
        </div>-->

        <div class="row">
            <div class="col-sm-6">
                <!--   <div class="col-lg-3 col-md-6 col-sm-6 col-xs-12"> -->
                <c:url var="linkHref" value="/BackController?action=eventReservation" />
                <a href="${linkHref}">
                    <div class="card green summary-inline">
                        <div class="card-body">
                            <i class="icon fa fa-share-alt fa-4x"></i>
                            <div class="content">
                                <div class="title" style="font-size:25px;"><span style="color:#000000"> <u>Event</u></span> Reservation</div>
                                <div class ="description" style="color:#000000;">Click here if an event reservation needs to be viewed,edited and deleted</div>
                            </div>
                            <div class="clear-both"></div>
                        </div>
                    </div>
                </a>
                <!--    </div> -->
            </div>
            <div class="col-sm-6">
                <!--   <div class="col-lg-3 col-md-6 col-sm-6 col-xs-12"> -->
                <c:url var="linkHref" value="/BackController?action=subEventReservation" />
                <a href="${linkHref}">
                    <div class="card green summary-inline">
                        <div class="card-body">
                            <i class="icon fa fa-share-alt fa-4x"></i>
                            <div class="content">
                                <div class="title" style="font-size:25px;"><span style="color:#000000"> <u>Sub Event</u></span> Reservation</div>
                                <div class ="description" style="color:#000000;">Click here if a sub event reservation needs to be viewed,edited and deleted</div>
                            </div>
                            <div class="clear-both"></div>
                        </div>
                    </div>
                </a>
                <!--    </div> -->
            </div>
        </div>
    </div>
</div>



<jsp:include page="footer.jsp" />
