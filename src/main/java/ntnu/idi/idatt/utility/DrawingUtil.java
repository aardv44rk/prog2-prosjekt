package ntnu.idi.idatt.utility;

import javafx.geometry.Bounds;
import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.Parent;

/**
 * Utility class for drawing-related operations in JavaFX.
 */
public class DrawingUtil {

  /**
   * Calculates the center coordinates of a node relative to its parent.
   *
   * @param parent The parent node.
   * @param node   The child node.
   * @return The center coordinates of the child node relative to the parent.
   */
  public static Point2D getCenterCoords(Parent parent, Node node) {
    Bounds bounds = node.localToScene(node.getBoundsInLocal());

    double centerX = bounds.getMinX() + bounds.getWidth() / 2;
    double centerY = bounds.getMinY() + bounds.getHeight() / 2;

    return parent.sceneToLocal(new Point2D(centerX, centerY));
  }

  /**
   * Calculates the center coordinates of a node relative to its parent, with an offset node.
   *
   * @param parent      The parent node.
   * @param node        The child node.
   * @param offsetNode  The offset node.
   * @return The center coordinates of the child node relative to the parent, adjusted for the offset node.
   */
  public static Point2D getCenterCoordsOffsetForNode(Parent parent, Node node, Node offsetNode) {
    Bounds tileBoundsInLocal = node.getBoundsInLocal();
    Bounds tileBoundsInScene = node.localToScene(tileBoundsInLocal);
    Bounds pieceBounds = offsetNode.getLayoutBounds();

    double centerX = tileBoundsInScene.getMinX() + tileBoundsInScene.getWidth() / 2;
    double centerY = tileBoundsInScene.getMinY() + tileBoundsInScene.getHeight() / 2;

    double layoutX = centerX - pieceBounds.getWidth() / 2;
    double layoutY = centerY - pieceBounds.getHeight() / 2;

    return parent.sceneToLocal(new Point2D(layoutX, layoutY));
  }

}
