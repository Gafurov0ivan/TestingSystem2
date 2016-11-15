<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<html>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<head>
    <title>Completed tests</title>
</head>
<body>

<nav class="navbar navbar-default">
    <div class="container-fluid">
        <div class="navbar-header">
            <a class="navbar-brand" href="#">Ultimate Testing System</a>
        </div>
        <ul class="nav navbar-nav">
            <li><a href="/">HOME</a></li>
            <li class="active"><a href="#">MY PROFILE</a></li>
            <li><a href="/editTest">ADD TEST</a></li>
            <li><a href="/about">CONTACT</a></li>
        </ul>
    </div>
</nav>

<div class="container col-md-2"></div>
<div class="container col-md-8">
    <nav class="navbar navbar-default">
        <div class="container-fluid">
            <ul class="nav navbar-nav">
                <li><a href="userTests">MY TESTS</a></li>
                <li class="active"><a href="completedTests">COMPLETED TESTS</a></li>
            </ul>
        </div>
    </nav>

    <table class="table table-bordered">
        <thead>
        <tr>
            <th>Test name</th>
            <th>Date</th>
            <th>Result</th>
            <th>Result, %</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${tests}" var="test">
            <tr>
                <td><c:out value="${test.test.caption}"/></td>
                <td><fmt:formatDate value="${test.date}" pattern="dd.MM.yyyy HH:mm"/></td>
                <td><c:out value="${test.result} из ${test.test.questionCount}"/></td>
                <td><c:out value="${test.resultPercent}"/></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
<div class="container col-md-2"></div>
</body>
</html>
