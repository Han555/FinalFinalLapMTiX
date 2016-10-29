<%-- 
    Document   : viewEventWebpage
    Created on : 15 Oct, 2016, 8:04:35 PM
    Author     : JingYing
--%>

<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<!doctype html>
<jsp:include page="header2.jsp" />
<!--<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/jqueryui/1.12.1/jquery-ui.css" />
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>-->
<script>
    $(function () {
        $("#tabs").tabs();
    });
</script>

<!-- Main Content -->
<div class="container">
    <%
        ArrayList data = (ArrayList) request.getAttribute("data");
        List<ArrayList> sessions = (List<ArrayList>) request.getAttribute("sessions");
        List<ArrayList> promotions = (List<ArrayList>) request.getAttribute("promotions");
    %>
    <div class="row">
<!--        <div class="col-md-1">
        </div>-->
        <div class="col-lg-8">
            <div style="margin-left:0px"><%=data.get(1)%></div>
        </div><br><br>
    </div>
    <div class="row">
        <!--<div class="col-md-1">
        </div>-->
        <div class="col-md-8" style="background-color:#F1F1F1;-webkit-border-radius: 2px 2px 2px 2px;
border-radius: 2px 2px 2px 2px;">
            <div class="row" style="margin-bottom:20px">
            <div class="col-md-4" style="margin-top:15px"><img src="ContentImageController?id=<%=data.get(3)%>" width="210px" height="210px"></div>
            <div class="col-md-8" style=" width: 300px; max-width: 300px;margin-top:15px"><b>Synopsis</b><br>
                <div class="entry rich_content">
                    <div class="showMore more" style="height:200px;"><%=data.get(2)%></div>
                </div>

            </div></div>
                  <div class="row">
                      
            <div id="tabs">
                <ul>
                    <li><a href="#tabs-1">Admission Rules</a></li>
                    <li><a href="#tabs-2">Program</a></li>
                    <li><a href="#tabs-3">Others</a></li>
                </ul>
                <div id="tabs-1"><p><%=data.get(5)%></p></div>
                <div id="tabs-2"><p><b>Program Details</b></p><p><%=data.get(6)%></p><br>
                    <%if (sessions.size()==0){%>
                    <p><b>Sessions</b></p>
                     <p>No available sessions</p>
                    <%} else {%>
                    <p><b>Sessions</b></p>
                    <% for (int i = 0; i < sessions.size(); i++) {%>
                    <p>Name : <%=sessions.get(i).get(0)%></p>
                    <p>Start Time : <%=sessions.get(i).get(1)%></p>
                    <p>End Time : <%=sessions.get(i).get(2)%></p>
                    <% for (int j = 3; j < sessions.get(i).size(); j += 2) {%>
                    <p>Category <%=sessions.get(i).get(j)%> :  $<%=sessions.get(i).get(j + 1)%></p>
                    <%}%><br><%}}%>
                    
                    
                </div>
                <div id="tabs-3"><p><b>Other Details</b></p><p><%=data.get(4)%></p><br>
                    <p><b>Promotions</b></p>
                    <%if (promotions.size()==0){%>
                     <p>No available promotions</p>
                    <%} else {%>
                    <% for (int i = 0; i < promotions.size(); i++) {%>
                    <p>Promotion Name : <%=promotions.get(i).get(0)%></p>
                    <p>Discount Rate : <%=promotions.get(i).get(1)%></p>
                    <p>Requirements : <%=promotions.get(i).get(2)%></p>
                    <p>Promotion type : <%=promotions.get(i).get(3)%></p><br><%}}%>
                
                </div>
            </div></div>

        </div>
        <div class="col-md-4"> 
            <div class="ui-widget-content" style="width: 250px;background-color:#F1F1F1;margin-bottom:50px;-webkit-border-radius: 2px 2px 2px 2px;
border-radius: 2px 2px 2px 2px;">
              
                <%if (sessions.size()!=0){%>
                <a href="Controller?action=bookTicket&id=<%=data.get(14)%>"><button type="button" class="btn btn-info" style="margin-top:20px;margin-left:25px;width: 200px">Buy Ticket</button></a>
                <%}%>
                <p><br></p>
                <div style="margin-left:20px;margin-bottom:20px" ><b>Event Name</b><br><%=data.get(9)%></div>
                 <div style="margin-left:20px;margin-bottom:20px" ><b>Event Start</b><br><%=data.get(10)%></div>
                 <div style="margin-left:20px;margin-bottom:20px" ><b>Event End</b><br><%=data.get(11)%></div>
                 <div style="margin-left:20px;margin-bottom:20px" ><b>Event Description</b><br><%=data.get(12)%></div>
                 <div style="margin-left:20px;margin-bottom:20px" ><b>Venue  </b><br><%=data.get(13)%></div>

            </div>

        </div></div>
</div>
<jsp:include page="footer.jsp" />
