<%-- 
    Document   : promotion
    Created on : Sep 20, 2016, 11:23:07 AM
    Author     : Student-ID
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<!doctype html>
<jsp:include page="header.jsp" />
<!-- Main Content -->


<div class="container-fluid">
    <%String type = request.getAttribute("type").toString();
            List<ArrayList> data = (List<ArrayList>) request.getAttribute("data");
        %>

    <c:url var="formAction" value="/BackController?action=promotionCreated" />
    <div class="side-body padding-top">

        <div class="row">
            <div class="col-md-8">
                <div class="card">

                    <div class="header">
                        <h4 class="title">Set Promotion</h4>   
                    </div>           
                    <c:if test="${customized == 'true'}">
                        <font color="red">Customize Promotion Created Successfully!</font><br/>
                    </c:if>
                    <form id="contact_form" action="${formAction}" method="POST">
                       <%
                            if (data.size() == 0) {
                        %>
                        <div align="center"><h3>Create An Event First!</h3></div><br><br>
                        <% } else {%>
                        
                        <div align="center"><h1>Product Management System</h1></div>
                        <div align="center"><h3>Set Promotion</h3></div><br><br>
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
                            <%if (type.equals("1")) {
                            %>
                            <tr>
                                <td align="right">Credit Card : &nbsp</td>
                                <td>
                                    <select class="form-control" name="requirement" required="true">
                                        <option value="American Express Card">American Express Card</option>
                                        <option value="ANZ Card">ANZ Card</option>
                                        <option value="Citi Bank Card">Citi Bank Card</option>
                                        <option value="HSBC Card">HSBC Card</option>
                                        <option value="OCBC Frank Card">OCBC Frank Card</option>
                                        <option value="POSB Card">POSB Card</option>
                                        <option value="Standard Chartered Card">Standard Chartered Card</option>
                                        <option value="UOB Card">UOB Card</option>
                                    </select></td></tr>
                            <tr>
                                <td>&nbsp</td> 
                                <td>&nbsp</td>                     
                            </tr>
                            <%} else if (type.equals("2")) { %>
                            <tr>
                                <td align="right">Membership Required : &nbsp</td>
                                <td align="left"><input type="text" name="requirement" required="true" class="form-control"></td>
                            </tr> 
                            <tr>
                                <td>&nbsp</td> 
                                <td>&nbsp</td>                     
                            </tr>
                            <%} else if (type.equals("4")) { %>
                            <tr>
                                <td align="right">Volume Required : &nbsp</td>
                                <td align="left"><input type="number" name="requirement" min="1" required="true" class="form-control"></td>
                            </tr>
                            <tr>
                                <td>&nbsp</td> 
                                <td>&nbsp</td>                     
                            </tr>
                            <%} else if (type.equals("5")) { %>
                            <tr>
                                <td align="left"><input type="text" hidden="true" value="null" name="requirement" required="true"></td>
                            </tr><%}%>
                            <tr>
                                <td align="right">Description : &nbsp</td>
                                <td align="left"><input type="text" name="description" required="true" class="form-control"></td>
                            </tr>
                            <tr>
                                <td>&nbsp</td> 
                                <td>&nbsp</td>                     
                            </tr>
                        </table>
                        <table id="table" class="table table-hover">
                            <thead>
                                <tr>
                                    <th data-field="state" data-radio="true"></th>
                                    <th data-field="type">Event Type</th>
                                    <th data-field="name">Name</th>
                                    <th data-field="start">Start Date</th>
                                    <th data-field="end">End Date</th>
                                </tr>
                            </thead>
                            <tbody>
                                <%
                                    for (int i = 0; i < data.size(); i++) {
                                %>
                                <tr>
                                    <td><input type="checkbox" name="id" value="<%= data.get(i).get(0) + " " + data.get(i).get(4)%>"></td>
                                    <td><%= data.get(i).get(4)%></td>
                                    <td><%= data.get(i).get(1)%></td>
                                    <td><%= data.get(i).get(2)%></td>
                                    <td><%= data.get(i).get(3)%></td></tr>
                                    <% }%>
                            </tbody></table>
                        <div align="center" ><c:url var="formAction" value="/BackController" /><input type="submit" id="button" disabled="false"/></div>
                        <script>
                            var checkboxes = $("input[type='checkbox']"),
                                    submitButt = $("input[type='submit']");

                            checkboxes.click(function () {
                                submitButt.attr("disabled", !checkboxes.is(":checked"));
                            });
                        </script>
                        <input type="hidden" value="<%=type%>" name="type">
                        <%}%>
                    </form>


                </div>
            </div>
        </div> 
    </div>
</div>

<jsp:include page="footer.jsp" />


