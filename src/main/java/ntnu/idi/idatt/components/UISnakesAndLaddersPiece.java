package ntnu.idi.idatt.components;

import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import ntnu.idi.idatt.utility.StyleUtil;

/**
 * Component for a Snakes and Ladders piece.
 */
public class UISnakesAndLaddersPiece extends VBox {

  private final Color color;

  /**
   * Constructor for the UISnakesAndLaddersPiece class.
   *
   * @param color The color of the piece.
   */
  public UISnakesAndLaddersPiece(Color color) {
    this.color = color;
    getStyleClass().add("snl-piece");
    setStyle("-fx-background-color: " + StyleUtil.toRgbString(color) + ";");
    Region filler = new Region();
    filler.getStyleClass().add("snl-piece-filler");
    getChildren().add(filler);
  }

  public Color getColor() {
    return color;
  }
}
