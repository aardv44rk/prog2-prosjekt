package ntnu.idi.idatt.games.ludo;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javafx.scene.Node;
import javafx.scene.Parent;
import ntnu.idi.idatt.AppState;
import ntnu.idi.idatt.AssetRepository;
import ntnu.idi.idatt.components.UILudoBoard;
import ntnu.idi.idatt.components.UILudoPiece;
import ntnu.idi.idatt.models.Dice;
import ntnu.idi.idatt.models.Die;
import ntnu.idi.idatt.models.GameConfig;
import ntnu.idi.idatt.models.Piece;
import ntnu.idi.idatt.models.Player;

/**
 * Controller for the Ludo game. It handles the game logic and user interactions.
 */
public class LudoController {

  private final LudoEngine engine;
  private final LudoView view;
  private final LinkedHashMap<Piece, UILudoPiece> pieces;
  private final LudoBoard board;
  private final UILudoBoard uIBoard;

  public LudoController() {
    GameConfig config = AppState.getCurrentGameConfig();

    this.engine = new LudoEngine(
        config.getPlayers(),
        config.getBoard(),
        config.getCurrentPlayerIndex(),
        new Dice(List.of(new Die(6))));

    this.view = new LudoView();
    this.pieces = new LinkedHashMap<>();
    this.board = new LudoBoard(engine.getPlayers());
    this.uIBoard = new UILudoBoard();

    setPieces();

    view.setBoard(uIBoard);

    renderPlayerList();

    setupEventHandlers();
  }

  public Parent getView() {
    return view;
  }

  private void setupEventHandlers() {
    view.rollSetOnClick(this::handleTurn);
  }

  private void handleTurn() {
    LudoTurnOptions turn = engine.startTurn();
    view.setDieEyes(turn.getRoll());

    if (!turn.isSkip()) {
      view.highlightPieces(turn.getMovable().stream().map(pieces::get).toList());
      for (Piece piece : turn.getMovable()) {
        UILudoPiece uIPiece = pieces.get(piece);
        uIPiece.setOnAction(e -> {
          engine.movePiece(piece, turn.getRoll());
          view.resetPieces();
          if (!turn.isReRoll()) {
            engine.nextPlayer();
            view.shufflePlayerList();
          }
        });
      }
    }
  }

  private void setPieces() {
    if (engine.getPlayers().stream().map(Player::getPieces).anyMatch(List::isEmpty)) {
      engine.initPieces();
    }

    List<Player> players = engine.getPlayers();
    for (int i = 0; i < players.size(); i++) {
      Player p = players.get(i);
      for (int j = 0; j < p.getPieces().size(); j++) {
        Piece piece = p.getPieces().get(j);
        UILudoPiece uIPiece = new UILudoPiece(AssetRepository.LUDO_COLORS.get(i));
        pieces.put(piece, uIPiece);
      }
    }
  }

  private void renderPlayerList() {
    LinkedHashMap<String, Node> namePieceList = new LinkedHashMap<>();
    for (Map.Entry<Piece, UILudoPiece> entry : pieces.entrySet()) {
      namePieceList.put(entry.getKey().getOwner().getName(),
          new UILudoPiece(entry.getValue().getColor()));
    }
    view.setPlayerList(namePieceList);
  }
}
