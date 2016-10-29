<%-- 
    Document   : saveEventWithSub
    Created on : Sep 22, 2016, 8:51:38 PM
    Author     : catherinexiong
--%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<jsp:useBean id="event" class="entity.Event" scope="request"/>
<jsp:include page="header.jsp" />

<!-- Main Content -->
<div class="side-body">
    <div class="page-title">
        <span class="title"></span>
    </div>
    <div class="row">
        <div class="col-xs-12">
            <div class="card">
                <div class="card-header">
                    <div class="card-title">
                        <div class="title">Create Sub Events under an Event</div>
                        <div class="description"></div>
                    </div>
                </div>
                <div class="card-body">
                    <div style="padding-bottom: 20px;">
                        <table class=" table table-hover" cellspacing="0" width="100%">
                            <thead>
                                <tr>

                                    <th>Event Name</th>
                                    <th>Event Description</th>
                                    <th>Event Organizer Username</th>

                                </tr>
                            </thead>
                            <tbody>
                                <tr>

                                    <th>${event.name}</th>
                                    <th>${event.description}</th>
                                    <th>${event.user.username}</th>

                                </tr>
                            </tbody>
                        </table>
                    </div>
                                    <% 
                                        session.setAttribute("eventid", event.getId()); %>
                    <div class="sub-title">Please Press the Button Below to Create Sub Event <span class="description">( To Search Avaliable Properties for Sub Event )</span>  
                    </div>
                    
                       

                        
                        <div class="form-group" style="padding-bottom: 60px;">
                            <c:url var="linkHref" value="/BackController?action=subReservationSearch" />
                            <div class="col-sm-offset-2 col-sm-10">
                              <a href="${linkHref}">
                                <button type="button" class="btn btn-default" value="seSubmit">Create Sub Event</button></a>
                            </div>
                        </div>
                   
                </div>
            </div>
        </div>
    </div>


</div>








<script>
    var propertyId = 1;
    var blockedDates = null;
    var publicHolidays = null;


    function notifyPeak() {

    }
    function deNotifyPeak() {

    }

    $(document).ready(function () {





        $('#daterange').daterangepicker(function (start, end, label) {
            console.log("New date range selected: ' + start.format('YYYY-MM-DD') + ' to ' + end.format('YYYY-MM-DD') + ' (predefined range: ' + label + ')");
        });


    });
</script>

    <jsp:include page="footer.jsp" />


