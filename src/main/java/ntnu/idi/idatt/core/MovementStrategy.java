package ntnu.idi.idatt.core;

/**
 * Defines a strategy for moving a piece on the board.
 */
public interface MovementStrategy {

  /**
   * Moves the piece a given number of steps according to the strategy.
   *
   * @param piece The piece to move.
   * @param steps Number of steps to move.
   * @param board The board context.
   */
  void move(Piece piece, int steps, Board board);
}
