<header>
    <nav class="navbar navbar-expand-md navbar-dark"
         style="background-color: mediumseagreen">
        <div>
            <a href=href="<%= request.getContextPath() %>/login" class="navbar-brand"> Todo App</a>
        </div>

        <c:choose>
            <c:when test="${sessionScope.user_logged != null}">
                <ul class="navbar-nav navbar-collapse justify-content-end">
                    <li><a href="<%= request.getContextPath() %>/logout" class="nav-link">Log Out</a></li>
                </ul>
            </c:when>
            <c:otherwise>
                <ul class="navbar-nav navbar-collapse justify-content-end">
                    <li><a href="<%= request.getContextPath() %>/login" class="nav-link">Login</a></li>
                    <li><a href="<%= request.getContextPath() %>/register" class="nav-link">Signup</a></li>
                </ul>
            </c:otherwise>
        </c:choose>
    </nav>
</header>