<%-- 
    Document   : review
    Created on : Mar 1, 2021, 5:30:19 PM
    Author     : Admin
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Review Page</title>
    </head>
    <body>
        <c:set var="countt" value="${requestScope.COUNT_NO * 20 - 20}"></c:set>       
        <c:set var="list" value="${sessionScope.LIST_REVIEW}"></c:set> 
        <h3>${requestScope.MSG_RATING_IN_PART}</h3>
        <form action="ReviewS" method="POST">
            <input type="submit" name="btnAction" value="Home">
        </form>
        <c:if test="${list != null}">
            <c:if test="${not empty list}">
                <table border="1">
                    <thead>
                        <tr>
                            <th>No.</th>
                            <th>Car name</th>
                            <th>Feedback</th>
                            <th>Rate (1-10)</th>
                            <th>Complete</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="f" items="${list}" varStatus="countt">
                        <form action="ReviewS" method="POST">
                            <tr>
                                <td>${countt.count}</td>
                                <td>${f.carName}</td>
                                <td>
                                    <input type="text" name="txtFeedback" placeholder="under 100 words" maxlength = "100" value=" ">
                                </td>
                                <td>
                                    <input type="number" name="txtRating" min="1" max="10" value="1">
                                </td>
                                <td>
                                    <input type="hidden" name="txtCarIdToRating" value="${f.carId}">
                                    <input type="submit" name="btnAction" value="Complete">
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
