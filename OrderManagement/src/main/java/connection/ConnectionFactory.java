package connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Factory class responsible for creating and testing the connection to the database.
 * <p>
 * Provides a method to establish a connection to the database used for the Order Management system.
 */
public class ConnectionFactory {
    /**
     * Establishes and returns a connection to the database.
     *
     * @return a Connection object if the connection is successful. Otherwise, returns null.
     */
    public static Connection getConnection() {
        try {
            Class.forName("org.postgresql.Driver");

            String url = "jdbc:postgresql://localhost:5432/order_management";
            String user = "postgres";
            String password = "D3n1s404";

            Connection con = DriverManager.getConnection(url, user, password);
            return con;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Main method used to test the database connection.
     * Displays "Connection successful!" if the connection is successful, "Connection failed." otherwise.
     *
     * @param args are the command line arguments.
     */
    public static void main(String[] args) {
        Connection con = getConnection();
        if (con != null) {
            System.out.println("Connection successful!");
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Connection failed.");
        }
    }
}