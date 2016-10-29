<%-- 
    Document   : trackFees
    Created on : Oct 10, 2016, 8:17:31 PM
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
            <c:if test="${reply == 'true'}">
                <font color="red">Reply successfully sent!</font><br/>
            </c:if>
        </div>
        <div class="row">
            <div class="col-md-8">
                <div class="card">
                    <div class="header">
                        <h4 class="title">Booking Fees</h4>

                    </div>
                    <div class="content">
                        <table class="table table-inbox table-hover">
                            <thead>
                                <tr>
                                    <th>Event</th>
                                    <th>Organizer</th>
                                    <th>Venue</th>
                                    <th>Start Date</th>
                                    <th>End Date</th>
                                    <th>Fees($)</th>
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
                                        String event = inboxPage.get(i).get(1);
                                        String organizer = inboxPage.get(i).get(2);
                                        String startDate = inboxPage.get(i).get(3);
                                        String endDate = inboxPage.get(i).get(4);
                                        String fees = inboxPage.get(i).get(5);
                                        String status = inboxPage.get(i).get(6);
                                        String venue = inboxPage.get(i).get(7);
                                %>

                                <c:url var="formAction" value="/BackFinanceController?action=editFeesStatus" />

                                <tr class="unread">
                                    <td class="view-message"><%= event%></td>
                                    <td class="view-message "><%= organizer%></td>
                                    <td class="view-message "><%= venue%></td>
                                    <td class="view-message "><%= startDate%></td>
                                    <td class="view-message "><%= endDate%></td>
                                    <td class="view-message "><%= fees%></td>
                                    <td class="view-message"><%= status%> </td>
                                    <% if (status.equals("unpaid")) {%>
                                    <td class="view-message">
                                        <form id="verifyForm" name="verifyForm" action="${formAction}" method="post">

                                            <input type="hidden" name="feesid" value=<%= id%> readonly="readonly" />
                                            <input type="hidden" name="paymentstatus" value="paid" readonly="readonly" />
                                            <c:url var="formAction" value="/BackFinanceController" />
                                            <input type="submit" value="Mark as paid" /> 
                                        </form>
                                    </td>
                                    <% } else { %>
                                    <td class="view-message">
                                        <form id="verifyForm" name="verifyForm" action="${formAction}" method="post">

                                            <input type="hidden" name="feesid" value=<%= id%> readonly="readonly" />
                                            <input type="hidden" name="paymentstatus" value="unpaid" readonly="readonly" />
                                            <c:url var="formAction" value="/BackFinanceController" />
                                            <input type="submit" value="Mark as unpaid" /> 
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
                                                <c:url var="linkHref" value="/BackFinanceController?action=trackFees&page=${i}" />

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


