package ntnu.idi.idatt.menu.gameMenu;

import javafx.scene.Parent;
import ntnu.idi.idatt.AppState;
import ntnu.idi.idatt.core.Router;
import ntnu.idi.idatt.models.GameInfo;
import ntnu.idi.idatt.core.GameRegistry;

/**
 * Controller for the GameMenuView. It handles the logic for selecting a game from the menu.
 */
public class GameMenuController {

  private final GameMenuView view;

  /**
   * Constructor for the GameMenuController.
   *
   * @param view The GameMenuView instance.
   */
  public GameMenuController(GameMenuView view) {
    this.view = view;
  }

  /**
   * Returns the view for the GameMenuController by setting up the game list and
   * the action for when a game is selected.
   *
   * @return The GameMenuView instance.
   */
  public Parent getView() {
    view.setGames(
        GameRegistry.games,
        this::onGameSelected
    );
    return view;
  }

  /**
   * Sets the action to be performed when a game is selected.
   *
   * @param game The selected game.
   */
  private void onGameSelected(GameInfo game) {
    AppState.setSelectedGame(game);
    Router.navigateTo("load");
  }
}
