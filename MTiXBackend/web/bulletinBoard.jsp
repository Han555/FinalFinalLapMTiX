<%-- 
    Document   : bulletinBoard
    Created on : Sep 12, 2016, 12:41:38 PM
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
    <c:url var="formAction" value="/BackController?action=readBulletin" />
    <div class="side-body padding-top">
        <div class="row">
            <c:if test="${role == 'super administrator'}">

                <c:url var="linkHref" value="/BackController?action=composeBulletin" />
                <p><a href="${linkHref}" class="btn btn-primary" role="button">New Bulletin</a> </p>
            </c:if>
        </div>
        <div class="row">
            <c:if test="${created == 'true'}">
                <font color="red">Bulletin message successfully broadcast!</font><br/>
            </c:if>
        </div>
        <div class="row">
            <div class="col-md-8">
                <div class="thumbnail">
                    <div class="header">
                        <h4 class="title">Bulletin Board</h4>

                    </div>
                    <div class="content">
                        <table class="table table-inbox table-hover">
                            <thead>
                                <tr>
                                    <th></th>
                                    <th></th>
                                    <th>Subject</th>
                                    <th></th>
                                </tr>
                            </thead>
                            <tbody>
                                <%
                                    int size = (int) Integer.parseInt((String) request.getAttribute("recordSize"));
                                    ArrayList<ArrayList<String>> boardPage = (ArrayList<ArrayList<String>>) request.getAttribute("board");
                                    for (int i = 0; i < size; i++) {
                                        String id = boardPage.get(i).get(0);
                                        String subject = boardPage.get(i).get(1);
                                %>

                                <c:url var="formAction" value="/BackController?action=readBulletin" />

                                <tr class="unread">


                                    <td class="view-message">
                                        <form id="verifyForm" name="verifyForm" action="${formAction}" method="post">

                                            <input type="hidden" name="messageid" value=<%= id%> readonly="readonly" />
                                            <c:url var="formAction" value="/BackController" />
                                            <input type="submit" value="Read" /> 
                                        </form></td>
                                    <td class="view-message"></td>
                                    <td class="view-message"><%= subject%></td>
                                    <td class="view-message"> </td>
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
                                                <c:url var="linkHref" value="/BackController?action=bulletinBoard&page=${i}" />

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


