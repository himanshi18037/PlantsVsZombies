package sample;

import javafx.scene.image.ImageView;

import java.io.Serializable;


public class PeaShots implements Serializable {

    private CellLocation location;
    private transient ImageView imageView;

    public PeaShots(CellLocation l, ImageView iv){
        location = l;
        imageView = iv;
    }

    public ImageView getPea(){
        return imageView;
    }

    public CellLocation getLocation(){
        return location;
    }

    public void updateLocation(double x, double y){
        location.setX_coordinate(x);
        location.setY_coordinate(y);
    }

}
