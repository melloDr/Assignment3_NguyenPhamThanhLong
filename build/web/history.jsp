<%-- 
    Document   : history
    Created on : Mar 6, 2021, 5:37:53 PM
    Author     : Admin
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>History Page</title>
    </head>
    <body>
        <script>
            function myFunction() {
                var checkBox = document.getElementById("myCheck");
                var text = document.getElementById("text");
                var date = document.getElementById("date");
                if (checkBox.checked == true) {
                    text.style.display = "block";
                    date.style.display = "none";
                } else {
                    text.style.display = "none";
                    date.style.display = "block";
                }
            }
        </script>
        <form action="HistoryS" method="POST">
            <label for="myCheck">Search by name?</label>
            <input type="checkbox" id="myCheck" onclick="myFunction()"><br>
            <input type="text" name="contentToSearch" id="text" style="display:none">
            <input type="date" name="dateToSearch" id="date" >
            <input type="submit" name="btnAction" value="Search history">
        </form>     
        
        ${sessionScope.MSG_SEARCH_HISTORY}
        ${sessionScope.MSG_CANCEL_ORDER}
        <h3>${sessionScope.MSG_CANCLE_IN_PAST}</h3>
        <c:set var="countt" value="${requestScope.COUNT_NO * 20 - 20}"></c:set>       
        <c:set var="list" value="${sessionScope.LIST_SEARCH_HISTORY}"></c:set> 
        <c:if test="${list != null}">
            <c:if test="${not empty list}">
                <table border="1">
                    <thead>
                        <tr>
                            <th>No.</th>
                            <th>Total this cart</th>
                            <th>Date of order</th>
                            <th>Rental date</th>
                            <th>Return date</th>
                            <th>Cancer order?</th>
                            <th>Retail and rating</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="f" items="${list}" varStatus="countt">
                        <form action="HistoryS" method="POST">
                            <tr>
                                <td>${countt.count}</td>
                                <td>${f.totalCart}</td>
                                <td>${f.rentalDate}</td>
                                <td>${f.dateRental}</td>
                                <td>${f.dateReturn}</td>
                                <td>
                                    <input type="hidden" name="txtRentIdToCancel" value="${f.rentId}">
                                    <input type="submit" name="btnAction" value="Cancel this order">
                                </td>
                                <td>
                                    <input type="hidden" name="txtRentIdToView" value="${f.rentId}">
                                    <input type="submit" name="btnAction" value="View detail and rating">
                                </td>
                            </tr>
                        </form>
                    </c:forEach>
                </tbody>
            </table>

        </c:if>
    </c:if>
</body>
</html>
