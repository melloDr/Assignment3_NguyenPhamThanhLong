<%-- 
    Document   : confirm
    Created on : Mar 5, 2021, 5:51:53 PM
    Author     : Admin
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Confirm Page</title>
    </head>
    <body>
        <c:set var="EMAIL_LOGIN" value="${sessionScope.NAME_LOGIN}"></c:set>    
        <h3>Hello: ${EMAIL_LOGIN}</h3>
        <a href="LogOutS">log out</a>
        <form action="ConfirmS" method="POST">
            <input type="submit" name="btnAction" value="search">
        </form>
        <h3>Date rent: ${sessionScope.MSG_BUYDATE}</h3> 
        <c:if test="${not empty MSG_PERCENT}">  
            <h3>${MSG_PERCENT}</h3>
        </c:if>
        <h3>Price of Your Cart: ${sessionScope.TOTAL_CART} on the total bill</h3>
        <h3>In total you will rent for ${sessionScope.SPACE_OF_DAY} days</h3>
        <form action="ConfirmS" method="POST">
            <input type="text" name="txtCodeDiscount" placeholder="If you have code, input here!">
            <input type="submit" name="btnAction" value="Add discount!">
            <input type="submit" name="btnAction" value="Complete the order">
        </form>
        <h3>Please prepare cash upon receipt / payment</h3>
    </body>
</html>
