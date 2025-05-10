package ntnu.idi.idatt.games.snakesandladders;

import ntnu.idi.idatt.models.Board;
import ntnu.idi.idatt.models.MovementStrategy;
import ntnu.idi.idatt.models.Piece;
import ntnu.idi.idatt.models.Tile;

public class LinearMovementStrategy implements MovementStrategy {

  @Override
  public void move(Piece piece, int steps, Board board) {
    int currentPos = piece.getCurrentTile().getTileId();
    int targetPos = Math.min(currentPos + steps, board.getTiles().size() - 1);
    Tile targetTile = board.getTile(targetPos);
    piece.setCurrentTile(targetTile);
    targetTile.land(piece, board);
  }
}
