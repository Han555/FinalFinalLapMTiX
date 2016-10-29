<%-- 
    Document   : editManpower
    Created on : Sep 25, 2016, 10:57:29 AM
    Author     : hyc528
--%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<jsp:useBean id="properties" class="java.util.List<entity.PropertyEntity>" scope="request"/>
<jsp:include page="header.jsp" />

<!-- Main Content -->
<div class="side-body">
    <div class="page-title">
        <span class="title">Manpowers</span>
    </div>
    <div class="row">
        <div class="col-xs-12">
            <div class="card">
                <div class="card-header">
                    <div class="card-title">
                        <div class="title">View, Update and Delete Manpowers</div>
                        <div class="description">The table below shows manpowers group by properties.</div>
                    </div>
                </div>
                <div class="card-body">
                    <div style="padding-bottom: 20px;">
                        <table class="table-hover" cellspacing="0" width="100%">
                            <thead>
                                <tr>
                                    <th>#</th>
                                    <th>Staff Role</th>
                                    <th>Number of the Staff</th>
                                    <th>Standard</th>
                                    <th>Price</th>
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
                    <label for="emrole" class="col-sm-2 control-label">Staff Role</label>
                    <div class="col-sm-6">
                        <input type="text" id="update-mrole" class="form-control" name="mrole">
                        <div id="alert-empty"></div>
                    </div>
                </div>
                <div class="form-group" style="padding-bottom: 30px;" >
                    <label for="mnumber" class="col-sm-2 control-label">Number of the Staff</label>
                    <div class="col-sm-6">
                        <input type="text" id="update-mnumber" class="form-control" name="mnumber">
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

    var manpowerId;
    var manpowerList = [];

    var msg;








    function deleteManpower(id) {
        manpowerId = id;
        $("#delete-confirm").modal();
    }

    $("#deleteOk").click(function () {
        $.ajax({
            url: "DeleteManpower?id=" + manpowerId,
            success: function (result) {
                if (result == "\"success\"") {
                    getManpower();
                }
            }

        });
    });

    function update(id) {
        manpowerId = id;

        console.log(manpowerList);
        var originalname;
        var originalloca;
        for (var i = 0; i < manpowerList.length; i++) {
            if (manpowerList[i].id == manpowerId) {
                originalname = manpowerList[i].staffRole;
                originalloca = manpowerList[i].number;
            }
        }
        $("#update-mrole").val(originalname);
        $("#update-mnumber").val(originalloca);
        $("#update-data").modal();
    }

    $("#updateOk").click(function () {
        console.log("DAS");
        console.log($("#update-role").val());
        $.ajax({
            url: "UpdateManpower?mid=" + manpowerId + "&propertyId=" + propertyId + "&mrole=" + $("#update-mrole").val() + "&mnumber=" + $("#update-mnumber").val(),
            success: function (result) {
                if (result == "\"success\"") {
                    getManpower();
                    $("#update-data").modal('hide');
                } else {
                    $("#notifyPeak").html("Errors happened when updating data.").css("color", "red");

                }
            }

        });
    });

    function getManpower() {
        $.ajax({
            url: "manpowerList?id=" + propertyId,
            success: function (result) {
                var str = "";
                manpowerList = result;
                for (var i = 0; i < result.length; i++) {
                    console.log(result[i]);
                    str += "<tr><td>" + (i + 1) + "</td><td>" + result[i].staffRole + "</td><td>" + result[i].number + "</td><td>" + result[i].standard + "</td><td>" + result[i].price + "</td>";
                    str += '<td><button type="button" class="btn btn-default" onclick="deleteManpower(' + result[i].id + ');">Delete</button></td>';
                    str += '<td><button type="button" class="btn btn-default" onclick="update(' + result[i].id + ');">Update</button></td>';
                    str += '</tr>'
                }
                $('#items').html(str);
            }
        });
    }

    $(document).ready(function () {


        getManpower();
        $('#propertyList').change(function () {
            propertyId = $(this).val();
            getManpower();

        });
    });





</script>
<jsp:include page="footer.jsp" />


