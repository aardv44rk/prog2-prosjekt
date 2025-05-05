package ntnu.idi.idatt.core;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;
import javafx.stage.Stage;

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
      primaryScene.setContent(previous.getContent());
      primaryScene.setNavBar(previous.getNavBar());
    } else {
      navigateTo("home");
    }
  }

  public static void navigateTo(String routeName) {
    // Does route exist
    if (!routes.containsKey(routeName)) {
      throw new IllegalArgumentException("Route " + routeName + " not found");
    }

    // Is route = current route
    Route route = routes.get(routeName);
    boolean isSameAsCurrent =
        !history.isEmpty() && routeName.equals(history.peek().getName());
    if (isSameAsCurrent) {
      throw new IllegalArgumentException("Double navigation to: " + routeName);
    }

    primaryScene.setContent(route.getContent());
    primaryScene.setNavBar(route.getNavBar());
    history.push(route);
  }
}

