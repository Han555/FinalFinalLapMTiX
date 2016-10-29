<%-- 
    Document   : createOutlet
    Created on : Sep 25, 2016, 2:47:13 PM
    Author     : hyc528
--%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<jsp:useBean id="properties" class="java.util.List<entity.FoodOutletEntity>" scope="request"/>
<jsp:include page="header.jsp" />
<!-- Main Content -->

<div class="container-fluid">
    <div class="side-body">
        <div class="page-title">
            <span class="title">Add New Outlet</span>
        </div>
        <div class="row">
            <div class="col-xs-12">
                <div class="card">
                    <div class="card-header">
                        <div class="card-title">
                            <div class="title">Please fill up the * fields below to complete creation of F&B Outlets</div>
                            <!--<div class="description">Please fill up the fields below to complete creation of Equipment</div>-->
                        </div>
                    </div>
                    <div class="card-body">
                        <c:url var="formAction" value="/BackController?action=outletCreated" />
                        <form class="form-horizontal" id="formSubmit" action="${formAction}" method="post" >
                            <div class="form-group" style="padding-bottom: 20px;" >
                                <label for="ename" class="col-sm-2 control-label">Outlet Name<span style="color:#FF0000">*</span></label>
                                <div class="col-sm-4">
                                    <input type="text" name="oname" id="oname" class="form-control" value="" required = "true"> 
                                </div> 
                            </div>
                            <div class="form-group" style="padding-bottom: 20px;" >
                                <label for="ename" class="col-sm-2 control-label">Outlet Type<span style="color:#FF0000">*</span></label>
                                <div class="col-sm-4">
                                    <input type="text" name="otype" id="otype" class="form-control" value="" required = "true"> 
                                </div> 
                            </div>
                            <div class="form-group" style="padding-bottom: 20px;" >
                                <label for="standard" class="col-sm-2 control-label">Outlet Description<span style="color:#FF0000">*</span></label>
                                <div class="col-sm-4">
                                <input type="text" name="odescription" id="odescription" class="form-control" value="" required = "true"> 
                                </div>
                                <!--                                <div class="col-sm-4">
                                                                    <select class="js-example-basic-single js-states" style="width: 100%" name="estandard" id="estandard" required = "true">
                                
                                                                        <option value="s">Standard</option>
                                                                        <option value="ns">Non Standard</option>
                                                                    </select>
                                                                </div> -->
                            </div>
                            <!--
                            <div class="form-group" style="padding-bottom: 20px;" >
                                <label for="ename" class="col-sm-2 control-label">Price<span style="color:#FF0000">*</span></label>
                                <div class="col-sm-4">
                                    <input type="number" name="eprice" id="eprice" class="form-control" value="" required = "true" min="0"> 
                                </div> 
                            </div> -->
                            <div class="form-group" style="padding-bottom: 20px;" >
                                <label for="ename" class="col-sm-2 control-label">MIEC Property it belongs to<span style="color:#FF0000">*</span></label>
                                <div class="col-sm-4">
                                    <select class="js-example-basic-single js-states" style="width: 100%" name="foname" id="foname" required = "true">

                                        <c:forEach items="${properties}" var="properties">
                                            <option value="${properties.id}">${properties.propertyName}</option>
                                        </c:forEach>
                                    </select>
                                </div> 
                            </div>


                            <div class="form-group" style="padding-bottom: 20px;">
                                <div class="col-sm-offset-2 col-sm-10">
                                    <button type="submit" class="btn btn-default" value="formSubmit">Submit</button><span style="color:#FF0000;">${errormsg}</span>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<jsp:include page="footer.jsp" />
