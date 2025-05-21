package ntnu.idi.idatt.components;

import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import ntnu.idi.idatt.utility.StyleUtil;

/**
 * Component for a Ludo piece.
 */
public class UILudoPiece extends Button {

  private final Color color;

  /**
   * Constructor for the UILudoPiece class.
   *
   * @param color The color of the piece.
   */
  public UILudoPiece(Color color) {
    this.color = color;
    getStyleClass().add("snl-piece");
    setStyle("-fx-background-color: " + StyleUtil.toRgbString(color) + ";");
  }

  public Color getColor() {
    return color;
  }
}
