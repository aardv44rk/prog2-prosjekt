package ntnu.idi.idatt.games.ludo;

import javafx.scene.Parent;

public class LudoController {

  private final LudoView view;

  public LudoController() {
    this.view = new LudoView();
  }

  public Parent getView() {
    return view;
  }
}
