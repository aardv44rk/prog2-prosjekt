package ntnu.idi.idatt.models;

import java.util.List;
import java.util.function.Supplier;

public class GameInfo {

  private final String name;
  private final String rules;
  private final int playerMin;
  private final int playerMax;
  private final Supplier<List<Board>> boardOptionsSupplier;

  public GameInfo(String name, String rules, int playerMin, int playerMax,
      Supplier<List<Board>> boardOptionsSupplier) {
    this.name = name;
    this.rules = rules;
    this.playerMin = playerMin;
    this.playerMax = playerMax;
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

  public List<Board> getBoardOptions() {
    return boardOptionsSupplier.get();
  }
}
