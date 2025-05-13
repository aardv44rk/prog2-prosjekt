package ntnu.idi.idatt.utility;

import java.net.URL;
import javafx.scene.Scene;
import javafx.scene.paint.Color;

public class StyleUtil {

  public static void applyStyleIfExists(Scene scene, String stylesheetPath) {
    URL resource = StyleUtil.class.getResource(stylesheetPath);
    if (resource != null) {
      scene.getStylesheets().add(resource.toExternalForm());
    } else {
      System.err.println("Unable to find stylesheet: " + stylesheetPath);
    }
  }

  public static String toRgbString(Color c) {
    int r = (int) (c.getRed() * 255);
    int g = (int) (c.getGreen() * 255);
    int b = (int) (c.getBlue() * 255);
    double opacity = c.getOpacity();

    if (opacity < 1.0) {
      return String.format("rgba(%d, %d, %d, %.3f)", r, g, b, opacity);
    } else {
      return String.format("#%02X%02X%02X", r, g, b);
    }
  }

}
