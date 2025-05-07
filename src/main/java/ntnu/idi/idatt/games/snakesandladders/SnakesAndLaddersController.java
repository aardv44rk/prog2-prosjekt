package ntnu.idi.idatt.games.snakesandladders;

import java.util.ArrayList;
import java.util.List;
import javafx.scene.Parent;
import ntnu.idi.idatt.AppState;
import ntnu.idi.idatt.components.UISnakesAndLaddersBoard;
import ntnu.idi.idatt.models.Dice;
import ntnu.idi.idatt.models.Die;
import ntnu.idi.idatt.models.GameConfig;
import ntnu.idi.idatt.models.Player;

public class SnakesAndLaddersController {

  private final SnakesAndLaddersEngine engine;
  private final SnakesAndLaddersView view;

  public SnakesAndLaddersController() {
    GameConfig config = AppState.getCurrentGameConfig();
    this.engine = new SnakesAndLaddersEngine(
        config.getPlayers(),
        config.getBoard(),
        config.getCurrentPlayerIndex(),
        new Dice(List.of(new Die(6), new Die(6)))
    );
    if (this.engine.getPlayers().stream().map(Player::getPieces).anyMatch(List::isEmpty)) {
      this.engine.initPieces();
    }
    this.view = new SnakesAndLaddersView();
    SnakesAndLaddersBoard board = (SnakesAndLaddersBoard) config.getBoard();
    this.view.setBoard(
        new UISnakesAndLaddersBoard(board.getRows(), board.getColumns(), new ArrayList<>()));
    view.setDiceEyes(2, 2);
    setupEventHandlers();
  }

  public Parent getView() {
    return view;
  }

  public void setupEventHandlers() {

  }

  public void playCLI() {
    if (engine == null) {
      System.out.println("Game not started!");
      return;
    }

    engine.startGame();
  }
}
