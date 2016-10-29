<%-- 
    Document   : ConcertHallSelected
    Created on : Sep 20, 2016, 10:50:03 AM
    Author     : catherinexiong
--%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<jsp:include page="header.jsp" />
<!-- Main Content -->

<div class="side-body">
    <div class="container">
        <div class="page-title">
            <span class="title" style="font-size: 40px;color: #22A7F0">Merlion Concert Hall</span>
        </div>
        <div class="row">
            <div class="col-sm-6">

                <img src="img/property/Concerthall_layout.png" alt="concert_main" style="width:100%;"> </div>
            <div class="col-sm-6">
                <div class="card">
                    <div class="card-header">
                        <div class="card-title">
                            <div class="title">Reserve Concert Hall</div>
                            <div class="description"> </div>
                        </div>
                    </div>
                    <div class="card-body">
                        <div class="sub-title">Please fill in the form below to reserve this venue <span class="description">( Include the email of Event Organizer who requires the reservation )</span>
                            <c:url var="formAction" value="/BackController?action=saveNewEvent" />
                            <form class="form-horizontal" id="formSubmit" action="${formAction}" method="post">
                                <div class="form-group" style="padding-bottom: 20px;" >
                                    <label for="eventname" class="col-sm-4 control-label">Event Name<span style="color:#FF0000;">*</span></label>
                                    <div class="col-sm-8">
                                        <Input type="text" id="eventname" name="eventname" value="${eventname}" required>
                                    </div> 
                                </div>


                                <div class="form-group" style="padding-bottom: 20px;">

                                    <label for="eventdes" class="col-sm-4 control-label">Event Description<span style="color:#FF0000;">*</span></label>
                                    <div class="col-sm-8">
                                        <textarea class="form-control" rows="3" required id="eventdes" name="eventdes" >${eventdes}</textarea>
                                    </div>
                                </div>
                                <div class="form-group" style="padding-bottom: 20px;" >
                                    <label for="eoemail" class="col-sm-4 control-label">Event Organizer Email<span style="color:#FF0000;">*</span></label>
                                    <div class="col-sm-8">
                                        <Input type="email" id="eoemail" name="eoemail" required>
                                        <span style="color:#FF0000">${userResult}</span>
                                    </div> 
                                </div>
                                <div class="form-group" style="padding-bottom: 20px;">


                                    <input type="hidden" name="pname" value="Merlion Concert Hall">

                                </div>
                                <div class="form-group" >


                                    <input type="hidden" name="daterange" value="${daterange}">

                                </div>
                                <div class="form-group" >


                                    <input type="hidden" name="type" value="${type}">

                                </div>
                                <div class="form-group" >
                                    <div class="col-sm-10">
                                        <button type="submit" class="btn btn-default" value="formSubmit">Reserve Venue</button><span>${msg}</span>
                                    </div>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <script>
        var id = 0;
        $(".p1").click(function () {
            id = $(this).attr('id');

            $(".modal-header #myModalLabel").text('Detailed Seats Arrangement #' + id);
            $("#popup").html("LOADING...");
            $("#popup").html('<iframe id="p1frame" class="embed-responsive-item" frameborder="0" src="http://localhost:8080/MTiX-war/seat.jsp?id=' + id + '"></iframe>');

            //console.log('"http://localhost:8080/MTiX-war/seat.jsp?id=' + id + '"');
        });
    </script>
    <div class="modal fade modal-primary" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
        <div class="modal-dialog modal-lg">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title" id="myModalLabel">Detailed seats</h4>
                </div>
                <div class="modal-body">
                    <div class="embed-responsive embed-responsive-16by9" id="popup">
                        <!---
                        <iframe id="p1frame" class="embed-responsive-item" frameborder="0" src="http://localhost:8080/MTiX-war/seat.jsp?id="></iframe>
                        -->
                    </div>


                    <div class="modal-footer">
                        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                        <button type="button" class="btn btn-primary">OK</button>
                    </div>
                </div>
            </div>
        </div>


        <jsp:include page="footer.jsp" />
