package ntnu.idi.idatt;

import javafx.application.Application;
import javafx.stage.Stage;
import ntnu.idi.idatt.components.NavBar;
import ntnu.idi.idatt.core.PrimaryScene;
import ntnu.idi.idatt.games.ludo.LudoController;
import ntnu.idi.idatt.games.snakesandladders.SnakesAndLaddersController;
import ntnu.idi.idatt.menu.gameSetup.GameSetupController;
import ntnu.idi.idatt.menu.gameSetup.GameSetupView;
import ntnu.idi.idatt.menu.gameLoad.GameLoadController;
import ntnu.idi.idatt.menu.gameLoad.GameLoadView;
import ntnu.idi.idatt.core.Route;
import ntnu.idi.idatt.core.Router;
import ntnu.idi.idatt.menu.gameMenu.GameMenuController;
import ntnu.idi.idatt.menu.gameMenu.GameMenuView;
import ntnu.idi.idatt.menu.home.HomeController;
import ntnu.idi.idatt.menu.home.HomeView;
import ntnu.idi.idatt.menu.settings.SettingsController;
import ntnu.idi.idatt.menu.settings.SettingsView;
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
            () -> null,
            () -> new HomeController(new HomeView()).getView()));
    Router.registerRoute(
        new Route(
            "settings",
            () -> new NavBar("Settings", Router::goBack, false),
            () -> new SettingsController(new SettingsView()).getView()));
    Router.registerRoute(
        new Route(
            "menu",
            () -> new NavBar("Play", Router::goBack, false),
            () -> new GameMenuController(new GameMenuView()).getView()));
    Router.registerRoute(
        new Route(
            "load",
            () -> new NavBar(AppState.getSelectedGame().getName(), Router::goBack, false),
            () -> new GameLoadController(new GameLoadView()).getView()));
    Router.registerRoute(
        new Route(
            "setup",
            () -> new NavBar(AppState.getSelectedGame().getName(), Router::goBack, false),
            () -> new GameSetupController(new GameSetupView()).getViewForGame(AppState.getSelectedGame())));
    Router.registerRoute(
        new Route(
            "Snakes and Ladders",
            () -> new NavBar(
                AppState.getSelectedGame().getName(),
                Router::showPauseMenu,
                true),
            () -> new SnakesAndLaddersController().getView()
        ));
    Router.registerRoute(
        new Route(
            "Ludo",
            () -> new NavBar(
                AppState.getSelectedGame().getName(),
                Router::showPauseMenu,
                true),
            () -> new LudoController().getView()));

    Router.navigateTo("home");

    stage.show();
  }

  public static void main(String[] args) {
    launch(args);
  }
}
