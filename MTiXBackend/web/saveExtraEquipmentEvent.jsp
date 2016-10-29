<%-- 
    Document   : saveExtraEquipmentEvent
    Created on : Sep 23, 2016, 9:50:34 AM
    Author     : catherinexiong
--%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<jsp:useBean id="equipment" class="java.util.List<entity.EquipmentEntity>" scope="request"/>
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
                        <div class="title">Following Equipments Added to Reservation ${event.name}</div>
                        <div class="description"></div>
                    </div>
                </div>
                <div class="card-body">
                    <div style="padding-bottom: 20px;">
                        <table class=" table table-hover" cellspacing="0" width="100%">
                            <thead>
                                <tr>
                                    
                                    <th>Equipment Name</th>
                                    <th>Price</th>
                                    

                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach items="${equipment}" var="equipment">
                                <tr>
                                    

                                    <td>${equipment.equipmentName}</td>
                                    <td>${equipment.price}</td>
                                   

                                </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                    </div>
                                  
                    <div class="sub-title">Please Press the Button Below to Add Extra Manpower 
                    </div>
                    
                      <% 
                                        session.setAttribute("seid", request.getAttribute("seid"));
                                        session.setAttribute("pid", request.getAttribute("pid"));
session.setAttribute("eventid",request.getAttribute("eventid"));%>


                        
                        <div class="col-sm-12 text-center" style="padding-bottom:50px;" >
                        <c:url var="moreSub" value="/BackController?action=subReservationSearch" />
                        <a class="btn btn-primary" href="${moreSub}" role="button">Create Another Sub Event</a>
                        <c:url var="addManpower" value="/BackController?action=addExtraManpower" />
                        <a class="btn btn-primary" href="${addManpower}" role="button">Add Additional Manpower</a>
                        
                    </div>
                   
                </div>
            </div>
        </div>
    </div>


</div>



    <jsp:include page="footer.jsp" />

