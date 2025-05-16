package ntnu.idi.idatt.components;

import javafx.geometry.Point2D;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import ntnu.idi.idatt.AssetRepository;

public class UISnakesAndLaddersLadder extends Pane {

  public UISnakesAndLaddersLadder(Point2D start, Point2D end, boolean isAscending) {
    double dx = end.getX() - start.getX();
    double dy = end.getY() - start.getY();

    double totalLength = Math.sqrt(dx * dx + dy * dy);
    double segmentLength = 5;

    double dirX = dx / totalLength;
    double dirY = dy / totalLength;

    double drawnLength = 0;
    int i = 0;

    while (drawnLength + segmentLength <= totalLength) {
      double x1 = start.getX() + dirX * drawnLength;
      double y1 = start.getY() + dirY * drawnLength;
      double x2 = start.getX() + dirX * (drawnLength + segmentLength);
      double y2 = start.getY() + dirY * (drawnLength + segmentLength);

      Line segment = new Line(x1, y1, x2, y2);
      segment.setStroke(
          i % 2 == 0 ? Color.WHITE : (isAscending) ? AssetRepository.LADDER_UP
              : AssetRepository.LADDER_DOWN);
      segment.setStrokeWidth(12);
      getChildren().add(segment);

      drawnLength += segmentLength + 10;
      i++;
    }
  }
}
