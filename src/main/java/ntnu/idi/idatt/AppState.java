package ntnu.idi.idatt;

import ntnu.idi.idatt.models.GameConfig;
import ntnu.idi.idatt.models.GameInfo;
import ntnu.idi.idatt.core.GameRegistry;

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

  public static void setSelectedGame(GameInfo selectedGame) {
    AppState.selectedGame = selectedGame;
  }

  public static GameConfig getCurrentGameConfig() {
    return currentGameConfig;
  }

  public static void setCurrentGameConfig(GameConfig currentGameConfig) {
    AppState.currentGameConfig = currentGameConfig;
  }

  public static double getVolume() {
    return volume;
  }

  public static void setVolume(double volume) {
    AppState.volume = volume;
  }
}
