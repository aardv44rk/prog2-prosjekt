package ntnu.idi.idatt.core;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;
import javafx.stage.Stage;
import ntnu.idi.idatt.components.AlertDialog;
import ntnu.idi.idatt.components.PauseMenu;
import ntnu.idi.idatt.exceptions.InvalidInputException;

/**
 * Router class for managing navigation between different views in the application.
 */
public class Router {

  private static Stage primaryStage;
  private static PrimaryScene primaryScene;
  private static final Map<String, Route> routes = new HashMap<>();
  private static final Stack<Route> history = new Stack<>();
  private static final PauseMenu pauseMenu = new PauseMenu();

  static {
    pauseMenu.resumeButtonSetOnClick(() -> primaryScene.removeNode(pauseMenu));
    pauseMenu.saveGameButtonSetOnClick(() -> System.out.println("Saving game..."));
    pauseMenu.savePlayersButtonSetOnClick(() -> System.out.println("Saving players..."));
    pauseMenu.exitButtonSetOnClick(() -> {
      primaryScene.removeNode(pauseMenu);
      navigateTo("home");
    });
  }

  /**
   * Registers a new route in the router.
   *
   * @param route The route to register.
   */
  public static void registerRoute(Route route) {
    routes.put(route.getName(), route);
  }

  public static void setStage(Stage stage) {
    primaryStage = stage;
  }

  public static Stage getStage() {
    return primaryStage;
  }

  /**
   * Sets the primary scene for the router.
   */
  public static void setScene(PrimaryScene scene) {
    primaryScene = scene;
    primaryStage.setScene(primaryScene);
  }

  /**
   * Returns to home screen.
   */
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

  /**
   * Navigates to a specified route.
   *
   * @param routeName The name of the route to navigate to.
   */
  public static void navigateTo(String routeName) {
    // Does route exist
    if (!routes.containsKey(routeName)) {
      throw new IllegalStateException("Route " + routeName + " not found");
    }

    // Is route = current route
    Route route = routes.get(routeName);
    boolean isSameAsCurrent =
        !history.isEmpty() && routeName.equals(history.peek().getName());
    if (isSameAsCurrent) {
      throw new InvalidInputException("Double navigation to: " + routeName);
    }

    primaryScene.setContent(route.getContent());
    primaryScene.setNavBar(route.getNavBar());
    history.push(route);
  }

  /**
   * Shows an alert dialog with a title, message, and button text.
   *
   * @param title       The title of the alert dialog.
   * @param message     The message to display in the alert dialog.
   * @param buttonText  The text for the button in the alert dialog.
   * @param runnable    A runnable to execute when the button is clicked.
   */
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

  /**
   * Shows the pause menu.
   */
  public static void showPauseMenu() {
    primaryScene.addNode(pauseMenu);
  }
}

