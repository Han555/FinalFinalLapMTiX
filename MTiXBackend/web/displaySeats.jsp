<%-- 
    Document   : displaySeats
    Created on : 21 Sep, 2016, 10:37:50 PM
    Author     : JingYing
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList, java.util.List" %>
<!DOCTYPE html>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<!doctype html>
<jsp:include page="header2.jsp" />
<!-- Main Content -->


<div class="container-fluid">
    <%
        List<ArrayList> data = (List<ArrayList>) request.getAttribute("data");
        List<ArrayList> price = (List<ArrayList>) request.getAttribute("price");
    %>

    <c:url var="formAction" value="/BackController?action=seatsPriceCreated" />
    <div class="side-body padding-top">

        <div class="row">
            <div class="col-sm-6">
                <div class="header">
                    <h4 class="title">Set Area Pricing</h4>   
                </div>
                <%
                    if (data == null) {
                %>
                <div align="center"><h3>No SESSION Found!</h3></div><br><br>
                <%} else {
                    String x = data.get(0).get(5).toString();
                %>
                <%  if (x.equals("1")) { %>
                <table>
                    <tr>
                        <td>&nbsp; Previous Price : &nbsp;</td>
                        <%  if (x.equals("1")) { %>
                        <td><select class="form-control" required="true">
                                <option>ID | CAT1 | CAT2 | CAT3 | CAT 4 | Seats Option</option>
                                <%
                                    for (int i = 0; i < price.size(); i = i + 4) {
                                %>
                                <option><%=price.get(i).get(0) + " | " + price.get(i).get(2) + " | " + price.get(i + 1).get(2) + " | " + price.get(i + 2).get(2) + " | " + price.get(i + 3).get(2) + " | " + price.get(i).get(3)%></option>
                                <%}%>
                            </select></td> <%} else {%>
                        <td><select class="form-control" required="true">
                                <option>ID | CAT1 | CAT2 | CAT3 | Seats Option</option>
                                <%
                                    for (int i = 0; i < price.size(); i = i + 3) {
                                %>
                                <option><%=price.get(i).get(0) + " | " + price.get(i).get(2) + " | " + price.get(i + 1).get(2) + " | " + price.get(i + 2).get(2) + " | " + price.get(i).get(3)%></option>
                                <%}%>
                            </select></td> <%}%>
                    </tr>
                    <tr>
                        <td>&nbsp</td> 
                    </tr>
                </table>
                <img id="shape1" src="img/property/Concerthall_layout.png" style="width:100%; height: 100%;" usemap="#concertHall" alt="" />
                <map name="concertHall" id="concertHall">
                    <area shape="rect" coords="1078,1078,1080,1080" alt="Image Map" style="outline:none;" title="Image Map"/>
                    <area id="Seat_19" alt="" title="Seat_19" color="green" href="#" shape="poly" coords="546,88,526,96,508,108,502,120,504,136,502,146,492,142,484,126,472,114,460,102,456,86,462,78,484,70,504,62,522,58,550,54,574,56,586,64,602,74,616,96,626,110,614,114" style="outline:none;" target="_self"     />
                    <area id="Seat_17" alt="" title="Seat_17" color="green" href="#" shape="poly" coords="582,248,568,246,550,244,532,248,514,258,502,272,484,274,464,264,466,246,478,238,500,230,516,222,546,218,568,216,588,216,600,224,604,234,602,244,598,252" style="outline:none;" target="_self"     />
                    <area id="Seat_16" alt="" title="Seat_16" color="green" href="#" shape="poly" coords="567,435,557,423,552,403,530,382,511,359,492,332,488,309,495,305,509,312,541,365,564,393,576,417,580,432" style="outline:none;" target="_self"     />
                    <area id="Seat_18" alt="" title="Seat_18" color="green" href="#" shape="poly" coords="674,331,654,313,626,291,616,273,632,266,662,289,704,337,722,365,748,381,726,389,743,391,711,375" style="outline:none;" target="_self"     />
                    <area id="Seat_10" alt="" title="Seat_10" color="green" href="#" shape="poly" coords="499,538,524,525,559,524,576,524,588,505,572,495,541,496,510,494,490,509,476,519,479,528" style="outline:none;" target="_self"     />
                    <area id="Seat_9" alt="" title="Seat_9" color="green" href="#" shape="poly" coords="512,589,504,572,496,558,488,567,495,581,503,593" style="outline:none;" target="_self"     />
                    <area id="Seat_8" alt="" title="Seat_8" color="green" href="#" shape="poly" coords="490,598,481,581,474,566,467,578,468,589,482,605,489,609,496,607" style="outline:none;" target="_self"     />
                    <area id="Seat_12" alt="" title="Seat_12" color="green" href="#" shape="poly" coords="650,542,639,535,625,523,621,517,636,515,653,522,666,540,660,549,656,553" style="outline:none;" target="_self"     />
                    <area id="Seat_11" alt="" title="Seat_11" color="green" href="#" shape="poly" coords="609,549,596,541,591,531,596,518,613,529,631,541,637,554,625,561" style="outline:none;" target="_self"     />
                    <area id="Seat_6" alt="" title="Seat_6" color="green" href="#" shape="poly" coords="526,643,518,634,506,622,493,633,507,643,523,661,534,661,536,655" style="outline:none;" target="_self"     />
                    <area id="Seat_7" alt="" title="Seat_7" color="green" href="#" shape="poly" coords="550,628,534,613,523,614,528,627,544,646,558,658,559,646" style="outline:none;" target="_self"     />
                    <area id="Seat_13" alt="" title="Seat_13" color="green" href="#" shape="poly" coords="678,597,665,580,651,573,641,581,654,595,668,610,676,616,683,609" style="outline:none;" target="_self"     />
                    <area id="Seat_14" alt="" title="Seat_14" color="green" href="#" shape="poly" coords="696,575,682,562,671,561,670,574,688,591,694,601,703,595" style="outline:none;" target="_self"     />
                    <area id="Seat_15" alt="" title="Seat_15" color="green" href="#" shape="poly" coords="705,640,694,627,684,623,681,637,692,649,711,660" style="outline:none;" target="_self"     />
                    <area id="Seat_5" alt="" title="Seat_5" color="green" href="#" shape="poly" coords="582,672,566,661,558,665,563,680,571,695,579,701,586,691" style="outline:none;" target="_self"     />
                    <area id="Seat_3" alt="" title="Seat_3" color="green" href="#" shape="poly" coords="581,843,552,844,537,855,522,862,502,853,477,843,473,829,494,815,522,805,554,797,584,800,601,808,604,821" style="outline:none;" target="_self"     />
                    <area id="Seat_2" alt="" title="Seat_2" color="green" href="#" shape="poly" coords="521,902,512,883,509,869,491,863,476,861,466,866,474,879,480,895,488,910,494,925,514,922,532,923" style="outline:none;" target="_self"     />
                    <area id="Seat_4" alt="" title="Seat_4" color="green" href="#" shape="poly" coords="668,870,652,876,638,866,626,854,610,844,606,830,616,816,626,814,656,834,666,846,676,858" style="outline:none;" target="_self"     />
                    <area id="Seat_1" alt="" title="Seat_1" color="green" href="#" shape="poly" coords="679,923,659,901,638,879,614,871,588,864,567,859,555,873,545,886,542,906,563,942,580,965,588,991,602,995,634,977,671,969,688,963,714,963,721,951" style="outline:none;" target="_self"     />
                    <area id="Seat_21" alt="" title="Seat_21" color="blue" href="#" shape="poly" coords="724,941,704,927,683,901,674,886,691,878,708,898,724,913,734,925" style="outline:none;" target="_self"     />
                    <area id="Seat_20" alt="" title="Seat_20" color="red" href="#" shape="poly" coords="566,990,564,973,553,959,543,942,530,933,518,943,514,952,526,977,537,997,551,1005,565,1006" style="outline:none;" target="_self"     />
                    <area id="Seat_22" alt="" title="Seat_22" href="#" color ="gray" shape="poly" coords="510,590,514,588,508,594,514,602,522,596" style="outline:none;" target="_self"     />
                    <area id="Seat_23" alt="" title="Seat_23" href="#" color ="gray" shape="poly" coords="636,564,644,560,640,552,630,552,630,560" style="outline:none;" target="_self"     />
                    <area id="Seat_24" alt="" title="Seat_24" href="#" color ="gray" shape="poly" coords="498,542,500,534,502,526,510,534,506,544,510,542" style="outline:none;" target="_self"     />
                    <area id="Seat_25" alt="" title="Seat_25" href="#" color ="gray" shape="poly" coords="578,524,576,514,586,510,588,518,584,522" style="outline:none;" target="_self"     />
                    <area id="Seat_26" alt="" title="Seat_26" href="#" color ="gray" shape="poly" coords="488,292,478,280,482,272,492,272,502,278,504,288,498,294" style="outline:none;" target="_self"     />
                    <area id="Seat_27" alt="" title="Seat_27" href="#" color ="gray" shape="poly" coords="600,256,602,246,608,234,618,244,612,258,602,264" style="outline:none;" target="_self"     />
                </map>
                <script lang="javascript">
                    $(document).ready(function () {
                        $('#shape1').mapster({
                            singleSelect: true,
                            mapKey: 'color',
                            fill: true,
                            fillOpacity: 1,
                        });
                    });
                    function setCat1() {
                        $('#Seat_19').mapster('select');
                    }
                    function setCat2() {
                        $('#Seat_20').mapster('select');
                    }
                    function setCat3() {
                        $('#Seat_21').mapster('select');
                    }
                    function setCat4() {
                        $('#Seat_22').mapster('select');
                    }
                </script>
                <%} else {%>
                <table>
                    <tr>
                        <td>&nbsp; Previous Price : &nbsp;</td>
                        <%  if (x.equals("1")) { %>
                        <td><select class="form-control" required="true">
                                <option>ID | CAT1 | CAT2 | CAT3 | CAT 4 | Seats Option</option>
                                <%
                                    for (int i = 0; i < price.size(); i = i + 4) {
                                %>
                                <option><%=price.get(i).get(0) + " | " + price.get(i).get(2) + " | " + price.get(i + 1).get(2) + " | " + price.get(i + 2).get(2) + " | " + price.get(i + 3).get(2) + " | " + price.get(i).get(3)%></option>
                                <%}%>
                            </select></td> <%} else {%>
                        <td><select class="form-control" required="true">
                                <option>ID | CAT1 | CAT2 | CAT3 | Seats Option</option>
                                <%
                                    for (int i = 0; i < price.size(); i = i + 3) {
                                %>
                                <option><%=price.get(i).get(0) + " | " + price.get(i).get(2) + " | " + price.get(i + 1).get(2) + " | " + price.get(i + 2).get(2) + " | " + price.get(i).get(3)%></option>
                                <%}%>
                            </select></td> <%}%>
                    </tr>
                    <tr>
                        <td>&nbsp</td> 
                    </tr>
                </table>
                <img id="shape1" src="img/property/Theatre.png" style="width:100%; height: 100%;" usemap="#theatre" alt="" />
                <map name="theatre" id="theatre">
                    <area shape="rect" coords="766,574,768,576" alt="Image Map" style="outline:none;" title="Image Map" href="http://www.image-maps.com/index.php?aff=mapped_users_0" />
                    <area id="seat_1" alt="" title="seat_1" color="brown" href="#" shape="poly" coords="375,462,348,448,330,426,310,397,322,387,346,379,363,392,379,412,392,423,406,439,406,451" style="outline:none;" target="_self"     />
                    <area id="seat_2" alt="" title="seat_2" color="brown" href="#" shape="poly" coords="412,431,397,415,378,392,369,373,371,356,398,354,421,347,438,356,450,368,471,392,493,413,502,432,477,448,442,451" style="outline:none;" target="_self"     />
                    <area id="seat_3" alt="" title="seat_3" color="brown" href="#" shape="poly" coords="516,422,496,407,480,376,467,349,494,343,511,355,524,370,540,387,554,401,552,415" style="outline:none;" target="_self"     />
                    <area id="seat_4" alt="" title="seat_4" color="blue" href="#" shape="poly" coords="274,325,254,307,242,293,244,283,264,273,280,273,298,285,310,299,314,311" style="outline:none;" target="_self"     />
                    <area id="seat_5" alt="" title="seat_5" color="blue" href="#" shape="poly" coords="356,304,330,309,314,300,304,285,300,273,313,266,344,260,362,258,375,255,386,268,394,283,386,295" style="outline:none;" target="_self"     />
                    <area id="seat_6" alt="" title="seat_6" color="blue" href="#" shape="poly" coords="419,288,408,288,396,275,389,259,382,249,392,242,418,241,430,241,440,257,446,277" style="outline:none;" target="_self"     />
                    <area id="seat_7" alt="" title="seat_7" color="green" href="#" shape="poly" coords="234,269,215,270,203,252,193,242,205,232,227,225,241,229,253,247,258,257" style="outline:none;" target="_self"     />
                    <area id="seat_8" alt="" title="seat_8" color="green" href="#" shape="poly" coords="294,246,272,249,255,238,240,221,252,216,276,209,310,206,320,201,332,221,338,232" style="outline:none;" target="_self"     />
                    <area id="seat_9" alt="" title="seat_9" color="green" href="#" shape="poly" coords="378,231,358,233,344,227,341,212,336,203,354,198,375,196,384,198,390,219" style="outline:none;" target="_self"     />
                </map>
                <script lang="javascript">
                    $(document).ready(function () {
                        $('#shape1').mapster({
                            singleSelect: true,
                            mapKey: 'color',
                            fill: true,
                            fillOpacity: 1,
                        });
                    });
                    function setCat1() {
                        $('#seat_1').mapster('select');
                    }
                    function setCat2() {
                        $('#seat_4').mapster('select');
                    }
                    function setCat3() {
                        $('#seat_7').mapster('select');
                    }</script>
                <%}%></div>
            <div class="col-sm-6">
                <form id="contact_form" action="${formAction}" method="POST">
                    <table align="center">
                        <tr>
                            <td>&nbsp</td> 
                        </tr>
                        <tr>
                            <td>&nbsp</td> 
                        </tr>
                        <tr>
                            <td>&nbsp</td> 
                        </tr>
                        <tr>
                            <td align="right">Apply to all Event/Sub-Event Session : &nbsp;</td>
                            <td align="center"><input type="radio" required="true" name="apply" value="yes">Yes &nbsp;<br></td>
                            <td align="left"><input type="radio" required="true" name="apply" value="no">No<br></td>

                        </tr>
                        <tr>
                            <td>&nbsp</td> 
                        </tr>
                    </table>
                    <table align="center">
                        <tr>
                            <td>Session : &nbsp;</td>
                            <td>
                                <select class="form-control" id="id" name="id" required="true">
                                    <%
                                        for (int i = 0; i < data.size(); i++) {
                                    %>
                                    <option value="<%=data.get(i).get(0)%>"><%=data.get(i).get(0) + " | " + data.get(i).get(1) + " | " + data.get(i).get(3) + " | " + data.get(i).get(4)%></option>
                                    <% } %>
                                </select></td>
                        </tr>
                        <tr>
                            <td>&nbsp</td> 
                        </tr>
                    </table>
                    <table align="center">
                        <%
                            for (int j = 0; j < Integer.valueOf(data.get(0).get(6).toString()); j++) {
                        %>
                        <tr>
                            <td align="right">Category <%=j + 1%> : &nbsp</td>
                            <td align="left"><input type="number" class="form-control" required="true" id="cat<%=j + 1%>" name="cat<%=j + 1%>" onFocus="setCat<%=j + 1%>()"</td> 
                        </tr>
                        <tr>
                            <td>&nbsp</td> 
                        </tr>
                        <%}%>
                        <tr>
                            <td align="right">Seats Option : &nbsp</td>
                            <td><select class="form-control" required="true" id="seatsOption" name="seatsOption">
                                    <option value="Free Seating">Free Seating</option>
                                    <option value="Fix Seating">Fix Seating within Sector</option>
                                </select></td>
                        </tr>
                        <tr>
                            <td>&nbsp</td> 
                        </tr>
                        <tr>
                            <td>&nbsp</td> 
                            <td>&nbsp</td> 
                            <td><input type="submit" value="Submit"/></td>
                        </tr>
                    </table>
                    <input type="hidden" value=<%=data.get(0).get(6)%> name="noCat">
                    <%}%>
                    <script>
                        $("form").submit(function (event) {
                            var i;
                            var j;
                            if ($('#seatsOption').val() == "Free Seating") {
                                for (i = 1; i <= <%=Integer.valueOf(data.get(0).get(6).toString())%>; i++) {
                                    for (j = i + 1; j <= <%=Integer.valueOf(data.get(0).get(6).toString())%>; j++) {
                                        var x = document.getElementById("cat" + i).value;
                                        var y = document.getElementById("cat" + j).value;
                                        if (x !== y) {
                                            alert("For Free seating ticket price must be the same as all category.");
                                            return false;
                                        }
                                    }
                                }
                            }
                        });
                    </script>
                </form>
            </div>
        </div> 
    </div>

</div>

<jsp:include page="footer.jsp" />