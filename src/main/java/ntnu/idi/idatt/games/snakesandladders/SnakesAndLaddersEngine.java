package ntnu.idi.idatt.games.snakesandladders;

import java.util.List;
import ntnu.idi.idatt.models.Board;
import ntnu.idi.idatt.models.Dice;
import ntnu.idi.idatt.models.Piece;
import ntnu.idi.idatt.models.Player;
import ntnu.idi.idatt.models.SimpleGameEngine;

/**
 * Game engine for Snakes and Ladders. Handles game flow and win condition.
 */
public class SnakesAndLaddersEngine extends SimpleGameEngine {

  /**
   * Constructor for SnakesAndLaddersEngine.
   *
   * @param players            List of players in the game.
   * @param board              The game board.
   * @param currentPlayerIndex Index of the current player.
   * @param dice               The dice used in the game.
   */
  public SnakesAndLaddersEngine(List<Player> players, Board board, int currentPlayerIndex,
      Dice dice) {
    super(players, board, currentPlayerIndex, dice);
  }

  /**
   * Initializes the pieces for each player on the board.
   */
  @Override
  public void initPieces() {
    for (Player p : players) {
      p.getPieces().clear();
      p.getPieces().add(new Piece(board.getTile(0), p, new LinearMovementStrategy()));
    }
  }

  /**
   * Checks the win condition for the game. If a player reaches the last tile, they win.
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
}
