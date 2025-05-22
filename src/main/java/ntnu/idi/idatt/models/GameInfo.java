package ntnu.idi.idatt.models;

import java.util.List;
import java.util.function.Supplier;
import ntnu.idi.idatt.exceptions.InvalidInputException;
import ntnu.idi.idatt.utility.ArgumentValidator;

/**
 * Represents information about a game, including its name, rules, player
 * limits, and engine
 * factory.
 */
public class GameInfo {

  private final String name;
  private final String rules;
  private final int playerMin;
  private final int playerMax;
  private final Supplier<List<Board>> boardOptionsSupplier;

  /**
   * Constructor for GameInfo.
   *
   * @param name                 the name of the game
   * @param rules                the rules of the game
   * @param playerMin            the minimum number of players
   * @param playerMax            the maximum number of players
   * @param boardOptionsSupplier the supplier for board options
   */
  public GameInfo(
      String name, String rules, int playerMin, int playerMax,
      Supplier<List<Board>> boardOptionsSupplier) {
    if (!isValidGameInfo(name, rules, playerMin, playerMax, boardOptionsSupplier)) {
      throw new InvalidInputException("Invalid game info parameters");
    }
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

  /**
   * Validates the game information parameters.
   *
   * @param name                 The name of the game.
   * @param rules                The rules of the game.
   * @param playerMin            The minimum number of players.
   * @param playerMax            The maximum number of players.
   * @param engineFactory        The factory function to create the game engine.
   * @param boardOptionsSupplier The supplier for board options.
   * @return true if all parameters are valid, false otherwise.
   */
  public boolean isValidGameInfo(
      String name, String rules, int playerMin, int playerMax,
      Supplier<List<Board>> boardOptionsSupplier) {
    return ArgumentValidator.isValidString(name)
        && ArgumentValidator.isValidString(rules)
        && ArgumentValidator.isPositiveInteger(playerMin)
        && ArgumentValidator.isPositiveInteger(playerMax)
        && ArgumentValidator.isValidObject(boardOptionsSupplier);
  }
}
