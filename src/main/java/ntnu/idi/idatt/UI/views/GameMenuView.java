package ntnu.idi.idatt.UI.views;

import java.util.ArrayList;
import java.util.List;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import ntnu.idi.idatt.AppState;
import ntnu.idi.idatt.Router;
import ntnu.idi.idatt.UI.components.GameCard;
import ntnu.idi.idatt.core.GameInfo;
import ntnu.idi.idatt.core.GameRegistry;

public class GameMenuView extends VBox {

  public GameMenuView() {

    this.getStyleClass().add("game-menu");

    List<GameInfo> games = GameRegistry.games;
    List<GameCard> gameCardList = new ArrayList<>();

    games.forEach(gameInfo -> {
      GameCard gameCard = new GameCard(gameInfo.getName());
      gameCard.setOnClick(() -> {
        AppState.setSelectedGame(gameInfo);
        Router.navigateTo("load");
      });
      gameCardList.add(gameCard);
    });

    HBox gameCards = new HBox();
    gameCards.getStyleClass().add("game-cards");

    gameCards.getChildren().addAll(gameCardList);
    this.getChildren().add(gameCards);
  }
}
