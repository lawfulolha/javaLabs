package test;

import main.GameCharacter;
import org.testng.Assert;
import org.testng.annotations.Test;

public class GameCharacterTest extends Assert{
    private GameCharacter good;
    private GameCharacter bad;
    private GameCharacter ugly;

    @Test
    public void builderTest() {
        GameCharacter good = GameCharacter.newBuilder()
                .setName("The Good")
                .setHealth(100).setArmor(50).setDamage(40).setShootingRange(40)
                .build();
    }
    @Test(expectedExceptions = IllegalArgumentException.class)
    public void builderIllegalArgTest() {
        GameCharacter bad = GameCharacter.newBuilder()
                .setName("The Bad")
                .setHealth(300).setArmor(340).setDamage(30).setShootingRange(-10)
                .build();
    }
    @Test(expectedExceptions = IllegalArgumentException.class)
    public void builderEmptyArgTest() {
        GameCharacter ugly = GameCharacter.newBuilder()
                .setName("The Ugly")
                .build();
    }

}
