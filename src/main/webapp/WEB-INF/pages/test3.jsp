<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<html>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script type="text/javascript" src="/resources/js/materialize.min.js"></script>
<link href="http://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
<link type="text/css" rel="stylesheet" href="/resources/css/materialize.min.css" media="screen,projection"/>
<head>
  <meta charset="UTF-8">
  <title>Тест "${test.caption}"</title>
</head>

<body>
<div class="row">
  <ul id="dropdown1" class="dropdown-content">
    <li><a href="/logout">ВЫХОД</a></li>
  </ul>
  <nav class="teal lighten-3">
    <div class="nav-wrapper">
      <div class="brand-logo center">
        Ultimate Testing System
      </div>
      <ul class="left hide-on-med-and-down">
        <li><a href="/allTests">ГЛАВНАЯ</a></li>
        <li><a href="/userTests">МОЙ ПРОФИЛЬ</a></li>
      </ul>
      <ul class="right hide-on-med-and-down">
        <li><a class="dropdown-button" href="#!" data-activates="dropdown1">${userName}<i
                class="material-icons right">arrow_drop_down</i></a></li>
      </ul>
    </div>
  </nav>
</div>


<div class="col s1"></div>
<div class="col s10">
<div id="upperBtns">
  <c:forEach items="${test.questions}" var="question" varStatus="loop">
    <a id="btn${loop.index+1}" class="btn btn-sm btn-upper btn-primary" onclick="show(this)">${loop.index+1}</a>
  </c:forEach>
</div>
</div>
<div class="col s1"></div>

<div class="row">
<div class="col s4"></div>
<div class="col s4">
  <div class="row">
    <div class="control-group" id="fields">
      <legend></legend>
      <div class="form-group" style="text-align: center">
        <label class="control-label">${test.caption}</label>
      </div>

      <c:choose>
      <c:when test="${testNotAvailable}">
        <div class="text" style="text-align: center; color: #EF3B3A; font-size: large">
          На данный момент тест недоступен для прохождения по решению автора.
        </div>
          <br/><br/>
        <div style="text-align: center">
          <a href="allTests" class="btn btn-primary">Просмотреть доступные тесты</a>
        </div>
      </c:when>
      <c:otherwise>
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
                <div class="text" style="text-align: center">
                  <i>Вопрос № ${outterLoop.index+1}</i>
                </div>
                <br/> <br/>

                <div class="text">${qu.question}</div>
                <i>Выберите ${qu.answerCount} варианта(ов).</i>
                <br/> <br/>
                <c:forEach items="${qu.answers}" var="answer" varStatus="innerLoop">
                  <div class="row">
                    <input type="${(qu.answerCount==1) ? 'radio' : 'checkbox'}"
                           id="ch_${innerLoop.index}" name="${qu.id}" value="${answer.id}"/>
                    <label for="ch_${innerLoop.index}"><c:out value="${innerLoop.index+1}.  ${answer.text}"/></label>
                  </div>
                  <br/>
                </c:forEach>
              </div>
            </c:forEach>
            <div class="row" style="text-align: center">
              <button id="prevBtn" type="button" style="text-align: center"
                      class="btn btn-sm btn-primary" onclick="showPrev()">Предыдущий
              </button>
              <button id="nextBtn" type="button" style="text-align: center"
                      class="btn btn-sm btn-primary" onclick="showNext()">Следующий
              </button>
            </div>
            <br/><br/>

            <div id="submitForm" style="text-align: center">
              <button type="submit" formmethod="post" class="btn waves-effect waves-light">
                Завершить тест
              </button>
            </div>
          </form>
        </c:otherwise>
        </c:choose>
      </c:otherwise>
      </c:choose>
    </div>
  </div>
</div>
<div class="col s4"></div>
</div>

<script type="text/javascript">
  document.page = 1;
  document.pageCount = ${fn:length(test.questions)};

  $(document).ready(function () {
    document.getElementById("upperBtns").hidden = ${denied};
    document.getElementById("form1").hidden = false;
    disableBtns();
    var btn = document.getElementById("btn1");
    btn.classList.add("btn-info");
    btn.classList.remove("btn-primary");
  });

  function showPrev() {
    var old = document.page;
    document.page--;
    refreshButtonsAndForms(old, document.page);
    disableBtns();
  }

  function showNext() {
    var old = document.page;
    document.page++;
    refreshButtonsAndForms(old, document.page);
    disableBtns();
  }

  function disableBtns() {
    document.getElementById("prevBtn").disabled = document.page == 1;
    document.getElementById("nextBtn").disabled = document.page == document.pageCount;
    document.getElementById("submitForm").hidden = document.page < document.pageCount;
  }

  function show(btn) {
    var id = btn.textContent;
    refreshButtonsAndForms(document.page, id);
    document.page = id;
    disableBtns();
  }

  function refreshButtonsAndForms(oldi, newi) {
    var btnOld = document.getElementById("btn"+oldi);
    btnOld.classList.remove("btn-info");
    btnOld.classList.add("btn-primary");

    var btnNew = document.getElementById("btn"+newi);
    btnNew.classList.add("btn-info");
    btnNew.classList.remove("btn-primary");

    document.getElementById("form" + oldi).hidden = true;
    document.getElementById("form" + newi).hidden = false;
  }


</script>
</body>
</html>