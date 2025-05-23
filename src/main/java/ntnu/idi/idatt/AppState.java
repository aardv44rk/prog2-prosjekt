package ntnu.idi.idatt;

import ntnu.idi.idatt.models.GameConfig;
import ntnu.idi.idatt.models.GameInfo;
import ntnu.idi.idatt.utility.ArgumentValidator;
import ntnu.idi.idatt.core.GameRegistry;
import ntnu.idi.idatt.exceptions.GameLogicException;

/**
 * Class representing the application state. It holds the current game configuration, selected game,
 * and volume settings.
 */
public class AppState {

  private static GameInfo selectedGame = GameRegistry.games.getFirst();
  private static GameConfig currentGameConfig;
  private static double volume;

  public static GameInfo getSelectedGame() {
    return selectedGame;
  }

  /**
   * Sets the selected game. Throws an exception if the game is null.
   *
   * @param selectedGame The selected game to set.
   * @throws GameLogicException if the selected game is null.
   */
  public static void setSelectedGame(GameInfo selectedGame) {
    if (!ArgumentValidator.isValidObject(selectedGame)) {
      throw new GameLogicException("Selected game cannot be null");
    }
    AppState.selectedGame = selectedGame;
  }

  public static GameConfig getCurrentGameConfig() {
    return currentGameConfig;
  }

  /**
   * Sets the current game configuration. Throws an exception if the configuration is null.
   *
   * @param currentGameConfig The current game configuration to set.
   * @throws GameLogicException if the current game configuration is null.
   */
  public static void setCurrentGameConfig(GameConfig currentGameConfig) {
    if (!ArgumentValidator.isValidObject(currentGameConfig)) {
      throw new GameLogicException("Current game configuration cannot be null");
    }
    AppState.currentGameConfig = currentGameConfig;
  }

  public static double getVolume() {
    return volume;
  }

  public static void setVolume(double volume) {
    if (!ArgumentValidator.isValidDouble(volume)) {
      throw new GameLogicException("Volume must be between 0.0 and 1.0");
    }
    AppState.volume = volume;
  }
}
