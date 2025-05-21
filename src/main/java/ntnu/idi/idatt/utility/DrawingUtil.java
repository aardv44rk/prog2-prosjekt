package ntnu.idi.idatt.utility;

import javafx.geometry.Bounds;
import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.Parent;

public class DrawingUtil {

  public static Point2D getCenterCoords(Parent parent, Node node) {
    Bounds bounds = node.localToScene(node.getBoundsInLocal());

    double centerX = bounds.getMinX() + bounds.getWidth() / 2;
    double centerY = bounds.getMinY() + bounds.getHeight() / 2;

    return parent.sceneToLocal(new Point2D(centerX, centerY));
  }

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
