package test;

import main.Game;
import main.GameCharacter;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import service.GameService;

import java.util.*;

public class gameServiceTest {
    private GameService gameService;
    private Game game;
    private GameCharacter good;
    private GameCharacter bad;
    private GameCharacter ugly;
    private GameCharacter naruto;

    {
        good = GameCharacter.newBuilder()
                .setName("The Good")
                .setHealth(100).setArmor(50).setDamage(50).setShootingRange(40)
                .build();
        bad = GameCharacter.newBuilder()
                .setName("The Bad")
                .setHealth(10).setArmor(1).setDamage(13).setShootingRange(10)
                .build();

        ugly = GameCharacter.newBuilder()
                .setName("The Ugly")
                .setHealth(10).setArmor(0).setDamage(6).setShootingRange(10)
                .build();

        ugly = GameCharacter.newBuilder()
                .setName("Naruto")
                .setHealth(100).setArmor(0).setDamage(100).setShootingRange(20)
                .build();

    }

    @BeforeMethod
    public void createGame() {
        game = new Game();
        game.addPlayer(good);
        game.addPlayer(ugly);
        game.addPlayer(bad);
    }

    @BeforeMethod
    public void createGameService() {
        gameService = new GameService(game);
    }

    @Test
    public void sortPlayersByNameTest(){

        SortedSet<GameCharacter> sortedPlayers = gameService.sortPlayersByName();
        SortedSet<GameCharacter> expectedSortedPlayers = new TreeSet<>();
        expectedSortedPlayers.add(bad);
        expectedSortedPlayers.add(good);
        expectedSortedPlayers.add(ugly);
        Assert.assertEquals(expectedSortedPlayers, sortedPlayers);

    }
    @Test
    public void sortPlayersByDamageTest() {

        Object[] sortedPlayers = gameService.sortPlayersByDamage().toArray();
        Object[] expectedSortedPlayers = {bad ,good, ugly };
        Assert.assertEquals(expectedSortedPlayers, sortedPlayers);
    }
    @Test
    public void countPlayersWDamageMoreThanTest() {

        long countPlayers = gameService.countPlayersWDamageMoreThan(49);
        long expectedCountPlayers = 2;
        Assert.assertEquals(expectedCountPlayers, countPlayers);
    }

    @Test
    public void ifExistsByNameTest() {
        boolean actual = gameService.ifExistsByName("The Bad");
        Assert.assertTrue(actual);
    }

    @Test
    public void removePlayersWithoutArmorTest() {

        gameService.removePlayersWithoutArmor();
        Object[] finalPlayers = game.getPlayers().toArray();
        Assert.assertEquals(finalPlayers, new Object[]{good,bad});
    }
}