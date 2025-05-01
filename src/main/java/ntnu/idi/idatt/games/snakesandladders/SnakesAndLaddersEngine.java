package ntnu.idi.idatt.games.snakesandladders;

import java.util.List;
import ntnu.idi.idatt.models.Board;
import ntnu.idi.idatt.models.GameEngine;
import ntnu.idi.idatt.models.Piece;
import ntnu.idi.idatt.models.Player;

/**
 * Game engine for Snakes and Ladders. Handles game flow and win condition.
 */
public class SnakesAndLaddersEngine extends GameEngine {

  public SnakesAndLaddersEngine(List<Player> players, Board board, int currentPlayerIndex) {
    super(players, board, currentPlayerIndex);
  }

  @Override
  public void startGame() {
    System.out.println("Starting Snakes and Ladders...");
    while (!isGameOver()) {
      handleTurn();
    }
  }

  @Override
  public void handleTurn() {
    Player player = getCurrentPlayer();
    Piece piece = player.getPieces().get(0); // Only one piece in SnL

    int steps = rollDice();
    System.out.println(player.getName() + " rolled " + steps);

    piece.move(steps, board);

    if (checkWinCondition() != null) {
      endGame();
      System.out.println(player.getName() + " has won!");
    } else {
      nextPlayer();
    }
  }

  @Override
  public Player checkWinCondition() {
    for (Player player : players) {
      Piece piece = player.getPieces().get(0);
      if (piece.getCurrentTile().getTileId() == board.getTiles().size() - 1) {
        return player;
      }
    }
    return null;
  }

  private int rollDice() {
    return (int) (Math.random() * 6) + 1;
  }
}
