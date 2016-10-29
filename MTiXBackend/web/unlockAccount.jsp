<%-- 
    Document   : unlockAccount
    Created on : Sep 1, 2016, 12:54:51 PM
    Author     : Student-ID
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<link href="assets/css/style2.css" rel="stylesheet">

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Unlock Account</title>
    </head>
    <body>
        <%
            String message = "Unlock account ";
            String username = "";
            username += (String) request.getAttribute("username");
            message += username;
        %>



        <c:url var="formAction" value="/BackController?action=unlocking" />

        <div class="container">
            <section id="content">

                <form id="unlockAccount" name="unlockAccount" action="${formAction}" method="post">
                    <h1><%= message%></h1>
                    <input type="hidden" name="userName" value=<%= username%> readonly="readonly" />
                    <c:url var="formAction" value="/Controller" />
                    <input type="submit" value="Unlock Account"  /> 

                </form>
            </section>
        </div>
    </body>
</html>
