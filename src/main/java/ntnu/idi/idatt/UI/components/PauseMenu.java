package ntnu.idi.idatt.UI.components;

import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import ntnu.idi.idatt.core.Router;

public class PauseMenu extends StackPane {

  public PauseMenu() {
    this.getStyleClass().add("pause-menu");

    Rectangle blur = new Rectangle(1280, 720);
    blur.getStyleClass().add("pause-menu-blur");

    VBox pauseDialog = new VBox();
    pauseDialog.getStyleClass().add("pause-menu-dialog");

    Label title = new Label("Paused");
    title.getStyleClass().add("title");

    TextButton resumeButton = new TextButton("Resume");
    resumeButton.setOnMouseClicked(e -> {
      this.setVisible(false);
    });

    TextButton settingsButton = new TextButton("Save");
    settingsButton.setOnMouseClicked(e -> {
      System.out.println("Saving...");
    });

    TextButton exitButton = new TextButton("Exit");
    exitButton.setOnMouseClicked(e -> {
      this.setVisible(false);
      Router.navigateTo("home");
    });
    pauseDialog.getChildren().addAll(title, resumeButton, settingsButton, exitButton);

    this.getChildren().addAll(blur, pauseDialog);
  }
}
