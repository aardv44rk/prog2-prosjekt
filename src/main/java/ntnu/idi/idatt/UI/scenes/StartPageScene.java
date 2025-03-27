package ntnu.idi.idatt.UI.scenes;

import ntnu.idi.idatt.UI.views.StartPageView;
import ntnu.idi.idatt.utility.StyleUtil;

public class StartPageScene extends BaseScene {

  public StartPageScene() {
    super(new StartPageView());
    StyleUtil.applyStyleIfExists(this, "start-page.css");
  }
}