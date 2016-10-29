<%-- 
    Document   : finances
    Created on : Oct 7, 2016, 7:45:28 PM
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

                                    <div class="content">             
                                        <form action="https://www.sandbox.paypal.com/cgi-bin/webscr" method="post">
                                            <input type="hidden" name="cmd" value="_xclick">
                                            <input type="hidden" name="business" value="nicefood555@gmail.com">
                                            <input type="hidden" name="item_name" value="Item name goes here">
                                            <input type="hidden" name="item_number" value="ITEMNO001">
                                            <input type="hidden" name="amount" value="5">
                                            <input type="hidden" name="quantity" value="1">
                                            <input type="hidden" name="no_note" value="1">
                                            <input type="hidden" name="currency_code" value="SGD">
                                            <INPUT TYPE="hidden" NAME="return" value="http://localhost:8080/MTiX-war/Controller?action=home">
                                            <!-- Enable override of buyers's address stored with PayPal . -->
                                            <input type="hidden" name="address_override" value="1">
                                            <!-- Set variables that override the address stored with PayPal. -->
                                            <input type="hidden" name="first_name" value="Han">
                                            <input type="hidden" name="last_name" value="Singapore">
                                            <input type="hidden" name="address1" value="Address line one">
                                            <input type="hidden" name="city" value="Singapore">
                                            <input type="hidden" name="state" value="Singapore">
                                            <input type="hidden" name="zip" value="560102">
                                            <input type="hidden" name="country" value="SIN">
                                            <input type="image" name="submit"
                                                   src="https://www.paypalobjects.com/en_US/i/btn/btn_buynow_LG.gif"
                                                   alt="PayPal - The safer, easier way to pay online">
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


