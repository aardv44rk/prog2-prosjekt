package ntnu.idi.idatt;

import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import ntnu.idi.idatt.UI.scenes.StartPageScene;

public class Main extends Application {

  @Override
  public void start(Stage stage) {
    Router.setStage(stage);

    // Replace with your actual first scene
    Scene root = new StartPageScene();
    Router.navigateTo(root);
  }

  public static void main(String[] args) {
    launch(args);
  }
}
