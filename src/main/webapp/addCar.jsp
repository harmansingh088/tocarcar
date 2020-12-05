<%--
  Created by IntelliJ IDEA.
  User: harman
  Date: 2020-11-29
  Time: 11:17 p.m.
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Add Car</title>
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
            <li><a href="/" >Home</a></li>
            <li><a href="/myCars" class="active">My Cars</a></li>
            <li><a href="/myPostings">My Postings</a></li>
            <li><a href="/viewPostings">Rent Car</a></li>
            <li><a href="/myBookings">My Bookings</a></li>
            <li><a href="/logout">Logout</a></li>
        </ul>
    </nav>

    <!-- Main -->
    <div id="main">
        <div class="inner">
            <header id="inner">
                <h1>Add car</h1>
                <p>You can add car to your inventory for which you can then post ad.</p>
            </header>

            <section>
                <form method="POST" action="/addCar" enctype="multipart/form-data">
                    <div class="container">

                        <label for="company"><b>Company</b></label>
                        <select name="company" id="company" required>
                            <option value="">Select Company</option>
                            <c:forEach items="${requestScope.companyNames}" var="company">
                                <option value="${company}">${company}</option>
                            </c:forEach>
                        </select>
                        <br>
                        <br>

                        <label for="name"><b>Name</b></label>
                        <input type="text" name="name" id="name" placeholder="Name" required/>
                        <br>
                        <br>

                        <label for="colour"><b>Colour</b></label>
                        <input type="text" name="colour" id="colour" placeholder="Colour" required/>
                        <br>

                        <label for="year"><b>Year</b></label>
                        <input type="number" name="year" id="year" placeholder="Year" min="2006" max="2021" required/>
                        <br>
                        <br>
                        <hr>

                        <label for="description"><b>Description</b></label>
                        <textarea name="description" id="description" rows="3" placeholder="Description"></textarea>
                        <br>
                        <br>

                        <label for="price"><b>Price</b></label>
                        <input type="currency" name="price" id="price" placeholder="Price" min="10" required/>
                        <br>
                        <br>
                        <hr>

                        <label for="photos"><b>Photos</b></label>
                        <input type="file" name="photos" id="photos" accept="image/x-png,image/gif,image/jpeg" multiple/>
                        <br>
                        <br>
                        <hr>

                        <div class="field text-right">
                            <label>&nbsp;</label>
                            <ul class="actions">
                                <li><input type="submit" value="Add Car" class="primary" /></li>
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


