<%-- 
    Document   : createLicensePayment
    Created on : Oct 12, 2016, 10:45:50 PM
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
    <c:url var="formAction" value="/BackFinanceController?action=makeLicensePayment" />
    <div class="side-body padding-top">
       
        <div class="row">
            <div class="col-md-6">
                <div class="card">
                    <div class="content"> 
                        <div class="container-fluid">
                            <div class="row">
                                <div class="col-md-8">
                                    <div class="header">
                                        <h4 class="title">Create New Licensing Payment</h4>   
                                    </div>
                                    <div class="content">             
                                        <form id="contact_form" action="${formAction}" method="POST">
                                            <div class="row">
                                                <label style="text-align: justify" for="name">Company:</label><br />
                                                <input style="text-align: justify" id="to" class="input" name="company" type="text" value="" size="30" /><br />
                                            </div>
                                            <div class="row">
                                                <label style="text-align: justify" for="email">Email:</label><br />
                                                <input style="text-align: justify" id="subject" class="input" name="email" type="text" value="" size="30" /><br />
                                            </div>
                                            <div class="row">
                                                <label style="text-align: justify" for="email">Amount:</label><br />
                                                <input style="text-align: justify" id="subject" class="input" name="amount" type="text" value="" size="30" /><br />
                                            </div>
                                            <div class="row">
                                                <label style="text-align: justify" for="email">Date:</label><br />
                                                <input style="text-align: justify" id="subject" class="input" name="date" type="text" value="" size="30" /><br />
                                            </div>
                                            
                                            <c:url var="formAction" value="/BackFinanceController" />
                                            <input type="submit" value="Create Fees" />
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