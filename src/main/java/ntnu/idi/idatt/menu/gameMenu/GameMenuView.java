package ntnu.idi.idatt.menu.gameMenu;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import ntnu.idi.idatt.components.GameCard;
import ntnu.idi.idatt.models.GameInfo;

/**
 * The GameMenuView class represents the view for the game menu. It displays a list of game cards
 * that users can select to start a game.
 */
public class GameMenuView extends VBox {

  HBox gameCardContainer;

  /**
   * Constructor for the GameMenuView class. It initializes the view with a container for game cards.
   */
  public GameMenuView() {
    getStyleClass().add("game-menu-view");

    gameCardContainer = new HBox();
    gameCardContainer.getStyleClass().add("game-card-container");

    getChildren().add(gameCardContainer);
  }

  /**
   * Sets the list of games to be displayed in the game menu. Each game card is clickable and
   * triggers the provided action when clicked.
   *
   * @param games   The list of GameInfo objects representing the games.
   * @param onClick The action to be performed when a game card is clicked.
   */
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
