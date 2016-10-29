<%-- 
    Document   : eventRecords
    Created on : Oct 9, 2016, 11:33:14 PM
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
    <c:url var="formAction" value="/EventController?action=readMessage" />
    <div class="side-body padding-top">

        <div class="row">
            <c:url var="linkHref" value="/EventController?action=viewPayment" />
        </div>
        <div class="row">
            <c:if test="${payment == 'true'}">
                <font color="red">Payment successfully Made!</font><br/>
            </c:if>
            <c:if test="${invoice == 'true'}">
                <font color="red">Email invoice has been sent.</font><br/>
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

                                    <th>Customer</th>
                                    <th>Ticket Quantity</th>
                                    <th>Price(per ticket)</th>
                                    <th>Status</th>
                                    <th></th>
                                </tr>
                            </thead>
                            <tbody>
                                <%
                                    int size = (int) Integer.parseInt((String) request.getAttribute("recordSize"));
                                    ArrayList<ArrayList<String>> inboxPage = (ArrayList<ArrayList<String>>) request.getAttribute("inbox");
                                    for (int i = 0; i < size; i++) {
                                        String customer = inboxPage.get(i).get(0);
                                        String ticket = inboxPage.get(i).get(1);
                                        String amount = inboxPage.get(i).get(2);
                                        String status = inboxPage.get(i).get(3);
                                %>


                                <tr class="unread">

                                    <td class="view-message "><%= customer%></td>
                                    <td class="view-message "><%= ticket%></td>
                                    <td class="view-message "><%= amount%></td>
                                    <td class="view-message"><%= status%></td>
                                    
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
                                                <c:url var="linkHref" value="/EventController?action=eventDetails&page=${i}" />

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


