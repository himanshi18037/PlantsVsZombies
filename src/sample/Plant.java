package sample;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

public abstract class Plant extends GameCharacters {

    private int waitTime;
    private int plantCost;
    private ImageView plantIm;
    private int shopTag;
    private boolean isAlive = true;
    protected Timeline working;
    protected AnchorPane pane;
    protected ImageView plant;

    public void setPlantIm(ImageView i){
        plantIm = i;
    }

    public ImageView getPlantIm(){
        return plantIm;
    }

    public void killPlant(){
      //  KeyFrame kf = new KeyFrame(Duration.seconds(1));
       // working.getKeyFrames().add(kf);
        working.stop();
        isAlive = false;
      //  working.setOnFinished(e->{
            pane.getChildren().remove(this.plant);
        //});
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

class SunFlower extends Plant{


    @Override
    public void activatePlant(ImageView plant) {

    }
}

class Stone extends Plant{

    @Override
    public void activatePlant(ImageView plant) {

    }
}

class CherryBomb extends Plant{

    @Override
    public void activatePlant(ImageView plant) {

    }
}


