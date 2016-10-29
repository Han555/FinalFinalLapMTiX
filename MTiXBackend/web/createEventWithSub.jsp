<%-- 
    Document   : createEventWithSub
    Created on : Sep 22, 2016, 12:24:01 PM
    Author     : catherinexiong
--%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<jsp:include page="header.jsp" />
<!-- Main Content -->

<div class="container-fluid">
    <div class="side-body">
        <div class="page-title">
            <span class="title">Create Main Event</span>

        </div>



        <div class="row">
            <div class="col-xs-12">
                <div class="card">
                    <div class="card-header">
                        <div class="card-title">
                            <div class="title">Fill In The Form Blow</div>
                            <div class="description">Please enter the details of main event (Include indicate the email of Event Organizer who requires this event)</div>
                        </div>
                    </div>
                    <div class="card-body">
                        <c:url var="formAction" value="/BackController?action=saveEventWithSub" />
                        <form class="form-horizontal" id="formSubmit" action="${formAction}" method="post">
                            <div class="form-group" style="padding-bottom: 20px;" >
                                <label for="eventname" class="col-sm-2 control-label">Event Name<span style="color:#FF0000;">*</span></label>
                                <div class="col-sm-8">
                                    <Input type="text" id="eventname" name="eventname" required>
                                </div> 
                            </div>
                            <div class="form-group" style="padding-bottom: 20px;" >
                                <label for="eventdes" class="col-sm-2 control-label">Event Description<span style="color:#FF0000;">*</span></label>
                                <div class="col-sm-8">
                                    <textarea class="form-control" rows="3" required id="eventdes" name="eventdes"></textarea>
                                </div> 
                            </div>
                            <div class="form-group" style="padding-bottom: 20px;" >
                                <label for="eoemail" class="col-sm-2 control-label">Event Organizer Email<span style="color:#FF0000;">*</span></label>
                                <div class="col-sm-8">
                                    <Input type="email" id="eoemail" name="eoemail" required>
                                    <span style="color:#FF0000">${userResult}</span>
                                </div> 
                            </div>




                            <div class="form-group" style="padding-bottom: 20px;">
                                <div class="col-sm-offset-2 col-sm-10">
                                    <button type="submit" class="btn btn-default" value="formSubmit">Submit</button>
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



</script>      

<jsp:include page="footer.jsp" />

