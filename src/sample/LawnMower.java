package sample;


import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class LawnMower {

    private boolean activeStatus = false;
    private ImageView mower;

    public LawnMower(ImageView m){
        mower = m;
    }

    public ImageView getLawnMower(){
        return mower;
    }

    public boolean getActiveStatus(){
        return activeStatus;
    }

    public void changeActiveStatus(){
        activeStatus = !activeStatus;
    }

}
