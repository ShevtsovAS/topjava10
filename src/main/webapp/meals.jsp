<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://sargue.net/jsptags/time" prefix="javatime" %>
<jsp:useBean id="meals" scope="request" type="java.util.List" />
<html>
<head>
    <title>Meals List</title>
    <style>
        table {
            border: 1px solid #000;
            -webkit-border-radius: 20px;
            -moz-border-radius: 20px;
            border-radius: 20px;
            border-spacing: 0;
            width: 50%;
        }

        table th, table td {
            border: 1px solid #555;
            padding: 10px;
        }

        table th {
            background: green;
            color: #fff;
            font-size: 16px;
            letter-spacing: 2px;
        }

        table td {
            text-align: center;
        }

        table th:first-child {border-top-left-radius: 20px}
        table th:last-child {border-top-right-radius: 20px}
        table tr:last-child td:last-child {border-bottom-right-radius: 20px}
        table tr:last-child td:first-child {border-bottom-left-radius: 20px}

    </style>
</head>
<body>
    <h2><a href="index.html">Home</a></h2>
    <table>
        <thead>
            <tr>
                <th>Дата и время</th>
                <th>Описание</th>
                <th>Калории</th>
                <th colspan="2">Действие</th>
            </tr>
        </thead>
        <tbody>
        <c:if test="${empty meals}">
            <tr style="color: gray">
                <td colspan="5" style="text-align: center">Нет элементов для отображения</td>
            </tr>
        </c:if>
        <c:forEach items="${meals}" var="meal">
            <c:if test="${meal.exceed}">
                <tr style="color: red">
                    <td><javatime:format value="${meal.dateTime}" pattern="dd.MM.yyyy HH:mm" /></td>
                    <td>${meal.description}</td>
                    <td>${meal.calories}</td>
                    <td><a href="meals?action=edit&id=${meal.id}">edit</a></td>
                    <td><a href="meals?action=delete&id=${meal.id}">delete</a></td>
                </tr>
            </c:if>
            <c:if test="${not meal.exceed}">
                <tr style="color: green">
                    <td><javatime:format value="${meal.dateTime}" pattern="dd.MM.yyyy HH:mm" /></td>
                    <td>${meal.description}</td>
                    <td>${meal.calories}</td>
                    <td><a href="meals?action=edit&id=${meal.id}">edit</a></td>
                    <td><a href="meals?action=delete&id=${meal.id}">delete</a></td>
                </tr>
            </c:if>
        </c:forEach>
        </tbody>
    </table>
    <p><a href="meals?action=insert">Add meal</a></p>
</body>
</html>
