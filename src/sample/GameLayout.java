package sample;

public class GameLayout {

    private Row allRows[];

    public class Row{
        private Plant plantsInRow[] = new Plant[9];

        Row(){
            for (int i = 0; i<9; i++){
                plantsInRow[i] = null;
            }
        }

        void insertPlant(int col, Plant p){
            if (plantsInRow[col] != null){
                throw new CellAlreadyOccupiedException();
            }
            plantsInRow[col] = p;
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

}