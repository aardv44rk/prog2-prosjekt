package ntnu.idi.idatt.core;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;
import javafx.stage.Stage;
import ntnu.idi.idatt.components.AlertDialog;
import ntnu.idi.idatt.components.PauseMenu;

public class Router {

  private static Stage primaryStage;
  private static PrimaryScene primaryScene;
  private static final Map<String, Route> routes = new HashMap<>();
  private static final Stack<Route> history = new Stack<>();
  private static final PauseMenu pauseMenu = new PauseMenu();

  static {
    pauseMenu.resumeButtonSetOnClick(() -> primaryScene.removeNode(pauseMenu));
    pauseMenu.saveButtonSetOnClick(() -> System.out.println("Saving..."));
    pauseMenu.exitButtonSetOnClick(() -> {
      primaryScene.removeNode(pauseMenu);
      navigateTo("home");
    });
  }

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

  public static void showAlert(String title, String message, String buttonText, Runnable runnable) {
    AlertDialog alertDialog = new AlertDialog(title, message, buttonText, AlertDialogType.INFO);
    alertDialog.buttonSetOnClick(() -> {
      if (runnable != null) {
        runnable.run();
      }
      primaryScene.removeNode(alertDialog);
    });
    primaryScene.addNode(alertDialog);
  }

  public static void showPauseMenu() {
    primaryScene.addNode(pauseMenu);
  }
}

