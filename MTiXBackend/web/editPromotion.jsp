<%-- 
    Document   : editPromotion
    Created on : Sep 20, 2016, 1:07:43 PM
    Author     : Student-ID
--%>

<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<!doctype html>
<jsp:include page="header.jsp" />
<!-- Main Content -->
<!-- Main Content -->
<div class="container-fluid">
    <%
            ArrayList data = (ArrayList) request.getAttribute("data");
            String promotionType = "";
            for (int j = 7; j < data.size();j++){
                            promotionType += data.get(7) + " ";
                        }
    %>
    <c:url var="formAction" value="/BackController?action=promotionEdited" />
    <div class="side-body padding-top">

        <div class="row">
            <div class="col-md-8">
                <div class="card">
                    <div class="header">
                        <h4 class="title">Edit Promotion</h4>   
                    </div>                        


                    <form id="contact_form" action="${formAction}" method="POST"> 
                        <table align="center">
                            <tr>
                                <td>&nbsp</td> 
                            </tr>
                            <tr>
                                <td>&nbsp</td> 
                            </tr>
                            <tr>
                                <td align="right">Name : &nbsp</td>
                                <td align="left"><input type="text" required="true" name="name" class="form-control" value="<%=data.get(3)%>"> </td>  
                                <td>&nbsp</td> <td>&nbsp</td> 
                                <td align="right">Type : &nbsp</td>
                                <td align="left"><input type="text" disabled="true" class="form-control" value="<%=promotionType%>"</td>    
                            </tr>

                            <tr>
                                <td>&nbsp</td> 
                                <td>&nbsp</td>                     
                            </tr>
                            <tr>
                                <td align="right">Requirement : &nbsp</td>
                                <td align="left"><input type="text" required="true" class="form-control" name="requirement" value="<%=data.get(4)%>"</td>     
                                <td>&nbsp</td> <td>&nbsp</td> 
                                <td align="right">Discount Rate(%) : &nbsp</td>
                                <td align="left"><input type="number" min="0" max="100"  required="true" class="form-control" name="discount" value="<%=data.get(5)%>"</td>      
                            </tr>

                            <tr>
                                <td>&nbsp</td> 
                                <td>&nbsp</td>                     
                            </tr>
                            <tr>
                                <td align="right">Description : &nbsp</td>
                                <td align="left"><input type="text" required="true" class="form-control" name="desc" value="<%=data.get(6)%>"</td>       
                            </tr>
                            <tr>
                                <td>&nbsp</td> 
                            </tr>
                            <tr>
                                <td align="left"><input hidden="true" type="text" name="eventId" value="<%=data.get(0)%>"</td>                 
                            </tr>
                            <tr>
                                <td align="left"><input hidden="true" type="text" name="eventType" value="<%=data.get(1)%>"</td>                 
                            </tr>
                            <tr>
                                <td align="left"><input hidden="true" type="text" name="id" value="<%=data.get(2)%>"</td>                 
                            </tr>
                            <tr>
                                <td>&nbsp</td> 
                                <td>&nbsp</td>                     
                            </tr>
                            <tr>
                                <td>&nbsp</td> 
                                <td>&nbsp</td> 
                                <td><c:url var="formAction" value="/BackController" /><input type="submit" value="Submit"/></td>
                            </tr>
                        </table>
                    </form>
                </div>
            </div>
        </div> 
    </div>
</div>

<jsp:include page="footer.jsp" />


