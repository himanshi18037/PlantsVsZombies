package sample;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.AudioClip;
import javafx.util.Duration;

import java.io.File;
import java.util.HashSet;
import java.util.Random;

public class Levels_Common_Features {

    private static HashSet<ImageView> zombiesOnGrid = new HashSet<>();
    private static AnchorPane pane;

    Levels_Common_Features(AnchorPane pane){
        this.pane = pane;
    }


    public static void droppingSun(Label toEdit){

        int time = 12;

        int min_x = 125;
        int max_x = 560;

        int min_stop_y = 150;
        int max_stop_y = 430;

        Timeline tl = new Timeline();
        tl.getKeyFrames().add(new KeyFrame(Duration.seconds(time), actionEvent -> {
            ImageView sun = new ImageView();
            sun.setImage(new Image("sample/resources/images/plants/sun.png"));

            Random rand = new Random();

            int x_coord = rand.nextInt(max_x-min_x+1) + min_x;

            int y_stop_coord = rand.nextInt(max_stop_y-min_stop_y+1) + min_stop_y;

            sun.setX(x_coord);
            sun.setY(0);

            pane.getChildren().add(sun);

            Timeline timeline = new Timeline();
            KeyValue kv = new KeyValue(sun.yProperty(), y_stop_coord);
            KeyFrame kf = new KeyFrame(Duration.seconds(6), e->{

                sun.setOnMouseClicked(clicked->{
                    pane.getChildren().remove(sun);
                    toEdit.setText( Integer.toString(Integer.parseInt(toEdit.getText()) + 25));
                });
            }, kv);
            timeline.getKeyFrames().add(kf);
            timeline.play();

        }));

        tl.setCycleCount(Timeline.INDEFINITE);
        tl.play();

    }

    public void zombie_Move(int level){
        int time = 15;

        int[] y_pos;

        if (level == 1){
            y_pos = new int[]{269};
        }else if (level == 2 || level == 3){
            y_pos = new int[]{206, 269, 338};
        }else {
            y_pos = new int[]{147, 206, 269, 338, 403};
        }

        AudioClip zombieReleased  = new AudioClip(new File("src/sample/resources/soundClips/groan.wav").toURI().toString());
        AudioClip zombieWalks = new AudioClip(new File("src/sample/resources/soundClips/grasswalk.wav").toURI().toString());
        AudioClip zombiesComing = new AudioClip(new File("src/sample/resources/soundClips/zombie_wave.wav").toURI().toString());
        zombiesComing.play();

        Timeline tl = new Timeline();
        tl.getKeyFrames().add(new KeyFrame(Duration.seconds(time), actionEvent -> {
            ImageView zombie = new ImageView();
            zombie.setImage(new Image("sample/resources/images/zombies/Zombieidle.gif"));

            zombie.setFitHeight(76);
            zombie.setFitWidth(61);

            Random rand = new Random();

            int y_stop_coord = y_pos[rand.nextInt(y_pos.length)];

            zombie.setX(700);
            zombie.setY(y_stop_coord);

            pane.getChildren().add(zombie);
            zombiesOnGrid.add(zombie);
            zombieReleased.play();

            Timeline timeline = new Timeline();
            KeyValue kv = new KeyValue(zombie.xProperty(), 125);
            KeyFrame kf = new KeyFrame(Duration.seconds(30),e-> {
                zombieWalks.play();
            }, kv);
            timeline.getKeyFrames().add(kf);
            timeline.play();

        }));

        tl.setCycleCount(Timeline.INDEFINITE);
        tl.play();
    }

    public void addPlant(String name){
        Image im = getPlantImage(name);

        ImageView imageView = new ImageView();


        imageView.setImage(im);

        imageView.setFitWidth(50);
        imageView.setFitHeight(50);

    }

    public void shootPea(ImageView plant){
        double time = 1.5;
        AudioClip peaThrown  = new AudioClip(new File("src/sample/resources/soundClips/peathrown.wav").toURI().toString());

        Timeline tl = new Timeline();
        tl.getKeyFrames().add(new KeyFrame(Duration.seconds(time), actionEvent -> {

            ImageView pea = new ImageView();
            Image image = new Image("sample/resources/images/plants/pea.png");

            pea.setImage(image);

            pea.setFitWidth(15);
            pea.setFitHeight(15);

            double min_x = plant.getLayoutX() + 45;
            int max_x = 700;

            pea.setX(min_x);
            pea.setY(plant.getLayoutY()+1);

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

    private Image getPlantImage(String name){

        if (name.equals("peashooter")){
            return new Image("sample/resources/images/plants/peashooter.gif");
        }else if (name.equals("walnut")){
            return new Image("sample/resources/images/plants/potato.png");
        }else if (name.equals("sunflower")){
            return new Image("sample/resources/images/plants/sunflower.gif");
        }else if (name.equals("cherry_bomb")){
            return new Image("sample/resources/images/plants/cherry_bomb.png");
        }
        else {
            return null;
        }
    }

    public void moveLawnMower(ImageView mower){

        Timeline move = new Timeline();
        KeyValue kv = new KeyValue(mower.xProperty(), 650);
        KeyFrame kf = new KeyFrame(Duration.seconds(5), mow->{
            if (mower.getX() > 600){
                pane.getChildren().remove(mower);
            }
        }, kv);
        move.getKeyFrames().add(kf);
        move.play();
    }

    public void setProgress(){

        ImageView iv = new ImageView();
        Image im = new Image("sample/resources/images/level_char_misc/progress.png");

        iv.setImage(im);

        iv.setX(590);
        iv.setY(90);

        iv.setFitHeight(15);
        iv.setFitWidth(1);

        pane.getChildren().add(iv);

        Timeline t = new Timeline();

        KeyValue kv = new KeyValue(iv.fitWidthProperty(), 240);
        t.getKeyFrames().add(new KeyFrame(Duration.minutes(3), kv));
        t.play();
    }

}
