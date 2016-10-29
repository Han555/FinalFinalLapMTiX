<%-- 
    Document   : createEquipmentMain
    Created on : Sep 23, 2016, 2:05:26 AM
    Author     : hyc528
--%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<jsp:useBean id="properties" class="java.util.List<entity.PropertyEntity>" scope="request"/>
<jsp:include page="header.jsp" />
<!-- Main Content -->

<div class="container-fluid">
    <div class="side-body">
        <div class="page-title">
            <span class="title">Add New Equipment</span>
        </div>
        <div class="row">
            <div class="col-xs-12">
                <div class="card">
                    <div class="card-header">
                        <div class="card-title">
                            <div class="title">Please fill up the * fields below to complete creation of Equipment</div>
                            <!--<div class="description">Please fill up the fields below to complete creation of Equipment</div>-->
                        </div>
                    </div>
                    <div class="card-body">
                        <c:url var="formAction" value="/BackController?action=equipmentCreated" />
                        <form class="form-horizontal" id="formSubmit" action="${formAction}" method="post" >
                            <div class="form-group" style="padding-bottom: 20px;" >
                                <label for="ename" class="col-sm-2 control-label">MIEC Property it belongs to<span style="color:#FF0000">*</span></label>
                                <div class="col-sm-4">
                                    <select class="js-example-basic-single js-states" style="width: 100%" name="epname" id="epname" required = "true">

                                        <c:forEach items="${properties}" var="properties">
                                            <option value="${properties.id}">${properties.propertyName}</option>
                                        </c:forEach>
                                    </select>
                                </div> 
                            </div>
                            
                            <div class="form-group" style="padding-bottom: 20px;" >
                                <label for="elocation" class="col-sm-2 control-label">Location<span style="color:#FF0000">*</span></label>
                                <div class="col-sm-4">
                                    <select class="js-example-basic-single js-states" style="width: 100%" name="elocation" id="elocation" required = "true">

                                     
                                            <option value="Storage Room A">Storage Room A</option>
                                            <option value="Storage Room B">Storage Room B</option>
                                            <option value="Storage Room C">Storage Room C</option>
                                      
                                    </select>
                                    
                                </div> 
                            </div>
                            <div class="form-group" style="padding-bottom: 20px;" >
                                <label for="ename" class="col-sm-2 control-label">Equipment Name<span style="color:#FF0000">*</span></label>
                                <div class="col-sm-4">
                                    <input type="text" name="ename" id="ename" class="form-control" value="" required = "true"> 
                                </div> 
                            </div>
                            <div class="form-group" style="padding-bottom: 20px;" >
                                <label for="ename" class="col-sm-2 control-label">Location<span style="color:#FF0000">*</span></label>
                                <div class="col-sm-4">
                                    <input type="text" name="elocation" id="elocation" class="form-control" value="" required = "true"> 
                                </div> 
                            </div>
                            <div class="form-group" style="padding-bottom: 20px;" >
                                <label for="standard" class="col-sm-2 control-label">Standard<span style="color:#FF0000">*</span></label>
                                <div class="col-sm-4">
                                    <select class="js-example-basic-single js-states" style="width: 100%" name="estandard" id="estandard" required = "true">

                                        <option value="s">Standard</option>
                                        <option value="ns">Non Standard</option>
                                    </select>
                                </div> 
                            </div>
                            <!--
                            <div class="form-group" style="padding-bottom: 20px;" >
                                <label for="ename" class="col-sm-2 control-label">Price<span style="color:#FF0000">*</span></label>
                                <div class="col-sm-4">
                                    <input type="number" name="eprice" id="eprice" class="form-control" value="" required = "true" min="0"> 
                                </div> 
                            </div> -->
                            


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
<!--<script>
    var propertyId = 1;
    var blockedDates = null;
    var publicHolidays = null;




    $(document).ready(function () {





        $('#daterange').daterangepicker(function (start, end, label) {
            console.log("New date range selected: ' + start.format('YYYY-MM-DD') + ' to ' + end.format('YYYY-MM-DD') + ' (predefined range: ' + label + ')");
        });


    });  -->
    

<jsp:include page="footer.jsp" />