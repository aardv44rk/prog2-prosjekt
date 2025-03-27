package ntnu.idi.idatt.UI.components;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class GameCard extends VBox {

  public GameCard(String game) {
    this.getStyleClass().add("game-card");
    
    Button gameButton = new Button("Game");
    gameButton.setOnAction(e -> {
      System.out.println("Navigating to game: " + game);
    });

    Label gameLabel = new Label(game);

    this.getChildren().addAll(gameButton, gameLabel);
  }
}
