<%-- 
    Document   : promotion_ticket
    Created on : Sep 20, 2016, 12:06:32 PM
    Author     : Student-ID
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>

<!doctype html>
<jsp:include page="header.jsp" />
<!-- Main Content -->


<div class="container-fluid">
    <% String type = request.getAttribute("type").toString();
            String info = request.getAttribute("info").toString();
            int category = Integer.valueOf(request.getAttribute("category").toString());
    %>

    <c:url var="formAction" value="/BackController?action=promotionCreated" />
    <div class="side-body padding-top">

        <div class="row">
            <div class="col-md-8">
                <div class="card">

                    <div class="header">
                        <h4 class="title">Set Promotion</h4>   
                    </div>                        
                    <form id="contact_form" action="${formAction}" method="POST">
                        <%
                            if (category == 0){
                        %>
                        <div align="center"><h3>Please Create a session/ Set Price for the Seats first!</h3></div><br><br>

                        <%} else {%>
                        <table align="center">
                            <tr>
                                <td>&nbsp</td> 
                                <td>&nbsp</td>                     
                            </tr>
                            <tr>
                                <td align="right">Promotion Name : &nbsp</td>
                                <td align="left"><input type="text" name="name" required="true" class="form-control"></td>
                            </tr>
                            <tr>
                                <td>&nbsp</td> 
                                <td>&nbsp</td>                     
                            </tr>
                            <tr>
                                <td align="right">Discount Rate(%) : &nbsp</td>
                                <td align="left"><input type="number" name="discount" min="1" max="100" required="true" class="form-control"></td>
                            </tr>
                            <tr>
                                <td>&nbsp</td> 
                                <td>&nbsp</td>                     
                            </tr>
                            <tr>
                                <td align="right">Category : &nbsp</td>
                                <td><select class="form-control" name="requirement" required="true">
                                        <% for (int i = 0; i < category; i++) {%>
                                        <option value="Category<%=i + 1%>">Category <%=i + 1%></option>
                                        <% }
                                        %>
                                    </select></td></tr>
                            <tr>
                                <td>&nbsp</td> 
                                <td>&nbsp</td>                     
                            </tr>
                            <tr>
                                <td align="right">Description : &nbsp</td>
                                <td align="left"><input type="text" name="description" required="true" class="form-control"></td>
                            </tr>
                            <tr>
                                <td>&nbsp</td> 
                                <td>&nbsp</td>                     
                            </tr>
                        </table>
                        <div align="center" ><c:url var="formAction" value="/BackController" /><input type="submit" id="button"/></div>
                        <input type="hidden" value="<%=type%>" name="type">
                        <input type="hidden" value="<%=info%>" name="id">
                        <%}%>
                    </form>

                </div>
            </div>
        </div> 
    </div>
</div>

<jsp:include page="footer.jsp" />


