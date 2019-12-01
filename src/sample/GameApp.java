package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.AudioClip;
import javafx.stage.Stage;

import java.io.*;

public class GameApp extends Application {

    private static final AudioClip clip1 = new AudioClip(new File("src/sample/resources/soundClips/clip1.wav").toURI().toString());
    private static Player player = null;

    public static void setPlayer(Player p){
        player = p;
    }

    public static Player getPlayer(){
        return player;
    }
    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("resources/fxml/level_1.fxml"));
        primaryStage.setTitle("Plants Vs Zombies");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();

        clip1.setCycleCount(-1);
        clip1.play();

    }


    public static void main(String[] args) {
        launch(args);
    }

    public static void saveGame() throws IOException {
        ObjectOutputStream out = null;
        String fileName = player.getName() + ".txt";

        player.startSerialisingProcedure();

        try{
            out = new ObjectOutputStream(new FileOutputStream(fileName));
            out.writeObject(player);
        }finally {
          if (out != null)
            out.close();
        }
    }

    public static void loadGame(String playerName, ImageView pane) throws IOException, ClassNotFoundException {
        ObjectInputStream in = null;

        try{
            String filename = playerName + ".txt";
            File file = new File(filename);

            if (!file.exists()){
                System.out.println("No previous game record corresponding to given player found. Exiting...");
            }
            in = new ObjectInputStream(new FileInputStream(filename));
            Player p = (Player) in.readObject();
            p.setUpGame(pane);

        }finally{
            if (in != null){
                in.close();
            }
        }
    }
}
