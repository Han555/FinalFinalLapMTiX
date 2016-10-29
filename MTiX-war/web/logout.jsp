<%-- 
    Document   : logout
    Created on : Sep 4, 2016, 5:37:16 PM
    Author     : Student-ID
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <% response.setHeader("Cache-Control", "no-cache"); //HTTP 1.1 
            response.setHeader("Pragma", "no-cache"); //HTTP 1.0 
            response.setDateHeader("Expires", 0); //prevents caching at the proxy server  
        %>
        <title>Inactivity Session</title>
    </head>
    <body>
        <h1>Account logged out due to inactivity session</h1>

        <%
            request.setAttribute("error", "true");
            RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
            rd.forward(request, response);

        %> 

    <c:url var="linkHref" value="/Controller?action=login" />
    <li><a href="${linkHref}">Login</a></li>
</body>
</html>
