package sample.Controllers;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class GameOverScreen {
    public Button continuationButton;

    public void toLevelsScreen(MouseEvent mouseEvent) {
        try {
            FXMLLoader fl = new FXMLLoader(getClass().getResource("../resources/fxml/levels_Screen.fxml"));
            Parent root = fl.load();
            int level = Levels_Common_Features.getLevel();
            if (level == 2){
                ((Levels_Screen_Controller)fl.getController()).level1_won();
            }else if (level == 3){
                ((Levels_Screen_Controller)fl.getController()).level2_won();
            }else if (level == 4){
                ((Levels_Screen_Controller)fl.getController()).level3_won();
            }else if (level == 5){
                ((Levels_Screen_Controller)fl.getController()).level4_won();
            }
            Stage stage = (Stage) continuationButton.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();

        }catch (IOException e){

        }
    }
}
