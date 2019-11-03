package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class Levels_Screen_Controller {

    public ImageView backButton;
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

    private void getLevel(int level_num){
        try {
            String level  = "level_" + level_num + ".fxml";
            Parent root = FXMLLoader.load(getClass().getResource("resources/fxml/" + level));
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
            Parent root = FXMLLoader.load(getClass().getResource("resources/fxml/Menu_Screen.fxml"));
            Stage stage = (Stage) backButton.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();

        }catch (IOException e){

        }
    }
}
