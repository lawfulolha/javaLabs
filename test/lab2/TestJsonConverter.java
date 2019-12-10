package test;
/*
import com.fasterxml.jackson.core.JsonProcessingException;
import exception.ConvertException;
import main.GameCharacter;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import service.JsonConverter;

import java.io.File;
import java.io.IOException;

public class TestJsonConverter {

    private JsonConverter<GameCharacter> playerJsonConverter;
    private GameCharacter player;

    @BeforeTest
    public void beforeTest() {
        playerJsonConverter = new JsonConverter<>(GameCharacter.class);
       GameCharacter player = GameCharacter.newBuilder()
                .setName("The Player")
                .setHealth(69).setArmor(69).setDamage(69).setShootingRange(69)
               .setPosition(10,10)
                .build();
    }

    @Test
    public void serializeToStringTest() throws JsonProcessingException {
        String expected = "{\"name\",\"health\",\"armor\"}";
        String actual = playerJsonConverter.serializeToString(player);
        Assert.assertEquals(actual, expected);
    }

    @Test
    public void deserializeFromStringTest() throws IOException {
        String s = "{\"name\":\"The Player\",\"health\":\"69\",\"armor\":\"69\",\"damage\":\"69\",\"shootingRange\":\"69\",\"position.x\":\"10\",\"position.y\":\"10}";
        Assert.assertEquals( playerJsonConverter.deserializeFromString(s), player);
    }

    @Test(dataProvider = "fileProvider")
    public void fromFileTest(File file) throws ConvertException {
        Assert.assertEquals(playerJsonConverter.fromFile(file), player);
    }

    @DataProvider
    public Object[][] fileProvider() throws ConvertException {
        File f = new File("result.json");
        playerJsonConverter.toFile(player, f);
        return new Object[][]{{f}};
    }

}
*/

import com.fasterxml.jackson.core.JsonProcessingException;
import exception.ConvertException;
import main.GameCharacter;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import service.JsonConverter;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class TestJsonConverter {

    private JsonConverter playerJsonConverter = new JsonConverter<>(GameCharacter.class);
    private GameCharacter player;

    @BeforeTest
    public void beforeTest() {
        ArrayList<GameCharacter> arrayPlayers = new ArrayList<>();
        player = GameCharacter.newBuilder()
                .setName("The Player")
                .setHealth(69).setArmor(69).setDamage(69).setShootingRange(69)
                .setPosition(10,10)
                .build();
        arrayPlayers.add(player);

    }

    @Test
    public void serializeToStringTest() throws JsonProcessingException {
        String expected = "{\"name\":\"The Player\",\"health\":69,\"armor\":69,\"damage\":69,\"shootingRange\":69,\"x\":10,\"y\":10}";
        String actual = playerJsonConverter.serializeToString(player);
        Assert.assertEquals(actual, expected);
    }

    @Test
    public void deserializeFromStringTest() throws IOException {
        String expected = "{\"name\":\"The Player\",\"health\":69,\"armor\":69,\"damage\":69,\"shootingRange\":69}";
        Assert.assertEquals(player, playerJsonConverter.deserializeFromString(expected));
    }

    @Test(dataProvider = "fileProvider")
    public void fromFileTest(File file) throws ConvertException {
        Assert.assertEquals(player,playerJsonConverter.fromFile(file));
    }

    @DataProvider
    public Object[][] fileProvider() throws ConvertException {
        File f = new File("result.json");
        playerJsonConverter.toFile(player, f);
        return new Object[][]{{f}};
    }

}