package ntnu.idi.idatt.models;

import java.util.Map;

/**
 * Represents a generic board in a board game. Responsible for managing tiles and providing access
 * to them.
 */
public abstract class Board {

  protected Map<Integer, Tile> tiles;

  /**
   * Retrieves the tile with the specified ID.
   *
   * @param tileId The ID of the tile.
   * @return The tile with the given ID, or null if not found.
   */
  public Tile getTile(int tileId) {
    return tiles.get(tileId);
  }
  
  /**
   * Returns all tiles on the board.
   *
   * @return Map of tile IDs to Tile objects.
   */
  public Map<Integer, Tile> getTiles() {
    return tiles;
  }

  /**
   * Initializes the board structure and tiles. Concrete implementations will define board layout.
   */
  protected abstract void initializeBoard();
}
