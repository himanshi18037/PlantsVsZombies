package sample;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.AudioClip;
import javafx.util.Duration;

import java.io.File;

public class PeaPlant extends Plant{

    private AnchorPane pane;
    private ImageView plant;

    {
        super.setImage(new Image("sample/resources/images/plants/peashooter.gif"));
        this.setWaitTime(10);
        this.setPlantCost(100);
    }

    public PeaPlant(AnchorPane pane){
        this.pane = pane;
    }

    @Override
    public void activatePlant(ImageView plant) {
        this.plant = plant;
        this.shootPea();
    }

    public void shootPea(){
        double time = 1.5;
        AudioClip peaThrown  = new AudioClip(new File("src/sample/resources/soundClips/peathrown.wav").toURI().toString());

        Timeline tl = new Timeline();
        tl.getKeyFrames().add(new KeyFrame(Duration.seconds(time), actionEvent -> {

            ImageView pea = new ImageView();
            Image image = new Image("sample/resources/images/plants/pea.png");

            pea.setImage(image);

            pea.setFitWidth(15);
            pea.setFitHeight(15);

            double min_x = plant.getX() + 45;
            int max_x = 700;

            pea.setX(min_x);
            pea.setY(plant.getY()+1);

            pane.getChildren().add(pea);
            peaThrown.play();

            Timeline timeline = new Timeline();
            KeyValue kv = new KeyValue(pea.xProperty(), max_x);
            timeline.getKeyFrames().add(new KeyFrame(Duration.seconds(7), e ->{

                if (pea.getX() > 650){
                    pane.getChildren().remove(pea);
                }

            }, kv));
            timeline.play();

        }));

        tl.setCycleCount(Timeline.INDEFINITE);
        tl.play();

    }

}
