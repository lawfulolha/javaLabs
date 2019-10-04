package test;


import tasks.Variant13;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import static org.testng.Assert.assertEquals;
public class TestVariant13 {


    /////////
    @Test
    public void MassTest(){
        assertEquals(new int[]{1, 2, 3}, new int[]{1, 2, 3});
    }


    ///////////////////////////////////////////////
    @Test(dataProvider = "inputProvider")
    public void inputTest(double a, double b, double[] exp) {
        assertEquals(new Variant13().inputOutputTask(a,b),exp );
    }

    @DataProvider
    public Object[][] inputProvider(){

        return new Object[][]{{1,2,new double[]{3.141592653589793,12.566370614359172,9.42477796076938}},
                {2,3,new double[]{12.566370614359172,28.274333882308138,15.707963267948966}},
                {1,3,new double[]{3.141592653589793,28.274333882308138,25.132741228718345}}};

    }


    ///////////////////////////////////////////////
    @Test(dataProvider = "integerProvider")
    public void  integerNumberTest(int a, int expected){
        assertEquals( new Variant13().integerNumberTask(a), expected);
    }

    @DataProvider
    public Object[][] integerProvider() {

        return new Object[][] { { 100, 1 }, { 120, 201 }, { 139, 391 } };
    }


    //////////////////////////////////////////////////
    @Test(dataProvider = "booleanProvider")
    public void booleanTest(int a, int b, int c, boolean expected ){
        assertEquals(new Variant13().booleanTask(a,b,c),expected);
    }

    @DataProvider
    public Object[][] booleanProvider() {

        return new Object[][] {{1,-1,1,true}, {2,2,2,true}, {1,1,1,true}};
    }


    ///////////////////////////////////////////////////
    @Test(dataProvider = "ifProvider")
    public void ifTest (int a, int b , int c, int expected){
        assertEquals( new Variant13().ifTask(a,b,c), expected);
    }

    @DataProvider
    public Object[][] ifProvider(){
        return new Object[][] {{3,3,3,3}, { 2,1,3,2},{4,4,-1,-1}};
    }


    ////////////////////////////////////////////////////

    @Test(dataProvider = "switchProvider")
    public void switchTest(int n, double val, double[] exp) {
        assertEquals(new Variant13().switchTask(n,val), exp);
    }

    @DataProvider
    public Object[][] switchProvider() {
        return new Object[][] { { 3, 3, new double[] { 4.242640687119285, 6,3.0,9  }},
                { 2, 2, new double[] {1.414213562373095, 2 ,1 ,1  }},
                { 1, 3, new double[] { 3,4.242640687119286,2.121320343559643, 4.500000000000001  }} };
    }

    /////////////////////////////////////////////////////
    @Test(dataProvider = "forProvider")
    public void forTest(int n, double expected){
        assertEquals(new Variant13().forTask(n), expected);
    }

    @DataProvider
    public Object[][] forProvider(){
        return new Object[][] {{9,1.5},{8,-0.3999999999999999},{7,1.4000000000000001}};
    }


    /////////////////////////////////////////////////////////
    @Test(dataProvider = "whileProvider")
    public void whileTest(int a, int[] expected){
        assertEquals(new Variant13().whileTask(a), expected);
    }

    @DataProvider
    public Object[][] whileProvider() {
        return new Object[][]{{1, 3, 3}, {3, 6, 4}, {10, 15, 6}};
    }


    //////////////////////////////////////////////////////////

    @Test(dataProvider = "seriesTestProvider")
    public void seriesTest(int[] arr, int exp){
        assertEquals( new Variant13().seriesTask(arr),exp);
    }

    @DataProvider
    public Object[][] seriesTestProvider()
    {
        int[] arr1={1,-3,4, 0};
        int[] arr2={2,3,2,0};
        int[] arr3={1,2,-2,6, 0};
        return new Object[][] {{arr1, 4}, {arr2, 4}, {arr3, 8}};
    }


    ///////////////////////////////////////////////////////////
    @Test(dataProvider = "sortDecProvider")
    public void sortDecTest (float a, float b, float c, float[] exp)
    {
        assertEquals(new Variant13().SortDec3(a, b, c), exp );
    }

    @DataProvider
    public Object[][] sortDecProvider()
    {
        return new Object[][] {{1,2,3,new float[]{3,2,1}}, {1,5,3,new float[]{5,3,1}}, {3,4,3,new float[]{4,3,3}}};
    }


    ///////////////////////////////////////////////////////////////
    @Test(dataProvider = "digitCountProvider")
    public void digitCount(int a, int exp)
    {
        assertEquals(new Variant13().DigitCount(a),exp);
    }

    @DataProvider
    public Object[][] digitCountProvider()
    {
        return new Object[][]{{123,3}, {3455,4},{12,2}};
    }


    //////////////////////////////////////////////////////////////
    @Test(dataProvider = "minMaxProvider")
    public void minMaxTest(int n, int[] arr, int exp)
    {
        assertEquals(new Variant13().minMax(n,arr),exp);
    }

    @DataProvider
    public Object[][]  minMaxProvider()
    {
        int[] arr1={3,1,5};
        int[] arr2={2,4,2};
        int[] arr3={3,3,4};
        return new Object[][]{{3,arr1,2}, {3,arr2, 0},{3,arr3,0}};
    }


    /////////////////////////////////////////////////
    @Test(dataProvider = "arrayTestProvider")
    public void arrayTest(int n, double[] array, double[] exp) {
        assertEquals(new Variant13().arrayTask(n, array), exp);
    }

    @DataProvider
    public Object[][] arrayTestProvider() {
        return new Object[][] {
                {3, new double[] { 10, 2, 3 },  new double[] {3, 10}},
                {1, new double[] { 10},  new double[] {10}},
                {7, new double[] { 2,4, 3, 5, -4, 9, 2},  new double[] {2,-4,3,2 } }};
    }


    //////////////////////////////////////////////////////////////////


    @Test(dataProvider = "matrixProvider")
    public void twoDimensionArrayTest(int[][] input, int N, String exp) {
        assertEquals(new Variant13().twoDimensionArrayTask(input, N), exp);
    }

    @DataProvider
    public Object[][] matrixProvider() {
        int[][] input1 = {
                {2, 3, 6, 9},
                {34, 98, -9, 2},
                {-4, 2, 1, 6},
                {-98, 8, 1, 5}};
        String output1  = "2 3 6 9 2 6 5 34 98 -9 1 1 -4 2 8 -98 ";


        int[][] input2 = {{2, 3, 6, 9, -9},
                {-4, 2, 1, 6, 1},
                {34, 98, -9, 2, 1},
                {-98, 8, 1, 5, 3},
                {1, 1, 1, 1, 1}};

        String output2  = "2 3 6 9 -9 1 1 3 1 -4 2 1 6 2 5 1 34 98 -9 1 1 -98 8 1 1 ";

        int[][] input3 = {{-98, 8, 1},
                {-4, 2, 1},
                {34, 98, -9}};
        String output3  = "-98 8 1 1 -9 -4 2 98 34 ";
        return new Object[][] { {input1, 4, output1}, { input2, 5, output2 }, { input3, 3, output3 } };

    }

    @Test
    public void arrayTest2(){
        assertEquals(new int[]{2, 3}, new int[]{2, 3});
    }


}