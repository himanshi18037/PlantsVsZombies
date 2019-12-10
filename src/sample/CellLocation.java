package sample;

import java.io.Serializable;

public class CellLocation implements Serializable {
    private double x_coordinate;
    private double y_coordinate;

    public CellLocation(double x, double y){
        x_coordinate  = x;
        y_coordinate = y;
    }

    public double getX_coordinate(){
        return x_coordinate;
    }

    public double getY_coordinate(){
        return y_coordinate;
    }

    public void setX_coordinate(double x){
        x_coordinate = x;
    }

    public void setY_coordinate(double y){
        y_coordinate = y;
    }

}
