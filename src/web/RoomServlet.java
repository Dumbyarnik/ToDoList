package web;
/*
 * Class created on 05.08.2021
 * Class is used to control room screen
 * */

import client.Client;
import client.ClientInterface;
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
        // if user was logged in
        if (validate(request, response)) {
            // if user didn't choose a room
            if (request.getSession().getAttribute("room") == null){
                request.getRequestDispatcher("jsp/room.jsp").forward(request, response);
            }
            // if the room was already chosen
            else {
                // if the button change a room was pressed
                if (request.getParameter("action") != null) {
                    if (request.getParameter("action").equals("changeRoom")){
                        this.changeRoom(request, response);
                    }
                }
                else {
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

        // Creating new client for the session and registering it by server
        Client client = new Client();
        try {
            client.startClient();
        } catch (NotBoundException e) {
            e.printStackTrace();
        }
        client.setRoom(room);
        client.setUsername(request.getSession().getAttribute("user_logged").toString());
        serverInterface.subscribeClient(client);
        request.getSession().setAttribute("client", client);

        // going to todolist page
        response.sendRedirect("/todoapp/todolist");
    }
}
