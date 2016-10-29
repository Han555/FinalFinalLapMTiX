<%-- 
    Document   : bookingTickets
    Created on : Oct 9, 2016, 12:04:33 PM
    Author     : catherinexiong
--%>

<%@page import="java.util.LinkedHashMap"%>
<%@page import="java.util.Date"%>
<%@page import="entity.SectionEntity"%>
<%@page import="java.util.Collection"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.Calendar"%>
<%@page import="java.util.HashMap"%>
<%@page import="entity.SessionEntity"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<jsp:useBean id="sections" class="java.util.List<entity.SectionEntity>" scope="request"/>
<jsp:useBean id="promotions" class="java.util.Collection<entity.Promotion>" scope="request"/>
<jsp:include page="header.jsp" />
<link href="css/calendardate.css" rel='stylesheet' type='text/css' />






<% HashMap<Long, List<Double>> map = (HashMap<Long, List<Double>>) request.getAttribute("pricingHashMap"); %>

<div class="container">
    <div class="panel panel-default col-lg-12" style="box-shadow: 0px 3px 10px 1px rgba(119, 119, 119, 0.75);
         -moz-box-shadow: 0px 3px 10px 1px rgba(119, 119, 119, 0.75);
         -webkit-box-shadow: 0px 3px 10px 1px rgba(119, 119, 119, 0.75);">
        <div class="panel-heading" style="height:40px;margin-left: -15px; margin-right: -15px;background: repeating-linear-gradient(
             -45deg,
             transparent,
             transparent,
             #EEE 2px,
             transparent 3px
             );"></div> 
        <div class="col-lg-12">
            <div class="panel panel-default" id="date-time-panel" style="margin-top: 20px;margin-left: -15px;">
                <div class="panel-heading" style=" margin-top:10px;color:white;text-align: center;font-size:20px;background-color:#FE980F;//#EBEDE8;">
                    Step 1: Choose Date & Time
                </div>
                <div class="panel-body">
                    <p style="margin-top: 10px;margin-bottom:10px;text-align: center">Which date and time would you like? </p>
                    <%
                        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MMM/dd/EEE");
                        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");

                        List<SessionEntity> sessions = (List<SessionEntity>) request.getAttribute("sessions");
                        String seatOption = new String();
                        for (SessionEntity s : sessions) {
                            seatOption = s.getSeatOption();
                        }
                        HashMap<String, List<SessionEntity>> dateMap = new LinkedHashMap();
                        for (int i = 0; i < sessions.size(); i++) {
                            String date = dateFormat.format(sessions.get(i).getTimeStart());
                            long HOUR = 3600 * 1000;
                            Date currentD = new Date();
                            Date revisedD = new Date(currentD.getTime() + 2 * HOUR);
                            if ((sessions.get(i).getTimeStart()).after(revisedD)) {
                                if (dateMap.get(date) == null) {
                                    List<SessionEntity> list = new ArrayList();
                                    list.add(sessions.get(i));
                                    dateMap.put(date, list);
                                } else {
                                    dateMap.get(date).add(sessions.get(i));
                                }
                            }
                        }

                        for (Map.Entry<String, List<SessionEntity>> entry : dateMap.entrySet()) { %>
                    <% String dateStr = entry.getKey();%>
                    <time class="date-as-calendar position-pixels">
                        <span class="day"><%=dateStr.substring(9, 11)%></span>
                        <span class="weekday"><%=dateStr.substring(12)%></span>
                        <span class="month"><%=dateStr.substring(5, 8)%></span>
                        <span class="year"><%=dateStr.substring(0, 4)%></span>
                    </time>

                    <div class="arrow_box" style="margin-top:-55px;margin-left: 80px;height:50px;">
                        <% for (SessionEntity s : entry.getValue()) {%>
                        <label class="radio-inline" style="margin-top:15px;margin-left: 25px;width:200px">
                            <input type="radio" name="sessionList" value="<%=s.getId()%>" /><span style="color:white"> <%= timeFormat.format(s.getTimeStart())%> </span>
                        </label>
                        <% } %>
                    </div>

                    <br>
                    <br>
                    <%}
                    %>
                </div>
            </div>
        </div>


        <%if (seatOption.equals("Free Seating")) {%>
        <div class="col-lg-12">
            <div class="panel panel-default" id="free-seating-panel" style="display: none; margin-left:-15px;">
                <div class="panel-heading" style="margin-top:10px;color:white;text-align: center;font-size:20px;background-color:#FE980F;">
                    Step 2: Ticket Type
                </div>
                <div class="panel-body" id="free-seating-panel-body">
                    <div style="text-align:center;color:#515151;padding-top:20px;padding-bottom:20px;padding-left:10px;padding-right:10px">Please confirm your preferred quantity from the different types below and click ADD TO CART to continue.</div>
                    <div style="padding-left:40px;padding-right:40px;">
                        <form id="promotion-form" name="promotion-form">
                            <table class="table table-responsive" id="tablight" >
                                <thead>
                                    <tr></tr>
                                </thead>

                                <tbody id="promotion-items">

                                    <tr>
                                        <td>
                                            <div class="radio">
                                                <label><input type="radio" name="selectedPromotion" value=0>Standard</label>

                                            </div> </td>

                                        <td id="free-standard-price"></td>

                                    </tr>
                                    <c:forEach items="${promotions}" var="promotion">
                                        <tr>
                                            <td><div class="radio">
                                                    <label><input type="radio" name="selectedPromotion" value="${promotion.id}">${promotion.name}</label>


                                                </div> </td>

                                            <td id="free-promotion-price${promotion.id}"></td>



                                        </tr>

                                    </c:forEach>
                                </tbody>
                            </table>
                            <table class="table table-responsive">
                                <thead>
                                    <tr></tr>
                                </thead>
                                <tbody>
                                <div class="form-group" style="padding-left:20px;padding-bottom:20px;">
                                    <tr>
                                        <td> <label for="numOfTickets1" class="col-sm-6 control-label" >Number of Tickets</label> </td>
                                        <td> <select class="js-example-basic-single js-states col-sm-12" style="width:50%;" name="numOfTickets1" id="numOfTickets1" required>


                                            </select> </td></tr>
                                </div>


                                </tbody>

                            </table>
                            <div class="form-group" style="padding-left:20px;padding-bottom:20px;">
                                <button type="button" class="btn btn-info" value="formSubmit" id="cartFree">Add To Cart</button>
                            </div>
                        </form>
                    </div>
                    <div style="text-align:center;color:#515151;font-size:12px;padding-top:20px;padding-bottom:20px;padding-left:10px;padding-right:10px">Note: The ticket prices above exclude a per ticket booking fee of S$3. Prices include GST.</div>
                </div>
            </div>

        </div>

        <%} else {%>      
        <div class="col-lg-12">
            <div class="panel panel-default" id="section-panel" style="display: none; margin-left:-15px;">
                <div class="panel-heading" style="margin-top:10px;color:white;text-align: center;font-size:20px;background-color:#FE980F;">
                    Step 2: Select Section
                </div>
                <div class="panel-body" id="ssection-panel-body">
                    <c:if test="${property.propertyName == 'Merlion Concert Hall'}">

                        <img src="images/property/Concerthall_layout.png" alt="concert_layout" usemap="#image-map" >
                    </c:if>
                    <c:if test="${property.propertyName == 'Merlion Star Theater'}">

                        <img src="images/property/Theatre.png" alt="concert_layout" usemap="#image-map" >
                    </c:if>
                    <map name="image-map">
                        <c:forEach items="${sections}" var="section">
                            <area id="${section.numberInProperty}" class= "p1" href="#"  coords="${section.coords}"  shape="poly"  >
                        </c:forEach>
                    </map>
                    <div class="col-lg-4">
                        <div class="panel panel-default" id="price-div" style="display: none; margin-left:-15px;-moz-border-radius:10px;
                             -webkit-border-radius: 10px;border-radius:10px;width:75%">
                            <div class="panel-heading" id="price-div-heading" style="text-align: center;font-size:20px;">

                            </div>
                            <div class="panel-body" id="price-div-body" style="color:pink">


                            </div>

                        </div>
                    </div>
                </div>
            </div>

        </div>

        <%}%>
        <div class="col-lg-12">
            <div class="panel panel-default" id="free-section-seat-panel" style="display: none; margin-left:-15px;">
                <div class="panel-heading" style="margin-top:10px;color:white;text-align: center;font-size:20px;background-color:#FE980F;">
                    Step 3: Ticket Type
                </div>
                <div class="panel-body" id="free-seating-panel-body">
                    <div style="text-align:center;color:#515151;padding-top:20px;padding-bottom:20px;padding-left:10px;padding-right:10px">Please confirm your preferred quantity from the different types below and click ADD TO CART to continue.</div>
                    <div style="padding-left:40px;padding-right:40px;">
                        <form id="promotion-form" name="promotion-form">
                            <table class="table table-responsive" id="tablight" >
                                <thead>
                                    <tr></tr>
                                </thead>

                                <tbody id="promotion-items">

                                    <tr>
                                        <td>
                                            <div class="radio">
                                                <label><input type="radio" name="selectedPromotion" value=0>Standard</label>

                                            </div> </td>

                                        <td id="free-standard-price"></td>

                                    </tr>
                                    <c:forEach items="${promotions}" var="promotion">
                                        <tr>
                                            <td><div class="radio">
                                                    <label><input type="radio" name="selectedPromotion" value="${promotion.id}">${promotion.name}</label>


                                                </div> </td>

                                            <td id="free-promotion-price${promotion.id}"></td>



                                        </tr>

                                    </c:forEach>
                                </tbody>
                            </table>
                            <table class="table table-responsive">
                                <thead>
                                    <tr></tr>
                                </thead>
                                <tbody>
                                <div class="form-group" style="padding-left:20px;padding-bottom:20px;">
                                    <tr>
                                        <td> <label for="numOfTickets1" class="col-sm-6 control-label" >Number of Tickets</label> </td>
                                        <td> <select class="js-example-basic-single js-states col-sm-12" style="width:50%;" name="numOfTickets1" id="numOfTickets1" required>


                                            </select> </td></tr>
                                </div>


                                </tbody>

                            </table>
                            <div class="form-group" style="padding-left:20px;padding-bottom:20px;">
                                <button type="button" class="btn btn-info" value="formSubmit" id="cartFreeSection">Add To Cart</button>
                            </div>
                        </form>
                    </div>
                    <div style="text-align:center;color:#515151;font-size:12px;padding-top:20px;padding-bottom:20px;padding-left:10px;padding-right:10px">Note: The ticket prices above exclude a per ticket booking fee of S$3. Prices include GST.</div>
                </div>
            </div>

        </div>
    </div>
</div>
<%if (seatOption.equals("Free Seating")) {%>
<div class="modal fade" id="myModal" role="dialog">
    <div class="modal-dialog  modal-lg">

        <!-- Modal content-->
        <div class="modal-content">
            <div class="modal-body">
                <div class="container">
                    <div class="main">
                        <!-- start registration -->
                        <div class="registration">
                            <div class="registration_left" style="margin-left:20px;">
                                <h2>new user? <span> create an account </span></h2>

                                <script>
                                    (function () {

                                        // Create input element for testing
                                        var inputs = document.createElement('input');

                                        // Create the supports object
                                        var supports = {};

                                        supports.autofocus = 'autofocus' in inputs;
                                        supports.required = 'required' in inputs;
                                        supports.placeholder = 'placeholder' in inputs;

                                        // Fallback for autofocus attribute
                                        if (!supports.autofocus) {

                                        }

                                        // Fallback for required attribute
                                        if (!supports.required) {

                                        }

                                        // Fallback for placeholder attribute
                                        if (!supports.placeholder) {

                                        }

                                        // Change text inside send button on submit
                                        var send = document.getElementById('register-submit');
                                        if (send) {
                                            send.onclick = function () {
                                                this.innerHTML = '...Sending';
                                            }
                                        }

                                    })();
                                </script>
                                <div class="registration_form" style="width:60%;">
                                    <!-- Form -->

                                    <form id="regis-form"  method="post">




                                        <div>
                                            <input type="submit" value="create an account" id="register-submit">
                                        </div>
                                        <div class="sky-form" style="margin-left:20px;">
                                            <label class="checkbox"><input type="checkbox" name="checkbox" ><i></i>i agree to&nbsp;<a class="terms" href="#"> terms of service</a> </label>
                                        </div>
                                    </form>
                                    <!-- /Form -->
                                </div>
                            </div>
                            <div class="registration_left" style="margin-left:0;" >
                                <h2>existing user</h2>
                                <div class="registration_form" style="width:50%">
                                    <!-- Form -->
                                    <c:url var="formActionL" value="/Controller?action=promptLogin" />
                                    <form id="login-form" action="${formActionL}" method="post">
                                        <div>
                                            <label>
                                                <input placeholder="email:" type="email" name="userName"  required>
                                            </label>
                                        </div>
                                        <div>
                                            <label>
                                                <input placeholder="password" type="password" name="password"  required>
                                            </label>
                                        </div>	<div id="notifyLoginError"></div>
                                        <div style="display:none"><input type="text" id="promotion-pop2" name="promotion-pop" ></div>
                                        <div style="display:none"><input type="text" id= "numTicket-pop2" name="numTicket-pop" ></div>
                                        <div style="display:none"><input type="text" id= "sessionPop" name="sessionPop" ></div>
                                        <div style="display:none"><input type="text" id= "pricePop" name="pricePop" ></div>
                                        <div>
                                            <input type="button" value="sign in" id="login-submit">
                                        </div>
                                        <div class="forget">
                                            <c:url var="forget" value="/Controller?action=forgetPassword" />
                                            <a href="${forget}">forgot your password</a>
                                        </div>
                                    </form>
                                    <!-- /Form -->
                                </div>
                            </div>
                            <div class="clearfix"></div>
                        </div>
                                         </div>
                                        </div>        
                                    </div>
                                    <div class="modal-footer">

                                        <button type="button" class="btn btn-default" data-dismiss="modal" aria-label="Close">Cancel</button>
                                    </div>
                                </div>

                            </div>
                        <!-- end registration -->
                        <%} else {%>
                        <div class="modal fade" id="myModal" role="dialog">
                            <div class="modal-dialog  modal-lg">

                                <!-- Modal content-->
                                <div class="modal-content">
                                    <div class="modal-body">
                                        <div class="container">
                                            <div class="main">
                                                <!-- start registration -->
                                                <div class="registration">
                                                    <div class="registration_left" style="margin-left:20px;">
                                                        <h2>new user? <span> create an account </span></h2>

                                                        <script>
                                                            (function () {

                                                                // Create input element for testing
                                                                var inputs = document.createElement('input');

                                                                // Create the supports object
                                                                var supports = {};

                                                                supports.autofocus = 'autofocus' in inputs;
                                                                supports.required = 'required' in inputs;
                                                                supports.placeholder = 'placeholder' in inputs;

                                                                // Fallback for autofocus attribute
                                                                if (!supports.autofocus) {

                                                                }

                                                                // Fallback for required attribute
                                                                if (!supports.required) {

                                                                }

                                                                // Fallback for placeholder attribute
                                                                if (!supports.placeholder) {

                                                                }

                                                                // Change text inside send button on submit
                                                                var send = document.getElementById('register-submit');
                                                                if (send) {
                                                                    send.onclick = function () {
                                                                        this.innerHTML = '...Sending';
                                                                    }
                                                                }

                                                            })();
                                                        </script>
                                                        <div class="registration_form" style="width:60%;">
                                                            <!-- Form -->

                                                            <form id="regis-form"  method="post">




                                                                <div>
                                                                    <input type="submit" value="create an account" id="register-submit">
                                                                </div>
                                                                <div class="sky-form" style="margin-left:20px;">
                                                                    <label class="checkbox"><input type="checkbox" name="checkbox" ><i></i>i agree to &nbsp;<a class="terms" href="#"> terms of service</a> </label>
                                                                </div>
                                                            </form>

                                                            <!-- /Form -->
                                                        </div>
                                                    </div>
                                                    <div class="registration_left" style="margin-left:0;" >
                                                        <h2>existing user</h2>
                                                        <div class="registration_form" style="width:50%">
                                                            <!-- Form -->

                                                            <form id="login-form"  method="post">
                                                                <div>
                                                                    <label>
                                                                        <input placeholder="email:" type="email" name="userName"  required>
                                                                    </label>
                                                                </div>
                                                                <div>
                                                                    <label>
                                                                        <input placeholder="password" type="password" name="password"  required>
                                                                    </label>
                                                                </div>	<div id="notifyLoginError"></div>
                                                                <div style="display:none"><input type="text" id="promotion-pop2" name="promotion-pop" ></div>
                                                                <div style="display:none"><input type="text" id= "numTicket-pop2" name="numTicket-pop" ></div>
                                                                <div style="display:none"><input type="text" id= "sessionPop" name="sessionPop" ></div>
                                                                <div style="display:none"><input type="text" id= "pricePop" name="pricePop" ></div>
                                                                <div style="display:none"><input type="text" id= "sectionPop" name="sectionPop" ></div>
                                                                <div>
                                                                    <input type="button" value="sign in" id="login-submit">
                                                                </div>
                                                                <div class="forget">
                                                                    <c:url var="forget" value="/Controller?action=forgetPassword" />
                                                                    <a href="${forget}">forgot your password</a>
                                                                </div>
                                                            </form>
                                                            <!-- /Form -->
                                                        </div>
                                                    </div>
                                                    <div class="clearfix"></div>
                                                </div>


                                            </div>
                                        </div>        
                                    </div>
                                    <div class="modal-footer">

                                        <button type="button" class="btn btn-default" data-dismiss="modal" aria-label="Close">Cancel</button>
                                    </div>
                                </div>

                            </div>
                            <%}%>
                        </div>

                        <script>
                            var closedSections = [];
                            var closedSectionsCapacity = [];
                            var reservedSectionsCapacity = [];
                            var reservedSections = [];
                            var priceList = [];
                            var scroll_offset;
                            var selectedSession;
                            var selectedPromotion;
                            var numOfTickets1;
                            var content;
                            var hoveredId;
                            var category = [];
                            var promotionPrice = [];
                            var remains = [];
                            var currentPrice;
                            var remainCapacityFree;
                            var selectedSection;
                            <c:forEach items="${sections}" var="section">
                            category.push("${section.category.categoryName}");
                            </c:forEach>
                            console.log(category);
                            $("input[name='sessionList']").click(function () {

                                selectedSession = $("input[name='sessionList']:checked").val();
                                $.ajax({
                                    url: "sectionListSpecial?type=closed&id=" + selectedSession,
                                    async: false,
                                    success: function (result) {
                                        for (var i = 0; i < result.length; i++) {
                                            closedSections.push(result[i].numberInProperty);
                                            closedSectionsCapacity.push(result[i].capacity);
                                        }
                                    }
                                });
                                console.log("=====closed ajax " + closedSections);
                                console.log(closedSectionsCapacity);
                                $.ajax({
                                    url: "sectionListSpecial?type=reserved&id=" + selectedSession,
                                    async: false,
                                    success: function (result) {
                                        for (var i = 0; i < result.length; i++) {
                                            reservedSections.push(result[i].numberInProperty);
                                            reservedSectionsCapacity.push(result[i].capacity);
                                        }

                                    }
                                });
                                console.log("=====reserved ajax " + closedSections);
                                console.log(closedSectionsCapacity);

                                $.ajax({
                                    url: "sessionPriceList?type=${type}&id=" + selectedSession,
                                    async: false,
                                    success: function (result) {
                                        priceList = result;
                                        console.log("=====sessionPriceList ajax " + priceList);
                                    }
                                });

                                $.ajax({
                                    url: "CheckTicketSalesController?type=${type}&id=" + selectedSession,
                                    async: false,
                                    success: function (result) {
                                        remains = result;
                                        console.log(remains);
                                    }
                                });
                            <%if (seatOption.equals("Free Seating")) {%>
                                remainCapacityFree = 0;
                                for (var i = 0; i < remains.length; i++) {

                                    remainCapacityFree += remains[i];
                                }
                                for (var i = 0; i < closedSectionsCapacity.length; i++) {
                                    remainCapacityFree -= closedSectionsCapacity[i];
                                }
                                for (var i = 0; i < reservedSectionsCapacity.length; i++) {
                                    remainCapacityFree -= reservedSectionsCapacity[i];

                                }
                                console.log("=====remainCapacityFree " + remainCapacityFree);

                            <%}%>


                            <%if (seatOption.equals("Free Seating")) {%>
                                $("#free-seating-panel").show();

                                scroll_offset = $("#free-seating-panel").offset();
                                //console.log("offset", scroll_offset);
                                $("html,body").animate({
                                    scrollTop: scroll_offset.top
                                }, 2000);
                                promotionPrice.push(0 + '$' + priceList[0]);
                                $("#free-standard-price").html('$' + priceList[0]);
                                console.log($("#free-standard-price").val());
                            <c:forEach items="${promotions}" var="promotion">
                                promotionPrice.push(${promotion.id} + '$' + priceList[0] * (100 -${promotion.discountRate}) / 100);

                                $("#free-promotion-price${promotion.id}").html('$' + priceList[0] * (100 -${promotion.discountRate}) / 100);
                            </c:forEach>


                            <% } else {%>



                                $("#section-panel").show();
                                scroll_offset = $("#section-panel").offset();
                                //console.log("offset", scroll_offset);
                                $("html,body").animate({
                                    scrollTop: scroll_offset.top
                                }, 2000);

                            <%}%>
                            });


                            $("input[name='selectedPromotion']").click(function () {
                                selectedPromotion = $("input[name='selectedPromotion']:checked").val();
                                console.log(selectedPromotion);
                                $("#numOfTickets1").select2("val", " ");

                            <%if (seatOption.equals("Free Seating")) {%>
                                if (selectedPromotion == 0) {
                                    if (remainCapacityFree < 10) {
                                        var str = "<option></option>";
                                        for (var i = 1; i <= remainCapacityFree; i++) {
                                            str += "<option value=" + i + ">" + i + "</option>";

                                        }

                                    } else {
                                        var str = "<option></option>";
                                        for (var i = 1; i <= 10; i++) {
                                            str += "<option value=" + i + ">" + i + "</option>";

                                        }
                                    }
                                } else {
                            <c:forEach items="${promotions}" var="promotion">
                                    if (selectedPromotion ==${promotion.id}) {
                                        if (isPositiveInteger("${promotion.requirements}")) {
                                            var str = "<option></option><option value= ${promotion.requirements} > ${promotion.requirements} </option>"


                                        }

                                        else {
                                            if (remainCapacityFree < 10) {
                                                var str = "<option></option>";
                                                for (var i = 1; i <= remainCapacityFree; i++) {
                                                    str += "<option value=" + i + ">" + i + "</option>";

                                                }

                                            } else {
                                                var str = "<option></option>";
                                                for (var i = 1; i <= 10; i++) {
                                                    str += "<option value=" + i + ">" + i + "</option>";

                                                }
                                            }

                                        }

                                    }
                            </c:forEach>
                                }
                                $('#numOfTickets1').html(str);
                            <%} else {%>
                                if (selectedPromotion == 0) {
                                    if (remains[selectedSection - 1] < 10) {
                                        var str = "<option></option>";
                                        for (var i = 1; i <= remains[selectedSection - 1]; i++) {
                                            str += "<option value=" + i + ">" + i + "</option>";

                                        }

                                    } else {
                                        var str = "<option></option>";
                                        for (var i = 1; i <= 10; i++) {
                                            str += "<option value=" + i + ">" + i + "</option>";

                                        }
                                    }
                                } else {
                            <c:forEach items="${promotions}" var="promotion">
                                    if (selectedPromotion ==${promotion.id}) {
                                        if (isPositiveInteger("${promotion.requirements}")) {
                                            var str = "<option></option><option value= ${promotion.requirements} > ${promotion.requirements} </option>"


                                        }

                                        else {
                                            if (remains[selectedSection - 1] < 10) {
                                                var str = "<option></option>";
                                                for (var i = 1; i <= remains[selectedSection - 1]; i++) {
                                                    str += "<option value=" + i + ">" + i + "</option>";

                                                }

                                            } else {
                                                var str = "<option></option>";
                                                for (var i = 1; i <= 10; i++) {
                                                    str += "<option value=" + i + ">" + i + "</option>";

                                                }
                                            }

                                        }

                                    }
                            </c:forEach>
                                }
                                $('#numOfTickets1').html(str);
                            <%}%>

                            });




                            $('#numOfTickets1').change(function () {
                                numOfTickets1 = $(this).val();
                                console.log("=====get Select2 Tickets " + numOfTickets1);
                                for (var i = 0; i < promotionPrice.length; i++) {
                                    var priceSplit = promotionPrice[i].split("$");
                                    console.log("====Split the Price " + priceSplit[0] + priceSplit[1]);
                                    if (selectedPromotion == priceSplit[0]) {
                                        currentPrice = priceSplit[1];
                                    }
                                }

                            });



                            $("#tablight tr:gt(0)").click(function () //获取第2行及以后的 
                            {
                                $(this).addClass("selectedP").siblings().removeClass("selectedP").end().find(":radio").attr("checked", true);
                            });


                            function isPositiveInteger(n) {
                                return 0 === n % (!isNaN(parseFloat(n)) && 0 < ~~n);
                            }
                            <%if (seatOption.equals("Free Seating")) {%>
                            $("#cartFree").click(function () {
                                $("#promotion-pop2").val(selectedPromotion);
                                $("#numTicket-pop2").val(numOfTickets1);
                                $("#sessionPop").val(selectedSession);
                                $("#pricePop").val(currentPrice);
                                console.log($("#promotion-pop2").val());
                                console.log($("#numTicket-pop2").val());
                                console.log($("#sessionPop").val());
                                console.log($("#pricePop").val());
                            <% String username = (String) session.getAttribute("username");
                                if (username == null) { %>
                                $("#myModal").modal();


                            <%} else {%>
                            <c:url var="addToCartSuccess" value="Controller?action=addToCartSuccess"/>
                                $.ajax({
                                    url: "AddToCartController?username=<%=username%>&sid=" + selectedSession + "&pid=" + selectedPromotion + "&numT=" + numOfTickets1 + "&price=" + currentPrice,
                                    success: function (result) {
                                        if (result = "\"success\"") {

                                            window.location.href = "${addToCartSuccess}";
                                        }
                                    }
                                });

                            <%}%>

                            });
                            <%} else {%>
                            $("#cartFreeSection").click(function () {
                                $("#promotion-pop2").val(selectedPromotion);
                                $("#numTicket-pop2").val(numOfTickets1);
                                $("#sessionPop").val(selectedSession);
                                $("#pricePop").val(currentPrice);
                                $("#sectionPop").val(selectedSection);
                                console.log($("#promotion-pop2").val());
                                console.log($("#numTicket-pop2").val());
                                console.log($("#sessionPop").val());
                                console.log($("#pricePop").val());
                                console.log($("#sectionPop").val());
                            <% String username = (String) session.getAttribute("username");
                                if (username == null) { %>
                                $("#myModal").modal();


                            <%} else {%>
                            <c:url var="addToCartSuccessSection" value="Controller?action=addToCartSuccessSection"/>
                                $.ajax({
                                    url: "AddToCartSectionController?username=<%=username%>&sid=" + selectedSession + "&pid=" + selectedPromotion + "&numT=" + numOfTickets1 + "&price=" + currentPrice + "&section=" + selectedSection,
                                    success: function (result) {
                                        if (result = "\"success\"") {

                                            window.location.href = "${addToCartSuccessSection}";
                                        }
                                    }
                                });

                            <%}%>

                            });

                            <%}%>

                            $("#login-submit").click(function () {
                                console.log("===login-submit: " + currentPrice);
                            <c:url var="loginSuccess" value="Controller?action=loginSuccess"/>
                            <c:url var="shopCart" value="Controller?action=shopCart"/>
                                $.ajax({
                                    type: "POST",
                                    url: "promptLoginCheckController",
                                    data: $('#login-form').serialize(),
                                    success: function (result) {
                                        console.log(result);
                                        if (result == "\"mismatch\"") {

                                            $("#notifyLoginError").html("Email and Password Mismatch.").css("color", "red");
                                        } else if (result == "\"nouser\"") {
                                            $("#notifyLoginError").html("Username is not Exist.").css("color", "red");
                                        } else if (result == "\"successadd\"") {
                                            window.location.href = "${shopCart}";
                                        } else if (result == "\"failtoadd\"") {
                                            window.location.href = "${loginSuccess}";
                                        }
                                    }
                                });
                            });
                            $(".p1").mouseover(function (e) {
                                hoveredId = this.id;
                                console.log(hoveredId);
                                var available = true;
                                //console.log(closedSections);
                                for (var i = 0; i < closedSections.length; i++) {
                                    //console.log("===" + closedSections[i]);
                                    if (hoveredId == closedSections[i]) {
                                        available = false;
                                    }
                                }

                                console.log(reservedSections);
                                for (var i = 0; i < reservedSections.length; i++) {
                                    if (hoveredId == reservedSections[i]) {
                                        available = false;
                                    }

                                }

                                $("#price-div").show();
                                $("#price-div").offset({left: e.pageX, top: e.pageY});
                                $("#price-div-heading").html("Section " + hoveredId);
                                console.log(available);
                                if (available == true) {


                                    $("#price-div-body").html("<div><div style='float:left;color:grey;'>" + category[hoveredId - 1] + "</div><div style='color:grey;text-align:right;'>$" + priceList[hoveredId - 1] + "</div></div>" + "<br />" + "<div style='color:black;text-align:center'>Available</div>");
                                } else {

                                    $("#price-div-body").html("<div><div style='float:left;color:grey;'>" + category[hoveredId - 1] + "</div><div style='color:grey;text-align:right;'>$" + priceList[hoveredId - 1] + "</div></div>" + "<br />" + "<div style='color:red;text-align:center' >Unavailable</div>");
                                }

                            });
                            $(".p1").mouseout(function () {
                                hoveredId = this.id;
                                console.log(hoveredId);
                                $("#price-div").hide();
                            });
                            $(".p1").click(function () {
                                selectedSection = this.id;
                                for (var i = 0; i < closedSections.length; i++) {
                                    if (selectedSection == closedSections[i])
                                        return false;
                                }

                                for (var i = 0; i < reservedSections.length; i++) {
                                    if (selectedSection == reservedSections[i])
                                        return false;
                                }

                                $("#free-section-seat-panel").show();

                                scroll_offset = $("#free-section-seat-panel").offset();
                                //console.log("offset", scroll_offset);
                                $("html,body").animate({
                                    scrollTop: scroll_offset.top
                                }, 2000);
                                promotionPrice.push(0 + '$' + priceList[0]);
                                $("#free-standard-price").html('$' + priceList[0]);
                                console.log($("#free-standard-price").val());
                            <c:forEach items="${promotions}" var="promotion">
                                promotionPrice.push(${promotion.id} + '$' + priceList[0] * (100 -${promotion.discountRate}) / 100);

                                $("#free-promotion-price${promotion.id}").html('$' + priceList[0] * (100 -${promotion.discountRate}) / 100);
                            </c:forEach>

                        //        $("#seat-panel").show();
                        //        scroll_offset = $("#seat-panel").offset();
                        //        //console.log("offset", scroll_offset);
                        //        $("html,body").animate({
                        //            scrollTop: scroll_offset.top
                        //        }, 2000);
                            });
                            $(document).ready(function () {
                                $('#numOfTickets1').select2({placeholder: 'Select # of Tickets Needed'});
                            });


                        </script>
                        <jsp:include page="footer.jsp" />
