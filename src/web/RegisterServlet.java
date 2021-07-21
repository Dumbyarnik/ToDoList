package web;

import client.Client;
import client.ClientInterface;
import server.ServerInterface;
import server.database.UserDAO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.rmi.NotBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;


@WebServlet(name= "/register")
    public class RegisterServlet extends HttpServlet {

        private static final long serialVersionUID = 1L;

        // Server side
        Registry registry = null;
        ServerInterface serverInterface;


        private UserDAO userDao;

        public void init() {

            try {
                registry = LocateRegistry.getRegistry();
                serverInterface = (ServerInterface) registry.lookup("ChatServer");
            } catch (NotBoundException | RemoteException ex) {
                ex.printStackTrace();
            }

            userDao = new UserDAO();
        }

        protected void doPost(HttpServletRequest request, HttpServletResponse response)
                throws ServletException, IOException {
            register(request, response);

        }

        protected void doGet(HttpServletRequest request, HttpServletResponse response)
                throws ServletException, IOException {
            response.sendRedirect("register.jsp");

        }

        private void register(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

            // Experimental server code
            String firstName = request.getParameter("firstName");
            String lastName = request.getParameter("lastName");
            String username = request.getParameter("username");
            String password = request.getParameter("password");

            Client client = new Client();
            ClientInterface handle = (ClientInterface) UnicastRemoteObject.exportObject((Remote) client, 0);

            serverInterface.subscribeUser(username, handle);


            // Dennis Database Code
            /*String firstName = request.getParameter("firstName");
            String lastName = request.getParameter("lastName");
            String username = request.getParameter("username");
            String password = request.getParameter("password");

            User employee = new User();
            employee.setFirstName(firstName);
            employee.setLastName(lastName);
            employee.setUserName(username);
            employee.setPassword(password);

            try {
                int result = userDao.registerUser(employee);
                if (result == 1) {
                    request.setAttribute("NOTIFICATION", "User Registered Successfully!");
                }

            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }*/

            RequestDispatcher dispatcher = request.getRequestDispatcher("register.jsp");
            dispatcher.forward(request, response);
        }
    }
