package ntnu.idi.idatt.games.snakesandladders;

import ntnu.idi.idatt.core.*;

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
      piece.setCurrentTile(destination);
      System.out.println(
          piece.getOwner().getName() + " climbs ladder to tile " + destinationTileId);
    }
  }

  public int getDestinationTileId() {
    return destinationTileId;
  }
}

