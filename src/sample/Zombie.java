package sample;

import javafx.scene.image.ImageView;

public class Zombie extends GameCharacters{

    private ImageView linkedGUIZombie;
    private boolean isAlive = true;
    private int laneNum;

    public Zombie(ImageView iv, int num){
        this.setHealth(6);
        this.linkedGUIZombie = iv;
        this.laneNum = num;
    }

    public int getLane(){
        return laneNum;
    }

    public ImageView getLinkedGUIZombie(){
        return linkedGUIZombie;
    }

    public boolean checkIfAlive(){
        return isAlive;
    }

    public void killZombie(){
        isAlive = false;
    }


}
