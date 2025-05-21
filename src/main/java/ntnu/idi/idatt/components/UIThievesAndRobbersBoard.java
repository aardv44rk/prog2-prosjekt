package ntnu.idi.idatt.components;

import java.util.HashMap;
import java.util.Map;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import ntnu.idi.idatt.AssetRepository;

public class UIThievesAndRobbersBoard extends StackPane {

  private final Map<Integer, UIThievesAndRobbersTile> tiles;
  private final int width;
  private final int height;
  private final int[] money;
  private final Pane pieceLayer;

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

  private Map<Integer, UIThievesAndRobbersTile> generateTiles() {
    Map<Integer, UIThievesAndRobbersTile> tileList = new HashMap<>();
    for (int i = 0; i < width * 2 + (height - 2) * 2; i++) {
      UIThievesAndRobbersTile tile = new UIThievesAndRobbersTile(money[i] + "");
      tile.setColor(AssetRepository.TAR_PATH);
      tileList.put(i, tile);
    }
    tileList.get(0).setColor(AssetRepository.TAR_START);
    return tileList;
  }

  public void renderPiece(UIPiece piece) {
    if (!pieceLayer.getChildren().contains(piece)) {
      pieceLayer.getChildren().add(piece);
    }
  }
}
