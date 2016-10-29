<%-- 
    Document   : deleteEquipment
    Created on : Sep 23, 2016, 2:15:23 AM
    Author     : hyc528
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="header.jsp" />
<!DOCTYPE html>
<div class="container-fluid">
    <div class="side-body">
        <div class="page-title">
            <span class="title">Delete Equipment</span>
        </div>
        <div class="row">
            <div class="col-xs-12">
                <div class="card">
                    <div class="card-header">
                        <div class="card-title">
                            <div class="title">Please select the Equipment to be deleted.</div>
                            <!--<div class="description">Please fill up the fields below to complete creation of Equipment</div>-->
                        </div>
                    </div>
                    <table class="table-hover" cellspacing="0" width="100%">
                        <thead>
                            <tr>
                                <th>Equipment ID</th>
                                <th>Equipment Name</th>
                                <th>Location</th>
                                <th>Standard</th>
                                <th>Price</th>
                            </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${eList}" var="equipment">
                            <tr>
                                <th>${equipment.id}</th>
                                <th>${equipment.equipmentName}</th>
                                <th>${equipment.location}</th>
                                <th>${equipment.standard}</th>
                                <th>${equipment.price}</th>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
</div>

<script>
    function getEquipment() {
        $.ajax({
            url: "maintenanceList?id=" + propertyId,
            success: function (result) {
                var str = "";
                console.log(result);
                console.log(result.length);
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
</script>