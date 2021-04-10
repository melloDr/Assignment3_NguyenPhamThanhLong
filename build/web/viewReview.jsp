<%-- 
    Document   : viewReview
    Created on : Mar 6, 2021, 10:01:45 PM
    Author     : Admin
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>view review Page</title>
    </head>
    <body>
        <form action="ShowFeedbackS" method="POST">
            <input type="submit" name="btnAction" value="Home">
        </form>
        <h3>${requestScope.MSG_RATING_IN_PART}</h3>
        <c:set var="countt" value="${requestScope.COUNT_NO * 20 - 20}"></c:set>       
        <c:set var="list" value="${sessionScope.LIST_FEEDBACK}"></c:set> 
        <c:if test="${list != null}">
            <c:if test="${not empty list}">
                <table border="1">
                    <thead>
                        <tr>
                            <th>No.</th>
                            <th>Car name</th>
                            <th>Feedback</th>
                            <th>Rate</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="f" items="${list}" varStatus="countt">
                            <tr>
                                <td>${countt.count}</td>
                                <td>${f.carName}</td>
                                <td>${f.feedback}</td>
                                <td>${f.rating}</td>
                            </tr>
                        </tbody>
                    </c:forEach>
                </table>

            </c:if>
        </c:if>
        <form action="ShowFeedbackS" method="POST">
            <c:forEach var="i" begin="1" end="${requestScope.END}">
                <input type="submit" name="btnPage" value="${i}">    
            </c:forEach>
        </form>
    </body>
</html>
