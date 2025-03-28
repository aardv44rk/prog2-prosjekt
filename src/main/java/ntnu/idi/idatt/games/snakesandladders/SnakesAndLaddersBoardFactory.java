package ntnu.idi.idatt.games.snakesandladders;

import ntnu.idi.idatt.core.Board;

/**
 * Factory class to create a Snakes and Ladders board.
 */
public class SnakesAndLaddersBoardFactory {

  public static Board createStandardBoard() {
    return new SnakesAndLaddersBoard();
  }
}
