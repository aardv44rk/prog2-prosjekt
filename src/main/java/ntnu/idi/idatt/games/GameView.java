package ntnu.idi.idatt.games;

import java.util.LinkedHashMap;
import javafx.scene.Node;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import ntnu.idi.idatt.components.GamePlayerList;
import ntnu.idi.idatt.components.TextButton;
import ntnu.idi.idatt.components.UIDie;


public class GameView extends BorderPane {

  private final GamePlayerList playerList;
  private final UIDie leftDie;
  private final UIDie rightDie;
  private final TextButton rollButton;

  public GameView() {
    getStyleClass().add("game-view");

    BorderPane left = new BorderPane();
    left.getStyleClass().add("game-left");

    playerList = new GamePlayerList();

    this.leftDie = new UIDie(6);
    this.rightDie = new UIDie(6);

    BorderPane dice = new BorderPane();
    dice.setLeft(leftDie);
    dice.setRight(rightDie);

    rollButton = new TextButton("Roll");

    VBox diceBox = new VBox();
    diceBox.getStyleClass().add("game-dice");
    diceBox.getChildren().addAll(dice, rollButton);

    left.setTop(playerList);
    left.setBottom(diceBox);

    setLeft(left);
  }

  public void setPlayerList(LinkedHashMap<String, Node> players) {
    playerList.setPieces(players);
  }

  public void shufflePlayerList() {
    playerList.shuffle();
  }

  public void setBoard(Node board) {
    BorderPane borderPane = new BorderPane(board);
    borderPane.getStyleClass().add("game-board-container");
    setCenter(borderPane);
  }

  public void setStats(Node node) {
    setRight(node);
  }

  public void setDiceEyes(int leftEyes, int rightEyes) {
    leftDie.setEyes(leftEyes);
    rightDie.setEyes(rightEyes);
  }

  public void rollSetOnClick(Runnable runnable) {
    rollButton.setOnAction(e -> runnable.run());
  }
}
