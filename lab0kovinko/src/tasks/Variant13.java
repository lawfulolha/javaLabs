package tasks;
import static java.lang.Math.pow;
import static java.lang.Math.sqrt;

public class Variant13 {

    //INPUT OUTPUT
    public double[] inputOutputTask(double r1, double r2)
    {
        double area1 = Math.PI*pow(r1,2);
        double area2 = Math.PI*pow(r2,2);
        double areaCircle =Math.abs(area1 - area2);
        return new double[]{area1, area2, areaCircle};
    }

    //INTEGER
    public int integerNumberTask(int num) {
        double rest = (num%100)*10;
        return (int) rest + num/100;
    }

    //BOOLEAN
    public boolean booleanTask( int A,int B,int C) {
        return ( A>0 || B>0 || C>0 );
    }

    //IF
    public int ifTask(int a, int b, int c) {
        if((a>b && a<c) || (a<b && a>c))
            return a;
        else if((b>a && b<c) || (b<a && b>c))
            return b;
        else
            return c;
    }

    // SWITCH CASE
    public double[] switchTask( int elNum, double val) {

        if (val<0) throw new IllegalArgumentException("value must be positive");
        double elbow, hyp, height, area;
        switch (elNum) {
            case(1):
            {
                elbow = val;
                hyp = elbow*sqrt(2);
                height = hyp/2;
                area = hyp*height/2;
            }
            break;
            case(2):
            {
                hyp = val;
                elbow = hyp/sqrt(2);
                height = hyp/2;
                area = hyp*height/2;
            }
            break;
            case(3):
            {
                height = val;
                hyp = 2*height;
                elbow = hyp/sqrt(2);
                area = hyp*height/2;
            }
            break;
            case(4):
            {
                area = val;
                elbow = sqrt(area*2);
                hyp = elbow*sqrt(2);
                height = hyp/2;
            }
            break;
            default:
                throw new IllegalArgumentException("n must be in range (1, 4) !");
        }
        return new double[] {elbow, hyp, height, area};
    }


    //FOR
    public double forTask ( int N ){
        double sum = 0;
        for( int i = 1; i<=N; i++){
            sum += pow(-1,i+1)*(1 +(double) i/10 );
        }
        return sum;
    }


    //WHILE
    public int[] whileTask (int A){
        int sum = 0;
        int K=0;
        for(K=0; sum <= A; K++){
            sum += K;
        }
        int[] res = {sum, K};
        return res;
    }


    //SERIES
    public int seriesTask (int[] arr){
        int sum = 0;
        for(int i = 0; arr[i]!= 0 && i<arr.length ; i++){
            if( arr[i] > 0 && arr[i]%2 == 0)
                sum += arr[i];
        }
        return sum;
    }

    public float min(float a, float b)
    {
        if(a<b) return a;
        return b;
    }

    public float max(float a, float b)
    {
        if(a>b) return a;
        return b;
    }

    //PROC SORTDEC3
    public float[] SortDec3(float A, float B, float C){
        float temp1, temp2;
        temp1 = min(min(A,B),C);
        temp2 = max(max(A,B),C);
        B = A + B + C - temp1 - temp2;
        A = temp2;
        C = temp1;
        return new float[]{A,B,C};
    }

    //PROC DIGIT COUNT
    public int DigitCount(int K)
    {
        int count = 1;

        while((K/=10) > 0) count++;
        return count;
    }

    //MINMAX
    public int minMax(int N, int[] arr)
    {
        int max= 0;
        int num = 0;
        for(int i=0; i<arr.length; i++){
            if((arr[i]%2!=0 && arr[i]>max ) || i==0) {
                max = arr[i];
                num = i;
            }
        }
        return num;
    }

    //ARRAY
    public double[] arrayTask(int N, double[] arr)
    {
        if(N%2==0) throw new IllegalArgumentException("N must be odd");

        int size = N/2+1;
        double[] res = new double[size];
        int i,j;
        for(i = arr.length-1, j=0; i>=0 && i%2==0 && j<= size ; i=i-2, ++j ) {
            res[j] = arr[i];
        }

        return res;
    }

    //MATRIX
    public String twoDimensionArrayTask(int[][] array, int N) {
        String res = "";
        int K ;
        K = N;
        int j,l;
        for(int i=0; i<N; ++i) {
            for (l = 0; l < N - i-1; ++l)
                res += array[i][l] + " ";

            for (j = i ; j < N; ++j)
                res += array[j][N-i-1] + " ";
        }
        return res;}



    public static void main(String[] args) {


        int[][] arr ={{1,1,1,1},{2,2,2,2},{3,3,3,3},{4,4,4,4}};
        double[] ar = {1,2,3,4,5,6,7};

        Variant13 C = new Variant13();


        //INPUT OUTPUT TASK
        System.out.println("INPUT OUTPUT TASK: ");
        double[] arr2 = C.inputOutputTask(1,2);
        for(int i = 0; i<3; i++)
            System.out.println(arr2[i]);

        // INTEGER TASK
        System.out.println("INTEGER TASK: "+C.integerNumberTask(123));

        //BOOLEAN
        System.out.println( "BOOLEAN: "+C.booleanTask(-1,-2,-3));

        //IF
        System.out.println("IF: "+C.ifTask(3,1,2));

        //CASE
        double[] ar1 = C.switchTask(2,4);
        System.out.println("SWITCH: ");
        for(int i = 0; i<ar1.length; i++)
            System.out.println(ar1[i]);

        //FOR
        System.out.println("FOR: "+ C.forTask(9));
        //WHILE
        int[] arInt = C.whileTask(12);
        System.out.println("WHILE: Sum = "+arInt[0]+" K = "+arInt[1]);

        // SERIES
        System.out.println("SERIES: "+C.seriesTask(new int[]{1,-2,3,4,-5,6,7,0}));

        //PROC SORTDEC
        float[] arFloat;
        arFloat = C.SortDec3(3, 9, 1);
        System.out.println("PROC SORTDEC: A = "+arFloat[0]+" B = "+arFloat[1]+" C = "+arFloat[2]);

        //MINMAX
        System.out.println("MIN MAX: "+ C.minMax(6, new int[] {1,2,3,4,5,5}));

        //ARRAY
        System.out.println("ARRAY : ");
        ar1 =  C.arrayTask(7, ar);
        for(int i = 0; i<ar1.length; i++)
            System.out.println(ar1[i]);

        //MATRIX
        System.out.println( "MATRIX: "+C.twoDimensionArrayTask(arr,4));
    }
}