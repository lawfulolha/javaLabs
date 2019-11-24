package test;
import main.Position;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
public class PositionTest extends Assert{

    @DataProvider(name="PositionTestData")
    public Object[][] posDataProviderMethod(){
        return new Object[][]{{new Position(-1,-1),new Position(1,1)},
                {new Position(1000,1000),new Position(800,400)},
                { new Position(20,40),new Position(20,40)}};
    }

    @Test(dataProvider="PositionTestData")
    public void setPositionTest(Position position,Position expected){
        Position pos = new Position();
        pos.setPosition(position.getX(),position.getY());
        Assert.assertEquals(pos, expected);
    }
}
