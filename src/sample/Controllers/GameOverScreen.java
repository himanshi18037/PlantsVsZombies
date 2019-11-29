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
            Parent root = FXMLLoader.load(getClass().getResource("../resources/fxml/levels_Screen.fxml"));
            Stage stage = (Stage) continuationButton.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();

        }catch (IOException e){

        }
    }
}
