<%-- 
    Document   : readMessage
    Created on : Sep 10, 2016, 9:21:42 PM
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
    <c:url var="formAction" value="/Controller?action=replyMessage" />
    <div class="side-body padding-top">
        <div class="row">
            <c:if test="${missend == 'true'}">
                <font color="red">Account to which message is to be sent does not exist!</font><br/>
            </c:if>
            <c:if test="${sent == 'true'}">
                <font color="red">Message successfully sent!</font><br/>
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
                                        <h4 class="title">Message</h4>   
                                    </div>
                                    <div>             
                                        <form id="contact_form" action="${formAction}" method="POST">
                                            <div class="row">
                                                <label style="text-align: justify" for="name">From:</label><br />
                                                <p style="text-align: justify"><%= ((ArrayList) request.getAttribute("message")).get(0)%> </p>
                                            </div>
                                            <div class="row">
                                                <label style="text-align: justify" for="email">Subject:</label><br />
                                                <p style="text-align: justify"><%= ((ArrayList) request.getAttribute("message")).get(1)%> </p>
                                            </div>
                                            <div class="row">
                                                <label style="text-align: justify" for="message">Message:</label><br />
                                                <p style="text-align: justify"><%= ((ArrayList) request.getAttribute("message")).get(2)%> </p>
                                            </div>
                                            <input type="hidden" name="username" value=<%= request.getAttribute("username")%> readonly="readonly" />
                                            <input type="hidden" name="receiver" value=<%= ((ArrayList) request.getAttribute("message")).get(0)%> readonly="readonly" />
                                            <input type="hidden" name="messageid" value=<%= ((ArrayList) request.getAttribute("message")).get(3)%> readonly="readonly" />
                                            <c:url var="formAction" value="/Controller" />
                                            <input type="submit" value="Reply message" />
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


