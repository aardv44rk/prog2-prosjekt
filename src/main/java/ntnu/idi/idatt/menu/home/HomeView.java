package ntnu.idi.idatt.menu.home;

import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import ntnu.idi.idatt.components.TextButton;

/**
 * Class representing the main menu off the application.
 */
public class HomeView extends VBox {

  TextButton startButton;
  TextButton settingsButton;
  TextButton quitButton;

  /**
   * Constructor for the HomeView class. It initializes the view with a title and buttons.
   */
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

  /**
   * Sets the action to be performed when the start button is clicked.
   *
   * @param runnable The action to be performed.
   */
  public void startSetOnClick(Runnable runnable) {
    startButton.setOnAction(e -> runnable.run());
  }

  /**
   * Sets the action to be performed when the settings button is clicked.
   *
   * @param runnable The action to be performed.
   */
  public void settingsSetOnClick(Runnable runnable) {
    settingsButton.setOnAction(e -> runnable.run());
  }

  /**
   * Sets the action to be performed when the quit button is clicked.
   *
   * @param runnable The action to be performed.
   */
  public void quitSetOnClick(Runnable runnable) {
    quitButton.setOnAction(e -> runnable.run());
  }
}
