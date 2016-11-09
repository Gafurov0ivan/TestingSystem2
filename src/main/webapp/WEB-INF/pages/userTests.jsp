<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
  <title>Мои тесты</title>
</head>
<body>
<div class="starter-template">
  <h1>Мои тесты</h1>
</div>
<br>
<table>
  <thead>
  <tr>
    <th>Наименование теста</th>
  </tr>
  </thead>
  <tbody>
  <c:forEach items="${tests}" var = "test">
    <tr>
      <td><c:out value="${test.caption}"/></td>
    </tr>
  </c:forEach>
  </tbody>
</table>

<p><a href="newTest">Создать новый тест</a></p>
</body>
</html>
