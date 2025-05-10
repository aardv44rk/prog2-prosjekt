package ntnu.idi.idatt.components;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class GameCard extends VBox {

  private final Button gameButton;

  public GameCard(String gameName) {
    this.getStyleClass().add("game-card");

    gameButton = new Button("Game");
    Label gameLabel = new Label(gameName);

    this.getChildren().addAll(gameButton, gameLabel);
  }

  public void setOnClick(Runnable runnable) {
    gameButton.setOnMouseClicked(event -> {
      runnable.run();
    });
  }
}
