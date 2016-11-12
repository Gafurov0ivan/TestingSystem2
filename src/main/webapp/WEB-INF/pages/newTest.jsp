<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en" xmlns="http://www.w3.org/1999/html">
<head>
  <meta charset="UTF-8">
  <title>Title</title>
  <!-- Latest compiled and minified CSS -->
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">


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
        <li><a href="/">HOME</a></li>
        <li><a href="/userTests">MY PROFILE</a></li>
        <li class="active"><a href="#">ADD TEST</a></li>
        <li><a href="/about">CONTACT</a></li>
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
        <form role="form" autocomplete="off" lpformnum="1" class="control">
          <fieldset class="myclass">
            <div class="form-group">
              <label class="control-label" for="question">Текст вопроса</label>
              <textarea class="form-control" id="questiontext" name="question" rows="2">Введите текст вопроса</textarea>
            </div>
            <!-- Form Name -->
            <legend></legend>
            <!-- Fuel UX Radios http://getfuelux.com/javascript.html#radio -->
            <div class="form-group">
              <label id="radios" class="control-label">Количество правильных ответов</label>
              <div class=" required">
                <div class="radio">
                  <label class="radio-custom" data-initialize="radio" id="radios-0">
                    <input name="radios" type="radio" value="single">
                    <span class="radio-label">Один</span>
                  </label>
                </div>
                <div class="radio">
                  <label class="radio-custom" data-initialize="radio" id="radios-1">
                    <input name="radios" type="radio" value="multiple">
                    <span class="radio-label">Несколько</span>
                  </label>
                </div>
              </div>
            </div>
            <label id="answers" class="control-label">Варианты ответов</label>

            <div class="entry input-group form-group">
              <div class="input-group-addon"><input type="checkbox" name="s[]" value="ch1" class="chkbx"></div>
              <input class="form-control forclone" name="field[0]" type="text" placeholder="Введите вариант ответа" id="field1">
              <span class="input-group-btn">
                            <button class="btn btn-success btn-add" type="button">
                                <span class="glyphicon glyphicon-plus"></span>
                            </button>
                            </span>
            </div>
          </fieldset>
          <button type="submit" formmethod="post" id="submitForm" class="btn btn-primary" aria-label="">
            Сохранить
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
      newEntry.find('input').attr('name','filed[' + nm + ']');
      controlForm.find('.entry:last .chkbx').val('ch' + nm);
      controlForm.find('.entry:last .chkbx').attr('name','s[]');
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
</body>
</html>