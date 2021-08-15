package web;
/*
 * Class created on 23.08.2021
 * Class is used to control new todo & edit screen
 * */

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

@WebServlet(name= "/newtodo")
    public class NewTodoServlet extends CommonServlet {

        private static final long serialVersionUID = 1L;
        // Server side
        Registry registry = null;
        ServerInterface serverInterface;

        String old_item = "";

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
            // if the button edit was clicked on todos screen
            if (request.getParameter("edit") != null) {
                try {
                    updateTodo(request, response);
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
            // if button add was clicked on todos screen
            else if (request.getParameter("new") != null) {
                try {
                    addTodo(request, response);
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
            else {
                doGet(request, response);
            }

        }

        protected void doGet(HttpServletRequest request, HttpServletResponse response)
                throws ServletException, IOException {
            // if user logged in
            if (validate(request, response)) {
                // if user chose a room
                if (validateRoom(request, response)) {
                    request.getRequestDispatcher("/jsp/newTodo.jsp").forward(request, response);

                    // getting the old name of the item for the updates
                    if (request.getSession().getAttribute("todo") != null){
                        Todo todo = (Todo)request.getSession().getAttribute("todo");
                        old_item = todo.getItem();
                    }

                    request.getSession().setAttribute("todo", null);
                }
            }

        }

        private void addTodo(HttpServletRequest request, HttpServletResponse response) throws IOException, ClassNotFoundException {
            String item = request.getParameter("itemName");
            String status = request.getParameter("status");
            String date = request.getParameter("date");
            String room = request.getSession().getAttribute("room").toString();

            // adding todo on the server
            try {
                serverInterface.addTodoDatabase(item, status, date, room,
                        request.getSession().getAttribute("user_logged").toString());
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            response.sendRedirect("/todoapp/todolist");
        }

        private void updateTodo(HttpServletRequest request, HttpServletResponse response) throws IOException, ClassNotFoundException {

            String id_tmp = request.getParameter("edit_id");
            int id = Integer.parseInt(id_tmp);
            String item = request.getParameter("itemName");
            String status = request.getParameter("status");
            String date = request.getParameter("date");

            // editing todo on the server
            try {
                serverInterface.updateTodoDatabase(id, item, status, date,
                        request.getSession().getAttribute("room").toString(),
                        request.getSession().getAttribute("user_logged").toString(),
                        old_item);
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            response.sendRedirect("/todoapp/todolist");
        }
    }
