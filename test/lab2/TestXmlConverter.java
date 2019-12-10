package test;
/*

import com.fasterxml.jackson.core.JsonProcessingException;
import exception.ConvertException;
import main.GameCharacter;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import service.XmlConverter;

import java.io.File;
import java.io.IOException;

public class TestXmlConverter {
    private XmlConverter<GameCharacter> playerXmlConverter;
    private GameCharacter player;

    @BeforeTest
    public void beforeTest() {
        playerXmlConverter = new XmlConverter<> (GameCharacter.class);
        GameCharacter player = GameCharacter.newBuilder()
                .setName("The Player")
                .setHealth(69).setArmor(69).setDamage(69).setShootingRange(69)
                .setPosition(10, 10)
                .build();
    }

    @Test
    public void serializeToStringTest() throws JsonProcessingException {
        String s = "<GameCharacter><name>The Player</name><health>69</health><armor>69</armor><damage>69</damage><shootingRange>69</shootingRange><x>10</x><y>10</y></GameCharacter>";
        Assert.assertEquals( s , playerXmlConverter.serializeToString(player));
    }

    @Test
    public void deserializeFromStringTest() throws IOException {
        String s = "<GameCharacter><name>The Player</name><health>69</health><armor>69</armor><damage>69</damage><shootingRange>69</shootingRange><x>10</x><y>10</y></GameCharacter>";
        Assert.assertEquals(player, playerXmlConverter.deserializeFromString(s));
    }

    @Test(dataProvider = "fileProvider")
    public void fromFileTest(File file) throws ConvertException {
        Assert.assertEquals(playerXmlConverter.fromFile(file), player);
    }

    @DataProvider
    public Object[][] fileProvider() throws ConvertException {
        File f = new File("D:/LABS/Lab2Serialization/result.json");
        playerXmlConverter.toFile(player, f);
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
import service.XmlConverter;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class TestXmlConverter {
    //private XmlConverter<Players> playerXmlConverterXmlConverter;
    private GameCharacter player;

    private XmlConverter<GameCharacter> playerXmlConverter;

    @BeforeTest
    public void beforeTest() {
        playerXmlConverter = new XmlConverter<>(GameCharacter.class);
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
        String s =  "<GameCharacter><name>The Player</name><health>69</health><armor>69</armor><damage>69</damage><shootingRange>69</shootingRange><x>10</x><y>10</y></GameCharacter>";
        Assert.assertEquals( playerXmlConverter.serializeToString(player), s );
    }

    @Test
    public void deserializeFromStringTest() throws IOException {
        String s =  "<GameCharacter><name>The Player</name><health>69</health><armor>69</armor><damage>69</damage><shootingRange>69</shootingRange></GameCharacter>";
        Assert.assertEquals(player, playerXmlConverter.deserializeFromString(s));
    }

    @Test(dataProvider = "fileProvider")
    public void fromFileTest(File file) throws ConvertException {
        Assert.assertEquals(playerXmlConverter.fromFile(file), player);
    }

    @DataProvider
    public Object[][] fileProvider() throws ConvertException {
        File f = new File("D:\\result.json");
        playerXmlConverter.toFile(player, f);
        return new Object[][]{{f}};
    }
}