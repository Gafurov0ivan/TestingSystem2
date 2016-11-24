<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<head>
    <title>Мои тесты</title>
</head>
<body>

<nav class="navbar navbar-default">
    <div class="container-fluid">
        <div class="navbar-header">
            <a class="navbar-brand" href="#">Ultimate Testing System</a>
        </div>
        <ul class="nav navbar-nav">
            <li><a href="/allTests">ГЛАВНАЯ</a></li>
            <li class="active"><a href="#">МОЙ ПРОФИЛЬ</a></li>
            <li><a href="/about">КОНТАКТЫ</a></li>
        </ul>
    </div>
</nav>
<div class="container col-md-2"></div>
<div class="container col-md-8">
    <nav class="navbar navbar-default">
        <div class="container-fluid">
            <ul class="nav navbar-nav">
                <li class="active"><a href="userTests">Мои тесты</a></li>
                <li><a href="completedTests">Пройденные тесты</a></li>
            </ul>
        </div>
    </nav>
    <form id="tabForm" action="userTests">
        <div class="btn-group" role="group">
            <button id="subButton" type="submit" formmethod="post" class="btn btn-default">Скрыть</button>
            <button id="showButton" type="submit" formmethod="post" class="btn btn-default">Опубликовать</button>
        </div>
        <table class="table table-bordered" style="font-size: 11pt">
            <thead>
            <tr>
                <th width="10px"><input type="checkbox" onclick="selectAll(this)"/></th>
                <th style="text-align: center" width="40px">№</th>
                <th style="text-align: center">Тест</th>
                <th style="text-align: center" width="80px">Доступен</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${tests}" var="test" varStatus="loop">
                <tr>
                    <td>
                        <input type="checkbox" name="id" value="${test.id}">
                    </td>
                    <td style="text-align: center">${loop.index+1}</td>
                    <td>
                        <div>
                            <a href="editTest?id=${test.id}">
                                <c:out value="${test.caption}"/>
                            </a>
                        </div>
                    </td>
                    <td>
                        <c:choose>
                            <c:when test="${test.visible}">
                                <div style="text-align: center" class="text">Да</div>
                            </c:when>
                            <c:otherwise>
                                <div style="text-align: center" class="text">Нет</div>
                            </c:otherwise>
                        </c:choose>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </form>

    <div style="text-align: center">
        <form class="text-center" action="newTest">
            <input type="hidden" name="ADDTEST" value="">
            <input type="submit" formmethod="post" id="removeQuestionButton" class="btn btn-primary btn-upper" value="Создать новый тест"/>
        </form>
    </div>
</div>
<div class="container col-md-2"></div>
<script type="text/javascript">

    $('#subButton').click(function () {
        $('<input />').attr('type', 'hidden')
                .attr('name', 'makeInvisible')
                .appendTo('#tabForm');
    });

    $('#showButton').click(function () {
        $('<input />').attr('type', 'hidden')
                .attr('name', 'makeVisible')
                .appendTo('#tabForm');
    });

    function selectAll(bx) {
        console.log(bx.checked);
        var cbs = document.getElementsByTagName('input');
        for (var i = 0; i < cbs.length; i++) {
            if (cbs[i].type == 'checkbox') {
                cbs[i].checked = bx.checked;
            }
        }
    }

</script>
</body>
</html>
