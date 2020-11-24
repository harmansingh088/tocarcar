<%--
  Created by IntelliJ IDEA.
  User: harman
  Date: 2020-11-23
  Time: 9:22 p.m.
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
                <span class="fa fa-car"></span> <span class="title">CAR RENTAL WEBSITE BY VEVS</span>
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
            <li><a href="about.jsp">About Us</a></li>
            <li><a href="contact.jsp">Contact Us</a></li>
            <li><a href="/registration">Sign Up</a></li>
            <li><a href="/login" class="active">Log In</a></li>
        </ul>
    </nav>

    <!-- Main -->
    <div id="main">
        <div class="inner">

            <header id="inner">
                <h1>Login</h1>
                <p>Please fill in this form to create an account.</p>
            </header>

            <section>
                <form method="POST" action="/login">
                    <div class="container">
                        <hr>

                        <label for="email"><b>Email</b></label>
                        <input type="text" placeholder="Enter Email" name="email" id="email" required>
                        <br>

                        <label for="password"><b>Password</b></label>
                        <input type="password" placeholder="Enter Password" name="password" id="password" required>
                        <br>

                        <button type="submit" class="registerbtn">Login</button>
                    </div>

                    <div class="container signin">
                        <p>Don't have an account? <a href="/registration">Sign up</a>.</p>
                    </div>
                </form>
            </section>
        </div>
    </div>

    <!-- Footer -->
    <footer id="footer">
        <div class="inner">


            <ul class="copyright">
                <li>&copy; 2018 <a href="#">Car Rental Company Ltd</a> | All rights reserved.</li>
                <li>Design: <a href="https://html5up.net">HTML5 UP</a></li>
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

