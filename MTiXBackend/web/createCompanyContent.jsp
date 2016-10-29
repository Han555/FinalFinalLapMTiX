<%-- 
    Document   : createCompanyContent
    Created on : 19 Oct, 2016, 10:05:46 AM
    Author     : JingYing
--%>

<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<!doctype html>
<jsp:include page="header2.jsp" />
<!-- Main Content -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>

<div class="container-fluid">
    <c:url var="formAction"  value="/ContentBackController?action=companyInfoCreated" />
    <div class="side-body padding-top">
        <form id="contact_form" action="${formAction}" enctype="multipart/form-data" method="POST">
            <div class="row">
                <div class="col-md-12">
                    <div class="card">       
                        <div class="header">
                            <h4 class="title">Create Company Content</h4>   
                        </div>
                        <table align="left">
                            <tr>
                                <td>&nbsp</td>                     
                            </tr>
                            <tr><h5><b>Company Image</b></h5> </tr>
                            <tr>
                            <img id="previewHolder" src="img/contentManagement/placeholder.png" alt="Place your image here" width="250px" height="250px"/>
                            <input type="file" name="filePhoto" value="" id="filePhoto" required="true">
                            </tr>
                        </table>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-md-12">
                    <div class="card">  
                        <br>
                        <table>
                            <tr>
                            <label>Mission : </label>
                            <input type="text" name="mission" required="true" class="form-control">
                            </tr><br>
                            <tr>
                            <label>Vision : </label>
                            <input type="text" name="vision" required="true" class="form-control">
                            </tr><br>
                            <tr>
                            <label>About Us : </label>
                            <textarea class="form-control" required="true" name="aboutUs" cols="1" rows="7"></textarea>              
                            </tr><br>
                            <tr>
                            <label>Contact Details : </label>
                            <textarea class="form-control" required="true" name="contactDetails" cols="1" rows="7"></textarea>              
                            </tr><br>
                            <tr>
                            <label>Career Opportunity : </label>
                            <textarea class="form-control" required="true" name="career" cols="1" rows="7"></textarea>              
                            </tr><br>
                            <tr>
                            <label>Other Details : </label>
                            <textarea class="form-control" required="true" name="others" cols="1" rows="7"></textarea>              
                            </tr><br>
                            <tr>
                                <td align="center"><input type="submit" value="Submit"/></td>
                            <input type="hidden" id="ext" name="ext">
                            </tr>
                        </table>
                    </div>
                </div>
            </div> 
        </form>
    </div>
</div>
<script>
    function readURL(input) {
        var ext = $('#filePhoto').val().split('.').pop().toLowerCase();
        if ($.inArray(ext, ['gif', 'png', 'jpg', 'jpeg']) == -1) {
            alert('Invalid File Type! Please upload an image file');
            $("#filePhoto").val('');

        } else {
            if (input.files && input.files[0]) {
                $("#ext").val(ext);
                var reader = new FileReader();
                reader.onload = function (e) {
                    $('#previewHolder').attr('src', e.target.result);
                }

                reader.readAsDataURL(input.files[0]);
            }
        }
    }

    $("#filePhoto").change(function () {
        readURL(this);
    });

</script>


<jsp:include page="footer.jsp" />
