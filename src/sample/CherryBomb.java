package sample;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;
import sample.Controllers.Levels_Common_Features;

public class CherryBomb extends Plant{

    private AnchorPane pane;
    private ImageView plant;
    {
        super.setImage(new Image("sample/resources/images/plants/cherry_bomb.png"));
        this.setWaitTime(10);
        this.setPlantCost(150);

        this.setShopTag(2);
    }
    public CherryBomb(AnchorPane pane){
        this.pane = pane;
    }
    @Override
    public void activatePlant(ImageView plant) {
        this.plant=plant;
        this.burst();
    }

    public void burst(){
        double time = 5;
        Timeline tl = new Timeline();
        Levels_Common_Features.getTimeline().add(tl);
        Levels_Common_Features.getTimeline().add(tl);
        tl.getKeyFrames().add(new KeyFrame(Duration.seconds(time), actionEvent -> {

            ImageView cherry = new ImageView();
            Image image = new Image("sample/resources/images/plants/cherry_bomb.png");

            cherry.setImage(image);

            cherry.setFitWidth(70);
            cherry.setFitHeight(70);

            double min_x = plant.getX() +30;
            int max_x = 700;

            cherry.setX(min_x);
            cherry.setY(plant.getY()+4);
            pane.getChildren().add(cherry);
            Timeline timeline = new Timeline();
            Levels_Common_Features.getTimeline().add(timeline);


            cherry.setOnMouseClicked(clicked->{
                pane.getChildren().remove(cherry);

            });


            timeline.play();

        }));

        tl.setCycleCount(Timeline.INDEFINITE);
        tl.play();

    }

}
