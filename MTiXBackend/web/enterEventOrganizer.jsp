<%-- 
    Document   : enterEventOrganizer
    Created on : 13 Oct, 2016, 11:52:27 PM
    Author     : JingYing
--%>

<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<!doctype html>
<jsp:include page="header.jsp" />
<!-- Main Content -->

<div class="container-fluid">
    <%
        ArrayList data = (ArrayList) request.getAttribute("data");
    %>
    <div class="side-body padding-top">
        <c:url var="formAction" value="/ContentBackController?action=contentMain" />
        <div class="row">
            <div class="col-md-10">
                <div class="card">       
                    <form id="contact_form" action="${formAction}" method="POST">  
                        <c:if test="${error == 'true'}">
                            <font color="red">Cannot Find Event Organizer</font><br/>
                        </c:if>
                        <table align="center">
                            <tr>
                                <td align="right">Event Organizer Email : &nbsp</td>
                                <td align="left">
                                    <select class="form-control" name="email" class="form-control date" required="true">
                                        <%
                                            for (int i = 0; i < data.size(); i++){%>
                                        <option value="<%=data.get(i) %>"><%=data.get(i) %></option>
                                        <% }
                                        %>
                                    </select></td>  
                            </tr>
                            <tr>
                                <td>&nbsp</td> 
                                <td>&nbsp</td>                     
                            </tr>
                        </table>
                        <table align="center">
                            <tr>
                                <td>&nbsp</td> 
                                <td>&nbsp</td> <td>
                                    <input type="submit" value="Submit"/></td>
                            </tr>
                        </table> 
                    </form> 

                </div>
            </div>
        </div> 
    </div>
</div>

<jsp:include page="footer.jsp" />

