package ntnu.idi.idatt.components;

import java.util.HashMap;
import java.util.Map;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import ntnu.idi.idatt.AssetRepository;
import ntnu.idi.idatt.utility.StyleUtil;

/**
 * Component for displaying the board in the Thieves and Robbers game.
 */
public class UIThievesAndRobbersBoard extends StackPane {

  private static final int MIN_MONEY = -20;
  private static final int MAX_MONEY = 40;

  private final Map<Integer, UIThievesAndRobbersTile> tiles;
  private final int width;
  private final int height;
  private final int[] money;
  private final Pane pieceLayer;

  /**
   * Constructor for the UIThievesAndRobbersBoard component.
   *
   * @param width  The width of the board.
   * @param height The height of the board.
   * @param money  An array representing the money on each tile.
   */
  public UIThievesAndRobbersBoard(int width, int height, int[] money) {
    getStyleClass().add("tar-board");
    this.width = width;
    this.height = height;
    this.money = money;
    this.tiles = generateTiles();

    GridPane board = new GridPane();
    board.getStyleClass().add("tar-board-board");

    for (int i = 0; i < width; i++) {
      for (int j = 0; j < height; j++) {
        board.add(new UIThievesAndRobbersTile(""), i, j);
      }
    }

    int index = 0;

    for (int x = 0; x < width; x++) {
      board.add(tiles.get(index++), x, height - 1);
    }

    for (int y = height - 2; y > 0; y--) {
      board.add(tiles.get(index++), width - 1, y);
    }

    for (int x = width - 1; x >= 0; x--) {
      board.add(tiles.get(index++), x, 0);
    }

    for (int y = 1; y < height - 1; y++) {
      board.add(tiles.get(index++), 0, y);
    }

    this.pieceLayer = new Pane();
    getChildren().addAll(board, pieceLayer);
  }

  public Map<Integer, UIThievesAndRobbersTile> getTiles() {
    return tiles;
  }

  /**
   * Generates the tiles for the board.
   *
   * @return A map of tile indices to UIThievesAndRobbersTile objects.
   */
  private Map<Integer, UIThievesAndRobbersTile> generateTiles() {
    Map<Integer, UIThievesAndRobbersTile> tileList = new HashMap<>();
    for (int i = 0; i < width * 2 + (height - 2) * 2; i++) {
      UIThievesAndRobbersTile tile = new UIThievesAndRobbersTile(money[i] + "");
      tile.setColor(StyleUtil.greenRedGradientColor(money[i], MIN_MONEY, MAX_MONEY));
      tileList.put(i, tile);
    }
    tileList.get(0).setColor(AssetRepository.TAR_START);
    return tileList;
  }

  /**
   * Renders a piece on the board.
   *
   * @param piece The piece to be rendered.
   */
  public void renderPiece(UIPiece piece) {
    if (!pieceLayer.getChildren().contains(piece)) {
      pieceLayer.getChildren().add(piece);
    }
  }
}
