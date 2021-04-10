<%-- 
    Document   : register
    Created on : Feb 26, 2021, 7:21:26 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Register Page</title>
    </head>
    <body>          
        <form action="registerS" method="POST">
            <input type="submit" name="btnAction" value="login">
        </form>   
        ${requestScope.MSG_REGISTER}
        <form action="registerS" method="POST">
            email: <input type="text" name="txtEmailToRegister" placeholder="Enter your email" value="${sessionScope.TXTEMAIL}"><br/>
            password:<input type="password" name="txtPassToRegister" value="${sessionScope.TXTPASS}"><br/>
            phone number:<input type="text" name="txtPhoneToRegister" value="${sessionScope.TXTPHONE}"><br/>
            your name:<input type="text" name="txtNameToRegister" value="${sessionScope.TXTNAME}"><br/>
            your address:<input type="text" name="txtAddressToRegister" value="${sessionScope.TXTADDRESS}"><br/>
            <input type="submit" name="btnAction" value="Register">
        </form><br/>
    </body>
</html>
