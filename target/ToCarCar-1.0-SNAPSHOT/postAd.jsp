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
            <li><a href="contact.jsp">Contact Us</a></li>
            <li><a href="/myCars">My Cars</a></li>
            <li><a href="/addCar">Add Car</a></li>
            <li><a href="/logout">Logout</a></li>
        </ul>
    </nav>

    <!-- Main -->
    <div id="main">
        <div class="inner">
            <span class="image main"><a href="#inner" class="scrolly"><img src="images/banner.jpg" alt="" /></a></span>

            <header id="inner">
                <h1>Post an ad for your car</h1>
                <p>Etiam quis viverra lorem, in semper lorem. Sed nisl arcu euismod sit amet nisi euismod sed cursus arcu elementum ipsum arcu vivamus quis venenatis orci lorem ipsum et magna feugiat veroeros aliquam. Lorem ipsum dolor sit amet nullam dolore.</p>
            </header>

            <section>
                <h2></h2>
                <form method="POST" action="/postAd" enctype="multipart/form-data">
                    <div class="fields">
                        <div class="field">
                            <select name="company" id="company">

                                <option value="">Select Company</option>
                                <c:forEach items="${requestScope.companyNames}" var="company">
                                    <option value="${company}">${company}</option>
                                </c:forEach>
                            </select>
                        </div>

                        <div class="field">
                            <input type="text" name="name" id="name" placeholder="Name" />
                        </div>

                        <div class="field">
                            <input type="text" name="colour" id="colour" placeholder="Colour" />
                        </div>

                        <div class="field">
                            <input type="number" name="year" id="year" placeholder="Year" />
                        </div>

                        <div class="field">
                            <textarea name="description" id="description" rows="3" placeholder="Description"></textarea>
                        </div>

                        <div class="field">
                            <input type="currency" name="price" id="price" placeholder="Price" />
                        </div>

                        <div class="field">
                            <input type="file" name="photos" id="photos" accept="image/x-png,image/gif,image/jpeg" multiple/>
                        </div>

                        <div class="field text-right">
                            <label>&nbsp;</label>

                            <ul class="actions">
                                <li><input type="submit" value="Send" class="primary" /></li>
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

