package ntnu.idi.idatt.utility;

import java.net.URL;
import javafx.scene.Scene;
import ntnu.idi.idatt.AssetRepository;

public class StyleUtil {

  public static void applyStyleIfExists(Scene scene, String stylesheetPath) {
    URL resource = StyleUtil.class.getResource("/styles/scenes/" + stylesheetPath);
    if (resource != null) {
      scene.getStylesheets().add(resource.toExternalForm());
    } else {
      System.err.println("Unable to find stylesheet: " + stylesheetPath);
    }
  }

  public static void applyGlobalStyleSheets(Scene scene) {
    for (String stylesheet : AssetRepository.getGlobalStyles()) {
      scene.getStylesheets().add(stylesheet);
    }
  }
}
