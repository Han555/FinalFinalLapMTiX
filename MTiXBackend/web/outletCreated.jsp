
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<jsp:useBean id="outlet" class="entity.FoodOutletEntity" scope="request"/>
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
                        <div class="title">New Outlet Created Below</div>
                        <div class="description"></div>
                    </div>
                </div>
                <div class="card-body">
                    <div style="padding-bottom: 20px;">
                        <table class=" table table-hover" cellspacing="0" width="100%">
                            <thead>
                                <tr>

                                    <th>Outlet Name</th>
                                    <th>Outlet Type</th>
                                    <th>Description</th>
                                </tr>
                            </thead>
                            <tbody>

                                <tr>
                                    <td>${outlet.outletName}</td>
                                    <td>${outlet.outletType}</td>
                                    <td>${outlet.property.propertyName}</td>

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




