<%-- 
    Document   : oCRMViewCustomer
    Created on : Oct 26, 2016, 2:26:19 PM
    Author     : hyc528
--%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<jsp:include page="header.jsp" />

<!-- Main Content -->
<div class="side-body">
    <div class="page-title">
        <span class="title">Customer Lists</span>
    </div>
    <div class="row">
        <div class="col-xs-12">
            <div class="card">
                <div class="card-header">
                    <div class="card-title">
                        <div class="title">View and Update User Information</div>
                        <div class="description"></div>
                    </div>
                </div>
                <div class="card-body">
                    <div style="padding-bottom: 20px;">
                        <table class="table-hover" cellspacing="0" width="100%">
                            <thead>
                                <tr>
                                    <th>#</th>
                                    <th>Username</th>
                                    <th>First Name</th>
                                    <th>Last Name</th>
                                    <th>Age</th>
                                    <th>Contact Number</th>
                                    <th>Loyalty Card ID</th>
                                    <th>Loyalty Points</th>
                                    <th>Join Date</th>
                                    <!--<th>Total Spending</th>-->
                                    <th>Update</th>
                                </tr>
                            </thead>
                            <tbody id="items">
                                
                            </tbody>
                        </table>
                    </div>
                    <!--                   <div class="form-group" style="padding-bottom: 50px;" >
                                            <label for="propertyList" class="col-sm-2 control-label">Choose a Property</label>
                                            <div class="col-sm-6">
                                                <select class="js-example-basic-single js-states" id="propertyList" name="propertyList">
                </select>
            </div>
        </div>-->
                </div>
            </div>
        </div>
    </div>


</div>





<!--<div class="modal fade" id="delete-confirm" role="dialog">
    <div class="modal-dialog">

         Modal content
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
</div>-->

<div class="modal fade" id="update-data" role="dialog">
    <div class="modal-dialog">

        <!-- Modal content-->
        <div class="modal-content">
            <div class="modal-body">
                <div class="form-group" style="padding-bottom: 30px;" >
                    <label for="lname" class="col-sm-2 control-label">New Loyalty Points</label>
                    <div class="col-sm-6">
                        <input type="text" id="update-loyaltypts" class="form-control" name="lname">
                        <div id="alert-empty"></div>
                    </div>
                </div>
                <div class="form-group" style="padding-bottom: 30px;" >
                    <label for="update-cumspend" class="col-sm-2 control-label">New Mobile Number</label>
                    <div class="col-sm-6">
                        <input type="text" id="update-cumspend" class="form-control" name="fname">
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
//    var propertyId = 1;

    var customerId;
    var customerList=[];



    function update(id) {
        customerId = id;

        console.log(customerList);

        var phonenum;

        var loyaltypoints;

        var cumspend;

        for (var i = 0; i < customerList.length; i++) {
            if (customerList[i].userId == customerId) {

                phonenum = customerlist[i].mobileNumber;
                loyaltypoints = customerlist[i].loyaltyPoints;
                cumspend = customerlist[i].cumulativeSpend;
            }
        }

        $("#update-loyaltypts").val(loyaltypoints);
//        $("#update-cumspend").val(cumspend);
        $("#update-phonenum").val(phonenum);
        $("#update-data").modal();
    }

    $("#updateOk").click(function () {
        console.log("DAS");
        console.log($("#update-loyaltypts").val());
        $.ajax({
            url: "UpdateCustomer?cid=" + customerId + "&phonenum=" + $("#update-phonenum").val() + "&loyaltypts=" + $("#update-loyaltypts").val() + "&cumspend=" + $("#update-cumspend").val(),
            success: function (result) {
                if (result == "\"success\"") {
                    getCustomer();
                    $("#update-data").modal('hide');
                } else {
                    $("#notifyPeak").html("Errors happened when updating data.").css("color", "red");

                }
            }

        });
    });

    function getCustomer() {
        $.ajax({
            url: "CustomerListController",
            success: function (result) {
                var str = "";
                
                customerList = result;
                console.log("====getCustomer1"+customerList);
                for (var i = 0; i < result.length; i++) {
                    console.log(result[i]);
                    str += "<tr><td>" + (i + 1) + "</td><td>" + result[i].username + "</td><td>" + result[i].firstName + "</td><td>"+ result[i].lastName + "</td><td>" + result[i].age+"</td><td>" + result[i].mobileNumber + "</td><td>" + result[i].loyaltyCardId + "</td><td>"
                             + result[i].loyaltyPoints + "</td><td>" + result[i].joinDate  + "</td>";
                    str += '<td><button type="button" class="btn btn-default" onclick="update(' + result[i].userId + ');">Update</button></td>';
                    str += '</tr>'
                }
                $('#items').html(str);
            }
        });
    }

    $(document).ready(function () {


        getCustomer();
//        $('#propertyList').change(function () {
//            propertyId = $(this).val();
//            getEquipment();
//
//        });
    });





</script>
<jsp:include page="footer.jsp" />
