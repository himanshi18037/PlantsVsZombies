package sample;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class FlyingZombie extends Zombie{

    public FlyingZombie(ImageView iv, int num){
        super(iv, num);
        this.setHealth(10);
        this.setAttackPower(4);
    }

}