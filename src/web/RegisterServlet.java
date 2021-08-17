package web;
/*
 * Class created on 23.07.2021
 * Class is used to control register screen
 * */

import server.ServerInterface;
import server.database.registration.User;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;


@WebServlet(name= "/register")
    public class RegisterServlet extends HttpServlet {

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

        protected void doPost(HttpServletRequest request, HttpServletResponse response)
                throws ServletException, IOException {
            // if we have post action, then go to register method
            try {
                register(request, response);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }

        protected void doGet(HttpServletRequest request, HttpServletResponse response)
                throws ServletException, IOException {
            // if user is not logged in, then we go to register screen
            if (request.getSession().getAttribute("user_logged") == null) {
                request.getRequestDispatcher("/register.jsp").forward(request, response);
                request.getSession().setAttribute("error", null);
            }
            // if user didn't choose room -> room screen
            else if (request.getSession().getAttribute("room") == null){
                response.sendRedirect("/todoapp/room");
            }
            // else todolist screen
            else
                response.sendRedirect("/todoapp/todolist");

        }

        private void register(HttpServletRequest request, HttpServletResponse response)
                throws IOException, ClassNotFoundException {

            String firstName = request.getParameter("firstName");
            String lastName = request.getParameter("lastName");
            String username = request.getParameter("username");
            String password = request.getParameter("password");

            int result = 0;

            // subscribing user on the server
            try {
                result = serverInterface.subscribeUserDatabase(firstName, lastName, username, password);
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

            if (result == 0){
                request.getSession().setAttribute("error", "Username ist schon belegt");
                response.sendRedirect("/todoapp/register");
            } else {
                response.sendRedirect("/todoapp/userlogin");
            }
        }
    }
