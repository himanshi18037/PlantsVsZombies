package sample;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

import javax.xml.stream.Location;
import java.rmi.registry.LocateRegistry;

public abstract class Plant extends GameCharacters {

    private int waitTime;
    private int plantCost;
    private transient ImageView plantIm;
    private int shopTag;
    private boolean isAlive = true;
    protected transient Timeline working;
    protected transient AnchorPane pane;
    protected transient ImageView plant;

    public void setPlantIm(ImageView i){
        plantIm = i;
        location = new CellLocation(i.getX(), i.getY());
    }

    public ImageView getPlantIm(){
        return plantIm;
    }

    public void killPlant(){
      //  KeyFrame kf = new KeyFrame(Duration.seconds(1));
       // working.getKeyFrames().add(kf);
        working.stop();
        isAlive = false;
        pane.getChildren().remove(this.plant);
        //});
    }

    public CellLocation getLocation(){
        return this.location;
    }

    public boolean getAliveStatus(){
        return isAlive;
    }

    protected void setShopTag(int num){
        shopTag = num;
    }

    public int getShopTag(){
        return shopTag;
    }

    protected void setWaitTime(int time){
        this.waitTime = time;
    }

    protected void setPlantCost(int cost){
        plantCost = cost;
    }

    public int getCost(){
        return plantCost;
    }

    public int getWaitTime(){
        return waitTime;
    }

    public abstract void activatePlant(ImageView plant);

}


