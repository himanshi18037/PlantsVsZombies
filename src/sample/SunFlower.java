package sample;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;
import sample.Controllers.Levels_Common_Features;


public class SunFlower extends Plant{

    {
        super.setImage(new Image("sample/resources/images/plants/sunflower.gif"));
        this.setWaitTime(10);
        this.setPlantCost(50);
        this.setShopTag(1);
        this.setAttackPower(0);
        this.setHealth(5);
    }
    public SunFlower(AnchorPane pane){
        this.pane = pane;
    }
    @Override
    public void activatePlant(ImageView plant) {
        this.plant=plant;
        this.generateSun();
    }

    public void generateSun(){
        double time = 10;
        Label toEdit=Levels_Common_Features.getNumSunTokens();
        Timeline tl = new Timeline();
        Levels_Common_Features.getTimeline().add(tl);
        this.working = tl;
        tl.getKeyFrames().add(new KeyFrame(Duration.seconds(time), actionEvent -> {

            ImageView sun = new ImageView();
            Image image = new Image("sample/resources/images/plants/sun.png");

            sun.setImage(image);
            sun.setFitWidth(45);
            sun.setFitHeight(45);

            double min_x = plant.getX() + 20;

            sun.setX(min_x);
            sun.setY(plant.getY()+4);
            pane.getChildren().add(sun);

            sun.setOnMouseClicked(clicked->{
                pane.getChildren().remove(sun);
                toEdit.setText( Integer.toString(Integer.parseInt(toEdit.getText()) + 25));
            });

        }));

        tl.setCycleCount(Timeline.INDEFINITE);
        tl.play();

    }


}
