package test;


import DAO.GameCharDAO;
import DAO.GameDAO;
import database.ConnectionManager;
import database.DatabaseStructure;
import main.Game;
import main.GameCharacter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertTrue;

public class GameCharDAOTest {
    private Connection connection;
    private GameCharacter player;
    private GameCharacter playerForUpdate;
    private GameCharDAO gameCharDAO;
    private Game game; private GameDAO gameDAO;
//    @BeforeClass
//    public void createDB() throws Exception {
//        DataBaseStructure.createEmployeesTable();
//    }
//
//    @BeforeClass
//    public void dropDB() throws Exception {
//        DataBaseStructure.dropEmployeesTable();
//    }


    @BeforeTest
    public void before() throws Exception {
        connection = ConnectionManager.getConnection();

        DatabaseStructure.createGamesTable();
        DatabaseStructure.createGameCharTable();

        gameCharDAO = new GameCharDAO(connection);
        player = GameCharacter.newBuilder()
                .setId(23)
                .setName("John")
                .setHealth(33)
                .setArmor(40).setDamage(45).setShootingRange(45).setPosition(2,2)
                .build();
        playerForUpdate = GameCharacter.newBuilder()
                .setId(23)
                .setName("John")
                .setHealth(33)
                .setArmor(40).setDamage(45).setShootingRange(45).setPosition(2,2)
                .build();

    }

    @AfterTest
    public void after() throws SQLException {
        connection.close();
    }

    @Test
    public void createTest() throws Exception {
       gameDAO = new GameDAO(connection);
        GameCharacter good = GameCharacter.newBuilder().setId(69).setName("the good")
                .setHealth(50).setArmor(50).setDamage(50).setShootingRange(50)
                .setPosition(10,10).build();
        GameCharacter bad = GameCharacter.newBuilder().setId(48).setName("the bad")
                .setHealth(50).setArmor(50).setDamage(50).setShootingRange(50)
                .setPosition(30,10).build();
        game =  new Game(2,"new game", new GameCharacter[]{good,bad});

        gameDAO.create(game);
   //     DatabaseStructure.createGameCharTable();
        assertEquals(true, gameCharDAO.create(player));
    }

    @Test
    public void readTest() {
        assertEquals(gameCharDAO.read(23), player);
    }

    @Test
    public void resultSetToObjTest() throws SQLException {
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM players");
        ResultSet resultSet = statement.executeQuery();
        assertEquals(player , gameCharDAO.resultSetToObj(resultSet));
    }

    @Test
    public void updateTest() {
        assertEquals(gameCharDAO.update(playerForUpdate), true);
    }

    /*
    @AfterClass
    public void deleteTest() {
        gameDAO.delete(game);
        assertEquals(gameCharDAO.delete(player), true);
    }
*/
}