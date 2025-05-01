package ntnu.idi.idatt.menu.home;

import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import ntnu.idi.idatt.UI.components.TextButton;

public class HomeView extends VBox {

  TextButton startButton;
  TextButton settingsButton;
  TextButton quitButton;

  public HomeView() {
    getStyleClass().add("home-view");

    Label titleTop = new Label("Audun & Oscar");
    Label titleBottom = new Label("Games");
    titleTop.getStyleClass().add("title");
    titleBottom.getStyleClass().add("title");

    VBox title = new VBox(titleTop, titleBottom);

    startButton = new TextButton("Start");
    settingsButton = new TextButton("Settings");
    quitButton = new TextButton("Quit");

    getChildren().addAll(title, startButton, settingsButton, quitButton);
  }

  public void startSetOnClick(Runnable runnable) {
    startButton.setOnAction(e -> runnable.run());
  }

  public void settingsSetOnClick(Runnable runnable) {
    settingsButton.setOnAction(e -> runnable.run());
  }

  public void quitSetOnClick(Runnable runnable) {
    quitButton.setOnAction(e -> runnable.run());
  }
}
