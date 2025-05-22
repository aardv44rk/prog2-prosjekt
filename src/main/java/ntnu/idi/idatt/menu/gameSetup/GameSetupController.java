package ntnu.idi.idatt.menu.gamesetup;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javafx.scene.Parent;
import javafx.stage.FileChooser;
import ntnu.idi.idatt.AppState;
import ntnu.idi.idatt.core.Router;
import ntnu.idi.idatt.games.snakesandladders.SnakesAndLaddersBoardFactory;
import ntnu.idi.idatt.models.Board;
import ntnu.idi.idatt.models.GameConfig;
import ntnu.idi.idatt.models.GameInfo;
import ntnu.idi.idatt.models.Player;

/**
 * Controller for the GameSetupView. It handles the logic for setting up a game.
 */
public class GameSetupController {

  private final GameSetupView view;
  private GameInfo currentGameInfo;

  /**
   * Constructor for the GameSetupController.
   *
   * @param view The GameSetupView instance.
   */
  public GameSetupController(GameSetupView view) {
    this.view = view;
  }

  /**
   * Returns the view for setting up a game. It sets the players and boards based on the provided
   * GameInfo.
   *
   * @param gameInfo The GameInfo object containing game details.
   * @return The Parent object representing the view.
   */
  public Parent getViewForGame(GameInfo gameInfo) {
    this.currentGameInfo = gameInfo;
    view.setPlayers(gameInfo.getPlayerMin(), gameInfo.getPlayerMax());
    view.setBoards(gameInfo.getBoardOptions());
    setupEventHandlers();
    return view;
  }

  /**
   * Sets up the event handlers for the buttons in the GameSetupView.
   */
  private void setupEventHandlers() {
    view.startButtonSetOnClick(() -> {
      AppState.setCurrentGameConfig(
          new GameConfig(
              view.getPlayers(),
              view.getSelectedBoard(),
              0
          )
      );
      Router.navigateTo(AppState.getSelectedGame().getName());
    });

    view.loadPlayersButtonSetOnClick(() -> {
      FileChooser fileChooser = new FileChooser();
      fileChooser.setTitle("Load Players");
      fileChooser.getExtensionFilters().addAll(
          new FileChooser.ExtensionFilter("CSV Files", "*.csv")
      );
      File selectedFile = fileChooser.showOpenDialog(Router.getStage());

      if (selectedFile != null) {
        try {
          List<Player> dummyPlayers = new ArrayList<>();
          dummyPlayers.add(new Player("Dummy", new ArrayList<>()));
          Board dummyBoard = SnakesAndLaddersBoardFactory.createSmallBoard();
          GameConfig tempConfig = new GameConfig(dummyPlayers, dummyBoard, 0);

          List<Player> loadedPlayers = tempConfig.loadPlayerList(selectedFile.getAbsolutePath());

          if (loadedPlayers.isEmpty()) {
            Router.showAlert("Info", "No players found in the file", "OK", null);
          } else if (loadedPlayers.size() > currentGameInfo.getPlayerMax()) {
            Router.showAlert("Error", "Too many players in the file. Max: " + currentGameInfo.getPlayerMax(), "OK", null);
            view.setLoadedPlayers(loadedPlayers.subList(0, currentGameInfo.getPlayerMax()), currentGameInfo.getPlayerMin(), currentGameInfo.getPlayerMax());
          } else {
            view.setLoadedPlayers(loadedPlayers, currentGameInfo.getPlayerMin(), currentGameInfo.getPlayerMax());
            Router.showAlert("Success", "Players loaded successfully", "OK", null);
          }

        } catch (Exception e) {
          System.err.println("Error loading players: " + e.getMessage());
          Router.showAlert("Error", "Could not load player data: " + e.getMessage(), "OK", null);
        }
      }
    });
  }
}
