package web;

import server.ServerInterface;
import server.database.registration.User;

import javax.servlet.RequestDispatcher;
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
            try {
                register(request, response);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

        }

        protected void doGet(HttpServletRequest request, HttpServletResponse response)
                throws ServletException, IOException {
            response.sendRedirect("register.jsp");

        }

        private void register(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, ClassNotFoundException {

            String firstName = request.getParameter("firstName");
            String lastName = request.getParameter("lastName");
            String username = request.getParameter("username");
            String password = request.getParameter("password");

            User user = new User();
            user.setFirstName(firstName);
            user.setLastName(lastName);
            user.setUserName(username);
            user.setPassword(password);

            // subscribing user on the server
            try {
                int result = serverInterface.subscribeUserDatabase(firstName, lastName, username, password);
                if (result == 1) {
                    request.setAttribute("NOTIFICATION", "User Registered Successfully!");
                }
                if (result==0){
                    request.setAttribute("NOTIFICATION", "Username ist schon belegt");
                }

            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

            RequestDispatcher dispatcher = request.getRequestDispatcher("register.jsp");
            dispatcher.forward(request, response);
        }
    }
