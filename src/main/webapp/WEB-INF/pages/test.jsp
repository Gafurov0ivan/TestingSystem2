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
      <li><a href="/">HOME</a></li>
      <li><a href="/userTests">MY PROFILE</a></li>
      <li><a href="/editTest">ADD TEST</a></li>
      <li><a href="/about">CONTACT</a></li>
    </ul>
  </div>
</nav>

<div class="container col-md-4"></div>

<div class="container col-md-4">
  <div class="row">
    <div class="control-group" id="fields">
      <div class="controls">
        <form class="control">
          <legend></legend>

          <div class="form-group" style="text-align: center">
            <label class="control-label">${test.caption}</label>
          </div>

          <c:choose>
            <c:when test="${denied}">
              <div class="text">Вы уже проходили данный тест. Повторное прохождение невозможно.</div>
              <br/>
              <br/>

              <div style="text-align: center">
                <a href="showTest?id=${test.id}" class="btn btn-primary">Посмотерть результат</a>
                <a href="completedTests" class="btn btn-primary">Перейти к пройденным тестам</a>
              </div>
            </c:when>

            <c:otherwise>
              <c:forEach items="${test.questions}" var="qu" varStatus="outterLoop">
                <div class="row">

                  <div class="text">${outterLoop.index+1}. ${qu.question}</div>

                  <i>Выберите ${qu.answerCount} варианта(ов).</i>

                  <c:forEach items="${qu.answers}" var="answer" varStatus="innerLoop">
                    <div class="row">
                      <input type="checkbox" name="${qu.id}" value="${answer.id}"/>
                      <c:out value="${innerLoop.index+1}.  ${answer.text}"/>
                    </div>
                  </c:forEach>
                  <br/>
                </div>
              </c:forEach>
              <div style="text-align: center">
                <button type="submit" formmethod="post" id="submitForm" class="btn btn-primary" aria-label="">
                  Узнать результат
                </button>
              </div>

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
