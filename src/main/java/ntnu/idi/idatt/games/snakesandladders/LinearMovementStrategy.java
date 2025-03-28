package ntnu.idi.idatt.games.snakesandladders;

import ntnu.idi.idatt.core.*;

public class LinearMovementStrategy implements MovementStrategy {

  @Override
  public void move(Piece piece, int steps, Board board) {
    int currentPos = piece.getCurrentTile().getTileId();
    int targetPos = Math.min(currentPos + steps, board.getTiles().size() - 1);
    Tile targetTile = board.getTile(targetPos);
    piece.setCurrentTile(targetTile);
    System.out.println(piece.getOwner().getName() + " moved to tile " + targetPos);
    targetTile.land(piece, board);
  }
}
