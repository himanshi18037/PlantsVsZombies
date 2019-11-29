package sample.Controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Rectangle;
import javafx.stage.Modality;
import javafx.stage.Stage;
import sample.CellLocation;
import sample.PeaPlant;
import sample.Plant;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.ArrayList;

public class Level_1_controller implements Initializable {
    
    @FXML
    public ImageView menu_b;
    @FXML
    public AnchorPane pane;
    public ImageView mower_center;
    public Label num_sun;
    public ImageView ingame;
    public Rectangle saveGame;
    public Rectangle exit;
    public Rectangle backtogame;
    public Rectangle mainMenu;
    private Levels_Common_Features lcf;
    private Plant currentlySelectedPlant;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        lcf = new Levels_Common_Features(pane);
        lcf.droppingSun(num_sun);
        lcf.zombie_Move(1);
        lcf.setProgress();
        lcf.level = 1;
    }

    public void mowDown(MouseEvent m) {
        lcf.moveLawnMower(mower_center);
    }

    public void invokeMenu(MouseEvent mouseEvent) throws IOException, InterruptedException {
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
        if (currentlySelectedPlant!=null)
//            System.out.println(mouseEvent.getSceneX() + " " + mouseEvent.getSceneY());
            lcf.addAPlant(currentlySelectedPlant, new CellLocation(mouseEvent.getSceneX(), mouseEvent.getSceneY()));
            currentlySelectedPlant = null;

    }

    public void backtogame(MouseEvent mouseEvent) {
        System.out.println("back to gameeeee");
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
        System.out.println("sdfdghgj");
    }

    public void mainMenu(MouseEvent mouseEvent) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("../resources/fxml/Menu_Screen.fxml"));
            Stage stage = (Stage) mainMenu.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
            System.out.println("weregtrhtjfbgfg");

        }catch (IOException e){

        }
    }


    public void exit(MouseEvent mouseEvent) {
    }
}
