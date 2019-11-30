package sample;

import java.util.ArrayList;

public class GameLayout {

    private Row allRows[];

    public class Row {
        private Plant plantsInRow[] = new Plant[9];

        private ArrayList<Zombie> allZombies = new ArrayList<Zombie>();

        Row() {
            for (int i = 0; i < 9; i++) {
                plantsInRow[i] = null;
            }
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

}