<!--<link rel="stylesheet" type="text/css" href="lib/css/jquery.dataTables.min.css">
<link href='https://cdn.datatables.net/responsive/2.1.0/css/responsive.dataTables.min.css' rel='stylesheet' type='text/css'>-->

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<jsp:useBean id="eList" class="java.util.List<entity.EquipmentEntity>" scope="request"/>
<jsp:include page="header.jsp" />
<div class="side-body">
    <div class="page-title">
        <span class="title">Display all Equipments</span>
    </div>
    <div class="card-body">
        <!--        <div style="padding-bottom: 20px;">
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
                        <tbody id="items"></tbody>
                    </table>
                </div>-->

        <table class="table table-hover" cellspacing="0" width="100%">
            <thead>
                <tr>
                    <th>Property It Belongs To</th>
                    <th>Equipment Name</th>
                    <th>Location</th>
                    <th>Standard</th>
                    <th>Price</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${eList}" var="equipment">
                    <tr>
                        <td>${equipment.property.propertyName}</td>
                        <td>${equipment.equipmentName}</td>
                        <td>${equipment.location}</td>
                        <c:if test="${equipment.standard=='TRUE'}">
                            <td>Yes</td>
                        </c:if>
                        <c:if test="${equipment.standard=='FALSE'}">
                            <td>No</td>
                        </c:if>
                        <td>${equipment.price}</td>
                    </c:forEach>
            </tbody>
        </table>


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


<jsp:include page="footer.jsp" />