package ntnu.idi.idatt.games.ludo;

import java.util.HashMap;
import java.util.Map;

import ntnu.idi.idatt.models.Piece;
import ntnu.idi.idatt.models.Player;
import ntnu.idi.idatt.models.Tile;

public class PlayerGoal { // should be a subclass of Tile?
 
  private Player owner;
  private final Map<Integer, Tile> goalTiles; // Pos -> tile (1-4)
  private final int color;

  public PlayerGoal(Player owner, int color) {
    this.owner = owner;
    this.color = color;
    this.goalTiles = new HashMap<>();

    initializeGoalTiles();
  }

  private void initializeGoalTiles() {
    for (int i = 1; i <= 4; i++) {
      // using custom made ids for goal tiles to avoid conflict
      int tileId = 1000 + (color * 100) + i;
      goalTiles.put(i, new Tile(tileId));
    }
  }

  public Tile getTile(int pos) {
    return goalTiles.get(pos);
  }

  public int getFirstAvailablePosition() {
    for (int i = 1; i <= 4; i++) {
      Tile tile = goalTiles.get(i);
      boolean occupied = false;

      for (Piece piece : owner.getPieces()) {
        if (piece.getCurrentTile().equals(tile)) {
          occupied = true;
          break;
        }
      }

      if (!occupied) {
        return i; // return the first available position
      }
    }
    return -1; // all positions are occupied
  }

  public boolean isComplete() {
    return getPieceCount() == 4; // all 4 pieces are in the goal
  }



  public int getPieceCount() {
    return owner.getPieces().stream()
        .filter(piece -> goalTiles.containsValue(piece.getCurrentTile()))
        .toList()
        .size();
  }

  public int getColor() {
    return color;
  }

  public Player getOwner() {
    return owner;
  }

  public Map<Integer, Tile> getGoalTiles() {
    return goalTiles;
  }

  public void setOwner(Player owner) {
    this.owner = owner;
  }
}
