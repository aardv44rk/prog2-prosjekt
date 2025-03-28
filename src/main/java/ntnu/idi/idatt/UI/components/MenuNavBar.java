package ntnu.idi.idatt.UI.components;

import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.Scene;
import ntnu.idi.idatt.Router;

public class MenuNavBar extends HBox {

  public MenuNavBar(String title, Runnable action, boolean isPause) {
    this.getStyleClass().add("menu-nav-bar");

    IconButton backButton = new IconButton(isPause ? "x" : "<");
    backButton.setOnMouseClicked(e -> {
      action.run();
    });

    Label titleLabel = new Label(title);
    titleLabel.getStyleClass().add("title");

    this.getChildren().addAll(backButton, titleLabel);
  }
}
