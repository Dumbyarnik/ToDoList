<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>

<head>
    <meta charset="ISO-8859-1">
    <title>Insert title here</title>

    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
          crossorigin="anonymous">
</head>

<body>
<jsp:include page='header.jsp'>
    <jsp:param name="header" value=""/>
</jsp:include>

<div class="container">

    <h2>New Todo</h2>
    <div class="col-md-6 col-md-offset-3">

        <form action="<%=request.getContextPath()%>/newTodo" method="post">

            <br class="form-group">
                <label for="itemName">Name:</label> <input type="text" class="form-control" id="itemName" placeholder="Item Name" name="itemName" required>
                <p>Status:</p>
                <select name="status" id="status">
                    <option value="planned">planned</option>
                    <option value="in progress">in progress</option>
                    <option value="done">done</option>
                </select> <br>
                <label for="date">Date:</label> <input type="date" class="form-control" id="date" placeholder="Date" name="date" required>
            </div>

            <button type="submit" class="btn btn-primary">Submit</button>

        </form>
    </div>
</div>
<jsp:include page='footer.jsp'>
    <jsp:param name="header" value=""/>
</jsp:include>
</body>

</html>