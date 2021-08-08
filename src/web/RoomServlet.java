package web;

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

@WebServlet(name = "/room")
public class RoomServlet extends CommonServlet {
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
        if (validate(request, response)) {
            if (request.getSession().getAttribute("room") == null){
                request.getRequestDispatcher("jsp/room.jsp").forward(request, response);
            } else {
                if (request.getParameter("action").equals("changeRoom")) {
                    this.changeRoom(request, response);
                } else {
                    response.sendRedirect("/todoapp/todolist");
                }
            }
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // setting parameter room and go to todo
        String room = request.getParameter("room");
        request.getSession().setAttribute("room", room);
        response.sendRedirect("/todoapp/todolist");
    }
}
