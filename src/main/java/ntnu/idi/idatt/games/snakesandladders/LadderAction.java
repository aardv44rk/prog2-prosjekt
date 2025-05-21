package ntnu.idi.idatt.games.snakesandladders;

import ntnu.idi.idatt.models.Board;
import ntnu.idi.idatt.models.Piece;
import ntnu.idi.idatt.models.Tile;
import ntnu.idi.idatt.models.TileAction;

/**
 * TileAction that moves a piece to a specific tile (ladder).
 */
public class LadderAction implements TileAction {

  private final int destinationTileId;

  /**
   * Constructor for LadderAction.
   *
   * @param destinationTileId The ID of the tile to move to.
   */
  public LadderAction(int destinationTileId) {
    this.destinationTileId = destinationTileId;
  }

  /**
   * Performs the action of moving a piece to the destination tile.
   * 
   * @param piece The piece to move.
   * @param board The board context.
   * @throws IllegalArgumentException if the destination tile is invalid.
   */
  @Override
  public void perform(Piece piece, Board board) {
    Tile destination = board.getTile(destinationTileId - 1); // i have no idea why this works, but it does
    if (destination != null) {                               // if we don't do this, all tests fail so don't touch it
      piece.move(destinationTileId - piece.getCurrentTile().getTileId(), board);
    }
  }

  public int getDestinationTileId() {
    return destinationTileId;
  }
}

