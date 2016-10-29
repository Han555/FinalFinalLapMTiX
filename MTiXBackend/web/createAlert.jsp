<%-- 
    Document   : copySetTickets
    Created on : Sep 22, 2016, 6:35:43 PM
    Author     : Student-ID
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList, java.util.List" %>
<!DOCTYPE html>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<!doctype html>
<jsp:include page="header2.jsp" />
<!-- Main Content -->
<!-- Multidates -->
<script src="http://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.3.0/js/bootstrap-datepicker.js"></script>


<div class="container-fluid">
    <%
        List<ArrayList> data = (List<ArrayList>) request.getAttribute("data");
        List<ArrayList> alerts = (List<ArrayList>) request.getAttribute("alerts");
        String date = (String) request.getAttribute("date");
        String endDate = (String) request.getAttribute("endDate");
    %>

    <c:url var="formAction" value="/BackController?action=createdAlert" />
    <div class="side-body padding-top">

        <div class="row">
            <form id="contact_form" action="${formAction}" method="POST">
                <%
                    if (data == null) {
                %>
                <div align="center"><h3>No SESSION Found!</h3></div><br><br>
                <%} else {
                %>
                <div class="col-sm-6">
                    <div class="card">
                        <div class="header">
                            <h4 class="title">Create/Edit Alerts</h4>   
                        </div> 
                        <table align="center">
                            <tr>
                                <td>&nbsp</td> 
                            </tr>
                            <tr>
                                <td>&nbsp</td> 
                            </tr>
                            <tr>
                                <td align="right">Apply to all Event/Sub-Event Session : &nbsp;</td>
                                <td align="center"><input type="radio" required="true" name="apply" value="yes">Yes &nbsp;<br></td>
                                <td align="left"><input type="radio" required="true" name="apply" value="no">No<br></td>

                            </tr>
                            <tr>
                                <td>&nbsp</td> 
                            </tr>
                        </table>
                        <table align="center">
                            <tr>
                                <td align="right">Session : &nbsp</td>
                                <td>
                                    <select class="form-control" id="id" name="id" required="true">
                                        <%                                            for (int i = 0; i < data.size(); i++) {
                                        %>
                                        <option value="<%=data.get(i).get(0)%>"><%=data.get(i).get(0) + " | " + data.get(i).get(1) + " | " + data.get(i).get(3) + " | " + data.get(i).get(4)%></option>
                                        <% }%>
                                    </select></td>
                            </tr>
                            <tr>
                                <td>&nbsp</td> 
                            </tr>
                            <tr>
                                <td align="right">Alert Type : &nbsp</td>
                                <td align="left">
                                    <select class="form-control" name="type" required="true">
                                        <option value="1">Informative Alert - Check every 3 days</option>
                                        <option value="2">Important Alert - Check every 2 days</option>
                                        <option value="3">Urgent Alert - Check every 1 day</option>
                                    </select></td>
                            </tr>
                            <tr>
                                <td>&nbsp</td> 
                            </tr>
                            <tr><td align="right">Start Alert Date : &nbsp</td>
                                <td align="left"><input type="text" class="form-control startdate" id="start" required="true" name="date"</td>    
                            <font color="red">From now till <%=date%></font><br/>
                            <script>
                                $('.startdate').datepicker({
                                    multidate: false,
                                    format: 'yyyy-mm-dd',
                                    startDate: '$.now()',
                                    endDate: '<%=date%>'
                                });
                            </script>
                            </tr>
                            <tr>
                                <td>&nbsp</td> 
                            </tr>
                            <tr><td align="right">End Alert Date : &nbsp</td>
                                <td align="left"><input type="text" class="form-control enddate" id="end" required="true" name="endDate"</td>    
                            <font color="red">From Start Alert Date till <%=endDate%></font><br/>
                            <script>
                                $('.enddate').datepicker({
                                    multidate: false,
                                    format: 'yyyy-mm-dd',
                                    startDate: '$.now()',
                                    endDate: '<%=endDate%>'
                                });

                                $("form").submit(function (event) {
                                    if (new Date($('.enddate').val()) <= new Date($('.startdate').val())) {
                                        alert("Alert Start Date must before Alert End Date.");
                                        return false;
                                    }
                                });
                            </script>
                            </tr>
                            <tr>
                                <td>&nbsp</td> 
                            </tr>
                            <tr>
                                <td align="right">Below sales(%) : &nbsp</td>
                                <td align="left"><input type="number" min="0" max="100" name="sales" required="true" class="form-control"></td> 
                            </tr>
                            <tr>
                                <td>&nbsp</td> 
                            </tr>
                            <tr>
                                <td align="right">In-Charge email : &nbsp</td>
                                <td align="left"><input type="email" name="email" required="true" class="form-control"></td> 
                            </tr>
                            <tr>
                                <td>&nbsp</td> 
                            </tr>
                            <tr>
                                <td align="center"><input type="submit" value="Submit"/></td>
                            </tr>
                        </table>
                    </div>
                </div>
            </form>

            <div class="col-sm-6">
                    <table id="table" class="table table-hover table-bordered">
                        <thead>
                            <tr>
                                <th data-field="type">Session No.</th>
                                <th data-field="name">Sales(%)</th>
                                <th data-field="start">Alert Type</th>
                                <th data-field="email">Email</th>
                                <th data-field="start">Start Date</th>
                                <th data-field="end">End Date</th>
                            </tr>
                        </thead>
                        <tbody>
                            <%
                                for (int i = 0; i < alerts.size(); i++) {
                            %>
                            <tr>
                                <td><%= alerts.get(i).get(0)%></td>
                                <td><%= alerts.get(i).get(1)%></td>
                                <td><%= alerts.get(i).get(2)%></td>
                                <td><%= alerts.get(i).get(3)%></td>
                                <td><%= alerts.get(i).get(4)%></td>
                                <td><%= alerts.get(i).get(5)%></td></tr>
                                <% }%>
                        </tbody>

                    </table>
                    <%}%>
                </div>

        </div> 
    </div>

</div>

<jsp:include page="footer.jsp" />