package ntnu.idi.idatt.UI.scenes;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;

public class PrimaryScene extends Scene {

  private static final BorderPane borderPane = new BorderPane();
  private static final int WIDTH = 1280;
  private static final int HEIGHT = 720;

  public PrimaryScene() {
    super(borderPane, WIDTH, HEIGHT);
  }

  public void setNavBar(Parent navBar) {
    borderPane.setTop(navBar);
  }

  public void setContent(Parent content) {
    borderPane.setCenter(content);
  }
}