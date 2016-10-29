<%-- 
    Document   : compose
    Created on : Sep 9, 2016, 9:55:19 PM
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
    <c:url var="formAction" value="/BackController?action=createMessage" />
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
                                        <h4 class="title" style="padding-left: 20px">Compose New Message</h4>   
                                    </div>
                                    <div class="content">             
                                        <form id="contact_form" action="${formAction}" method="POST">
                                            <div class="row" style="padding-left: 20px">
                                                <label style="text-align: justify" for="name">To:</label><br />
                                                <input style="text-align: justify" id="to" class="input" name="to" type="text" value="" size="30" /><br />
                                            </div>
                                            <div class="row" style="padding-left: 20px">
                                                <label style="text-align: justify" for="email">Subject:</label><br />
                                                <input style="text-align: justify" id="subject" class="input" name="subject" type="text" value="" size="30" /><br />
                                            </div>
                                            <div class="row" style="padding-left: 20px">
                                                <label style="text-align: justify" for="message">Your message:</label><br />
                                                <textarea style="text-align: justify" id="message" class="input" name="message" rows="7" cols="30"></textarea><br />
                                            </div>
                                            <input type="hidden" name="username" value=<%= request.getAttribute("username")%> readonly="readonly" />
                                            <c:url var="formAction" value="/BackController" />
                                            <button class="btn btn-default" type="submit" value="Send message">Send Message</button>
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

<jsp:include page="footer.jsp" />


