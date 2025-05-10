package ntnu.idi.idatt.components;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javafx.application.Platform;
import javafx.geometry.Bounds;
import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import ntnu.idi.idatt.AssetRepository;
import ntnu.idi.idatt.games.snakesandladders.SnakesAndLaddersLadder;
import ntnu.idi.idatt.utility.StyleUtil;

public class UISnakesAndLaddersBoard extends StackPane {

  private final Map<Integer, UISnakesAndLaddersTile> tiles;
  private final int rows;
  private final int columns;
  private final Pane ladderLayer;
  private final Pane pieceLayer;

  public UISnakesAndLaddersBoard(int rows, int columns, List<SnakesAndLaddersLadder> ladders) {
    getStyleClass().add("snl-board");

    this.rows = rows;
    this.columns = columns;
    this.tiles = generateUIBoardTileList();

    GridPane board = new GridPane();
    board.getStyleClass().add("snl-board-board");

    for (int i = 0; i < tiles.size(); i++) {
      UISnakesAndLaddersTile tile = tiles.get(i);

      int row = rows - 1 - i / columns;
      int column = (i / columns % 2 == 0) ? i % columns : columns - 1 - i % columns;

      board.add(tile, column, row);
    }

    ladderLayer = new Pane();
    Platform.runLater(() -> {
      for (SnakesAndLaddersLadder ladder : ladders) {
        List<Point2D> coords = getLadderCoords(ladder);
        drawLadder(coords.getFirst(), coords.getLast());
      }
    });

    pieceLayer = new Pane();
    getChildren().addAll(board, ladderLayer, pieceLayer);

  }

  public UISnakesAndLaddersTile getTile(int tileId) {
    return tiles.get(tileId);
  }

  public Collection<UISnakesAndLaddersTile> getTiles() {
    return tiles.values();
  }

  public void drawPiece(UISnakesAndLaddersPiece piece, int tileId) {
    Platform.runLater(() -> {
      UISnakesAndLaddersTile tile = tiles.get(tileId);
      Point2D coords = getCoordsForPiece(tile, piece);
      long sameCoords = pieceLayer.getChildren().stream()
          .map(p -> getCoordsForPiece(tile, (UISnakesAndLaddersPiece) p))
          .filter(c -> c == coords)
          .count();
      piece.setLayoutX(coords.getX());
      piece.setLayoutY(coords.getY() - sameCoords * 10);
      if (!pieceLayer.getChildren().contains(piece)) {
        pieceLayer.getChildren().add(piece);
      }
    });
  }

  private Map<Integer, UISnakesAndLaddersTile> generateUIBoardTileList() {
    Map<Integer, UISnakesAndLaddersTile> tileList = new HashMap<>();
    for (int i = 0; i < rows * columns; i++) {
      tileList.put(i, new UISnakesAndLaddersTile(i + 1));
    }
    return tileList;
  }

  private List<Point2D> getLadderCoords(SnakesAndLaddersLadder ladder) {
    int startId = ladder.startTileId();
    int endId = ladder.endTileId();

    UISnakesAndLaddersTile startTile = tiles.get(startId - 1);
    UISnakesAndLaddersTile endTile = tiles.get(endId - 1);

    String startColor = (startId < endId)
        ? StyleUtil.toRgbString(AssetRepository.LADDER_START_UP)
        : StyleUtil.toRgbString(AssetRepository.LADDER_START_DOWN);

    String endColor = (startId < endId)
        ? StyleUtil.toRgbString(AssetRepository.LADDER_END_UP)
        : StyleUtil.toRgbString(AssetRepository.LADDER_END_DOWN);

    startTile.setStyle("-fx-background-color: " + startColor);
    endTile.setStyle("-fx-background-color: " + endColor);

    Point2D start = getCenterCoords(startTile);
    Point2D end = getCenterCoords(endTile);

    return List.of(start, end);
  }

  private void drawLadder(Point2D start, Point2D end) {
    double dx = end.getX() - start.getX();
    double dy = end.getY() - start.getY();

    double totalLength = Math.sqrt(dx * dx + dy * dy);
    double segmentLength = 5;

    double dirX = dx / totalLength;
    double dirY = dy / totalLength;

    double drawnLength = 0;
    int i = 0;

    while (drawnLength + segmentLength <= totalLength) {
      double x1 = start.getX() + dirX * drawnLength;
      double y1 = start.getY() + dirY * drawnLength;
      double x2 = start.getX() + dirX * (drawnLength + segmentLength);
      double y2 = start.getY() + dirY * (drawnLength + segmentLength);

      Line segment = new Line(x1, y1, x2, y2);
      segment.setStroke(
          i % 2 == 0 ? Color.WHITE : (start.getY() > end.getY()) ? AssetRepository.LADDER_UP
              : AssetRepository.LADDER_DOWN);
      segment.setStrokeWidth(12);
      ladderLayer.getChildren().add(segment);

      drawnLength += segmentLength + 10;
      i++;
    }
  }

  private Point2D getCenterCoords(Node node) {
    Bounds bounds = node.localToScene(node.getBoundsInLocal());

    double centerX = bounds.getMinX() + bounds.getWidth() / 2;
    double centerY = bounds.getMinY() + bounds.getHeight() / 2;

    return sceneToLocal(new Point2D(centerX, centerY));
  }

  private Point2D getCoordsForPiece(Node tile, Node piece) {
    tile.applyCss();
    tile.autosize();
    piece.applyCss();
    piece.autosize();

    Bounds tileBoundsInLocal = tile.getBoundsInLocal();
    Bounds tileBoundsInScene = tile.localToScene(tileBoundsInLocal);
    Bounds pieceBounds = piece.getLayoutBounds();

    System.out.println("=== DEBUG INFO ===");
    System.out.println("Tile bounds (local): " + tileBoundsInLocal);
    System.out.println("Tile bounds (scene): " + tileBoundsInScene);
    System.out.println("Piece layout bounds: " + pieceBounds);

    double centerX = tileBoundsInScene.getMinX() + tileBoundsInScene.getWidth() / 2;
    double centerY = tileBoundsInScene.getMinY() + tileBoundsInScene.getHeight() / 2;

    double layoutX = centerX - pieceBounds.getWidth() / 2;
    double layoutY = centerY - pieceBounds.getHeight() / 2;

    System.out.println("Calculated top-left (scene): " + layoutX + ", " + layoutY);

    Point2D result = pieceLayer.sceneToLocal(new Point2D(layoutX, layoutY));
    System.out.println("Converted to local (pieceLayer): " + result);

    return result;
  }


}
