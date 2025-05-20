package ntnu.idi.idatt.games.snakesandladders;

import java.util.List;
import ntnu.idi.idatt.models.Board;
import ntnu.idi.idatt.models.Tile;
import ntnu.idi.idatt.utility.ArgumentValidator;

import java.util.HashMap;

/**
 * Snakes and Ladders board implementation.
 */
public class SnakesAndLaddersBoard extends Board {

  private final int rows;
  private final int columns;
  private final List<SnakesAndLaddersLadder> ladders;

  /**
   * Constructor for SnakesAndLaddersBoard.
   *
   * @param rows     Number of rows on the board.
   * @param columns  Number of columns on the board.
   * @param ladders  List of ladders on the board.
   */
  public SnakesAndLaddersBoard(int rows, int columns, List<SnakesAndLaddersLadder> ladders) {
    if (!isValidBoard(rows, columns, ladders)) {
      throw new IllegalArgumentException("Invalid board parameters");
    }
    this.rows = rows;
    this.columns = columns;
    this.ladders = ladders;

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

  public List<SnakesAndLaddersLadder> getLadders() {
    return ladders;
  }

  @Override
  public String toString() {
    StringBuilder s = new StringBuilder();
    for (int i = 0; i < rows; i++) {
      StringBuilder row = new StringBuilder();
      for (int j = 0; j < columns; j++) {
        row.append(tiles.get(i * columns + j)).append(" ");
      }
      s.append(row).append("\n");
    }
    return s.toString();
  }

  /**
   * Validates a Snakes and Ladders board.
   * 
   * @param rows     Number of rows.
   * @param columns  Number of columns.
   * @param ladders  List of ladders.
   * @return true if the board is valid, false otherwise.
   */
  public boolean isValidBoard(int rows, int columns, List<SnakesAndLaddersLadder> ladders) {
    return ArgumentValidator.isPositiveInteger(rows) && ArgumentValidator.isPositiveInteger(columns) 
        && ArgumentValidator.isValidList(ladders);
  }
}
