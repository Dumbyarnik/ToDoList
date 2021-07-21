package client;

import java.rmi.RemoteException;
import java.util.ArrayList;

public class Client implements ClientInterface {

    @Override
    public void receiveNewData(String username, String message) throws RemoteException {
        System.out.println(username + ": " + message);
    }

    // Todo Functionality
    public void receiveTodo(ArrayList<String> todo) throws RemoteException {
        int i = 0;

        for (String s : todo){
            System.out.println(i + ". " + s);
            i++;
        }


    }

}
