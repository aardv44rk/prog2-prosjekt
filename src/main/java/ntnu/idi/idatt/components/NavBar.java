package ntnu.idi.idatt.components;

import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import ntnu.idi.idatt.AppState;
import ntnu.idi.idatt.core.Router;

/**
 * Navbar component class.
 */
public class NavBar extends BorderPane {

  /**
   * Constructor for the NavBar class.
   *
   * @param title  The title to be displayed on the navbar.
   * @param action The action to be performed when the back button is clicked.
   * @param isPause Indicates if the navbar is in pause mode.
   */
  public NavBar(String title, Runnable action, boolean isPause, boolean hasHelp) {
    getStyleClass().add("nav-bar");

    HBox navBar = new HBox();
    navBar.getStyleClass().add("nav-bar-left");

    IconButton backButton = new IconButton(isPause ? "x" : "<");
    backButton.setOnAction(e -> {
      action.run();
    });

    Label titleLabel = new Label(title);
    titleLabel.getStyleClass().add("title");

    navBar.getChildren().addAll(backButton, titleLabel);
    setLeft(navBar);

    if (hasHelp) {
      IconButton helpButton = new IconButton("?");
      helpButton.getStyleClass().add("nav-bar-help-button");
      helpButton.setOnAction(e -> {
        Router.showAlert("Help", AppState.getSelectedGame().getRules(), "Close", null);
      });
      setRight(helpButton);
    }
  }
}
