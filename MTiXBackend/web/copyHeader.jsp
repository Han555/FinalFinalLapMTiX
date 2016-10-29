<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>

    <head>
        <title>MTiX Backend System</title>
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <!-- Fonts -->
        <link href='http://fonts.googleapis.com/css?family=Roboto+Condensed:300,400' rel='stylesheet' type='text/css'>
        <link href='http://fonts.googleapis.com/css?family=Lato:300,400,700,900' rel='stylesheet' type='text/css'>
        <!-- CSS Libs -->
        <link rel="stylesheet" type="text/css" href="lib/css/bootstrap.min.css">

        <link rel="stylesheet" type="text/css" href="lib/css/font-awesome.min.css">
        <link rel="stylesheet" type="text/css" href="lib/css/animate.min.css">
        <link rel="stylesheet" type="text/css" href="lib/css/bootstrap-switch.min.css">
        <link rel="stylesheet" type="text/css" href="lib/css/checkbox3.min.css">
        <link rel="stylesheet" type="text/css" href="lib/css/jquery.dataTables.min.css">
        <link rel="stylesheet" type="text/css" href="lib/css/dataTables.bootstrap.css">
        <link rel="stylesheet" type="text/css" href="lib/css/select2.min.css">
        <link rel="stylesheet" type="text/css" href="lib/css/jquery.seat-charts.css">
        <!-- CSS App -->
        <link rel="stylesheet" type="text/css" href="css/style.css">
        <link rel="stylesheet" type="text/css" href="css/daterangepicker.css">
        <link rel="stylesheet" type="text/css" href="css/themes/flat-blue.css">

        <!-- Javascript Libs -->
        <script type="text/javascript" src="lib/js/jquery.min.js"></script>
        <script type="text/javascript" src="lib/js/animatedModal.js"></script>
        <script type="text/javascript" src="lib/js/bootstrap.min.js"></script>
        <script type="text/javascript" src="lib/js/jquery.seat-charts.js"></script>
        <script type="text/javascript" src="lib/js/Chart.min.js"></script>
        <script type="text/javascript" src="lib/js/bootstrap-switch.min.js"></script>
        <script type="text/javascript" src="lib/js/jquery.matchHeight-min.js"></script>
        <script type="text/javascript" src="lib/js/jquery.dataTables.min.js"></script>
        <script type="text/javascript" src="lib/js/dataTables.bootstrap.min.js"></script>
        <script type="text/javascript" src="lib/js/select2.full.min.js"></script>
        <script type="text/javascript" src="lib/js/ace/ace.js"></script>
        <script type="text/javascript" src="lib/js/ace/mode-html.js"></script>
        <script type="text/javascript" src="lib/js/ace/theme-github.js"></script>
        <!-- Javascript -->

        <script type="text/javascript" src="js/app.js"></script>
        <!--  <script type="text/javascript" src="js/index.js"></script> -->
        <script type="text/javascript" src="js/moment.min.js"></script>
        <!--   <script type="text/javascript" src="js/modal.js"></script> -->
        <script type="text/javascript" src="js/daterangepicker.js"></script>
    </head>

    <body class="flat-blue">
        <div class="app-container">
            <div class="row content-container">
                <nav class="navbar navbar-default navbar-fixed-top navbar-top">
                    <div class="container-fluid">
                        <div class="navbar-header">
                            <button type="button" class="navbar-expand-toggle">
                                <i class="fa fa-bars icon"></i>
                            </button>
                            <ol class="breadcrumb navbar-breadcrumb">
                                <li class="active">MTiX Backend System</li>
                            </ol>
                            <button type="button" class="navbar-right-expand-toggle pull-right visible-xs">
                                <i class="fa fa-th icon"></i>
                            </button>
                        </div>
                        <ul class="nav navbar-nav navbar-right">
                            <button type="button" class="navbar-right-expand-toggle pull-right visible-xs">
                                <i class="fa fa-times icon"></i>
                            </button>

                            <li class="dropdown profile">
                                <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false"><%= request.getAttribute("username")%> <span class="caret"></span></a>
                                <ul class="dropdown-menu animated fadeInDown">
                                    <li class="profile-img">
                                        <img src="img/profile/picjumbo.com_HNCK4153_resize.jpg" class="profile-img">
                                    </li>
                                    <li>
                                        <div class="profile-info">

                                            <h4 class="username"><%= request.getAttribute("username")%></h4>
                                            <p><%= request.getAttribute("username")%></p>
                                            <div class="btn-group margin-bottom-2x" role="group">
                                                <button type="button" class="btn btn-default"><i class="fa fa-user"></i> Profile</button>
                                                <c:url var="linkHref" value="/BackController?action=logout" />
                                                <a href="${linkHref}"> <button type="button" class="btn btn-default"><i class="fa fa-sign-out"></i> Logout</button></a>
                                            </div>
                                        </div>
                                    </li>
                                </ul>
                            </li>
                        </ul>
                    </div>
                </nav>
                <div class="side-menu sidebar-inverse">
                    <nav class="navbar navbar-default" role="navigation">
                        <div class="side-menu-container">
                            <div class="navbar-header">
                                <c:url var="linkHref" value="/Controller"/>
                                <a class="navbar-brand" href="${linkHref}">
                                    <div class="icon fa fa-paper-plane"></div>
                                    <div class="title">Property Management System</div>
                                </a>
                                <button type="button" class="navbar-expand-toggle pull-right visible-xs">
                                    <i class="fa fa-times icon"></i>
                                </button>
                            </div>
                            <ul class="nav navbar-nav">
                                <li class="active">
                                    <c:url var="linkHref" value="/BackController?action=home" />
                                    <a href="${linkHref}">
                                        <span class="icon fa fa-tachometer"></span><span class="title">Home</span>
                                    </a>
                                </li>
                                <c:if test="${(role == 'super administrator' || role == 'product manager')}">
                                    <li class="panel panel-default dropdown">
                                        <a data-toggle="collapse" href="#dropdown-element">
                                            <span class="icon fa fa-desktop"></span><span class="title">Product Management</span>
                                        </a>
                                        <!-- Dropdown level 1 -->
                                        <div id="dropdown-element" class="panel-collapse collapse">
                                            <div class="panel-body">
                                                <ul class="nav navbar-nav">
                                                    <li><c:url var="linkHref" value="/BackController?action=promotionMain" /><a href="${linkHref}">Promotion</a>
                                                    </li>
                                                    <li><c:url var="linkHref" value="/BackController?action=sessionMain" /><a href="${linkHref}">Sessions</a>
                                                    </li>
                                                    <li><c:url var="linkHref" value="/BackController?action=configuration" /><a href="${linkHref}">Seat Configuration</a>
                                                    </li>
                                                    
                                                </ul>
                                            </div>
                                        </div>
                                    </li>
                                </c:if>

                                <c:if test="${(role == 'super administrator' || role == 'property manager')}">
                                    <!-- Dropdown-->
                                    <li class="panel panel-default dropdown">
                                        <a data-toggle="collapse" href="#component-example">
                                            <span class="icon fa fa-cubes"></span><span class="title">Property Management</span>
                                        </a>
                                        <!-- Dropdown level 1 -->
                                        <div id="component-example" class="panel-collapse collapse">
                                            <div class="panel-body">
                                                <ul class="nav navbar-nav">
                                                    <li><c:url var="linkHref" value="/BackController?action=viewAllProperty" /><a href="${linkHref}">Seating Plan Management</a>
                                                    </li>
                                                    <li><c:url var="linkHref" value="/BackController?action=reservationSearch" /><a href="${linkHref}">Reservation Of Properties</a>
                                                    </li>
                                                    <li><c:url var="linkHref" value="/BackController?action=maintenance" /><a href="${linkHref}">Maintenance Schedule</a>
                                                    </li>
                                                    <li><c:url var="linkHref" value="/BackController?action=sessionMain" /><a href="${linkHref}">Manpower</a>
                                                    </li>
                                                    <li><c:url var="linkHref" value="/BackController?action=configuration" /><a href="${linkHref}">Equipment & Fixture</a>
                                                    </li>
                                                    <li><c:url var="linkHref" value="/BackController?action=foodOutlets" /><a href="${linkHref}">Food Outlets</a>
                                                    </li>
                                                </ul>
                                            </div>
                                        </div>
                                    </li>
                                </c:if>
                                <!-- Dropdown-->
                                <li class="panel panel-default dropdown">
                                    <a data-toggle="collapse" href="#dropdown-example">
                                        <span class="icon fa fa-slack"></span><span class="title">Finances</span>
                                    </a>
                                    <!-- Dropdown level 1 -->
                                    <div id="dropdown-example" class="panel-collapse collapse">
                                        <div class="panel-body">
                                            <ul class="nav navbar-nav">
                                                <li><a href="pages/login.html">Login</a>
                                                </li>
                                                <li><a href="pages/index.html">Landing Page</a>
                                                </li>
                                            </ul>
                                        </div>
                                    </div>
                                </li>
                                <!-- Dropdown-->
                                
                                <li>
                                    <a href="license.html">
                                        <span class="icon fa fa-thumbs-o-up"></span><span class="title">License</span>
                                    </a>
                                </li>
                            </ul>
                        </div>
                        <!-- /.navbar-collapse -->
                    </nav>
                </div>