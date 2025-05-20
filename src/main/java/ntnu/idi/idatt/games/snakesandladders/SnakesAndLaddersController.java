package ntnu.idi.idatt.games.snakesandladders;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.atomic.AtomicInteger;
import javafx.geometry.Bounds;
import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.Parent;
import ntnu.idi.idatt.AppState;
import ntnu.idi.idatt.AssetRepository;
import ntnu.idi.idatt.components.UISnakesAndLaddersBoard;
import ntnu.idi.idatt.components.UISnakesAndLaddersLadder;
import ntnu.idi.idatt.components.UISnakesAndLaddersPiece;
import ntnu.idi.idatt.components.UISnakesAndLaddersTile;
import ntnu.idi.idatt.core.Router;
import ntnu.idi.idatt.models.Dice;
import ntnu.idi.idatt.models.Die;
import ntnu.idi.idatt.models.GameConfig;
import ntnu.idi.idatt.models.Piece;
import ntnu.idi.idatt.models.Player;

public class SnakesAndLaddersController {

  private final SnakesAndLaddersEngine engine;
  private final SnakesAndLaddersView view;
  private final LinkedHashMap<Piece, UISnakesAndLaddersPiece> pieces;
  private final SnakesAndLaddersBoard board;
  private final UISnakesAndLaddersBoard uIBoard;
  private final Map<Integer, UISnakesAndLaddersTile> tiles;

  public SnakesAndLaddersController() {
    GameConfig config = AppState.getCurrentGameConfig();

    this.engine = new SnakesAndLaddersEngine(
        config.getPlayers(),
        config.getBoard(),
        config.getCurrentPlayerIndex(),
        new Dice(List.of(new Die(6), new Die(6)))
    );
    this.view = new SnakesAndLaddersView();
    this.board = (SnakesAndLaddersBoard) config.getBoard();
    this.pieces = new LinkedHashMap<>();
    this.uIBoard = new UISnakesAndLaddersBoard(board.getRows(), board.getColumns());
    this.tiles = uIBoard.getTiles();

    setPieces();

    view.setBoard(uIBoard);

    renderPlayerList();

    // Render pieces and ladders after tiles finish rendering to get correct coords
    for (Entry<Piece, UISnakesAndLaddersPiece> entry : pieces.entrySet()) {
      entry.getValue().layoutBoundsProperty().addListener((o, oldBounds, newBounds) -> {
        if (newBounds.getWidth() > 0 && newBounds.getHeight() > 0) {
          updatePiece(entry.getKey().getCurrentTile().getTileId(), entry.getValue());
        }
      });
      uIBoard.renderPiece(entry.getValue());
    }

    AtomicInteger remaining = new AtomicInteger(tiles.size());
    for (UISnakesAndLaddersTile tile : tiles.values()) {
      tile.layoutBoundsProperty().addListener((o, oldBounds, newBounds) -> {
        if (newBounds.getWidth() > 0 && newBounds.getHeight() > 0) {
          if (remaining.decrementAndGet() == 0) {
            renderLadders();
          }
        }
      });
    }

    updateDice();
    setupEventHandlers();
  }

  public Parent getView() {
    return view;
  }

  private void setupEventHandlers() {
    view.rollSetOnClick(() -> {
      if (!engine.isGameOver()) {
        engine.handleTurn();
        updateDice();
        view.shufflePlayerList();
        if (engine.isGameOver()) {
          Piece p = engine.getCurrentPlayer().getPieces().getFirst();
          updatePiece(p.getCurrentTile().getTileId(), pieces.get(p));
          Router.showAlert("Game over!", engine.getCurrentPlayer().getName() + " has won.", "Close",
              null);
        } else {
          Piece p = engine.getLastPlayer().getPieces().getFirst();
          updatePiece(p.getCurrentTile().getTileId(), pieces.get(p));
        }
      }
    });
  }

  private void setPieces() {
    if (engine.getPlayers().stream().map(Player::getPieces).anyMatch(List::isEmpty)) {
      engine.initPieces();
    }
    List<Piece> pieceList = engine.getPlayers().stream()
        .map(Player::getPieces)
        .flatMap(Collection::stream).toList();

    for (int i = 0; i < pieceList.size(); i++) {
      UISnakesAndLaddersPiece piece = new UISnakesAndLaddersPiece(
          AssetRepository.SNL_COLORS.get(i));
      pieces.put(pieceList.get(i), piece);
    }
  }

  private void renderLadders() {
    board.getLadders().forEach(ladder -> {
      tiles.get(ladder.startTileId()).setColor(
          ladder.isAscending() ? AssetRepository.LADDER_START_UP
              : AssetRepository.LADDER_START_DOWN);
      tiles.get(ladder.endTileId()).setColor(
          ladder.isAscending() ? AssetRepository.LADDER_END_UP
              : AssetRepository.LADDER_END_DOWN);
      uIBoard.renderLadder(
          new UISnakesAndLaddersLadder(
              getBoardCenterCoords(tiles.get(ladder.startTileId())),
              getBoardCenterCoords(tiles.get(ladder.endTileId())),
              ladder.isAscending()
          )
      );
    });
  }

  private void renderPlayerList() {
    LinkedHashMap<String, Node> namePieceList = new LinkedHashMap<>();
    for (Map.Entry<Piece, UISnakesAndLaddersPiece> entry : pieces.entrySet()) {
      namePieceList.put(entry.getKey().getOwner().getName(),
          new UISnakesAndLaddersPiece(entry.getValue().getColor()));
    }
    view.setPlayerList(namePieceList);
  }


  private void updateDice() {
    int[] diceValues = engine.getDice().getValues();
    view.setDiceEyes(diceValues[0], diceValues[1]);
  }

  private void updatePiece(int tileId, UISnakesAndLaddersPiece piece) {
    UISnakesAndLaddersTile tile = tiles.get(tileId);

    Point2D coords = getCoordsForPiece(tile, piece);
    long sameCoords = pieces.values().stream()
        .filter(p -> !p.equals(piece)
            && p.getLayoutX() == coords.getX()
            && p.getLayoutY() == coords.getY())
        .count();
    piece.setLayoutX(coords.getX());
    piece.setLayoutY(coords.getY() - sameCoords * 5);
  }

  private Point2D getBoardCenterCoords(Node node) {
    Bounds bounds = node.localToScene(node.getBoundsInLocal());

    double centerX = bounds.getMinX() + bounds.getWidth() / 2;
    double centerY = bounds.getMinY() + bounds.getHeight() / 2;

    return uIBoard.sceneToLocal(new Point2D(centerX, centerY));
  }

  private Point2D getCoordsForPiece(Node tile, Node piece) {
    Bounds tileBoundsInLocal = tile.getBoundsInLocal();
    Bounds tileBoundsInScene = tile.localToScene(tileBoundsInLocal);
    Bounds pieceBounds = piece.getLayoutBounds();

    double centerX = tileBoundsInScene.getMinX() + tileBoundsInScene.getWidth() / 2;
    double centerY = tileBoundsInScene.getMinY() + tileBoundsInScene.getHeight() / 2;

    double layoutX = centerX - pieceBounds.getWidth() / 2;
    double layoutY = centerY - pieceBounds.getHeight() / 2;

    return uIBoard.sceneToLocal(new Point2D(layoutX, layoutY));
  }
}
