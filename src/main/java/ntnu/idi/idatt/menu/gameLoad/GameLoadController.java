package ntnu.idi.idatt.menu.gameLoad;

import javafx.scene.Parent;
import ntnu.idi.idatt.AppState;
import ntnu.idi.idatt.core.Router;

public class GameLoadController {

  private final GameLoadView view;

  public GameLoadController(GameLoadView view) {
    this.view = view;
    setupEventHandlers();
  }

  public Parent getView() {
    return view;
  }

  private void setupEventHandlers() {
    view.newGameButtonSetOnClick(() -> Router.navigateTo("setup"));
    view.loadGameButtonSetOnClick(() -> Router.navigateTo(AppState.getSelectedGame().getName()));
  }
}
