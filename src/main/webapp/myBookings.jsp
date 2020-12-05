<%--
  Created by IntelliJ IDEA.
  User: Harman.Malhotra
  Date: 12/4/2020
  Time: 1:03 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>My Bookings</title>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no" />
    <link rel="stylesheet" href="assets/css/main.css" />
    <noscript><link rel="stylesheet" href="assets/css/noscript.css" /></noscript>
    <link rel="stylesheet" href="assets/css/snackbar.css" />
</head>
<body class="is-preload" onload="afterPageLoad()">
<!-- Wrapper -->
<div id="wrapper">

    <!-- Header -->
    <header id="header">
        <div class="inner">

            <!-- Logo -->
            <a href="/" class="logo">
                <span class="fa fa-car"></span> <span class="title">To Car Car</span>
            </a>

            <!-- Nav -->
            <nav>
                <ul>
                    <li><a href="#menu">Menu</a></li>
                </ul>
            </nav>

        </div>
    </header>

    <!-- Menu -->
    <nav id="menu">
        <h2>Menu</h2>
        <ul>
            <li><a href="/" >Home</a></li>
            <li><a href="/myCars">My Cars</a></li>
            <li><a href="/myPostings">My Postings</a></li>
            <li><a href="/viewPostings">Rent Car</a></li>
            <li><a href="/myBookings" class="active">My Bookings</a></li>
            <li><a href="/logout">Logout</a></li>
        </ul>
    </nav>

    <div id="snackbar">Some text some message..</div>

    <!-- Main -->
    <div id="main">
        <div class="inner">
            <header id="inner">
                <h1>My Upcoming Bookings</h1>
            </header>
            <table class="styled-table">
                <thead>
                <tr>
                    <th>User Name</th>
                    <th>Company</th>
                    <th>Name</th>
                    <th>Year</th>
                    <th>Date(YYYY-MM-DD)</th>
                    <th>Price</th>
                    <th>View</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${requestScope.myUpcomingBookings}" var="postingObj">
                    <tr>
                        <td>${postingObj.user['firstName']} ${postingObj.user['lastName']}</td>
                        <td>${postingObj.car['company']}</td>
                        <td>${postingObj.car['name']}</td>
                        <td>${postingObj.car['year']}</td>
                        <td>${postingObj.carPosting['postingDate']}</td>
                        <td>$${postingObj.carPosting['price']}</td>
                        <td>
                            <a href="/viewMyBooking?carPostingId=${postingObj.carPosting['carPostingId']}" style="background-color: #585858; color: white; padding: 0.5em 0.5em;" > View </a>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>

            <br>
            <br>

            <header id="inner2">
                <h1>My Old Bookings</h1>
            </header>
            <table class="styled-table">
                <thead>
                <tr>
                    <th>User Name</th>
                    <th>Company</th>
                    <th>Name</th>
                    <th>Year</th>
                    <th>Date(YYYY-MM-DD)</th>
                    <th>Price</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${requestScope.myOldBookings}" var="postingObj">
                    <tr>
                        <td>${postingObj.user['firstName']} ${postingObj.user['lastName']}</td>
                        <td>${postingObj.car['company']}</td>
                        <td>${postingObj.car['name']}</td>
                        <td>${postingObj.car['year']}</td>
                        <td>${postingObj.carPosting['postingDate']}</td>
                        <td>$${postingObj.carPosting['price']}</td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>

    <!-- Footer -->
    <footer id="footer">
        <div class="inner">
            <ul class="copyright">
                <li>&copy; 2020 To Car Car | All rights reserved.</li>
            </ul>
        </div>
    </footer>

</div>

<!-- Scripts -->
<script src="assets/js/jquery.min.js"></script>
<script src="assets/js/jquery.scrolly.min.js"></script>
<script src="assets/js/jquery.scrollex.min.js"></script>
<script src="assets/js/browser.min.js"></script>
<script src="assets/js/breakpoints.min.js"></script>
<script src="assets/js/util.js"></script>
<script src="assets/js/main.js"></script>
<script>
    function afterPageLoad(){
        var url_string = window.location.href;
        var url = new URL(url_string);

        var afterCarBook = url.searchParams.get("afterCarBook");
        var afterCancelBooking = url.searchParams.get("afterCancelBooking");

        if(afterCarBook){
            showSnackbar("Congrats, You have successfully booked the car!", "#2ecc72")
        }

        else if(afterCancelBooking){
            showSnackbar("You have successfully cancelled the booking!", "#EEC213")
        }

    }

    function showSnackbar(message, colour){
        let snackbar = document.getElementById("snackbar");
        snackbar.innerText = message;
        snackbar.style.backgroundColor = colour
        snackbar.className = "show";
        setTimeout(function(){
            snackbar.className = snackbar.className.replace("show", "");
        }, 3000);
    }
</script>
</body>
</html>




