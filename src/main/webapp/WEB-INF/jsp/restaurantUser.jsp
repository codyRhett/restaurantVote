<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>Рестораны</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/style.css"/>
</head>
<body>
<div>
    <h2>Список ресторанов, за которые вы проголосвали</h2>
    <table>
        <tr><th>Название ресторана</th><th>Ваша оценка</th></tr>
        <c:forEach items="${restaurantVoteForm}" var="restaurant">
            <tr><td>${restaurant.name}</td><td>${restaurant.rating}</td></tr>
        </c:forEach>
    </table>
    <br/>
    <input type="button" onclick="history.back(-2); return false;" value="Назад"/>
    <br/>
    <br/>
    <a href="/">Главная</a>
</div>
</body>
</html>