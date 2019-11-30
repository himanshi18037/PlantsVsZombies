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

public class Level_3_controller implements Initializable {
    private Levels_Common_Features lcf;
    public AnchorPane pane3;
    public Label pointslabel;
    public ImageView peaplantcover;
    public ImageView sunplantcover;
    public ImageView bombplantcover;
    public ImageView bombplant;
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
        allPlantCovers = new ImageView[]{peaplantcover, sunplantcover,bombplantcover};
        lcf = new Levels_Common_Features(pane3);
        lcf.droppingSun(pointslabel);
        lcf.zombie_Move(3);
        lcf.setProgress();
        lcf.initialiseLevel(3);
        lcf.checkPlantAvailability(allPlantCovers);
        lcf.setLawnMowers(new LawnMower[]{new LawnMower(moverup), new LawnMower(movercenter), new LawnMower(moverdown)});
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
        currentlySelectedPlant=new SunFlower(pane3);
    }

    public void placePeaPlant(MouseEvent mouseEvent) {
        currentlySelectedPlant = new PeaPlant(pane3);
    }

    public void placeBombPlant(MouseEvent mouseEvent) {
        currentlySelectedPlant = new CherryBomb(pane3);
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
            }else if(currentlySelectedPlant.getClass().equals(SunFlower.class))
            {
                cover = allPlantCovers[1];
            }
            else{
                cover=allPlantCovers[2];
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
    }
    
}
