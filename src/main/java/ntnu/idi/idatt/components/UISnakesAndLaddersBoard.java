package ntnu.idi.idatt.components;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

/**
 * Component for the Snakes and Ladders board.
 * This class extends StackPane and represents the game board.
 */
public class UISnakesAndLaddersBoard extends StackPane {

  private final Map<Integer, UISnakesAndLaddersTile> tiles;
  private final int rows;
  private final int columns;
  private final Pane ladderLayer;
  private final Pane pieceLayer;

  /**
   * Constructor for the UISnakesAndLaddersBoard class.
   * Initializes the board with the specified number of rows and columns.
   *
   * @param rows    the number of rows in the board
   * @param columns the number of columns in the board
   */
  public UISnakesAndLaddersBoard(int rows, int columns) {
    getStyleClass().add("snl-board");

    this.rows = rows;
    this.columns = columns;
    this.tiles = generateTiles();

    GridPane board = new GridPane();
    board.getStyleClass().add("snl-board-board");

    for (int i = 0; i < tiles.size(); i++) {
      UISnakesAndLaddersTile tile = tiles.get(i);

      int row = rows - 1 - i / columns;
      int column = (i / columns % 2 == 0) ? i % columns : columns - 1 - i % columns;

      board.add(tile, column, row);
    }

    ladderLayer = new Pane();
    pieceLayer = new Pane();
    getChildren().addAll(board, ladderLayer, pieceLayer);
  }

  public Map<Integer, UISnakesAndLaddersTile> getTiles() {
    return tiles;
  }

  /**
   * Generates the tiles for the board.
   *
   * @return a map of tile IDs to UISnakesAndLaddersTile objects
   */
  private Map<Integer, UISnakesAndLaddersTile> generateTiles() {
    Map<Integer, UISnakesAndLaddersTile> tileList = new HashMap<>();
    for (int i = 0; i < rows * columns; i++) {
      tileList.put(i, new UISnakesAndLaddersTile(i + 1));
    }
    return tileList;
  }

  /**
   * Renders a piece on the board.
   *
   * @param piece the UISnakesAndLaddersPiece to render
   */
  public void renderPiece(UISnakesAndLaddersPiece piece) {
    if (!pieceLayer.getChildren().contains(piece)) {
      pieceLayer.getChildren().add(piece);
    }
  }

  /**
   * Renders a ladder on the board.
   *
   * @param ladder the UISnakesAndLaddersLadder to render
   */
  public void renderLadder(UISnakesAndLaddersLadder ladder) {
    if (!ladderLayer.getChildren().contains(ladder)) {
      ladderLayer.getChildren().add(ladder);
    }
  }

  @Override
  public String toString() {
    StringBuilder s = new StringBuilder();
    List<String> rowList = new ArrayList<>();
    for (int i = 0; i < rows; i++) {
      StringBuilder row = new StringBuilder();
      for (int j = 0; j < columns; j++) {
        if (i % 2 == 1) {
          row.append(i * columns + (columns - j - 1)).append(" ");
        } else {
          row.append(i * columns + j).append(" ");
        }
      }
      rowList.add(row.toString());
    }
    rowList = rowList.reversed();
    for (String row : rowList) {
      s.append(row).append("\n");
    }
    return s.toString();
  }
}
