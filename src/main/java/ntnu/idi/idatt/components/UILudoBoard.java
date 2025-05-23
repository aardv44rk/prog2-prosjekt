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

/**
 * Component for the Ludo board.
 * This class extends StackPane and contains the board layout and tiles.
 */
public class UILudoBoard extends StackPane {

  private final Map<Integer, UILudoTile> tiles;
  private final GridPane board;
  private final Pane pieceLayer;

  /**
   * Constructor for the UILudoBoard class.
   * Initializes the board and tiles, and adds them to the component.
   */
  public UILudoBoard() {
    getStyleClass().add("ludo-board");

    tiles = new HashMap<>();
    pieceLayer = new Pane();

    board = generateLudoBoard();
    board.getStyleClass().add("ludo-board-board");

    getChildren().addAll(board, pieceLayer);
  }

  /**
   * Generates the main path tiles for the Ludo board.
   *
   * @return A map of tile IDs to UILudoTile objects representing the main path.
   */
  @SuppressWarnings("unused")
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

  /**
   * Generates the final stretch tiles for each player.
   *
   * @return A list of maps, each containing tile IDs and corresponding UILudoTile objects
   * for the final stretch.
   */
  @SuppressWarnings("unused")
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

  /**
   * Generates the home tiles for each player.
   *
   * @return A list of maps, each containing tile IDs and corresponding UILudoTile objects for the
   * home area.
   */
  @SuppressWarnings("unused")
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

  /**
   * Generates the Ludo board layout.
   * This method creates a 15x15 grid and sets the colors for each tile based on its position.
   *
   * @return A JavaFX GridPane representing the Ludo board layout.
   */
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
