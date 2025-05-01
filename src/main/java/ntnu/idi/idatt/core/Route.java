package ntnu.idi.idatt.core;

import java.util.function.Supplier;
import javafx.scene.Parent;

public class Route {

  private final String name;
  private final Parent navBar;
  private final Parent content;

  public Route(String name, Parent navBar, Parent content) {
    this.name = name;
    this.navBar = navBar;
    this.content = content;
  }

  public String getName() {
    return name;
  }

  public Parent getNavBar() {
    return navBar;
  }

  public Parent getContent() {
    return content;
  }
}