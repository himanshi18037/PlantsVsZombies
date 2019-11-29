package sample;

import javafx.scene.image.ImageView;

public abstract class Plant extends GameCharacters {

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


