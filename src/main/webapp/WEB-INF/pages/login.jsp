<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <title>Войти</title>
  <link href="<c:url value="/resources/css/login.css" />" rel="stylesheet">
</head>

<body>
<div class="container">
  <form method="POST" action="${contextPath}/login" class="form-signin">
    <div class="form-group ${error != null ? 'has-error' : ''}">
      <span>${message}</span>
      <input name="username" type="text" class="form-control" placeholder="Username" />
      <input name="password" type="password" class="form-control" placeholder="Password"/>
      <span>${error}</span>
      <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
      <button class="button" type="submit">Войти</button>
      <p class="text-left"><a href="${contextPath}/registration" style="text-decoration: none;">Регистрация</a></p>
    </div>
  </form>
</div>
</body>
</html>


<%--<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>--%>
<%--<!DOCTYPE html>--%>
<%--<html >--%>
<%--<head>--%>
<%--<meta charset="UTF-8">--%>
<%--<title>Login</title>--%>

<%--<link href="<c:url value="/resources/css/login.css" />" rel="stylesheet">--%>
<%--<script src="<c:url value="/resources/js/login.js" />"></script>--%>
<%--<script src="<c:url value="/resources/js/jquery.1.10.2.min.js" />"></script>--%>


<%--</head>--%>

<%--<body>--%>
<%--<div class="login-page">--%>
<%--<div class="form">--%>
<%--<form class="register-form">--%>
<%--<input type="text" placeholder="name"/>--%>
<%--<input type="password" placeholder="password"/>--%>
<%--<input type="text" placeholder="email address"/>--%>
<%--<button>register</button>--%>
<%--<p class="message"><a href="#">sign in</a></p>--%>
<%--</form>--%>
<%--<form class="login-form">--%>
<%--<input type="text" placeholder="username"/>--%>
<%--<input type="password" placeholder="password"/>--%>
<%--<button>login</button>--%>
<%--<p class="message"><a href="#">registration</a></p>--%>
<%--</form>--%>
<%--</div>--%>
<%--</div>--%>
<%--<script src='http://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js'></script>--%>

<%--<script src="/resources/js/login.js"></script>--%>

<%--</body>--%>
<%--</html>--%>
