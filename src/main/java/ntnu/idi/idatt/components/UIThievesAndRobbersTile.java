package ntnu.idi.idatt.components;

import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import ntnu.idi.idatt.utility.StyleUtil;

public class UIThievesAndRobbersTile extends BorderPane {

  public UIThievesAndRobbersTile(String number) {
    getStyleClass().add("tar-tile");

    Label numberLabel = new Label();
    if (!number.isEmpty()) {
      numberLabel.setText("+$" + number);
    }
    numberLabel.getStyleClass().add("tar-tile-number");

    setBottom(numberLabel);
  }

  public void setColor(Color color) {
    setStyle(String.format("-fx-background-color: %s;", StyleUtil.toRgbString(color)));
  }
}