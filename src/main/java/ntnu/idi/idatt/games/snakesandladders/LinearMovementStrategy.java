package ntnu.idi.idatt.games.snakesandladders;

import ntnu.idi.idatt.models.Board;
import ntnu.idi.idatt.models.MovementStrategy;
import ntnu.idi.idatt.models.Piece;
import ntnu.idi.idatt.models.Tile;

/**
 * Implements a linear movement strategy for a piece in Snakes and Ladders.
 */
public class LinearMovementStrategy implements MovementStrategy {

  /**
   * Moves the piece forward by a specified number of steps on the board.
   *
   * @param piece The piece to move.
   * @param steps The number of steps to move forward.
   * @param board The board context.
   */
  @Override
  public void move(Piece piece, int steps, Board board) {
    int currentPos = piece.getCurrentTile().getTileId();
    int targetPos = Math.min(currentPos + steps, board.getTiles().size() - 1);
    Tile targetTile = board.getTile(targetPos);
    piece.setCurrentTile(targetTile);
    targetTile.land(piece, board);
  }
}
