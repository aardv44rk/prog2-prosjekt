package ntnu.idi.idatt.components;

import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

/**
 * PauseMenu component class. Represents the pause menu in the game.
 * Contains buttons for resuming the game, saving the game, saving players, and exiting.
 */
public class PauseMenu extends VBox {

  TextButton resumeButton;
  TextButton saveGameButton;
  TextButton savePlayersButton;
  TextButton exitButton;

  /**
   * Constructor for the PauseMenu class.
   * Initializes the pause menu with buttons and a title.
   */
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

  /**
   * Sets the action to be performed when the resume button is clicked.
   *
   * @param runnable The action to be performed.
   */
  public void resumeButtonSetOnClick(Runnable runnable) {
    resumeButton.setOnAction(e -> runnable.run());
  }

  /**
   * Sets the action to be performed when the save game button is clicked.
   *
   * @param runnable The action to be performed.
   */
  public void saveGameButtonSetOnClick(Runnable runnable) {
    saveGameButton.setOnAction(e -> runnable.run());
  }

  /**
   * Sets the action to be performed when the save players button is clicked.
   *
   * @param runnable The action to be performed.
   */
  public void savePlayersButtonSetOnClick(Runnable runnable) {
    savePlayersButton.setOnAction(e -> runnable.run());
  }

  /**
   * Sets the action to be performed when the exit button is clicked.
   *
   * @param runnable The action to be performed.
   */
  public void exitButtonSetOnClick(Runnable runnable) {
    exitButton.setOnAction(e -> runnable.run());
  }
}
