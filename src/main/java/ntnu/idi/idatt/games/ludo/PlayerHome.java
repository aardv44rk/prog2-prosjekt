package ntnu.idi.idatt.games.ludo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ntnu.idi.idatt.models.Piece;
import ntnu.idi.idatt.models.Player;
import ntnu.idi.idatt.models.Tile;

public class PlayerHome {
  private final Player owner;
  private final int color; // 0 = red, 1 = green, 2 = yellow, 3 = blue
  private final Map<Integer, Tile> homeTiles; // Pos -> tile (1-4)
  private final int startTileId;

  public PlayerHome(Player owner, int color, Map<Integer, Tile> homeTiles, int startTileId) {
    this.owner = owner;
    this.color = color;
    this.startTileId = startTileId;
    this.homeTiles = new HashMap<>();

    for (int i = 1; i <= 4; i++) {
      // using custom made ids for home tiles to avoid potential conflicts
      int tileId = 2000 * (color + 1) + i;
      homeTiles.put(i, new Tile(tileId));
    }
  }

  public Tile getTile(int pos) {
    return homeTiles.get(pos);
  }

  public List<Piece> getHomePieces() {
    return owner.getPieces().stream()
        .filter(piece -> homeTiles.containsValue(piece.getCurrentTile()))
        .toList();
  }

  public int getPieceCount() {
    return getHomePieces().size();
  }

  public int getColor() {
    return color;
  }

  public Player getOwner() {
    return owner;
  }

  public int getStartTileId() {
    return startTileId;
  }

  public Map<Integer, Tile> getHomeTiles() {
    return homeTiles;
  }
}
