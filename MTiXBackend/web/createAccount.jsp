<%-- 
    Document   : createAccount
    Created on : Sep 11, 2016, 2:53:12 PM
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
            <div class="col-xs-12">
                <div class="card">
                    <div class="card-header">
                        <div class="card-title">
                            <div class="title">Create Account</div>
                        </div>
                    </div>
                    <div class="card-body">
                        <c:url var="formAction" value="/BackController?action=creating" />
                        <form id="contact_form" action="${formAction}" method="POST">
                            <div class="form-group" style="padding-bottom: 50px;">

                                <label for="eventdaterange" class="col-sm-2 control-label">Email Username</label>
                                <div class="col-sm-6">
                                    <input type="email" name="username" id="username" class="form-control" value="" required
                                           data-fv-notempty-message="Email username is required">

                                </div> 
                            </div>
                            <div class="form-group" style="padding-bottom: 50px;">

                                <label for="eventdaterange" class="col-sm-2 control-label">Role</label>
                                <div class="col-sm-6">
                                    <select class="js-example-basic-single js-states" style="width: 100%" name="role" id="role">
                                        <option value="product manager">product manager</option>
                                        <option value="property manager">property manager</option>
                                        <option value="event organizer">event organizer</option>
                                        <option value="finance manager">finance manager</option>
                                        <option value="content manager">content manager</option>
                                        <option value="crm manager">crm manager</option>

                                    </select>
                                </div> 
                            </div>
                            <div class="form-group" style="padding-bottom: 50px;">

                                <label for="eventdaterange" class="col-sm-2 control-label">Mobile Number</label>
                                <div class="col-sm-6">
                                    <input type="text" name="mobileNumber" id="username" class="form-control" value="" required
                                           data-fv-notempty-message="Mobile Number is required">

                                </div> 
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

<jsp:include page="footer.jsp" />


