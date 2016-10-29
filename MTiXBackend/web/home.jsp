<%-- 
    Document   : home
    Created on : Sep 10, 2016, 11:46:10 PM
    Author     : Student-ID
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<!doctype html>
<jsp:include page="header.jsp" />
<!-- Main Content -->
<!-- Main Content -->
<div class="container-fluid">
    <div class="side-body padding-top">
        <div class="row">
            <div class="col-lg-3 col-md-6 col-sm-6 col-xs-12">
                <c:url var="linkHref" value="/BackController?action=bulletinBoard" />
                <a href="${linkHref}">
                    <div class="card green summary-inline">
                        <div class="card-body">
                            <i class="icon fa fa-inbox fa-4x"></i>
                            <div class="content">
                                <div class="title">Bulletin Board</div>
                            </div>
                            <div class="clear-both"></div>
                        </div>
                    </div>
                </a>
            </div>
            <div class="col-lg-3 col-md-6 col-sm-6 col-xs-12">
                <c:url var="linkHref" value="/BackController?action=message" />
                <a href="${linkHref}">
                    <div class="card yellow summary-inline">
                        <div class="card-body">
                            <i class="icon fa fa-comments fa-4x"></i>
                            <div class="content">
                                <div class="title">Inbox Messages</div>
                            </div>
                            <div class="clear-both"></div>
                        </div>
                    </div>
                </a>
            </div>
            <c:if test="${role == 'super administrator'}">
                <div class="col-lg-3 col-md-6 col-sm-6 col-xs-12">
                    <c:url var="linkHref" value="/BackController?action=createAccount" />
                    <a href="${linkHref}">
                        <div class="card blue summary-inline">
                            <div class="card-body">
                                <i class="icon fa fa-share-alt fa-4x"></i>
                                <div class="content">
                                    <div class="title">Create Account</div>
                                </div>
                                <div class="clear-both"></div>
                            </div>
                        </div>
                    </a>
                </div>
            </c:if>

        </div>
        <div class="row">
            <c:if test="${(role == 'super administrator' || role == 'property manager')}">
                <div class="col-sm-6 col-md-3">
                    <div class="thumbnail">
                        <img src="assets/img/faces/propertymanagement.jpg" style="width:300 px;height:300px">
                        <div class="caption">
                            <h3 style="text-align: center">Property Management System</h3>
                            <p><c:url var="linkHref" value="/BackController?action=propertyMain" /><a href="${linkHref}" class="btn btn-primary" role="button">Enter</a> </p>
                        </div>
                    </div>
                </div>
            </c:if>
            <c:if test="${(role == 'super administrator' || role == 'product manager')}">
                <div class="col-sm-6 col-md-3">
                    <div class="thumbnail">
                        <img src="assets/img/faces/productmanagement.jpg" alt="theater_main" style="width:300 px;height:300px">
                        <div class="caption">
                            <h3 style="text-align: center">Product Management System</h3>
                            <p><c:url var="linkHref" value="/BackController?action=productMain" /><a href="${linkHref}" class="btn btn-primary" role="button">Enter</a> </p>
                        </div>
                    </div>
                </div>
            </c:if>   

            <c:if test="${(role == 'super administrator' || role == 'event organizer')}">
                <div class="col-sm-6 col-md-3">
                    <div class="thumbnail">
                        <img src="assets/img/faces/events.jpg" alt="theater_main" style="width:300 px;height:300px">
                        <div class="caption">
                            <h3 style="text-align: center">Event Management System</h3>
                            <c:url var="formAction" value="/EventController?action=viewEvents" />
                            <form id="verifyForm" name="verifyForm" action="${formAction}" method="post">
                                <c:url var="formAction" value="/EventController?action=viewEvents" />
                                <input type="hidden" name="username" value=<%= request.getAttribute("username")%> readonly="readonly" />

                                <c:url var="formAction" value="/EventController" />
                                <input type="submit" class="btn btn-primary" value="Enter" /> 
                            </form>                       
                        </div>
                    </div>
                </div>
            </c:if>   

            <c:if test="${(role == 'super administrator' || role == 'finance manager')}">
                <div class="col-sm-6 col-md-3">
                    <div class="thumbnail">
                        <img src="assets/img/faces/finances.jpg" alt="theater_main" style="width:300 px;height:300px">
                        <div class="caption">
                            <h3 style="text-align: center">Finance Management System</h3>
                            <c:url var="formAction" value="/BackFinanceController?action=finances" />
                            <form id="verifyForm" name="verifyForm" action="${formAction}" method="post">
                                <c:url var="formAction" value="/BackFinanceController?action=finances" />
                                <input type="hidden" name="username" value=<%= request.getAttribute("username")%> readonly="readonly" />
                                <input type="hidden" name="role" value=<%= request.getAttribute("role")%> readonly="readonly" />
                                <c:url var="formAction" value="/BackFinanceController" />
                                <input type="submit" class="btn btn-primary" value="Enter" /> 
                            </form>                        
                        </div>
                    </div>
                </div>
            </c:if>
            <c:if test="${(role == 'event organizer')}">
                <div class="col-sm-6 col-md-3">
                    <div class="thumbnail">
                        <img src="img/alert.jpg" alt="ticket_main" style="width:300 px;height:300px">
                        <div class="caption">
                            <h3 style="text-align: center">Alert</h3>
                            <p><c:url var="linkHref" value="/BackController?action=alertMain" /><a href="${linkHref}" class="btn btn-primary" role="button">Enter</a> </p>
                        </div>
                    </div>
                </div>
            </c:if>
        </div>
        <div class="row">
            <c:if test="${(role == 'event organizer' || role == 'super administrator')}">
                <div class="col-sm-6 col-md-4">
                    <div class="thumbnail">
                        <img src="img/web.jpg" alt="theater_main" style="width:300 px;height:300px">
                        <div class="caption">
                            <h3 style="text-align: center">Webpage Details</h3>
                            <c:url var="formAction" value="/ContentBackController?action=contentMain" />
                            <form id="verifyForm" name="verifyForm" action="${formAction}" method="post">
                                <input type="hidden" name="username" value=<%= request.getAttribute("username")%> readonly="readonly" />
                                <input type="hidden" name="role" value=<%= request.getAttribute("role")%> readonly="readonly" />
                                <c:url var="formAction" value="/ContentBackController" />
                                <input type="submit" class="btn btn-primary" value="Enter" /> 
                            </form>      
                        </div>
                    </div>
                </div>
            </c:if>
            <c:if test="${(role == 'content manager')}">
                <div class="col-sm-6 col-md-4">
                    <div class="thumbnail">
                        <img src="img/web.jpg" alt="theater_main" style="width:300 px;height:300px">
                        <div class="caption">
                            <h3 style="text-align: center">Content Management System (Used By Content Manager)</h3>
                            <c:url var="formAction" value="/ContentBackController?action=contentReviewMain" />
                            <form id="verifyForm" name="verifyForm" action="${formAction}" method="post">
                                <input type="hidden" name="username" value=<%= request.getAttribute("username")%> readonly="readonly" />
                                <input type="hidden" name="role" value=<%= request.getAttribute("role")%> readonly="readonly" />
                                <c:url var="formAction" value="/ContentBackController" />
                                <input type="submit" class="btn btn-primary" value="Enter" /> 
                            </form>  
                        </div>
                    </div>
                </div>
            </c:if>
            <c:if test="${(role == 'super administrator' || role == 'crm manager')}">
                <div class="col-sm-6 col-md-3">
                    <div class="thumbnail">
                        <img src="assets/img/faces/finances.jpg" alt="theater_main" style="width:300 px;height:300px">
                        <div class="caption">
                            <h3 style="text-align: center">CRM System</h3>
                            <c:url var="formAction" value="/BackCRMController?action=crmFirst" />
                            <form id="verifyForm" name="verifyForm" action="${formAction}" method="post">
                                <c:url var="formAction" value="/BackCRMController?action=crmFirst" />
                                <input type="hidden" name="username" value=<%= request.getAttribute("username")%> readonly="readonly" />
                                <input type="hidden" name="role" value=<%= request.getAttribute("role")%> readonly="readonly" />
                                <c:url var="formAction" value="/BackCRMController" />
                                <input type="submit" class="btn btn-primary" value="Enter" /> 
                            </form>                        
                        </div>
                    </div>
                </div>
            </c:if>
            <div class="col-sm-6 col-md-4">
                    <div class="thumbnail">
                        <img src="img/web.jpg" alt="theater_main" style="width:300 px;height:300px">
                        <div class="caption">
                            <h3 style="text-align: center">Analytical Table</h3>
                            <c:url var="formAction" value="/EventController?action=analyze" />
                            <form id="verifyForm" name="verifyForm" action="${formAction}" method="post">
                                <input type="hidden" name="username" value=<%= request.getAttribute("username")%> readonly="readonly" />
                                <input type="hidden" name="role" value=<%= request.getAttribute("role")%> readonly="readonly" />
                                <c:url var="formAction" value="/EventController" />
                                <input type="submit" class="btn btn-primary" value="Enter" /> 
                            </form>  
                        </div>
                    </div>
                </div>
        </div>
    </div> 
</div>
</div>




