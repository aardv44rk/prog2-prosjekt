package ntnu.idi.idatt.UI.scenes;

import javafx.scene.Node;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import ntnu.idi.idatt.UI.components.MenuNavBar;
import ntnu.idi.idatt.UI.components.PauseMenu;
import ntnu.idi.idatt.utility.StyleUtil;

public class GameScene extends BaseScene {

  public GameScene(Node game, String title) {
    super(new StackPane());

    PauseMenu pauseMenu = new PauseMenu();
    pauseMenu.hidePauseMenu();

    BorderPane layout = new BorderPane();
    layout.setTop(new MenuNavBar(title, pauseMenu::showPauseMenu, true));
    layout.setCenter(game);

    StackPane rootPane = (StackPane) getRoot();
    rootPane.getChildren().addAll(layout, pauseMenu);

    StyleUtil.applyStyleIfExists(this, "game.css");
  }
}