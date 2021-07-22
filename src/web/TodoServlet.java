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
import java.util.ArrayList;

import static java.lang.Integer.parseInt;

@WebServlet(name = "/todo")
public class TodoServlet extends HttpServlet {

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
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // subscribing user on the server
        try {
            // Getting todo list from the server
            ArrayList<Todo> todos = serverInterface.getTodoDatabase();
            ArrayList<String> todosString = new ArrayList<>();

            for (Todo item : todos)
                todosString.add(item.getItem());
            // Setting list to the attribute in jsp
            request.setAttribute("todoList", todosString);

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher("todoList.jsp");
        dispatcher.forward(request, response);
    }

}