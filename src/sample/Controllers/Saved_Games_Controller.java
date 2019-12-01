package sample.Controllers;

import com.sun.prism.Image;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import sample.GameApp;

import java.io.IOException;

public class Saved_Games_Controller {

    public AnchorPane pane;
    @FXML
    private ImageView back_button;


    @FXML
    public void back_to_main_menu(MouseEvent mouseEvent) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("../resources/fxml/Menu_Screen.fxml"));
            Stage stage = (Stage) back_button.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
//
        }catch (IOException e){
//
        }

    }

    public void load(MouseEvent mouseEvent) throws IOException, ClassNotFoundException{
        GameApp.loadGame("vibhu", back_button);
    }
}
