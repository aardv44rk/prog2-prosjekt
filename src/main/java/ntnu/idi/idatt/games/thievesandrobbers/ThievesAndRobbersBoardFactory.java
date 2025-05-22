package ntnu.idi.idatt.games.thievesandrobbers;

import java.util.List;
import ntnu.idi.idatt.models.Board;

/**
 * Factory class for creating Thieves and Robbers game boards.
 * This class provides methods to create different sizes of game boards.
 */
public class ThievesAndRobbersBoardFactory {

  /**
   * Creates a small Thieves and Robbers game board.
   *
   * @return A ThievesAndRobbersBoard object representing a small board.
   */
  public static Board createSmallBoard() {
    return new ThievesAndRobbersBoard(8, 6);
  }

  /**
   * Creates a standard Thieves and Robbers game board.
   *
   * @return A ThievesAndRobbersBoard object representing a standard board.
   */
  public static Board createStandardBoard() {
    return new ThievesAndRobbersBoard(8, 8);
  }

  /**
   * Creates a big Thieves and Robbers game board.
   *
   * @return A ThievesAndRobbersBoard object representing a big board.
   */
  public static Board createBigBoard() {
    return new ThievesAndRobbersBoard(10, 10);
  }

  /**
   * Returns a list of available Thieves and Robbers game boards.
   *
   * @return A list of ThievesAndRobbersBoard objects representing different board sizes.
   */
  public static List<Board> getBoards() {
    return List.of(createSmallBoard(), createStandardBoard(), createBigBoard());
  }

}
