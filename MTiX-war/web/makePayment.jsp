<%-- 
    Document   : makePayment
    Created on : Oct 8, 2016, 2:51:23 PM
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
<div class="container">
    <div class="panel panel-default col-lg-12" style="box-shadow: 0px 3px 10px 1px rgba(119, 119, 119, 0.75);
         -moz-box-shadow: 0px 3px 10px 1px rgba(119, 119, 119, 0.75);
         -webkit-box-shadow: 0px 3px 10px 1px rgba(119, 119, 119, 0.75);">
        <div class="panel-heading" style="height:40px;text-align:center;font-size:20px;margin-left: -15px; margin-right: -15px;background: repeating-linear-gradient(
             -45deg,
             transparent,
             transparent,
             #EEE 2px,
             transparent 3px
             );">My Shopping Bag</div> 
    <div class="check">	
    <c:url var="formAction" value="/FinanceController?action=addAddress" />
    
        <div class="row">
            <c:if test="${missend == 'true'}">
                <font color="red">Account to which message is to be sent does not exist!</font><br/>
            </c:if>
            <c:if test="${sent == 'true'}">
                <font color="red">Message successfully sent!</font><br/>
            </c:if>
        </div>
        <div class="row">
            <div class="col-md-6">
                <div class="card">
                    <div class="content"> 
                        <div class="container-fluid">
                            <div class="row">
                                <div class="col-md-8">
                                    <div class="header">
                                        <h4 class="title">Payment Details</h4>   
                                    </div>
                                    <div class="content">             
                                        <form id="contact_form" action="${formAction}" method="POST">
                                            <div class="row">
                                                <h5 class="title">Event Name: </h5> <label style="text-align: justify" for="email"><%= request.getAttribute("event")%></label><br />
                                            </div>
                                            <div class="row">
                                                <h5 class="title">Ticket Quantity: </h5> <label style="text-align: justify" for="email"><%= request.getAttribute("quantity")%></label><br />
                                            </div>
                                            <div class="row">
                                                <h5 class="title">Price($): </h5> <label style="text-align: justify" for="email"><%= request.getAttribute("price")%></label><br /><br/>
                                            </div>
                                            <div class="header">
                                                <h4 class="title">Billing Address</h4>   
                                            </div>
                                            <div class="row">
                                                <label style="text-align: justify" for="name">Address:</label><br />
                                                <input style="text-align: justify" id="to" class="input" name="address" type="text" value="" size="60" /><br />
                                            </div>
                                            <div class="row">
                                                <label style="text-align: justify" for="name">Country:</label><br />
                                                <input style="text-align: justify" id="to" class="input" name="country" type="text" value="" size="60" /><br />
                                            </div>
                                            <div class="row">
                                                <label style="text-align: justify" for="name">City:</label><br />
                                                <input style="text-align: justify" id="to" class="input" name="city" type="text" value="" size="60" /><br />
                                            </div>
                                            <div class="row">
                                                <label style="text-align: justify" for="name">ZIP Code:</label><br />
                                                <input style="text-align: justify" id="to" class="input" name="zip" type="text" value="" size="60" /><br />
                                            </div>
                                            <input type="hidden" name="username" value=<%= request.getAttribute("username")%> readonly="readonly" />
                                            <input type="hidden" name="paymentid" value=<%= request.getAttribute("paymentid")%> readonly="readonly" />
                                            <input type="hidden" name="event" value="<%= request.getAttribute("event")%>" readonly="readonly" />
                                            <input type="hidden" name="ticket" value=<%= request.getAttribute("quantity")%> readonly="readonly" />
                                            <input type="hidden" name="price" value=<%= request.getAttribute("price")%> readonly="readonly" />
                                            <c:url var="formAction" value="/FinanceController" />
                                            <input type="submit" value="Submit" />
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


