package ntnu.idi.idatt.components;

import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import ntnu.idi.idatt.utility.StyleUtil;

/**
 * Component for a game piece.
 */
public class UIPiece extends VBox {

  private final Color color;

  /**
   * Constructor for the UIPiece class.
   *
   * @param color The color of the piece.
   */
  public UIPiece(Color color) {
    this.color = color;
    getStyleClass().add("piece");
    setStyle("-fx-background-color: " + StyleUtil.toRgbString(color) + ";");
    Region filler = new Region();
    filler.getStyleClass().add("piece-filler");
    getChildren().add(filler);
  }

  public Color getColor() {
    return color;
  }
}