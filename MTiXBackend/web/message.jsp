<%-- 
    Document   : message
    Created on : Sep 4, 2016, 5:36:06 PM
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
    <c:url var="formAction" value="/BackController?action=readMessage" />
    <div class="side-body padding-top">

        <div class="row">
            <c:url var="linkHref" value="/BackController?action=compose" />
            <p><a href="${linkHref}" class="btn btn-primary" role="button">New</a> </p>
        </div>
        <div class="row">
            <c:if test="${reply == 'true'}">
                <font color="red">Reply successfully sent!</font><br/>
            </c:if>
        </div>
        <div class="row">
            <div class="col-md-8">
                <div class="card">
                    <div class="header">
                        <h4 class="title">Inbox</h4>

                    </div>
                    <div class="content">
                        <table class="table table-inbox table-hover">
                            <thead>
                                <tr>
                                    <th></th>
                                    <th>Sender</th>
                                    <th>Subject</th>
                                    <th>Status</th>
                                </tr>
                            </thead>
                            <tbody>
                                <%
                                    int size = (int) Integer.parseInt((String) request.getAttribute("recordSize"));
                                    ArrayList<ArrayList<String>> inboxPage = (ArrayList<ArrayList<String>>) request.getAttribute("inbox");
                                    for (int i = 0; i < size; i++) {
                                        String id = inboxPage.get(i).get(0);
                                        String sender = inboxPage.get(i).get(1);
                                        String subject = inboxPage.get(i).get(2);
                                        String status = inboxPage.get(i).get(3);
                                %>

                                <c:url var="formAction" value="/BackController?action=readMessage" />

                                <tr class="unread">


                                    <td class="view-message">
                                        <form id="verifyForm" name="verifyForm" action="${formAction}" method="post">

                                            <input type="hidden" name="messageid" value=<%= id%> readonly="readonly" />
                                            <c:url var="formAction" value="/BackController" />
                                            <input type="submit" value="Read" /> 
                                        </form></td>

                                    <td class="view-message  dont-show"><%= sender%></td>
                                    <td class="view-message "><%= subject%></td>
                                    <% if (status.equals("unread")) { %>
                                    <td class="view-message"><span class="label label-danger pull-right">unread</span> </td>
                                    <% } else { %>
                                    <td class="view-message"> </td>
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
                                                <c:url var="linkHref" value="/BackController?action=message&page=${i}" />

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


