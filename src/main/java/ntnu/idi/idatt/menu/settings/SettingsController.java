package ntnu.idi.idatt.menu.settings;

import javafx.scene.Parent;

/**
 * Controller for the SettingsView. It handles the logic for the settings menu.
 */
public class SettingsController {

  private final SettingsView view;

  /**
   * Constructor for the SettingsController.
   *
   * @param view The SettingsView instance.
   */
  public SettingsController(SettingsView view) {
    this.view = view;
  }

  public Parent getView() {
    return view;
  }

}
