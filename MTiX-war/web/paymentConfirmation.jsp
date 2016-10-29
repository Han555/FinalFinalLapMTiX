<%-- 
    Document   : paymentConfirmation
    Created on : Oct 8, 2016, 10:00:46 PM
    Author     : Student-ID
--%>

<%@page import="java.lang.String"%>
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
                                        <h4 class="title">Payment Confirmation</h4>   
                                    </div>
                                    <div class="content">             
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
                                            <h5 class="title">Address: </h5> <label style="text-align: justify" for="email"><%= request.getAttribute("address")%></label><br /><br/>
                                        </div>
                                        <div class="row">
                                            <h5 class="title">Country: </h5> <label style="text-align: justify" for="email"><%= request.getAttribute("country")%></label><br /><br/>
                                        </div>
                                        <div class="row">
                                            <h5 class="title">City: </h5> <label style="text-align: justify" for="email"><%= request.getAttribute("city")%></label><br /><br/>
                                        </div>
                                        <div class="row">
                                            <h5 class="title">Zip: </h5> <label style="text-align: justify" for="email"><%= request.getAttribute("zip")%></label><br /><br/>
                                        </div>
                      
                                        
                                        <form action="https://www.sandbox.paypal.com/cgi-bin/webscr" method="post">
                                            <%
                                                String id = (String) request.getAttribute("paymentid");
                                                String username = (String) request.getAttribute("username");
                                                username = "&name=" + username;
                                            %>

                                            <%String address = "http://localhost:8080/MTiX-war/FinanceController?action=viewPayment&id=" + id +username;%>
                                            <input type="hidden" name="cmd" value="_xclick">
                                            <input type="hidden" name="business" value="nicefood555@gmail.com">
                                            <input type="hidden" name="item_name" value="<%= request.getAttribute("event")%>">
                                            <input type="hidden" name="amount" value="<%= request.getAttribute("price")%>">
                                            <input type="hidden" name="quantity" value="<%= request.getAttribute("quantity")%>">
                                            <input type="hidden" name="currency_code" value="SGD">
                                            <INPUT TYPE="hidden" NAME="return" value=<%= address%>>
                                            <!-- Enable override of buyers's address stored with PayPal . -->
                                            <input type="hidden" name="address_override" value="0">
                                            <!-- Set variables that override the address stored with PayPal. -->
                                            <input type="hidden" name="first_name" value="<%= request.getAttribute("username")%>">
                                            <input type="hidden" name="address1" value="<%= request.getAttribute("address")%>">
                                            <input type="hidden" name="country" value="<%= request.getAttribute("country")%>">
                                            <input type="hidden" name="city" value=<%= request.getAttribute("city")%>>
                                            <input type="hidden" name="zip" value="<%= request.getAttribute("zip")%>">
                                            
                                            <input type="image" name="submit"
                                                   src="https://www.paypalobjects.com/en_US/i/btn/btn_buynow_LG.gif"
                                                   alt="PayPal - The safer, easier way to pay online">
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


