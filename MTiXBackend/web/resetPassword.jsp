<%-- 
    Document   : resetPassword
    Created on : Sep 4, 2016, 1:17:28 PM
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
        <title>Reset Password</title>
    </head>
    <body>

        <c:url var="formAction" value="/BackController?action=sendResetPassword" />
        <div class="container">
            <section id="content">


                <form id="resetForm" name="resetForm" action="${formAction}" method="post">
                    <h1>Reset Password</h1>

                    <c:if test="${absent == 'true'}">
                        <font color="red">User account does not exist! Please try again!</font><br/>
                    </c:if>
                    <input id="userName" name="userName" type="text" placeholder="Username"/><br/>

                    <c:url var="formAction" value="/BackController" />
                    <input type="submit" value="Reset" /> 
                </form>
            </section>
        </div>
    </body>
</html>
