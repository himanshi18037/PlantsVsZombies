package sample;

import sample.Controllers.Levels_Common_Features;

import java.io.Serializable;

public class Player implements Serializable {

    private String name;
    private GameLayout currentLayout;
    private int level;

    public String getName(){
        return  name;
    }

    public void setLevel(int i){
        level = i;
    }

    public Player(String name){
        this.name = name;
    }

    public void startSerialisingProcedure() {
        level = Levels_Common_Features.getLevel();
        currentLayout = Levels_Common_Features.getGl();
        currentLayout.updatePlantLocations();
        currentLayout.updateZombieLocations();
        currentLayout.updateSunTokens();
    }

    public void setUpGame() {
    }
}
