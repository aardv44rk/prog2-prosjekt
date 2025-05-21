package ntnu.idi.idatt.games.thievesAndRobbers;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.Parent;
import ntnu.idi.idatt.AppState;
import ntnu.idi.idatt.AssetRepository;
import ntnu.idi.idatt.components.UIPiece;
import ntnu.idi.idatt.components.UIThievesAndRobbersBoard;
import ntnu.idi.idatt.components.UIThievesAndRobbersTile;
import ntnu.idi.idatt.core.Router;
import ntnu.idi.idatt.games.GameView;
import ntnu.idi.idatt.models.Dice;
import ntnu.idi.idatt.models.Die;
import ntnu.idi.idatt.models.GameConfig;
import ntnu.idi.idatt.models.Piece;
import ntnu.idi.idatt.models.Player;
import ntnu.idi.idatt.utility.DrawingUtil;

public class ThievesAndRobbersController {

  private final ThievesAndRobbersEngine engine;
  private final GameView view;
  private final ThievesAndRobbersBoard board;
  private final LinkedHashMap<Piece, UIPiece> pieces;
  private final UIThievesAndRobbersBoard uIBoard;
  private final Map<Integer, UIThievesAndRobbersTile> tiles;

  public ThievesAndRobbersController() {
    GameConfig config = AppState.getCurrentGameConfig();

    this.engine = new ThievesAndRobbersEngine(
        config.getPlayers(),
        config.getBoard(),
        config.getCurrentPlayerIndex(),
        new Dice(List.of(new Die(6), new Die(6)))
    );
    this.view = new GameView();
    this.board = (ThievesAndRobbersBoard) config.getBoard();
    this.pieces = new LinkedHashMap<>();
    this.uIBoard = new UIThievesAndRobbersBoard(board.getWidth(), board.getHeight(),
        board.getMoneyList());
    this.tiles = uIBoard.getTiles();

    setPieces();

    view.setBoard(uIBoard);
    
    renderPlayerList();

    for (Entry<Piece, UIPiece> entry : pieces.entrySet()) {
      entry.getValue().layoutBoundsProperty().addListener((o, oldBounds, newBounds) -> {
        if (newBounds.getWidth() > 0 && newBounds.getHeight() > 0) {
          updatePiece(entry.getKey().getCurrentTile().getTileId(), entry.getValue());
        }
      });
      uIBoard.renderPiece(entry.getValue());
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
      UIPiece piece = new UIPiece(
          AssetRepository.SNL_COLORS.get(i));
      pieces.put(pieceList.get(i), piece);
    }
  }

  private void renderPlayerList() {
    LinkedHashMap<String, Node> namePieceList = new LinkedHashMap<>();
    for (Map.Entry<Piece, UIPiece> entry : pieces.entrySet()) {
      namePieceList.put(entry.getKey().getOwner().getName(),
          new UIPiece(entry.getValue().getColor()));
    }
    view.setPlayerList(namePieceList);
  }


  private void updateDice() {
    int[] diceValues = engine.getDice().getValues();
    view.setDiceEyes(diceValues[0], diceValues[1]);
  }

  private void updatePiece(int tileId, UIPiece piece) {
    UIThievesAndRobbersTile tile = tiles.get(tileId);

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
