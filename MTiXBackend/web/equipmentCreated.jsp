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
                    <c:forEach items="${eList}" var="equipment">
                        <tr>
                            <td>${equipment.id}</td>
                            <td>${equipment.equipmentName}</td>
                            <td>${equipment.location}</td>
                            <td>${equipment.standard}</td>
                            <td>${equipment.price}</td>
                        </c:forEach>
                </tbody>
            </table>

            <jsp:include page="footer.jsp" />