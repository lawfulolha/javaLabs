package test;

        import DAO.GameDAO;
        import database.ConnectionManager;
        import main.Game;
        import main.GameCharacter;
        import org.testng.annotations.*;

        import java.sql.Connection;
        import java.sql.PreparedStatement;
        import java.sql.ResultSet;
        import java.sql.SQLException;

        import static org.testng.AssertJUnit.assertEquals;
        import static org.testng.AssertJUnit.assertTrue;


public class GameDAOTest {

    private Connection connection;
    private Game game;
    private Game gameForUpdate;
    private GameDAO gameDAO;

    @BeforeTest
    public void before() throws SQLException {
        connection = ConnectionManager.getConnection();
        gameDAO = new GameDAO(connection);
        GameCharacter good = GameCharacter.newBuilder().setId(69).setName("the good")
                .setHealth(50).setArmor(50).setDamage(50).setShootingRange(50)
                .setPosition(10,10).build();
        GameCharacter bad = GameCharacter.newBuilder().setId(48).setName("the bad")
                .setHealth(50).setArmor(50).setDamage(50).setShootingRange(50)
                .setPosition(30,10).build();
         int gameId = (int) Math.random()*5;
        game =  new Game(gameId,"new game", new GameCharacter[]{good,bad});
        gameForUpdate = new Game(gameId,"new game", new GameCharacter[]{good,bad});
    }



    @Test
    public void createTest() {
        assertTrue(gameDAO.create(game));
    }

    @Test
    public void readTest() {
        assertEquals(gameDAO.read(1), game);
    }

    @Test
    public void resultSetToObjTest() throws SQLException {
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM games  WHERE id=1");
        ResultSet resultSet = statement.executeQuery();
        assertEquals(gameDAO.resultSetToObj(resultSet), game);
    }

    @Test
    public void updateTest() {
        assertTrue(gameDAO.update(gameForUpdate));
    }

    @AfterTest
    public void after() throws SQLException {
        connection.close();
    }

    @AfterClass
    public void deleteTest() {
        assertTrue(gameDAO.delete(game));
    }

}