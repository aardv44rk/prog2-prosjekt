package ntnu.idi.idatt.UI.components;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class NewPlayer extends HBox {

  TextField nameField;
  Button removeButton;

  public NewPlayer(Color color, String player, String name) {
    getStyleClass().add("new-player");

    Rectangle rect = new Rectangle(50, 50);
    rect.getStyleClass().add("new-player-rect");
    rect.setFill(color);

    Label playerLabel = new Label(player);
    playerLabel.getStyleClass().add("new-player-label");

    nameField = new TextField(name);
    nameField.getStyleClass().add("new-player-name");

    removeButton = new Button("-");

    getChildren().addAll(rect, playerLabel, nameField, removeButton);
  }

  public String getName() {
    return nameField.getText().trim();
  }

  public void setOnClick(Runnable runnable) {
    removeButton.setOnAction(e -> runnable.run());
  }
}
