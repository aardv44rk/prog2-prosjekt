package ntnu.idi.idatt.games.snakesandladders;

import java.util.List;
import ntnu.idi.idatt.models.Board;

/**
 * Factory class to create a Snakes and Ladders board.
 */
public class SnakesAndLaddersBoardFactory {

  public static Board createStandardBoard() {
    return new SnakesAndLaddersBoard();
  }

  public static List<Board> getBoards() {
    return List.of(createStandardBoard());
  }
}
