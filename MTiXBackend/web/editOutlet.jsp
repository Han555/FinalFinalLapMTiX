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
        <span class="title"></span>
    </div>
    <div class="row">
        <div class="col-xs-12">
            <div class="card">
                <div class="card-header">
                    <div class="card-title">
                        <div class="title">View, Update and Delete F&B Outlets</div>
                        <div class="description">The table below shows the F&B Outlets group by properties.</div>
                    </div>
                </div>
                <div class="card-body">
                    <div style="padding-bottom: 20px;">
                        <table class="table-hover" cellspacing="0" width="100%">
                            <thead>
                                <tr>
                                    <th>#</th>
                                    <th>Outlet Name</th>
                                    <th>Type of Outlet</th>
                                    <th>Description</th>
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
                    <label for="ename" class="col-sm-2 control-label">Outlet Name</label>
                    <div class="col-sm-6">
                        <input type="text" id="update-oname" class="form-control" name="oname">
                        <div id="alert-empty"></div>
                    </div>
                </div>
                <div class="form-group" style="padding-bottom: 30px;" >
                    <label for="mtype" class="col-sm-2 control-label">Type of Outlet</label>
                    <div class="col-sm-6">
                        <input type="text" id="update-otype" class="form-control" name="otype">
                        <div id="alert-empty"></div>
                    </div>
                </div>
                <div class="form-group" style="padding-bottom: 30px;" >
                    <label for="odes" class="col-sm-2 control-label">Outlet Description</label>
                    <div class="col-sm-6">
                        <input type="text" id="update-odes" class="form-control" name="odes">
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

    var outletId;
    var outletList = [];

    var msg;








    function deleteOutlet(id) {
        outletId = id;
        $("#delete-confirm").modal();
    }

    $("#deleteOk").click(function () {
        $.ajax({
            url: "DeleteOutlet?id=" + outletId,
            success: function (result) {
                if (result == "\"success\"") {
                    getFoodOutlet();
                }
            }

        });
    });

    function update(id) {
        outletId = id;

        console.log(outletList);
        var originalname;
        var originalloca;
        var originaldes;
        for (var i = 0; i < outletList.length; i++) {
            if (outletList[i].id == outletId) {
                originalname = outletList[i].outletName;
                originalloca = outletList[i].outletType;
                originaldes = outletList[i].outletDescription;
            }
        }
        $("#update-oname").val(originalname);
        $("#update-otype").val(originalloca);
        $("#update-odes").val(originaldes);
        $("#update-data").modal();
    }

    $("#updateOk").click(function () {
        console.log("DAS");
        console.log($("#update-name").val());
        $.ajax({
            url: "UpdateOutlet?mid=" + outletId + "&propertyId=" + propertyId + "&name=" + $("#update-oname").val() + "&type=" + $("#update-otype").val() + "&des=" + $("#update-odes").val(),
            success: function (result) {
                if (result == "\"success\"") {
                    getFoodOutlet();
                    $("#update-data").modal('hide');
                } else {
                    $("#notifyPeak").html("Errors happened when updating data.").css("color", "red");

                }
            }

        });
    });

    function getFoodOutlet() {
        $.ajax({
            url: "outletList?id=" + propertyId,
            success: function (result) {
                var str = "";
                outletList = result;
                for (var i = 0; i < result.length; i++) {
                    console.log(result[i]);
                    str += "<tr><td>" + (i + 1) + "</td><td>" + result[i].outletName + "</td><td>" + result[i].outletType + "</td><td>" + result[i].outletDescription + "</td>";
                    str += '<td><button type="button" class="btn btn-default" onclick="deleteOutlet(' + result[i].id + ');">Delete</button></td>';
                    str += '<td><button type="button" class="btn btn-default" onclick="update(' + result[i].id + ');">Update</button></td>';
                    str += '</tr>'
                }
                $('#items').html(str);
            }
        });
    }

    $(document).ready(function () {


        getFoodOutlet();
        $('#propertyList').change(function () {
            propertyId = $(this).val();
            getFoodOutlet();

        });
    });





</script>
<jsp:include page="footer.jsp" />


