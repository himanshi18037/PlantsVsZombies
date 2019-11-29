package sample.Controllers;

import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.File;
import java.io.IOException;

public class Home_Screen_Controller{

    @FXML
    private ImageView imageView;

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private StackPane pane;

    @FXML
    private void startGame(MouseEvent mouseEvent) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("../resources/fxml/Menu_Screen.fxml"));

            Scene scene = imageView.getScene();

            root.translateYProperty().set(scene.getHeight());
            pane.getChildren().add(root);

            Timeline tl = new Timeline();
            KeyValue kv = new KeyValue(root.translateYProperty(), 0, Interpolator.EASE_IN);
            KeyFrame kf = new KeyFrame(Duration.seconds(0.1), kv);

            tl.getKeyFrames().add(kf);
            tl.setOnFinished(e->{pane.getChildren().remove(anchorPane);});
            tl.play();

        }catch (IOException e){

        }
    }
}
