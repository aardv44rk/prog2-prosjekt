package ntnu.idi.idatt.components;

import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import ntnu.idi.idatt.utility.StyleUtil;

public class UILudoTile extends VBox {

  public UILudoTile() {
    getStyleClass().add("ludo-tile");
    Region filler = new Region();
    filler.getStyleClass().add("ludo-tile-filler");
    getChildren().add(filler);
  }

  public void setColor(Color color) {
    setStyle(String.format("-fx-background-color: %s;", StyleUtil.toRgbString(color)));
  }
}
