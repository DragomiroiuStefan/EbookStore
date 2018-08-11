<%-- 
    Document   : signUpPage
    Created on : Aug 10, 2018, 1:14:47 PM
    Author     : stefan
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Registration</title>
    </head>
    <body>
        <h3>Registration: </h3>
        <form action="signup" method="POST">
            <label for="ssn">ssn: </label>
            <input type="text" name="ssn"><br>
            <label for="username">username: </label>
            <input type="text" name="username"><br>
            <label for="password">password: </label>
            <input type="password" name="password"><br><br>
            <input type="submit" value="Register">
        </form>
    </body>
</html>
