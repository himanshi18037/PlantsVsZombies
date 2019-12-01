package sample;

import sample.Controllers.Levels_Common_Features;

import java.io.Serializable;
import java.util.ArrayList;

public class GameLayout implements Serializable {

    private Row allRows[];
    private LawnMower mowers[];
    private int numSunTokens;

    public int getNumSunTokens(){
        return numSunTokens;
    }

    public void updateZombieLocations() {
        for (int i = 0; i<allRows.length; i++){
            ArrayList<Zombie> allZb =  allRows[i].getAllZombies();

            for (Zombie z: allZb){
                if (z.checkIfAlive()){
                    z.setLocation(z.getLinkedGUIZombie().getX(), z.getLinkedGUIZombie().getY());
                }
            }
        }
    }

    public void updatePlantLocations() {
        for (int i = 0; i<allRows.length; i++){
            Plant[] allPlants = allRows[i].getPlants();

            for (Plant p: allPlants){
                if (p!=null && p.getAliveStatus()){
                    p.setLocation(p.getPlantIm().getX(), p.getPlantIm().getY());
                }
            }
        }

    }

    public void updateSunTokens() {
        numSunTokens = Integer.parseInt(Levels_Common_Features.getNumSunTokens().getText());
    }

    public class Row implements Serializable{
        private Plant plantsInRow[] = new Plant[9];

        private ArrayList<Zombie> allZombies = new ArrayList<Zombie>();

        Row() {
            for (int i = 0; i < 9; i++) {
                plantsInRow[i] = null;
            }
        }

        Plant[] getPlants(){
            return plantsInRow;
        }

        void insertPlant(int col, Plant p) {
            if (plantsInRow[col] != null) {
                throw new CellAlreadyOccupiedException();
            }
            plantsInRow[col] = p;
        }

        void addZombie(Zombie z) {
            allZombies.add(z);
        }
        public Plant[] getPlantsInRow(){
            return plantsInRow;
        }

        void removePlant(Plant p){
            for (int i = 0; i<9; i++){
                if (plantsInRow[i] == p){
                    plantsInRow[i] = null;
                    break;
                }
            }
        }

        ArrayList<Zombie> getAllZombies(){
            return allZombies;
        }

    }

    public ArrayList<Zombie> getAllZombies(int i){
        return allRows[i].getAllZombies();
    }

    public GameLayout(int level){

        if (level == 1){
            allRows = new Row[1];
        }else if (level == 2 || level == 3){
            allRows = new Row[3];
        }else {
            allRows = new Row[5];
        }

        for (int i = 0; i<allRows.length; i++){
            allRows[i] = new Row();
        }
    }

    public void addPlant(int row, int col, Plant p){
        allRows[row].insertPlant(col, p);
    }
    public void addZombie(Zombie z, int row){
        allRows[row].addZombie(z);

    }

    public void removePlant(int row, Plant p){
        allRows[row].removePlant(p);
    }

    public Plant[] getAllPlantsInRow(int x){
        return allRows[x].getPlantsInRow();
    }

    public void removePlant(Plant p){
        for (int i = 0; i<allRows.length; i++){
            allRows[i].removePlant(p);
        }
    }

    public void setMowers(LawnMower[] lm){
        mowers = lm;
    }

}