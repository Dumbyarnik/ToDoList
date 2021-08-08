package web;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CommonServlet extends HttpServlet {
    protected boolean validate(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if (request.getSession().getAttribute("user_logged") == null){
            request.getSession().setAttribute("error", "You haven't logged in yet");
            response.sendRedirect("/todoapp/userlogin");
            return false;
        }
        return true;
    }

    protected boolean validateRoom(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if (request.getSession().getAttribute("room") == null){
            response.sendRedirect("/todoapp/room");
            return false;
        }
        return true;
    }

    protected void logout (HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getSession().setAttribute("user_logged", null);
        request.getSession().setAttribute("error", "You have logged out");
        response.sendRedirect("/todoapp/userlogin");
    }
}
