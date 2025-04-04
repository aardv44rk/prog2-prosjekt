package ntnu.idi.idatt;

import ntnu.idi.idatt.core.GameInfo;
import ntnu.idi.idatt.core.GameRegistry;

public class AppState {

  private static GameInfo selectedGame = GameRegistry.games.get(0);
  private static double volume;

  public static GameInfo getSelectedGame() {
    return selectedGame;
  }

  public static void setSelectedGame(GameInfo selectedGame) {
    AppState.selectedGame = selectedGame;
  }

  public static double getVolume() {
    return volume;
  }

  public static void setVolume(double volume) {
    AppState.volume = volume;
  }
}
