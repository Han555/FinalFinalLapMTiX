<%-- 
    Document   : oCRMLinkCard
    Created on : Oct 25, 2016, 10:55:35 PM
    Author     : hyc528
--%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<jsp:include page="header.jsp" />
<!-- Main Content -->
<div class="container-fluid">
    <div class="side-body">
        <div class="page-title">
            <span class="title">Link Card to Customer</span>
        </div>
        <div class="row">
            <div class="col-xs-12">
                <div class="card">
                    <div class="card-header">
                        <div class="card-title">
                            <div class="title">Please fill up the * fields below to link a Loyalty Card to Customer</div>
                            <!--<div class="description">Please fill up the fields below to complete creation of Equipment</div>-->
                            <!--</div>-->
                        </div>
                    </div>
                    <div class="card-body">
                        <c:url var="formAction" value="/BackCRMController?action=oCRMcardLinked" />
                        <c:if test="${error == 'true'}">
                    <div style="color:red">Errors happen when linking the card</div>
                </c:if>
                        <form class="form-horizontal" id="formSubmit" action="${formAction}" method="post" >
                            <div class="form-group" style="padding-bottom: 20px;" >
                                <label for="cemail" class="col-sm-2 control-label">Customer Email<span style="color:#FF0000">*</span></label>
                                <div class="col-sm-4">
                                    <input type="text" name="cemail" id="cemail" class="form-control" value="" required = "true"> 
                                </div> 
                            </div>

                            <div class="form-group" style="padding-bottom: 20px;" >
                                <div class="form-group" style="padding-bottom: 20px;" >
                                    <label for="cnumber" class="col-sm-2 control-label">Loyalty Card Number<span style="color:#FF0000">*</span></label>
                                    <div class="col-sm-4">
                                        <input type="text" name="cnumber" id="cnumber" class="form-control" value="" required = "true"> 
                                    </div> 
                                </div>
                                <!--                                <div class="col-sm-4">
                                                                    <select class="js-example-basic-single js-states" style="width: 100%" name="elocation" id="elocation" required = "true">
                                
                                                                     
                                                                            <option value="Storage Room A">Storage Room A</option>
                                                                            <option value="Storage Room B">Storage Room B</option>
                                                                            <option value="Storage Room C">Storage Room C</option>
                                                                      
                                                                    </select>
                                                                    
                                                                </div> -->
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
