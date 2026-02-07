<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

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
        <c:set var="roles" value="${roles}"/>

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
              <td>Status</td>
              <td><input name="status" type="text" value="${user.status}"/></td>
            </tr>


            <tr>
                 <td>Role</td>
                 <sec:authorize access="hasRole('ROLE_ADMIN')">
                     <td>
                         <select multiple name="roleIds">
                             <c:forEach items="${roles}" var="role">
                                 <option value="${role.id}">${role.name}</option>
                             </c:forEach>
                         </select>
                     </td>
                 </sec:authorize>

                 <td>
                     <c:forEach items="${user.roles}" var="role1">
                        ${role1.name}<br />
                     </c:forEach>
                 </td>
            </tr>

            <input name="id" type="integer" value="${user.id}" hidden=true/>
            <input name="password" type="password" placeholder="password" value="${user.password}" hidden=true/>
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