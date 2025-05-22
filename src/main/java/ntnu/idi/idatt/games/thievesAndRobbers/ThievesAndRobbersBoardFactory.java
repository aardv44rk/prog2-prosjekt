package ntnu.idi.idatt.games.thievesandrobbers;

import java.util.List;
import ntnu.idi.idatt.models.Board;

public class ThievesAndRobbersBoardFactory {

  public static Board createSmallBoard() {
    return new ThievesAndRobbersBoard(8, 6);
  }

  public static Board createStandardBoard() {
    return new ThievesAndRobbersBoard(8, 8);
  }

  public static Board createBigBoard() {
    return new ThievesAndRobbersBoard(10, 10);
  }

  public static List<Board> getBoards() {
    return List.of(createSmallBoard(), createStandardBoard(), createBigBoard());
  }

}
