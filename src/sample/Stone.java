package sample;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;
import sample.Controllers.Levels_Common_Features;

import java.util.HashSet;

public class Stone extends Plant{

    {
        super.setImage(new Image("sample/resources/images/plants/potato.png"));
        this.setWaitTime(10);
        this.setPlantCost(50);
        this.setHealth(20);
        this.setAttackPower(20);
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

    private int checkLane(double y){
        if (y>=174 && y<239){
            return 1;
        }else if (y>239 && y<=298){
            return 2;
        }else if (y>298 && y<=362){
            return 3;
        }else if (y>362 && y<=426){
            return 4;
        }else if (y>426 && y<=490){
            return 5;
        }

        return -1;
    }

    public void stone(){
        double time = 1;
        Timeline tl = new Timeline();
        this.working = tl;
        Levels_Common_Features.getTimeline().add(tl);
        tl.getKeyFrames().add(new KeyFrame(Duration.seconds(time), actionEvent -> {

            HashSet<Zombie> allZombies = Levels_Common_Features.getAllZombies();
            for (Zombie z: allZombies){
                if (z.checkIfAlive() && z.getLane() == checkLane(this.getPlantIm().getX()) && (z.getLinkedGUIZombie().getX() - this.getPlantIm().getX()) < 75){
                    z.getWalking().pause();
                    z.isAttacked(1);
                    this.isAttacked(1);
                }
                if (z.getHealth()<=0){
                    z.killZombie();
                    pane.getChildren().remove(z.getLinkedGUIZombie());

                }

                if (this.getHealth()<=0){
                    Levels_Common_Features.getGl().removePlant(this);
                    this.killPlant();
                    break;
                }
            }


        }));

        tl.setCycleCount(Timeline.INDEFINITE);
        tl.play();

    }
}