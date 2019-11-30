package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.media.AudioClip;
import javafx.stage.Stage;

import java.io.File;

public class GameApp extends Application {

    private static final AudioClip clip1 = new AudioClip(new File("src/sample/resources/soundClips/clip1.wav").toURI().toString());

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("resources/fxml/level_4.fxml"));
        primaryStage.setTitle("Plants Vs Zombies");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();

        clip1.setCycleCount(-1);
        clip1.play();

    }


    public static void main(String[] args) {
        launch(args);
    }
}
