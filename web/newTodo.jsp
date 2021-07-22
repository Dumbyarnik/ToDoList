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

            <div class="form-group">
                <label for="itemName">Name:</label>
                <input type="text" class="form-control" id="itemName" placeholder="Item Name" name="itemName" required>

                <p>Status:</p>
                <fieldset class="form-group">
                    <label>Todo Status</label>
                    <select class="form-control" name="status">
                        <option value="1">planned</option>
                        <option value="2">in progress</option>
                        <option value="3">complete</option>
                    </select>
                </fieldset>

                <label for="date">Date:</label>
                <input type="date" class="form-control" id="date" placeholder="Date" name="date" required>
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