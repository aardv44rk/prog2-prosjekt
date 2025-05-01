package ntnu.idi.idatt.UI.views;

import java.util.Map;
import java.util.function.Supplier;
import javafx.scene.Parent;
import ntnu.idi.idatt.games.ludo.LudoView;
import ntnu.idi.idatt.games.snakesandladders.SnakesAndLaddersView;
import ntnu.idi.idatt.models.GameInfo;
import ntnu.idi.idatt.models.GameRegistry;

public class ViewFactory {

  private static final Map<GameInfo, Supplier<Parent>> gameViewMap = Map.of(
      GameRegistry.games.get(0), SnakesAndLaddersView::new,
      GameRegistry.games.get(1), LudoView::new
  );

  public static Parent getGameView(GameInfo gameInfo) {
    Supplier<Parent> viewSupplier = gameViewMap.get(gameInfo);
    if (viewSupplier == null) {
      throw new IllegalArgumentException("Unknown game info: " + gameInfo);
    }
    return viewSupplier.get();
  }
}