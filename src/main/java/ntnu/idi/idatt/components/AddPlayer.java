package ntnu.idi.idatt.components;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

public class AddPlayer extends HBox {

  Button addButton;
  TextField playerName;

  public AddPlayer() {
    getStyleClass().add("add-player");

    addButton = new Button("+");
    addButton.getStyleClass().add("add-player-button");

    Label addLabel = new Label("Add:");
    addLabel.getStyleClass().add("add-player-label");

    playerName = new TextField();
    playerName.getStyleClass().add("add-player-input");

    getChildren().addAll(addButton, addLabel, playerName);
  }

  public String getName() {
    return playerName.getText().trim();
  }

  public void setOnClick(Runnable runnable) {
    addButton.setOnAction(e -> runnable.run());
  }
}
