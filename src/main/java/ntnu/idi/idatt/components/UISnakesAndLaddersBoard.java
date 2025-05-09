package ntnu.idi.idatt.components;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class UISnakesAndLaddersBoard extends StackPane {

  private final Map<Integer, UISnakesAndLaddersTile> tiles;
  private final int rows;
  private final int columns;

  public UISnakesAndLaddersBoard(int rows, int columns, List<UISnakesAndLaddersLadder> ladders) {
    getStyleClass().add("snl-board");

    this.rows = rows;
    this.columns = columns;
    this.tiles = generateUIBoardTileList();

    VBox board = new VBox();
    board.getStyleClass().add("snl-board-board");

    List<HBox> boardRows = new ArrayList<>();
    for (int i = 0; i < rows; i++) {
      List<UISnakesAndLaddersTile> row = new ArrayList<>();
      for (int j = 0; j < columns; j++) {
        row.add(tiles.get(i * columns + j));
      }
      if (i % 2 == 1) {
        Collections.reverse(row);
      }
      HBox rowHbox = new HBox();
      rowHbox.getChildren().addAll(row);
      boardRows.add(rowHbox);
    }
    Collections.reverse(boardRows);
    board.getChildren().addAll(boardRows);
    getChildren().add(board);
  }

  public UISnakesAndLaddersTile getTile(int tileId) {
    return tiles.get(tileId);
  }

  public Collection<UISnakesAndLaddersTile> getTiles() {
    return tiles.values();
  }

  private Map<Integer, UISnakesAndLaddersTile> generateUIBoardTileList() {
    Map<Integer, UISnakesAndLaddersTile> tileList = new HashMap<>();
    for (int i = 0; i < rows * columns; i++) {
      tileList.put(i, new UISnakesAndLaddersTile(i + 1));
    }
    return tileList;
  }
}
