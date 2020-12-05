<%--
  Created by IntelliJ IDEA.
  User: harman
  Date: 2020-11-16
  Time: 6:36 p.m.
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="models.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>TOCARCAR</title>
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
                <span class="fa fa-car"></span> <span class="title">TO CAR CAR</span>
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
            <%
                Object useridString = session.getAttribute("loggedInUserId");
                String userType = String.valueOf(session.getAttribute("loggedInUserType"));
                if(useridString == null){
                %><li><a href="/registration">Sign Up</a></li>
                    <li><a href="/login">Log In</a></li> <%
                }
                else if(userType.equalsIgnoreCase("Admin")){
                    %><li><a href="/adminAllUsers">All Users</a></li>
                        <li><a href="/adminNewPostings">New Postings</a></li>
                     <li><a href="/logout">Logout</a></li> <%
                }
                else if (userType.equalsIgnoreCase("Customer")){
                    %><li><a href="/myCars">My Cars</a></li>
                        <li><a href="/myPostings">My Postings</a></li>
                        <li><a href="/viewPostings">Rent Car</a></li>
                        <li><a href="/myBookings">My Bookings</a></li>
                        <li><a href="/logout">Logout</a></li> <%
                }
            %>

        </ul>
    </nav>

    <!-- Main -->
    <div id="main">
        <div class="inner">
            <span class="image main"><a href="#inner" class="scrolly"><img src="images/banner.jpg" alt="" /></a></span>

            <header id="inner">
                <h1>Rent a car at low prices</h1>
                <p></p>
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
                            <p>Available Vehicles: <br> Audi | BMW | Mercedes</p>
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
                            <p>Available Vehicles: <br> Acura| Dodge | Lexus</p>
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
                            <p>Available Vehicles: <br> Toyota | Honda | Mazda </p>
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
                            <p>Available Vehicles: <br> Toyota | Honda | Mazda</p>
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
                            <p>Available Vehicles: <br> Chevrolet | Ford</p>
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
                            <p>Available Vehicles: <br> Kia | Hyundai</p>
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
                    <li><span class="fa fa-envelope-o"></span> <a href="#">tocarcar@example.com</a></li>
                    <li><span class="fa fa-phone"></span> +1 1234567890 </li>
                    <li><span class="fa fa-map-pin"></span> New Westminster, Canada</li>
                </ul>
            </section>

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
