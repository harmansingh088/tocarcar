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
    <title>Title</title>
</head>
<body>
    <form method="POST" action="/registration">
        <div class="container">
            <h1>Register</h1>
            <p>Please fill in this form to create an account.</p>
            <hr>

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

            <p>By creating an account you agree to our <a href="#">Terms & Privacy</a>.</p>
            <button type="submit" class="registerbtn">Register</button>
        </div>

        <div class="container signin">
            <p>Already have an account? <a href="#">Sign in</a>.</p>
        </div>
    </form>
</body>
</html>
