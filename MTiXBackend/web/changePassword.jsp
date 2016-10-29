<%-- 
    Document   : changePassword
    Created on : Sep 4, 2016, 2:51:18 PM
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
        <title>Change Resetted Password</title>

    </head>
    <body>
        <c:url var="formAction" value="/BackController?action=change" />
        <div class="container">
            <section id="content">


                <form id="verifyForm" name="verifyForm" action="${formAction}" method="post">
                    <h1>Change Resetted Password</h1>
                    <input id="password" name="newPass" type="password" placeholder="password"/><br/>
                    <input id="password2" name="newPass2" type="password" placeholder="verify password"/><br/>



                    <input type="hidden" name="userName" value=<%= request.getAttribute("username")%> readonly="readonly" />
                    <c:url var="formAction" value="/BackController" />
                    <input type="submit" value="Change" /> 

                </form>
            </section>
        </div>
    </body>
</html>
