package sample;

import com.sun.javafx.logging.PlatformLogger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Rectangle;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Level_1_controller implements Initializable {

    @FXML
    public ImageView menu_b;
    @FXML
    public AnchorPane pane;
    public ImageView pea_shooter;
    public ImageView mower_center;
    public Label num_sun;
    private Levels_Common_Features lcf;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        lcf = new Levels_Common_Features(pane);
        lcf.droppingSun(num_sun);
        lcf.zombie_Move(1);
        lcf.shootPea(pea_shooter);
        lcf.setProgress();
    }

    public void mowDown(MouseEvent m) {
        lcf.moveLawnMower(mower_center);
    }

    public void invokeMenu(MouseEvent mouseEvent) throws IOException, InterruptedException {
        Parent pause_screen = FXMLLoader.load(getClass().getResource("resources/fxml/ingame_menu.fxml"));
        Stage pause = new Stage();
        pause.initModality(Modality.APPLICATION_MODAL);
        pause.initOwner(menu_b.getScene().getWindow());
        pause.setTitle("Pause Menu");
        pause.setScene(new Scene(pause_screen));

        pause.show();

    }

    public void highlightCell(MouseEvent mouseEvent) {
        Rectangle iv  = (Rectangle)(mouseEvent.getSource());
        iv.setOpacity(100);
    }

    public void removeHighlight(MouseEvent mouseEvent) {
        Rectangle iv  = (Rectangle)(mouseEvent.getSource());
        iv.setOpacity(0);
    }
}
