<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<head>
  <title>Мои тесты</title>
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
        <li class="active"><a href="userTests">MY TESTS</a></li>
        <li><a href="completedTests">COMPLETED TESTS</a></li>
      </ul>
    </div>
  </nav>
  <form id="form" action="#" method="post" onsubmit="onsubmit();">
    <div class="btn-group" role="group">
      <button type="submit" class="btn btn-default">Delete</button>
    </div>
    <table class="table table-bordered">
      <thead>
      <tr>
        <th>Наименование</th>
      </tr>
      </thead>
      <tbody>
      <c:forEach items="${tests}" var="test">
        <tr>
          <td>
            <div>
              <input type="checkbox" name="id" value="${test.id}">
              <a href="newTest?id=${test.id}">
                <c:out value="${test.caption}"/>
              </a>
            </div>
          </td>
        </tr>
      </c:forEach>
      </tbody>
    </table>
  </form>
  <form>
    <a href="newTest" class="btn btn-default">Создать новый тест</a>
  </form>
</div>
<script type="text/javascript">
  $(function () {

    function onsubmit(){
//      $('#form').serialize() + '&yournewvar=yournewvalue';


      var form = $('#form1');
      form = form.serializeArray();
      form = form.concat(
        {action: "delete"}
      );
      console.log("fff");
      $.post('/change-user-details', form, function(d) {
        if (d.error) {
          alert(d.error)
        }
      });
    }
  });

</script>
</body>
</html>
