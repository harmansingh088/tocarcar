<%@ page import="models.Car" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: Harman.Malhotra
  Date: 12/1/2020
  Time: 11:33 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Phantom by HTML5 UP</title>
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
            <a href="index.jsp" class="logo">
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
            <li><a href="/" class="active">Home</a></li>
            <li><a href="about.jsp">About Us</a></li>
            <li><a href="/myCars">My Cars</a></li>
            <li><a href="/addCar">Add Car</a></li>
            <li><a href="/logout">Logout</a></li>
        </ul>
    </nav>

    <!-- Main -->
    <div id="main">
        <div class="inner">
            <header id="inner">
                <h1>Added Cars</h1>
                <p>These are all your added cars.</p>
            </header>
            <table class="styled-table">
                <thead>
                <tr>
                    <th>Company</th>
                    <th>Name</th>
                    <th>Year</th>
                    <th>Price</th>
                    <th>Post ad</th>
                </tr>
                </thead>
                <tbody>
                    <c:forEach items="${requestScope.userCars}" var="car">
                        <tr>
                            <td>${car['company']}</td>
                            <td>${car['name']}</td>
                            <td>${car['year']}</td>
                            <td>${car['price']}</td>
                            <td><a href="/postAd?carId=${car['carId']}"> Post Ad</a></td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
            <div class="container signin">
                <p>Add more cars <a href="/addCar">Add Car</a>.</p>
            </div>
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

