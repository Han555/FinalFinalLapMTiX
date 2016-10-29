<%-- 
    Document   : TheaterSelected
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
            <span class="title" style="font-size: 40px;color: #22A7F0">Merlion Star Theater</span>
        </div>
        <div class="row">
            <div class="col-sm-6">

                <img src="img/property/Theatre.png" alt="theater_layout" style="width:100%;"> </div>
            <div class="col-sm-6">
                <div class="card">
                    <div class="card-header">
                        <div class="card-title">
                            <div class="title">Reserve Start Theater</div>
                            <div class="description"> </div>
                        </div>
                    </div>
                    <div class="card-body">
                        <div class="sub-title">Please fill in the form below to reserve this venue <span class="description">( Include the email of Event Organizer who requires the reservation )</span>
                            <c:url var="formAction" value="/BackController?action=saveNewSubEvent" />
                            <form class="form-horizontal" id="formSubmit" action="${formAction}" method="post">
                                <div class="form-group" style="padding-bottom: 20px;" >
                                    <label for="eventname" class="col-sm-4 control-label">Sub Event Name<span style="color:#FF0000;">*</span></label>
                                    <div class="col-sm-8">
                                        <Input type="text" id="eventname" name="eventname" value="${eventname}" required>
                                    </div> 
                                </div>



                                <div class="form-group" style="padding-bottom: 20px;" >
                                    <label for="eoemail" class="col-sm-4 control-label">Event Organizer Email<span style="color:#FF0000;">*</span></label>
                                    <div class="col-sm-8">
                                        <Input type="email" id="eoemail" name="eoemail" required>
                                        <span style="color:#FF0000">${userResult}</span>
                                    </div> 
                                </div>
                                <div class="form-group" >


                                    <input type="hidden" name="pname" value="Merlion Star Theater">

                                </div>
                                <div class="form-group">


                                    <input type="hidden" name="eventid" value="${eventid}">

                                </div>
                                <div class="form-group">


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


</div>





<jsp:include page="footer.jsp" />

