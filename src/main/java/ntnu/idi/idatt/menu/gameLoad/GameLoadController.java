package ntnu.idi.idatt.menu.gameLoad;

import javafx.scene.Parent;
import ntnu.idi.idatt.AppState;
import ntnu.idi.idatt.core.Router;

/**
 * Controller for the GameLoadView. It handles the logic for loading a game or starting a new one.
 */
public class GameLoadController {

  private final GameLoadView view;

  /**
   * Constructor for the GameLoadController.
   *
   * @param view The GameLoadView instance.
   */
  public GameLoadController(GameLoadView view) {
    this.view = view;
    setupEventHandlers();
  }

  public Parent getView() {
    return view;
  }

  /**
   * Sets up the event handlers for the buttons in the GameLoadView.
   */
  private void setupEventHandlers() {
    view.newGameButtonSetOnClick(() -> Router.navigateTo("setup"));
    view.loadGameButtonSetOnClick(() -> Router.navigateTo(AppState.getSelectedGame().getName()));
  }
}
