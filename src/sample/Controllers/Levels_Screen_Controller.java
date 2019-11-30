package sample.Controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.Serializable;

public class Levels_Screen_Controller{

    public ImageView backButton;
    public ImageView level2_cover;
    public ImageView level3_cover;
    public ImageView level4_cover;
    public ImageView level5_cover;

    @FXML
    private ImageView l1;
    @FXML
    private ImageView l2;
    @FXML
    private ImageView l3;
    @FXML
    private ImageView l4;
    @FXML
    private ImageView l5;

    public void level1_won(){
        level2_cover.toBack();
    }

    public void level2_won(){
        level1_won();
        level3_cover.toBack();
    }

    public void level3_won(){
        level2_won();
        level4_cover.toBack();
    }

    public void level4_won(){
        level3_won();
        level5_cover.toBack();
    }


    private void getLevel(int level_num){
        try {
            String level  = "level_" + level_num + ".fxml";
            Parent root = FXMLLoader.load(getClass().getResource("../resources/fxml/" + level));
            Stage stage = (Stage) l1.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();

        }catch (IOException e){

        }
    }

    public void getLevel1(MouseEvent mouseEvent) {
        getLevel(1);
    }

    public void getLevel2(MouseEvent mouseEvent) {
        getLevel(2);
    }

    public void getLevel3(MouseEvent mouseEvent) {
       getLevel(3);
    }

    public void getLevel4(MouseEvent mouseEvent) {
        getLevel(4);
    }

    public void getLevel5(MouseEvent mouseEvent) {
        getLevel(5);
    }

    public void go_to_main_menu(MouseEvent mouseEvent) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("../resources/fxml/Menu_Screen.fxml"));
            Stage stage = (Stage) backButton.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();

        }catch (IOException e){

        }
    }
}
