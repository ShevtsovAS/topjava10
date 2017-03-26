<%@ page import="ru.javawebinar.topjava.model.Meal" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://sargue.net/jsptags/time" prefix="javatime" %>
<html>
<head>
    <title>Meal information</title>
</head>
<body>
    <%
        Meal meal = (Meal) request.getAttribute("meal");
    %>
    <form method="post" action="meals">
        <c:if test="<%=meal != null%>">
            ID: <input type="text" readonly="readonly" name="id" value="<%=meal.getId()%>" /><br/>
            Дата и время: <input
                type="datetime-local"
                name="dt"
                value="<%=meal.getDateTime()%>" /><br/>
            Описание: <input type="text" name="description" value="<%=meal.getDescription()%>" /><br/>
            Калории: <input type="text" name="calories" value="<%=meal.getCalories()%>" /><br/>
        </c:if>
        <c:if test="<%=meal == null%>">
            ID: <input type="text" readonly="readonly" name="id" value=""/><br/>
            Дата и время: <input
                type="datetime-local"
                name="dt"
                value="" /><br/>
            Описание: <input type="text" name="description" value="" /><br/>
            Калории: <input type="text" name="calories" value="" /><br/>
        </c:if>
        <input type="submit" value="OK" />
    </form>
</body>
</html>
