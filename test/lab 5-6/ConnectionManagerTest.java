package test;

import database.ConnectionManager;
import org.testng.annotations.Test;

import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionManagerTest {

    @Test
    public void getConnectionTest() throws SQLException {
        Connection connection = ConnectionManager.getConnection();
        connection.close();
    }
}