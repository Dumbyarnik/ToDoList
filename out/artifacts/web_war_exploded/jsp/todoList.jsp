<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>

<head>
    <meta charset="ISO-8859-1">
    <title>Todo List</title>


    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
          crossorigin="anonymous">

    <style>
        input[type=button],
        input[type=submit] {
            background-color: white;
            color: green;
            border-radius: 5px;
            border: 1px solid green;
            padding: 5px 5px;
            margin: 5px 5px;
            cursor: pointer;
        }
    </style>
</head>

<body>
<jsp:include page='todoheader.jsp'>
    <jsp:param name="header" value=""/>
</jsp:include>

<div class="container">

    <br>
    <h2>Room: ${sessionScope.room}</h2>
    <br>

    <!-- Table of ToDos -->
    <form class="col-md-10 col-md-offset-4" action="${pageContext.request.contextPath}/todolist" method="POST">
        <div class="container text-left">
            <input type="submit" name="add" value="Add">
            <br>
            <table class="table table-bordered">
                <thead>
                    <tr>
                        <th scope="col">Name</th>
                        <th scope="col">Status</th>
                        <th scope="col">Date</th>
                        <th scope="col">Days Left</th>
                        <th scope="col">Action</th>
                    </tr>
                </thead>

                <tbody>
                    <c:forEach var="todo" items="${todoList}">
                        <tr>
                            <td> <c:out value="${todo.item}" /> </td>
                            <td> <c:out value="${todo.status}" /> </td>
                            <td> <c:out value="${todo.date}" /> </td>
                            <td> <c:out value="${todo.days}" /> </td>
                            <td>
                                <input type="submit" name="delete" value="Delete">
                                <input type="hidden" name="delete_id" value="${todo.id}" />

                                <input type="submit" name="edit" value="Edit">
                                <input type="hidden" name="edit_id" value="${todo.id}" />
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
    </form>
    <br>

    <h4>Updates</h4>
    <c:forEach items="${sessionScope.updates}" var="item">
        ${item}<br>
    </c:forEach>
    <br>
</div>
<jsp:include page='footer.jsp'>
    <jsp:param name="header" value=""/>
</jsp:include>
</body>

</html>