<%--
  Created by IntelliJ IDEA.
  User: Dell
  Date: 2020-12-05
  Time: 7:27 a.m.
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>View Postings</title>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no" />
    <link rel="stylesheet" href="assets/css/main.css" />
    <noscript><link rel="stylesheet" href="assets/css/noscript.css" /></noscript>
</head>
<body class="is-preload">
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
            <ul>
                <li><a href="/" >Home</a></li>
                <li><a href="/myCars">My Cars</a></li>
                <li><a href="/myPostings">My Postings</a></li>
                <li><a href="/viewPostings">Rent Car</a></li>
                <li><a href="/myBookings" class="active">My Bookings</a></li>
                <li><a href="/logout">Logout</a></li>
            </ul>
        </ul>
    </nav>

    <!-- Main -->
    <div id="main">
        <div class="inner">
            <header id="inner">
                <h1>Your Booking</h1>
            </header>

            <section>
                <h3><c:out value="${requestScope.carPostingWrapperObj.car['company']}"/> <c:out value="${requestScope.carPostingWrapperObj.car['name']}"/></h3>
                <h3>Year <c:out value="${requestScope.carPostingWrapperObj.car['year']}" /></h3>
                <c:forEach items="${requestScope.carPostingWrapperObj.carPhotos}" var="carPhoto">
                    <img src="data:image/jpg;base64,${carPhoto}" width="250" height="250"/>
                </c:forEach>
                <p><c:out value="${requestScope.carPostingWrapperObj.carPosting['description']}" /></p>
                <h3>Date <c:out value="${requestScope.carPostingWrapperObj.carPosting['postingDate']}" /></h3>
                <h3>Price $<c:out value="${requestScope.carPostingWrapperObj.carPosting['price']}" /></h3>


                <form method="POST" action="/viewMyBooking" >
                    <input type="submit" value="Go Back" name="Back" class="primary" style="background-color: #EEC213;"/>
                    <input type="submit" value="Cancel Booking" name="Cancel" class="primary" style="background-color: #3498DB;"/>
                </form>
            </section>

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

</body>
</html>
