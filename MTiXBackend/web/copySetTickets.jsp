<%-- 
    Document   : copySetTickets
    Created on : Sep 22, 2016, 6:35:43 PM
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
    <c:url var="formAction" value="/BackController?action=addTickets" />
    <div class="side-body padding-top">


        <div class="row">
            <c:if test="${added == 'true'}">
                <font color="red">Tickets successfully added!</font><br/>
            </c:if>
        </div>
        <div class="row">
            <div class="col-md-8">
                <div class="card">
                    <div class="header">
                        <h4 class="title">Promotions</h4>

                    </div>
                    <div class="content">
                        <table class="table table-inbox table-hover">
                            <thead>
                                <tr>
                                    <th>Promotion</th>
                                    <th>Description</th>
                                    <th>Number of Tickets</th>
                                    <th>Amount to be added</th>
                                    <th></th>
                                </tr>
                            </thead>
                            <tbody>
                                <%
                                    int size = (int) Integer.parseInt((String) request.getAttribute("recordSize"));
                                    ArrayList<ArrayList<String>> inboxPage = (ArrayList<ArrayList<String>>) request.getAttribute("inbox");
                                    for (int i = 0; i < size; i++) {
                                        String id = inboxPage.get(i).get(0);
                                        String promotion = inboxPage.get(i).get(1);
                                        String description = inboxPage.get(i).get(2);
                                        String numberOfTickets = inboxPage.get(i).get(3);
                                %>

                                <c:url var="formAction" value="/BackController?action=readMessage" />

                                <tr class="unread">




                                    <td class="view-message  dont-show"><%= promotion%></td>
                                    <td class="view-message "><%= description%></td>
                            <form id="verifyForm" name="verifyForm" action="${formAction}" method="post">
                                <td class="view-message"><input type="text" name="numberOfTics" /></td>
                                <form id="verifyForm" name="verifyForm" action="${formAction}" method="post">
                                <td class="view-message">
                                    <input type="hidden" name="promotionId" value=<%= id%> readonly="readonly" />
                                    <c:url var="formAction" value="/BackController" />
                                    <input type="submit" value="Add" /> 
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
                                                <c:url var="linkHref" value="/BackController?action=setTickets&page=${i}" />

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


