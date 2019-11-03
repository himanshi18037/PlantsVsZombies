package sample;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class Take_name_controller {
    public Button back_Button;
    public TextField name;

    public void backToMainMenu(MouseEvent mouseEvent) {
        toMainMenu("None");
    }

    public void passName(MouseEvent mouseEvent) {

        String player_name = name.getText();
        toMainMenu(player_name);
    }

    private void toMainMenu(String player_name){
        try {
            FXMLLoader fl = new FXMLLoader(getClass().getResource("resources/fxml/Menu_Screen.fxml"));
            Parent root = fl.load();
            if (!player_name.equals("None")){
                ((Menu_Screen_Controller)fl.getController()).setPlayerName(player_name);
            }
            Stage stage = (Stage) back_Button.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();


        }catch (IOException e){

        }
    }
}
