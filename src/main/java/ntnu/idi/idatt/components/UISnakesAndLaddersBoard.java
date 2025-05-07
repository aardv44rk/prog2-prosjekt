package ntnu.idi.idatt.components;

import java.util.ArrayList;
import java.util.List;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class UISnakesAndLaddersBoard extends VBox {

  public UISnakesAndLaddersBoard(int rows, int columns, List<UISnakesAndLaddersLadder> ladders) {
    getStyleClass().add("snl-board");
    List<HBox> rowContainer = new ArrayList<>();

    for (int i = rows; i >= 0; i--) {
      HBox row = new HBox();
      for (int j = 1; j <= columns; j++) {
        row.getChildren().add(new UISnakesAndLaddersTile(i * rows + i + j));
      }
      rowContainer.add(row);
    }

    getChildren().addAll(rowContainer);
  }
}
