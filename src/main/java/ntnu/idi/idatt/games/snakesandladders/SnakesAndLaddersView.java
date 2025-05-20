package ntnu.idi.idatt.games.snakesandladders;

import java.util.LinkedHashMap;
import javafx.scene.Node;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import ntnu.idi.idatt.components.GamePlayerList;
import ntnu.idi.idatt.components.TextButton;
import ntnu.idi.idatt.components.UIDie;
import ntnu.idi.idatt.utility.ArgumentValidator;

/**
 * View for the Snakes and Ladders game.
 */
public class SnakesAndLaddersView extends BorderPane {

  private final GamePlayerList playerList;
  private final UIDie leftDie;
  private final UIDie rightDie;
  private final TextButton rollButton;

  /**
   * Constructor for SnakesAndLaddersView.
   * Initializes the view components and layout.
   */
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
    borderPane.getStyleClass().add("snakes-and-ladders-board-container");
    setCenter(borderPane);
  }

  /**
   * Sets the dice eyes for the left and right dice.
   * 
   * @param leftEyes  the number of eyes of left die
   * @param rightEyes the number of eyes of right die
   * @throws IllegalArgumentException if the number of eyes are invalid
   */
  public void setDiceEyes(int leftEyes, int rightEyes) {
    if (!ArgumentValidator.isPositiveInteger(leftEyes) || !ArgumentValidator.isPositiveInteger(rightEyes)) {
      throw new IllegalArgumentException("Invalid dice eyes");
    }
    leftDie.setEyes(leftEyes);
    rightDie.setEyes(rightEyes);
  }

  public void rollSetOnClick(Runnable runnable) {
    rollButton.setOnAction(e -> runnable.run());
  }
}
