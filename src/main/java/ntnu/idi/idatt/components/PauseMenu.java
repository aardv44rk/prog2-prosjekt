package ntnu.idi.idatt.components;

import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class PauseMenu extends VBox {

  TextButton resumeButton;
  TextButton saveGameButton;
  TextButton savePlayersButton;
  TextButton exitButton;

  public PauseMenu() {
    getStyleClass().add("pause-menu");

    Label title = new Label("Paused");
    title.getStyleClass().add("title");

    resumeButton = new TextButton("Resume");
    saveGameButton = new TextButton("Save game");
    savePlayersButton = new TextButton("Save players");
    exitButton = new TextButton("Exit");

    getChildren().addAll(title, resumeButton, saveGameButton, savePlayersButton, exitButton);
  }

  public void resumeButtonSetOnClick(Runnable runnable) {
    resumeButton.setOnAction(e -> runnable.run());
  }

  public void saveGameButtonSetOnClick(Runnable runnable) {
    saveGameButton.setOnAction(e -> runnable.run());
  }

  public void savePlayersButtonSetOnClick(Runnable runnable) {
    savePlayersButton.setOnAction(e -> runnable.run());
  }


  public void exitButtonSetOnClick(Runnable runnable) {
    exitButton.setOnAction(e -> runnable.run());
  }
}
