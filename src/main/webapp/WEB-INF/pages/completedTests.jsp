<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<html>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<head>
  <title>Завершенные тесты</title>

</head>
<body>

<nav class="navbar navbar-default">
  <div class="container-fluid">
    <div class="navbar-header">
      <a class="navbar-brand" href="#">Ultimate Testing System</a>
    </div>
    <ul class="nav navbar-nav">
      <li><a href="/">HOME</a></li>
      <li class="active"><a href="/userTests">MY PROFILE</a></li>
      <li><a href="#">ADD TEST</a></li>
      <li><a href="/about">CONTACT</a></li>
    </ul>
  </div>
</nav>

<div class="container">

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
      <th>Наименование теста</th>
      <th>Дата прохождения</th>
      <th>Результат</th>
      <th>Результат, %</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${tests}" var="test">
      <tr>
        <td><c:out value="${test.test.caption}"/></td>
        <td><c:out value="${test.date}"/></td>
        <td><c:out value="${test.result} из ${test.test.questionCount}"/></td>
        <td><c:out value="${test.resultPercent}"/></td>
      </tr>
    </c:forEach>
    </tbody>
  </table>
</div>
</body>

<%--<script type="text/javascript">--%>
  <%--function formatDate(date) {--%>
    <%--var now = date;--%>
    <%--return dateFormat(now, "dddd, mmmm dS, yyyy, h:MM:ss TT");--%>

  <%--}--%>
<%--</script>--%>
</html>
