<%-- 
    Document   : aCRMMain
    Created on : Oct 25, 2016, 10:51:55 PM
    Author     : hyc528
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<!doctype html>
<jsp:include page="header.jsp" />
<!-- Main Content -->
<div class="container-fluid">
    <div class="page-title">
        <span class="title">Analytical CRM Management Backend System</span>
    </div>
    <div class="side-body padding-top">
        <div class="row">
            <div class="col-sm-5">
                <!--    <div class="col-lg-3 col-md-6 col-sm-6 col-xs-12"> -->
                <c:url var="linkHref" value="/BackController?action=oCRMLinkCard" />
                <a href="${linkHref}">
                    <div class="card green summary-inline">
                        <div class="card-body">
                            <i class="icon fa fa-share-alt fa-4x"></i>
                            <div class="content">
                                <div class="title">View</div>
                            </div>
                            <div class="clear-both"></div>
                        </div>
                    </div>
                </a>
            </div>
            <div class="col-sm-5">
                <!--    <div class="col-lg-3 col-md-6 col-sm-6 col-xs-12"> -->
                <c:url var="linkHref" value="/BackController?action=oCRMEditCustomer" />
                <a href="${linkHref}">
                    <div class="card blue summary-inline">
                        <div class="card-body">
                            <i class="icon fa fa-share-alt fa-4x"></i>
                            <div class="content">
                                <div class="title">View & Edit Customer information</div>
                            </div>
                            <div class="clear-both"></div>
                        </div>
                    </div>
                </a>
            </div>
        </div>


        <!--            <div class="col-sm-4">
        
                        <div class="col-lg-3 col-md-6 col-sm-6 col-xs-12"> 
        <c:url var="linkHref" value="/BackController?action=deleteManpower" />
        <a href="${linkHref}">
            <div class="card yellow summary-inline">
                <div class="card-body">
                    <i class="icon fa fa-share-alt fa-4x"></i>
                    <div class="content">
                        <div class="title">Delete Manpower</div>
                    </div>
                    <div class="clear-both"></div>
                </div>
            </div>
        </a>
    </div>
</div>
<div class="row">
    <div class="col-sm-4">

        <div class="col-lg-3 col-md-6 col-sm-6 col-xs-12"> 
        <c:url var="linkHref" value="/BackController?action=viewAllManpower" />
        <a href="${linkHref}">
            <div class="card green summary-inline">
                <div class="card-body">
                    <i class="icon fa fa-share-alt fa-4x"></i>
                    <div class="content">
                        <div class="title">View Manpower</div>
                    </div>
                    <div class="clear-both"></div>
                </div>
            </div>
        </a>
    </div>
    <div class="col-sm-4">

        <div class="col-lg-3 col-md-6 col-sm-6 col-xs-12"> 
        <c:url var="linkHref" value="/BackController?action=setPriceManpower" />
        <a href="${linkHref}">
            <div class="card blue summary-inline">
                <div class="card-body">
                    <i class="icon fa fa-share-alt fa-4x"></i>
                    <div class="content">
                        <div class="title">Set Price Manpower</div>
                    </div>
                    <div class="clear-both"></div>
                </div>
            </div>
        </a>
    </div>-->
    </div> 
</div>

<jsp:include page="footer.jsp" />


