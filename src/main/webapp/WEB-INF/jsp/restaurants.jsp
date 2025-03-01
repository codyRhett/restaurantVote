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
    <h2>Список ресторанов.</h2>
       <br />
    <table>
        <tr><th>Название ресторана</th><th>Средний рейтинг</th></tr>
        <c:forEach items="${restaurantsForm}" var="restaurant">
            <c:url var="myURL" value="/api/restaurant/"></c:url>
            <c:url var="myURLdelete" value="/api/restaurant/delete/"></c:url>
            <c:set var="myURLpart" value="${restaurant.id}"/>
            <tr><td><a href="${myURL}${restaurant.id.toString()}" >${restaurant.name}</a></td><td>${restaurant.rating}</td>
            <td><a href="${myURLdelete}${restaurant.id.toString()}">Delete</a></td></tr>
        </c:forEach>
    </table>

    <a href="/">Главная</a>
</div>
</body>
</html>