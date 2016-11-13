<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html >
<head>
  <meta charset="UTF-8">
  <title>Login</title>

  <link href="<c:url value="/resources/css/login.css" />" rel="stylesheet">
  <script src="<c:url value="/resources/js/login.js" />"></script>
  <script src="<c:url value="/resources/js/jquery.1.10.2.min.js" />"></script>


</head>

<body>
<div class="login-page">
  <div class="form">
    <form class="register-form">
      <input type="text" placeholder="name"/>
      <input type="password" placeholder="password"/>
      <input type="text" placeholder="email address"/>
      <button>register</button>
      <p class="message"><a href="#">sign in</a></p>
    </form>
    <form class="login-form">
      <input type="text" placeholder="username"/>
      <input type="password" placeholder="password"/>
      <button>login</button>
      <p class="message"><a href="#">registration</a></p>
    </form>
  </div>
</div>
<script src='http://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js'></script>

<script src="js/login.js"></script>

</body>
</html>

