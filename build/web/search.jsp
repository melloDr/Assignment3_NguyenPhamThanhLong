<%-- 
    Document   : search
    Created on : Mar 1, 2021, 10:57:14 AM
    Author     : Admin
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Search Page</title>
    </head>
    <body>          
        <c:set var="EMAIL_LOGIN" value="${sessionScope.NAME_LOGIN}"></c:set> 
        <c:if test="${!not empty EMAIL_LOGIN}">  
            <form action="SearchCarS" method="POST">
                <input type="submit" name="btnAction" value="login or register">
            </form>
        </c:if>
        <c:if test="${not empty EMAIL_LOGIN}">   
            <h3>Hello: ${EMAIL_LOGIN}</h3>
            <a href="LogOutS">log out</a> <br/>
            <a href="HistoryS">View history</a> 
            <form action="SearchCarS" method="POST">
                <input type="submit" name="btnAction" value="Your cart">
                <input type="submit" name="btnAction" value="Show feedback">
            </form>
        </c:if>
        <h4>${requestScope.MSG_DATE}</h4>
        <h4>${requestScope.MSG_CART}</h4>
        <h4>${requestScope.MSG_THANKS}</h4>
        <c:if test="${not empty TOTAL_CART}">  
            <h4>Price of Your Cart: ${sessionScope.TOTAL_CART} in ${sessionScope.SPACE_OF_DAY} day</h4>
        </c:if>
        <h4>${requestScope.MSG_INPUTNAME}</h4>
        <h4>${requestScope.MSG_QUANTITY}</h4>
        <h4>${requestScope.MSG_DATE_TO_RENT}</h4>
        <c:set var="countt" value="${requestScope.COUNT_NO * 20 - 20}"></c:set>       
        <c:set var="searchBy" value="${fn:split('name-category', '-')}" ></c:set>
            Currently we are providing 3 types of cars, which are: suv, minivan and sedan.
            <form action="SearchCarS" method="POST">
                <input type="text" name="txtContentToSearch" value="${sessionScope.CONTENT_SEARCH}" placeholder="input name or category"><br/>
            <select name="txtSearchBy">
                <c:forEach var="i" items="${searchBy}">
                    <option  value="${i}" >${i}</option>
                </c:forEach>
            </select><br/>
            <input type="date" name="txtDateRental" value="${sessionScope.CONTENT_DATERENTAL}" min="${sessionScope.DATE_START}">
            <input type="date" name="txtDateReturn" value="${sessionScope.CONTENT_DATERETURN}" min="${sessionScope.DATE_START}"><br/>
            Quantity: <input type="number" id="quantity" name="txtQuantitySearch" min="1" max="10" value="${sessionScope.QUANTITY_SEARCH}">
            <input type="submit" name="btnAction" value="Search">
        </form>
        <c:set var="list" value="${sessionScope.LIST_ALL_CAR}"></c:set> 
        <c:if test="${list != null}">
            <c:if test="${not empty list}">
                <table border="1">
                    <thead>
                        <tr>
                            <th>No.</th>
                            <th>Car Name</th>
                            <th>Color</th>
                            <th>Year</th>
                            <th>Category</th>
                            <th>Price</th>
                            <th>Quantity</th>
                            <th>Choose</th>
                        </tr>
                    </thead>
                    <tbody>  
                        <c:forEach var="f" items="${list}" varStatus="countt">
                        <form action="RentS" method="POST">
                            <tr>
                                <td>${countt.count}</td>
                                <td>${f.name}</td>
                                <td>${f.color}</td>
                                <td>${f.year}</td>
                                <td>${f.category}</td>
                                <td>${f.price}</td>
                                <td>${f.quantity}</td>
                                <td>
                                    <input type="hidden" name="txtCarIdToRent" value="${f.carId}">
                                    <input type="submit" name="btnAction" value="rent it!">
                                </td>
                            </tr>
                        </form>
                    </c:forEach>
                </tbody>
            </table>
        </c:if>
    </c:if>
    <%--<c:if test="${requestScope.END > 1}">--%>
    <form action="SearchS" method="POST">
        <c:forEach var="i" begin="1" end="${requestScope.END}">
            <input type="submit" name="btnPage" value="${i}">    
        </c:forEach>
    </form>
    <%--</c:if>--%>
</body>
</html>
