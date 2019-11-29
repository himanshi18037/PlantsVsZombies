package sample;

public class CellLocation {
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

}
