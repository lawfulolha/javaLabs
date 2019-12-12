package test;

import database.DatabaseStructure;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class TestDatabaseStructure {

    @BeforeTest
    public void createGameTableTest() throws Exception {
        DatabaseStructure.createGamesTable();
    }
    @Test
    public void createGameCharTableTest() throws Exception {
        DatabaseStructure.createGameCharTable();
    }

    @AfterClass
    public void dropGameCharTableTest() throws Exception {
        DatabaseStructure.dropGameCharTable();
    }

    @AfterClass
    public void dropGameTableTest() throws Exception {
        DatabaseStructure.dropGamesTable();
    }

}