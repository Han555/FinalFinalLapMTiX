<%-- 
    Document   : viewEvents
    Created on : Oct 9, 2016, 5:35:33 PM
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
    <c:url var="formAction" value="/EventController?action=eventDetails" />
    <div class="side-body padding-top">

        <div class="row">
            <c:url var="linkHref" value="/EventController?action=eventDetails" />
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

                                    <th>Event</th>
                                    <th></th>
                                    <th></th>
                                    <th></th>
                                </tr>
                            </thead>
                            <tbody>
                                <%
                                    int size = (int) Integer.parseInt((String) request.getAttribute("recordSize"));
                                    ArrayList<String> inboxPage = (ArrayList<String>) request.getAttribute("inbox");
                                    for (int i = 0; i < size; i++) {
                                        String eventName = inboxPage.get(i);
                                        
                                %>

                                <c:url var="formAction" value="/EventController?action=eventDetails" />

                                <tr class="unread">




                                    <td class="view-message "><%= eventName%></td>
                                    <td class="view-message "></td>
                                    <td class="view-message "></td>
                                    <td class="view-message">
                                        <form id="verifyForm" name="verifyForm" action="${formAction}" method="post">

                                            <input type="hidden" name="event" value="<%= eventName%>" readonly="readonly" />
                                            <c:url var="formAction" value="/EventController" />
                                            <input type="submit" value="View Details" /> 
                                        </form></td>
                                        
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
                                                <c:url var="linkHref" value="/EventController?action=viewEvents&page=${i}" />

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


