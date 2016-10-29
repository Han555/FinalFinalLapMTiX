
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<jsp:useBean id="equipment" class="entity.EquipmentEntity" scope="request"/>
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
                        <div class="title">New Equipment Created Below</div>
                        <div class="description"></div>
                    </div>
                </div>
                <div class="card-body">
                    <div style="padding-bottom: 20px;">
                        <table class=" table table-hover" cellspacing="0" width="100%">
                            <thead>
                                <tr>

                                    <th>Equipment Name</th>
                                    <th>Equipment Location</th>
                                    <th>Property It Belongs To</th>
                                    <th>Is Standard</th>
                                        <c:if test="${standard=='TRUE'}">
                                        <th>Equipment Price</th>
                                        </c:if>
                                        <c:if test="${standard=='FALSE'}">
                                        <th>Set Price</th>
                                        </c:if>


                                </tr>
                            </thead>
                            <tbody>
                                <tr>



                                    <td>${equipment.equipmentName}</td>
                                    <td>${equipment.location}</td>
                                    <td>${equipment.property.propertyName}</td>
                                    <c:if test="${equipment.standard=='TRUE'}">
                                        <td>Yes</td>
                                    </c:if>
                                    <c:if test="${equipment.standard=='FALSE'}">
                                        <td>No</td>
                                    </c:if>
                                    <c:if test="${standard=='TRUE'}">
                                        <td>${equipment.price}</td>
                                    </c:if>
                                    <c:if test="${standard=='FALSE'}">
                                        <td><button type="button" class="btn btn-default" data-toggle="modal" data-target="#myModal" id="price">Set Price</button></td>
                                    </c:if>
                                </tr>
                            </tbody>
                        </table>

                    </div>


                </div>
            </div>

        </div>
    </div>

</div>
<script>
    var id = 0;
    $(".price").click(function () {
        // id = $(this).attr('id');

        $(".modal-header #myModalLabel").text('Please Set Price');
        $("#popup").html("LOADING...");
        // $("#popup").html('<iframe id="p1frame" class="embed-responsive-item" frameborder="0" src="http://localhost:8080/MTiXBackend/seat.jsp?id=' + id + '"></iframe>');

        //console.log('"http://localhost:8080/MTiX-war/seat.jsp?id=' + id + '"');
    });
</script>
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" >
    <div class="modal-dialog">
        <div class="modal-content">
            <c:url var="formAction" value="/BackController?action=setPriceJustCreated" />
            <form class="form-horizontal" id="seSubmit" action="${formAction}" method="post">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title" id="myModalLabel"></h4>
                </div>
                <!-- Modal content-->
                <div class="modal-content">
                    <div class="modal-body">

                        <div class="form-group" style="padding-bottom: 30px;" >
                            <label for="eprice" class="col-sm-2 control-label">Price</label>
                            <div class="col-sm-6">
                                <input type="text" id="eprice" class="form-control" name="eprice">

                            </div>
                        </div>
                        <div class="form-group" style="padding-bottom: 30px;" >


                            <input type="hidden" id="eid" class="form-control" name="eid" value="${equipment.id}">

                        </div>
                    </div>



                </div>
                <div class="modal-footer">
                    <div class="form-group" style="padding-bottom: 30px;" >
                        <c:url var="setPrice" value="/Controller?action=setPriceJustCreated"/>
                        <button type="submit" class="btn btn-default" id="setOk" href="${setPrice}">Ok</button>
                        <button type="button" class="btn btn-default" data-dismiss="modal">Cancel</button> 
                    </div>                
                </div>

        </div>
        </form>
    </div>

</div>
</div>


<jsp:include page="footer.jsp" />




