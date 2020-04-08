<%-- 
    Document   : login
    Created on : Apr 8, 2020, 2:57:59 PM
    Author     : 798794
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login</title>
    </head>
    <body>
        <h1>Login Page</h1>
        <form method="post" action="login">
            username: <input type="text" required name="username"><br>
            password: <input type="password" required name="password"><br>
            <input type="submit" value="Submit">
        </form>
        <p>
            New user? Please <a href="register">register</a>
        </p>
    </body>
</html>
