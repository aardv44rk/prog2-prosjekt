package ntnu.idi.idatt.components;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 * Component for displaying a new player in the game.
 */
public class NewPlayer extends HBox {

  private final Rectangle rect;
  private final Label playerLabel;
  private final TextField playerName;
  private final Button removeButton;

  /**
   * Constructor for the NewPlayer component.
   *
   * @param color        The color of the player.
   * @param playerNumber The number of the player.
   * @param name         The name of the player.
   * @param removable    Indicates if the player can be removed.
   */
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

  /**
   * Returns the name of the player, trimmed.
   *
   * @return The name of the player, trimmed.
   */
  public String getName() {
    return playerName.getText().trim();
  }

  /**
   * Gets the number of the player.
   *
   * @return The number of player
   */
  public int getPlayerNumber() {
    return Integer.parseInt(playerLabel.getText().substring(1, 2));
  }

  /**
   * Sets the number of the player, formatted as P#: where # is the number.
   *
   * @param playerNumber The number of the player.
   */
  public void setPlayerNumber(int playerNumber) {
    playerLabel.setText("P" + playerNumber + ":");
  }

  /**
   * Sets the color of the player model, by filling the rectangle with the color.
   *
   * @param color The color of the player.
   */
  public void setColor(Color color) {
    rect.setFill(color);
  }

  /**
   * Sets the action to be performed when the remove button is clicked.
   *
   * @param runnable The action to be performed.
   */
  public void setOnClick(Runnable runnable) {
    removeButton.setOnAction(e -> runnable.run());
  }
}
