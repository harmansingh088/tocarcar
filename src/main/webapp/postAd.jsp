<%--
  Created by IntelliJ IDEA.
  User: harman
  Date: 2020-11-29
  Time: 7:54 p.m.
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Post Ad</title>
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
            <li><a href="/">Home</a></li>
            <li><a href="/myCars">My Cars</a></li>
            <li><a href="/myPostings" class="active">My Postings</a></li>
            <li><a href="/viewPostings">Rent Car</a></li>
            <li><a href="/myBookings">My Bookings</a></li>
            <li><a href="/logout">Logout</a></li>
        </ul>
    </nav>

    <!-- Main -->
    <div id="main">
        <div class="inner">
            <header id="inner">
                <h1>Post an ad for your car</h1>
                <p>Here you can add date, price and description for you car.</p>
            </header>

            <section>
                <h3><c:out value="${requestScope.carSelected['company']}"/> <c:out value="${requestScope.carSelected['name']}"/></h3>
                <h3>Year <c:out value="${requestScope.carSelected['year']}" /></h3>
                <c:forEach items="${requestScope.imageBase64StringList}" var="carPhoto">
                    <img src="data:image/jpg;base64,${carPhoto}" width="240" height="300"/>
                </c:forEach>
                <form method="POST" action="/postAd">
                    <div class="container">

                        <label for="postingDate"><b>Date</b></label>
                        <input type="date" name="postingDate" id="postingDate" placeholder="Posting Date" required/>
                        <br>
                        <br>
                        <hr>

                        <label for="description"><b>Description</b></label>
                        <textarea name="description" id="description" rows="3" placeholder="Description" value="${requestScope.carSelected['description']}"></textarea>
                        <br>
                        <br>

                        <label for="price"><b>Price</b></label>
                        <input type="currency" name="price" id="price" placeholder="Price" min="10" required value="${requestScope.carSelected['price']}"/>
                        <br>
                        <br>
                        <hr>


                        <div class="field text-right">
                            <label>&nbsp;</label>
                            <ul class="actions">
                                <li><input type="submit" value="Post Ad" class="primary" /></li>
                            </ul>
                        </div>
                    </div>
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

