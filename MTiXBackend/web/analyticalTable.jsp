<%-- 
    Document   : analyticalTable
    Created on : Oct 28, 2016, 8:03:03 AM
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
    <c:url var="formAction" value="/BackCRMController?action=readMessage" />
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
                        <h4 class="title">Analytical RFM & CLV Score</h4>

                    </div>
                    <div class="content">
                        <table class="table table-inbox table-hover">
                            <thead>
                                <tr>
                                    <th>Customer</th>
                                    <th></th>
                                    <th>Recency</th>
                                    <th></th>
                                    <th>Frequency</th>
                                    <th></th>
                                    <th>Monetary</th>
                                    <th></th>
                                    <th>Customer Lifetime Value</th>
                                </tr>
                            </thead>
                            <tbody>
                                <%
                                    int size = (int) Integer.parseInt((String) request.getAttribute("recordSize"));
                                    ArrayList<ArrayList<String>> inboxPage = (ArrayList<ArrayList<String>>) request.getAttribute("inbox");
                                    for (int i = 0; i < size; i++) {
                                        String user = inboxPage.get(i).get(0);
                                        String recency = inboxPage.get(i).get(1);
                                        String frequency = inboxPage.get(i).get(2);
                                        String monetary = inboxPage.get(i).get(3);
                                        String clv = inboxPage.get(i).get(4);
                                        
                                %>

                                <c:url var="formAction" value="/EventController?action=editFeesStatus" />

                                <tr class="unread">
                                    <td class="view-message"><%= user%></td>
                                    <td class="view-message"></td>
                                    <td class="view-message "><%= recency%></td>
                                    <td class="view-message"></td>
                                    <td class="view-message "><%= frequency%></td>
                                    <td class="view-message"></td>
                                    <td class="view-message "><%= monetary%></td>
                                    <td class="view-message"></td>
                                    <td class="view-message "><%= clv%></td>
                                    
                                    
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
                                                <c:url var="linkHref" value="/EventController?action=analyze&page=${i}" />

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


