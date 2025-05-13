package ntnu.idi.idatt.components;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;

public class UISnakesAndLaddersTile extends BorderPane {

  private final StackPane pieces;

  public UISnakesAndLaddersTile(int number) {
    getStyleClass().add("snl-tile");

    this.pieces = new StackPane();

    Label numberLabel = new Label("" + number);
    numberLabel.getStyleClass().add("snl-tile-number");

    setCenter(pieces);
    setBottom(numberLabel);
  }

  public void addPiece(UISnakesAndLaddersPiece piece) {
    pieces.getChildren().add(piece);
    StackPane.setMargin(piece, new Insets(0, 0, (pieces.getChildren().size() - 4) * 10, 0));
  }

  public void clearPieces() {
    pieces.getChildren().clear();
  }
}
