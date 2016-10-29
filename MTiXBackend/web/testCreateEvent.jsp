<%-- 
    Document   : testCreateEvent
    Created on : Sep 19, 2016, 4:18:19 PM
    Author     : Student-ID
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>MTIX</title>
        <!-- Latest compiled and minified CSS -->
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">

        <!-- Optional theme -->
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css" integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous">

    </head>
    <body>
        <c:url var="formAction" value="/BackController?action=createEvent" />
        <c:if test="${created == 'true'}">
                <font color="red">Event successfully created!</font><br/>
            </c:if>
       <form id="contact_form" action="${formAction}" method="POST">
            <div align="center"><h1>Product Management System</h1></div>
            <div align="center"><h3>Create Event</h3></div><br><br>
            <table align="center">
                <tr>
                    <td>&nbsp</td> 
                </tr>
                <tr>
                    <td>&nbsp</td> 
                </tr>
            </table><br>
            <table align="center">
                <tr>
                    <td align="right">Event Name : </td>
                    <td align="left"><input type="text" name="name" required="true" length="30"</td>              
                </tr>
                <tr>
                    <td align="right">Enter Equipment : </td>
                    <td align="left"><input type="text" name="equipment" required="true" length="30"</td>                
                </tr>
                <tr>
                    <td align="right">Enter Manpower : </td>
                    <td align="left"><input type="number" name="man" required="true" min="1"</td>                
                </tr>
                <tr>
                    <td align="right">Enter Start Date : </td>
                    <td align="left"><input type="date" name="start" required="true">              
                </tr>
                <tr>
                    <td align="right">Enter End Date : </td>
                    <td align="left"><input type="date" name="end" required="true">              
                </tr>
                <tr>
                    <td>&nbsp</td> 
                    <td>&nbsp</td> 
                </tr>
                <tr>
                    <td>&nbsp</td> 
                    <td>&nbsp</td> 
                </tr>
                 <tr>
                    <td>&nbsp</td> 
                    <td>&nbsp</td> 
                    <c:url var="formAction" value="/BackController" />
                    <td><input type="submit" value="Register"/></td>
                </tr>
            </table>
            </form> 
         <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
         <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
    </body>
</html>
