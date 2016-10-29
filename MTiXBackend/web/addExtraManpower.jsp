<%-- 
    Document   : addExtraManpower
    Created on : Sep 23, 2016, 9:12:28 AM
    Author     : catherinexiong
--%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<jsp:include page="header.jsp" />
<!-- Main Content -->

<div class="container-fluid">
    <div class="side-body">
        <div class="page-title">
            <span class="title">Add Extra Equipment</span>

        </div>



        <div class="row">
            <div class="col-xs-12">
                <div class="card">
                    <div class="card-header">
                        <div class="card-title">
                            <div class="title">Please Select Extra Equipments Needed</div>

                        </div>
                    </div>
                    <div class="card-body">
                        <c:url var="formAction" value="/BackController?action=saveExtraManpower" />
                        <form class="form-horizontal" id="formSubmit" action="${formAction}" method="post">
                            <div class="form-group" style="padding-bottom: 20px;" >
                                <label for="evalue" class="col-sm-2 control-label">Add Non-Standard Manpower</label>
                                <div class="col-sm-6">
                                    <select class="js-example-basic-multiple" multiple="multiple" style="width: 100%" name="evalue" id="evalue" required>
                                        <c:forEach items="${manpower}" var="manpower">
                                            <option value="${manpower.id}">${manpower.staffRole} $ ${manpower.price}</option>
                                        </c:forEach>

                                    </select>
                                </div> 
                            </div>


                            <div class="form-group" style="padding-bottom: 20px;">


                                <div class="col-sm-6">
                                    <input type="hidden" name="seid" id="seid" class="form-control" value="${seid}">


                                </div> 
                            </div>
                            <div class="form-group" style="padding-bottom: 20px;">


                                <div class="col-sm-6">
                                    <input type="hidden" name="eid" id="eid" class="form-control" value="${eventid}">


                                </div> 
                            </div>

                            <div class="form-group" style="padding-bottom: 20px;">


                                <div class="col-sm-6">
                                    <input type="hidden" name="pid" id="pid" class="form-control" value="${pid}">


                                </div> 
                            </div>

                            <div class="form-group" style="padding-bottom: 20px;">
                                <div class="col-sm-offset-2 col-sm-10">
                                    <button type="submit" class="btn btn-default" value="formSubmit">Submit</button><span style="color:#FF0000">${errormsg}</span>
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


