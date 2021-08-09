package web;
/*
 * Class created on 23.07.2021
 * Class is user as a father class for other servlets
 * because it has a method validate and validateRoom
 * which all servlets need
 * */

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CommonServlet extends HttpServlet {

    // validates if a user already logged in
    protected boolean validate(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // if attribute of session is null
        if (request.getSession().getAttribute("user_logged") == null){
            request.getSession().setAttribute("error", "You haven't logged in yet");
            response.sendRedirect("/todoapp/userlogin");
            return false;
        }
        return true;
    }

    // validates if user already chose a room
    protected boolean validateRoom(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        if (request.getSession().getAttribute("room") == null){
            response.sendRedirect("/todoapp/room");
            return false;
        }
        return true;
    }

    // method for logout button
    protected void logout (HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // setting attributes to null and go to login screen
        request.getSession().setAttribute("user_logged", null);
        request.getSession().setAttribute("room", null);
        request.getSession().setAttribute("error", "You have logged out");
        response.sendRedirect("/todoapp/userlogin");
    }

    // method for change room button
    protected void changeRoom (HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        // setting attribute to null and go to room screen
        request.getSession().setAttribute("room", null);
        response.sendRedirect("/todoapp/room");
    }
}
