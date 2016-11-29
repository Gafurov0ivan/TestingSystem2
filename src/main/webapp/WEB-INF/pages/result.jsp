<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<head>
    <meta charset="UTF-8">
    <title>Результат теста ${testCaption}</title>
</head>
<body>
<div class="container col-md-1"></div>
<div class="container col-md-10">
    <nav class="navbar navbar-default">
        <div class="container-fluid">
            <div class="navbar-header">
                <a class="navbar-brand" href="#">Ultimate Testing System</a>
            </div>
            <ul class="nav navbar-nav">
                <li><a href="/allTests">ГЛАВНАЯ</a></li>
                <li><a href="/userTests">МОЙ ПРОФИЛЬ</a></li>
            </ul>
            <ul class="nav navbar-nav navbar-right">
                <li><a href="/logout"><span class="glyphicon glyphicon-log-out"></span>ВЫХОД</a></li>
            </ul>
        </div>
    </nav>
</div>
<div class="container col-md-1"></div>

<div class="container col-md-4"></div>
<div class="container col-md-4">
    <div class="row">
        <div class="control-group" id="fields">
            <div class="controls">
                <form role="form" class="control">
                    <div class="form-group" style="text-align: center">
                        <label class="control-label">${testCaption}</label>
                    </div>
                    <legend></legend>
                    <div class="form-group" style="text-align: center">
                        <label class="control-label">Ваш результат ${testResult} из ${questionCount}
                            (${testResultPercent} %)</label>
                    </div>
                    <form>
                        <div style="text-align: center">
                            <a href="completedTests" class="btn btn-primary">Перейти к пройденным тестам</a>
                            <a href="showTest?id=${testId}" class="btn btn-primary">Посмотерть ответы</a>
                        </div>
                    </form>
                </form>
            </div>
        </div>
    </div>
</div>
<div class="container col-md-4"></div>

</body>
</html>
