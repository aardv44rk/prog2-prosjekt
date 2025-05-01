package ntnu.idi.idatt.games.snakesandladders;

import ntnu.idi.idatt.models.Board;
import ntnu.idi.idatt.models.Tile;

import java.util.HashMap;

/**
 * Snakes and Ladders board implementation.
 */
public class SnakesAndLaddersBoard extends Board {

  public SnakesAndLaddersBoard() {
    initializeBoard();
  }

  @Override
  protected void initializeBoard() {
    tiles = new HashMap<>();
    int size = 100;

    for (int i = 0; i < size; i++) {
      tiles.put(i, new Tile(i));
    }

    tiles.get(3).setTileAction(new LadderAction(22));
    tiles.get(8).setTileAction(new LadderAction(30));
  }
}
