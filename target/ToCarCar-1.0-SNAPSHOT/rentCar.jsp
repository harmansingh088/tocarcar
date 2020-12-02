<%--
  Created by IntelliJ IDEA.
  User: harman
  Date: 2020-11-29
  Time: 7:23 p.m.
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
            <li><a href="/logout">Log Out</a></li>
        </ul>
    </nav>

    <!-- Main -->
    <div id="main">
        <div class="inner">
            <span class="image main"><a href="#inner" class="scrolly"><img src="images/banner.jpg" alt="" /></a></span>

            <header id="inner">
                <h1>Rent a car at low prices</h1>
                <p>Etiam quis viverra lorem, in semper lorem. Sed nisl arcu euismod sit amet nisi euismod sed cursus arcu elementum ipsum arcu vivamus quis venenatis orci lorem ipsum et magna feugiat veroeros aliquam. Lorem ipsum dolor sit amet nullam dolore.</p>
            </header>

            <section class="tiles">
                <article class="style1">
									<span class="image">
										<img src="images/image-1.jpg" alt="" />
									</span>
                    <a href="#footer" class="scrolly">
                        <h2>Large: Premium</h2>

                        <p>price from: <strong> $ 140.00</strong></p>

                        <div class="content">
                            <p>Available Vehicles: <br> Opel Astra | Tesla Model S | Mazda 6</p>
                        </div>
                    </a>
                </article>
                <article class="style2">
									<span class="image">
										<img src="images/image-2.jpg" alt="" />
									</span>
                    <a href="#footer" class="scrolly">
                        <h2>Large: Station wagon</h2>

                        <p>price from: <strong>$ 120.00</strong></p>

                        <div class="content">
                            <p>Available Vehicles: <br> Opel Astra | Tesla Model S | Mazda 6</p>
                        </div>
                    </a>
                </article>
                <article class="style3">
									<span class="image">
										<img src="images/image-3.jpg" alt="" />
									</span>
                    <a href="#footer" class="scrolly">
                        <h2>Medium: Low emission</h2>

                        <p>price from: <strong>$ 75.00</strong></p>

                        <div class="content">
                            <p>Available Vehicles: <br> Opel Astra | Tesla Model S | Mazda 6</p>
                        </div>
                    </a>
                </article>
                <article class="style4">
									<span class="image">
										<img src="images/image-4.jpg" alt="" />
									</span>
                    <a href="#footer" class="scrolly">
                        <h2>Small: Economy</h2>

                        <p>price from: <strong> $ 65.00</strong></p>

                        <div class="content">
                            <p>Available Vehicles: <br> Opel Astra | Tesla Model S | Mazda 6</p>
                        </div>
                    </a>
                </article>
                <article class="style5">
									<span class="image">
										<img src="images/image-5.jpg" alt="" />
									</span>
                    <a href="#footer" class="scrolly">
                        <h2>Small: Mini</h2>

                        <p>price from: <strong>$ 55.00</strong></p>

                        <div class="content">
                            <p>Available Vehicles: <br> Opel Astra | Tesla Model S | Mazda 6</p>
                        </div>
                    </a>
                </article>
                <article class="style6">
									<span class="image">
										<img src="images/image-6.jpg" alt="" />
									</span>
                    <a href="#footer" class="scrolly">
                        <h2>Small: Mini Low emission</h2>

                        <p>price from: <strong>$ 49.00</strong></p>

                        <div class="content">
                            <p>Available Vehicles: <br> Opel Astra | Tesla Model S | Mazda 6</p>
                        </div>
                    </a>
                </article>
            </section>
        </div>
    </div>

    <!-- Footer -->
    <footer id="footer">
        <div class="inner">
            <section>
                <h2>Contact Info</h2>

                <ul class="alt">
                    <li><span class="fa fa-envelope-o"></span> <a href="#">carrentalwebsite@untitled.tld</a></li>
                    <li><span class="fa fa-phone"></span> (000) 000-0000 x12387 </li>
                    <li><span class="fa fa-map-pin"></span> 1234 Somewhere Road #5432 Nashville, TN 00000 United States of America</li>
                </ul>

                <h2>Follow Us</h2>

                <ul class="icons">
                    <li><a href="#" class="icon style2 fa-twitter"><span class="label">Twitter</span></a></li>
                    <li><a href="#" class="icon style2 fa-facebook"><span class="label">Facebook</span></a></li>
                    <li><a href="#" class="icon style2 fa-instagram"><span class="label">Instagram</span></a></li>
                    <li><a href="#" class="icon style2 fa-linkedin"><span class="label">LinkedIn</span></a></li>
                    <li><a href="#" class="icon style2 fa-pinterest"><span class="label">Pinterest</span></a></li>
                </ul>
            </section>

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

