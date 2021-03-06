<%--
  Created by IntelliJ IDEA.
  User: harman
  Date: 2020-11-22
  Time: 5:07 p.m.
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Registration</title>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no" />
    <link rel="stylesheet" href="assets/css/main.css" />
    <noscript><link rel="stylesheet" href="assets/css/noscript.css" /></noscript>
    <link rel="stylesheet" href="assets/css/snackbar.css" />
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
            <li><a href="/">Home</a></li>
            <li><a href="/registration" class="active">Sign Up</a></li>
            <li><a href="/login">Log In</a></li>
        </ul>
    </nav>

    <div id="snackbar">Some text some message..</div>

    <!-- Main -->
    <div id="main">
        <div class="inner">

            <header id="inner">
                <h1>Registration</h1>
                <p>Please fill in this form to create an account.</p>
            </header>

            <section>
                <form method="POST" action="/registration" onsubmit="return validation()">
                    <div class="container">

                        <label for="firstName"><b>First Name</b></label>
                        <input type="text" placeholder="First Name" name="firstName" id="firstName" required>
                        <br>

                        <label for="lastName"><b>Last Name</b></label>
                        <input type="text" placeholder="Last Name" name="lastName" id="lastName" required>
                        <br>

                        <label for="email"><b>Email</b></label>
                        <input type="text" placeholder="Enter Email" name="email" id="email" required>
                        <br>

                        <label for="password"><b>Password</b></label>
                        <input type="password" placeholder="Enter Password" name="password" id="password" required>
                        <br>

                        <label for="pswRepeat"><b>Repeat Password</b></label>
                        <input type="password" placeholder="Repeat Password" name="pswRepeat" id="pswRepeat" required>
                        <br>

                        <label for="phoneNumber"><b>Phone Number</b></label>
                        <input type="text" placeholder="Phone Number" name="phoneNumber" id="phoneNumber" required>
                        <br>

                        <label for="age"><b>Age</b></label>
                        <input type="number" placeholder="Age" name="age" id="age" required>

                        <hr>

                        <button type="submit" class="registerbtn">Register</button>
                    </div>

                    <br>
                    <br>
                    <div class="container signin">
                        <p>Already have an account? <a href="/login">Sign in</a>.</p>
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

<script>
    function validation() {
        if (document.getElementById("password").value != document.getElementById("pswRepeat").value) {
            let snackbar = document.getElementById("snackbar");
            snackbar.innerText = "Passwords do not match";
            snackbar.style.backgroundColor = "#FAC42F"
            snackbar.className = "show";
            setTimeout(function(){
                snackbar.className = snackbar.className.replace("show", "");
            }, 3000);
            return false;
        }
    }
</script>

</body>
</html>
