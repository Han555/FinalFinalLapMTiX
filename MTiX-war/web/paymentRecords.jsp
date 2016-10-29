<%-- 
    Document   : paymentRecords
    Created on : Oct 8, 2016, 1:35:35 PM
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
    <c:url var="formAction" value="/Controller?action=readMessage" />
    

        <div class="row">
            <c:url var="linkHref" value="/FinanceController?action=viewPayment" />
        </div>
        <div class="row">
            <c:if test="${payment == 'true'}">
                <font color="red">Payment successfully Made!</font><br/>
            </c:if>
            <c:if test="${invoice == 'true'}">
                <font color="red">Email invoice has been sent.</font><br/>
            </c:if>
                <c:if test="${cannot == 'true'}">
                <font color="red">Refund Request Denied.</font><br/>
            </c:if>
                
        </div>
        <div class="row">
            <div class="col-md-8">
                <div class="card">
                    <div class="header">
                        <h4 class="title">Payment Records</h4>

                    </div>
                    <div class="content">
                        <table class="table table-inbox table-hover">
                            <thead>
                                <tr>

                                    <th>Event</th>
                                    <th>Ticket Quantity</th>
                                    <th>Price</th>
                                    <th>Status</th>
                                    <th></th>
                                </tr>
                            </thead>
                            <tbody>
                                <%
                                    int size = (int) Integer.parseInt((String) request.getAttribute("recordSize"));
                                    ArrayList<ArrayList<String>> inboxPage = (ArrayList<ArrayList<String>>) request.getAttribute("inbox");
                                    for (int i = 0; i < size; i++) {
                                        String id = inboxPage.get(i).get(0);
                                        String eventName = inboxPage.get(i).get(1);
                                        String ticket = inboxPage.get(i).get(2);
                                        String amount = inboxPage.get(i).get(3);
                                        String status = inboxPage.get(i).get(4);
                                %>

                                <c:url var="formAction" value="/FinanceController?action=makePayment" />
                                <c:url var="formAction2" value="/FinanceController?action=requestRefund" />

                                <tr class="unread">




                                    <td class="view-message "><%= eventName%></td>
                                    <td class="view-message "><%= ticket%></td>
                                    <td class="view-message "><%= amount%></td>
                                    <% if (status.equals("unpaid")) {%>
                                    <td class="view-message"><%= status%></td>
                                    <td class="view-message">
                                        <form id="verifyForm" name="verifyForm" action="${formAction}" method="post">

                                            <input type="hidden" name="paymentid" value=<%= id%> readonly="readonly" />
                                            <input type="hidden" name="event" value=<%= eventName%> readonly="readonly" />
                                            <input type="hidden" name="ticket" value=<%= ticket%> readonly="readonly" />
                                            <input type="hidden" name="amount" value=<%= amount%> readonly="readonly" />
                                            <c:url var="formAction" value="/FinanceController" />
                                            <input type="submit" value="Pay" /> 
                                        </form></td>
                                        <% } else if (status.equals("paid")) {%>
                                    <td class="view-message"><%= status%></td>
                                    <td class="view-message">
                                        <form id="verifyForm" name="verifyForm" action="${formAction2}" method="post">

                                            <input type="hidden" name="paymentid" value=<%= id%> readonly="readonly" />
                                            <input type="hidden" name="event" value=<%= eventName%> readonly="readonly" />
                                            <input type="hidden" name="ticket" value=<%= ticket%> readonly="readonly" />
                                            <input type="hidden" name="amount" value=<%= amount%> readonly="readonly" />
                                            <c:url var="formAction2" value="/FinanceController" />
                                            <input type="submit" value="Request Refund" /> 
                                        </form>
                                    </td>
                                    
                                    <% } else {%>
                                    <td class="view-message"><%= status%></td>
                                    <td class="view-message"></td>
                                    <% } %>
                                </tr>
                                <%
                                    }
                                %>
                            </tbody>
                        </table>
                        <table border="1" cellpadding="5" cellspacing="5">
                            <tr>
                                <c:forEach begin="1" end="${noOfPages}" var="i">
                                    <c:choose>
                                        <c:when test="${currentPage eq i}">
                                            <td>${i}</td>
                                        </c:when>
                                        <c:otherwise>
                                            <td>
                                                <c:url var="linkHref" value="/FinanceController?action=viewPayment&page=${i}" />

                                                <a href="${linkHref}">${i}</a></td>
                                            </c:otherwise>
                                        </c:choose>
                                    </c:forEach>
                            </tr>
                        </table>
                        <div class="footer">

                        </div>
                    </div>
                </div>
            </div>

        </div>
    </div> 
</div>
</div>

<jsp:include page="footer.jsp" />


