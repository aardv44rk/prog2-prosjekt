package ntnu.idi.idatt.utility;

import java.net.URL;
import javafx.scene.Scene;

public class StyleUtil {

  public static void applyStyleIfExists(Scene scene, String stylesheetPath) {
    URL resource = StyleUtil.class.getResource(stylesheetPath);
    if (resource != null) {
      scene.getStylesheets().add(resource.toExternalForm());
    } else {
      System.err.println("Unable to find stylesheet: " + stylesheetPath);
    }
  }
}
