package ntnu.idi.idatt.components;

import javafx.scene.control.Button;

/**
 * Component for creating an icon button.
 * This class extends the Button class and adds a specific style class to it.
 */
public class IconButton extends Button {

  /**
   * Constructor for the IconButton class.
   * 
   * @param icon The icon to be displayed on the button.
   */
  public IconButton(String icon) {
    super(icon);
    this.getStyleClass().add("icon-button");
  }
}
