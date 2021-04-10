<%-- 
    Document   : rent
    Created on : Mar 3, 2021, 12:43:27 PM
    Author     : Admin
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Rent Page</title>
    </head>
    <body>            
        <a href="LogOutS">log out</a>     
        <form action="UpdateS" method="POST">
            <input type="submit" name="btnAction" value="search">
        </form>   
        <h3>Hello: ${sessionScope.NAME_LOGIN}</h3>
        <h3>Date rent: ${sessionScope.MSG_BUYDATE}</h3> 
        <h3>${requestScope.MSG_QUANTITY_ADD}</h3> 
        You rent from ${sessionScope.CONTENT_DATERENTAL} to ${sessionScope.CONTENT_DATERETURN}
        <h3>Price of Your Cart: ${sessionScope.TOTAL_CART} in ${sessionScope.SPACE_OF_DAY} day</h3>
        <c:set var="countt" value="${0}"></c:set>   
        <c:set var="list" value="${sessionScope.LIST_RENT}"></c:set> 
        <c:if test="${list != null}">
            <c:if test="${not empty list}">
                <table border="1">
                    <thead>
                        <tr>
                            <th>No.</th>
                            <th>Name of car</th>
                            <th>Category</th>
                            <th>Quantity</th>
                            <th>Price</th>
                            <th>Total price of car</th>
                            <th>Delete</th>
                            <th>Update</th>
                        </tr>
                    </thead>
                    <tbody>                  
                        <c:forEach var="f" items="${list}" varStatus="countt">
                        <form action="UpdateS" method="POST">
                            <tr>
                                <td>${countt.count}</td>
                                <td>${f.carName}</td>
                                <td>${f.carCategory}</td>
                                <td>
                                    <input type="number" id="quantity" name="txtQuantityToRent" min="1" max="${sessionScope.QUANTITY_MAX}" value="${f.quantity}">
                                </td>
                                <td>${f.price}</td>
                                <td>${f.totalPrice}</td>
                                <td>
                                    <input type="hidden" name="txtIdToUpdate" value="${f.carId}">
                                    <input type="submit" name="btnAction" value="Update quantity">
                                </td>
                                <td>                         
                                    <input type="hidden" name="txtIdToDelete" value="${f.carId}">
                                    <input type="submit" name="btnAction" value="Delete" onclick="return confirm('Are you sure want to delete?');">
                                </td>
                            </tr>
                        </form>
                    </c:forEach>
                </tbody>
            </table>
        </c:if>
    </c:if>
    <form action="UpdateS" method="POST">
        <input type="submit" name="btnAction" value="Confirm">
    </form>
</body>
</html>
