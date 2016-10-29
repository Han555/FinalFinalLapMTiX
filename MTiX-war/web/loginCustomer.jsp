<%-- 
    Document   : loginCustomer
    Created on : Oct 26, 2016, 10:32:54 PM
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
        <div class="registration">
            <div class="registration_left">
                <h2>new user? <span> create an account </span></h2>
                <!-- [if IE] 
                    < link rel='stylesheet' type='text/css' href='ie.css'/>  
                 [endif] -->  

                <!-- [if lt IE 7]>  
                    < link rel='stylesheet' type='text/css' href='ie6.css'/>  
                <%
                    StringBuffer sb = new StringBuffer();
                    char[] chars = "abcdefghijklmnopqrstuvwxyz0123456789/?<>}{.,$#!%&*".toCharArray();
                    Random random = new Random();
                    for (int i = 1; i <= 5; i++) {
                        sb.append(chars[random.nextInt(chars.length)]);
                    }
                    String cap = new String(sb);
                %>  
                        <! [endif] -->  
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
                <div class="registration_form">
                    <!-- Form -->
                    <c:if test="${conflict == 'true'}">
                        <div style="color:red">User Email has already been registered!</div>
                    </c:if>
                    <c:if test="${mismatch == 'true'}">
                        <div style="color:red">Password Mismatch!</div>
                    </c:if>
                    <c:if test="${captcha == 'true'}">
                        <div style="color:red">Captcha entered incorrectly!</div>
                    </c:if>
                     <c:if test="${registered == 'true'}">
                        <div style="color:red" >Account has been Registered! Please verify it through your email!</div>
                    </c:if>
                    <form id="registration_form" action="Controller?action=regisCustomer" method="post">
                        <div>
                            <label>
                                <input placeholder="email address:" type="email" name="email" value="${email}" required>
                            </label>
                        </div>
                        <div>
                            <label>
                                <input placeholder="first name:" type="text" name="first" value="${first}" required autofocus>
                            </label>
                        </div>
                        <div>
                            <label>
                                <input placeholder="last name:" type="text" name="last" value="${last}" required autofocus>
                            </label>
                        </div>


                        <div>
                            <label>
                                <input placeholder="password" type="password" name="password" required>
                            </label>
                        </div>						
                        <div>
                            <label>
                                <input placeholder="retype password" type="password" name="password2" required>
                            </label>
                        </div>	
                        <label for="day">Date of Birth (autotab):</label>
                        <div id="date1" class="datefield" >


                            <input id="day" name="day" value="${day}" type="tel" maxlength="2" placeholder="DD" style="width:10%;display:inline;" required/> /              
                            <input id="month" name="month" value="${month}" type="tel" maxlength="2" placeholder="MM" style="width:10%;display:inline;" required/>/
                            <input id="year" name="year" value="${year}" type="tel" maxlength="4" placeholder="YYYY" style="width:15%;display:inline;" required/>

                        </div>
                        <input type="tel" name="phone" value="${phone}" id="phone" required> 
                        <div >

                            <script type ="text/javascript">
                                function validation() {
                                    var c = document.forms ["registerForm"]["cap1"].value;
                                    if (c == null || c == "")
                                    {
                                        alert("Please Enter Captcha");
                                        return false;
                                    }
                                }
                            </script> 

                            <table border="0">  
                                <tbody>  
                                    <tr>  
                                        <td>  
                                            <div style="background-color: aqua"><h2><s><i><font face="casteller"><%=cap%></font></i></s></h2></div>  
                                        </td>  
                                        <td><input type="text" name="cap1" placeholder="Captcha" value="" required /></td>  
                                        <td><input type="hidden" name="cap2" value='<%=cap%>' readonly="readonly" </td>  
                                    </tr>  
                                </tbody>  
                            </table> 
                        </div>
                        <div>
                            <input type="submit" value="create an account" id="register-submit">
                        </div>
                        <!--				<div class="sky-form">
                                                                <label class="checkbox"><input type="checkbox" name="checkbox" ><i></i>i agree to &nbsp;<a class="terms" href="#"> terms of service</a> </label>
                                                        </div>-->
                    </form>
                    <!-- /Form -->
                </div>
            </div>
           
          
                <div class="registration_left">
                    <h2>existing user</h2>
                    <div class="registration_form">
                        <!-- Form -->
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
                        <!-- /Form -->
                    </div>
                </div>
           
            <div class="clearfix"></div>
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