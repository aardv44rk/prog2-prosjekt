package ntnu.idi.idatt.games.thievesandrobbers;

import java.util.HashMap;
import java.util.Random;
import ntnu.idi.idatt.models.Board;
import ntnu.idi.idatt.models.Tile;

/**
 * Thieves and Robbers board implementation.
 * This class represents the game board for the Thieves and Robbers game.
 * It initializes the board with tiles and their corresponding actions.
 */
public class ThievesAndRobbersBoard extends Board {

  private static final int MIN_MONEY = -20;
  private static final int MAX_MONEY = 40;

  private final int width;
  private final int height;

  /**
   * Constructor for ThievesAndRobbersBoard.
   *
   * @param width  The width of the board.
   * @param height The height of the board.
   */
  public ThievesAndRobbersBoard(int width, int height) {
    this.width = width;
    this.height = height;

    initializeBoard();
  }

  /**
   * Initializes the board with tiles and their corresponding actions.
   */
  @Override
  protected void initializeBoard() {
    tiles = new HashMap<>();
    Random random = new Random();
    Tile firstTile = new Tile(0);
    firstTile.setTileAction(new MoneyAction(100));
    tiles.put(0, firstTile);
    for (int i = 1; i < width * 2 + (height - 2) * 2; i++) {
      Tile tile = new Tile(i);
      int randInt = random.nextInt(MIN_MONEY, MAX_MONEY);
      tile.setTileAction(new MoneyAction(randInt));
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
