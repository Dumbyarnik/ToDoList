package client;

import broadcast.BroadcastInterface;
import server.ServerInterface;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.Scanner;

import static java.lang.Integer.parseInt;

public class RunClient {

    // for user input
    static Scanner scanner;
    public static String username = "";

    public static void main(String[] args) {

        Registry registry = null;
        ServerInterface serverInterface;
        BroadcastInterface broadcastInterface = null;

        try {
            registry = LocateRegistry.getRegistry();
            serverInterface = (ServerInterface) registry.lookup("ChatServer");
            System.out.println("Server found");

            scanner = new Scanner(System.in);

            // 1 - subscribe
            // 2 - unsubscribe
            // 3 - send message
            // 4 - quit

            while (true) {
                System.out.println("1 - Subscribe\n" +
                        "2 - Unsubscribe\n" +
                        "3 - Send Message\n" +
                        "5 - Show To Do\n" +
                        "6 - Create To Do\n" +
                        "7 - Delete To Do\n" +
                        "8 - Update To Do\n" +
                        "4 - Exit");
                int action = scanner.hasNextInt() ? scanner.nextInt() : 0;
                scanner.nextLine();
                switch (action) {
                    case 1 -> {
                        if (!username.isEmpty()) {
                            System.out.println("You are already subscribed!");
                            break;
                        }
                        Client client = new Client();
                        ClientInterface handle = (ClientInterface) UnicastRemoteObject.exportObject(client, 0);
                        System.out.print("Username: ");
                        username = scanner.nextLine();
                        broadcastInterface = serverInterface.subscribeUser(username, handle);
                        if (broadcastInterface == null) {
                            username = "";
                            System.err.println("Error while subscribing.");
                        }
                        System.out.println("Subscribed!");
                    }
                    case 2 -> {
                        if (username.isEmpty()) {
                            System.out.println("You are already unsubscribed!");
                            break;
                        }
                        if (serverInterface.unsubscribeUser(username)) {
                            System.out.println("Unsubscribed");
                            username = "";
                        } else System.err.println("Error while unsubscribing.");
                    }
                    case 3 -> {
                        if (!username.isEmpty()) {
                            System.out.print("Message: ");
                            assert broadcastInterface != null;
                            broadcastInterface.sendMessage(scanner.nextLine());
                            break;
                        }
                        System.err.println("You aren't subscribed yet.");
                    }
                    case 4 -> {
                        if (!username.isEmpty()) {
                            if (serverInterface.unsubscribeUser(username)) {
                                System.out.println("Unsubscribed!");
                                username = "";
                            } else System.err.println("Error while unsubscribing.");
                        }
                        System.exit(0);
                    }
                    // show To Do
                    case 5 -> {
                        if (!username.isEmpty()) {
                            assert broadcastInterface != null;
                            broadcastInterface.broadcastTodo();
                            break;
                        }
                        System.err.println("You aren't subscribed yet.");
                    }
                    // add to do
                    case 6 -> {
                        if (!username.isEmpty()) {
                            if (serverInterface.addTodo(scanner.nextLine())) {
                                assert broadcastInterface != null;
                                broadcastInterface.broadcastTodo();
                                break;
                            } else System.err.println("This To Do exists");
                        }
                    }
                    // delete to do
                    case 7 -> {
                        if (!username.isEmpty()) {
                            if (serverInterface.deleteTodo(parseInt(scanner.nextLine()))) {
                                assert broadcastInterface != null;
                                broadcastInterface.broadcastTodo();
                                break;
                            } else System.err.println("This To Do doesnt exist");
                        }
                    }
                    case 8 -> {
                        if (!username.isEmpty()) {
                            System.out.print("update ToDo: ");
                            int tmp_int = parseInt(scanner.nextLine());
                            String string_tmp = scanner.nextLine();
                            boolean tmp = serverInterface.updateTodo(tmp_int, string_tmp);

                            if (tmp){
                                assert broadcastInterface != null;
                                broadcastInterface.broadcastTodo();
                                break;
                            }

                        }
                        System.err.println("This To Do doesnt exist");
                    }

                    default -> System.out.println("Input invalid.");
                }
            }
        } catch (Exception exception) {
            System.out.println("Server doesn't exist");
            System.exit(-1);
        }
    }

}
