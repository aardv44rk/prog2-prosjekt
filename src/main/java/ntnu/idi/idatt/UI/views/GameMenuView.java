package ntnu.idi.idatt.UI.views;

import java.util.ArrayList;
import java.util.List;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import ntnu.idi.idatt.UI.components.GameCard;

public class GameMenuView extends VBox {

  public GameMenuView() {

    this.getStyleClass().add("game-menu-view");

    // Fetch games list
    List<GameCard> games = new ArrayList<>();
    games.add(new GameCard("Snakes and ladders"));
    games.add(new GameCard("Ludo"));
    games.add(new GameCard("Chess"));

    HBox gameCards = new HBox();
    gameCards.getStyleClass().add("game-cards");

    gameCards.getChildren().addAll(games);
    this.getChildren().add(gameCards);
  }
}
