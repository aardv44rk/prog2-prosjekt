package ntnu.idi.idatt.UI.components;

import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.Scene;
import ntnu.idi.idatt.Router;

public class MenuTopBar extends HBox {

  public MenuTopBar(String title, Scene backDestination) {
    this.getStyleClass().add("menu-top-bar");

    IconButton backButton = new IconButton("<");
    backButton.setOnMouseClicked(e -> {
      Router.navigateTo(backDestination);
    });

    Label titleLabel = new Label(title);
    titleLabel.getStyleClass().add("title");

    this.getChildren().addAll(backButton, titleLabel);
  }
}
