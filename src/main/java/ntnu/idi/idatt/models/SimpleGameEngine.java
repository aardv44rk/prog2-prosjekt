package ntnu.idi.idatt.models;

import java.util.List;
import ntnu.idi.idatt.exceptions.InvalidInputException;
import ntnu.idi.idatt.utility.ArgumentValidator;

/**
 * SimpleGameEngine is a basic implementation of a game engine that handles the game logic.
 * 
 */
public class SimpleGameEngine extends GameEngine {

  protected final Dice dice;

  /**
   * Constructor for SimpleGameEngine.
   *
   * @param players          List of players in the game
   * @param board            The game board
   * @param currentPlayerIndex Index of the current player
   * @param dice             The dice used in the game
   */
  public SimpleGameEngine(List<Player> players, Board board, int currentPlayerIndex, Dice dice) {
    super(players, board, currentPlayerIndex);
    if (!ArgumentValidator.isValidObject(dice)) {
      throw new InvalidInputException("Invalid dice");
    }
    this.dice = dice;
  }

  public void handleTurn() {
    Player player = getCurrentPlayer();
    Piece piece = player.getPieces().getFirst();

    dice.roll();
    int steps = dice.getValue();

    piece.move(steps, board);

    if (checkWinCondition() != null) {
      endGame();
    }
  }

  public void initPieces() {

  }

  @Override
  public Player checkWinCondition() {
    return null;
  }

  public Dice getDice() {
    return dice;
  }
}
