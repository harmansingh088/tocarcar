<%--
  Created by IntelliJ IDEA.
  User: Harman.Malhotra
  Date: 12/3/2020
  Time: 10:20 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>New Postings</title>
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
            <li><a href="/adminAllUsers">All Users</a></li>
            <li><a href="/adminNewPostings" class="active">New Postings</a></li>
            <li><a href="/logout">Logout</a></li>
        </ul>
    </nav>

    <!-- Main -->
    <div id="main">
        <div class="inner">
            <header id="inner">
                <h1>New Postings to Approve/Reject</h1>
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
                <c:forEach items="${requestScope.adminNewPostings}" var="postingObj">
                    <tr>
                        <td>${postingObj.user['firstName']} ${postingObj.user['lastName']}</td>
                        <td>${postingObj.car['company']}</td>
                        <td>${postingObj.car['name']}</td>
                        <td>${postingObj.car['year']}</td>
                        <td>${postingObj.carPosting['postingDate']}</td>
                        <td>$${postingObj.carPosting['price']}</td>
                        <td>
                            <a href="/adminViewPosting?carPostingId=${postingObj.carPosting['carPostingId']}" style="background-color: #585858; color: white; padding: 0.5em 0.5em;" > View </a>
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

</body>
</html>


