package ntnu.idi.idatt.games.ludo;

import ntnu.idi.idatt.exceptions.InvalidInputException;
import ntnu.idi.idatt.models.Board;
import ntnu.idi.idatt.models.MovementStrategy;
import ntnu.idi.idatt.models.Piece;


public class LudoMovementStrategy implements MovementStrategy {

  @Override
  public void move(Piece piece, int steps, Board board) {
    LudoBoard ludoBoard = (LudoBoard) board;
    LudoTile nextTile = ludoBoard.getNextTile(piece, steps);
    if (nextTile == null) {
      throw new InvalidInputException("No tile found.");
    }

    piece.setCurrentTile(nextTile);

    if (nextTile.hasEnemyBlock(piece.getOwner())) {
      piece.setCurrentTile(null);
    } else {
      piece.setCurrentTile(nextTile);
    }
  }
}
