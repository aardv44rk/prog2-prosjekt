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
   * Finds the file path for an image given the image name.
   *
   * @param imageName The name of the image.
   * @return The image path as a string if found; or else {@code} null.
   */
  public static String getImagePath(String imageName) {
    URL resource = StyleUtil.class.getResource("/images/" + imageName + ".png");
    if (resource != null) {
      return resource.toExternalForm();
    }
    System.err.println("Unable to find image path: " + imageName);
    return null;
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

  /**
   * Generates a color gradient from green to red based on a value within a specified range.
   *
   * @param value The value to be converted to a color.
   * @param min   The minimum value of the range.
   * @param max   The maximum value of the range.
   * @return A Color object representing the gradient color.
   */
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
