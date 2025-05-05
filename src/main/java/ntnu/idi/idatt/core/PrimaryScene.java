package ntnu.idi.idatt.core;

import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import ntnu.idi.idatt.UI.components.PauseMenu;

public class PrimaryScene extends Scene {

  private static final int WIDTH = 1280;
  private static final int HEIGHT = 720;
  private final BorderPane borderPane;
  private final PauseMenu pauseMenu;

  public PrimaryScene() {
    super(new StackPane(), WIDTH, HEIGHT);

    this.borderPane = new BorderPane();
    this.pauseMenu = new PauseMenu();
    hidePauseMenu();

    ((StackPane) getRoot()).getChildren().addAll(borderPane, pauseMenu);
  }

  public void setNavBar(Node navBar) {
    borderPane.setTop(navBar);
  }

  public void setContent(Parent content) {
    borderPane.setCenter(content);
  }

  public void showPauseMenu() {
    pauseMenu.setVisible(true);
  }

  public void hidePauseMenu() {
    pauseMenu.setVisible(false);
  }
}