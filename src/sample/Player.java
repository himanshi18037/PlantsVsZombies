package sample;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import sample.Controllers.Level_1_controller;
import sample.Controllers.Levels_Common_Features;
import sample.Controllers.Menu_Screen_Controller;

import java.io.IOException;
import java.io.Serializable;

public class Player implements Serializable {

    private String name;
    private GameLayout currentLayout;
    private int level = 1;

    public String getName(){
        return  name;
    }

    public void setLevel(int i){
        level = i;
    }

    public Player(String name){
        this.name = name;
    }

    public GameLayout getGL(){
        return currentLayout;
    }

    public void startSerialisingProcedure() {
        level = Levels_Common_Features.getLevel();
        currentLayout = Levels_Common_Features.getGl();
        currentLayout.updatePlantLocations();
        currentLayout.updateZombieLocations();
        currentLayout.updateSunTokens();
    }

    public void setUpGame(ImageView p) {
        try {
//            String l  = "level_" + level + ".fxml";
//            FXMLLoader fl = new FXMLLoader(getClass().getResource("sample/resources/fxml/level_1.fxml"));
//            Parent root = fl.load();
////            if (level == 1){
////                ((Level_1_controller)fl.getController()).setUp(this);
////            }
//
//            System.out.println("here1");
//
//            Stage stage = (Stage) p.getScene().getWindow();
//            stage.setScene(new Scene(root));
//            stage.show();
            Parent root = FXMLLoader.load(getClass().getResource("/root/PlantsVsZombies/src/sample/resources/fxml/level_2_won.fxml"));
            Stage stage = (Stage) p.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        }catch (IOException e){

        }
    }
}
