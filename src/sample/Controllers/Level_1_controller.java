package sample.Controllers;

import javafx.animation.KeyFrame;

import javafx.animation.Timeline;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Rectangle;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;
import sample.CellLocation;
import sample.GameLayout;
import sample.PeaPlant;
import sample.Plant;

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
    private Levels_Common_Features lcf;
    private Plant currentlySelectedPlant;



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        lcf = new Levels_Common_Features(pane);
        lcf.droppingSun(num_sun);
        lcf.zombie_Move(1);
        lcf.setProgress();
        lcf.initialiseLevel(1);
        lcf.checkPlantAvailability(new ImageView[]{plant_cover_pea});
    }

    public void mowDown(MouseEvent m) {
        lcf.moveLawnMower(mower_center);
    }

    public void invokeMenu(MouseEvent mouseEvent) throws IOException, InterruptedException {
        Parent pause_screen = FXMLLoader.load(getClass().getResource("../resources/fxml/ingame_menu.fxml"));
        Stage pause = new Stage();
        pause.initModality(Modality.APPLICATION_MODAL);
        pause.initOwner(menu_b.getScene().getWindow());
        pause.setTitle("Pause Menu");
        pause.setScene(new Scene(pause_screen));

        pause.show();

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
            t.getKeyFrames().add(new KeyFrame(Duration.seconds(currentlySelectedPlant.getWaitTime())));
            t.play();
            t.setOnFinished(actionEvent -> {
                plant_cover_pea.toBack();
                lcf.getPlantFromShop(reference.getShopTag()).changeAvailabilityStatus();
            });
            currentlySelectedPlant = null;
        }
    }
}
