package ntnu.idi.idatt.menu.settings;

import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

/**
 * The SettingsView class represents the view for the settings menu.
 */
public class SettingsView extends VBox {

  /**
   * Constructor for the SettingsView class.
   */
  public SettingsView() {
    getChildren().add(new Label("Settings"));
  }

}
