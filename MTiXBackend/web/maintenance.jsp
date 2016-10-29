<%-- 
    Document   : maintenance
    Created on : Sep 20, 2016, 5:02:22 PM
    Author     : Student-ID
--%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<jsp:useBean id="properties" class="java.util.List<entity.PropertyEntity>" scope="request"/>
<jsp:include page="header.jsp" />

<!-- Main Content -->
<div class="side-body">
    <div class="page-title">
        <span class="title">Maintenance Schedule</span>
    </div>
    <div class="row">
        <div class="col-xs-12">
            <div class="card">
                <div class="card-header">
                    <div class="card-title">
                        <div class="title">Create, View, Update and Delete Maintenance Schedules</div>
                        <div class="description">The table below shows maintenance schedules group by properties.</div>
                    </div>
                </div>
                <div class="card-body">
                    <div style="padding-bottom: 20px;">
                        <table class="table-hover" cellspacing="0" width="100%">
                            <thead>
                                <tr>
                                    <th>No.</th>
                                    <th>Start Date</th>
                                    <th>End Date</th>
                                    <th>Delete</th>
                                    <th>Update</th>
                                </tr>
                            </thead>
                            <tbody id="items"></tbody>
                        </table>
                    </div>
                    <div class="sub-title">Please Fill in the Form below <span class="description">( To Create New Maintenance Schedule )</span>  </div>
                   <form class="form-horizontal" id="maintenanceForm" name="maintenanceForm"> 
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
                    
                    <div class="form-group" style="padding-bottom: 30px;" >
                        <label for="config-demo" class="col-sm-2 control-label">Choose a date range</label>
                        <div class="col-sm-6">
                            <input type="text" id="new-range" class="form-control" name="mdaterange">
                            <i class="glyphicon glyphicon-calendar fa fa-calendar"></i><div id="alert-empty"></div>
                        </div>
                    </div>
                    <div class="form-group" style="padding-bottom: 60px;">
                        <div class="col-sm-offset-2 col-sm-10">
                            <button type="button" class="btn btn-default" value="msubmit" id="msubmit">Create New Maintenance Schedule</button>
                        </div>
                    </div>
                    </form> 
                </div>
            </div>
        </div>
    </div>


</div>

<div class="modal fade" id="confirm-peak" role="dialog">
    <div class="modal-dialog">

        <!-- Modal content-->
        <div class="modal-content">
            <div class="modal-body">
                <p>Your chosen period contains peak date(s). </p>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
            </div>
        </div>

    </div>
</div>

<div class="modal fade" id="conflict-alert" role="dialog">
    <div class="modal-dialog">

        <!-- Modal content-->
        <div class="modal-content">
            <div class="modal-body">
                <p>The period you chose conflicts with an existing maintenance period or an event.</p>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
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

<div class="modal fade" id="update-daterange" role="dialog">
    <div class="modal-dialog">

        <!-- Modal content-->
        <div class="modal-content">
            <div class="modal-body">
                    <div class="form-group" style="padding-bottom: 30px;" >
                        <label for="config-demo" class="col-sm-2 control-label">Choose a date range</label>
                        <div class="col-sm-6">
                            <input type="text" id="update-range" class="form-control" name="udaterange">
                            <i class="glyphicon glyphicon-calendar fa fa-calendar"></i><div id="alert-empty"></div>
                        </div>
                    </div><br/>
                <div id="notifyPeak"></div>
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
    var blockedDates;
    var maintenanceId;
    var maintenanceList = [];
    var publicHolidays;
    var msg;
    var isUpdate = false;

    function blockDates() {
        $.ajax({
            url: "propertyOccupiedDates?id=" + propertyId,
            success: function (result) {
                blockedDates = result;
            }
        });
    }
    
    function notifyPeak() {
        if (isUpdate) {
            $("#notifyPeak").html("Please note: your chosen date range contain peak period.").css("color","red");
        } else {
            $("#confirm-peak").modal();
        }
    }
    
    function deNotifyPeak() {
        if (isUpdate) {
            $("#notifyPeak").html("");
        }
    }

    function deleteMaintenance(id) {
        maintenanceId = id;
        $("#delete-confirm").modal();
    }
    
    $("#deleteOk").click(function() {
        $.ajax({
            url: "DeleteMaintenance?id=" + maintenanceId,
            success:function(result) {
                if (result == "\"success\"") {
                    getMaintenance();
                }
            }
            
        });
    });
    
    function update(id) {
        maintenanceId = id;
        isUpdate = true;
        console.log(maintenanceList);
        var original;
        for (var i = 0; i < maintenanceList.length; i++) {
           if (maintenanceList[i].id == maintenanceId) {
               original = maintenanceList[i].startDate + " - " + maintenanceList[i].endDate;
           }   
        }
        $("#update-range").val(original);
        $("#update-daterange").modal();
    }
    
    $("#updateOk").click(function() {
        console.log("DAS");
        console.log($("#update-range").val());
        $.ajax({
            url: "UpdateMaintenance?mid=" + maintenanceId + "&propertyId=" + propertyId + "&mdaterange=" + $("#update-range").val(),
            success:function(result) {
                if (result == "\"success\"") {
                    getMaintenance();
                    $("#update-daterange").modal('hide');
                } else {
                    $("#notifyPeak").html("Your date period conflicts with an existing event or a maintenance schedule.").css("color", "red");
                    
                }
            }
            
        });
    });
    
    function getMaintenance() {
        $.ajax({
            url: "maintenanceList?id=" + propertyId,
            success: function (result) {
                var str = "";
                maintenanceList = result;
                for (var i = 0; i < result.length; i++) {
                    console.log(result[i]);
                    str += "<tr><td>" + (i + 1) + "</td><td>" + result[i].startDate + "</td><td>" + result[i].endDate + "</td>";
                    str += '<td><button type="button" class="btn btn-default" onclick="deleteMaintenance(' + result[i].id + ');">Delete</button></td>';
                    str += '<td><button type="button" class="btn btn-default" onclick="update(' + result[i].id + ');">Update</button></td>';
                    str += '</tr>'
                }
                $('#items').html(str);
            }
        });
    }

    $(document).ready(function () {

        blockDates();
        getMaintenance();
        $('#propertyList').change(function () {
            propertyId = $(this).val();
            getMaintenance();
            blockDates();
        });

        $.ajax({
            url: "publicHolidays",
            success: function (result) {
                publicHolidays = result;
            }
        });

        $('#new-range').daterangepicker(function (start, end, label) {
            console.log("New date range selected: ' + start.format('YYYY-MM-DD') + ' to ' + end.format('YYYY-MM-DD') + ' (predefined range: ' + label + ')");
        });

        
        $('#update-range').daterangepicker(function (start, end, label) {
            console.log("New date range selected: ' + start.format('YYYY-MM-DD') + ' to ' + end.format('YYYY-MM-DD') + ' (predefined range: ' + label + ')");
        });

       $("#msubmit").bind("click", function(){
        if ($("#new-range").val().length === 0) {
            $("#alert-empty").html("Please fill in date range.");
            return;
        } else {
            $("#alert-empty").html("");
        }
        $.ajax({
            type: "POST",
            url: 'CreateNewMaintenance',
            data: $("#maintenanceForm").serialize(),
            success: function (result) {
                msg=result;
                console.log("back");
                console.log(result)
                if (msg == "\"conflict\""){
                    $("#conflict-alert").modal();
                }
                getMaintenance();
                
            }
        });
        console.log($("#maintenanceForm").serialize());
        
    });
    });




</script>
<jsp:include page="footer.jsp" />


