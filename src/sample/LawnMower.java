package sample;

import javafx.scene.image.ImageView;

import java.io.Serializable;

public class LawnMower implements Serializable {

    private boolean activeStatus = false;
    private transient ImageView mower;

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
