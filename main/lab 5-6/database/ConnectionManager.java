package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionManager {

    private Properties properties = new Properties();

    private Connection makeConnection() throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/gameCharacterDB",
                "postgres", "1");

        return connection;
    }

    public static Connection getConnection() throws SQLException {
        return new ConnectionManager().makeConnection();
    }
}