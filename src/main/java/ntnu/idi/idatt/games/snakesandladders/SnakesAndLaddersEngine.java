package ntnu.idi.idatt.games.snakesandladders;

import java.util.List;
import ntnu.idi.idatt.models.Board;
import ntnu.idi.idatt.models.Dice;
import ntnu.idi.idatt.models.GameEngine;
import ntnu.idi.idatt.models.Piece;
import ntnu.idi.idatt.models.Player;

/**
 * Game engine for Snakes and Ladders. Handles game flow and win condition.
 */
public class SnakesAndLaddersEngine extends GameEngine {

  private final Dice dice;

  public SnakesAndLaddersEngine(List<Player> players, Board board, int currentPlayerIndex,
      Dice dice) {
    super(players, board, currentPlayerIndex);

    this.dice = dice;
  }

  public void initPieces() {
    for (Player p : players) {
      p.getPieces().clear();
      p.getPieces().add(new Piece(board.getTile(0), p, new LinearMovementStrategy()));
    }
  }

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
