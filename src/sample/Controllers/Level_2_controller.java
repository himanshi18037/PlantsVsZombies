package sample.Controllers;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;
import sample.*;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Level_2_controller implements Initializable {
    private Levels_Common_Features lcf;
    public AnchorPane pane2;
    public Label pointslabel;
    public ImageView peaplantcover;
    public ImageView sunplantcover;
    public ImageView peaplant;
    public ImageView sunplant;
    public ImageView moverup;
    public ImageView movercenter;
    public ImageView moverdown;
    public ImageView ingame;
    public Rectangle saveGame;
    public Rectangle exit;
    public Rectangle backtogame;
    public Rectangle mainMenu;
    private Plant currentlySelectedPlant;
    private ImageView[] allPlantCovers;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        allPlantCovers = new ImageView[]{peaplantcover, sunplantcover};
        lcf = new Levels_Common_Features(pane2);
        lcf.droppingSun(pointslabel);
        lcf.zombie_Move(2);
        lcf.setProgress();
        lcf.initialiseLevel(2);
        lcf.checkPlantAvailability(allPlantCovers);
        lcf.setLawnMowers(new LawnMower[]{new LawnMower(moverup), new LawnMower(movercenter), new LawnMower(moverdown)});
    }


    public void ingame(MouseEvent mouseEvent) throws IOException, InterruptedException {
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

    public void placesunplant(MouseEvent mouseEvent) {
        currentlySelectedPlant=new SunFlower(pane2);
    }

    public void placepeaplant(MouseEvent mouseEvent) {
        currentlySelectedPlant = new PeaPlant(pane2);
    }

    public void provideLocation(MouseEvent mouseEvent) {
        boolean success = false;
        if (currentlySelectedPlant!=null) {
            success = lcf.addAPlant(currentlySelectedPlant, new CellLocation(mouseEvent.getSceneX(), mouseEvent.getSceneY()));
        }

        if (success){
            Plant reference = currentlySelectedPlant;
            lcf.getPlantFromShop(currentlySelectedPlant.getShopTag()).changeAvailabilityStatus();
            ImageView cover;

            if (currentlySelectedPlant.getClass().equals(PeaPlant.class)){
                cover = allPlantCovers[0];
            }else {
                cover = allPlantCovers[1];
            }

            cover.toFront();
            Timeline t = new Timeline();
            Levels_Common_Features.getTimeline().add(t);
            t.getKeyFrames().add(new KeyFrame(Duration.seconds(currentlySelectedPlant.getWaitTime())));
            t.play();
            t.setOnFinished(actionEvent -> {
                cover.toBack();
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

    public void saveGame(MouseEvent mouseEvent) {
    }

    public void mainMenu(MouseEvent mouseEvent) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("../resources/fxml/Menu_Screen.fxml"));
            Stage stage = (Stage) mainMenu.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();

        }catch (IOException e){

        }
    }


    public void exit(MouseEvent mouseEvent) {
        try {
            for(int i=0;i<Levels_Common_Features.getTimeline().size();i++){
                Levels_Common_Features.getTimeline().get(i).stop();
            }
            Parent root = FXMLLoader.load(getClass().getResource("../resources/fxml/level_2.fxml" ));
            Stage stage = (Stage) pane2.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();

        }catch (IOException e){

        }
    }


}
