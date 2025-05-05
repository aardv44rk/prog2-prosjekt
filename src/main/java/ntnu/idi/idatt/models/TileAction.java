package ntnu.idi.idatt.models;

/**
 * Represents an action performed when a piece lands on a tile.
 */
public interface TileAction {

  /**
   * Executes the action for the given piece.
   *
   * @param piece The piece landing on the tile.
   * @param board The board context.
   */
  void perform(Piece piece, Board board);
}
