<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>

<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <title>Регистрация</title>
</head>

<body>
<div>
<form:form action="/api/restaurant" method="POST" modelAttribute="restaurantRegistrationForm">
    <h2>Регистрация</h2>
    <div>
      <form:input type="text" path="name" placeholder="Name"
                  autofocus="true"></form:input>
      <form:errors path="name"></form:errors>
        ${usernameError}
    </div>
    <div>
      <form:input type="text" path="cuisine" placeholder="Cuisine"></form:input>
    </div>
    <button type="submit">Зарегистрироваться</button>
  </form:form>
  <a href="/">Главная</a>
</div>
</body>
</html>