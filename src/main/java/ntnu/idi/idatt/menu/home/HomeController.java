package ntnu.idi.idatt.menu.home;

import javafx.scene.Parent;
import ntnu.idi.idatt.core.Router;

public class HomeController {

  private final HomeView view;

  public HomeController() {
    this.view = new HomeView();
    setupEventHandlers();
  }

  public Parent getView() {
    return view;
  }

  private void setupEventHandlers() {
    view.startSetOnClick(() -> Router.navigateTo("menu"));
    view.settingsSetOnClick(() -> Router.navigateTo("settings"));
    view.quitSetOnClick(() -> System.exit(0));
  }

}
