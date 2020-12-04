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
    <title>Login</title>
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
            <li><a href="/registration">Sign Up</a></li>
            <li><a href="/login" class="active">Log In</a></li>
        </ul>
    </nav>

    <div id="snackbar">Some text some message..</div>

    <!-- Main -->
    <div id="main">
        <div class="inner">

            <header id="inner">
                <h1>Login</h1>
                <p>Enter your email address and password to login into your account.</p>
            </header>

            <section>
                <form method="POST" action="/login">
                    <div class="container">

                        <label for="email"><b>Email</b></label>
                        <input type="text" placeholder="Enter Email" name="email" id="email" required>
                        <br>

                        <label for="password"><b>Password</b></label>
                        <input type="password" placeholder="Enter Password" name="password" id="password" required>
                        <br>

                        <button type="submit" class="registerbtn">Login</button>
                    </div>

                    <br>
                    <br>
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
    let isLoginError = '${isLoginError}';
    if(isLoginError){
        let snackbar = document.getElementById("snackbar");
        snackbar.innerText = "Login failed! Please check enter email and password again.";
        snackbar.style.backgroundColor = "red"
        snackbar.className = "show";
        setTimeout(function(){
            snackbar.className = snackbar.className.replace("show", "");
        }, 3000);
    }
</script>

</body>
</html>

