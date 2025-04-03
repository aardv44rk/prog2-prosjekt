package ntnu.idi.idatt;

import javafx.application.Application;
import javafx.stage.Stage;
import ntnu.idi.idatt.UI.components.NavBar;
import ntnu.idi.idatt.UI.scenes.PrimaryScene;
import ntnu.idi.idatt.UI.views.GameMenuView;
import ntnu.idi.idatt.UI.views.GameSetupView;
import ntnu.idi.idatt.UI.views.GameLoadView;
import ntnu.idi.idatt.UI.views.SettingsMenuView;
import ntnu.idi.idatt.UI.views.StartPageView;
import ntnu.idi.idatt.UI.views.ViewFactory;
import ntnu.idi.idatt.utility.StyleUtil;


public class Main extends Application {

  @Override
  public void start(Stage stage) {
    Router.setStage(stage);
    PrimaryScene primaryScene = new PrimaryScene();
    StyleUtil.applyStyleIfExists(primaryScene, "/styles/global/global.css");
    Router.setScene(primaryScene);

    Router.registerRoute(new Route("start", null, StartPageView::new));
    Router.registerRoute((new Route("settings", () -> new NavBar("Settings", Router::goBack, false),
        SettingsMenuView::new)));
    Router.registerRoute(
        new Route("menu", () -> new NavBar("Play", Router::goBack, false), GameMenuView::new));
    Router.registerRoute(new Route("load",
        () -> new NavBar(AppState.getSelectedGame().getName(), Router::goBack, false),
        GameLoadView::new));
    Router.registerRoute(new Route("setup",
        () -> new NavBar(AppState.getSelectedGame().getName(), Router::goBack, false),
        GameSetupView::new));
    Router.registerRoute(new Route("game",
        () -> new NavBar(AppState.getSelectedGame().getName(), Router::goBack, true),
        ViewFactory.getGameViewFactory(AppState.getSelectedGame())));

    Router.navigateTo("start");

    stage.show();
  }

  public static void main(String[] args) {
    launch(args);
  }
}
