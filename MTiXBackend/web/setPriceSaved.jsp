
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
                        <div class="title">Price Set for Non Standard Equipment</div>
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
                                       
                                        <th>Price</th>
                                     


                                </tr>
                            </thead>
                            <tbody>
                                <tr>



                                    <td>${equipment.equipmentName}</td>
                                    <td>${equipment.location}</td>
                                    <td>${equipment.property.propertyName}</td>
                                    <td>${equipment.standard}</td>
                                   <td>${equipment.price}</td>
                                </tr>
                            </tbody>
                        </table>

                    </div>


                </div>
            </div>

        </div>
    </div>
    
</div>

   





<jsp:include page="footer.jsp" />





