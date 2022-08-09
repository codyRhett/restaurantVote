<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>Рестораны</title>
</head>
<body>
<div>
    <h2>Список пользователей.</h2>
       <br />
    <table>
        <c:forEach items="${usersForm}" var="user">

            <c:url var="myURL" value="/api/admin/user/"></c:url>
            <c:set var="myURLpart" value="${user.id}"/>

            <tr>
                <td><a href="${myURL}${user.id.toString()}" >${user.username}</a></td>
            </tr>
        </c:forEach>
    </table>

    <a href="/">Главная</a>
</div>
</body>
</html>