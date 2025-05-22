package ntnu.idi.idatt.components;

import javafx.scene.control.Label;

/**
 * Component for displaying a die in the UI.
 */
public class UIDie extends Label {

  /**
   * Constructor for the UIDie class.
   * Initializes the die with a specific number of eyes and adds a style class.
   *
   * @param eyes The number of eyes on the die.
   */
  public UIDie(int eyes) {
    getStyleClass().add("die");
    this.setText(Integer.toString(eyes));
  }

  /**
   * Sets the number of eyes on the die.
   *
   * @param eyes The number of eyes to be displayed on the die.
   */
  public void setEyes(int eyes) {
    setText(Integer.toString(eyes));
  }
}
