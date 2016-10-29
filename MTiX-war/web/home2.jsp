<%-- 
    Document   : home
    Created on : Aug 28, 2016, 12:55:08 PM
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
                <c:url var="linkHref" value="/Controller?action=bulletinBoard" />
                <a href="${linkHref}">
                    <div class="card red summary-inline">
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
                <c:url var="linkHref" value="/Controller?action=message" />
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
                    <div class="col-lg-3 col-md-6 col-sm-6 col-xs-12">
                <c:url var="linkHref" value="/Controller?action=createAdmin" />
                <a href="${linkHref}">
                    <div class="card yellow summary-inline">
                        <div class="card-body">
                            <i class="icon fa fa-comments fa-4x"></i>
                            <div class="content">
                                <div class="title">create admin</div>
                            </div>
                            <div class="clear-both"></div>
                        </div>
                    </div>
                </a>
            </div>


        </div>
        <div class="row">
            <div class="col-sm-6 col-md-6">
                <div class="thumbnail">
                    <img src="assets/img/faces/purchasetickets.jpg" style="width:300 px;height:300px">
                    <div class="caption">
                        <h3 style="text-align: center">Buy Tickets</h3>
                        <p><c:url var="linkHref" value="/Controller?action=buyTickets" /><a href="${linkHref}" class="btn btn-primary" role="button">Enter</a> </p>
                    </div>
                </div>
            </div>


            <div class="col-sm-6 col-md-6">
                <div class="thumbnail">
                    <img src="assets/img/faces/personalfinances.jpg" alt="theater_main" style="width:300 px;height:300px">
                    <div class="caption">
                        <h3 style="text-align: center">Finances</h3>
                        <c:url var="formAction" value="/FinanceController?action=viewPayment" />
                        <form id="verifyForm" name="verifyForm" action="${formAction}" method="post">
                            <c:url var="formAction" value="/FinanceController?action=viewPayment" />
                            <input type="hidden" name="username" value=<%= request.getAttribute("username")%> readonly="readonly" />

                            <c:url var="formAction" value="/FinanceController" />
                            <input type="submit" class="btn btn-primary" value="Enter" /> 
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div> 
</div>
</div>




