<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>

<!DOCTYPE HTML>
<html>
<head>
  <title>Главная</title>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
  <link rel="stylesheet" type="text/css" href="${contextPath}/resources/css/style.css">
  <body>
  <div>
<h3>Веб-сервис голосования за лучший ресторан</h3>
<ul class="menu-main">
      <h2>${pageContext.request.userPrincipal.name}</h2>
      <sec:authorize access="!isAuthenticated()">
        <li><a href="/login">Sign in</a></li>
        <li><a href="/api/registration">Sign up</a></li>
      </sec:authorize>

    <sec:authorize access="isAuthenticated()">
      <sec:authentication var="principal" property="principal" />
      <li><a href="/logout">Sign out</a></li>
      <li><a href="/api/restaurant">Sign new restaurant</a></li>

       <c:url var="myURL" value="/api/user/"></c:url>
       <c:set var="myURLpart" value="${principal.id}"/>
       <c:set var="myURLpart1" value="/restaurants"/>

       <tr>
         <td><li><a href="${myURL}${myURLpart}" >Edit user</a></li></td>
       </tr>

      <tr>
        <td><li><a href="${myURL}${myURLpart}${myURLpart1}" >Show vote-info</a></li></td>
      </tr>
      <li><a href="/api/restaurant/list">Restaurants</a></li>
    </sec:authorize>

    <sec:authorize access="hasRole('ROLE_ADMIN')">
        <li><a href="/api/admin/users">Show users</a></li>
    </sec:authorize>
</ul>
  </div>
  </body>

</head>
</html>