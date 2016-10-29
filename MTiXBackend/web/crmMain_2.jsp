<%-- 
    Document   : CRMMain
    Created on : Oct 25, 2016, 10:30:03 PM
    Author     : hyc528
--%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<jsp:include page="header.jsp" />
<!-- Main Content -->
<div class="side-body">


    <div class="container"> 
        <div class="page-title">
            <span class="title">Customer Relationship Management Backend System</span>
        </div>

        <div class="row">

            <div class="col-sm-6 col-md-4">
                <div class="thumbnail">
                    <img src="img/AnalyticalCRM.jpg" alt="theater_main" style="width:300 px;height:300px">
                    <div class="caption">
                        <h3 style="text-align: center">Analytical CRM</h3>
                        <p><c:url var="linkHref" value="/BackCRMController?action=aCRMMain" /><a href="${linkHref}" class="btn btn-primary" role="button">Enter</a> </p>
                    </div>
                </div>
            </div>
            <div class="col-sm-6 col-md-4">
                <div class="thumbnail">
                    <img src="img/CRM_Backup.jpg" alt="concert_main" style="width:300 px;height:300px">
                    <div class="caption">
                        <h3 style="text-align: center">Operational CRM</h3>
                        <p><c:url var="linkHref" value="/BackCRMController?action=oCRMMain" /><a href="${linkHref}" class="btn btn-primary" role="button">Enter</a> </p>
                    </div>
                </div>
            </div>
        </div>

        <jsp:include page="footer.jsp" />

