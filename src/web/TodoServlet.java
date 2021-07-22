package web;

import server.ServerInterface;
import server.database.todolist.Todo;

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
import java.sql.SQLException;
import java.util.ArrayList;

import static java.lang.Integer.parseInt;

@WebServlet(name = "/todoList")
public class TodoServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    // Server side
    Registry registry = null;
    ServerInterface serverInterface;

    @Override
    public void init() {
        // initializing server
        try {
            registry = LocateRegistry.getRegistry();
            serverInterface = (ServerInterface) registry.lookup("ChatServer");
        } catch (NotBoundException | RemoteException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // if delete button was klicked
        if (request.getParameter("delete") != null) {
            // getting unique id parameter
            String id_tmp = request.getParameter("delete_id");
            int id = Integer.parseInt(id_tmp);
            try {
                deleteTodo(request, response, id);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getServletPath();

        try {
            switch (action) {

                default:
                    listTodo(request, response);
                    break;
            }
        } catch (ClassNotFoundException ex) {
            throw new ServletException(ex);
        }
    }


    private void listTodo(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ClassNotFoundException, ServletException {
        // Setting list to the attribute in jsp
        ArrayList<Todo> todos = serverInterface.getTodoDatabase();
        request.setAttribute("todoList", todos);

        RequestDispatcher dispatcher = request.getRequestDispatcher("todoList.jsp");
        dispatcher.forward(request, response);
    }

    private void deleteTodo(HttpServletRequest request, HttpServletResponse response, int id)
            throws SQLException, IOException, ClassNotFoundException, ServletException {
        // deleteing the id
        serverInterface.deleteTodoDatabase(id);

        RequestDispatcher dispatcher = request.getRequestDispatcher("todoList.jsp");
        dispatcher.forward(request, response);
    }

}