package ntnu.idi.idatt.menu.home;

import javafx.scene.Parent;
import ntnu.idi.idatt.core.Router;

/**
 * Controller for the HomeView. It handles the logic for navigating to different parts of the
 * application.
 */
public class HomeController {

  private final HomeView view;

  /**
   * Constructor for the HomeController.
   *
   * @param view The HomeView instance.
   */
  public HomeController(HomeView view) {
    this.view = view;
    setupEventHandlers();
  }

  public Parent getView() {
    return view;
  }

  /**
   * Sets up the event handlers for the buttons in the HomeView.
   */
  private void setupEventHandlers() {
    view.startSetOnClick(() -> Router.navigateTo("menu"));
    view.settingsSetOnClick(() -> Router.navigateTo("settings"));
    view.quitSetOnClick(() -> System.exit(0));
  }

}
