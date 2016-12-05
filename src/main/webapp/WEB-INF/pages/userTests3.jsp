<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script type="text/javascript" src="/resources/js/materialize.min.js"></script>
<link href="http://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
<link type="text/css" rel="stylesheet" href="/resources/css/materialize.min.css" media="screen,projection"/>
<head>
    <title>Мои тесты</title>
</head>
<body>
<div class="row">
    <ul id="dropdown1" class="dropdown-content">
        <li><a href="/logout">ВЫХОД</a></li>
    </ul>
    <nav class="teal lighten-1">
        <div class="nav-wrapper">
            <div class="brand-logo center">
                Ultimate Testing System
            </div>
            <ul class="left hide-on-med-and-down">
                <li><a href="/allTests">ГЛАВНАЯ</a></li>
                <li class="active"><a href="#">МОЙ ПРОФИЛЬ</a></li>
            </ul>
            <ul class="right hide-on-med-and-down">
                <li><a class="dropdown-button" href="#!" data-activates="dropdown1">${userName}<i
                        class="material-icons right">arrow_drop_down</i></a></li>
            </ul>
        </div>
    </nav>
</div>
<div class="row">
    <div class="col s2"></div>
    <div class="col s8">
        <nav class="navbar navbar-default">
            <div class="container-fluid">
                <ul class="nav navbar-nav">
                    <li class="active"><a href="userTests">Мои тесты</a></li>
                    <li><a href="completedTests">Пройденные тесты</a></li>
                </ul>
            </div>
        </nav>

        <table class="table table-bordered" style="font-size: 11pt">
            <thead>
            <tr>
                <th style="text-align: center" width="40px">№</th>
                <th style="text-align: center">Тест</th>
                <th style="text-align: center" width="180px">Доступен</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${tests}" var="test" varStatus="loop">
                <tr>
                    <form id="tabForm" action="userTests">
                        <td style="text-align: center">${loop.index+1}</td>
                        <td>
                            <div>
                                <a href="editTest?id=${test.id}">
                                    <c:out value="${test.caption}"/>
                                </a>
                            </div>
                        </td>
                        <td style="text-align: center">
                            <div class="switch">
                                <label>
                                    <input type="checkbox"
                                           <c:if test="${test.visible}">checked</c:if>
                                           onclick="changeStatus(${test.id});">
                                    <span class="lever"></span>
                                </label>
                                <button id="subButton2" hidden="true" type="submit" formmethod="post"></button>
                            </div>
                        </td>
                    </form>
                </tr>
            </c:forEach>
            </tbody>
        </table>

        <div style="text-align: center">
            <form class="text-center" action="newTest">
                <input type="hidden" name="ADDTEST" value="">
                <input type="submit" formmethod="post" id="removeQuestionButton" class="btn btn-primary btn-upper"
                       value="Создать новый тест"/>
            </form>
        </div>
    </div>
    <div class="col s2"></div>
</div>
<script type="text/javascript">

    function changeStatus(id) {
        $('<input />').attr('type', 'hidden')
                .attr('name', 'id')
                .attr('value', id)
                .appendTo('#tabForm');
        $('#subButton2').click();
    }
</script>
</body>
</html>
