<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Мои тесты</title>
</head>
<body>
<div class="starter-template">
  <h1>Универсальная тестовая система</h1>
</div>
<table border=1>
  <thead>
  <tr>
    <th>ID</th>
    <th>Название теста</th>
  </tr>
  </thead>
  <tbody>
  <c:forEach items="${tests}" var="test">
    <tr>
      <td><c:out value="${test.id}"/></td>
      <td><c:out value="${test.caption}"/></td>
    </tr>
  </c:forEach>
  </tbody>
</table>
<p><a href="newTest">Добавить новый тест</a></p>
</body>
</html>
