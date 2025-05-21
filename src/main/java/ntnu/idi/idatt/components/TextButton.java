package ntnu.idi.idatt.components;

import javafx.scene.control.Button;

/**
 * Component for creating an icon button.
 * This class extends the Button class and adds a specific style class to it.
 */
public class TextButton extends Button {

  /**
   * Constructor for the TextButton class.
   * Initializes the button with the given text and adds a specific style class.
   *
   * @param text The text to be displayed on the button.
   */
  public TextButton(String text) {
    super(text);
    this.getStyleClass().add("text-button");
  }
}
