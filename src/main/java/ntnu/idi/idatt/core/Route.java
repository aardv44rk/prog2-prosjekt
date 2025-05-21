package ntnu.idi.idatt.core;

import java.util.function.Supplier;
import javafx.scene.Node;
import javafx.scene.Parent;

public class Route {

  private final String name;
  private final Supplier<Parent> contentSupplier;
  private final Supplier<Node> navBarSupplier;
  private final Object controller;

  public Route(String name, Supplier<Node> navBarSupplier, Supplier<Parent> contentSupplier) {
    this(name, navBarSupplier, contentSupplier, null);
  }

  public Route(String name, Supplier<Node> navBarSupplier, Supplier<Parent> contentSupplier, Object controller) {
    this.name = name;
    this.navBarSupplier = navBarSupplier;
    this.contentSupplier = contentSupplier;
    this.controller = controller;
  }

  public String getName() {
    return name;
  }

  public Parent getContent() {
    return contentSupplier.get();
  }

  public Node getNavBar() {
    return navBarSupplier != null ? navBarSupplier.get() : null;
  }

  public Object getController() {
    return controller;
  }
}
