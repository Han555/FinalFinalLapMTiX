<%-- 
    Document   : verifyCustomer
    Created on : Oct 27, 2016, 4:30:21 PM
    Author     : catherinexiong
--%>

<%@page import="java.util.Random"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<jsp:include page="header.jsp" />
<!-- FormValidation CSS file -->
<link rel="stylesheet" href="/vendor/formvalidation/dist/css/formValidation.min.css">
<link rel="stylesheet" type="text/css" href="lib/css/intlTelInput.css">
<script type="text/javascript" src="lib/js/jquery.autotab.min.js"></script>
<script type="text/javascript" src="lib/js/intlTelInput.js"></script>

<div class="container">
    <div class="main">
        <!-- start registration -->



        <script>
            (function () {

                // Create input element for testing
                var inputs = document.createElement('input');

                // Create the supports object
                var supports = {};

                supports.autofocus = 'autofocus' in inputs;
                supports.required = 'required' in inputs;
                supports.placeholder = 'placeholder' in inputs;

                // Fallback for autofocus attribute
                if (!supports.autofocus) {

                }

                // Fallback for required attribute
                if (!supports.required) {

                }

                // Fallback for placeholder attribute
                if (!supports.placeholder) {

                }

                // Change text inside send button on submit
                var send = document.getElementById('register-submit');
                if (send) {
                    send.onclick = function () {
                        this.innerHTML = '...Sending';
                    }
                }

            })();
        </script>

        <!-- Form -->
        

        <!-- /Form -->



        
                <!-- Form -->
                <c:if test="${accountverified == 'true'}">
                    <div class="registration_left">
            <h2>Please Log in</h2>
            <div class="registration_form">
                
                    <c:if test="${nouser == 'nouser'}">
                    <div style="color:red">Username is not exist in the system!</div>
                </c:if>
                <c:if test="${mismatch == 'mismatch'}">
                    <div style="color:red">Passwords mismatch</div>
                </c:if>
                        <form id="registration_form" action="Controller?action=customerDoLogin" method="post">
                            <div>
                                <label>
                                    <input placeholder="email:" name="userName" type="email" tabindex="3" required>
                                </label>
                            </div>
                            <div>
                                <label>
                                    <input placeholder="password" name="password" type="password" tabindex="4" required>
                                </label>
                            </div>						
                            <div>
                                <input type="submit" value="sign in" id="register-submit">
                            </div>
                            <div class="forget">
                                <a href="#">forgot your password</a>
                            </div>
                        </form>
                    </c:if>
                <c:if test="${accountverified == 'false'}" >
                    <div class="registration_left">
            <h2>Please Change Your Password</h2>
            <div class="registration_form">
                <c:if test="${oldpass == 'true'}">
                    <div style="color:red">Old Password mismatch!</div>
                </c:if>
                <c:if test="${matchpass == 'true'}">
                    <div style="color:red">New Passwords mismatch!</div>
                </c:if>
                <form id="registration_form" action="Controller?action=verifyCustomer1" method="post">

                    <div>
                        <label>
                            <input placeholder="old password" name="oldPass" type="password" tabindex="4" required>
                        </label>
                    </div>
                    <div>
                        <label>
                            <input placeholder="new password" name="newPass" type="password" tabindex="4" required>
                        </label>
                    </div>
                    <div>
                        <label>
                            <input placeholder="retype new password" name="newPass2" type="password" tabindex="4" required>
                        </label>
                    </div>
                   <c:if test="${oldpass == 'true'}">
                        <input type="hidden" name="userName" value=<%= request.getAttribute("verifyUser")%> />
                    </c:if>
                    <c:if test="${matchpass == 'true'}">
                        <input type="hidden" name="userName" value=<%= request.getAttribute("verifyUser")%>  />
                    </c:if>
                    <div>
                        <label>
                            <input type="hidden" name="userName" tabindex="4" value=<%=request.getParameter("name")%>
                        </label>
                    </div>
                    <div>
                        <input type="submit" value="Change" id="register-submit">
                    </div>
                    <div class="forget">
                        <a href="#">forgot your password</a>
                    </div>
                </form>
                        </c:if>
                <!-- /Form -->
            </div>
        </div>

        <div class="clearfix"></div>
    </div>
</div>
<script>
    $(document).ready(function () {
        $('#date1 input').autotab_magic().autotab_filter('numeric');
        $("#phone").intlTelInput({
            // allowDropdown: false,
            // autoHideDialCode: false,
            // autoPlaceholder: "off",
            // dropdownContainer: "body",
            // excludeCountries: ["us"],
            geoIpLookup: function (callback) {
                $.get("http://ipinfo.io", function () {
                }, "jsonp").always(function (resp) {
                    var countryCode = (resp && resp.country) ? resp.country : "";
                    callback(countryCode);
                });
            },
            initialCountry: "auto",
            // nationalMode: false,
            // numberType: "MOBILE",
            // onlyCountries: ['us', 'gb', 'ch', 'ca', 'do'],
            //preferredCountries: ['cn', 'jp'],
            // separateDialCode: true,
            utilsScript: "lib/js/utils.js"});




    });
</script>
<jsp:include page="footer.jsp" />