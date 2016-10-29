<%-- 
    Document   : deleteTicketSelectSession
    Created on : 4 Oct, 2016, 12:54:59 AM
    Author     : JingYing
--%>

<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<!doctype html>
<jsp:include page="header.jsp" />
<!-- Main Content -->
<!-- Main Content -->
<div class="container-fluid">
    <%
        List<ArrayList> data = (List<ArrayList>) request.getAttribute("data");
        String type = request.getAttribute("eventType").toString();
    %>
    <c:url var="formAction" value="/BackController?action=deleteTickets" />
    <div class="side-body padding-top">

        <div class="row">
            <div class="col-md-8">
                <div class="card">
                    <div class="header">
                        <h4 class="title">Select Session to delete Reserved Sections</h4>   
                    </div>                        

                    <form id="contact_form" action="${formAction}" method="POST">
                        <%
                            if (data == null) {
                        %>
                        <div align="center"><h3>No Session Found!</h3></div><br><br>
                        <%} else {
                        %><table id="table" class="table table-hover">
                            <thead>
                                <tr>
                                    <th data-field="state" data-radio="true"></th>
                                    <th data-field="name">Name</th>
                                    <th data-field="description">Description</th>
                                    <th data-field="start">Start</th>
                                    <th data-field="end">End</th>
                                </tr>
                            </thead>
                            <tbody>
                                <%
                                    for (int i = 0; i < data.size(); i++) {
                                %>
                                <tr>
                                    <td><input type="radio" name="id" required="true" value="<%= data.get(i).get(0)%>"></td>
                                    <td><%= data.get(i).get(1)%></td>
                                    <td><%= data.get(i).get(2)%></td>
                                    <td><%= data.get(i).get(3)%></td>
                                    <td><%= data.get(i).get(4)%></td></tr>
                                    <% }%>
                            </tbody>
                            <tr>
                                <td align="left"><input hidden="true" type="text" name="type" value="<%=type%>"/></td>                 
                            </tr>
                        </table>
                        <div align="center" ><c:url var="formAction" value="/BackController" /><input type="submit" value="Select"/></div>
                            <%}%>
                    </form> 
                </div>
            </div>
        </div> 
    </div>
</div>

<jsp:include page="footer.jsp" />

