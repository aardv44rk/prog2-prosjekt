package ntnu.idi.idatt.games.snakesandladders;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.atomic.AtomicInteger;
import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.Parent;
import ntnu.idi.idatt.AppState;
import ntnu.idi.idatt.AssetRepository;
import ntnu.idi.idatt.components.UIPiece;
import ntnu.idi.idatt.components.UISnakesAndLaddersBoard;
import ntnu.idi.idatt.components.UISnakesAndLaddersLadder;
import ntnu.idi.idatt.components.UISnakesAndLaddersTile;
import ntnu.idi.idatt.core.Router;
import ntnu.idi.idatt.games.GameView;
import ntnu.idi.idatt.models.Dice;
import ntnu.idi.idatt.models.Die;
import ntnu.idi.idatt.models.GameConfig;
import ntnu.idi.idatt.models.Piece;
import ntnu.idi.idatt.models.Player;
import ntnu.idi.idatt.utility.DrawingUtil;

/**
 * Controller for the Snakes and Ladders game.
 */
public class SnakesAndLaddersController {

  private final SnakesAndLaddersEngine engine;
  private final GameView view;
  private final SnakesAndLaddersBoard board;
  private final LinkedHashMap<Piece, UIPiece> pieces;
  private final UISnakesAndLaddersBoard uIBoard;
  private final Map<Integer, UISnakesAndLaddersTile> tiles;

  /**
   * Constructor for the Snakes and Ladders controller. Initializes the game engine, view, and
   * board. Sets up the pieces and event handlers.
   */
  public SnakesAndLaddersController() {
    GameConfig config = AppState.getCurrentGameConfig();

    this.engine = new SnakesAndLaddersEngine(
        config.getPlayers(),
        config.getBoard(),
        config.getCurrentPlayerIndex(),
        new Dice(List.of(new Die(6), new Die(6)))
    );
    this.view = new GameView();
    this.board = (SnakesAndLaddersBoard) config.getBoard();
    this.pieces = new LinkedHashMap<>();
    this.uIBoard = new UISnakesAndLaddersBoard(board.getRows(), board.getColumns());
    this.tiles = uIBoard.getTiles();

    setPieces();

    view.setBoard(uIBoard);

    renderPlayerList();

    // Render pieces and ladders after tiles finish rendering to get correct coords
    for (Entry<Piece, UIPiece> entry : pieces.entrySet()) {
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

  /**
   * Sets up event handlers for the game. Handles the roll button click event and updates the game
   * state accordingly.
   */
  private void setupEventHandlers() {
    view.rollSetOnClick(() -> {
      if (!engine.isGameOver()) {
        engine.handleTurn();
        updateDice();
        view.shufflePlayerList();
        Player currentPlayer = engine.getCurrentPlayer();
        Piece currentPiece = currentPlayer.getPieces().getFirst();
        updatePiece(currentPiece.getCurrentTile().getTileId(), pieces.get(currentPiece));
        if (engine.isGameOver()) {
          Router.showAlert("Game over!", currentPlayer.getName() + " has won.", "Close",
              null);
        }
        engine.nextPlayer();
      }
    });
  }

  /**
   * Sets up the pieces for the game. Initializes the pieces for each player and assigns them to the
   * corresponding UI elements.
   */
  private void setPieces() {
    if (engine.getPlayers().stream().map(Player::getPieces).anyMatch(List::isEmpty)) {
      engine.initPieces();
    }
    List<Piece> pieceList = engine.getPlayers().stream()
        .map(Player::getPieces)
        .flatMap(Collection::stream).toList();

    for (int i = 0; i < pieceList.size(); i++) {
      UIPiece piece = new UIPiece(
          AssetRepository.SNL_COLORS.get(i));
      pieces.put(pieceList.get(i), piece);
    }
  }

  /**
   * Renders the ladders on the board. Sets the color of the tiles based on the ladder's direction
   * (ascending or descending).
   */
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
              DrawingUtil.getCenterCoords(uIBoard, tiles.get(ladder.startTileId())),
              DrawingUtil.getCenterCoords(uIBoard, tiles.get(ladder.endTileId())),
              ladder.isAscending()
          )
      );
    });
  }

  /**
   * Renders the player list on the UI.
   */
  private void renderPlayerList() {
    LinkedHashMap<String, Node> namePieceList = new LinkedHashMap<>();
    for (Map.Entry<Piece, UIPiece> entry : pieces.entrySet()) {
      namePieceList.put(entry.getKey().getOwner().getName(),
          new UIPiece(entry.getValue().getColor()));
    }
    view.setPlayerList(namePieceList);
  }


  /**
   * Updates the dice display on the UI. Sets the eyes of the dice based on the current values.
   */
  private void updateDice() {
    int[] diceValues = engine.getDice().getValues();
    view.setDiceEyes(diceValues[0], diceValues[1]);
  }

  /**
   * Updates the position of a piece on the board and sets the layout coordinates of the piece based
   * on its current tile.
   *
   * @param tileId The ID of the tile where the piece is located.
   * @param piece  The piece to be updated.
   */
  private void updatePiece(int tileId, UIPiece piece) {
    UISnakesAndLaddersTile tile = tiles.get(tileId);

    Point2D coords = DrawingUtil.getCenterCoordsOffsetForNode(uIBoard, tile, piece);
    long sameCoords = pieces.values().stream()
        .filter(p -> !p.equals(piece)
            && p.getLayoutX() == coords.getX()
            && p.getLayoutY() == coords.getY())
        .count();
    piece.setLayoutX(coords.getX());
    piece.setLayoutY(coords.getY() - sameCoords * 5);
  }
}
