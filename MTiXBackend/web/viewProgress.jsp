<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList, java.util.List" %>
<!DOCTYPE html>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<!doctype html>
<jsp:include page="header2.jsp" />
<!-- Main Content -->
<!-- load jquery ui library -->
<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/jqueryui/1.12.1/jquery-ui.js"></script>

<script src="js/bootstrap-collapse.js" type="text/javascript"></script>
<script src="js/bootstrap-transition.js" type="text/javascript"></script>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/jqueryui/1.12.1/jquery-ui.css" />
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<script>
    $(function () {
        $("#accordion").accordion({
            collapsible: true
        });
    });

    $(function () {
        $("#accordion1").accordion({
            collapsible: true
        });
    });

    $(function () {
        $("#dialog").dialog();
    });
</script>


<div class="container-fluid">
    <%
        List<ArrayList> data = (List<ArrayList>) request.getAttribute("data");
        List<ArrayList> sessions = (List<ArrayList>) request.getAttribute("sessions");
        List<ArrayList> sales = (List<ArrayList>) request.getAttribute("sales");
        int totalTickets = 0;
        int totalSoldTickets = 0;
    %>
    <div class="side-body padding-top">
        <div class="row">
             <div><h2><b>Sales Details</b></h2></div>
            <div class="col-md-8">
                <div id="accordion1">
                    <%        for (int i = 0; i < data.size(); i++) {
                    %>
                    <h3><%=data.get(i).get(1)%> Event Sales Details</h3>
                    <div>
                        <p>Event Type : <%=data.get(i).get(4)%></p>
                        <p>Start Date : <%=data.get(i).get(2)%></p>
                        <p>End Date : <%=data.get(i).get(3)%></p>
                        <p>Property Name : <%=data.get(i).get(5)%></p>
                        <p>No of Category : <%=data.get(i).get(6)%></p>
                        <p>Promotions : <%=data.get(i).get(7)%></p>
                        <br>
                        <p><b>Session Sales Details</b></p> <br>
                        <%
                            for (int j = 0; j < sales.size(); j++) { //Different Sessions
                                if (sales.get(j).get(1).toString().equals(data.get(i).get(4).toString())
                                        && sales.get(j).get(0).toString().equals(data.get(i).get(0).toString())) {%>
                        <p><b>Session Name : <%=sales.get(j).get(3)%>  Session ID = <%=sales.get(j).get(2)%></b></p>

                        <%
                            for (int k = 4; k < sales.get(j).size(); k += 4) {
                                totalTickets += Integer.valueOf(sales.get(j).get(k + 1).toString());
                                totalSoldTickets += Integer.valueOf(sales.get(j).get(k).toString());
                        %>
                        <p>Category No <%= sales.get(j).get(k + 3)%></p>
                        <p><font color="red">Number of Tickets: <%= sales.get(j).get(k + 1)%>  |  Number of Sold Tickets: <%= sales.get(j).get(k)%></font></p>
                        <div class="progress">
                            <div class="progress-bar progress-bar-success" role="progressbar" aria-valuenow="<%= sales.get(j).get(k + 2)%>"
                                 aria-valuemin="0" aria-valuemax="100" style="width:<%= sales.get(j).get(k + 2)%>%">
                            </div></div>
                            <%} %>
                        <br>
                            <%}
                                }%>
                    </div>
                    <%}%>
                </div>
            </div>
            <div class="col-md-2 col-sm-2 col-xs-3">
            <div class="card">
                <div class="card-body">
                    <span class="count_top"><i class="fa fa-ticket fa-fw"></i> No of Tickets</span>
                    <div class="content">
                        <div align="center" class="value"><h2><b><%= totalTickets%></b></h2></div>
                         <div align="center" class="value"><h6>Include RESERVED section tickets. Exclude CLOSED section tickets</h6></div>
                    </div>
                    <div class="clear-both"></div>
                </div>
            </div><br><br>
            <div class="card">
                <div class="card-body">
                    <span class="count_top"><i class="fa fa-shopping-cart fa-fw"></i> No of Sold Tickets</span>
                    <div class="content">
                        <div align="center" class="value"><h2><b><%=totalSoldTickets%></b></h2></div>
                    </div>
                    <div class="clear-both"></div>
                </div>
            </div>
        </div>
    </div>
    <div class="row">
        <div><h2><b>Event Details</b></h2></div>
        <div class="col-md-8">
            <div id="accordion">
                <%        for (int i = 0; i < data.size(); i++) {
                %>
                <h3><%=data.get(i).get(1)%> Event Details</h3>
                <div>
                    <p>Event Type : <%=data.get(i).get(4)%></p>
                    <p>Start Date : <%=data.get(i).get(2)%></p>
                    <p>End Date : <%=data.get(i).get(3)%></p>
                    <p>Property Name : <%=data.get(i).get(5)%></p>
                    <p>No of Category : <%=data.get(i).get(6)%></p>
                    <p>Promotions : <%=data.get(i).get(7)%></p>
                    <br>
                    <p><b>Sessions</b></p>
                    <table id="table" class="table table-hover">
                        <thead>
                            <tr>
                                <th data-field="end">Session No.</th>
                                <th data-field="name">Name</th>
                                <th data-field="description">Description</th>
                                <th data-field="start">Start</th>
                                <th data-field="end">End</th>
                                <th data-field="price">Ticket Prices</th>
                            </tr>
                        </thead>
                        <tbody>
                            <%
                                for (int j = 0; j < sessions.size(); j++) {
                                    if (sessions.get(j).get(1).toString().equals(data.get(i).get(4).toString())
                                            && sessions.get(j).get(0).toString().equals(data.get(i).get(0).toString())) {
                            %>
                            <tr>
                                <td><%= sessions.get(j).get(8)%></td>
                                <td><%= sessions.get(j).get(4)%></td>
                                <td><%= sessions.get(j).get(5)%></td>
                                <td><%= sessions.get(j).get(6)%></td>
                                <td><%= sessions.get(j).get(7)%></td>
                                <td><%= sessions.get(j).get(9)%></td>
                            </tr>
                            <% }
                                }%>
                        </tbody>
                    </table><br>
                    <p><b>Alerts</b></p>
                    <table id="table" class="table table-hover">
                        <thead>
                            <tr>
                                <th data-field="end">Session No.</th>
                                <th data-field="name">Alert Type</th>
                                <th data-field="description">Below Sales (%)</th>
                                <th data-field="description">In-Charged Person Email</th>
                                <th data-field="start">Start</th>
                                <th data-field="end">End</th>
                            </tr>
                        </thead>
                        <tbody>
                            <%
                                for (int j = 0; j < sessions.size(); j++) {
                                    if (sessions.get(j).get(1).toString().equals(data.get(i).get(4).toString())
                                            && sessions.get(j).get(0).toString().equals(data.get(i).get(0).toString())
                                            && sessions.get(j).size() == 15) {
                            %>
                            <tr>
                                <td><%= sessions.get(j).get(8)%></td>
                                <td><%= sessions.get(j).get(10)%></td>
                                <td><%= sessions.get(j).get(11)%></td>
                                <td><%= sessions.get(j).get(12)%></td>
                                <td><%= sessions.get(j).get(13)%></td>
                                <td><%= sessions.get(j).get(14)%></td>
                            </tr>
                            <% }
                                }%>
                        </tbody>
                    </table>
                </div>
                <%}%>
            </div>
        </div>
        <div class="col-md-2 col-sm-2 col-xs-3">
            <div class="card">
                <div class="card-body">
                    <span class="count_top"><i class="fa fa-group"></i>  No of Events</span>
                    <div class="content">
                        <div align="center" class="value"><h2><b><%=data.size()%></b></h2></div>
                    </div>
                    <div class="clear-both"></div>
                </div>
            </div><br><br>
            <div class="card">
                <div class="card-body">
                    <span class="count_top"><i class="fa fa-child"></i> No of Sessions</span>
                    <div class="content">
                        <div align="center" class="value"><h2><b><%=sessions.size()%></b></h2></div>
                    </div>
                    <div class="clear-both"></div>
                </div>
            </div>
        </div>
    </div>
</div>
</div>

<jsp:include page="footer.jsp" />