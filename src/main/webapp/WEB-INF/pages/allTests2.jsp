<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<head>
    <title>Все тесты</title>
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
            <li class="active"><a href="/allTests">ГЛАВНАЯ</a></li>
            <li><a href="/userTests">МОЙ ПРОФИЛЬ</a></li>
        </ul>
        <ul class="nav navbar-nav navbar-right">
            <li><a href="/logout"><span class="glyphicon glyphicon-log-out"></span>ВЫХОД</a></li>
        </ul>
    </div>
</nav>
</div>
<div class="container col-md-1"></div>

<div class="container col-md-3"></div>
<div class="container col-md-6">

    <div class="panel panel-default">
        <div class="panel-heading">
            <h1 class="panel-title">Панель фильтрации</h1>
            <span class="pull-right clickable"><i class="glyphicon glyphicon-chevron-down"></i></span>
        </div>
        <div class="panel-body">
            <form class="form-horizontal">
                <input type="text" class="form-control" name="captionFilter" id="captionFilter"
                       value="${captionFilter}" placeholder="Наименование">
                <br/>
                <button type="submit" id="applyFilter" class="btn btn-default">Найти</button>
                <button type="button" id="findAll" class="btn btn-default">Сбросить</button>
            </form>
        </div>
    </div>

    <c:if test="${tests.isEmpty()}">
        <div  style="color: #EF3B3A; text-align: center; font-size: 14pt">
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
<div class="container col-md-3"></div>
<script type="text/javascript">

    $(document).on('click', '.panel-heading span.clickable', function (e) {
        var $this = $(this);
        if (!$this.hasClass('panel-collapsed')) {
            $this.parents('.panel').find('.panel-body').slideUp();
            $this.addClass('panel-collapsed');
            $this.find('i').removeClass('glyphicon-chevron-up').addClass('glyphicon-chevron-down');
        } else {
            $this.parents('.panel').find('.panel-body').slideDown();
            $this.removeClass('panel-collapsed');
            $this.find('i').removeClass('glyphicon-chevron-down').addClass('glyphicon-chevron-up');
        }
    });

    $('#findAll').on('click', function () {
        $('#captionFilter').val('');
        $('#applyFilter').click();
    });

</script>
</body>
</html>
