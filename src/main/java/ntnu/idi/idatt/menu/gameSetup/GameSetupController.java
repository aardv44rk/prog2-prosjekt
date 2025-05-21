package ntnu.idi.idatt.menu.gameSetup;

import javafx.scene.Parent;
import ntnu.idi.idatt.AppState;
import ntnu.idi.idatt.core.Router;
import ntnu.idi.idatt.models.GameConfig;
import ntnu.idi.idatt.models.GameInfo;

public class GameSetupController {

  private final GameSetupView view;

  public GameSetupController(GameSetupView view) {
    this.view = view;
  }

  public Parent getViewForGame(GameInfo gameInfo) {
    view.setPlayers(gameInfo.getPlayerMin(), gameInfo.getPlayerMax());
    view.setBoards(gameInfo.getBoardOptions());
    setupEventHandlers();
    return view;
  }

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
