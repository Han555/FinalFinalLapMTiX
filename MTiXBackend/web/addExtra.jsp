
<%-- 
    Document   : addExtra
    Created on : Sep 26, 2016, 7:07:35 PM
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
                        <div class="title">Sub Events</div>
                        <div class="description"></div>
                    </div>
                </div>
                <div class="card-body">
                    <div style="padding-bottom: 20px;">
                        <table class=" table table-hover" cellspacing="0" width="100%">
                            <thead>
                                <tr>
                                    <th></th>
                                    <th>Sub Event Name</th>
                                    <th>Event It Under</th>
                                    <th>Start</th>
                                    <th>End</th>
                                    <th>Venue Name</th>
                                    <th>User Email</th>



                                </tr>
                            </thead>

                            <tbody>


                                <c:forEach items="${event.subEvents}" var="subevent">
                                    <tr>
                                        <td><div class="radio" style="margin-top: 0;">
                                                <input type="radio" name='selectedSubevent' value="${subevent.id}">
                                            </div> </td>

                                        <td>${subevent.name}</td>
                                        <td>${subevent.event.name}</td>
                                        <td>${subevent.start}</td>
                                        <td>${subevent.end}</td>
                                        <td>${subevent.property.propertyName}</td>
                                        <td>${subevent.user.username}</td>




                                    </tr>
                                </c:forEach>
                            </tbody>


                        </table>
                    </div>

                    <div class="sub-title">Please Press the Button Below to Add Extra Equipment and Manpower 
                    </div>



                    <c:url var="formAction" value="/BackController?action=addExtraEquipment" />
                    <form class="form-horizontal" id="formSubmit" >
                        <div class="form-group">


                            <input type="hidden" name="eventid" value="${eventid}">

                        </div>

                        <div class="form-group" >
                            <div class="col-sm-10">
                                <button type="submit" class="btn btn-default" value="formSubmit">No More Sub Event</button><span>${msg}</span>
                            </div>
                        </div>
                    </form>




                    <div class="col-sm-12 text-center" style="padding-bottom:50px;" >
                        <a class="btn btn-primary" role="button" id="createEquipment">Create Additional Equipment</a>
                        <a class="btn btn-primary" role="button" id="createManpower">Add Additional Manpower</a>

                    </div>

                </div>
            </div>
        </div>
    </div>


</div>


<script>

    $("#createEquipment").click(function () {
        var selected = $('input[name="selectedSubevent"]:checked').val();
        if (selected == null)
            alert("null");
        console.log("Selected: " + selected);
        window.location = "BackendCon?action=hdfs&id=" + selected;
    });

    $("#createManpower").click(function () {
        var selected = $('input[name="selectedSubevent"]:checked').val();
        console.log("Selected: " + selected);
        window.location = "http://apple.com"
    });
</script>

<jsp:include page="footer.jsp" />

