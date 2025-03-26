package ntnu.idi.idatt;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Router {

  private static Stage primaryStage;

  public static void setStage(Stage stage) {
    primaryStage = stage;
  }

  public static void navigateTo(Scene scene) {
    scene.getStylesheets().addAll(AssetRepository.getGlobalStyles());
    primaryStage.setScene(scene);
    primaryStage.show();
  }

  public static void navigateTo(Parent root) {
    Scene scene = new Scene(root);
    navigateTo(scene);
  }
}

