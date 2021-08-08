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
    <title>Room</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">

    <style>
        .alert {
            display:inline-block;
        }
    </style>

</head>

<body>

<jsp:include page='roomheader.jsp'>
    <jsp:param name="header" value=""/>
</jsp:include>
<div class="container col-md-8 col-md-offset-3" style="overflow: auto">

    <br>
    <h1>Room</h1>
    <br>

    <form action="<%=request.getContextPath()%>/room" method="post">

        <div class="form-group">
            <label>Room:</label>
            <input type="text" class="form-control" placeholder="Room" name="room" required>
        </div>

        <button type="submit" class="btn btn-primary">Choose or create a room</button>
    </form>
</div>
<jsp:include page='footer.jsp'>
    <jsp:param name="footer" value=""/>
</jsp:include>
</body>

</html>
