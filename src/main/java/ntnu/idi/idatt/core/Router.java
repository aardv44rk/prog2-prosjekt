package ntnu.idi.idatt.core;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;
import javafx.stage.Stage;
import ntnu.idi.idatt.AppState;
import ntnu.idi.idatt.components.AlertDialog;
import ntnu.idi.idatt.components.PauseMenu;
import ntnu.idi.idatt.games.snakesandladders.SnakesAndLaddersController;

public class Router {

  private static Stage primaryStage;
  private static PrimaryScene primaryScene;
  private static final Map<String, Route> routes = new HashMap<>();
  private static final Stack<Route> history = new Stack<>();
  private static final PauseMenu pauseMenu = new PauseMenu();
  private static Route currentRoute;

  static {
    pauseMenu.resumeButtonSetOnClick(() -> {
      if (primaryScene != null) {
        primaryScene.removeNode(pauseMenu);
      }
    });
    pauseMenu.saveGameButtonSetOnClick(() -> {
      if (primaryScene != null && primaryScene.getSaveGameAction() != null) {
        String gameName = "default";
        if (AppState.getSelectedGame().getName() != null) {
          gameName = AppState.getSelectedGame().getName().replaceAll("\\s+", "_").toLowerCase();
        }
        String fileName = gameName + "_quicksave.json";
        try {
          primaryScene.getSaveGameAction().accept(fileName);
          showAlert("Game saved", "Game saved to " + fileName, "OK", null);
        } catch (Exception e) {
          showAlert("Error", "Cannot save game right now.", "OK", null);
        }
      } else {
        showAlert("Error", "Cannot save game right now.", "OK", null);
      }
      if (primaryScene != null) {
        primaryScene.removeNode(pauseMenu);
      }
    });
    pauseMenu.savePlayersButtonSetOnClick(() -> {
      System.out.println("Save players button clicked"); // TODO we need to implement this too
    });
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

  public static PrimaryScene getPrimaryScene() {
    return primaryScene;
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

    Object controller = route.getController();
    if (controller instanceof SnakesAndLaddersController) {
      SnakesAndLaddersController snlController = (SnakesAndLaddersController) controller;
      primaryScene.setSaveGameAction(snlController::saveGame);
    } else {
      // TODO implement other game controllers
      primaryScene.setSaveGameAction(null);
    }
    
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

  private static void activateRoute(Route route) {
    primaryScene.setNavBar(route.getNavBar());
    primaryScene.setContent(route.getContent());

    primaryScene.setSaveGameAction(null);

    if (route.getController() instanceof SnakesAndLaddersController) {
      SnakesAndLaddersController snlController = (SnakesAndLaddersController) route.getController();
      primaryScene.setSaveGameAction(snlController::saveGame);
    } else {
      // TODO implement other game controllers
    }

    currentRoute = route;


  }

  public static void showPauseMenu() {
    primaryScene.addNode(pauseMenu);
  }
}

