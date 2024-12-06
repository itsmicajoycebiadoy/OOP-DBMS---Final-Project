// AbstractDatabaseConnector.java
package MainFiles;

import java.sql.Connection;
import java.sql.SQLException;

public abstract class DatabaseConnector {
    
    protected Connection con;

    // Abstract method to connect to the database
    public void connect() {
        try {
            connectDetails();
            System.out.println("Database connected successfully!");
        } catch (ClassNotFoundException | SQLException e) {
            handleConnectionError(e);
        }
    }

    // Abstract method to be implemented by the subclass to handle the specific connection details
    protected abstract void connectDetails() throws ClassNotFoundException, SQLException;
    
    // Method to handle errors
    private void handleConnectionError(Exception e) {
        e.printStackTrace();
        System.out.println("Database connection failed!");
    }

    // Getter method for connection
    public Connection getConnection() {
        return con;
    }
}
