package ntnu.idi.idatt.games.ludo;

import java.util.HashMap;
import java.util.Map;

import ntnu.idi.idatt.models.Player;
import ntnu.idi.idatt.models.Tile;

public class PlayerGoal { // should be a subclass of Tile?
 
  private final Player owner;
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
}
