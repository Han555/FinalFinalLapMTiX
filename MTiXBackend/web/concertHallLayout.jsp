<%-- 
    Document   : concertHallLayout
    Created on : Sep 20, 2016, 4:32:07 PM
    Author     : catherinexiong
--%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<jsp:useBean id="sections" class="java.util.List<entity.SectionEntity>" scope="request"/>
<jsp:include page="header.jsp" />
<!-- Main Content -->

<div class="side-body">
    <div class="container">
        <div class="page-title">
            <span class="title" style="font-size: 40px;color: #22A7F0">Merlion Concert Hall</span>
        </div>
        <div class="row">
            <img src="../MTiX-war/images/property/Concerthall_layout.png" alt="concert_layout" usemap="#image-map" >
            <map name="image-map">
                <c:forEach items="${sections}" var="section">
                <area data-toggle="modal" data-target="#myModal" id="${section.numberInProperty}" class= "p1" alt="section${section.numberInProperty}" title="section${section.numberInProperty}" href="#" coords="${section.coords}" shape="poly">
                </c:forEach>
            </map>
        </div>
    </div>
</div>
<script>
var id = 0;
    $(".p1").click(function () {
        
        id = $(this).attr('id');
       
        $(".modal-header #myModalLabel").text('Detailed Seats Arrangement #' + id);
        $("#popup").html("LOADING...");
        $("#popup").html('<iframe id="p1frame" class="embed-responsive-item" frameborder="0" src="http://localhost:8080/MTiXBackend/seat.jsp?id=' + id + '"></iframe>');
        
        //console.log('"http://localhost:8080/MTiX-war/seat.jsp?id=' + id + '"');
    });
</script>
<div class="modal fade modal-primary" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="myModalLabel">Detailed seats</h4>
            </div>
            <div class="modal-body">
                <div class="embed-responsive embed-responsive-16by9" id="popup">
                    <!---
                    <iframe id="p1frame" class="embed-responsive-item" frameborder="0" src="http://localhost:8080/MTiXBackend/seat.jsp?id="></iframe>
                    -->
                </div>


                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                    <button type="button" class="btn btn-primary">OK</button>
                </div>
            </div>
        </div>
    </div>
 

    <jsp:include page="footer.jsp" />