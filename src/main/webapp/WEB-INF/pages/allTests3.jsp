<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script type="text/javascript" src="/resources/js/materialize.min.js"></script>
<link href="http://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
<link type="text/css" rel="stylesheet" href="/resources/css/materialize.min.css" media="screen,projection"/>
<head>
    <title>Все тесты</title>
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
                <li  class="active"><a href="#">ГЛАВНАЯ</a></li>
                <li><a href="/userTests">МОЙ ПРОФИЛЬ</a></li>
            </ul>
            <ul class="right hide-on-med-and-down">
                <li><a class="dropdown-button" href="#!" data-activates="dropdown1">${userName}<i
                        class="material-icons right">arrow_drop_down</i></a></li>
            </ul>
        </div>
    </nav>
</div>

<div class="row">
    <div class="col s3"></div>
    <div class="col s6">
        <nav class="transparent">
        <div class="nav-wrapper">
            <form>
                <div class="input-field">
                    <input id="search" type="search" name="captionFilter" value="${captionFilter}" placeholder="Наименование">
                    <label for="search"><i class="material-icons">search</i></label>
                </div>
            </form>
        </div>
            </nav>
        <br/>
        <c:if test="${tests.isEmpty()}">
            <div style="color: #EF3B3A; text-align: center; font-size: 14pt">
                Ни одна запись не найдена.
            </div>
        </c:if>

        <table class="table table-striped" style="font-size: 11pt">
            <thead/>
            <tbody>
            <c:forEach items="${tests}" var="test">
                <tr>
                    <td>
                        <a href="test?id=${test.id}">
                            <c:out value="${test.caption}"/>
                        </a>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
    <div class="col s3"></div>
</div>
<script type="text/javascript">


    function clear() {

        console.log("ffff");
        $('#search').val('');
    }

</script>
</body>
</html>
