package ntnu.idi.idatt.UI.views;

import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import ntnu.idi.idatt.Router;
import ntnu.idi.idatt.UI.components.TextButton;

public class StartPageView extends VBox {

  public StartPageView() {
    getStyleClass().add("start-page");

    Label titleTop = new Label("Audun & Oscar");
    Label titleBottom = new Label("Games");
    titleTop.getStyleClass().add("title");
    titleBottom.getStyleClass().add("title");

    VBox title = new VBox(titleTop, titleBottom);

    TextButton startButton = new TextButton("Start");
    startButton.setOnMouseClicked(e -> {
      Router.navigateTo("menu");
    });

    TextButton settingsButton = new TextButton("Settings");
    settingsButton.setOnMouseClicked(e -> {
      Router.navigateTo("settings");
    });

    TextButton quitButton = new TextButton("Quit");
    quitButton.setOnMouseClicked(e -> {
      System.exit(0);
    });

    getChildren().addAll(title, startButton, settingsButton, quitButton);
  }
}
