<%-- 
    Document   : readBulletin
    Created on : Sep 12, 2016, 2:03:24 PM
    Author     : Student-ID
--%>

<%@page import="java.util.ArrayList"%>
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
            <div class="col-md-6">
                <div class="card">
                    <div class="content"> 
                        <div class="container-fluid">
                            <div class="row">

                                <div class="col-md-8">
                                    <div class="header">
                                        <h4 class="title">Bulletin Message</h4>   
                                    </div>
                                    <div class="content">             

                                        <div class="row">
                                            <label style="text-align: justify" for="email">Subject:</label><br />
                                            <p style="text-align: justify"><%= ((ArrayList) request.getAttribute("message")).get(0)%> </p>
                                        </div>
                                        <div class="row">
                                            <label style="text-align: justify" for="message">Message:</label><br />
                                            <p style="text-align: justify"><%= ((ArrayList) request.getAttribute("message")).get(1)%> </p>
                                        </div>


                                    </div>

                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

        </div>
    </div> 
</div>
</div>

<jsp:include page="footer.jsp" />


