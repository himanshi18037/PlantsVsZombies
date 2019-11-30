package sample.Controllers;

import javafx.animation.KeyFrame;

import javafx.animation.Timeline;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Rectangle;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;
import sample.*;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Level_1_controller implements Initializable {

    @FXML
    public ImageView menu_b;
    @FXML
    public AnchorPane pane;
    public ImageView mower_center;
    public Label num_sun;
    public ImageView plant_cover_pea;
    public ImageView ingame;
    private Levels_Common_Features lcf;
    private Plant currentlySelectedPlant;
    public Rectangle saveGame;
    public Rectangle exit;
    public Rectangle backtogame;
    public Rectangle mainMenu;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        lcf = new Levels_Common_Features(pane);
        lcf.droppingSun(num_sun);
        lcf.zombie_Move(1);
        lcf.setProgress();
        lcf.initialiseLevel(1);
        lcf.checkPlantAvailability(new ImageView[]{plant_cover_pea});
        lcf.setLawnMowers(new LawnMower[]{new LawnMower(mower_center)});
    }


    public void invokeMenu(MouseEvent mouseEvent){
        for(int i=0;i<Levels_Common_Features.getTimeline().size();i++){
            Levels_Common_Features.getTimeline().get(i).pause();
        }
        ingame.toFront();
        saveGame.toFront();
        exit.toFront();
        backtogame.toFront();
        mainMenu.toFront();

    }

    public void highlightCell(MouseEvent mouseEvent) {
        Rectangle iv  = (Rectangle)(mouseEvent.getSource());
        iv.setOpacity(100);
    }

    public void removeHighlight(MouseEvent mouseEvent) {
        Rectangle iv  = (Rectangle)(mouseEvent.getSource());
        iv.setOpacity(0);
    }

    public void selectPeaPlant(MouseEvent mouseEvent) {
        currentlySelectedPlant = new PeaPlant(pane);
    }

    public void provideLocation(MouseEvent mouseEvent) {
        boolean success = false;
        if (currentlySelectedPlant!=null) {
            success = lcf.addAPlant(currentlySelectedPlant, new CellLocation(mouseEvent.getSceneX(), mouseEvent.getSceneY()));
        }

        if (success){
            Plant reference = currentlySelectedPlant;
            lcf.getPlantFromShop(currentlySelectedPlant.getShopTag()).changeAvailabilityStatus();
            plant_cover_pea.toFront();
            Timeline t = new Timeline();
            Levels_Common_Features.getTimeline().add(t);
            t.getKeyFrames().add(new KeyFrame(Duration.seconds(currentlySelectedPlant.getWaitTime())));
            t.play();
            t.setOnFinished(actionEvent -> {
                plant_cover_pea.toBack();
                lcf.getPlantFromShop(reference.getShopTag()).changeAvailabilityStatus();
            });
            currentlySelectedPlant = null;
        }
    }
    public void backtogame(MouseEvent mouseEvent) {
        ingame.toBack();
        saveGame.toBack();
        exit.toBack();
        backtogame.toBack();
        mainMenu.toBack();
        for(int i=0;i<Levels_Common_Features.getTimeline().size();i++){
            Levels_Common_Features.getTimeline().get(i).play();
        }


    }

    public void saveGame(MouseEvent mouseEvent) throws IOException {
        for(int i=0;i<Levels_Common_Features.getTimeline().size();i++){
            Levels_Common_Features.getTimeline().get(i).stop();
        }
        GameApp.saveGame();
    }

    public void mainMenu(MouseEvent mouseEvent) {
        try {
            for(int i=0;i<Levels_Common_Features.getTimeline().size();i++){
                Levels_Common_Features.getTimeline().get(i).stop();
            }
            Parent root = FXMLLoader.load(getClass().getResource("../resources/fxml/Menu_Screen.fxml"));
            Stage stage = (Stage) mainMenu.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();

        }catch (IOException e){

        }
    }


    public void restartLevel(MouseEvent mouseEvent) {
        try {
            for(int i=0;i<Levels_Common_Features.getTimeline().size();i++){
                Levels_Common_Features.getTimeline().get(i).stop();
            }
            Parent root = FXMLLoader.load(getClass().getResource("../resources/fxml/level_1.fxml" ));
            Stage stage = (Stage) pane.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();

        }catch (IOException e){

        }
    }
}
