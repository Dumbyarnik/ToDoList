<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
<jsp:include page='todoheader.jsp'>
    <jsp:param name="header" value=""/>
</jsp:include>

<div class="container">

    <h2>New Todo</h2>
    <div class="col-md-6 col-md-offset-3">

        <form action="<%=request.getContextPath()%>/newtodo" method="post">

            <div class="form-group">
                <label for="itemName">Name:</label>
                <c:choose>
                    <c:when test="${sessionScope.todo != null}">
                        <input type="text" class="form-control" id="itemName"
                               value="<c:out value='${sessionScope.todo.item}' />" placeholder="Item Name" name="itemName" required>
                    </c:when>
                    <c:otherwise>
                        <input type="text" class="form-control" id="itemName" placeholder="Item Name" name="itemName" required>
                    </c:otherwise>
                </c:choose>

                <fieldset class="form-group">
                    <label>Todo Status</label>

                    <c:choose>
                        <c:when test="${sessionScope.todo != null}">
                            <c:choose>
                                <c:when test="${sessionScope.todo.status.equals('planned')}">
                                    <select class="form-control" name="status">
                                        <option selected="selected" value="planned">planned</option>
                                        <option value="in progress">in progress</option>
                                        <option value="complete">complete</option>
                                    </select>
                                </c:when>
                                <c:when test="${sessionScope.todo.status.equals('in progress')}">
                                    <select class="form-control" name="status">
                                        <option value="planned">planned</option>
                                        <option selected="selected" value="in progress">in progress</option>
                                        <option value="complete">complete</option>
                                    </select>
                                </c:when>
                                <c:otherwise>
                                    <select class="form-control" name="status">
                                        <option value="planned">planned</option>
                                        <option value="in progress">in progress</option>
                                        <option selected="selected" value="complete">complete</option>
                                    </select>
                                </c:otherwise>
                            </c:choose>
                        </c:when>
                        <c:otherwise>
                            <select  class="form-control" name="status">
                                <option selected="selected" value="planned">planned</option>
                                <option value="in progress">in progress</option>
                                <option  value="complete">complete</option>
                            </select>
                        </c:otherwise>
                    </c:choose>
                </fieldset>

                <label for="date">Date:</label>
                <c:choose>
                    <c:when test="${sessionScope.todo != null}">
                        <input type="date" class="form-control" id="date"
                               value="<c:out value='${sessionScope.todo.date}' />" placeholder="Date" name="date" required>
                    </c:when>
                    <c:otherwise>
                        <input type="date" class="form-control" id="date" placeholder="Date" name="date" required>
                    </c:otherwise>
                </c:choose>
            </div>

            <c:choose>
                <c:when test="${sessionScope.todo != null}">
                    <input type="submit" name="edit" value="Edit">
                    <input type="hidden" name="edit_id" value="${sessionScope.todo.id}" />
                </c:when>
                <c:otherwise>
                    <input type="submit" name="new" value="Add New">
                </c:otherwise>
            </c:choose>


        </form>
    </div>
</div>
<jsp:include page='footer.jsp'>
    <jsp:param name="header" value=""/>
</jsp:include>
</body>

</html>