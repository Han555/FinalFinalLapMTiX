<%-- 
    Document   : equipmentCreated
    Created on : Sep 23, 2016, 3:49:48 PM
    Author     : hyc528
--%>

<%@page import="java.util.Calendar"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList, java.text.SimpleDateFormat, java.util.Date" %>
<!DOCTYPE html>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<!doctype html>
<jsp:include page="header.jsp" />
<div class="side-body">
    <div class="page-title">
        <span class="title">The below Equipment has been created successfully!</span>
    </div>
    <div class="card-body">
        <div style="padding-bottom: 20px;">
            <table class="table-hover" cellspacing="0" width="100%">
                <thead>
                    <tr>
                        <th>Equipment ID</th>
                        <th>Equipment Name</th>
                        <th>Location</th>
                        <th>Standard</th>
                        <th>Price</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${mList}" var="manpower">
                        <tr>
                            <td>${manpower.id}</td>
                            <td>${manpower.equipmentName}</td>
                            <td>${manpower.location}</td>
                            <td>${manpower.standard}</td>
                            <td>${manpower.price}</td>
                        </c:forEach>
                </tbody>
            </table>

            <jsp:include page="footer.jsp" />