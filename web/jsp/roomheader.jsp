<header>
  <nav class="navbar navbar-expand-md navbar-dark"
       style="background-color: mediumseagreen">
    <div>
      <a href="<%= request.getContextPath() %>/todolist" class="navbar-brand"> Todo App</a>
    </div>

    <ul class="navbar-nav navbar-collapse justify-content-end">
      <li><a href="<%= request.getContextPath() %>/login?action=${"logout"}"
             class="nav-link">Log Out</a></li>
    </ul>
  </nav>
</header>
