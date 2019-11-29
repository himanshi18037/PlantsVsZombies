package sample.Controllers;

import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Rectangle;

public class Level_4_Controllers {

     public void highlightCell(MouseEvent mouseEvent) {
            Rectangle iv  = (Rectangle)(mouseEvent.getSource());
            iv.setOpacity(100);
     }

     public void removeHighlight(MouseEvent mouseEvent) {
            Rectangle iv  = (Rectangle)(mouseEvent.getSource());
            iv.setOpacity(0);
     }

}
