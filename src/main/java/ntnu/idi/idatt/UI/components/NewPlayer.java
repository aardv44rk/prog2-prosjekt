package ntnu.idi.idatt.UI.components;

import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class NewPlayer extends HBox {

  public NewPlayer(Color color, String player, String name) {
    this.getStyleClass().add("new-player");

    Rectangle rect = new Rectangle();
    this.getStyleClass().add("new-player-rect");

    Label playerLabel = new Label(player);
    playerLabel.getStyleClass().add("new-player-label");

    Label nameLabel = new Label(name);
    nameLabel.getStyleClass().add("new-player-label");

    getChildren().addAll(rect, playerLabel, nameLabel);
  }
}
