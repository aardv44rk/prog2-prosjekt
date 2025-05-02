package ntnu.idi.idatt.menu.gameLoad;

import javafx.scene.Parent;
import ntnu.idi.idatt.core.Router;

public class GameLoadController {

  private final GameLoadView view;

  public GameLoadController() {
    this.view = new GameLoadView();
    setupEventHandlers();
  }

  public Parent getView() {
    return view;
  }

  public void setupEventHandlers() {
    view.newGameButtonSetOnClick(() -> Router.navigateTo("setup"));
    view.loadGameButtonSetOnClick(() -> Router.navigateTo("game"));
  }
}
