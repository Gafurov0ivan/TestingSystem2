<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<head>
    <meta charset="UTF-8">
    <title>Test</title>
</head>
<body>

<nav class="navbar navbar-default">
    <div class="container-fluid">
        <div class="navbar-header">
            <a class="navbar-brand" href="#">Ultimate Testing System</a>
        </div>
        <ul class="nav navbar-nav">
            <li><a href="/">HOME</a></li>
            <li><a href="/userTests">MY PROFILE</a></li>
            <li><a href="/newTest">ADD TEST</a></li>
            <li><a href="/about">CONTACT</a></li>
        </ul>
    </div>
</nav>
</div>


<div class="container col-md-4"></div>
<div class="container col-md-4">
    <div class="row">
        <h2>Тест "${test.caption}"</h2>

        <form>
            <c:forEach items="${test.questions}" var="qu">
                <c:out value="${qu.question}"/>
                <c:if test="${qu.answerCount}>1">
                    <div class="text">
                        Выберите ${qu.answerCount} правильных вариантов
                    </div>
                </c:if>
                <c:forEach items="${qu.answers}" var="answer">
                    <div class="row">
                        <input type="checkbox" value="${answer.id}">
                        <input type="radio" value="${answer.id}">
                        <c:out value="${answer.text}"/>
                    </div>
                </c:forEach>
                <br/>
            </c:forEach>
            <button type="submit" class="btn btn-default">Finish</button>
        </form>
    </div>
</div>
<div class="container col-md-4"></div>

</body>
</html>
