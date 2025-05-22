package ntnu.idi.idatt.components;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

/**
 * Component for displaying a game card with a button and label.
 */
public class GameCard extends VBox {

  private final Button gameButton;

  /**
   * Constructor for the GameCard component.
   *
   * @param gameName The name of the game to be displayed on the card.
   */
  public GameCard(String gameName, String imageUrl) {
    this.getStyleClass().add("game-card");

    gameButton = new Button();
    gameButton.getStyleClass().add("game-card-button");
    if (imageUrl != null) {
      gameButton.setStyle("-fx-background-image: url('" + imageUrl + "');");
    } else {
      gameButton.setText("Game");
    }
    Label gameLabel = new Label(gameName);

    this.getChildren().addAll(gameButton, gameLabel);
  }

  /**
   * Sets the action to be performed when the game button is clicked.
   *
   * @param runnable The action to be performed.
   */
  public void setOnClick(Runnable runnable) {
    gameButton.setOnAction(event -> {
      runnable.run();
    });
  }
}
