package ntnu.idi.idatt.menu.gameload;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javafx.scene.Parent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import ntnu.idi.idatt.AppState;
import ntnu.idi.idatt.core.Router;
import ntnu.idi.idatt.games.snakesandladders.SnakesAndLaddersBoardFactory;
import ntnu.idi.idatt.models.Board;
import ntnu.idi.idatt.models.GameConfig;
import ntnu.idi.idatt.models.Player;

/**
 * Controller for the GameLoadView. It handles the logic for loading a game or starting a new one.
 */
public class GameLoadController {

  private final GameLoadView view;

  /**
   * Constructor for the GameLoadController.
   *
   * @param view The GameLoadView instance.
   */
  public GameLoadController(GameLoadView view) {
    this.view = view;
    setupEventHandlers();
  }

  public Parent getView() {
    return view;
  }

  /**
   * Sets up the event handlers for the buttons in the GameLoadView.
   */
  private void setupEventHandlers() {
    view.newGameButtonSetOnClick(() -> Router.navigateTo("setup"));
    view.loadGameButtonSetOnClick(() -> {
      FileChooser fileChooser = new FileChooser();
      fileChooser.setTitle("Load Game");
      fileChooser.getExtensionFilters().addAll(
        new FileChooser.ExtensionFilter("JSON files (*.json)", "*.json")
      );
      Stage stage  = Router.getStage();
      File selectedFile = fileChooser.showOpenDialog(stage);

      if (selectedFile != null) {
        try {
          List<Player> players = new ArrayList<>();
          players.add(new Player("Dummy", new ArrayList<>()));
          Board board = SnakesAndLaddersBoardFactory.createSmallBoard();
          GameConfig loaderConfig = new GameConfig(players, board, 0);

          GameConfig loadedConfig = loaderConfig.loadConfig(selectedFile.getAbsolutePath());
          AppState.setCurrentGameConfig(loadedConfig);
          Router.navigateTo(AppState.getSelectedGame().getName());
        } catch (IOException e) {
          System.err.println("Error loading game: " + e.getMessage());
          Router.showAlert("Error", "Could not load game state: " + e.getMessage(), "OK", null);
        }       
      }
    });
  }
}
