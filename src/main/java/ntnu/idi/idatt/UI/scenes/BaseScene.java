package ntnu.idi.idatt.UI.scenes;

import javafx.scene.Parent;
import javafx.scene.Scene;

public abstract class BaseScene extends Scene {

  private static final int WIDTH = 1280;
  private static final int HEIGHT = 720;

  public BaseScene(Parent root) {
    super(root, WIDTH, HEIGHT);
  }
}