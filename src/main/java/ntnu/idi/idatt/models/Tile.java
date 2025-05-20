package ntnu.idi.idatt.models;

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
   * @throws IllegalArgumentException if the tile ID is invalid.
   */
  public Tile(int tileId) {
    if (!isValidTile(tileId)) {
      throw new IllegalArgumentException("Invalid arguments");
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

  public boolean isValidTile(int tileId) {
    return tileId >= 0;
  }
}
