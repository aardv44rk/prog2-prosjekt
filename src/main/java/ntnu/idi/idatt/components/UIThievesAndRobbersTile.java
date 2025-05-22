package ntnu.idi.idatt.components;

import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import ntnu.idi.idatt.utility.StyleUtil;

/**
 * Component for displaying a tile in the Thieves and Robbers game.
 */
public class UIThievesAndRobbersTile extends BorderPane {

  /**
   * Constructor for the UIThievesAndRobbersTile component.
   *
   * @param number The number to be displayed on the tile.
   */
  public UIThievesAndRobbersTile(String number) {
    getStyleClass().add("tar-tile");

    Label numberLabel = new Label();
    if (!number.isEmpty()) {
      numberLabel.setText(number + "$");
    }
    numberLabel.getStyleClass().add("tar-tile-number");

    setBottom(numberLabel);
  }

  /**
   * Sets the color of the tile.
   *
   * @param color The color to be set for the tile.
   */
  public void setColor(Color color) {
    setStyle(String.format("-fx-background-color: %s;", StyleUtil.toRgbString(color)));
  }
}