<%-- 
    Document   : testTable
    Created on : Sep 12, 2016, 8:59:25 PM
    Author     : Student-ID
--%>

<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>testing table</title>

        <link href="https://cdn.datatables.net/1.10.12/css/jquery.dataTables.min.css" rel="stylesheet">
        <script type="text/javascript" src="https://code.jquery.com/jquery-1.12.3.js"></script>
        <script type="text/javascript" src="https://cdn.datatables.net/1.10.12/js/jquery.dataTables.min.js"></script>
        <script>$(document).ready(function () {
                $('#example').DataTable({
                    "pagingType": "full_numbers"
                });
            });
        </script>
    </head>
    <body>
        <table id="example" class="display" cellspacing="0" width="100%">
            <thead>
                <tr>
                    <th>Name</th>
                    <th>Position</th>
                    <th>Office</th>
                  
                </tr>
            </thead>
            <tfoot>
                <tr>
                    <th>Name</th>
                    <th>Position</th>
                    <th>Office</th>
                    
                </tr>
            </tfoot>
            <tbody>
                <tr>
                            <td>
                                <form id="verifyForm" name="verifyForm" action="${formAction}" method="post">
                                    <input type="hidden" name="messageid" value=<%= ((ArrayList) ((ArrayList) request.getAttribute("board")).get(0)).get(0)%> readonly="readonly" />
                                    <c:url var="formAction" value="/BackController" />
                                    <input type="submit" value="Read" /> 
                                </form></td>
                          
                            <td><%= ((ArrayList) ((ArrayList) request.getAttribute("board")).get(0)).get(1)%></td>
                            <td><span class="label label-danger pull-right">unread</span> </td>
                            
                        </tr>
                <tr>
                    <td>Garrett Winters</td>
                    <td>Accountant</td>
                    <td>Tokyo</td>
                   
                </tr>
                <tr>
                    <td>Ashton Cox</td>
                    <td>Junior Technical Author</td>
                    <td>San Francisco</td>
                    
                </tr>
                <tr>
                    <td>Cedric Kelly</td>
                    <td>Senior Javascript Developer</td>
                    <td>Edinburgh</td>
                    
                </tr>
                <tr>
                    <td>Airi Satou</td>
                    <td>Accountant</td>
                    <td>Tokyo</td>
                    
                </tr>
                <tr>
                    <td>Brielle Williamson</td>
                    <td>Integration Specialist</td>
                    <td>New York</td>
                   
                </tr>
                <tr>
                    <td>Herrod Chandler</td>
                    <td>Sales Assistant</td>
                    <td>San Francisco</td>
                   
                </tr>
                <tr>
                    <td>Rhona Davidson</td>
                    <td>Integration Specialist</td>
                    <td>Tokyo</td>
                    
                </tr>
                <tr>
                    <td>Colleen Hurst</td>
                    <td>Javascript Developer</td>
                    <td>San Francisco</td>
                    
                </tr>
                <tr>
                    <td>Sonya Frost</td>
                    <td>Software Engineer</td>
                    <td>Edinburgh</td>
                    
                </tr>
                <tr>
                    <td>Jena Gaines</td>
                    <td>Office Manager</td>
                    <td>London</td>
                    
                </tr>
                <tr>
                    <td>Quinn Flynn</td>
                    <td>Support Lead</td>
                    <td>Edinburgh</td>
                   
                </tr>
                <tr>
                    <td>Charde Marshall</td>
                    <td>Regional Director</td>
                    <td>San Francisco</td>
                    
                </tr>
                <tr>
                    <td>Haley Kennedy</td>
                    <td>Senior Marketing Designer</td>
                    <td>London</td>
                    
                </tr>
                <tr>
                    <td>Tatyana Fitzpatrick</td>
                    <td>Regional Director</td>
                    <td>London</td>
                    
                </tr>
                <tr>
                    <td>Michael Silva</td>
                    <td>Marketing Designer</td>
                    <td>London</td>
                    
                </tr>
                <tr>
                    <td>Paul Byrd</td>
                    <td>Chief Financial Officer (CFO)</td>
                    <td>New York</td>
                    
                </tr>
                <tr>
                    <td>Gloria Little</td>
                    <td>Systems Administrator</td>
                    <td>New York</td>
                    
                </tr>
                <tr>
                    <td>Bradley Greer</td>
                    <td>Software Engineer</td>
                    <td>London</td>
                    
                </tr>
                <tr>
                    <td>Dai Rios</td>
                    <td>Personnel Lead</td>
                    <td>Edinburgh</td>
                   
                </tr>
                <tr>
                    <td>Jenette Caldwell</td>
                    <td>Development Lead</td>
                    <td>New York</td>
                    
                </tr>
                <tr>
                    <td>Yuri Berry</td>
                    <td>Chief Marketing Officer (CMO)</td>
                    <td>New York</td>
                    
                </tr>
                <tr>
                    <td>Caesar Vance</td>
                    <td>Pre-Sales Support</td>
                    <td>New York</td>
                    
                </tr>
                <tr>
                    <td>Doris Wilder</td>
                    <td>Sales Assistant</td>
                    <td>Sidney</td>
                   
                </tr>
                <tr>
                    <td>Angelica Ramos</td>
                    <td>Chief Executive Officer (CEO)</td>
                    <td>London</td>
                    
                </tr>
                <tr>
                    <td>Gavin Joyce</td>
                    <td>Developer</td>
                    <td>Edinburgh</td>
                    
                </tr>
                <tr>
                    <td>Jennifer Chang</td>
                    <td>Regional Director</td>
                    <td>Singapore</td>
                    
                </tr>
                <tr>
                    <td>Brenden Wagner</td>
                    <td>Software Engineer</td>
                    <td>San Francisco</td>
                    
                </tr>
                <tr>
                    <td>Fiona Green</td>
                    <td>Chief Operating Officer (COO)</td>
                    <td>San Francisco</td>
                    
                </tr>
                <tr>
                    <td>Shou Itou</td>
                    <td>Regional Marketing</td>
                    <td>Tokyo</td>
                    
                </tr>
                <tr>
                    <td>Michelle House</td>
                    <td>Integration Specialist</td>
                    <td>Sidney</td>
                    
                </tr>
                <tr>
                    <td>Suki Burks</td>
                    <td>Developer</td>
                    <td>London</td>
                
                </tr>
                <tr>
                    <td>Prescott Bartlett</td>
                    <td>Technical Author</td>
                    <td>London</td>
                  
                </tr>
                <tr>
                    <td>Gavin Cortez</td>
                    <td>Team Leader</td>
                    <td>San Francisco</td>
                  
                </tr>
                <tr>
                    <td>Martena Mccray</td>
                    <td>Post-Sales support</td>
                    <td>Edinburgh</td>
                   
                </tr>
                <tr>
                    <td>Unity Butler</td>
                    <td>Marketing Designer</td>
                    <td>San Francisco</td>
                   
                </tr>
                <tr>
                    <td>Howard Hatfield</td>
                    <td>Office Manager</td>
                    <td>San Francisco</td>
                    
                </tr>
                <tr>
                    <td>Hope Fuentes</td>
                    <td>Secretary</td>
                    <td>San Francisco</td>
                    
                </tr>
                <tr>
                    <td>Vivian Harrell</td>
                    <td>Financial Controller</td>
                    <td>San Francisco</td>
                 
                </tr>
                <tr>
                    <td>Timothy Mooney</td>
                    <td>Office Manager</td>
                    <td>London</td>
                   
                </tr>
                <tr>
                    <td>Jackson Bradshaw</td>
                    <td>Director</td>
                    <td>New York</td>
                    
                </tr>
                <tr>
                    <td>Olivia Liang</td>
                    <td>Support Engineer</td>
                    <td>Singapore</td>
                   
                </tr>
                <tr>
                    <td>Bruno Nash</td>
                    <td>Software Engineer</td>
                    <td>London</td>
                    
                </tr>
                <tr>
                    <td>Sakura Yamamoto</td>
                    <td>Support Engineer</td>
                    <td>Tokyo</td>
                   
                </tr>
                <tr>
                    <td>Thor Walton</td>
                    <td>Developer</td>
                    <td>New York</td>
                   
                </tr>
                <tr>
                    <td>Finn Camacho</td>
                    <td>Support Engineer</td>
                    <td>San Francisco</td>
                  
                </tr>
                <tr>
                    <td>Serge Baldwin</td>
                    <td>Data Coordinator</td>
                    <td>Singapore</td>
                  
                </tr>
                <tr>
                    <td>Zenaida Frank</td>
                    <td>Software Engineer</td>
                    <td>New York</td>
                    
                </tr>
                <tr>
                    <td>Zorita Serrano</td>
                    <td>Software Engineer</td>
                    <td>San Francisco</td>
                    
                </tr>
                <tr>
                    <td>Jennifer Acosta</td>
                    <td>Junior Javascript Developer</td>
                    <td>Edinburgh</td>
                   
                </tr>
                <tr>
                    <td>Cara Stevens</td>
                    <td>Sales Assistant</td>
                    <td>New York</td>
                    
                </tr>
                <tr>
                    <td>Hermione Butler</td>
                    <td>Regional Director</td>
                    <td>London</td>
                  
                </tr>
                <tr>
                    <td>Lael Greer</td>
                    <td>Systems Administrator</td>
                    <td>London</td>
                    
                </tr>
                <tr>
                    <td>Jonas Alexander</td>
                    <td>Developer</td>
                    <td>San Francisco</td>
                   
                </tr>
                <tr>
                    <td>Shad Decker</td>
                    <td>Regional Director</td>
                    <td>Edinburgh</td>
                   
                </tr>
                <tr>
                    <td>Michael Bruce</td>
                    <td>Javascript Developer</td>
                    <td>Singapore</td>
                   
                </tr>
                <tr>
                    <td>Donna Snider</td>
                    <td>Customer Support</td>
                    <td>New York</td>
                    
                </tr>
            </tbody>
        </table>
        
         <table id="example" class="display" cellspacing="0" width="100%">
                    <thead>
                        <tr>
                            <th></th>
                            <th></th>
                            <th>Subject</th>
                            <th></th>
                            <th></th>
                            <th></th>
                        </tr>
                    </thead>
                    <tfoot>
                        <tr>
                            <th></th>
                            <th></th>
                            <th></th>
                            <th></th>
                            <th>Subject</th>
                            <th></th>
                        </tr>
                    </tfoot>
                    <tbody>
                        <tr >
                            <td >
                                <form id="verifyForm" name="verifyForm" action="${formAction}" method="post">
                                    <input type="hidden" name="messageid" value=<%= ((ArrayList) ((ArrayList) request.getAttribute("board")).get(0)).get(0)%> readonly="readonly" />
                                    <c:url var="formAction" value="/BackController" />
                                    <input type="submit" value="Read" /> 
                                </form></td>
                            <td ></td>
                            <td ><%= ((ArrayList) ((ArrayList) request.getAttribute("board")).get(0)).get(1)%></td>
                            <td ><span class="label label-danger pull-right">unread</span> </td>
                            <td ></td>
                            <td ></td>
                        </tr>

                        <tr >
                            <td >
                                <form id="verifyForm" name="verifyForm" action="${formAction}" method="post">
                                    <input type="hidden" name="messageid" value=<%= ((ArrayList) ((ArrayList) request.getAttribute("board")).get(0)).get(0)%> readonly="readonly" />
                                    <c:url var="formAction" value="/BackController" />
                                    <input type="submit" value="Read" /> 
                                </form></td>
                            <td ></td>
                            <td ><%= ((ArrayList) ((ArrayList) request.getAttribute("board")).get(0)).get(1)%></td>
                            <td ><span class="label label-danger pull-right">unread</span> </td>
                            <td ></td>
                            <td ></td>
                        </tr>
                        <tr >
                            <td >
                                <form id="verifyForm" name="verifyForm" action="${formAction}" method="post">
                                    <input type="hidden" name="messageid" value=<%= ((ArrayList) ((ArrayList) request.getAttribute("board")).get(0)).get(0)%> readonly="readonly" />
                                    <c:url var="formAction" value="/BackController" />
                                    <input type="submit" value="Read" /> 
                                </form></td>
                            <td ></td>
                            <td ><%= ((ArrayList) ((ArrayList) request.getAttribute("board")).get(0)).get(1)%></td>
                            <td ><span class="label label-danger pull-right">unread</span> </td>
                            <td ></td>
                            <td ></td>
                        </tr>
                        <tr >
                            <td >
                                <form id="verifyForm" name="verifyForm" action="${formAction}" method="post">
                                    <input type="hidden" name="messageid" value=<%= ((ArrayList) ((ArrayList) request.getAttribute("board")).get(0)).get(0)%> readonly="readonly" />
                                    <c:url var="formAction" value="/BackController" />
                                    <input type="submit" value="Read" /> 
                                </form></td>
                            <td ></td>
                            <td ><%= ((ArrayList) ((ArrayList) request.getAttribute("board")).get(0)).get(1)%></td>
                            <td ><span class="label label-danger pull-right">unread</span> </td>
                            <td ></td>
                            <td ></td>
                        </tr>
                        <tr >
                            <td >
                                <form id="verifyForm" name="verifyForm" action="${formAction}" method="post">
                                    <input type="hidden" name="messageid" value=<%= ((ArrayList) ((ArrayList) request.getAttribute("board")).get(0)).get(0)%> readonly="readonly" />
                                    <c:url var="formAction" value="/BackController" />
                                    <input type="submit" value="Read" /> 
                                </form></td>
                            <td ></td>
                            <td ><%= ((ArrayList) ((ArrayList) request.getAttribute("board")).get(0)).get(1)%></td>
                            <td ><span class="label label-danger pull-right">unread</span> </td>
                            <td ></td>
                            <td ></td>
                        </tr>
                        <tr >
                            <td >
                                <form id="verifyForm" name="verifyForm" action="${formAction}" method="post">
                                    <input type="hidden" name="messageid" value=<%= ((ArrayList) ((ArrayList) request.getAttribute("board")).get(0)).get(0)%> readonly="readonly" />
                                    <c:url var="formAction" value="/BackController" />
                                    <input type="submit" value="Read" /> 
                                </form></td>
                            <td ></td>
                            <td ><%= ((ArrayList) ((ArrayList) request.getAttribute("board")).get(0)).get(1)%></td>
                            <td ><span class="label label-danger pull-right">unread</span> </td>
                            <td ></td>
                            <td ></td>
                        </tr>
                        <tr >
                            <td >
                                <form id="verifyForm" name="verifyForm" action="${formAction}" method="post">
                                    <input type="hidden" name="messageid" value=<%= ((ArrayList) ((ArrayList) request.getAttribute("board")).get(0)).get(0)%> readonly="readonly" />
                                    <c:url var="formAction" value="/BackController" />
                                    <input type="submit" value="Read" /> 
                                </form></td>
                            <td ></td>
                            <td ><%= ((ArrayList) ((ArrayList) request.getAttribute("board")).get(0)).get(1)%></td>
                            <td ><span class="label label-danger pull-right">unread</span> </td>
                            <td ></td>
                            <td ></td>
                        </tr>
                        <tr >
                            <td >
                                <form id="verifyForm" name="verifyForm" action="${formAction}" method="post">
                                    <input type="hidden" name="messageid" value=<%= ((ArrayList) ((ArrayList) request.getAttribute("board")).get(0)).get(0)%> readonly="readonly" />
                                    <c:url var="formAction" value="/BackController" />
                                    <input type="submit" value="Read" /> 
                                </form></td>
                            <td ></td>
                            <td ><%= ((ArrayList) ((ArrayList) request.getAttribute("board")).get(0)).get(1)%></td>
                            <td ><span class="label label-danger pull-right">unread</span> </td>
                            <td ></td>
                            <td ></td>
                        </tr>
                        <tr >
                            <td >
                                <form id="verifyForm" name="verifyForm" action="${formAction}" method="post">
                                    <input type="hidden" name="messageid" value=<%= ((ArrayList) ((ArrayList) request.getAttribute("board")).get(0)).get(0)%> readonly="readonly" />
                                    <c:url var="formAction" value="/BackController" />
                                    <input type="submit" value="Read" /> 
                                </form></td>
                            <td ></td>
                            <td ><%= ((ArrayList) ((ArrayList) request.getAttribute("board")).get(0)).get(1)%></td>
                            <td ><span class="label label-danger pull-right">unread</span> </td>
                            <td ></td>
                            <td ></td>
                        </tr>
                        <tr >
                            <td >
                                <form id="verifyForm" name="verifyForm" action="${formAction}" method="post">
                                    <input type="hidden" name="messageid" value=<%= ((ArrayList) ((ArrayList) request.getAttribute("board")).get(0)).get(0)%> readonly="readonly" />
                                    <c:url var="formAction" value="/BackController" />
                                    <input type="submit" value="Read" /> 
                                </form></td>
                            <td ></td>
                            <td ><%= ((ArrayList) ((ArrayList) request.getAttribute("board")).get(0)).get(1)%></td>
                            <td ><span class="label label-danger pull-right">unread</span> </td>
                            <td ></td>
                            <td ></td>
                        </tr>
                        <tr >
                            <td >
                                <form id="verifyForm" name="verifyForm" action="${formAction}" method="post">
                                    <input type="hidden" name="messageid" value=<%= ((ArrayList) ((ArrayList) request.getAttribute("board")).get(0)).get(0)%> readonly="readonly" />
                                    <c:url var="formAction" value="/BackController" />
                                    <input type="submit" value="Read" /> 
                                </form></td>
                            <td ></td>
                            <td ><%= ((ArrayList) ((ArrayList) request.getAttribute("board")).get(0)).get(1)%></td>
                            <td ><span class="label label-danger pull-right">unread</span> </td>
                            <td ></td>
                            <td ></td>
                        </tr>
                        <tr >
                            <td >
                                <form id="verifyForm" name="verifyForm" action="${formAction}" method="post">
                                    <input type="hidden" name="messageid" value=<%= ((ArrayList) ((ArrayList) request.getAttribute("board")).get(0)).get(0)%> readonly="readonly" />
                                    <c:url var="formAction" value="/BackController" />
                                    <input type="submit" value="Read" /> 
                                </form></td>
                            <td ></td>
                            <td ><%= ((ArrayList) ((ArrayList) request.getAttribute("board")).get(0)).get(1)%></td>
                            <td ><span class="label label-danger pull-right">unread</span> </td>
                            <td ></td>
                            <td ></td>
                        </tr>
                        <tr >
                            <td >
                                <form id="verifyForm" name="verifyForm" action="${formAction}" method="post">
                                    <input type="hidden" name="messageid" value=<%= ((ArrayList) ((ArrayList) request.getAttribute("board")).get(0)).get(0)%> readonly="readonly" />
                                    <c:url var="formAction" value="/BackController" />
                                    <input type="submit" value="Read" /> 
                                </form></td>
                            <td ></td>
                            <td ><%= ((ArrayList) ((ArrayList) request.getAttribute("board")).get(0)).get(1)%></td>
                            <td ><span class="label label-danger pull-right">unread</span> </td>
                            <td ></td>
                            <td ></td>
                        </tr>
                        <tr >
                            <td >
                                <form id="verifyForm" name="verifyForm" action="${formAction}" method="post">
                                    <input type="hidden" name="messageid" value=<%= ((ArrayList) ((ArrayList) request.getAttribute("board")).get(0)).get(0)%> readonly="readonly" />
                                    <c:url var="formAction" value="/BackController" />
                                    <input type="submit" value="Read" /> 
                                </form></td>
                            <td ></td>
                            <td ><%= ((ArrayList) ((ArrayList) request.getAttribute("board")).get(0)).get(1)%></td>
                            <td ><span class="label label-danger pull-right">unread</span> </td>
                            <td ></td>
                            <td ></td>
                        </tr>
                        <tr >
                            <td >
                                <form id="verifyForm" name="verifyForm" action="${formAction}" method="post">
                                    <input type="hidden" name="messageid" value=<%= ((ArrayList) ((ArrayList) request.getAttribute("board")).get(0)).get(0)%> readonly="readonly" />
                                    <c:url var="formAction" value="/BackController" />
                                    <input type="submit" value="Read" /> 
                                </form></td>
                            <td ></td>
                            <td ><%= ((ArrayList) ((ArrayList) request.getAttribute("board")).get(0)).get(1)%></td>
                            <td ><span class="label label-danger pull-right">unread</span> </td>
                            <td ></td>
                            <td ></td>
                        </tr>
                        <tr >
                            <td >
                                <form id="verifyForm" name="verifyForm" action="${formAction}" method="post">
                                    <input type="hidden" name="messageid" value=<%= ((ArrayList) ((ArrayList) request.getAttribute("board")).get(0)).get(0)%> readonly="readonly" />
                                    <c:url var="formAction" value="/BackController" />
                                    <input type="submit" value="Read" /> 
                                </form></td>
                            <td ></td>
                            <td ><%= ((ArrayList) ((ArrayList) request.getAttribute("board")).get(0)).get(1)%></td>
                            <td ><span class="label label-danger pull-right">unread</span> </td>
                            <td ></td>
                            <td ></td>
                        </tr>
                        <tr >
                            <td >
                                <form id="verifyForm" name="verifyForm" action="${formAction}" method="post">
                                    <input type="hidden" name="messageid" value=<%= ((ArrayList) ((ArrayList) request.getAttribute("board")).get(0)).get(0)%> readonly="readonly" />
                                    <c:url var="formAction" value="/BackController" />
                                    <input type="submit" value="Read" /> 
                                </form></td>
                            <td ></td>
                            <td ><%= ((ArrayList) ((ArrayList) request.getAttribute("board")).get(0)).get(1)%></td>
                            <td ><span class="label label-danger pull-right">unread</span> </td>
                            <td ></td>
                            <td ></td>
                        </tr>

                    </tbody>
                </table>
    </body>
</html>
