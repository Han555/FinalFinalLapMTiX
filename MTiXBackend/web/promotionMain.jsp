<%-- 
    Document   : promotionMain
    Created on : Sep 20, 2016, 11:16:56 AM
    Author     : Student-ID
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<!doctype html>
<jsp:include page="header.jsp" />
<!-- Main Content -->


<div class="container-fluid">

    <c:url var="formAction" value="/BackController?action=promotion" />
    <div class="side-body padding-top">

        <div class="row">
            <div class="col-md-8">
                <div class="card">

                    <div class="header">
                        <h4 class="title">Set Promotion</h4>   
                    </div>           
                    <c:if test="${customized == 'true'}">
                        <font color="red">Customize Promotion Created Successfully!</font><br/>
                    </c:if>
                    <c:if test="${promotionCreated == 'true'}">
                        <font color="red">Promotion Created Successfully!!</font><br/>
                    </c:if>
                    <c:if test="${promotionCreated1245 == 'true'}">
                        <font color="red">Promotion Created Successfully!!</font><br/>
                    </c:if>
                    <form id="contact_form" action="${formAction}" method="POST">

                        <table align="center">
                            <tr>
                                <td>&nbsp</td> 
                                <td>&nbsp</td>                     
                            </tr>
                            <tr>
                                <td align="right">Promotion Type : &nbsp</td>
                                <td align="left">
                                    <select class="form-control" name="type" required="true">
                                        <option value="1">Credit Card</option>
                                        <option value="2">Membership</option>
                                        <option value="3">Ticket Pricing Category</option>
                                        <option value="4">Volume Discount</option>
                                        <option value="5">Bundled Event Promotion</option>
                                        <option value="6">Customize Promotion (Combine Promotion Types)</option>
                                    </select></td>
                            </tr>
                            <tr>
                                <td>&nbsp</td> 
                                <td>&nbsp</td>                     
                            </tr>
                        </table>
                        <div align="center" ><c:url var="formAction" value="/BackController" /><input type="submit" id="button"/></div>
                    </form>

                </div>
            </div>
        </div> 
    </div>
</div>

<jsp:include page="footer.jsp" />


