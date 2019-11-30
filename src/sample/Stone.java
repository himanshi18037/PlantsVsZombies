package sample;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;
import sample.Controllers.Levels_Common_Features;

public class Stone extends Plant{

    private AnchorPane pane;
    private ImageView plant;
    {
        super.setImage(new Image("sample/resources/images/plants/potato.png"));
        this.setWaitTime(10);
        this.setPlantCost(50);

        this.setShopTag(3);
    }
    public Stone(AnchorPane pane){
        this.pane = pane;
    }
    @Override
    public void activatePlant(ImageView plant) {
        this.plant=plant;
        this.stone();
    }

    public void stone(){
        double time = 5;
        Timeline tl = new Timeline();
        Levels_Common_Features.getTimeline().add(tl);
        Levels_Common_Features.getTimeline().add(tl);
        tl.getKeyFrames().add(new KeyFrame(Duration.seconds(time), actionEvent -> {

            ImageView cherry = new ImageView();
            Image image = new Image("sample/resources/images/plants/potato.png");
//
            cherry.setImage(image);
//
            cherry.setFitWidth(70);
            cherry.setFitHeight(70);

        }));

        tl.setCycleCount(Timeline.INDEFINITE);
        tl.play();

    }
}