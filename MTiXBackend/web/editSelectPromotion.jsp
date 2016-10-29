<%-- 
    Document   : editSelectPromotion
    Created on : Sep 20, 2016, 12:56:12 PM
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
    <c:url var="formAction" value="/BackController?action=editPromotion" />
    <div class="side-body padding-top">

        <div class="row">
            <div class="col-md-8">
                <div class="card">
                    <div class="header">
                        <h4 class="title">Select Promotion to Edit</h4>   
                    </div>                        
                    <c:if test="${edited == 'true'}">
                        <font color="red">Promotion Successfully Edited!</font><br/>
                    </c:if>

                    <form id="contact_form" action="${formAction}" method="POST"> 
                        <%
                            if (data == null) {
                        %>
                        <div align="center"><h3>No Promotion Found!</h3></div><br><br>
                        <%} else {
                        %><table id="table" class="table table-hover">
                            <thead>
                                <tr>
                                    <th data-field="state" data-radio="true"></th>
                                    <th data-field="name">Name</th>
                                    <th data-field="type">Promotion Type</th>
                                    <th data-field="discountRate">Discount Rate</th>
                                    <th data-field="requirement">Requirement</th>
                                </tr>
                            </thead>
                            <tbody>
                                <%
                                    for (int i = 0; i < data.size(); i++) {
                                        String promotionType = "";
                                        for (int j = 4; j < data.get(i).size(); j++) {
                                            promotionType += data.get(i).get(j) + " ";
                                        }
                                %>
                                <tr>
                                    <td><input type="radio" required="true" name="id" value="<%= data.get(i).get(0)%>"></td>
                                    <td><%= data.get(i).get(1)%></td>
                                    <td><%=promotionType%></td>
                                    <td><%= data.get(i).get(3)%></td>
                                    <td><%= data.get(i).get(2)%></td></tr>
                                    <% }%>
                            </tbody>
                        </table>
                        <div align="center" ><c:url var="formAction" value="/BackController" /><input type="submit" id="button"/></div>
                        <div><input hidden="true" type="text" name="type" value="<%=type%>"/></div>   
                            <%}%>
                    </form> 
                </div>
            </div>
        </div> 
    </div>
</div>

<jsp:include page="footer.jsp" />


