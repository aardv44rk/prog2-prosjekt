package ntnu.idi.idatt;

import java.util.function.Supplier;
import javafx.scene.Parent;

public class Route {

  private final String name;
  private final Supplier<Parent> navBarSupplier;
  private final Supplier<Parent> contentSupplier;

  public Route(String name, Supplier<Parent> navBarSupplier, Supplier<Parent> contentSupplier) {
    this.name = name;
    this.navBarSupplier = navBarSupplier;
    this.contentSupplier = contentSupplier;
  }

  public String getName() {
    return name;
  }

  public Supplier<Parent> getNavBarSupplier() {
    return navBarSupplier;
  }

  public Supplier<Parent> getContentSupplier() {
    return contentSupplier;
  }
}