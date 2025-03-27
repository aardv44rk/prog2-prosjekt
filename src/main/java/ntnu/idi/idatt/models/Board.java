package ntnu.idi.idatt.models;

import java.util.HashMap;
import java.util.Map;

public class Board {
  private Map<Integer, Tile> tiles;

  public Board() {
    this.tiles = new HashMap<>();
  }

  public void addTile(Tile tile) {
    tiles.put(tile.getTileId(), tile);
  }

  public Tile getTile(int position) {
    return tiles.get(position);
  }
}
