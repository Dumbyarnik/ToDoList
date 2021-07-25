package web;

import server.ServerInterface;
import server.database.todolist.Todo;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.sql.SQLException;
import java.util.ArrayList;

@WebServlet(name = "/todolist")
public class TodoServlet extends CommonServlet {

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

            // if delete button was clicked
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
            // the edit button was pressed
            else if (request.getParameter("edit") != null) {
                // getting unique id parameter
                String id_tmp = request.getParameter("edit_id");
                int id = Integer.parseInt(id_tmp);
                Todo todo = new Todo();
                // Getting todo to edit
                try {
                    todo = serverInterface.getOneTodo(id);
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
                request.getSession().setAttribute("todo", todo);
                response.sendRedirect("/todoapp/newtodo");
            }
            // if the button add was clicked
            else if (request.getParameter("add") != null) {
                response.sendRedirect("/todoapp/newtodo");
            }
            else {
                doGet(request, response);
            }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        if (validate(request, response)){
            try {
                listTodo(request, response);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }


    private void listTodo(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ClassNotFoundException, ServletException {
        // Setting list to the attribute in jsp
        ArrayList<Todo> todos = serverInterface.getTodoDatabase();
        request.setAttribute("todoList", todos);
        // refreshing every 5 seconds
        response.setIntHeader("Refresh", 3);
        request.getRequestDispatcher("/jsp/todoList.jsp").forward(request, response);
    }

    private void deleteTodo(HttpServletRequest request, HttpServletResponse response, int id)
            throws SQLException, IOException, ClassNotFoundException, ServletException {
        // deleteing the id
        serverInterface.deleteTodoDatabase(id);
        this.doGet(request, response);
    }

}