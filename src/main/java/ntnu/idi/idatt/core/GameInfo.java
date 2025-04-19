package ntnu.idi.idatt.core;

import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;

public class GameInfo {

  private final String name;
  private final String rules;
  private final int playerMin;
  private final int playerMax;
  private final Function<GameConfig, GameEngine> engineFactory;
  private final Supplier<List<Board>> boardOptionsSupplier;

  public GameInfo(String name, String rules, int playerMin, int playerMax,
      Function<GameConfig, GameEngine> engineFactory, Supplier<List<Board>> boardOptionsSupplier) {
    this.name = name;
    this.rules = rules;
    this.playerMin = playerMin;
    this.playerMax = playerMax;
    this.engineFactory = engineFactory;
    this.boardOptionsSupplier = boardOptionsSupplier;
  }

  public String getName() {
    return name;
  }

  public String getRules() {
    return rules;
  }

  public int getPlayerMin() {
    return playerMin;
  }

  public int getPlayerMax() {
    return playerMax;
  }

  public Function<GameConfig, GameEngine> getEngineFactory() {
    return engineFactory;
  }

  public Supplier<List<Board>> getBoardOptionsSupplier() {
    return boardOptionsSupplier;
  }

  public GameEngine createEngine(GameConfig gameConfig) {
    return engineFactory.apply(gameConfig);
  }

  public List<Board> getBoardOptions() {
    return boardOptionsSupplier.get();
  }
}
