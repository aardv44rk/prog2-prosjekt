package ntnu.idi.idatt.games.snakesandladders;

import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import ntnu.idi.idatt.components.UIDie;
import ntnu.idi.idatt.components.UISnakesAndLaddersBoard;

public class SnakesAndLaddersView extends BorderPane {

  private final UIDie leftDie;
  private final UIDie rightDie;
  private VBox right;

  public SnakesAndLaddersView() {
    getStyleClass().add("snakes-and-ladders-view");

    VBox left = new VBox();
    left.getStyleClass().add("snakes-and-ladders-left");

    this.leftDie = new UIDie(6);
    this.rightDie = new UIDie(6);

    HBox dice = new HBox();
    dice.getStyleClass().add("snakes-and-ladders-dice");
    dice.getChildren().addAll(leftDie, rightDie);

    left.getChildren().add(dice);

    right = new VBox();
    right.getStyleClass().add("snakes-and-ladders-right");

    setLeft(left);
    setRight(right);
  }

  public void setBoard(UISnakesAndLaddersBoard board) {
    right.getChildren().clear();
    right.getChildren().add(board);
  }

  public void setDiceEyes(int leftEyes, int rightEyes) {
    leftDie.setEyes(leftEyes);
    rightDie.setEyes(rightEyes);
  }
}
