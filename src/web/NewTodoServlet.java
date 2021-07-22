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
import java.util.Date;


@WebServlet(name= "/newTodo")
    public class NewTodoServlet extends HttpServlet {

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
                addTodo(request, response);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }

        protected void doGet(HttpServletRequest request, HttpServletResponse response)
                throws ServletException, IOException {
            response.sendRedirect("newTodo.jsp");

        }

        private void addTodo(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, ClassNotFoundException {

            String item = request.getParameter("itemName");
            String status = request.getParameter("status");
            String date = request.getParameter("date");

            if (status == "1")
                status = "planned";
            else if (status == "2")
                status = "in progress";
            else if (status == "3")
                status = "complete";

            // adding todo on the server
            try {
                int result = serverInterface.addTodoDatabase(item, status, date);
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            
            response.sendRedirect("todoList");
        }
    }
