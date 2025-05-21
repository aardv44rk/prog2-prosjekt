package ntnu.idi.idatt.menu.gameLoad;

import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import ntnu.idi.idatt.AppState;
import ntnu.idi.idatt.components.TextButton;

/**
 * The GameLoadView class represents the view for loading a game. It displays the game title and
 * provides buttons for starting a new game or loading an existing one.
 */
public class GameLoadView extends VBox {

  TextButton newGameButton;
  TextButton loadGameButton;

  /**
   * Constructor for the GameLoadView class. It initializes the view with a title and buttons.
   */
  public GameLoadView() {
    getStyleClass().add("game-load-view");

    VBox titleBox = new VBox();
    Label top = new Label(AppState.getSelectedGame().getName());
    Label bottom = new Label("Setup");

    titleBox.getStyleClass().add("game-load-title-box");
    top.getStyleClass().add("title");
    bottom.getStyleClass().add("title");

    titleBox.getChildren().addAll(top, bottom);

    HBox optionBox = new HBox();
    newGameButton = new TextButton("New Game");
    loadGameButton = new TextButton("Load Game");
    optionBox.getStyleClass().add("game-load-option-box");
    optionBox.getChildren().addAll(newGameButton, loadGameButton);

    getChildren().addAll(titleBox, optionBox);
  }

  /**
   * Sets the action to be performed when the new game button is clicked.
   *
   * @param runnable The action to be performed.
   */
  public void newGameButtonSetOnClick(Runnable runnable) {
    newGameButton.setOnAction(e -> runnable.run());
  }

  /**
   * Sets the action to be performed when the load game button is clicked.
   *
   * @param runnable The action to be performed.
   */
  public void loadGameButtonSetOnClick(Runnable runnable) {
    loadGameButton.setOnAction(e -> runnable.run());
  }

}
