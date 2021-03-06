<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<html>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<head>
  <meta charset="UTF-8">
  <title>Ответы на тест ${test.caption}</title>
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
        <form class="control">
          <legend></legend>

          <c:choose>

            <c:when test="${not empty test}">
              <div class="form-group" style="text-align: center">
                <input type="hidden" name="testId" value="${test.id}">
                <label class="control-label">${test.caption}</label>
                <br/>
                <div class="text">
                  Результат: ${correctQuestionIds.size()} из ${test.questions.size()}
                </div>
              </div>
              <c:forEach items="${test.questions}" var="qu" varStatus="outterLoop">
                <div class="text">${outterLoop.index+1}. ${qu.question}</div>

                <c:choose>
                  <c:when test="${correctQuestionIds.contains(qu.id)}">
                    <div style="color: #43A047" class="text">Ответ правильный.</div>
                  </c:when>
                  <c:otherwise>
                    <div style="color: #EF3B3A" class="text">Ответ неправильный.</div>
                  </c:otherwise>
                </c:choose>

                <c:forEach items="${qu.answers}" var="answer" varStatus="innerLoop">
                  <div class="row">
                    <div style="${answer.isCorrect ? 'color: #43A047' : ''} ">
                      <input type="${(qu.answerCount==1) ? 'radio' : 'checkbox'}" disabled="true" <c:if test="${fn:contains(userAnswers, answer.id)}">checked</c:if>/>
                      <c:out value="${innerLoop.index+1}. ${answer.text}"/>
                    </div>
                  </div>
                </c:forEach>
                <br/>

              </c:forEach>
              <div style="text-align: center">
                <a href="completedTests" class="btn btn-primary">Перейти к пройденным тестам</a>
              </div>
            </c:when>

            <c:otherwise>
              <form>
                <div class="text" style="text-align: center; color: #EF3B3A">
                  Проверить результаты теста можно только после прохождения.
                </div>
              </form>
              <form>
                <div style="text-align: center">
                  <a href="test?id=${testId}" class="btn btn-primary">Пройти тест сейчас?</a>
                  <a href="completedTests" class="btn btn-primary">Перейти к пройденным тестам</a>
                </div>
              </form>
            </c:otherwise>

          </c:choose>
        </form>
      </div>
    </div>
  </div>
</div>
<div class="container col-md-4"></div>
</body>
</html>
