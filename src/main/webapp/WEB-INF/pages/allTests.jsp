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

<nav class="navbar navbar-default">
    <div class="container-fluid">
        <div class="navbar-header">
            <a class="navbar-brand" href="#">Ultimate Testing System</a>
        </div>
        <ul class="nav navbar-nav">
            <li><a href="/">ГЛАВНАЯ</a></li>
            <li><a href="/userTests">МОЙ ПРОФИЛЬ</a></li>
            <li><a href="/editTest">СОЗДАТЬ ТЕСТ</a></li>
            <li class="active"><a href="/allTests">ВСЕ ТЕСТЫ</a></li>
            <li><a href="/about">КОНТАКТЫ</a></li>
        </ul>
    </div>
</nav>


<div class="container col-md-3"></div>
<div class="container col-md-6">

    <%--<form id="filterPanel">--%>
        <%--<div class="row">--%>
            <%--Фильтр по наименованию:--%>
            <%--<input id="captionFilter" name="captionFilter" >--%>
        <%--</div>--%>
        <%--<div class="row">--%>
            <%--<button id="okBtn" type="submit" class="btn btn-default" value="ОК"/>--%>
            <%--<button id="reBtn" type="submit" class="btn btn-default" value="Сбросить" onclick="resetFilter()"/>--%>
        <%--</div>--%>
    <%--</form>--%>

    <%--<form>--%>
        <%--<div class="row">--%>
            <%--<div class="col-xs-8 col-xs-offset-2">--%>
                <%--<div class="input-group">--%>
                    <%--<div class="input-group-btn search-panel">--%>
                        <%--<button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown">--%>
                            <%--<span id="search_concept">Filter by</span> <span class="caret"></span>--%>
                        <%--</button>--%>
                        <%--<ul class="dropdown-menu" role="menu">--%>
                            <%--<li><a href="#contains">Contains</a></li>--%>
                            <%--<li><a href="#its_equal">It's equal</a></li>--%>
                            <%--<li><a href="#greather_than">Greather than ></a></li>--%>
                            <%--<li><a href="#less_than">Less than < </a></li>--%>
                            <%--<li class="divider"></li>--%>
                            <%--<li><a href="#all">Anything</a></li>--%>
                        <%--</ul>--%>
                    <%--</div>--%>
                    <%--<input type="hidden" name="search_param" value="all" id="search_param">--%>
                    <%--<input type="text" class="form-control" name="x" placeholder="Search term...">--%>
                <%--<span class="input-group-btn">--%>
                    <%--<button class="btn btn-default" type="button"><span class="glyphicon glyphicon-search"></span></button>--%>
                <%--</span>--%>
                <%--</div>--%>
            <%--</div>--%>
        <%--</div>--%>
    <%--</form>--%>

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
  function resetFilter() {
      document.getElementById("captionFilter").removeAttribute("name");
      document.getElementById("okBtn").click();

  }

</script>
</body>
</html>
