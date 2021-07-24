package web;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CommonServlet extends HttpServlet {
    protected void validate(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if (request.getSession().getAttribute("user_logged") == null){
            request.setAttribute("error", "You haven't logged in yet");
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }
    }
}
