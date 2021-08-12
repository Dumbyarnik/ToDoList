package web;
/*
 * Class created on 23.07.2021
 * Class is used to control login screen
 * */

import client.Client;
import server.ServerInterface;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;


@WebServlet(name= "/userlogin")
public class LoginServlet extends CommonServlet {
    private static final long serialVersionUID = 1L;

    // Server side
    Registry registry = null;
    ServerInterface serverInterface;

    public void init() {
        // initializing server
        try {
            registry = LocateRegistry.getRegistry();
            serverInterface = (ServerInterface) registry.lookup("ChatServer");
        } catch (NotBoundException | RemoteException ex) {
            ex.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // if user is not logged -> login screen
        if (request.getSession().getAttribute("user_logged") == null) {
            request.getRequestDispatcher("/login.jsp").forward(request, response);
            request.getSession().setAttribute("error", null);
        }
        // if button logout was pressed
        else if (request.getParameter("action") != null) {
            if (request.getParameter("action").equals("logout")){
                this.logout(request, response);
            }
        }
        // if user logged in, but didn't change a room
        else if (request.getSession().getAttribute("room") == null) {
            response.sendRedirect("/todoapp/room");
        }
        else {
            response.sendRedirect("/todoapp/todolist");
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        authenticate(request, response);
    }

    private void authenticate(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        // login user on the server
        int result = 0;
        try {
            result = serverInterface.loginUser(username, password);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
         // if user was logged
        if (result == 1) {
            request.getSession().setAttribute("user_logged", username);
            response.sendRedirect("/todoapp/room");


            /* experimental code for the client */
            Client client = new Client();
            try {
                client.startClient();
            } catch (NotBoundException e) {
                e.printStackTrace();
            }

            request.getSession().setAttribute("client", client);
            /* end of experimental code */
        }
        // if username or password were wrong
        else {
            request.getSession().setAttribute("error", "Username or password are wrong");
            response.sendRedirect("/todoapp/userlogin");
        }
    }
}
