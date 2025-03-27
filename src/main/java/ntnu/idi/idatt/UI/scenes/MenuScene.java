package ntnu.idi.idatt.UI.scenes;

import javafx.scene.Node;
import javafx.scene.layout.BorderPane;
import ntnu.idi.idatt.UI.components.MenuTopBar;
import ntnu.idi.idatt.utility.StyleUtil;

public class MenuScene extends BaseScene {

  public MenuScene(Node menuViewContent, String title) {
    super(new BorderPane());

    BorderPane layout = (BorderPane) getRoot();
    layout.setTop(new MenuTopBar(title, new StartPageScene()));
    layout.setCenter(menuViewContent);

    StyleUtil.applyStyleIfExists(this, "menu.css");
  }
}
