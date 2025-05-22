package ntnu.idi.idatt.utility;

import java.net.URL;
import javafx.scene.Scene;
import javafx.scene.paint.Color;

/**
 * Utility class for applying styles to JavaFX components.
 */
public class StyleUtil {

  private StyleUtil() {
    // Prevent instantiation
  }

  /**
   * Applies a stylesheet to a JavaFX scene if the stylesheet exists.
   *
   * @param scene          The JavaFX scene to apply the stylesheet to.
   * @param stylesheetPath The path to the stylesheet.
   */
  public static void applyStyleIfExists(Scene scene, String stylesheetPath) {
    URL resource = StyleUtil.class.getResource(stylesheetPath);
    if (resource != null) {
      scene.getStylesheets().add(resource.toExternalForm());
    } else {
      System.err.println("Unable to find stylesheet: " + stylesheetPath);
    }
  }

  /**
   * Converts a JavaFX Color object to a CSS RGB string.
   *
   * @param c The JavaFX Color object to convert.
   * @return A string representing the color in CSS RGB format.
   */
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

  public static Color greenRedGradientColor(int value, int min, int max) {
    value = Math.max(min, Math.min(max, value));

    double normalized = (double) (value - min) / (max - min);

    double red = 1.0 - normalized;
    double green = normalized;
    double blue = 0.0;

    double mixFactor = 0.5; // 0 = full base color, 1 = full white
    red = red * (1 - mixFactor) + mixFactor;
    green = green * (1 - mixFactor) + mixFactor;
    blue = blue * (1 - mixFactor) + mixFactor;

    return new Color(red, green, blue, 1.0); // Full opacity
  }

}
