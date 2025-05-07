package ntnu.idi.idatt.components;

import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

public class NavBar extends HBox {

  public NavBar(String title, Runnable action, boolean isPause) {
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
