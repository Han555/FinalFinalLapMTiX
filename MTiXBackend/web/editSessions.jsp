<%-- 
    Document   : editSessions
    Created on : Sep 20, 2016, 10:32:18 AM
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
    <!-- Multidates -->
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.0/jquery.min.js"></script>
        <script src="http://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.3.0/js/bootstrap-datepicker.js"></script>
 
        <!-- Timepicker -->
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/timepicker/1.3.5/jquery.timepicker.css">
        <script src="https://cdnjs.cloudflare.com/ajax/libs/timepicker/1.3.5/jquery.timepicker.js"></script>
    <%
        ArrayList data = (ArrayList) request.getAttribute("data");
    %>
    <c:url var="formAction" value="/BackController?action=sessionEdited" />
    <div class="side-body padding-top">

        <div class="row">
            <div class="col-md-10">
                <div class="card">
                    <div class="header">
                        <h4 class="title">Edit Sessions</h4>   
                    </div>                        

                    <form id="contact_form" action="${formAction}" method="POST">
            <table align="center">
                <tr>
                    <td>&nbsp</td> 
                </tr>
                <tr>
                    <td>&nbsp</td> 
                </tr>
                <tr>
                    <td align="right">Session Name : &nbsp</td>
                    <td align="left"><input type="text" required="true" name="name" value="<%=data.get(1)%>"> </td>  
                    <td>&nbsp</td> <td>&nbsp</td> 
                    <td align="right">Previous Start Time : &nbsp</td>
                    <td align="left"><input type="text" disabled="true" value="<%=data.get(3)%>"</td>    
                </tr>
                <tr>
                    <td align="right">Session description : &nbsp</td>
                    <td align="left"><input type="text" required="true" name="description" value="<%=data.get(2)%>"</td>     
                    <td>&nbsp</td> <td>&nbsp</td> 
                    <td align="right">Previous End Time : &nbsp</td>
                    <td align="left"><input type="text" disabled="true" value="<%=data.get(4)%>"</td>      
                </tr>
                <tr>
                    <td>&nbsp</td> 
                </tr>
                <tr>
                    <td>&nbsp</td> 
                </tr>
            </table>
            <table align="center">
                <tr>
                    <td align="right">Session Start Time : &nbsp</td>
                    <td align="left"><input type="text" required="true" name="start" class="form-control start"</td>                 
                    <td>&nbsp</td>     
                    <td align="right">Session End Time : &nbsp</td>
                    <td align="left"><input type="text" required="true" name="end" class="form-control end"</td>         
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
                        minTime: '10',
                        maxTime: '11:00pm',
                        startTime: '10:00',
                        dynamic: false,
                        dropdown: true,
                        scrollbar: true
                    });
                </script>
                </tr>
                <tr>
                    <td align="left"><input hidden="true" type="text" name="previous" value="<%=data.get(3)%>"</td>                 
                </tr>
                <tr>
                    <td align="left"><input hidden="true" type="text" name="id" value="<%=data.get(0)%>"</td>                 
                </tr>
                <tr>
                    <td align="left"><input hidden="true" type="text" name="eventId" value="<%=data.get(5)%>"</td>                 
                </tr>
                <tr>
                    <td align="left"><input hidden="true" type="text" name="type" value="<%=data.get(6)%>"</td>                 
                </tr>
                <tr>
                    <td>&nbsp</td> 
                    <td>&nbsp</td>                     
                </tr>
                <tr>
                    <td>&nbsp</td> 
                    <td>&nbsp</td> 
                    <td><c:url var="formAction" value="/BackController" /><input type="submit" value="Submit"/></td>
                </tr>
            </table>
                    </form> 
                </div>
            </div>
        </div> 
    </div>
</div>

<jsp:include page="footer.jsp" />


