package ntnu.idi.idatt.games.snakesandladders;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import javafx.scene.Parent;
import javafx.util.Pair;
import ntnu.idi.idatt.AppState;
import ntnu.idi.idatt.AssetRepository;
import ntnu.idi.idatt.components.UISnakesAndLaddersBoard;
import ntnu.idi.idatt.components.UISnakesAndLaddersPiece;
import ntnu.idi.idatt.components.UISnakesAndLaddersTile;
import ntnu.idi.idatt.models.Dice;
import ntnu.idi.idatt.models.Die;
import ntnu.idi.idatt.models.GameConfig;
import ntnu.idi.idatt.models.Piece;
import ntnu.idi.idatt.models.Player;

public class SnakesAndLaddersController {

  private final SnakesAndLaddersEngine engine;
  private final Map<Piece, UISnakesAndLaddersPiece> pieces;
  private final SnakesAndLaddersView view;
  private final UISnakesAndLaddersBoard uIBoard;

  public SnakesAndLaddersController() {
    GameConfig config = AppState.getCurrentGameConfig();

    this.view = new SnakesAndLaddersView();

    // Engine
    this.engine = new SnakesAndLaddersEngine(
        config.getPlayers(),
        config.getBoard(),
        config.getCurrentPlayerIndex(),
        new Dice(List.of(new Die(6), new Die(6)))
    );

    // Piece init
    if (engine.getPlayers().stream().map(Player::getPieces).anyMatch(List::isEmpty)) {
      engine.initPieces();
    }
    List<Piece> pieceList = engine.getPlayers().stream()
        .map(Player::getPieces)
        .flatMap(Collection::stream).toList();

    pieces = new HashMap<>();
    for (int i = 0; i < pieceList.size(); i++) {
      pieces.put(
          pieceList.get(i),
          new UISnakesAndLaddersPiece(AssetRepository.SNL_COLORS.get(i))
      );
    }

    // Board
    SnakesAndLaddersBoard board = (SnakesAndLaddersBoard) config.getBoard();
    uIBoard = new UISnakesAndLaddersBoard(board.getRows(), board.getColumns(), board.getLadders());
    view.setBoard(uIBoard);

    // Name piece list
    List<Pair<String, UISnakesAndLaddersPiece>> namePieceList = new ArrayList<>();
    for (Player player : engine.getPlayers()) {
      namePieceList.add(new Pair<>(player.getName(), pieces.get(player.getPieces().getFirst())));
    }
    view.setPlayerList(namePieceList);

    updateDice();
    updatePieces();
    setupEventHandlers();
  }

  public Parent getView() {
    return view;
  }

  public void setupEventHandlers() {
    view.rollSetOnClick(() -> {
      if (!engine.isGameOver()) {
        engine.handleTurn();
        updateDice();
        updatePlayerList();
        updatePieces();
      }
    });
  }

  public void updateDice() {
    int[] diceValues = engine.getDice().getValues();
    view.setDiceEyes(diceValues[0], diceValues[1]);
  }

  public void updatePieces() {
    for (Entry<Piece, UISnakesAndLaddersPiece> entry : pieces.entrySet()) {
      int tileId = entry.getKey().getCurrentTile().getTileId();
      uIBoard.drawPiece(entry.getValue(), tileId);
    }
  }

  public void updatePlayerList() {
    view.shufflePlayerList();
  }

  public void playCLI() {
    if (engine == null) {
      System.out.println("Game not started!");
      return;
    }

    engine.playGame();
  }
}
