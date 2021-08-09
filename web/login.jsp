<%--
  Created by IntelliJ IDEA.
  User: Dennis
  Date: 21.07.2021
  Time: 15:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>

<head>
  <meta charset="ISO-8859-1">
  <title>Login</title>
  <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">

  <style>
    .alert {
      display:inline-block;
    }
  </style>

</head>

<body>

<jsp:include page='jsp/loginheader.jsp'>
  <jsp:param name="header" value=""/>
</jsp:include>
<div class="container col-md-8 col-md-offset-3" style="overflow: auto">

  <br>
  <h1>Login</h1>
  <br>
  <!-- placeholder for error -->
  <div class="alert alert-success center" role="alert">
    <p>${sessionScope.error}</p>
  </div>
  <br>

  <form action="<%=request.getContextPath()%>/userlogin" method="post">

    <div class="form-group">
      <label for="username">User Name:</label> <input type="text" class="form-control" id="username" placeholder="User Name" name="username" required>
    </div>

    <div class="form-group">
      <label for="password">Password:</label> <input type="password" class="form-control" id="password" placeholder="Password" name="password" required>
    </div>

    <button type="submit" class="btn btn-primary">Submit</button>
  </form>
</div>
<jsp:include page='jsp/footer.jsp'>
  <jsp:param name="footer" value=""/>
</jsp:include>
</body>

</html>