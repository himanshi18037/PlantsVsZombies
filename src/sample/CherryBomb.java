package sample;

import com.sun.javafx.collections.MappingChange;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;
import sample.Controllers.Levels_Common_Features;
import sample.Controllers.Levels_Screen_Controller;

import java.util.HashSet;

public class CherryBomb extends Plant{

    private AnchorPane pane;
    private ImageView plant;
    {
        super.setImage(new Image("sample/resources/images/plants/cherry_bomb.png"));
        this.setWaitTime(10);
        this.setPlantCost(150);
        this.setHealth(15);
        this.setAttackPower(10);
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
        working = tl;
        Levels_Common_Features.getTimeline().add(tl);
        tl.getKeyFrames().add(new KeyFrame(Duration.seconds(time), actionEvent -> {

            ImageView cherry = new ImageView();
            Image image = new Image("sample/resources/images/plants/cherry_bomb.png");

            cherry.setImage(image);

            cherry.setFitWidth(70);
            cherry.setFitHeight(70);

            double min_x = plant.getX();

            cherry.setX(min_x-5);
            cherry.setY(plant.getY()-5);
            pane.getChildren().remove(plant);
            pane.getChildren().add(cherry);

            Timeline t = new Timeline();

            KeyFrame kf = new KeyFrame(Duration.seconds(1), e->{
               // System.out.println("Executing  ");
                HashSet<Zombie> allZombies = Levels_Common_Features.getAllZombies();
                for (Zombie z: allZombies){
                    if (Math.abs(z.getLinkedGUIZombie().getX() - min_x)<75 && Math.abs(z.getLinkedGUIZombie().getY() - cherry.getY())<75){
                        z.killZombie();
                        pane.getChildren().remove(z.getLinkedGUIZombie());
                    }
                }
            });

            t.getKeyFrames().add(kf);
            t.setCycleCount(1);
            t.play();
            t.setOnFinished(e->{
                pane.getChildren().remove(cherry);
            });
        }));

        tl.setCycleCount(1);
        tl.play();

        tl.setOnFinished(e->{
            Levels_Common_Features.getGl().removePlant(this);
        });
    }

}
