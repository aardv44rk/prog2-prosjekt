package ntnu.idi.idatt;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Stack;
import java.util.function.Supplier;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import ntnu.idi.idatt.UI.scenes.PrimaryScene;

public class Router {

  private static Stage primaryStage;
  private static PrimaryScene primaryScene;
  private static final Map<String, Route> routes = new HashMap<>();
  private static final Stack<Route> history = new Stack<>();

  public static void registerRoute(Route route) {
    routes.put(route.getName(), route);
  }

  public static void setStage(Stage stage) {
    primaryStage = stage;
  }

  public static void setScene(PrimaryScene scene) {
    primaryScene = scene;
    primaryStage.setScene(scene);
  }

  public static void goBack() {
    if (history.size() > 1) {
      history.pop();
      Route previous = history.peek();
      primaryScene.setContent(previous.getContentSupplier().get());
      if (previous.getNavBarSupplier() != null) {
        primaryScene.setNavBar(previous.getNavBarSupplier().get());
      }
    } else {
      navigateTo("start");
    }
  }

  public static void navigateTo(String routeName) {
    if (routes.containsKey(routeName)) {
      Route route = routes.get(routeName);
      boolean isSameAsCurrent =
          !history.isEmpty() && routeName.equals(history.peek().getName());
      if (!isSameAsCurrent) {
        primaryScene.setContent(route.getContentSupplier().get());
        if (route.getNavBarSupplier() != null) {
          primaryScene.setNavBar(route.getNavBarSupplier().get());
        }
        history.push(route);
      } else {
        if (!history.isEmpty()) {
          System.err.println("Double navigation to: " + routeName);
        }
      }
    } else {
      throw new IllegalArgumentException("Route " + routeName + " not found");
    }
  }
}

