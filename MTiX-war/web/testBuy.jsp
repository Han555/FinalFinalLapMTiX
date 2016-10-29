<%-- 
    Document   : testBuy
    Created on : Oct 8, 2016, 12:47:50 PM
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
    <c:url var="formAction" value="/FinanceController?action=testBuy" />
    <div class="side-body padding-top">
       
        <div class="row">
            <div class="col-md-6">
                <div class="card">
                    <div class="content"> 
                        <div class="container-fluid">
                            <div class="row">
                                <div class="col-md-8">
                                    <div class="header">
                                        <h4 class="title">Create New Payment Record</h4>   
                                    </div>
                                    <div class="content">             
                                        <form id="contact_form" action="${formAction}" method="POST">
                                            <div class="row">
                                                <label style="text-align: justify" for="name">Receiver:</label><br />
                                                <input style="text-align: justify" id="to" class="input" name="receiver" type="text" value="" size="30" /><br />
                                            </div>
                                            <div class="row">
                                                <label style="text-align: justify" for="email">Event Name:</label><br />
                                                <input style="text-align: justify" id="subject" class="input" name="event" type="text" value="" size="30" /><br />
                                            </div>
                                            <div class="row">
                                                <label style="text-align: justify" for="email">ticket quantity:</label><br />
                                                <input style="text-align: justify" id="subject" class="input" name="quantity" type="text" value="" size="30" /><br />
                                            </div>
                                            <div class="row">
                                                <label style="text-align: justify" for="email">price:</label><br />
                                                <input style="text-align: justify" id="subject" class="input" name="price" type="text" value="" size="30" /><br />
                                            </div>
                                            <div class="row">
                                                <label style="text-align: justify" for="email">promotion:</label><br />
                                                <input style="text-align: justify" id="subject" class="input" name="promotion" type="text" value="" size="30" /><br />
                                            </div>
                                            <input type="hidden" name="username" value=<%= request.getAttribute("username")%> readonly="readonly" />
                                            <c:url var="formAction" value="/FinanceController" />
                                            <input type="submit" value="Create Record" />
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