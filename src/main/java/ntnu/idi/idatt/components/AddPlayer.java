package ntnu.idi.idatt.components;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

/**
 * Component for adding a player to the game.
 */
public class AddPlayer extends HBox {

  Button addButton;
  TextField playerName;

  /**
   * Constructor for the AddPlayer component.
   */
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

  /**
   * Sets the action to be performed when the add button is clicked.
   *
   * @param runnable The action to be performed.
   */
  public void setOnClick(Runnable runnable) {
    addButton.setOnAction(e -> runnable.run());
  }
}
