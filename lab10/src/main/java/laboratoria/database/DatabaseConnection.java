package laboratoria.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    private Connection connection;

    public void connect(String dbFile) throws SQLException {
        connection = DriverManager.getConnection("jdbc:sqlite:" + dbFile);
        System.out.println("Connected to the database: " + dbFile);
    }

    public void disconnect() throws SQLException {
        connection.close();
        System.out.println("Disconnected from the database.");
    }

    public Connection getConnection() {
        return connection;
    }
}
