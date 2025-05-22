package ntnu.idi.idatt.games.thievesandrobbers;

import ntnu.idi.idatt.models.Board;
import ntnu.idi.idatt.models.MovementStrategy;
import ntnu.idi.idatt.models.Piece;
import ntnu.idi.idatt.models.Tile;

/**
 * Circular movement strategy for the Thieves and Robbers game.
 * This strategy allows pieces to move in a circular manner on the board.
 */
public class CircularMovementStrategy implements MovementStrategy {

  /**
   * Moves the piece a specified number of steps on the board.
   * If the piece moves past the last tile, it wraps around to the beginning.
   *
   * @param piece  The piece to move.
   * @param steps  The number of steps to move.
   * @param board  The game board.
   */
  @Override
  public void move(Piece piece, int steps, Board board) {
    int currentPos = piece.getCurrentTile().getTileId();
    int targetPos = (currentPos + steps) % board.getTiles().size();
    Tile targetTile = board.getTile(targetPos);
    piece.setCurrentTile(targetTile);
    if (targetPos < currentPos) {
      board.getTile(0).getTileAction().perform(piece, board);
    }
    targetTile.land(piece, board);
  }
}
