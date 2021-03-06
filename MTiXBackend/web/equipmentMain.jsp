<%-- 
    Document   : equipmentMain
    Created on : Sep 23, 2016, 1:23:00 AM
    Author     : hyc528
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<!doctype html>
<jsp:include page="header.jsp" />
<!-- Main Content -->
<div class="container-fluid">
    <div class="side-body padding-top">
        <div class="row">
            <div class="col-sm-6">
                <!--    <div class="col-lg-3 col-md-6 col-sm-6 col-xs-12"> -->
                <c:url var="linkHref" value="/BackController?action=createEquipment" />
                <a href="${linkHref}">
                    <div class="card green summary-inline">
                        <div class="card-body">
                            <i class="icon fa fa-share-alt fa-4x"></i>
                            <div class="content">
                                <div class="title">Adding & Pricing of Equipment</div>
                            </div>
                            <div class="clear-both"></div>
                        </div>
                    </div>
                </a>
            </div>
            <div class="col-sm-6">
                <!--    <div class="col-lg-3 col-md-6 col-sm-6 col-xs-12"> -->
                <c:url var="linkHref" value="/BackController?action=editEquipment" />
                <a href="${linkHref}">
                    <div class="card blue summary-inline">
                        <div class="card-body">
                            <i class="icon fa fa-share-alt fa-4x"></i>
                            <div class="content">
                                <div class="title">View, Delete & Update Equipment</div>
                            </div>
                            <div class="clear-both"></div>
                        </div>
                    </div>
                </a>
            </div>


<!--            <div class="col-sm-4">

                <div class="col-lg-3 col-md-6 col-sm-6 col-xs-12"> 
                <c:url var="linkHref" value="/BackController?action=deleteEquipment" />
                <a href="${linkHref}">
                    <div class="card yellow summary-inline">
                        <div class="card-body">
                            <i class="icon fa fa-share-alt fa-4x"></i>
                            <div class="content">
                                <div class="title">Delete Equipment</div>
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
                <c:url var="linkHref" value="/BackController?action=viewAllEquipment" />
                <a href="${linkHref}">
                    <div class="card green summary-inline">
                        <div class="card-body">
                            <i class="icon fa fa-share-alt fa-4x"></i>
                            <div class="content">
                                <div class="title">View Equipment</div>
                            </div>
                            <div class="clear-both"></div>
                        </div>
                    </div>
                </a>
            </div>
                    
            <div class="col-sm-4">
                <div class="col-lg-3 col-md-6 col-sm-6 col-xs-12"> 
                <c:url var="linkHref" value="/BackController?action=setPriceEquipment" />
                <a href="${linkHref}">
                    <div class="card blue summary-inline">
                        <div class="card-body">
                            <i class="icon fa fa-share-alt fa-4x"></i>
                            <div class="content">
                                <div class="title">Price Equipment</div>
                            </div>
                            <div class="clear-both"></div>
                        </div>
                    </div>
                </a>
            </div>        -->
        </div>
    </div> 
</div>

