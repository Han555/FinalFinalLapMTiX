<%-- 
    Document   : settleTicketTakings
    Created on : Oct 15, 2016, 11:27:28 PM
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
    <c:url var="formAction" value="/BackFinanceController?action=readMessage" />
    <div class="side-body padding-top">


        <div class="row">
            <c:if test="${invoice == 'true'}">
                <font color="red">Invoice successfully sent!</font><br/>
            </c:if>
        </div>
        <div class="row">
            <div class="col-md-12">
                <div class="card">
                    <div class="header">
                        <h4 class="title">Ticket Takings Accounts</h4>

                    </div>
                    <div class="content">
                        <table class="table table-inbox table-hover">
                            <thead>
                                <tr>
                                    <th>Event</th>
                                    <th>Organizer</th>
                                    <th>Ticket Takings($)</th>
                                    <th>Extra Cost($)</th>
                                    <th></th>
                                    <th></th>
                                </tr>
                            </thead>
                            <tbody>
                                <%
                                    int size = (int) Integer.parseInt((String) request.getAttribute("recordSize"));
                                    ArrayList<ArrayList<String>> inboxPage = (ArrayList<ArrayList<String>>) request.getAttribute("inbox");
                                    for (int i = 0; i < size; i++) {
                                        String id = inboxPage.get(i).get(0);
                                        String event = inboxPage.get(i).get(2);
                                        String organizer = inboxPage.get(i).get(1);
                                        String cost = inboxPage.get(i).get(3);
                                        String ticketTakings = inboxPage.get(i).get(4);
                                        int cost1 = Integer.parseInt(cost);
                                        double ticketTakings1 = Double.parseDouble(ticketTakings);
                                %>

                                <c:url var="formAction" value="/BackFinanceController?action=sendNormInvoice" />
                                <c:url var="formAction2" value="/BackFinanceController?action=sendTopUpInvoice" />
                                
                                <tr class="unread">
                                    <td class="view-message"><%= event%></td>
                                    <td class="view-message "><%= organizer%></td>
                                    <td class="view-message "><%= ticketTakings%></td>
                                    <td class="view-message "><%= cost%></td>

                                    <% if (ticketTakings1 >= cost1) {%>
                                    <td class="view-message">
                                        <form id="verifyForm" name="verifyForm" action="${formAction}" method="post">
                                            
                                            <input type="hidden" name="organizer" value=<%= organizer%> readonly="readonly" />
                                            <input type="hidden" name="takingsid" value=<%= id%> readonly="readonly" />
                                            <input type="hidden" name="event" value="<%= event%>" readonly="readonly" />
                                            <input type="hidden" name="takings" value=<%= ticketTakings%> readonly="readonly" />
                                            <input type="hidden" name="cost" value=<%= cost%> readonly="readonly" />
                                            <c:url var="formAction" value="/BackFinanceController" />
                                            <input type="submit" value="Send Invoice" /> 
                                        </form>
                                    </td>
             
                                    <% }  else { %>
                                    <td class="view-message">
                                        <form id="verifyForm" name="verifyForm" action="${formAction2}" method="post">
                                            
                                            <input type="hidden" name="organizer" value=<%= organizer%> readonly="readonly" />
                                            <input type="hidden" name="takingsid" value=<%= id%> readonly="readonly" />
                                            <input type="hidden" name="event" value="<%= event%>" readonly="readonly" />
                                            <input type="hidden" name="takings" value=<%= ticketTakings%> readonly="readonly" />
                                            <input type="hidden" name="cost" value=<%= cost%> readonly="readonly" />
                                            <c:url var="formAction2" value="/BackFinanceController" />
                                            <input type="submit" value="Send Top Up Invoice" /> 
                                        </form>
                                    </td>
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
                                                <c:url var="linkHref" value="/BackFinanceController?action=settleTicketTakings&page=${i}" />

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


