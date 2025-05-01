package ntnu.idi.idatt.menu.gameLoad;

import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import ntnu.idi.idatt.AppState;
import ntnu.idi.idatt.core.Router;
import ntnu.idi.idatt.UI.components.TextButton;

public class GameLoadView extends VBox {

  TextButton newGameButton;
  TextButton loadGameButton;

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

  public void newGameButtonSetOnClick(Runnable runnable) {
    newGameButton.setOnMouseClicked(e -> runnable.run());
  }

  public void loadGameButtonSetOnClick(Runnable runnable) {
    loadGameButton.setOnMouseClicked(e -> runnable.run());
  }

}
