package test;

import exception.ConvertException;
import main.GameCharacter;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import service.TextConverter;

public class TestTextConverter {
    private GameCharacter player;

    @BeforeTest
    public void createMedicine() {
        player = GameCharacter.newBuilder()
                .setName("The Player")
                .setHealth(69).setArmor(69).setDamage(69).setShootingRange(69)
                .setPosition(10,10)
                .build();
    }

    @Test
    public void serializeToStringTest() throws ConvertException {
        String expected = "The Player---69---69---69---69---10---10";
        String playerstr = new TextConverter().serializeToString(player);
        Assert.assertEquals(expected, playerstr);
    }

    @Test
    public void deserializeFromStringTest() throws ConvertException {
        String string = "The Player---69---69---69---69---10---10";
        Assert.assertEquals(player, new TextConverter().deserializeFromString(string));
    }
}