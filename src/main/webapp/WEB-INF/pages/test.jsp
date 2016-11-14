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

<div class="container col-md-4"></div>

<div class="container col-md-4">
    <div class="row">
        <div class="control-group" id="fields">
            <div class="controls">
                <form role="form" autocomplete="off" lpformnum="1" class="control">
                    <fieldset class="myclass">
                        <div class="form-group">
                            <input type="hidden" name="testId" value="${test.id}">
                            <label class="control-label" >${test.caption}</label>
                        </div>
                        <legend></legend>

                        <c:forEach items="${test.questions}" var="qu">
                        <div class="form-group">
                            <div class="text">
                                    ${qu.question}
                            </div>
                            <div class="text" id="quCount" onload="formatText${qu.answerCount})">
                            </div>
                            <c:forEach items="${qu.answers}" var="answer">
                                <div class="row">
                                    <input type="checkbox" name="${qu.id}" value="${answer.id}">
                                    <%--<input type="radio" value="${answer.id}">--%>
                                    <c:out value="${answer.text}"/>
                                    <c:out value="${answer.isCorrect}"/>
                                </div>
                            </c:forEach>
                            <br/>
                        </div>
                        </c:forEach>

                    </fieldset>
                    <button type="submit" formmethod="post" id="submitForm" class="btn btn-primary" aria-label="">
                        Узнать результат
                    </button>
                </form>
            </div>
        </div>
    </div>
</div>
<div class="container col-md-4"></div>
</body>
</html>
