package ntnu.idi.idatt.components;

import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import ntnu.idi.idatt.utility.StyleUtil;

/**
 * Component for a Ludo tile.
 * This class extends VBox and represents a tile on the Ludo board.
 */
public class UILudoTile extends VBox {

  /**
   * Constructor for the UILudoTile class.
   * Initializes the tile with a default style and a filler region.
   */
  public UILudoTile() {
    getStyleClass().add("ludo-tile");
    Region filler = new Region();
    filler.getStyleClass().add("ludo-tile-filler");
    getChildren().add(filler);
  }

  /**
   * Sets the color of the tile.
   *
   * @param color The color to set for the tile.
   */
  public void setColor(Color color) {
    setStyle(String.format("-fx-background-color: %s;", StyleUtil.toRgbString(color)));
  }
}
