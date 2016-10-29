<%-- 
    Document   : copyCreateAccount
    Created on : Sep 21, 2016, 1:28:14 PM
    Author     : Student-ID
--%>

<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<!doctype html>
<jsp:include page="header.jsp" />
<!-- Main Content -->
<!-- Main Content -->
<div class="container-fluid">
    <c:url var="formAction" value="/BackController?action=creating" />
    <div class="side-body padding-top">
        <div class="row">

            <c:if test="${created == 'true'}">
                <font color="red">Account successfully created!</font><br/>
            </c:if>
            <c:if test="${conflict == 'true'}">
                <font color="red">Account has already been created!</font><br/>
            </c:if>
        </div>

        <div class="row">
            <div class="col-md-6">
                <div class="card">
                    <div class="content"> 
                        <div class="container-fluid">
                            <div class="row">

                                <div class="col-md-8">
                                    <div class="header">
                                        <h4 class="title">Create Account</h4>   
                                    </div>
                                    <div class="content">
                                        <form id="contact_form" action="${formAction}" method="POST">
                                            <div class="row">
                                                <label style="text-align: justify" for="name">Username:</label><br />
                                                <input style="text-align: justify" id="to" class="input" name="username" type="text" value="" size="30" /><br />
                                            </div>
                                            <div class="row">
                                                <label style="text-align: justify" for="email">Role:</label><br />
                                                <input style="text-align: justify" id="subject" class="input" name="role" type="text" value="" size="30" /><br />
                                            </div>
                                            <div class="row">
                                                <label style="text-align: justify" for="email">Mobile Number:</label><br />
                                                <input style="text-align: justify" id="subject" class="input" name="mobileNumber" type="text" value="" size="30" /><br />
                                            </div>
                                            <input type="hidden" name="username" value=<%= request.getAttribute("username")%> readonly="readonly" />
                                            <br>
                                            <c:url var="formAction" value="/BackController" />
                                            <input type="submit" value="Create Account" />
                                        </form>	
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

        </div>
    </div> 
</div>
</div>

<jsp:include page="footer.jsp" />


