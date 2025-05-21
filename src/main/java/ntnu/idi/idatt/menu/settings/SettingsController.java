package ntnu.idi.idatt.menu.settings;

import javafx.scene.Parent;

public class SettingsController {

  private final SettingsView view;

  public SettingsController(SettingsView view) {
    this.view = view;
  }

  public Parent getView() {
    return view;
  }

}
