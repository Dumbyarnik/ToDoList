package web;

import server.ServerInterface;
import server.database.login.Login;

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


@WebServlet("/login")
public class LoginServlet extends HttpServlet {
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
        request.getRequestDispatcher("/login.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        authenticate(request, response);
    }

    private void authenticate(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        Login loginBean = new Login();
        loginBean.setUsername(username);
        loginBean.setPassword(password);

        // login user on the server
        int result = 0;
        try {
            result = serverInterface.loginUser(username, password);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        if (result == 1) {
            request.getSession().setAttribute("user_logged", username);
            request.setAttribute("next", "next");
            request.getRequestDispatcher("todolist").forward(request, response);
        }

    }
}
