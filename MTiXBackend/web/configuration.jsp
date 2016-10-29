<%-- 
    Document   : configuration
    Created on : Sep 20, 2016, 1:29:28 PM
    Author     : Student-ID
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<!doctype html>
<jsp:include page="header.jsp" />
<!-- Main Content -->
<!-- Main Content -->
<div class="container-fluid">
    <div class="side-body padding-top">
        <div class="row">
            <div class="col-lg-3 col-md-6 col-sm-6 col-xs-12">
                <c:url var="linkHref" value="/BackController?action=displaySeatsMain" />
                <a href="${linkHref}">
                    <div class="card blue summary-inline">
                        <div class="card-body">
                            <i class="icon fa fa-share-alt fa-4x"></i>
                            <div class="content">
                                <div class="title">Set Area Pricing</div>
                            </div>
                            <div class="clear-both"></div>
                        </div>
                    </div>
                </a>
            </div>
          
            
                <div class="col-lg-3 col-md-6 col-sm-6 col-xs-12">
                    <c:url var="linkHref" value="/BackController?action=deletePromotionMain" />
                    <a href="${linkHref}">
                        <div class="card green summary-inline">
                            <div class="card-body">
                                <i class="icon fa fa-share-alt fa-4x"></i>
                                <div class="content">
                                    <div class="title">View Area Pricing</div>
                                </div>
                                <div class="clear-both"></div>
                            </div>
                        </div>
                    </a>
                </div>
            

        </div>
        
    </div> 
</div>
</div>
