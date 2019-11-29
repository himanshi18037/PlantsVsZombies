package sample;

import javafx.scene.image.ImageView;


public class PeaShots {

    private CellLocation location;
    private ImageView imageView;

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
