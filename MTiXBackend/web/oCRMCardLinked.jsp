<%-- 
    Document   : oCRMCardLinked
    Created on : Oct 27, 2016, 12:23:55 PM
    Author     : hyc528
--%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%--<jsp:useBean id="customer" class="java.util.List<entity.UserEntity>" scope="request"/>--%>
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
                        <div class="title">The </div>
                        <div class="description">The table below shows equipments group by properties.</div>
                    </div>
                </div>
                <div class="card-body">
                    <div style="padding-bottom: 20px;">
                        <table class="table-hover" cellspacing="0" width="100%">
                            <thead>
                                <tr>
                                    <th>#</th>
                                    <th>Last Name</th>
                                    <th>First Name</th>
                                    <th>Age</th>
                                    <th>Contact Number</th>
                                    <th>Loyalty Card ID</th>
                                    <th>Loyalty Points</th>
                                    <th>Join Date</th>
                                    <th>Total Spending</th>
                                </tr>
                            </thead>
                            <tbody id="items"></tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </div>


</div>
