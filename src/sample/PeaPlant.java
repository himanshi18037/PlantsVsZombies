package sample;

import com.sun.prism.shader.Solid_TextureYV12_AlphaTest_Loader;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.AudioClip;
import javafx.util.Duration;
import sample.Controllers.Levels_Common_Features;

import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;

public class PeaPlant extends Plant{

    private AnchorPane pane;
    private ImageView plant;
    private ArrayList<PeaShots> allShots = new ArrayList<PeaShots>();

    {
        super.setImage(new Image("sample/resources/images/plants/peashooter.gif"));
        this.setWaitTime(10);
        this.setPlantCost(100);
        this.setShopTag(0);
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

            PeaShots p = new PeaShots(new CellLocation(pea.getX(), pea.getY()), pea);
            allShots.add(p);
            pane.getChildren().add(pea);

           peaThrown.play();

            Timeline timeline = new Timeline();
            KeyValue kv = new KeyValue(pea.xProperty(), max_x);
            timeline.getKeyFrames().add(new KeyFrame(Duration.seconds(7), e ->{

                HashSet<ImageView> allZombies = Levels_Common_Features.getAllZombies();

                ArrayList<PeaShots> toRemove;

                for (ImageView z: allZombies){
                    toRemove = new ArrayList<PeaShots>();
                    for (PeaShots ps: allShots) {
                        if (z.intersects(ps.getPea().getLayoutBounds())) {
                            pane.getChildren().remove(ps.getPea());
                            toRemove.add(ps);
                            break;
                        }
                    }for (int i = 0; i<toRemove.size(); i++){
                        allShots.remove(toRemove.get(i));
                    }
                }

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
