<%@ page import="ru.javawebinar.topjava.model.Meal" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://sargue.net/jsptags/time" prefix="javatime" %>
<html>
<head>
    <title>Meal information</title>
    <link rel="stylesheet" type="text/css" href="style.css">
</head>
<body>
    <%
        Meal meal = (Meal) request.getAttribute("meal");
    %>
    <h2>Добавление еды</h2>
    <form method="post" action="meals">
        <c:if test="<%=meal != null%>">
            <input type="hidden" style="display: none" id="id" name="id" value="<%=meal.getId()%>"/>
            Дата и время: <input required
                type="datetime-local"
                name="dt"
                value="<%=meal.getDateTime()%>" />
            Описание: <input type="text" name="description" value="<%=meal.getDescription()%>" autofocus required/>
            Калории: <input type="number" step="any" name="calories" value="<%=meal.getCalories()%>" required/>
        </c:if>
        <c:if test="<%=meal == null%>">
            <input type="hidden" style="display: none" id="id" name="id" value=""/>
            Дата и время: <input required
                type="datetime-local"
                name="dt"
                value="" />
            Описание: <input type="text" name="description" value="" autofocus required/>
            Калории: <input type="number" step="any" name="calories" value="" required/>
        </c:if>
        <input type="submit" value="OK" />
    </form>
</body>
</html>
