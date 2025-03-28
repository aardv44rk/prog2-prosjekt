package ntnu.idi.idatt.UI.components;

import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import ntnu.idi.idatt.Router;
import ntnu.idi.idatt.UI.scenes.GameScene;
import ntnu.idi.idatt.UI.views.SnakesAndLaddersView;

public class GameCard extends VBox {

  public GameCard(Node game, String gameName) {
    this.getStyleClass().add("game-card");

    Button gameButton = new Button("Game");
    gameButton.setOnAction(e -> {
      // System.out.println("Navigating to game: " + game);
      Router.navigateTo(new GameScene(game, gameName));
    });

    Label gameLabel = new Label(gameName);

    this.getChildren().addAll(gameButton, gameLabel);
  }
}
