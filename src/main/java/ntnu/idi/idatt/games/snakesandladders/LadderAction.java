package ntnu.idi.idatt.games.snakesandladders;

import ntnu.idi.idatt.exceptions.InvalidInputException;
import ntnu.idi.idatt.models.Board;
import ntnu.idi.idatt.models.Piece;
import ntnu.idi.idatt.models.Tile;
import ntnu.idi.idatt.models.TileAction;
import ntnu.idi.idatt.utility.ArgumentValidator;

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
   * @throws InvalidInputException if the destination tile is invalid.
   */
  @Override
  public void perform(Piece piece, Board board) {
    if (!ArgumentValidator.isValidObject(piece) || !ArgumentValidator.isValidObject(board)) {
      throw new InvalidInputException("Invalid piece or board");
    }
    Tile destination = board.getTile(destinationTileId - 1); // -1 to 0 index
    
    if (destination != null) {                              
      piece.setCurrentTile(destination);
    } else {
      throw new InvalidInputException("Invalid destination tile");
    }
  }

  public int getDestinationTileId() {
    return destinationTileId;
  }
}

