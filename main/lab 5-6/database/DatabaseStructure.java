package database;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class DatabaseStructure {
    private static final String CREATE_PLAYERS = "DROP TABLE IF EXISTS players CASCADE; CREATE TABLE players (id INT PRIMARY KEY, name VARCHAR(20), health INT, armor INT, damage INT, shooting_range INT, x INT, y INT, game_id INT, FOREIGN KEY (game_id) REFERENCES games(id));";
    private static final String DROP_PLAYERS = "DROP TABLE players";

    private static final String CREATE_GAMES = "DROP TABLE IF EXISTS games CASCADE; CREATE TABLE games (id SERIAL NOT NULL PRIMARY KEY, name VARCHAR(20));";
    private static final String DROP_GAMES = "DROP TABLE games CASCADE";

    /**
     * Private method to crete table by string
     *
     * @param sqlString to create
     */
    private static void createTable(String sqlString) throws Exception {
        try (Connection connection = ConnectionManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(sqlString)) {
            statement.executeUpdate();
        }
    }

    /**
     * Private method to delete table by string
     *
     * @param sqlString to delete
     */
    private static void dropTable(String sqlString) throws Exception {
        try (Connection connection = ConnectionManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(sqlString)) {
            statement.execute();
        }
    }

    /**
     * Creating table game characters in gameCharDB
     *
     * @throws Exception
     */

    /**
     * Creating products table
     *
     * @throws Exception
     */
    public static void createGamesTable() throws Exception {
        createTable(CREATE_GAMES);
    }
    public static void createGameCharTable() throws Exception {
        createTable(CREATE_PLAYERS);
    }

    /**
     * Dropping table GameCharacter in GameCharacter DB
     *
     * @throws Exception if  wrong
     */
    public static void dropGameCharTable() throws Exception {
        dropTable(DROP_PLAYERS);
    }

    /**
     * Dropping products table
     *
     * @throws Exception
     */
    public static void dropGamesTable() throws Exception {
        dropTable(DROP_GAMES);
    }


}