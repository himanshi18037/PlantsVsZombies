package sample.Controllers;


import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

public class Menu_Screen_Controller {
    public Label nonamelabel;
    public Label player;
    @FXML
    private ImageView resume_game_button;

    public void setPlayerName(String s){
        player.setText(s);
    }

    public void newGame(MouseEvent mouseEvent) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("../resources/fxml/take_name.fxml"));
            Stage stage = (Stage) resume_game_button.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();

        }catch (IOException e){

        }
    }

    public void resumeGame(MouseEvent mouseEvent) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("../resources/fxml/saved_games.fxml"));
            Stage stage = (Stage) resume_game_button.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();

        }catch (IOException e){

        }
    }

    public void exit(MouseEvent mouseEvent) {
        System.exit(0);
    }

    public void levels_screen(MouseEvent mouseEvent) {
        try {
            if(player.getText().equals("Player Name")){
                nonamelabel.toFront();
            }
            else{
                Parent root = FXMLLoader.load(getClass().getResource("../resources/fxml/levels_Screen.fxml"));
                Stage stage = (Stage) resume_game_button.getScene().getWindow();
                stage.setScene(new Scene(root));
                stage.show();
            }


        }catch (IOException e){

        }
    }
}