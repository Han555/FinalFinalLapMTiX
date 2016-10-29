<%-- 
    Document   : setPriceManpower
    Created on : Sep 23, 2016, 2:15:55 AM
    Author     : hyc528
--%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<jsp:include page="header.jsp" />

<%--<jsp:useBean id="ensList" class="java.util.List<entity.Manpower>" scope="request"/>--%>
<div class="side-body">
    <div class="page-title">
        <span class="title">Set Price for Non Standard Manpower</span>
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
                    <th>Manpower ID</th>
                    <th>Staff Role</th>
                    <th>Number of the Staff</th>
                    <th>Standard</th>
                    <th>Price</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${ensList}" var="manpower">
                    <tr>
                        <th>${manpower.id}</th>
                        <th>${manpower.staffRole}</th>
                        <th>${manpower.number}</th>
                        <th>${manpower.standard}</th>
                        <th>${manpower.price}</th>
                        </c:forEach>
            </tbody>
        </table>


    </div>
</div>

<!--<script>
    function getNonStandardEquipment() {
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
</script>-->


<jsp:include page="footer.jsp" />
