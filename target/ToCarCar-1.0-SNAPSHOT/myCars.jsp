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
    <title>My Cars</title>
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
            <li><a href="/">Home</a></li>
            <li><a href="/myCars" class="active">My Cars</a></li>
            <li><a href="/myPostings">My Postings</a></li>
            <li><a href="/viewPostings">Rent Car</a></li>
            <li><a href="/myBookings">My Bookings</a></li>
            <li><a href="/logout">Logout</a></li>
        </ul>
    </nav>

    <div id="snackbar">Some text some message..</div>

    <!-- Main -->
    <div id="main">
        <div class="inner">
            <header id="inner">
                <h1>Added Cars</h1>
                <p>These are all your added cars.</p>
            </header>

            <div>
                <a href="/addCar" style="background-color: #585858; color: white; font-weight: bold; padding: 0.5em 0.5em;" > Add Car </a>
            </div>

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
                            <td>
                                <a href="/postAd?carId=${car['carId']}" style="background-color: #585858; color: white; padding: 0.5em 0.5em;" > Post Ad </a>
                            </td>
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

        var carAdded = url.searchParams.get("carAdded");
        var fromRegistration = url.searchParams.get("fromRegistration");
        var fromLogin = url.searchParams.get("fromLogin");

        if(carAdded){
            showSnackbar("Congrats, Your car has been added!")
        }
        else if(fromRegistration){
            showSnackbar("Congrats, You have been successfully registered!")
        }
        else if(fromLogin){
            showSnackbar("Welcome Back!")
        }
    }

    function showSnackbar(message){
        let snackbar = document.getElementById("snackbar");
        snackbar.innerText = message;
        snackbar.style.backgroundColor = "#2ecc72"
        snackbar.className = "show";
        setTimeout(function(){
            snackbar.className = snackbar.className.replace("show", "");
        }, 3000);
    }
</script>

</body>
</html>

