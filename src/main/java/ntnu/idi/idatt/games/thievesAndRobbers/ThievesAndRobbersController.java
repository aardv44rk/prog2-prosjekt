package ntnu.idi.idatt.games.thievesandrobbers;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.paint.Color;
import javafx.util.Pair;
import ntnu.idi.idatt.AppState;
import ntnu.idi.idatt.AssetRepository;
import ntnu.idi.idatt.components.MoneyGraph;
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

/**
 * Controller for the Thieves and Robbers game.
 */
public class ThievesAndRobbersController {

  private final ThievesAndRobbersEngine engine;
  private final GameView view;
  private final LinkedHashMap<Piece, UIPiece> pieces;
  private final UIThievesAndRobbersBoard uIBoard;
  private final Map<Integer, UIThievesAndRobbersTile> tiles;
  private final MoneyGraph moneyGraph;

  /**
   * Constructor for the Thieves and Robbers controller. Initializes the game engine, view, and
   * board. Sets up the pieces and event handlers.
   */
  public ThievesAndRobbersController() {
    GameConfig config = AppState.getCurrentGameConfig();

    this.engine = new ThievesAndRobbersEngine(
        config.getPlayers(),
        config.getBoard(),
        config.getCurrentPlayerIndex(),
        new Dice(List.of(new Die(6), new Die(6)))
    );
    this.view = new GameView();
    ThievesAndRobbersBoard board = (ThievesAndRobbersBoard) config.getBoard();
    this.pieces = new LinkedHashMap<>();
    this.uIBoard = new UIThievesAndRobbersBoard(board.getWidth(), board.getHeight(),
        board.getMoneyList());
    this.tiles = uIBoard.getTiles();

    List<Pair<String, Color>> playerColors = new ArrayList<>();

    for (int i = 0; i < engine.getPlayers().size(); i++) {
      Color color = AssetRepository.TAR_COLORS.get(i);
      playerColors.add(new Pair<>(engine.getPlayers().get(i).getName(), color));
    }

    this.moneyGraph = new MoneyGraph(playerColors);

    setPieces();

    view.setBoard(uIBoard);
    view.setStats(moneyGraph);

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

  /**
   * Sets up event handlers for the game.
   */
  private void setupEventHandlers() {
    view.rollSetOnClick(() -> {
      if (!engine.isGameOver()) {
        engine.handleTurn();
        Player currentPlayer = engine.getCurrentPlayer();
        Piece currentPiece = currentPlayer.getPieces().getFirst();
        int playerMoney = currentPlayer.getPieces().stream()
            .mapToInt(piece -> ((ThievesAndRobbersPiece) piece).getMoney())
            .sum();
        updateDice();
        updateMoneyGraph(currentPlayer.getName(), playerMoney);
        view.shufflePlayerList();
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
   * Sets the pieces for the players in the game.
   * If the pieces are empty, initializes them.
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
   * Renders the player list in the UI.
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
   * Updates the dice values in the UI.
   */
  private void updateDice() {
    int[] diceValues = engine.getDice().getValues();
    view.setDiceEyes(diceValues[0], diceValues[1]);
  }

  /**
   * Updates the position of a piece on the board.
   *
   * @param tileId The ID of the tile where the piece is located.
   * @param piece  The piece to be updated.
   */
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

  /**
   * Updates the money graph in the UI.
   *
   * @param playerName The name of the player.
   * @param amount     The amount of money to be updated.
   */
  private void updateMoneyGraph(String playerName, int amount) {
    moneyGraph.setMoney(playerName, amount);
  }

}
