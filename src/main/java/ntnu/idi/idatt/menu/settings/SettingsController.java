package ntnu.idi.idatt.menu.settings;

import javafx.scene.Parent;

public class SettingsController {

  private final SettingsView view;

  public SettingsController() {
    this.view = new SettingsView();
  }

  public Parent getView() {
    return view;
  }

}
