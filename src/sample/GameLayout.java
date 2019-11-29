package sample;

public class GameLayout {

    private int level;
    private Row allRows[];

    public class Row{
        private Plant plantsInRow[] = new Plant[9];
    }

    public GameLayout(int level){
        this.level = level;

        if (level == 1){
            allRows = new Row[1];
        }else if (level == 2 || level == 3){
            allRows = new Row[3];
        }else {
            allRows = new Row[5];
        }
    }

}