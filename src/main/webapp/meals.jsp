<%@ page import="ru.javawebinar.topjava.model.MealWithExceed" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://sargue.net/jsptags/time" prefix="javatime" %>
<html>
<head>
    <title>Meals List</title>
</head>
<body>
<%
    List<MealWithExceed> myMeals = (List<MealWithExceed>) request.getAttribute("meals");
%>
    <table>
        <thead>
            <tr>
                <th>Дата и время</th>
                <th>Описание</th>
                <th>Калории</th>
            </tr>
        </thead>
        <tbody>
        <c:forEach items="<%=myMeals%>" var="meal">
            <c:if test="${meal.exceed}">
                <tr style="color: red">
                    <th><javatime:format value="${meal.dateTime}" pattern="dd.MM.yyyy hh:mm" /></th>
                    <th>${meal.description}</th>
                    <th>${meal.calories}</th>
                </tr>
            </c:if>
            <c:if test="${not meal.exceed}">
                <tr style="color: green">
                    <th><javatime:format value="${meal.dateTime}" pattern="dd.MM.yyyy hh:mm" /></th>
                    <th>${meal.description}</th>
                    <th>${meal.calories}</th>
                </tr>
            </c:if>
        </c:forEach>
        </tbody>
    </table>
</body>
</html>
