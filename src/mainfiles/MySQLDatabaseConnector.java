// MySQLDatabaseConnector.java
package MainFiles;

import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQLDatabaseConnector extends DatabaseConnector {

    @Override
    protected void connectDetails() throws ClassNotFoundException, SQLException {
        // Load MySQL JDBC driver
        Class.forName("com.mysql.cj.jdbc.Driver");
        
        // Establish the connection
        con = DriverManager.getConnection("jdbc:mysql://localhost/rtis", "root", "mickay20050304");
    }
}
