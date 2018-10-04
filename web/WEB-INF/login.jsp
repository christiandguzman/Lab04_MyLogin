<%-- 
    Document   : login
    Created on : Sep 28, 2018, 4:03:10 PM
    Author     : 683676
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login</title>
    </head>
    <body>
        <h1>Login</h1>
        <br>
        <form method="post" action="login">
            Username: <input type="text" name="username" value=${username}>
            <br>
            Password: <input type="password" name="password">
            <br>
            <input type="checkbox" name="remember">Remember Me
            <br>
            <input type="submit" value="Login" checked>
            <br>
            ${invalid}
        </form>
    </body>
</html>
