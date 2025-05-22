package ntnu.idi.idatt.core;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

import javafx.stage.FileChooser;
import javafx.stage.Stage;
import ntnu.idi.idatt.AppState;
import ntnu.idi.idatt.components.AlertDialog;
import ntnu.idi.idatt.components.PauseMenu;
import ntnu.idi.idatt.models.GameConfig;
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
    pauseMenu.saveGameButtonSetOnClick(() -> {
      GameConfig currentGameConfig = AppState.getCurrentGameConfig();
      if (currentGameConfig == null) {
        Router.showAlert("Info", "No game data found to save", "OK", null);
        System.err.println("No game data found to save");
        return;
      }
      FileChooser fileChooser = new FileChooser();
      fileChooser.setTitle("Save Game");
      fileChooser.getExtensionFilters().addAll(
          new FileChooser.ExtensionFilter("JSON files (*.json)", "*.json")
      );
      String gameName = AppState.getSelectedGame() != null ? AppState.getSelectedGame().getName() : "game";
      String initialFileName = gameName.toLowerCase().replace(" ", "_") + "_save.json";
      fileChooser.setInitialFileName(initialFileName);
      
      File file = fileChooser.showSaveDialog(primaryStage);
      if (file != null) {
        try {
          currentGameConfig.saveConfig(file.getAbsolutePath());
          showAlert("Success", "Game saved successfully", "OK", null);
          System.out.println("Game saved to: " + file.getAbsolutePath());
          primaryScene.removeNode(pauseMenu);
        } catch (IOException e) {
          showAlert("Error", "Could not save game state: " + e.getMessage(), "OK", null);
          System.err.println("Error saving game: " + e.getMessage());
        }
      } else {
        showAlert("Error", "No game data to save!", "OK", null);
        System.err.println("No game data to save!");
      }
    });
    pauseMenu.savePlayersButtonSetOnClick(() -> {
      GameConfig currentGameConfig = AppState.getCurrentGameConfig();
      if (currentGameConfig == null || currentGameConfig.getPlayers() == null || currentGameConfig.getPlayers().isEmpty()) {
        Router.showAlert("Info", "No player data found to save", "OK", null);
        System.err.println("No player data found to save");
        return;
      }
      FileChooser fileChooser = new FileChooser();
      fileChooser.setTitle("Save Players");
      fileChooser.getExtensionFilters().addAll(
          new FileChooser.ExtensionFilter("CSV Files", "*.csv")
      );
      fileChooser.setInitialFileName("player_list.csv");

      File file = fileChooser.showSaveDialog(primaryStage);
      if (file != null) {
        try {
          currentGameConfig.savePlayerList(file.getAbsolutePath());
          showAlert("Success", "Players saved successfully", "OK", null);
          System.out.println("Players saved to: " + file.getAbsolutePath());
        } catch (IOException e) {
          showAlert("Error", "Could not save players: " + e.getMessage(), "OK", null);
          System.err.println("Error saving players: " + e.getMessage());
        }
      } else {
        showAlert("Error", "No player data to save!", "OK", null);
        System.err.println("No player data to save!");
      }
    });
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

