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

  public LadderAction(int destinationTileId) {
    this.destinationTileId = destinationTileId;
  }

  @Override
  public void perform(Piece piece, Board board) {
    Tile destination = board.getTile(destinationTileId);
    if (destination != null) {
      piece.move(destinationTileId - piece.getCurrentTile().getTileId(), board);
    }
  }

  public int getDestinationTileId() {
    return destinationTileId;
  }
}

