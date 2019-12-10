package sample.Controllers;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.io.IOException;

public class Level1Won {
    public Rectangle continueButton;

    public void continueToSecondLevel(MouseEvent mouseEvent) {
        try {
            FXMLLoader fl = new FXMLLoader(getClass().getResource("../resources/fxml/levels_Screen.fxml"));
            Parent root = fl.load();
            ((Levels_Screen_Controller)fl.getController()).level1_won();
            Stage stage = (Stage) continueButton.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();

        }catch (IOException e){

        }
    }
}
