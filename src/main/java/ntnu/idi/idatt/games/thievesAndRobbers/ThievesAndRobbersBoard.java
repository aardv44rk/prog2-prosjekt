package ntnu.idi.idatt.games.thievesAndRobbers;

import java.util.HashMap;
import java.util.Random;
import ntnu.idi.idatt.models.Board;
import ntnu.idi.idatt.models.Tile;

public class ThievesAndRobbersBoard extends Board {

  private final int width;
  private final int height;

  public ThievesAndRobbersBoard(int width, int height) {
    this.width = width;
    this.height = height;

    initializeBoard();
  }

  @Override
  protected void initializeBoard() {
    tiles = new HashMap<>();
    Random random = new Random();
    Tile firstTile = new Tile(0);
    firstTile.setTileAction(new MoneyAction(100));
    tiles.put(0, firstTile);
    for (int i = 1; i < width * 2 + (height - 2) * 2; i++) {
      Tile tile = new Tile(i);
      tile.setTileAction(new MoneyAction(random.nextInt(10, 31)));
      tiles.put(i, tile);
    }
  }

  public int getWidth() {
    return width;
  }

  public int getHeight() {
    return height;
  }

  public int[] getMoneyList() {
    return tiles.values().stream()
        .mapToInt(tiles -> ((MoneyAction) tiles.getTileAction()).getMoney()).toArray();
  }
}
