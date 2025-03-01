<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>Ресторан</title>
</head>
<body>
<div>
    <form action="/api/vote/save" modelAttribute="voteForm" method="POST">
        <c:set var="restaurantD" value="${restaurantForm}"/>
        <h2>${restaurantD.name}</h2>
        <c:set var="vote" value="${voteForm}"/>
        <h4>Оцените ресторан</h4>
        <input name="rating" type="integer" placeholder="Rating" min="1" max="10" value="${vote.rating}"/>
        <input name="userId" type="integer" placeholder="UserId" value="${vote.userId}" hidden=true/>
        <input name="restaurant.id" type="integer" value="${vote.restaurant.id}" hidden=true/>
        <input name="restaurant.name" type="text" value="${vote.restaurant.name}" hidden=true/>
        <input name="id" type="integer" value="${vote.id}" hidden=true/>
        <h4>Напишите комментарий</h4>
        <input name="comment" type="integer" value="${vote.comment}"/>
        </br>
        </br>
        <button type="submit">Оценить</button>
    </form>
    <br />
    <a href="/">Главная</a>
</div>
</body>
</html>