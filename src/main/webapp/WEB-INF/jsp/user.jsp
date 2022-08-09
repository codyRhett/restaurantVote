<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>Карточка пользователя</title>
</head>
<body>
<div>
    <h2>Карточка пользователя</h2>
    <form action="/api/user" modelAttribute="userForm" method="POST">
        <br />
        <c:set var="user" value="${userForm}"/>
        <c:set var="role" value="${roleForm}"/>

        <table>
        <tr>
          <td>Username</td>
          <td><input name="username" type="text" placeholder="username" value="${user.username}"/></td>
        </tr>
        <tr>
          <td>First name</td>
          <td><input name="firstName" type="text" placeholder="firstName" value="${user.firstName}"/></td>
        </tr>
        <tr>
          <td>Last name</td>
          <td><input name="lastName" type="text" placeholder="lastName" value="${user.lastName}"/></td>
        </tr>
        <tr>
          <td>E-mail</td>
          <td><input name="email" type="email" placeholder="email" value="${user.email}"/></td>
        </tr>

         <tr>
          <td>Password</td>
          <td><input name="password" type="password" placeholder="password" value="${user.password}"/></td>
         </tr>

        <tr>
          <td>Status</td>
          <td><input name="status" type="text" value="${user.status}"/></td>
        </tr>
        <tr>
          <td>Roles</td>

           <c:forEach items="${user.roles}" var="item">

                <td><input name="name" type="text" value="${item.name}"/></td>
                <input name="idRole" type="integer" value="${item.id}" hidden=true/>
            </c:forEach>
        </tr>
        <input name="id" type="integer" value="${user.id}" hidden=true/>
        </table>
        <br />
        <button type="submit">Редактировать</button>
        <input type="button" onclick="history.back(-2); return false;" value="Назад"/>
    </form>
    <br />
    <a href="/">Главная</a>
</div>
</body>
</html>