<%-- 
    Document   : createFees
    Created on : Oct 10, 2016, 4:50:23 PM
    Author     : Student-ID
--%>

<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<!doctype html>
<jsp:include page="header.jsp" />
<!-- Main Content -->
<!-- Main Content -->
<div class="container-fluid">
    <c:url var="formAction" value="/BackFinanceController?action=makeFees" />
    <div class="side-body padding-top">

        <div class="row">
            <div class="col-md-6">
                <div class="card">
                    <div class="content"> 
                        <div class="container-fluid">
                            <div class="row">
                                <div class="col-md-8">
                                    <div class="header">
                                        <h4 class="title">Create New Booking Fee</h4>   
                                    </div>
                                    <div class="content">             
                                        <form id="contact_form" action="${formAction}" method="POST">
                                            <div class="row">
                                                <label style="text-align: justify" for="name">Organizer:</label><br />
                                                <input style="text-align: justify" id="to" class="input" name="organizer" type="text" value="" size="30" /><br />
                                            </div>
                                            <div class="row">
                                                <label style="text-align: justify" for="email">Event Name:</label><br />
                                                <input style="text-align: justify" id="subject" class="input" name="event" type="text" value="" size="30" /><br />
                                            </div>
                                            <div class="row">
                                                <label style="text-align: justify" for="email">venue:</label><br />
                                                <input style="text-align: justify" id="subject" class="input" name="venue" type="text" value="" size="30" /><br />
                                            </div>
                                            <div class="row">
                                                <label style="text-align: justify" for="email">start date:</label><br />
                                                <input style="text-align: justify" id="subject" class="input" name="startDate" type="text" value="" size="30" /><br />
                                            </div>
                                            <div class="row">
                                                <label style="text-align: justify" for="email">end date:</label><br />
                                                <input style="text-align: justify" id="subject" class="input" name="endDate" type="text" value="" size="30" /><br />
                                            </div>
                                            <div class="row">
                                                <label style="text-align: justify" for="email">fees:</label><br />
                                                <input style="text-align: justify" id="subject" class="input" name="fees" type="text" value="" size="30" /><br />
                                            </div>
                                            <div class="row">
                                                <label style="text-align: justify" for="email">status:</label><br />
                                                <input style="text-align: justify" id="subject" class="input" name="status" type="text" value="" size="30" /><br />
                                            </div>
                                            <c:url var="formAction" value="/BackFinanceController" />
                                            <input type="submit" value="Create Fees" />
                                        </form>						

                                    </div>

                                </div>
                            </div>
                        </div>
                    </div>
                </div>

            </div>
        </div> 
    </div>
</div>

<jsp:include page="footer.jsp" />
