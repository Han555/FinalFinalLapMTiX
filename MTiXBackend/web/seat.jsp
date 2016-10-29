<%-- 
    Document   : seat
    Created on : Sep 20, 2016, 4:40:12 PM
    Author     : Student-ID
--%>



<!doctype html>

<html>
    <head>

        <title>Seats</title>

        <link href='http://fonts.googleapis.com/css?family=Lato:400,700' rel='stylesheet' type='text/css'>
        <link rel="stylesheet" type="text/css" href="lib/css/jquery.seat-charts.css">

        <style type="text/css">
            body {
                font-family: 'Lato', sans-serif;
            }
            a {
                color: #b71a4c;
            }
            .front-indicator {
                width: 145px;
             margin-left: auto; 
             margin-right: auto;
              //margin: 0 auto;
                margin-bottom: 30px;
                background-color: #000000;	
                color: #FFFFFF;
                text-align: center;
                padding: 3px;
                border-radius: 5px;
            }
            .wrapper {
                width: 100%;
                text-align: center;
            }
            .container {
                position: absolute;
                margin: 0 auto;
                //left:50%;
                text-align: center;
            }
            
            div.seatCharts-cell {
                //  color: #182C4E;
                height: 21px;
                width: 21px;
                line-height: 21px;
                font-size: 9px;

            }
            div.seatCharts-seat {
                color: #FFFFFF;
                cursor: pointer;	
            }
            div.seatCharts-row {
                height: 35px;
            }
            //   div.seatCharts-seat.available {
            //      background-color: #B9DEA0;

            //  }
            div.seatCharts-seat.available.first-class {
                /* 	background: url(vip.png); */
                background-color: #3a78c3;
            }
            div.seatCharts-seat.focused {
                background-color: #76B474;
            }
            div.seatCharts-seat.selected {
                background-color: #3a78c3;
            }
            div.seatCharts-seat.unavailable {
                background-color: #472B34;
            }
            div.seatCharts-container {
               // border-right: 1px dotted #adadad;
                 width:auto;
                 // padding: 20px;
                margin-left: auto; 
                margin-right: auto;
            }
          
           
        </style>
    </head>

    <% String areaId = request.getParameter("id");%>
    <body>

        <div class="wrapper">
            <div class="container">
                <div id="seat-map">
                    <div class="front-indicator">Stage</div>

                </div>

            </div>
        </div>

        <script src="http://code.jquery.com/jquery-1.11.0.min.js"></script>
        <script src="lib/js/jquery.seat-charts.js"></script>

        <script>
            var sectionMap = [];
            var firstSeatLabel = 1;

            if (<%=areaId%> == 1) {
               // alert('hi');
                sectionMap = [
                    'nnnnnnnnnnnnnnnnnnnnnnnnnnnnnn',
                    'nnnnnnnnnnnnnnnnnnnnnnnnnnnnnn',
                    'nnnnnnnnnnnnnnnnnnnnnnnnnnnnnn',
                    '_nnnnnnnnnnnnnnnnnnnnnnnnnnnn',
                    '_nnnnnnnnnnnnnnnnnnnnnnnnnnnn',
                    '_nnnnnnnnnnnnnnnnnnnnnnnnnnnn',
                    '_nnnnnnnnnnnnnnnnnnnnnnnnnnnn',
                    '_nnnnnnnnnnnnnnnnnnnnnnnnnnnn',
                    '_nnnnnnnnnnnnnnnnnnnnnnnnnnnn',
                    '_nnnnnnnnnnnnnnnnnnnnnnnnnnnn',
                    '_nnnnnnnnnnnnnnnnnnnnnnnnnnnn',
                    '_nnnnnnnnnnnnnnnnnnnnnnnnnnnn',
                    '_nnnnnnnnnnnnnnnnnnnnnnnnnnnn',
                    '_nnnnnnnnnnnnnnnnnnnnnnnnnnnn',
                    '_nnnnnnnnnnnnnnnnnnnnnnnnnnnn', 
                    '___nnnnnnnnnnnnnnnnnnnnnnnn',
                    '___nnnnnnnnnnnnnnnnnnnnnnnn',
                    '____nnnnnnnnnnnnnnnnnnnnnn',
                    '____nnnnnnnnnnnnnnnnnnnnnn',
                    '____nnnnnnnnnnnnnnnnnnnnnn',
                    '_____nnnnnnnnnnnnnnnnnnnn',
                    '______nnnnnnnnnnnnnnnnnn',
                    '________nnnnnnnnnnnnnn',
                    '___________nnnnnnnn',
                    
               
                ];
            } else if (<%=areaId%> == 2) {
               // alert('hi');
                sectionMap = [
                    
                    '___________nnnnn____________',
                    '___________nnnnn____________',
                    '___________nnnnn____________',
                    '___________nnnnn____________',
                    '___________nnnnn____________',
                    '___________nnnnn____________',
                    '___________nnnnn____________',
                    '___________nnnnn____________',
                    '___________nnnnn____________',
                    '___________nnnnn____________',
                    '___________nnnnn____________',
                    '___________nnnnn____________',
                    '___________nnnnnn___________',
                    '___________nnnnnn___________',
                    '___________nnnnnn___________',
                    '___________nnnnnn___________',
                    '____________nnnnn___________',
                    '_____________nnnn___________',
                    '_____________nnnn___________',
                    '______________nnn___________',
                   
                    
               
                ];
            } else if (<%=areaId%> == 3) {
               // alert('hi');
                sectionMap = [
                    
                    
                    '_______nnnnnnnnnnnnnnnn_______',
                    '______nnnnnnnnnnnnnnnnnn______',
                    '_____nnnnnnnnnnnnnnnnnnnn_____',
                    '____nnnnnnnnnnnnnnnnnnnnnn____',
                    '___nnnnnnnnnnnnnnnnnnnnnnnn___',
                    '__nnnnnnnnnnnnnnnnnnnnnnnnnn__',
                    '__nnnnnnnnnnnnnnnnnnnnnnnnnn__',
                    '_nnnnnnnnnnnnnnnnnnnnnnnnnnnn_',
                    'nnnnnnnnnnnnnnnnnnnnnnnnnnnnnn',
                    'nnnnnnnnnnnnnnnnnnnnnnnnnnnnnn',
                    
               
                ];
            } else if (<%=areaId%> == 4) {
               // alert('hi');
                sectionMap = [
                    
                   
                    '___________nnnnn____________',
                    '___________nnnnn____________',
                    '___________nnnnn____________',
                    '___________nnnnn____________',
                    '___________nnnnn____________',
                    '___________nnnnn____________',
                    '___________nnnnn____________',
                    '___________nnnnn____________',
                    '___________nnnnn____________',
                    '___________nnnnn____________',
                    '___________nnnnn____________',
                    '___________nnnnn____________',
                    '__________nnnnnn____________',
                    '__________nnnnnn____________',
                    '__________nnnnnn____________',
                    '__________nnnnnn____________',
                    '__________nnnnn_____________',
                    '__________nnnn______________',
                    '__________nnnn______________',
                    '__________nnn_______________',
                   
               
                ];
            } else if (<%=areaId%> == 5) {
               // alert('hi');
                sectionMap = [
                    
                    '_____________n_______________',
                    '_____________n_______________',
                    '_____________n_______________',
                    '_____________n_______________',
                    '_____________n_______________',
                   
                    
                    
                    
               
                ];
            }else if (<%=areaId%> == 6) {
               // alert('hi');
                sectionMap = [
                    
                    '_____________n_______________',
                    '_____________n_______________',
                    '_____________n_______________',
                    '_____________n_______________',
                    '_____________n_______________',
                    '_____________n_______________',
                    '_____________n_______________',
                    '_____________n_______________',
                   
                    
                    
               
                ];
            }else if (<%=areaId%> == 7) {
               // alert('hi');
                sectionMap = [
                    
                    '_____________n_______________',
                    '_____________n_______________',
                    '_____________n_______________',
                    '_____________n_______________',
                    '_____________n_______________',
                    '_____________n_______________',
                    '_____________n_______________',
                    '_____________n_______________',
                    '_____________n_______________',
                    '_____________n_______________',
                    
               
                ];
            }else if (<%=areaId%> == 8) {
               // alert('hi');
                sectionMap = [
                   
                    '_____________n_______________',
                    '_____________n_______________',
                    '_____________n_______________',
                    '_____________n_______________',
                    '_____________n_______________',
                    '_____________n_______________',
                    '_____________n_______________',
                    '_____________n_______________',
                    
                    
               
                ];
            }else if (<%=areaId%> == 9) {
               // alert('hi');
                sectionMap = [
                   
                    '_____________n_______________',
                    '_____________n_______________',
                    '_____________n_______________',
                    '_____________n_______________',
                    '_____________n_______________',
                    '_____________n_______________',
                    '_____________n_______________',
                    '_____________n_______________',
                    '_____________n_______________',
                    '_____________n_______________',
                    '_____________n_______________',
                    '_____________n_______________',
                    
               
                ];
            }else if (<%=areaId%> == 10) {
               // alert('hi');
                sectionMap = [
                    
                    'nnn________________________nnn',
                    '_nnnnn__________________nnnnn_',
                    '__nnnnn________________nnnnn__',
                    '____nnnnnnnnnnnnnnnnnnnnnn____',
                    '_____nnnnnnnnnnnnnnnnnnnn_____',
                    '______nnnnnnnnnnnnnnnnnn______',
                    '________nnnnnnnnnnnnnn________',
                    
               
                ];
            }else if (<%=areaId%> == 11) {
               // alert('hi');
                sectionMap = [
                    
                    '_____________n_______________',
                    '_____________n_______________',
                    '_____________n_______________',
                    '_____________n_______________',
                    '_____________n_______________',
                    '_____________n_______________',
                    '_____________n_______________',
                    '_____________n_______________',
                    '_____________n_______________',
                    '_____________n_______________',
                    '_____________n_______________',
                    '_____________n_______________',
                    
               
                ];
            }else if (<%=areaId%> == 12) {
               // alert('hi');
                sectionMap = [
                    
                    '_____________n_______________',
                    '_____________n_______________',
                    '_____________n_______________',
                    '_____________n_______________',
                    '_____________n_______________',
                    '_____________n_______________',
                    '_____________n_______________',
                    '_____________n_______________',
                    
               
                ];
            }else if (<%=areaId%> == 13) {
               // alert('hi');
                sectionMap = [
                    
                    '_____________n_______________',
                    '_____________n_______________',
                    '_____________n_______________',
                    '_____________n_______________',
                    '_____________n_______________',
                    '_____________n_______________',
                    '_____________n_______________',
                    '_____________n_______________',
                    '_____________n_______________',
                    '_____________n_______________',
                    
               
                ];
            }else if (<%=areaId%> == 14) {
               // alert('hi');
                sectionMap = [
                    
                    '_____________n_______________',
                    '_____________n_______________',
                    '_____________n_______________',
                    '_____________n_______________',
                    '_____________n_______________',
                    '_____________n_______________',
                    '_____________n_______________',
                    '_____________n_______________',
                    
               
                ];
            }else if (<%=areaId%> == 15) {
               // alert('hi');
                sectionMap = [
                    
                    '_____________n_______________',
                    '_____________n_______________',
                    '_____________n_______________',
                    '_____________n_______________',
                    '_____________n_______________',
                    
               
                ];
            }else if (<%=areaId%> == 16) {
               // alert('hi');
                sectionMap = [
                    
                    '____________nnnn_____________',
                    '____________nnnn_____________',
                    '____________nnnn_____________',
                    '____________nnnn_____________',
                    '____________nnnn_____________',
                    '____________nnnn_____________',
                    '____________nnnn_____________',
                    '____________nnnn_____________',
                    '____________nnnn_____________',
                    '____________nnnn_____________',
                    '____________nnnn_____________',
                    '____________nnnn_____________',
                    '____________nnnn_____________',
                    '____________nnnn_____________',
                    '____________nnnn_____________',
                    
                    
               
                ];
            }else if (<%=areaId%> == 17) {
               // alert('hi');
                sectionMap = [
                    'nnn________________________nnn',
                    'nnnnnn__________________nnnnnn',
                    'nnnnnnn________________nnnnnnn',
                    'nnnnnnnnnnnnnnnnnnnnnnnnnnnnnn',
                    'nnnnnnnnnnnnnnnnnnnnnnnnnnnnnn',
                    'nnnnnnnnnnnnnnnnnnnnnnnnnnnnnn',
                    '_nnnnnnnnnnnnnnnnnnnnnnnnnnnn_',
                    '__nnnnnnnnnnnnnnnnnnnnnnnnnn__',
                    '___nnnnnnnnnnnnnnnnnnnnnnnn___',
                    '____nnnnnnnnnnnnnnnnnnnnnn____',
                    '______nnnnnnnnnnnnnnnnnn______',
                    
               
                ];
            }else if (<%=areaId%> == 18) {
               // alert('hi');
                sectionMap = [
                    
                    '____________nnnn_____________',
                    '____________nnnn_____________',
                    '____________nnnn_____________',
                    '____________nnnn_____________',
                    '____________nnnn_____________',
                    '____________nnnn_____________',
                    '____________nnnn_____________',
                    '____________nnnn_____________',
                    '____________nnnn_____________',
                    '____________nnnn_____________',
                    '____________nnnn_____________',
                    '____________nnnn_____________',
                    '____________nnnn_____________',
                    '____________nnnn_____________',
                    '____________nnnn_____________',
                    
                    
               
                ];
            }else if (<%=areaId%> == 19) {
               // alert('hi');
                sectionMap = [
                    'nnn________________________nnn',
                    'nnnn______________________nnnn',
                    'nnnnn____________________nnnnn',
                    'nnnnnn__________________nnnnnn',
                    'nnnnnnnnnnnnnnnnnnnnnnnnnnnnnn',
                    'nnnnnnnnnnnnnnnnnnnnnnnnnnnnnn',
                    'nnnnnnnnnnnnnnnnnnnnnnnnnnnnnn',
                    'nnnnnnnnnnnnnnnnnnnnnnnnnnnnnn',
                    'nnnnnnnnnnnnnnnnnnnnnnnnnnnnnn',
                    'nnnnnnnnnnnnnnnnnnnnnnnnnnnnnn',
                    'nnnnnnnnnnnnnnnnnnnnnnnnnnnnnn',
                    '_nnnnnnnnnnnnnnnnnnnnnnnnnnnn_',
                    '_nnnnnnnnnnnnnnnnnnnnnnnnnnnn_',
                    '_nnnnnnnnnnnnnnnnnnnnnnnnnnnn_',
                    
                    
               
                ];
            }else if (<%=areaId%> == 20) {
               // alert('hi');
                sectionMap = [
                    
                    '_____________nn______________',
                    '_____________nn______________',
                    '_____________nn______________',
                    '____________nnnn_____________',
                    '____________nnnn_____________',
                    '____________nnnn_____________',
                    '____________nnnn_____________',
                    '____________nnnn_____________',
                    '____________nnnn_____________',
                   
                    
                    
               
                ];
            }else if (<%=areaId%> == 21) {
               // alert('hi');
                sectionMap = [
                    
                    '_____________nn______________',
                    '_____________nn______________',
                    '_____________nn______________',
                    '____________nnnn_____________',
                    '____________nnnn_____________',
                    '____________nnnn_____________',
                    '____________nnnn_____________',
                    '____________nnnn_____________',
                    '____________nnnn_____________',
                    
               
                ];
            }else if (<%=areaId%> == 22) {
               // alert('hi');
                sectionMap = [
                    
                    '_____________n_______________',
                    '_____________n_______________',
                    
                    
               
                ];
            }else if (<%=areaId%> == 23) {
               // alert('hi');
                sectionMap = [
                   
                    '_____________n_______________',
                    '_____________n_______________',
                    
               
                ];
            }else if (<%=areaId%> == 24) {
               // alert('hi');
                sectionMap = [
                    
                    '_____________nn______________',
                    '____________nnn______________',
                    
               
                ];
            }else if (<%=areaId%> == 25) {
               // alert('hi');
                sectionMap = [
                    
                    '_____________nn______________',
                    '____________nnn______________',
                    
               
                ];
            }else if (<%=areaId%> == 26) {
               // alert('hi');
                sectionMap = [
                    
                  '_____________nn______________',
                  '____________nnn______________',
                    
               
                ];
            }else if (<%=areaId%> == 27) {
               // alert('hi');
                sectionMap = [
               
                    '_____________nn______________',
                    '____________nnn______________',
                    
               
                ];
            }
            $(document).ready(function () {

                var sc = $('#seat-map').seatCharts({
                    map: sectionMap,
                    seats: {
                        n: {
                            price: 100,
                            classes: 'first-class', //your custom CSS class
                            category: 'Standard'

                        }


                    },
                    naming: {
                        top: false,
                        getLabel: function (character, row, column) {
                            return firstSeatLabel++;
                        },
                    },
                    legend: {
                        node: $('#legend'),
                        items: [
                            ['n', 'available', 'Standard']
                        ]
                    },
                    click: function () {
                        if (this.status() == 'available') {
                            //let's create a new <li> which we'll add to the cart items
                            $('<li>' + this.data().category + ' Seat # ' + this.settings.label + ': <b>$' + this.data().price + '</b> <a href="#" class="cancel-cart-item">[cancel]</a></li>')
                                    .attr('id', 'cart-item-' + this.settings.id)
                                    .data('seatId', this.settings.id);



                            return 'selected';
                        } else if (this.status() == 'selected') {


                            //seat has been vacated
                            return 'available';
                        } else if (this.status() == 'unavailable') {
                            //seat has been already booked
                            return 'unavailable';
                        } else {
                            return this.style();
                        }
                    }
                });



                //let's pretend some seats have already been booked
                sc.status('unavailable');

            });



        </script>


    </body>
</html>





