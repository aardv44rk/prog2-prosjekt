package ntnu.idi.idatt.components;

import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import ntnu.idi.idatt.utility.StyleUtil;

public class UISnakesAndLaddersPiece extends VBox {

  private final Color color;

  public UISnakesAndLaddersPiece(Color color) {
    this.color = color;
    getStyleClass().add("snl-piece");
    setStyle("-fx-background-color: " + StyleUtil.toRgbString(color) + ";");
  }

  public Color getColor() {
    return color;
  }
}
