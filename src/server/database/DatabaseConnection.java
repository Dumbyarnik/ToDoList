package server.database;


import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.SQLException;


import java.sql.DriverManager;
import java.sql.PreparedStatement;


public class DatabaseConnection {

    private static String CONN = "jdbc:mysql://localhost:3306/TODO"; // hier bitte link zur Datenbank eingeben

    // Logins
    static Connection connection=null;
    static final String USER = "root"; // hier User
    static final String PASS = "root"; // hier Passwort

    public static void main(String[] args) throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {


        String url = "jdbc:mysql://localhost:3306/TODO?characterEncoding=latin1&useConfigs=maxPerformance";
        Class.forName ("com.mysql.jdbc.Driver").getDeclaredConstructor().newInstance();


        connection=DriverManager.getConnection(url,USER,PASS);
        /*
        PreparedStatement ps = connection.prepareStatement("INSERT INTO ToDos (ToDoName)\n" +
                "VALUES ('hello');");

        int check=ps.executeUpdate();

        if(check==1) {
            System.out.println("It works");
        }
*/
        add("cool");
      update(2,"MrUpdate");
        delete(1);

    }


        public static void update(int ID,String newName) {


            try {
                String query1 =
                 "UPDATE ToDos\n" +
                         "SET ToDoName = '"+ newName +"'" +
                         "WHERE ToDoID=" +"'"+ID+"';";

                PreparedStatement ps2 = connection.prepareStatement(query1);
                int check=ps2.executeUpdate();
                if(check==1) {
                System.out.println("Great Sucess! ");
                }

            } catch (SQLException se) {
                System.out.println(se);
            }
        }

    public static void delete(int ID) {
        try {
            String query1 =
                    "DELETE FROM ToDos WHERE ToDoID=+"+ ID+"";

            PreparedStatement ps2 = connection.prepareStatement(query1);
            int check=ps2.executeUpdate();
            if(check==1) {
                System.out.println("Great Sucess Delete! ");
            }

        } catch (SQLException se) {
            System.out.println(se);
        }
    }

    public static void add(String Name) {


        try {
            String query1 = "INSERT INTO ToDos (ToDoName)\n" +
                    "VALUES ('"+Name+"');";

            PreparedStatement ps2 = connection.prepareStatement(query1);
            int check=ps2.executeUpdate();
            if(check==1) {
                System.out.println("Great Sucess Add! ");
            }

        } catch (SQLException se) {
            System.out.println(se);
        }
    }


    public static Connection getConnection() throws ClassNotFoundException, SQLException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {

        String url = "jdbc:mysql://localhost:3306/ToDo?characterEncoding=latin1&useConfigs=maxPerformance";
        Class.forName ("com.mysql.jdbc.Driver").getDeclaredConstructor().newInstance();


        connection=DriverManager.getConnection(url,USER,PASS);
        return connection;


    }
}

