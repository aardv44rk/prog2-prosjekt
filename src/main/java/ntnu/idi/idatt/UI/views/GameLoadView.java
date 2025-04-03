package ntnu.idi.idatt.UI.views;

import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import ntnu.idi.idatt.AppState;
import ntnu.idi.idatt.UI.components.TextButton;

public class GameLoadView extends VBox {

  public GameLoadView() {
    getStyleClass().add("game-load");

    VBox titleBox = new VBox();
    Label top = new Label(AppState.getSelectedGame().getName());
    Label bottom = new Label("Setup");

    titleBox.getStyleClass().add("game-load-title-box");
    top.getStyleClass().add("title");
    bottom.getStyleClass().add("title");

    titleBox.getChildren().addAll(top, bottom);

    HBox optionBox = new HBox();
    TextButton newGame = new TextButton("New Game");
    TextButton loadGame = new TextButton("Load Game");

    optionBox.getStyleClass().add("game-load-option-box");

    optionBox.getChildren().addAll(newGame, loadGame);

    getChildren().addAll(titleBox, optionBox);
  }
}
