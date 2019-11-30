package sample.Controllers;


import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.AudioClip;
import javafx.stage.Stage;
import javafx.util.Duration;
import sample.*;

import java.io.IOException;
import java.util.ArrayList;
import java.io.File;
import java.util.HashSet;
import java.util.Random;


public class Levels_Common_Features {

    private static HashSet<Zombie> zombiesOnGrid = new HashSet<>();
    private static AnchorPane pane;
    private int level;
    private static GameLayout gl;
    private static Label numSunTokens;
    private static Shop currentShop;
    private static ArrayList<Timeline> timelinearray=new ArrayList<Timeline>();
    private LawnMower[] lawnMowers;
    private Timeline checker;

    public static Label getNumSunTokens(){
        return numSunTokens;
    }
    public static GameLayout getGl(){
        return gl;
    }
   
    public static ArrayList<Timeline> getTimeline(){
        return timelinearray;
    }

    public Shop.PlantTags getPlantFromShop(int num){
        return currentShop.getPlant(num);
    }

    public void initialiseLevel(int l){
        level = l;
        gl = new GameLayout(level);
        currentShop = new Shop(level);
    }

    public void setLawnMowers(LawnMower[] lm){
        lawnMowers = lm;
    }

    public static HashSet<Zombie> getAllZombies(){
        return zombiesOnGrid;
    }


    Levels_Common_Features(AnchorPane pane){
        this.pane = pane;
    }

    public boolean addAPlant(Plant type, CellLocation location){

        if (type != null){
            int a = ((int) (adjustXCoordinate(location.getX_coordinate()) - 10)/44) - 3;
            int b = 0;
            int c = (int) (adjustYCoordinate(location.getY_coordinate()));

            if (c==237){
                b = 1;
            }else if(c==304){
                b = 2;
            }else if(c==369){
                b = 3;
            }

            if (level==2 || level==3){
                b-=1;
            }if (level == 1){
                b = 0;
            }

            if (a>3)
                a--;
            try{
                gl.addPlant(b, a, type);
            }catch (CellAlreadyOccupiedException e){
                return false;
            }
            ImageView plant = new ImageView();
            plant.setImage(type.getImage());
            plant.setX(adjustXCoordinate(location.getX_coordinate()));
            plant.setY(adjustYCoordinate(location.getY_coordinate()));
            type.setPlantIm(plant);

            plant.setFitHeight(50);
            plant.setFitWidth(50);
            pane.getChildren().add(plant);
            numSunTokens.setText( Integer.toString(Integer.parseInt(numSunTokens.getText()) - type.getCost()));
            type.activatePlant(plant);
            return true;
        }

        return false;
    }

    private double adjustYCoordinate(double y){
        int decideRow[] = {169, 232, 298, 362, 426};

        if (y>=174 && y<239){
            return 176;
        }else if (y>239 && y<=298){
            return 237;
        }else if (y>298 && y<=362){
            return 304;
        }else if (y>362 && y<=426){
            return 369;
        }else if (y>426 && y<=490){
            return 429;
        }return y;
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

    public static void checkPlantAvailability(ImageView [] allPlantsOfLevel){
        Timeline t = new Timeline();
        timelinearray.add(t);
        t.getKeyFrames().add(new KeyFrame(Duration.millis(50), e->{
            if (Integer.parseInt(numSunTokens.getText()) < 100){
                allPlantsOfLevel[0].toFront();
            }else {

                if (currentShop.getPlant(0).getAvailabilityStatus())
                    allPlantsOfLevel[0].toBack();
            }
            try{
                if (Integer.parseInt(numSunTokens.getText()) < 50){
                    allPlantsOfLevel[1].toFront();
                }else {

                    if (currentShop.getPlant(1).getAvailabilityStatus())
                        allPlantsOfLevel[1].toBack();
                }
            }catch (IndexOutOfBoundsException e1){}

            try{
                if (Integer.parseInt(numSunTokens.getText()) < 150){
                    allPlantsOfLevel[2].toFront();
                }else {

                    if (currentShop.getPlant(2).getAvailabilityStatus())
                        allPlantsOfLevel[2].toBack();
                }
            }catch (IndexOutOfBoundsException e1){}

            try{
                if (Integer.parseInt(numSunTokens.getText()) < 50){
                    allPlantsOfLevel[3].toFront();
                }else {

                    if (currentShop.getPlant(3).getAvailabilityStatus())
                        allPlantsOfLevel[3].toBack();
                }
            }catch (IndexOutOfBoundsException e1){}

        }));
        t.setCycleCount(Timeline.INDEFINITE);
        t.play();
    }

    public static void droppingSun(Label toEdit){

        numSunTokens = toEdit;

        int time = 10;

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
            KeyFrame kf = new KeyFrame(Duration.seconds(4), e->{

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
        int time = 10;

        int[] y_pos;

        if (level == 1){
            y_pos = new int[]{269};
        }else if (level == 2 || level == 3){
            y_pos = new int[]{204, 269, 338};
        }else {
            y_pos = new int[]{147, 206, 269, 338, 403};
        }

        AudioClip zombieReleased  = new AudioClip(new File("src/sample/resources/soundClips/groan.wav").toURI().toString());
   //     AudioClip zombieWalks = new AudioClip(new File("src/sample/resources/soundClips/grasswalk.wav").toURI().toString());
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

            int lnum = rand.nextInt(y_pos.length);
            int y_stop_coord = y_pos[lnum];

            zombie.setX(700);
            zombie.setY(y_stop_coord);
            zombie.toFront();
            Zombie z = new Zombie(zombie, lnum+1);
            gl.addZombie(z,lnum);


            pane.getChildren().add(zombie);
            zombiesOnGrid.add(z);
            zombieReleased.play();

            Timeline timeline = new Timeline();
            timelinearray.add(timeline);
            z.setWalking(timeline);
            KeyValue kv = new KeyValue(zombie.xProperty(), 125);
            KeyFrame kf = new KeyFrame(Duration.seconds(30),e-> {
  //              zombieWalks.play();
                try {
//                    zombieWalks.stop();
                    if (zombie.getX() <= 125 && z.checkIfAlive()) {
                        if (!lawnMowers[z.getLane()-1].getActiveStatus()){
                            lawnMowers[z.getLane()-1].changeActiveStatus();
                            moveLawnMower(lawnMowers[z.getLane()-1], z.getLane());
                        }else if (z.checkIfAlive()){
                            throw new HomeInvadedException();
                        }
                    }
                }catch (HomeInvadedException e1){
                    for(int i=0;i<Levels_Common_Features.getTimeline().size();i++){
                        Levels_Common_Features.getTimeline().get(i).stop();
                    }

                    this.gameLost();
                }


            }, kv);
            timeline.getKeyFrames().add(kf);
            timeline.play();

        }));

        Timeline t = new Timeline();
        Levels_Common_Features.getTimeline().add(t);
        checker = t;
        KeyFrame keyFrame = new KeyFrame(Duration.seconds(1), check->{
            for (Zombie z: getAllZombies()) {
                if (z.checkIfAlive())
                    checkPlantZombieCollision(z);
            }
        });
        t.getKeyFrames().add(keyFrame);
        t.setCycleCount(-1);
        t.play();

        tl.setCycleCount(Timeline.INDEFINITE);
        tl.play();

    }



    public void moveLawnMower(LawnMower mower, int lane){

        ImageView mowerImg = mower.getLawnMower();
        Timeline move = new Timeline();
        timelinearray.add(move);
        KeyValue kv = new KeyValue(mowerImg.xProperty(), 650);
        KeyFrame kf = new KeyFrame(Duration.seconds(7), mow->{

            if (mowerImg.getX() > 600){
                pane.getChildren().remove(mowerImg);
            }
        }, kv);

        Timeline tl = new Timeline();

        KeyFrame keyFrame = new KeyFrame(Duration.seconds(1), mow->{

            for (Zombie zb: zombiesOnGrid){

                if (zb.checkIfAlive() && zb.getLane() == lane && zb.getLinkedGUIZombie().intersects(mowerImg.getBoundsInParent())){
                    pane.getChildren().remove(zb.getLinkedGUIZombie());
                    zb.killZombie();
                    zb.getWalking().stop();

                }
            }


        });
        move.getKeyFrames().add(kf);
        tl.getKeyFrames().add(keyFrame);
        tl.setCycleCount(Timeline.INDEFINITE);
        tl.play();
        move.play();

        move.setOnFinished(e->{tl.stop();});
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

        t.setOnFinished(e->{

            try {
                checker.stop();
                for(int i=0;i<Levels_Common_Features.getTimeline().size();i++){
                    Levels_Common_Features.getTimeline().get(i).stop();
                }
                Parent root = null;
                if (level == 1){
                    root = FXMLLoader.load(getClass().getResource("../resources/fxml/level_1_won.fxml"));
                }else if(level == 2){
                    root = FXMLLoader.load(getClass().getResource("../resources/fxml/level_2_won.fxml"));
                }else if(level == 3){
                    root = FXMLLoader.load(getClass().getResource("../resources/fxml/level_3_won.fxml"));
                }else if (level == 4){
                    root = FXMLLoader.load(getClass().getResource("../resources/fxml/level_4_won.fxml"));
                }
                Stage stage = (Stage) pane.getScene().getWindow();
                stage.setScene(new Scene(root));
                stage.show();

            }catch (IOException e1){

            }
        });
    }

    private void gameLost(){
        try {
            checker.stop();
            for(int i=0;i<Levels_Common_Features.getTimeline().size();i++){
                Levels_Common_Features.getTimeline().get(i).stop();
            }
            FXMLLoader fl = new FXMLLoader(getClass().getResource("../resources/fxml/levels_Screen.fxml"));
            Parent root = fl.load();

            if (level == 2){
                ((Levels_Screen_Controller)fl.getController()).level1_won();
            }else if (level == 3){
                ((Levels_Screen_Controller)fl.getController()).level2_won();
            }else if (level == 4){
                ((Levels_Screen_Controller)fl.getController()).level3_won();
            }
            Stage stage = (Stage) pane.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();

        }catch (IOException e){

        }
    }

    private void checkPlantZombieCollision(Zombie z){
        int row = z.getLane();
        Plant[] allCurrent  = gl.getAllPlantsInRow(row-1);
        for (Plant p: allCurrent){
            if (p!=null && p.getAliveStatus() && p.getPlantIm().intersects(z.getLinkedGUIZombie().getBoundsInLocal())){
                z.getWalking().pause();
                boolean won = z.attackAPlant(p);
                if (won){
                    z.getWalking().play();
                    p.killPlant();
                    gl.removePlant(z.getLane()-1,p);

                }else{
                    z.getWalking().stop();
                    z.killZombie();
                    pane.getChildren().remove(z.getLinkedGUIZombie());
                }
            }
        }
    }


}
