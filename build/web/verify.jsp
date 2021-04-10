<%-- 
    Document   : verify
    Created on : Mar 1, 2021, 4:20:01 PM
    Author     : Admin
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Verify Page</title>
    </head>
    <body>
        <a href="login">login</a> 
        ${requestScope.MSG_CHECKCODE}
        <form action="CheckCodeS" method="POST">
            <h1>Welcome ${sessionScope.EMAIL_LOGIN}</h1>
            <h1>Enter the code in your gmail to complete account registration</h1>
            <input type="text" name="txtCodeInGmail" placeholder="input your code here!">
            <input type="submit" name="btnAction" value="Check code!">
        </form>
    </body>
</html>
