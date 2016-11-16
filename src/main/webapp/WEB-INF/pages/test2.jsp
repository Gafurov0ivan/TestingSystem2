<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<head>
  <meta charset="UTF-8">
  <title>Тест ${test.caption}</title>
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
      <li><a href="/about">КОНТАКТЫ</a></li>
    </ul>
  </div>
</nav>

<div class="container col-md-4"></div>
<div class="container col-md-4">

  <div class="row">
    <div class="control-group" id="fields">
      <legend></legend>
      <div class="form-group" style="text-align: center">
        <label class="control-label">${test.caption}</label>
      </div>
      <c:choose>
        <c:when test="${denied}">
          <div class="text" style="text-align: center; color: #EF3B3A">
            Вы уже проходили данный тест. Повторное прохождение невозможно.
          </div>
          <br/><br/>
          <div style="text-align: center">
            <a href="showTest?id=${test.id}" class="btn btn-primary">Посмотерть результат</a>
            <a href="completedTests" class="btn btn-primary">Перейти к пройденным тестам</a>
          </div>
        </c:when>
        <c:otherwise>
          <form>
          <c:forEach items="${test.questions}" var="qu" varStatus="outterLoop">
            <div id="form${outterLoop.index +1}" hidden="true">
              <div class="row">
                <div class="text">${outterLoop.index+1}. ${qu.question}</div>
                <i>Выберите ${qu.answerCount} варианта(ов).</i>
                <c:forEach items="${qu.answers}" var="answer" varStatus="innerLoop">
                  <div class="row">
                    <input type="checkbox" name="${qu.id}" value="${answer.id}"/>
                    <c:out value="${innerLoop.index+1}.  ${answer.text}"/>
                  </div>
                </c:forEach>
              </div>
            </div>
          </c:forEach>
          <div class="row" style="text-align: center">
            <button id="prevBtn" style="text-align: center" class="btn btn-primary" onclick="showPrev()">Предыдущий</button>
            <button id="nextBtn" style="text-align: center" class="btn btn-primary" onclick="showNext()">Следующий</button>
          </div>
            <br/><br/>
          <div id="submitForm" style="text-align: center">
            <button type="submit" formmethod="post" class="btn btn-primary">
              Завершить тест
            </button>
          </div>
          </form>
        </c:otherwise>

      </c:choose>
    </div>
  </div>
</div>
<div class="container col-md-4"></div>
<script type="text/javascript">
  document.page = 1;
  document.pageCount = 2;

  $(document).ready(function () {
    document.getElementById("form1").hidden = false;
    disableBtns();
  });

  function showPrev() {
    document.getElementById("form" + document.page).hidden = true;
    document.page--;
    document.getElementById("form" + document.page).hidden = false;
    disableBtns();
  }

  function showNext() {
    document.getElementById("form" + document.page).hidden = true;
    document.page++;
    document.getElementById("form" + document.page).hidden = false;
    disableBtns();
  }

  function disableBtns() {
    document.getElementById("prevBtn").disabled = document.page == 1;
    document.getElementById("nextBtn").disabled = document.page == document.pageCount;
    document.getElementById("submitForm").hidden = document.page < document.pageCount;
  }
</script>
</body>
</html>