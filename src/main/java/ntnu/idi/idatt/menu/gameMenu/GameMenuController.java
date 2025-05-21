package ntnu.idi.idatt.menu.gameMenu;

import javafx.scene.Parent;
import ntnu.idi.idatt.AppState;
import ntnu.idi.idatt.core.Router;
import ntnu.idi.idatt.models.GameInfo;
import ntnu.idi.idatt.core.GameRegistry;

public class GameMenuController {

  private final GameMenuView view;

  public GameMenuController(GameMenuView view) {
    this.view = view;
  }

  public Parent getView() {
    view.setGames(
        GameRegistry.games,
        this::onGameSelected
    );
    return view;
  }

  private void onGameSelected(GameInfo game) {
    AppState.setSelectedGame(game);
    Router.navigateTo("load");
  }
}
