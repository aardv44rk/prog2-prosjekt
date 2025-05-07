package ntnu.idi.idatt.components;

import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class UISnakesAndLaddersTile extends VBox {

  public UISnakesAndLaddersTile(int number) {
    getStyleClass().add("snl-tile");
    Label numberLabel = new Label("" + number);
    numberLabel.getStyleClass().add("snl-tile-number");
    getChildren().add(numberLabel);
  }
}
