package sample;

import javafx.scene.image.Image;

public abstract class GameCharacters {
    private int health;
    private int attackPower;

    private Image image;

    protected void setHealth(int health){
        this.health = health;
    }

    protected void setAttackPower(int power){
        this.attackPower = power;
    }


    protected void attack(GameCharacters character){
        character.isAttacked(attackPower);
    }

    protected void isAttacked(int pow){
        health -= pow;
    }

    public Image getImage(){
        return image;
    }

    protected void setImage(Image i){
        image = i;
    }

    public int getHealth(){
        return this.health;
    }

}



