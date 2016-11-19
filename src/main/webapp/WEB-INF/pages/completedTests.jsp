<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<html>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<head>
    <title>Пройденные тесты</title>
</head>
<body>

<nav class="navbar navbar-default">
    <div class="container-fluid">
        <div class="navbar-header">
            <a class="navbar-brand" href="#">Ultimate Testing System</a>
        </div>
        <ul class="nav navbar-nav">
            <li><a href="/">ГЛАВНАЯ</a></li>
            <li class="active"><a href="#">МОЙ ПРОФИЛЬ</a></li>
            <li><a href="/editTest">СОЗДАТЬ ТЕСТ</a></li>
            <li><a href="/about">КОНТАКТЫ</a></li>
        </ul>
    </div>
</nav>

<div class="container col-md-2"></div>
<div class="container col-md-8">
    <nav class="navbar navbar-default">
        <div class="container-fluid">
            <ul class="nav navbar-nav">
                <li><a href="userTests">Мои тесты</a></li>
                <li class="active"><a href="completedTests">Пройденные тесты</a></li>
            </ul>
        </div>
    </nav>

    <table class="table table-bordered" style="font-size: 11pt">
        <thead>
        <tr>
            <th style="text-align: center" width="40px">№</th>
            <th style="text-align: center">Тест</th>
            <th style="text-align: center">Дата прохождения</th>
            <th style="text-align: center">Результат</th>
            <th style="text-align: center">Результат, %</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${tests}" var="test" varStatus="loop">
            <tr>
                <td style="text-align: center">${loop.index+1}</td>
                <td>
                    <a href="showTest?id=${test.test.id}">
                        <c:out value="${test.test.caption}"/>
                    </a>
                </td>
                <td><fmt:formatDate value="${test.date}" pattern="dd.MM.yyyy HH:mm"/></td>
                <td style="text-align: center"><c:out value="${test.result} из ${test.test.questions.size()}"/></td>
                <td style="text-align: center">
                    <c:out value="${test.getResultPercent()}"/>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
<div class="container col-md-2"></div>
</body>
</html>
