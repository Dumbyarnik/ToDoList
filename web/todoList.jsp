<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>

<head>
    <meta charset="ISO-8859-1">
    <title>To Do List</title>

    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
          crossorigin="anonymous">
</head>

<body>
<jsp:include page='header.jsp'>
    <jsp:param name="header" value=""/>
</jsp:include>

<div class="container">

    <h2>ToDo List</h2>

    <!-- Table of ToDos -->
    <form class="col-md-6 col-md-offset-3">
        <div class="container text-left">
            <table class="table table-bordered">
                <thead>
                    <tr>
                        <th>Name</th>
                    </tr>
                </thead>

                <tbody>
                    <c:forEach var="item" items="${listTodo}">
                        <tr>
                            <td><c:out value="${item}" /></td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>

            <c:forEach var="list" items="${listTodo}">
                <c:out value="${list}"/>
            </c:forEach>


        </div>
    </form>
</div>
<jsp:include page='footer.jsp'>
    <jsp:param name="header" value=""/>
</jsp:include>
</body>

</html>