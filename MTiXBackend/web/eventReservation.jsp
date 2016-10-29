<%-- 
    Document   : eventReservation
    Created on : Sep 25, 2016, 12:14:03 PM
    Author     : catherinexiong
--%>


 

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<jsp:useBean id="properties" class="java.util.List<entity.PropertyEntity>" scope="request"/>
<jsp:include page="header.jsp" />

<!-- Main Content -->
<div class="side-body">
    <div class="page-title">
        <span class="title">Event Reservation</span>
    </div>
    <div class="row">
        <div class="col-xs-12">
            <div class="card">
                <div class="card-header">
                    <div class="card-title">
                        <div class="title">View, Update and Delete Event Reservation</div>
                        <div class="description">The table below shows event Reservation group by properties.</div>
                    </div>
                </div>
                <div class="card-body">
                    <div style="padding-bottom: 20px;">
                        <table class="table-hover" cellspacing="0" width="100%">
                            <thead>
                                <tr>
                                    <th>#</th>
                                    <th>Event Name</th>
                                    <th>Event Description</th>
                                    <th>Start</th>
                                    <th>End</th>
                                    <th>Event Organizer</th>
                                    <th>Delete</th>
                                    <th>Update</th>
                                </tr>
                            </thead>
                            <tbody id="items"></tbody>
                        </table>
                    </div>
                   <div class="form-group" style="padding-bottom: 50px;" >
                        <label for="propertyList" class="col-sm-2 control-label">Choose a Property</label>
                        <div class="col-sm-6">
                            <select class="js-example-basic-single js-states" id="propertyList" name="propertyList">
                                <c:forEach items="${properties}" var="properties">
                                    <option value="${properties.id}">${properties.propertyName}</option>
                                </c:forEach>	
                            </select>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>


</div>





<div class="modal fade" id="delete-confirm" role="dialog">
    <div class="modal-dialog">

        <!-- Modal content-->
        <div class="modal-content">
            <div class="modal-body">
                <p>Are you sure to delete?</p>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" id="deleteOk" data-dismiss="modal">Yes</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">No</button>
            </div>
        </div>

    </div>
</div>

<div class="modal fade" id="update-data" role="dialog">
    <div class="modal-dialog">

        <!-- Modal content-->
        <div class="modal-content">
            <div class="modal-body">
                    <div class="form-group" style="padding-bottom: 30px;" >
                        <label for="ename" class="col-sm-2 control-label">Event Name</label>
                        <div class="col-sm-6">
                            <input type="text" id="update-name" class="form-control" name="ename">
                            <div id="alert-empty"></div>
                        </div>
                    </div>
                    <div class="form-group" style="padding-bottom: 30px;" >
                        <label for="edes" class="col-sm-2 control-label">Event Description</label>
                        <div class="col-sm-6">
                            <input type="text" id="update-description" class="form-control" name="edes">
                            <div id="alert-empty"></div>
                        </div>
                    </div><br/>
               
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" id="updateOk">Ok</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">Cancel</button>
            </div>
        </div>

    </div>
</div>

<script>
    var propertyId = 1;
    
    var eId;
    var eList = [];
  
    var msg;
   

    
    
    
    
    

    function deleteEvent(id) {
        eId = id;
        $("#delete-confirm").modal();
    }
    
    $("#deleteOk").click(function() {
        $.ajax({
            url: "DeleteEvent?id=" + eId,
            success:function(result) {
                if (result == "\"success\"") {
                    getEvent();
                }
            }
            
        });
    });
    
    function update(id) {
        eId = id;
        
        console.log(eList);
        var originalname;
        var originaldes;
        for (var i = 0; i < eList.length; i++) {
           if (eList[i].id == eId) {
               originalname = eList[i].name;
               originaldes = eList[i].des;
           }   
        }
        $("#update-name").val(originalname);
        $("#update-description").val(originaldes);
        $("#update-data").modal();
    }
    
    $("#updateOk").click(function() {
        console.log("DAS");
        console.log($("#update-name").val());
        console.log($("#update-description").val());
        $.ajax({
            url: "UpdateEvent?eid=" + eId + "&propertyId=" + propertyId + "&ename=" + $("#update-name").val()+ "&edes=" + $("#update-description").val(),
            success:function(result) {
                if (result == "\"success\"") {
                    getEvent();
                    $("#update-data").modal('hide');
                } else {
                    $("#notifyPeak").html("Errors happened when updating data.").css("color", "red");
                    
                }
            }
            
        });
    });
    
    function getEvent() {
        $.ajax({
            url: "eventList?id=" + propertyId,
            success: function (result) {
                var str = "";
                eList = result;
                for (var i = 0; i < result.length; i++) {
                    console.log(result[i]);
                    str += "<tr><td>" + (i + 1) + "</td><td>" + result[i].name + "</td><td>" + result[i].des + "</td><td>" + result[i].startDate +"</td><td>" + result[i].endDate + "</td><td>" + result[i].email + "</td>";
                    str += '<td><button type="button" class="btn btn-default" onclick="deleteEvent(' + result[i].id + ');">Delete</button></td>';
                    str += '<td><button type="button" class="btn btn-default" onclick="update(' + result[i].id + ');">Update</button></td>';
                    str += '</tr>'
                }
                $('#items').html(str);
            }
        });
    }

    $(document).ready(function () {

       
        getEvent();
        $('#propertyList').change(function () {
            propertyId = $(this).val();
            getEvent();
           
        });
    });
  




</script>
<jsp:include page="footer.jsp" />



