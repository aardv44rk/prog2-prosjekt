package ntnu.idi.idatt.components;

import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import ntnu.idi.idatt.core.Router;

public class PauseMenu extends StackPane {

  TextButton resumeButton;
  TextButton saveButton;
  TextButton exitButton;

  public PauseMenu() {
    getStyleClass().add("pause-menu");

    Rectangle blur = new Rectangle(1280, 720);
    blur.getStyleClass().add("background-blur");

    VBox pauseDialog = new VBox();
    pauseDialog.getStyleClass().add("pause-menu-dialog");

    Label title = new Label("Paused");
    title.getStyleClass().add("title");

    resumeButton = new TextButton("Resume");
    saveButton = new TextButton("Save");
    exitButton = new TextButton("Exit");

    pauseDialog.getChildren().addAll(title, resumeButton, saveButton, exitButton);

    getChildren().addAll(blur, pauseDialog);
  }

  public void resumeButtonSetOnClick(Runnable runnable) {
    resumeButton.setOnAction(e -> runnable.run());
  }

  public void saveButtonSetOnClick(Runnable runnable) {
    saveButton.setOnAction(e -> runnable.run());
  }

  public void exitButtonSetOnClick(Runnable runnable) {
    exitButton.setOnAction(e -> runnable.run());
  }
}
