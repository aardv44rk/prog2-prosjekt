package ntnu.idi.idatt.menu.gameMenu;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import ntnu.idi.idatt.components.GameCard;
import ntnu.idi.idatt.models.GameInfo;

public class GameMenuView extends VBox {

  HBox gameCardContainer;

  public GameMenuView() {
    getStyleClass().add("game-menu-view");

    gameCardContainer = new HBox();
    gameCardContainer.getStyleClass().add("game-card-container");

    getChildren().add(gameCardContainer);
  }

  public void setGames(List<GameInfo> games, Consumer<GameInfo> onClick) {
    List<GameCard> gameCards = new ArrayList<>();

    for (GameInfo gameInfo : games) {
      GameCard gameCard = new GameCard(gameInfo.getName());
      gameCard.setOnClick(() -> {
        onClick.accept(gameInfo);
      });
      gameCards.add(gameCard);
    }

    gameCardContainer.getChildren().clear();
    gameCardContainer.getChildren().addAll(gameCards);
  }
}
