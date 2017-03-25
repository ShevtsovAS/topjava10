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
    <table class="table_blur">
        <thead>
            <tr>
                <th>Дата и время</th>
                <th>Описание</th>
                <th>Калории</th>
            </tr>
        </thead>
        <tbody>
        <c:forEach items="<%=myMeals%>" var="meal">
            <tr>
                <th><javatime:format value="${meal.dateTime}" pattern="dd.MM.yyyy hh:mm" /></th>
                <th>${meal.description}</th>
                <th>${meal.calories}</th>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</body>
</html>
