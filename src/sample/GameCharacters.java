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


    protected int attack(GameCharacters character){
        int a1 = this.health - character.attackPower;
        int a2 = character.health - this.attackPower;

        if (a1>a2){
            return 1;
        }

        return 2;
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



