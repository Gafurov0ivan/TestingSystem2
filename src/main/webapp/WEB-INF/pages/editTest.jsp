<jsp:useBean id="test" scope="request" type="ru.itpark.model.Test"/>
<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="en" xmlns="http://www.w3.org/1999/html">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <!-- Latest compiled and minified CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <link rel="stylesheet" href="<c:url value="/resources/css/editTest.css" />">

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
                <li></li>
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
            <form>
                <label class="control-label" for="question">Название теста</label>
            <div class="entry input-group form-group">

                <input class="form-control forclone" name="saveTestCaption" type="text" placeholder="Введите название теста"
                       id="field2" value="${test.caption}">
                <span class="input-group-btn">
                            <button class="btn btn-success" type="submit" value="" formmethod="post" id="saveName">сохранить</button>
                            </span>
            </div>
            </form>
        </div>
</div>
<div class="container col-md-4"></div>
<div class="container">
    <div class="container col-md-1"></div>
    <div class="container col-md-10">
        <c:set var="i" value="0"/>
        <c:forEach items="${test.questions}" var="question">
            <a class="btn btn-sm btn-upper ${(qId.toString().equals(i.toString())) ? 'btn-info' : 'btn-primary'}"
               href="/editTest?id=${test.id}&questionId=${i}">${i+1}</a>
            <c:set var="i" value="${i+1}"/>
        </c:forEach>
        <a class="btn btn-success btn-sm btn-upper" href="/editTest?id=${test.id}&addQuestion=true"><span
                class="glyphicon glyphicon-plus"></span></a>

    </div>

    <div class="container col-md-1"></div>
</div>
<div class="container col-md-4"></div>
<div class="container col-md-4">
    <div class="row">
        <div class="control-group" id="fields">
            <div class="controls">
                <form role="form" autocomplete="off" lpformnum="1" class="control">
                    <fieldset class="myclass">
                        <div class="form-group">
                            <label class="control-label" for="question">Текст вопроса</label>
                            <textarea class="form-control" id="questiontext" name="question"
                                      rows="2">${test.getQuestion(qId.intValue()).question}</textarea>
                        </div>
                        <!-- Form Name -->
                        <legend></legend>
                        <!-- Fuel UX Radios http://getfuelux.com/javascript.html#radio -->
                        <div class="form-group">
                            <label id="radios" class="control-label">Количество правильных ответов</label>
                            <div class=" required">
                                <div class="radio">
                                    <label class="radio-custom" data-initialize="radio" id="radios-0">
                                        <input name="radios" type="radio" value="single"
                                               <c:if test="${test.getQuestion(qId.intValue()).getAnswerCount() == 1}">checked</c:if>/>
                                        <span class="radio-label">Один</span>
                                    </label>
                                </div>
                                <div class="radio">
                                    <label class="radio-custom" data-initialize="radio" id="radios-1">
                                        <input name="radios" type="radio" value="multiple"
                                               <c:if test="${test.getQuestion(qId.intValue()).getAnswerCount() == 2}">checked</c:if>/>
                                        <span class="radio-label">Несколько</span>
                                    </label>
                                </div>
                            </div>
                        </div>
                        <label id="answers" class="control-label">Варианты ответов</label>


                        <c:set var="j" value="1"/>
                        <c:forEach items="${test.getQuestion(qId).answers}" var="answer">
                            <div class="entry input-group form-group">
                                <div class="input-group-addon">
                                    <input type="checkbox" name="s[]" value="ch${j}" class="chkbx"
                                           <c:if test="${answer.isCorrect}">checked</c:if>/>
                                </div>
                                <input class="form-control forclone" name="field[${j-1}]" type="text"
                                       placeholder="Введите вариант ответа" id="field${j}" value="${answer.text}">
                                <span class="input-group-btn">
                            <button class="btn ${(j).toString().equals(test.getQuestion(qId).answers.size().toString()) ? 'btn-add btn-success ' : 'btn-remove btn-danger'}"
                                    type="button">
                                <span class="${(j).toString().equals(test.getQuestion(qId).answers.size().toString()) ? 'glyphicon glyphicon-plus' : 'glyphicon glyphicon-minus'}"></span>
                            </button>
                            </span>
                            </div>
                            <c:set var="j" value="${j+1}"/>
                        </c:forEach>
                        <c:if test="${test.getQuestion(qId).answers == null || test.getQuestion(qId).answers.size() == 0}">
                            <div class="entry input-group form-group">
                                <div class="input-group-addon"><input type="checkbox" name="s[]" value="ch1"
                                                                      class="chkbx"></div>
                                <input class="form-control forclone" name="field[0]" type="text"
                                       placeholder="Введите вариант ответа" id="field1">
                                <span class="input-group-btn">
                            <button class="btn btn-add btn-success" type="button">
                                <span class="glyphicon glyphicon-plus"></span>
                            </button>
                            </span>
                            </div>
                        </c:if>


                    </fieldset>
                    <input type="hidden" name="SAVE" value=""/>
                    <button type="submit" formmethod="post" id="submitForm" class="btn btn-primary" aria-label="">
                        Сохранить
                    </button>
                </form>
                <form method="post" class="text-center">
                    <input type="hidden" name="REMOVE" value=""/>
                    <button type="${test.questions.size() == 1 ? 'hidden' : 'submit'}" formmethod="post"
                            id="removeQuestionButton" class="btn btn-danger btn-upper" aria-label="">
                        DELETE QUESTION
                    </button>
                </form>
            </div>
        </div>
    </div>
</div>
<div class="container col-md-4"></div>
</div>


</div>
<!-- jQuery library -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>

<!-- Latest compiled JavaScript -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<!--script src="http://bootsnipp.com/dist/scripts.min.js" ></script-->
<script type="text/javascript">
    $(function () {
        $(document).on('click', '.btn-add', function (e) {
            e.preventDefault();

            var controlForm = $('.myclass'),
                    currentEntry = $(this).parents('.entry:first'),
                    newEntry = $(currentEntry.clone()).appendTo(controlForm);
            nm = $('.forclone').length
            finded = newEntry.find('input');
            attrib = finded.attr('type');
            newEntry.find('input').val('');
            newEntry.find('input').attr('name', 'field[' + nm + ']');
            controlForm.find('.entry:last .chkbx').val('ch' + nm);
            controlForm.find('.entry:last .chkbx').attr('name', 's[]');
            controlForm.find('.entry:not(:last) .btn-add')
                    .removeClass('btn-add').addClass('btn-remove')
                    .removeClass('btn-success').addClass('btn-danger')
                    .html('<span class="glyphicon glyphicon-minus"></span>');
        }).on('click', '.btn-remove', function (e) {
            $(this).parents('.entry:first').remove();

            e.preventDefault();
            return false;
        });
    });

</script>
<script type="text/javascript">
    $('#form').submit(function (eventObj) {
        $(this).append('<input type="hidden" name="SAVE" value="" /> ');
        return true;
    });
</script>
</body>
</html>