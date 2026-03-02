<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Profile</title>
</head>
<body>
    <h1>User Profile</h1>

    <div>
        <span>Username:</span>
        <span>${username}</span>
    </div>

    <div>
        <span>Email:</span>
        <span>${email}</span>
        <span>
            <c:if test="${emailVerified}">✓ Verified</c:if>
            <c:if test="${!emailVerified}">✗ Not verified</c:if>
        </span>
    </div>

    <div>
        <span>Full Name:</span>
        <span>${fullName}</span>
    </div>

    <div>
        <span>First Name:</span>
        <span>${firstName}</span>
    </div>

    <div>
        <span>Last Name:</span>
        <span>${lastName}</span>
    </div>

    <h3>Roles</h3>
    <c:forEach var="role" items="${roles}">
        <span style="background: #007bff; color: white; padding: 5px; margin: 2px; display: inline-block;">
            ${role}
        </span>
    </c:forEach>

    <form action="/logout" method="post" style="margin-top: 20px;">
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        <button type="submit">Logout</button>
    </form>
</body>
</html>