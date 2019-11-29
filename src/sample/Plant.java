package sample;

import javafx.scene.image.ImageView;

public abstract class Plant extends GameCharacters {

    private int waitTime;
    private int plantCost;

    private int shopTag;

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


