<%-- 
    Document   : deleteSelectSession
    Created on : Sep 20, 2016, 12:04:39 AM
    Author     : Student-ID
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
    <c:url var="formAction" value="/BackController?action=sessionDeleted" />
    <div class="side-body padding-top">

        <div class="row">
            <div class="col-md-8">
                <div class="card">
                    <div class="header">
                        <h4 class="title">Select Sessions to delete</h4>   
                    </div>                        
                    <c:if test="${success == 'true'}">
                        <font color="red">Sessions Created Successfully!</font><br/>
                    </c:if>

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
                                    <td><input type="checkbox" name="id" value="<%= data.get(i).get(0)%>"></td>
                                    <td><%= data.get(i).get(1)%></td>
                                    <td><%= data.get(i).get(2)%></td>
                                    <td><%= data.get(i).get(3)%></td>
                                    <td><%= data.get(i).get(4)%></td></tr>
                                    <% }%>
                            </tbody>

                        </table>
                        <div align="center" ><c:url var="formAction" value="/BackController" /><input type="submit" id="button" disabled="false"/></div>
                            <%}%>
                        <script>
                            var checkboxes = $("input[type='checkbox']"),
                                    submitButt = $("input[type='submit']");

                            checkboxes.click(function () {
                                submitButt.attr("disabled", !checkboxes.is(":checked"));
                            });
                            
                            $("form").submit(function (event) {
                             if (confirm("You are about to delete the sessions you have selected. Are you sure about that?")){
                                 return true;
                             } else {
                                 return false;
                             }
                        });
                        </script>
                    </form> 
                </div>
            </div>
        </div> 
    </div>
</div>

<jsp:include page="footer.jsp" />


