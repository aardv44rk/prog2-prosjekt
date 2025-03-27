package ntnu.idi.idatt.UI.components;

import javafx.scene.control.Button;

public class IconButton extends Button {

  public IconButton(String icon) {
    super(icon);
    this.getStyleClass().add("icon-button");
  }
}
