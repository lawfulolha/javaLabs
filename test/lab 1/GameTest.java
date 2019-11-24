package test;
import main.Game;
import main.GameCharacter;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;


public class GameTest {
    private GameCharacter good;
    private GameCharacter bad;
    private GameCharacter ugly;
    private Game newGame;




        @BeforeTest
        public void createGame(){
            //Game newGame=new Game();

                good = GameCharacter.newBuilder()
                        .setName("The Good")
                        .setHealth(100).setArmor(50).setDamage(50).setShootingRange(40)
                        .build();
                bad = GameCharacter.newBuilder()
                        .setName("The Bad")
                        .setHealth(10).setArmor(1).setDamage(5).setShootingRange(10)
                        .build();

                ugly = GameCharacter.newBuilder()
                        .setName("The Ugly")
                        .setHealth(10).setArmor(1).setDamage(6).setShootingRange(10)
                        .build();

            newGame = new Game();

        }


        @Test(expectedExceptions = IllegalArgumentException.class)
        public void addPlayerMethodTest () {
            newGame.addPlayer(good);
            Game expGame = new Game();
            expGame = newGame;
            newGame.addPlayer(GameCharacter.newBuilder().setName("The Good").setHealth(100).setArmor(100).setDamage(3).setShootingRange(19).build());
            assertEquals(newGame, expGame);
        }
        @Test
        public void deletePlayerTest () {
            newGame.deletePlayer(good);
            assertEquals(newGame, new Game());
        }

        @Test
        public void getWinnerTest ()
         {
            newGame.addPlayer(good);
            newGame.addPlayer(bad);
            newGame.addPlayer(ugly);
            assertEquals(newGame.getWinner().getName(), good.getName());
        }
    }
