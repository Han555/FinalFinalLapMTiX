<%-- 
    Document   : createSession
    Created on : Sep 19, 2016, 6:07:03 PM
    Author     : Student-ID
--%>

<%@page import="java.util.Calendar"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList, java.text.SimpleDateFormat, java.util.Date" %>
<!DOCTYPE html>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<!doctype html>
<jsp:include page="header.jsp" />
<!-- Main Content -->

<!-- Multidates -->
<script src="http://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.3.0/js/bootstrap-datepicker.js"></script>

<!-- Timepicker -->
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/timepicker/1.3.5/jquery.timepicker.css">
<script src="https://cdnjs.cloudflare.com/ajax/libs/timepicker/1.3.5/jquery.timepicker.js"></script>
<div class="container-fluid">
    <%
        ArrayList data = (ArrayList) request.getAttribute("data");
    %>
    <c:url var="formAction" value="/BackController?action=sessionCreated" />
    <div class="side-body padding-top">

        <div class="row">
            <div class="col-md-10">
                <div class="card">

                    <div class="header">
                        <h4 class="title">Event: <%=data.get(0)%> to <%=data.get(1)%></h4>   
                    </div>                        
                    <form id="contact_form" action="${formAction}" method="POST">                        
                        <table align="center">
                            <tr>
                                <td align="right">Sessions Date : &nbsp</td>
                                <td align="left"><input type="text" class="form-control date" required="true" name="dates"</td>    
                            <script>
                                $('.date').datepicker({
                                    multidate: true,
                                    format: 'yyyy-mm-dd',
                                    startDate: '<%=data.get(0)%>',
                                    endDate: '<%=data.get(1)%>'
                                });
                            </script>

                            </tr>
                            <tr>
                                <td>&nbsp</td> 
                                <td>&nbsp</td>                     
                            </tr>
                        </table>
                        <table align="center">
                            <tr>
                                <td align="right">Session Name : &nbsp</td>
                                <td align="left"><input type="text" name="name" required="true" class="form-control"></td> 
                                <td>&nbsp</td> 
                                <td align="right">Session description : &nbsp</td>
                                <td align="left"><input type="text" name="description" required="true" class="form-control"</td>                 
                            </tr>
                            <tr>
                                <td>&nbsp</td> 
                                <td>&nbsp</td>                     
                            </tr>
                            <% int i = Integer.valueOf(data.get(2).toString()); //no
                                for (int j = 0; j < i; j++) {%>
                            <tr id="time">
                                <td align="right">Session<%=j + 1%> Start Time : &nbsp</td>
                                <td align="left"><input type="text" name="start<%=j%>" required="true" class="form-control start"</td>                 
                                <td>&nbsp</td>     
                                <td align="right">Session<%=j + 1%> End Time : &nbsp</td>
                                <td align="left"><input type="text" name="end<%=j%>" required="true" class="form-control end" </td>         
                            <script>
                                $('.start').timepicker({
                                    timeFormat: 'H:mm',
                                    interval: 60,
                                    minTime: '09',
                                    maxTime: '11:00pm',
                                    startTime: '09:00',
                                    dynamic: false,
                                    dropdown: true,
                                    scrollbar: true
                                });

                                $('.end').timepicker({
                                    timeFormat: 'H:mm',
                                    interval: 60,
                                    minTime: '09',
                                    maxTime: '11:00pm',
                                    startTime: '09:00',
                                    dynamic: false,
                                    dropdown: true,
                                    scrollbar: true
                                });

                            </script>
                            </tr>
                            <tr>
                                <td>&nbsp</td> 
                                <td>&nbsp</td>                     
                            </tr>

                            <% }

                            %>
                            <tr>
                                <td>&nbsp</td> 
                                <td>&nbsp</td>                     
                            </tr>
                            <tr>
                                <td>&nbsp</td> 
                                <td>&nbsp</td> 
                                <c:url var="formAction" value="/BackController" />
                                <td>
                                    <c:url var="formAction" value="/BackController" />
                                    <input type="submit" value="Submit"/></td>
                            </tr>

                            <input type="hidden" value=<%=data.get(2)%> name="no">
                            <input type="hidden" value=<%=data.get(3)%> name="type">
                            <input type="hidden" value=<%=data.get(4)%> name="id">

                        </table> 
                    </form> 

                </div>
            </div>
        </div> 
    </div>
</div>

<jsp:include page="footer.jsp" />


