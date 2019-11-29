package sample.Controllers;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.AudioClip;
import javafx.util.Duration;
import sample.CellLocation;
import sample.Plant;

import java.lang.reflect.Array;
import java.util.ArrayList;

import java.io.File;
import java.util.HashSet;
import java.util.Random;

public class Levels_Common_Features {

    private static HashSet<ImageView> zombiesOnGrid = new HashSet<>();
    private static AnchorPane pane;
    private static ArrayList<Timeline> timelinearray=new ArrayList<Timeline>();
    public static ArrayList<Timeline> getTimeline(){
        return timelinearray;
    }

    int level;

    Levels_Common_Features(AnchorPane pane){
        this.pane = pane;
    }

    public void addAPlant(Plant type, CellLocation location){

        if (type != null){
            ImageView plant = new ImageView();
            plant.setImage(type.getImage());
//            System.out.println(location.getX_coordinate() + " " + location.getY_coordinate());
            plant.setX(adjustXCoordinate(location.getX_coordinate()));
            if (level == 1) {
                plant.setY(304);
            }else{
                plant.setY(location.getY_coordinate());
            }
            plant.setFitHeight(50);
            plant.setFitWidth(50);
            pane.getChildren().add(plant);
            type.activatePlant(plant);
        }
    }

    private double adjustXCoordinate(double x){
        int middleRow[] = {157, 209, 259, 309, 358, 410, 460, 504, 563};
        int i = 0;
        for (; i<9; i++){
            if (middleRow[i]>x)
                break;
        }

        return middleRow[i-1] + 10;
    }


    public static void droppingSun(Label toEdit){

        int time = 12;

        int min_x = 125;
        int max_x = 560;

        int min_stop_y = 150;
        int max_stop_y = 430;

        Timeline tl = new Timeline();
        timelinearray.add(tl);
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
            timelinearray.add(timeline);
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
        timelinearray.add(tl);
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
            timelinearray.add(timeline);
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
        timelinearray.add(move);
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
        timelinearray.add(t);

        KeyValue kv = new KeyValue(iv.fitWidthProperty(), 240);
        t.getKeyFrames().add(new KeyFrame(Duration.minutes(3), kv));
        t.play();
    }

}
