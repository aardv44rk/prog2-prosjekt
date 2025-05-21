package ntnu.idi.idatt.games.thievesAndRobbers;

import ntnu.idi.idatt.models.Board;
import ntnu.idi.idatt.models.MovementStrategy;
import ntnu.idi.idatt.models.Piece;
import ntnu.idi.idatt.models.Tile;

public class CircularMovementStrategy implements MovementStrategy {

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
