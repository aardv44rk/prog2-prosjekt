package ntnu.idi.idatt.UI.components;

import javafx.scene.control.Button;

public class TextButton extends Button {

  public TextButton(String text) {
    super(text);
    this.getStyleClass().add("text-button");
  }
}
