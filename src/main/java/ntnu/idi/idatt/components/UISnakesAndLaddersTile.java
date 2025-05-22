package ntnu.idi.idatt.components;

import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import ntnu.idi.idatt.utility.StyleUtil;

/**
 * Component for a Snakes and Ladders tile.
 */
public class UISnakesAndLaddersTile extends BorderPane {

  /**
   * Constructor for the UISnakesAndLaddersTile class.
   *
   * @param number The number of the tile.
   */
  public UISnakesAndLaddersTile(int number) {
    getStyleClass().add("snl-tile");

    Label numberLabel = new Label("" + number);
    numberLabel.getStyleClass().add("snl-tile-number");

    setBottom(numberLabel);
  }

  public void setColor(Color color) {
    setStyle(String.format("-fx-background-color: %s;", StyleUtil.toRgbString(color)));
  }
}
