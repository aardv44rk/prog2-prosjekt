package ntnu.idi.idatt.games.thievesandrobbers;

import java.util.List;
import ntnu.idi.idatt.models.Board;
import ntnu.idi.idatt.models.Dice;
import ntnu.idi.idatt.models.Player;
import ntnu.idi.idatt.models.SimpleGameEngine;

/**
 * Game engine for the Thieves and Robbers game.
 */
public class ThievesAndRobbersEngine extends SimpleGameEngine {

  /**
   * Constructor for ThievesAndRobbersEngine.
   *
   * @param players            List of players in the game.
   * @param board              The game board.
   * @param currentPlayerIndex Index of the current player.
   * @param dice               The dice used in the game.
   */
  public ThievesAndRobbersEngine(List<Player> players, Board board,
      int currentPlayerIndex, Dice dice) {
    super(players, board, currentPlayerIndex, dice);

  }

  /**
   * Initializes the pieces for each player on the board.
   */
  @Override
  public void initPieces() {
    for (Player p : players) {
      p.getPieces().clear();
      p.getPieces()
          .add(new ThievesAndRobbersPiece(board.getTile(0), p, new CircularMovementStrategy(), 0));
    }
  }

  /**
   * Checks the win condition for the game. If a player has 1000 or more money, they win.
   *
   * @return The winning player, or null if no player has won.
   */
  @Override
  public Player checkWinCondition() {
    for (Player p : players) {
      if (p.getPieces().stream()
          .mapToInt(piece -> ((ThievesAndRobbersPiece) piece).getMoney())
          .sum() >= 1000) {
        return p;
      }
    }
    return null;
  }
}
