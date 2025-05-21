package ntnu.idi.idatt.games;

import java.util.LinkedHashMap;
import javafx.scene.Node;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import ntnu.idi.idatt.components.GamePlayerList;
import ntnu.idi.idatt.components.TextButton;
import ntnu.idi.idatt.components.UIDie;
import ntnu.idi.idatt.utility.ArgumentValidator;

/**
 * View for a simple game with 2 dice.
 */
public class GameView extends BorderPane {

  private final GamePlayerList playerList;
  private final UIDie leftDie;
  private final UIDie rightDie;
  private final TextButton rollButton;

  /**
   * Constructor for GameView. Initializes the view components and layout
   */
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

  /**
   * Sets the player list in the view.
   *
   * @param players the players to set in the player list.
   * @throws IllegalArgumentException if the players are invalid.
   */
  public void setPlayerList(LinkedHashMap<String, Node> players) {
    if (!ArgumentValidator.isValidObject(players)) {
      throw new IllegalArgumentException("Invalid players");
    }
    playerList.setPieces(players);
  }

  /**
   * Shuffles the player list.
   */
  public void shufflePlayerList() {
    playerList.shuffle();
  }

  /**
   * Sets the board in the view.
   *
   * @param board the board to set in the view.
   * @throws IllegalArgumentException if the board is invalid.
   */
  public void setBoard(Node board) {
    if (!ArgumentValidator.isValidObject(board)) {
      throw new IllegalArgumentException("Invalid board");
    }
    BorderPane borderPane = new BorderPane(board);
    borderPane.getStyleClass().add("game-board-container");
    setCenter(borderPane);
  }

  /**
   * Displays game stats on the right side of the game view.
   *
   * @param node the stats to set in the view.
   */
  public void setStats(Node node) {
    setRight(node);
  }

  /**
   * Sets the dice eyes for the left and right dice.
   *
   * @param leftEyes  the number of eyes of left die
   * @param rightEyes the number of eyes of right die
   * @throws IllegalArgumentException if the number of eyes are invalid
   */
  public void setDiceEyes(int leftEyes, int rightEyes) {
    leftDie.setEyes(leftEyes);
    rightDie.setEyes(rightEyes);
  }

  /**
   * Sets action for roll button when pressed.
   *
   * @param runnable action to run when roll is pressed
   */
  public void rollSetOnClick(Runnable runnable) {
    rollButton.setOnAction(e -> runnable.run());
  }
}
