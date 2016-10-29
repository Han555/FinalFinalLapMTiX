<%-- 
    Document   : home
    Created on : Aug 28, 2016, 12:55:08 PM
    Author     : Student-ID
--%>

<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<!doctype html>
<jsp:include page="header2.jsp" />
<!--<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>-->
<style>
    .carousel-inner > .item > img,
    .carousel-inner > .item > a > img {
        width: 300px;
        margin: auto;
        height: 225px;
        max-height: 225px;
    }
</style>

<!-- Main Content -->
<div class="container">
    <%
        List<ArrayList> data = (List<ArrayList>) request.getAttribute("data");
    %>
    <div class="row">
        <div class="col-lg-12">
            <div id="myCarousel" class="carousel slide" data-ride>
                <!-- Indicators -->
                <ol class="carousel-indicators">
                    <li data-target="#myCarousel" data-slide-to="0" class="active"></li>
                        <% for (int i = 1; i <= data.size(); i++) {%>
                    <li data-target="#myCarousel" data-slide-to="<%=i%>" class="active"></li>
                        <%}%>
                </ol>

                <!-- Wrapper for slides -->
                <div class="carousel-inner" role="listbox">
                    <div class="item active">
                        <img src="images/Content/MTiXWeb.png" width="300px" alt="Flower" height="300px">
                    </div>

                    <% for (int i = 1; i <= data.size(); i++) {%>
                    <div class="item">
                        <a href="ContentController?action=viewEventWebpage&id=<%=data.get(i - 1).get(0)%>"><img src="ContentImageController?id=<%=data.get(i - 1).get(2)%>" width="150px" height="150px"></a>
                        <div class="carousel-caption">
                            <h3><%=data.get(i - 1).get(1)%></h3>
                        </div>
                    </div>

                    <%}%>
                </div>

                <!-- Left and right controls -->
                <a class="left carousel-control" href="#myCarousel" role="button" data-slide="prev">
                    <span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
                    <span class="sr-only">Previous</span>
                </a>
                <a class="right carousel-control" href="#myCarousel" role="button" data-slide="next">
                    <span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
                    <span class="sr-only">Next</span>
                </a>
            </div>
        </div>

    </div>
    <div class="row">
        <table>
            <tr>
                <td>&nbsp; &nbsp;</td>
                <td>&nbsp; &nbsp;</td>
            </tr>
            <tr>
                <td>&nbsp; &nbsp;</td>
                <td>&nbsp; &nbsp;</td>
            </tr>
        </table>
    </div>
    <div class="row">
        <div class="col-md-3 ">
            <div class="header">
                <h5 class="title">Promotions</h5> 
            </div>
            <div class="image">
                <a href="ContentController?action=creditCardPromotion"><img src="/MTiX-war/images/Content/creditcards.jpg" width="150px" height="150px"></a>
                <h6>Credit Card Promotion</h6></div>
            <div class="image">
                <a href="ContentController?action=volumeDiscountPromotion"><img src="images/Content/volume_discount.png" width="150px" height="150px"></a>
                <h6>Volume Discount Promotion</h6></div>   
        </div>
        <div class="col-md-6 ">
            <div class="card">
                <div class="header">
                    <h5 class="title" >Events</h5> 
                </div>
                <div id ="eventList">

                </div>
            </div>
        </div>
        <div class="col-md-3 ">
            <table>
                <tr>
                    <td align="right">Event Type : &nbsp</td>
                    <td><select class="form-control" onchange="myFunction()" required="true" id="eventType" name="eventType">
                            <option value="All">All</option>
                            <option value="concert">Concert</option>
                            <option value="dance">Dance</option>
                            <option value="sports">Sports</option>
                        </select></td>
                </tr>
<!--                <tr>
                    <div class="image">
                <a href="ContentController?action=volumeDiscountPromotion"><img src="images/Content/About-Us.png" width="150px" height="150px"></a>
                <h6>Volume Discount Promotion</h6></div> 
                </tr>-->
            </table>
        </div>
    </div>

</div>
<script>

    $(document).ready(function () {
        var eventList = [];
        var no = 3;
        var str = "";
        var x = $('#eventType').val();
        $.ajax({
            url: "ContentEventTypeController?type=All",
            success: function (result) {
                eventList = result;
                for (var i = 1; i <= result.length; i++) {
                    str += "<div class=\"card-body \"> <div class=\"col-md-4 \"><div class=\"image\">";
                    str += "<a href=\"ContentController?action=viewEventWebpage&id=" + result[i - 1].id + "\"><img src=\"ContentImageController?id=" + result[i - 1].fileName + "\" width=\"150px\" height=\"150px\"></a>";
                    str += "<h6>" + result[i - 1].eventTitle + "</h6></div></div>";
                    if (i % no == 0) {
                        str += "</div><div class=\"card-body \">"
                    }
                }
                str += "</div>"
                $('#eventList').html(str);
            }
        });
    });

    function myFunction() {
        var eventList = [];
        var no = 3;
        var str = "";
        var x = $('#eventType').val();
        $.ajax({
            url: "ContentEventTypeController?type=" + x,
            success: function (result) {
                eventList = result;
                for (var i = 1; i <= result.length; i++) {
                    str += "<div class=\"card-body \"> <div class=\"col-md-4 \"><div class=\"image\">";
                    str += "<a href=\"ContentController?action=viewEventWebpage&id=" + result[i - 1].id + "\"><img src=\"ContentImageController?id=" + result[i - 1].fileName + "\" width=\"150px\" height=\"150px\"></a>";
                    str += "<h6>" + result[i - 1].eventTitle + "</h6></div></div>";
                    if (i % no == 0) {
                        str += "</div><div class=\"card-body \">"
                    }
                }
                str += "</div>"
                $('#eventList').html(str);
            }
        });
    }
    ;</script>

<jsp:include page="footer.jsp" />
