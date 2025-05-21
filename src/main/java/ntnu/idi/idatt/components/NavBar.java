package ntnu.idi.idatt.components;

import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

/**
 * Navbar component class.
 */
public class NavBar extends HBox {

  /**
   * Constructor for the NavBar class.
   *
   * @param title  The title to be displayed on the navbar.
   * @param action The action to be performed when the back button is clicked.
   * @param isPause Indicates if the navbar is in pause mode.
   */
  public NavBar(String title, Runnable action, boolean isPause) {
    this.getStyleClass().add("menu-nav-bar");

    IconButton backButton = new IconButton(isPause ? "x" : "<");
    backButton.setOnAction(e -> {
      action.run();
    });

    Label titleLabel = new Label(title);
    titleLabel.getStyleClass().add("title");

    this.getChildren().addAll(backButton, titleLabel);
  }
}
