package ntnu.idi.idatt.games.snakesandladders;

import java.util.List;
import ntnu.idi.idatt.models.Board;

/**
 * Factory class to create a Snakes and Ladders board.
 */
public class SnakesAndLaddersBoardFactory {

  public static Board createStandardBoard() {
    SnakesAndLaddersBoard board = new SnakesAndLaddersBoard(9, 10);

    board.getTile(80).setTileAction(new LadderAction(50));
    board.getTile(81).setTileAction(new LadderAction(50));
    board.getTile(82).setTileAction(new LadderAction(50));
    board.getTile(83).setTileAction(new LadderAction(50));

    return board;
  }

  public static List<Board> getBoards() {
    return List.of(createStandardBoard(), createStandardBoard(), createStandardBoard());
  }
}
