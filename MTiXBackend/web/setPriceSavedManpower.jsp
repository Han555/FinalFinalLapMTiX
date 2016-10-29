<%-- 
    Document   : setPriceSavedManpower
    Created on : Sep 25, 2016, 6:42:45 AM
    Author     : hyc528
--%>


<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<jsp:useBean id="manpower" class="entity.ManpowerEntity" scope="request"/>
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
                        <div class="title">Price Set for Non Standard Manpower</div>
                        <div class="description"></div>
                    </div>
                </div>
                <div class="card-body">
                    <div style="padding-bottom: 20px;">
                        <table class=" table table-hover" cellspacing="0" width="100%">
                            <thead>
                                <tr>

                                    <th>Staff Role</th>
                                    <th>Number of the Staff</th>
                                    <th>Property It Belongs To</th>
                                    <th>Is Standard</th>

                                    <th>Price</th>
                                </tr>
                            </thead>
                            <tbody>
                                <tr>
                                    <td>${manpower.staffRole}</td>
                                    <td>${manpower.number}</td>
                                    <td>${manpower.property.propertyName}</td>
                                    <td>${manpower.standard}</td>
                                    <td>${manpower.price}</td>
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





