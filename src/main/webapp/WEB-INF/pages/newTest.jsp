<!DOCTYPE html>
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
          <fieldset>
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
                    <input name="radios" type="radio" value="Один">
                    <span class="radio-label">Один</span>
                  </label>
                </div>
                <div class="radio">
                  <label class="radio-custom" data-initialize="radio" id="radios-1">
                    <input name="radios" type="radio" value="Несколько">
                    <span class="radio-label">Несколько</span>
                  </label>
                </div>
              </div>
            </div>
            <label id="answers" class="control-label">Варианты ответов</label>

            <div class="entry input-group form-group">
              <input class="form-control" name="fields[]" type="text"
                     placeholder="Введите вариант ответа" id="field1">
              <span class="input-group-btn">
                            <button class="btn btn-success btn-add" type="button">
                                <span class="glyphicon glyphicon-plus"></span>
                            </button>
                        </span>
            </div>

          </fieldset>
        </form>
        <div class="form-group">
          <label class="control-label" for="addquestion"></label>
          <div class="text-center">
            <button type="button" id="addquestion" name="addquestion" class="btn btn-primary btn-add-form clone" aria-label="">
              Добавить вопрос
            </button>

          </div>

        </div>
        <div class="form-group">
          <label class="control-label" for="addquestion"></label>
          <div class="text-center">
            <button type="button" id="savetest" name="addquestion" class="btn btn-primary btn-add-form" aria-label="">
              Получить JSON формы
            </button>

          </div>

        </div>
        <div class="form-group">
          <label class="control-label" for="jsonfld">JSON</label>
          <textarea class="form-control" id="jsonField" name="question" rows="5"></textarea>
        </div>
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

      var controlForm = $('.controls form:first'),
              currentEntry = $(this).parents('.entry:first'),
              newEntry = $(currentEntry.clone()).appendTo(controlForm);

      newEntry.find('input').val('');
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
  var regex = /^(.+?)(\d+)$/i;
  var cloneIndex = $(".clonedInput").length;

  function clone(){
    $(this).parents(".clonedInput").clone()
            .appendTo("controls")
            .attr("id", "clonedInput" +  cloneIndex)
            .find("*")
            .each(function() {
              var id = this.id || "";
              var match = id.match(regex) || [];
              if (match.length == 3) {
                this.id = match[1] + (cloneIndex);
              }
            })
            .on('click', 'button.clone', clone)
            .on('click', 'button.remove', remove);
    cloneIndex++;
  }
  function remove(){
    $(this).parents(".clonedInput").remove();
  }
  $("button.clone").on("click", clone);
  $("button.remove").on("click", remove);


</script>
<script type="text/javascript">
  $(function () {
    $(document).on('click', '.btn-add-form', function (e) {
      e.preventDefault();


      var frm = $("form.control");
      var formdata = JSON.stringify(frm);
      document.getElementById("jsonField").value = formdata;
      e.preventDefault();
      return formdata;
    });
  });


</script>
</body>
</html>