<%-- 
    Document   : deleteWebpage
    Created on : 15 Oct, 2016, 9:37:09 AM
    Author     : JingYing
--%>

<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<!doctype html>
<jsp:include page="header2.jsp" />
<!-- Main Content -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
<script src="http://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.3.0/js/bootstrap-datepicker.js"></script>

<div class="container-fluid">
    <%
        ArrayList data = (ArrayList) request.getAttribute("data");
    %>
    <c:url var="formAction"  value="/ContentBackController?action=webpageDeleted" />
    <div class="side-body padding-top">
        <form id="contact_form" action="${formAction}" method="POST">
            <div class="row">
                <div class="col-md-12">
                    <div class="card">       
                        <div class="header">
                            <h4 class="title">Delete WebPage</h4>   
                        </div>
                        <table align="left">
                            <tr>
                                <td>&nbsp</td>                     
                            </tr>
                            <tr><h5><b>Event Image</b></h5> </tr>
                            <tr>
                            <img id="previewHolder" src="contentImageController?id=<%=data.get(6) %>" width="250px" height="250px"/>
                            </tr>
                        </table>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-md-12">
                    <div class="card">  
                        <br>
                        <table>
                            <tr>
                            <label>Event Title : </label>
                            <input type="text" name="title" disabled="true" required="true" value="<%=data.get(4) %>" class="form-control">
                            </tr><br>
                            <tr>
                            <label>Synopsis : </label>
                            <textarea class="form-control" disabled="true" name="synopsis" cols="1" rows="7" ><%=data.get(5)%></textarea>              
                            </tr><br>
                            <tr>
                            <label>Program Details : </label>
                            <textarea class="form-control" disabled="true" name="programDetails" cols="1" rows="7"><%=data.get(9)%></textarea>              
                            </tr><br>
                            <tr>
                            <label>Admission Rules : </label>
                            <textarea class="form-control" disabled="true" name="rules" cols="1" rows="7"><%=data.get(8)%></textarea>              
                            </tr><br>
                            <tr>
                            <label>Other Details : </label>
                            <textarea class="form-control" disabled="true" name="details" cols="1" rows="7"><%=data.get(7)%></textarea>              
                        </tr><br><tr><td align="right">Start Publish Date : &nbsp</td>
                            <td align="left"><input type="text" disabled="true" class="form-control startdate" id="start" value="<%=data.get(10)%>" required="true" name="date"</td>    
                    <font color="red">From now till <%=data.get(2)%></font><br/>
                    </tr><br>
                    <tr><td align="right">End Publish Date : &nbsp</td>
                        <td align="left"><input type="text" disabled="true" class="form-control enddate" id="end" value="<%=data.get(11)%>" required="true" name="endDate"</td>    
                    <font color="red">From Start Publish Date till <%=data.get(3)%></font><br/>
                    </tr><br>
                    <tr>
                        <td align="center"><input type="submit" value="Delete"/></td>
                        <input type="hidden" value=<%=data.get(1)%> name="type">
                        <input type="hidden" value=<%=data.get(0)%> name="id">
                        <input type="hidden" id="ext" name="ext">
                    </tr>
                </table>
            </div>
        </div>
    </div> 
                        <script>
                              $("form").submit(function (event) {
                             if (confirm("You are about to delete the Webpage for you event. Are you sure about that?")){
                                 return true;
                             } else {
                                 return false;
                             }
                        });
                        </script>
</form>
</div>
</div>


<jsp:include page="footer.jsp" />

