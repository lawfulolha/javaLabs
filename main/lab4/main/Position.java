package main;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

public class Position {
/*
    @Min(value = 0)
    @Max(value = 600)

    public Integer x;
    @Min(value = 0)
    @Max(value = 400)
    public Integer y;

    public Position(){}

    public Position(int x, int y){
        this.x = x;
        this.y = y;
    }

    public void setX(int x) {
        if(x<=0) this.x = 1;
        else this.x = x;
    }

    public void setY(int y) {

        if(y>=MAX_Y) this.y = MAX_Y;
        else if(y<=0) this.y= 1;
        else this.y = y;
    }

    public void setPosition(int x, int y){

        if(x>=MAX_X)  this.x = MAX_X;
        else if(x<=0)  this.x = 1;
        else  this.x = x;
        if(y>=MAX_Y)  this.y = MAX_Y;
        else if(y<=0)  this.y = 1;
        else  this.y = y;
    }

    public int getY() {
        return y;
    }

    public Integer getX() {
        return x;
    }

    public int[] getPosition()
    {
        return new int[]{this.x, this.y};
    }

    @Override
    public String toString() {
        return   "Position x: "+x+ " y: " +y;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Position pos = (Position) o;

        if ( x != null ? !x.equals(pos.x) : pos.x != null) return false;
            return y != 0 ? y.equals(pos.y) : pos.y == null;
    }

    @Override
    public int hashCode() {
        int result = (x != null ? x.hashCode() : 0);
        result = 31 * result + (y != 0 ? y.hashCode() : 0);
        return result;
    }

 */


}
