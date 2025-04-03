package ntnu.idi.idatt.UI.scenes;

import javafx.scene.Node;
import javafx.scene.layout.BorderPane;
import ntnu.idi.idatt.Router;
import ntnu.idi.idatt.UI.components.MenuNavBar;
import ntnu.idi.idatt.UI.views.StartPageView;
import ntnu.idi.idatt.utility.StyleUtil;

public class MenuScene extends BaseScene {

  public MenuScene(Node menuViewContent, String title) {
    super(new BorderPane());

    BorderPane layout = (BorderPane) getRoot();
    layout.setTop(new MenuNavBar(
        title,
        () -> Router.navigateTo(new StartPageScene()),
        false)
    );
    layout.setCenter(menuViewContent);

    StyleUtil.applyStyleIfExists(this, "menu.css");
  }
}
