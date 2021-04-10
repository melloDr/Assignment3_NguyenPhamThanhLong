<%-- 
    Document   : login
    Created on : Feb 25, 2021, 7:58:13 PM
    Author     : Admin
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login Page</title>
        <script src="https://www.google.com/recaptcha/api.js" async defer></script>
    </head>
    <body>
        ${sessionScope.MSG_REGISTER}
        <form action="loginServlet" method="POST">
            <input type="submit" name="btnAction" value="register">
            <input type="submit" name="btnAction" value="search">
        </form>        
        <form action="loginServlet" method="POST">
            Your ID: <input type="text" name="txtEmailLogin" value="1">
            Password: <input type="password" name="txtPasswordLogin" value="1">
            <input type="submit" name="btnAction" value="Login">
            <div class="g-recaptcha" data-sitekey="6LdXOd8ZAAAAAI8ArRIVsN3ffOEEv7N_JXD4UuAp"></div>
            <input type="reset" value="Reset">
        </form><br/>
        ${requestScope.MSG_LOGIN}
        ${requestScope.MSG_RENT_LOGIN}
        <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
    </body>
</html>
