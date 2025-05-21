package ntnu.idi.idatt.menu.gameSetup;

import javafx.scene.Parent;
import ntnu.idi.idatt.AppState;
import ntnu.idi.idatt.core.Router;
import ntnu.idi.idatt.models.GameConfig;
import ntnu.idi.idatt.models.GameInfo;

/**
 * Controller for the GameSetupView. It handles the logic for setting up a game.
 */
public class GameSetupController {

  private final GameSetupView view;

  /**
   * Constructor for the GameSetupController.
   *
   * @param view The GameSetupView instance.
   */
  public GameSetupController(GameSetupView view) {
    this.view = view;
  }

  /**
   * Returns the view for setting up a game. It sets the players and boards based on the provided
   * GameInfo.
   *
   * @param gameInfo The GameInfo object containing game details.
   * @return The Parent object representing the view.
   */
  public Parent getViewForGame(GameInfo gameInfo) {
    view.setPlayers(gameInfo.getPlayerMin(), gameInfo.getPlayerMax());
    view.setBoards(gameInfo.getBoardOptions());
    setupEventHandlers();
    return view;
  }

  /**
   * Sets up the event handlers for the buttons in the GameSetupView.
   */
  private void setupEventHandlers() {
    view.startButtonSetOnClick(() -> {
      AppState.setCurrentGameConfig(
          new GameConfig(
              view.getPlayers(),
              view.getSelectedBoard(),
              0
          )
      );
      Router.navigateTo(AppState.getSelectedGame().getName());
    });
  }

}
