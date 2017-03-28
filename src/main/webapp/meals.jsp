<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://sargue.net/jsptags/time" prefix="javatime" %>
<jsp:useBean id="meals" scope="request" type="java.util.List" />
<html>
<head>
    <title>Meals List</title>
</head>
<body>
    <h2><a href="index.html">Home</a></h2>
    <table border="1">
        <thead>
            <tr>
                <th>Дата и время</th>
                <th>Описание</th>
                <th>Калории</th>
            </tr>
        </thead>
        <tbody>
        <c:forEach items="${meals}" var="meal">
            <c:if test="${meal.exceed}">
                <tr style="color: red">
                    <th><javatime:format value="${meal.dateTime}" pattern="dd.MM.yyyy HH:mm" /></th>
                    <th>${meal.description}</th>
                    <th>${meal.calories}</th>
                    <th><a href="meals?action=edit&id=${meal.id}">edit</a></th>
                    <th><a href="meals?action=delete&id=${meal.id}">delete</a></th>
                </tr>
            </c:if>
            <c:if test="${not meal.exceed}">
                <tr style="color: green">
                    <th><javatime:format value="${meal.dateTime}" pattern="dd.MM.yyyy HH:mm" /></th>
                    <th>${meal.description}</th>
                    <th>${meal.calories}</th>
                    <th><a href="meals?action=edit&id=${meal.id}">edit</a></th>
                    <th><a href="meals?action=delete&id=${meal.id}">delete</a></th>
                </tr>
            </c:if>
        </c:forEach>
        </tbody>
    </table>
    <p><a href="meals?action=insert">Add meal</a></p>
</body>
</html>
