package ntnu.idi.idatt.components;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class NewPlayer extends HBox {

  private final Rectangle rect;
  private final Label playerLabel;
  private final TextField playerName;
  private final Button removeButton;

  public NewPlayer(Color color, int playerNumber, String name, boolean removable) {
    getStyleClass().add("new-player");

    rect = new Rectangle(50, 50);
    rect.getStyleClass().add("new-player-rect");
    rect.setFill(color);

    playerLabel = new Label("P" + playerNumber + ":");
    playerLabel.getStyleClass().add("new-player-number-label");

    playerName = new TextField(name);
    playerName.getStyleClass().add("new-player-input");

    if (removable) {
      removeButton = new Button("-");
      removeButton.getStyleClass().add("new-player-remove-button");
      getChildren().addAll(rect, playerLabel, playerName, removeButton);
    } else {
      removeButton = null;
      getChildren().addAll(rect, playerLabel, playerName);
    }
  }

  public String getName() {
    return playerName.getText().trim();
  }

  public int getPlayerNumber() {
    return Integer.parseInt(playerLabel.getText().substring(1, 2));
  }

  public void setPlayerNumber(int playerNumber) {
    playerLabel.setText("P" + playerNumber + ":");
  }

  public void setColor(Color color) {
    rect.setFill(color);
  }

  public void setOnClick(Runnable runnable) {
    if (removeButton == null) {
      return;
    }
    removeButton.setOnAction(e -> runnable.run());
  }
}
