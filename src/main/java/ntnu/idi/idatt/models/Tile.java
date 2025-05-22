package ntnu.idi.idatt.models;

import ntnu.idi.idatt.exceptions.InvalidInputException;
import ntnu.idi.idatt.utility.ArgumentValidator;

/**
 * Represents a tile on the board. A tile may have an optional action performed when a piece lands
 * on it.
 */
public class Tile {

  private final int tileId;
  private TileAction tileAction;

  /**
   * Constructor for the Tile class.
   *
   * @param tileId The ID of the tile.
   * @throws InvalidInputException if the tile ID is invalid.
   */
  public Tile(int tileId) {
    if (!isValidTile(tileId)) {
      throw new InvalidInputException("Invalid arguments");
    }
    this.tileId = tileId;
  }

  /**
   * Called when a piece lands on this tile.
   *
   * @param piece The piece landing on the tile.
   * @param board The board context.
   */
  public void land(Piece piece, Board board) {
    if (tileAction != null) {
      tileAction.perform(piece, board);
    }
  }

  /**
   * Sets an action to be performed when a piece lands on this tile.
   *
   * @param action The TileAction to assign.
   */
  public void setTileAction(TileAction action) {
    this.tileAction = action;
  }

  public int getTileId() {
    return tileId;
  }

  public TileAction getTileAction() {
    return tileAction;
  }

  @Override
  public String toString() {
    return "" + tileId;
  }

  /**
   * Checks if the given tile ID is valid.
   *
   * @param tileId The tile ID to check.
   * @return true if the tile ID is valid, false otherwise.
   */
  public boolean isValidTile(int tileId) {
    return ArgumentValidator.isValidIndex(tileId);
  }
}
