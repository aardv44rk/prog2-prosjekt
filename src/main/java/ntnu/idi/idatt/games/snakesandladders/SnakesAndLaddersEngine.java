package ntnu.idi.idatt.games.snakesandladders;

import java.util.List;
import ntnu.idi.idatt.models.Board;
import ntnu.idi.idatt.models.Dice;
import ntnu.idi.idatt.models.GameEngine;
import ntnu.idi.idatt.models.Piece;
import ntnu.idi.idatt.models.Player;
import ntnu.idi.idatt.utility.ArgumentValidator;

/**
 * Game engine for Snakes and Ladders. Handles game flow and win condition.
 */
public class SnakesAndLaddersEngine extends GameEngine {

  private final Dice dice;

  /**
   * Constructor for SnakesAndLaddersEngine.
   *
   * @param players           List of players in the game.
   * @param board             The game board.
   * @param currentPlayerIndex Index of the current player.
   * @param dice              The dice used in the game.
   */
  public SnakesAndLaddersEngine(List<Player> players, Board board, int currentPlayerIndex, Dice dice) {
    super(players, board, currentPlayerIndex);
    if (!ArgumentValidator.isValidObject(dice)) {
      throw new IllegalArgumentException("Invalid dice");
    }
    this.dice = dice;
  }

  /**
   * Initializes the game pieces for each player.
   */
  public void initPieces() {
    for (Player p : players) {
      p.getPieces().clear();
      p.getPieces().add(new Piece(board.getTile(0), p, new LinearMovementStrategy()));
    }
  }

  /**
   * Handles the turn for the current player.
   */
  public void handleTurn() {
    Player player = getCurrentPlayer();
    Piece piece = player.getPieces().getFirst();

    dice.roll();
    int steps = dice.getValue();

    piece.move(steps, board);

    if (checkWinCondition() != null) {
      endGame();
    } else {
      nextPlayer();
    }
  }

  /**
   * Moves the piece to the next tile based on the rolled dice value.
   *
   * @param steps The number of steps to move.
   */
  @Override
  public Player checkWinCondition() {
    for (Player player : players) {
      Piece piece = player.getPieces().getFirst();
      if (piece.getCurrentTile().getTileId() == board.getTiles().size() - 1) {
        return player;
      }
    }
    return null;
  }

  public Dice getDice() {
    return dice;
  }
}
