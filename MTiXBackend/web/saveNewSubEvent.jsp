<%-- 
    Document   : saveNewSubEvent
    Created on : Sep 22, 2016
    Author     : catherinexiong
--%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<jsp:useBean id="subevents" class="java.util.List<entity.SubEvent>" scope="request"/>

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
                        <div class="title">Reservation Made for the Sub Event Below</div>
                        <div class="description"></div>
                    </div>
                </div>
                <div class="card-body">
                    <div style="padding-bottom: 20px;">
                        <table class=" table table-hover" cellspacing="0" width="100%">
                            <thead>
                                <tr>

                                    <th>Sub Event Name</th>
                                    <th>Event it under </th>
                                    <th>Sub Event Type</th>
                                    <th>Start Date</th>
                                    <th>End Date</th>
                                    <th>Property</th>
                                    <th>Event Organizer Username</th>

                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach items="${subevents}" var="subevent">
                                    <tr>

                                        <td>${subevent.name}</td>
                                        <td>${subevent.event.name}</td>
                                        <td>${subevent.type}</td>
                                        <td>${subevent.start}</td>
                                        <td>${subevent.end}</td>
                                        <td>${subevent.property.propertyName}</td>
                                        <td>${subevent.user.username}</td>

                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>

                    </div>
                    <c:url var="formAction" value="/BackController?action=subReservationSearch" />
                    <form class="form-horizontal" id="formSubmit" action="${formAction}" method="post">
                        <div class="form-group">


                            <input type="hidden" name="eventid" value="${eventid}">

                        </div>

                        <div class="form-group" >
                            <div class="col-sm-10">
                                <button type="submit" class="btn btn-default" value="formSubmit">Create Another Sub Event</button><span>${msg}</span>
                            </div>
                        </div>
                    </form>

                    <c:url var="formAction" value="/BackController?action=addExtra" />
                    <form class="form-horizontal" id="formSubmit" action="${formAction}" method="post">
                        <div class="form-group">


                            <input type="hidden" name="eventid" value="${eventid}">

                        </div>

                        <div class="form-group" >
                            <div class="col-sm-10">
                                <button type="submit" class="btn btn-default" value="formSubmit">No More Sub Event</button><span>${msg}</span>
                            </div>
                        </div>
                    </form>

                </div>
            </div>

        </div>
    </div>


    <jsp:include page="footer.jsp" />


