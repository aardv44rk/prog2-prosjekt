package ntnu.idi.idatt.games.snakesandladders;

import java.util.List;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.util.Pair;
import ntnu.idi.idatt.components.GamePlayerList;
import ntnu.idi.idatt.components.TextButton;
import ntnu.idi.idatt.components.UIDie;
import ntnu.idi.idatt.components.UISnakesAndLaddersBoard;
import ntnu.idi.idatt.components.UISnakesAndLaddersPiece;

public class SnakesAndLaddersView extends BorderPane {

  private final GamePlayerList playerList;
  private final UIDie leftDie;
  private final UIDie rightDie;
  private final TextButton rollButton;

  public SnakesAndLaddersView() {
    getStyleClass().add("snakes-and-ladders-view");

    BorderPane left = new BorderPane();
    left.getStyleClass().add("snakes-and-ladders-left");

    playerList = new GamePlayerList();

    this.leftDie = new UIDie(6);
    this.rightDie = new UIDie(6);

    BorderPane dice = new BorderPane();
    dice.setLeft(leftDie);
    dice.setRight(rightDie);

    rollButton = new TextButton("Roll");

    VBox diceBox = new VBox();
    diceBox.getStyleClass().add("snakes-and-ladders-dice");
    diceBox.getChildren().addAll(dice, rollButton);

    left.setTop(playerList);
    left.setBottom(diceBox);

    VBox center = new VBox();
    center.getStyleClass().add("snakes-and-ladders-center");

    setLeft(left);
    setCenter(center);
  }

  public void setPlayerList(List<Pair<String, UISnakesAndLaddersPiece>> players) {
    playerList.setPieces(players);
  }

  public void shufflePlayerList() {
    playerList.shuffle();
  }

  public void setBoard(UISnakesAndLaddersBoard board) {
    BorderPane borderPane = new BorderPane(board);
    borderPane.getStyleClass().add("snakes-and-ladders-board");
    setCenter(borderPane);
  }

  public void setDiceEyes(int leftEyes, int rightEyes) {
    leftDie.setEyes(leftEyes);
    rightDie.setEyes(rightEyes);
  }

  public void rollSetOnClick(Runnable runnable) {
    rollButton.setOnAction(e -> runnable.run());
  }
}
