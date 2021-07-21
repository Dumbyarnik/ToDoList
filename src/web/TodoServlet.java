package web;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

import static java.lang.Integer.parseInt;

@WebServlet(name = "/todo")
public class TodoServlet extends HttpServlet {

    public void init() {
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        ArrayList<String> listTodo = new ArrayList<>();
        listTodo.add("chekushka");
        listTodo.add("polushka");
        request.setAttribute("listTodo", listTodo);

        String UsernameLabel="passing value";
        request.setAttribute("mes_add_pageTitle",UsernameLabel);

        RequestDispatcher dispatcher = request.getRequestDispatcher("todo.jsp");
        dispatcher.forward(request, response);
    }

}