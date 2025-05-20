package ntnu.idi.idatt.components;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import ntnu.idi.idatt.AssetRepository;

public class UILudoBoard extends StackPane {

  private final Map<Integer, UILudoTile> tiles;
  private final GridPane board;
  private final Pane pieceLayer;

  public UILudoBoard() {
    getStyleClass().add("ludo-board");

    tiles = new HashMap<>();
    pieceLayer = new Pane();

    board = generateLudoBoard();
    board.getStyleClass().add("ludo-board-board");

    getChildren().addAll(board, pieceLayer);
  }

  private Map<Integer, UILudoTile> generateMainPathTiles() {
    Map<Integer, UILudoTile> mainTiles = new HashMap<>();
    for (int i = 0; i < 52; i++) {
      UILudoTile tile = new UILudoTile();
      if (i % 13 == 0) {
        tile.setColor(AssetRepository.LUDO_COLORS.get(i / 13));
      }
      mainTiles.put(i, tile);
      tiles.put(i, tile);
    }
    return mainTiles;
  }

  private List<Map<Integer, UILudoTile>> generateFinalStretchTiles() {
    List<Map<Integer, UILudoTile>> finalStretchTiles = new ArrayList<>();
    for (int i = 0; i < 4; i++) {
      finalStretchTiles.add(new HashMap<>());
      for (int j = 0; j < 6; j++) {
        UILudoTile tile = new UILudoTile();
        tile.setColor(AssetRepository.LUDO_COLORS.get(i));
        finalStretchTiles.getLast().put(j, tile);
      }
    }
    return finalStretchTiles;
  }

  private List<Map<Integer, UILudoTile>> generatePlayerHomeTiles() {
    List<Map<Integer, UILudoTile>> playerHomeTiles = new ArrayList<>();
    for (int i = 0; i < 4; i++) {
      playerHomeTiles.add(new HashMap<>());
      for (int j = 0; j < 4; j++) {
        UILudoTile tile = new UILudoTile();
        tile.setColor(AssetRepository.LUDO_COLORS.get(i));
        playerHomeTiles.getLast().put(j, tile);
      }
    }
    return playerHomeTiles;
  }

  public GridPane generateLudoBoard() {
    GridPane board = new GridPane();
    board.setGridLinesVisible(true); // Optional: for debugging layout

    for (int row = 0; row < 15; row++) {
      for (int col = 0; col < 15; col++) {
        UILudoTile tile = new UILudoTile();

        // Default background
        Color color = Color.LIGHTGRAY;

        // Home areas
        if (row < 6 && col < 6) {
          color = AssetRepository.PLAYER_RED;
        } else if (row < 6 && col > 8) {
          color = AssetRepository.PLAYER_YELLOW;
        } else if (row > 8 && col < 6) {
          color = AssetRepository.PLAYER_GREEN;
        } else if (row > 8 && col > 8) {
          color = AssetRepository.PLAYER_BLUE;
        }

        // Center goal area
        if (row >= 6 && row <= 8 && col >= 6 && col <= 8) {
          color = AssetRepository.LUDO_GOAL;
        }

        // Paths (basic cross)
        if ((row == 6 || row == 8) && col >= 0 && col < 6) {
          color = AssetRepository.PLAYER_GREEN;
        }
        if ((col == 6 || col == 8) && row >= 0 && row < 6) {
          color = AssetRepository.PLAYER_RED;
        }
        if ((row == 6 || row == 8) && col > 8 && col <= 14) {
          color = AssetRepository.PLAYER_BLUE;
        }
        if ((col == 6 || col == 8) && row > 8 && row <= 14) {
          color = AssetRepository.PLAYER_YELLOW;
        }

        // Final stretch to goal (center column/row)
        if (row == 7 && col >= 1 && col <= 5) {
          color = AssetRepository.PLAYER_GREEN;
        }
        if (col == 7 && row >= 1 && row <= 5) {
          color = AssetRepository.PLAYER_RED;
        }
        if (row == 7 && col >= 9 && col <= 13) {
          color = AssetRepository.PLAYER_BLUE;
        }
        if (col == 7 && row >= 9 && row <= 13) {
          color = AssetRepository.PLAYER_YELLOW;
        }

        tile.setColor(color);
        board.add(tile, col, row);
      }
    }

    return board;
  }
}
