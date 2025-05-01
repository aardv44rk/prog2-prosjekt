package ntnu.idi.idatt;

import javafx.application.Application;
import javafx.stage.Stage;
import ntnu.idi.idatt.UI.components.NavBar;
import ntnu.idi.idatt.core.PrimaryScene;
import ntnu.idi.idatt.UI.views.GameSetupView;
import ntnu.idi.idatt.menu.gameLoad.GameLoadController;
import ntnu.idi.idatt.menu.gameLoad.GameLoadView;
import ntnu.idi.idatt.UI.views.ViewFactory;
import ntnu.idi.idatt.core.Route;
import ntnu.idi.idatt.core.Router;
import ntnu.idi.idatt.menu.gameMenu.GameMenuController;
import ntnu.idi.idatt.menu.home.HomeController;
import ntnu.idi.idatt.menu.settings.SettingsController;
import ntnu.idi.idatt.utility.StyleUtil;


public class Main extends Application {

  @Override
  public void start(Stage stage) {
    Router.setStage(stage);
    PrimaryScene primaryScene = new PrimaryScene();
    StyleUtil.applyStyleIfExists(primaryScene, "/styles/global/global.css");
    Router.setScene(primaryScene);

    Router.registerRoute(
        new Route(
            "home",
            null,
            new HomeController().getView()));
    Router.registerRoute(
        new Route(
            "settings",
            new NavBar("Settings", Router::goBack, false),
            new SettingsController().getView()));
    Router.registerRoute(
        new Route(
            "menu",
            new NavBar("Play", Router::goBack, false),
            new GameMenuController().getView()));
    Router.registerRoute(
        new Route(
            "load",
            new NavBar(AppState.getSelectedGame().getName(), Router::goBack, false),
            new GameLoadController().getView()));
    Router.registerRoute(
        new Route(
            "setup",
            new NavBar(AppState.getSelectedGame().getName(), Router::goBack, false),
            new GameSetupView()));
    Router.registerRoute(
        new Route(
            "game",
            new NavBar(AppState.getSelectedGame().getName(),
                () -> primaryScene.showPauseMenu(),
                true),
            ViewFactory.getGameView(AppState.getSelectedGame())));

    Router.navigateTo("home");

    stage.show();
  }

  public static void main(String[] args) {
    launch(args);
  }
}
