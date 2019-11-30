package sample;

import javafx.animation.Timeline;
import javafx.scene.image.ImageView;

public class Zombie extends GameCharacters{

    private ImageView linkedGUIZombie;
    private boolean isAlive = true;
    private int laneNum;
    private Timeline walking;

    public Zombie(ImageView iv, int num){
        this.setHealth(8);
        this.linkedGUIZombie = iv;
        this.laneNum = num;
        this.setAttackPower(3);
    }

    public void setWalking(Timeline t){
        walking = t;
    }

    public Timeline getWalking(){
        return walking;
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

    public boolean attackAPlant(Plant p){
        int winner = this.attack(p);

        if (winner == 2){
            return false;
        }return true;
    }


}
