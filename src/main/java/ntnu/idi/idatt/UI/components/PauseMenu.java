package ntnu.idi.idatt.UI.components;

import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import ntnu.idi.idatt.Router;

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
      hidePauseMenu();
    });

    TextButton settingsButton = new TextButton("Save");
    settingsButton.setOnMouseClicked(e -> {
      System.out.println("Saving...");
    });

    TextButton exitButton = new TextButton("Exit");
    exitButton.setOnMouseClicked(e -> {
      Router.navigateTo("start");
    });
    pauseDialog.getChildren().addAll(title, resumeButton, settingsButton, exitButton);

    this.getChildren().addAll(blur, pauseDialog);
  }

  public void showPauseMenu() {
    this.setVisible(true);
    System.out.println("Showing PauseMenu");
  }

  public void hidePauseMenu() {
    this.setVisible(false);
    System.out.println("Hiding PauseMenu");
  }

}
