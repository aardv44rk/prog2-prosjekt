package ntnu.idi.idatt.games.snakesandladders;

import ntnu.idi.idatt.models.Board;
import ntnu.idi.idatt.models.Tile;

import java.util.HashMap;

/**
 * Snakes and Ladders board implementation.
 */
public class SnakesAndLaddersBoard extends Board {

  private final int rows;
  private final int columns;

  public SnakesAndLaddersBoard(int rows, int columns) {
    this.rows = rows;
    this.columns = columns;

    initializeBoard();
  }

  @Override
  protected void initializeBoard() {
    tiles = new HashMap<>();
    for (int i = 0; i < rows * columns; i++) {
      tiles.put(i, new Tile(i));
    }
  }

  public int getRows() {
    return rows;
  }

  public int getColumns() {
    return columns;
  }
}
